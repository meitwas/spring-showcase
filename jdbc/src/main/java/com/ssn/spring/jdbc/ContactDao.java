package com.ssn.spring.jdbc;

import java.util.List;

public interface ContactDao {
    List<Contact> findAll();
    String findLastNameById(Long id);

    List<Contact> findAllWithDetail();
}
