package com.arjun.identityreconciliation.controller;

import com.arjun.identityreconciliation.dto.IdentifyRequest;
import com.arjun.identityreconciliation.dto.IdentifyResponse;
import com.arjun.identityreconciliation.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/identify")
public class IdentifyController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<IdentifyResponse> identify(@RequestBody IdentifyRequest request) {
        IdentifyResponse response = contactService.processRequest(request);
        return ResponseEntity.ok(response);
    }
}
