package com.arjun.identityreconciliation.repository;

import com.arjun.identityreconciliation.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findByEmailOrPhoneNumber(String email, String phoneNumber);

    List<Contact> findAllByLinkedIdOrId(Long linkedId, Long id);
}
