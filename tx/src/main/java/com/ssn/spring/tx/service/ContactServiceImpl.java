package com.ssn.spring.tx.service;

import com.ssn.spring.tx.entity.Contact;
//import com.ssn.spring.tx.repo.ContactRepository;
import com.ssn.spring.tx.repo.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("contactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    @Transactional(readOnly=true)
    public List<Contact> findAll() {
        List<Contact> contacts = new ArrayList<>();
        contactRepository.findAll().forEach(contacts::add);

        return contacts;
    }

    @Override
    @Transactional(readOnly=true)
    public Contact findById(Long id) {
        return contactRepository.findById(id).get();
    }

    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public long countAll() {
        return contactRepository.countAllContacts();
    }


}

