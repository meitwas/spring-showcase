package com.ssn.spring.tx.service;

import com.ssn.spring.tx.entity.Contact;

import java.util.List;

public interface ContactService extends CountService {
    List<Contact> findAll();
    Contact findById(Long id);
    Contact save(Contact contact);
}
