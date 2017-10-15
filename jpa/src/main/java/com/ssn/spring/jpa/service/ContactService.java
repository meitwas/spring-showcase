package com.ssn.spring.jpa.service;

import com.ssn.spring.jpa.entity.Contact;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ContactService {
    List<Contact> findAll();
    List<Contact> findAllWithDetail();
    Contact findById(Long id);
    Contact save(Contact contact);
    void delete(Contact contact);
    List<Contact> findAllByNativeQuery();

    @Transactional(readOnly=true)
    List<Contact> findByCriteriaQuery(String firstName, String lastName);
}
