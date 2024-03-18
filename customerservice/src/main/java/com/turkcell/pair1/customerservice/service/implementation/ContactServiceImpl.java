package com.turkcell.pair1.customerservice.service.implementation;

import com.turkcell.pair1.customerservice.repository.ContactRepository;
import com.turkcell.pair1.customerservice.service.abstraction.ContactService;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
}
