package com.arjun.identityreconciliation.service;

import com.arjun.identityreconciliation.dto.IdentifyRequest;
import com.arjun.identityreconciliation.dto.IdentifyResponse;
import com.arjun.identityreconciliation.model.Contact;
import com.arjun.identityreconciliation.model.LinkPrecedence;
import com.arjun.identityreconciliation.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public IdentifyResponse processRequest(IdentifyRequest request) {
        String email = request.getEmail();
        String phone = request.getPhoneNumber();

        List<Contact> matchedContacts = contactRepository.findByEmailOrPhoneNumber(email, phone);

        Contact primary = null;

        if (matchedContacts.isEmpty()) {
            // Case 1: Create new Primary
            Contact newContact = new Contact();
            newContact.setEmail(email);
            newContact.setPhoneNumber(phone);
            newContact.setLinkPrecedence(LinkPrecedence.PRIMARY);
            newContact.setCreatedAt(LocalDateTime.now());
            newContact.setUpdatedAt(LocalDateTime.now());

            primary = contactRepository.save(newContact);
        } else {
            // Case 2: Find oldest primary contact
            primary = matchedContacts.stream()
                    .filter(c -> c.getLinkPrecedence() == LinkPrecedence.PRIMARY)
                    .min(Comparator.comparing(Contact::getCreatedAt))
                    .orElseThrow();

            for (Contact contact : matchedContacts) {
                if (contact.getLinkPrecedence() == LinkPrecedence.PRIMARY && !contact.getId().equals(primary.getId())) {
                    contact.setLinkPrecedence(LinkPrecedence.SECONDARY);
                    contact.setLinkedId(primary.getId());
                    contact.setUpdatedAt(LocalDateTime.now());
                    contactRepository.save(contact);
                }
            }

            // If this email or phone is new, insert as secondary
            boolean exists = matchedContacts.stream().anyMatch(c ->
                    Objects.equals(c.getEmail(), email) && Objects.equals(c.getPhoneNumber(), phone));

            if (!exists) {
                Contact secondary = new Contact();
                secondary.setEmail(email);
                secondary.setPhoneNumber(phone);
                secondary.setLinkPrecedence(LinkPrecedence.SECONDARY);
                secondary.setLinkedId(primary.getId());
                secondary.setCreatedAt(LocalDateTime.now());
                secondary.setUpdatedAt(LocalDateTime.now());
                contactRepository.save(secondary);
            }
        }

        // Get all linked contacts
        List<Contact> all = contactRepository.findAllByLinkedIdOrId(primary.getId(), primary.getId());

        Set<String> emails = all.stream().map(Contact::getEmail).filter(Objects::nonNull).collect(Collectors.toSet());
        Set<String> phones = all.stream().map(Contact::getPhoneNumber).filter(Objects::nonNull).collect(Collectors.toSet());
        List<Long> secondaryIds = all.stream()
                .filter(c -> c.getLinkPrecedence() == LinkPrecedence.SECONDARY)
                .map(Contact::getId)
                .toList();

        IdentifyResponse res = new IdentifyResponse();
        res.setPrimaryContactId(primary.getId());
        res.setEmails(emails);
        res.setPhoneNumbers(phones);
        res.setSecondaryContactIds(secondaryIds);

        return res;
    }
}
