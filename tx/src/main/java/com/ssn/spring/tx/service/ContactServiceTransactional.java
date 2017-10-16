package com.ssn.spring.tx.service;

import com.ssn.spring.tx.entity.Contact;
import com.ssn.spring.tx.repo.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("contactServiceTransactional")
@Repository
@Transactional
public class ContactServiceTransactional implements CountService {

    @Autowired
    @Qualifier("contactService")
    private ContactService contactService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public long countAll() {
        return contactService.countAll();
    }
}
