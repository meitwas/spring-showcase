package com.ssn.spring.jdbc;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcContactDaoSupport extends JdbcDaoSupport implements ContactDao {

    @Override
    public String findLastNameById(Long id) {
        String sql = "select last_name from contact where id = ?";


        return getJdbcTemplate().queryForObject(sql,
                new Object[] {id}, String.class);
    }

    @Override
    public List<Contact> findAll() {
        String sql = "select id, first_name, last_name, birth_date from contact";
        return getJdbcTemplate().query(sql, (rs, rowNum) -> {
            Contact contact = new Contact();
            contact.setId(rs.getLong("id"));
            contact.setLastName(rs.getString("last_name"));
            contact.setFirstName(rs.getString("first_name"));
            contact.setBirthDate(rs.getDate("birth_date"));

            return contact;
        });
    }

    @Override
    public List<Contact> findAllWithDetail() {
        String sql = "select c.id, c.first_name, c.last_name, c.birth_date" +
                ", t.id as contact_tel_id, t.tel_type, t.tel_number from contact c " +
                "left join contact_tel_detail t on c.id = t.contact_id";

        return getJdbcTemplate().query(sql, (ResultSet rs) -> {
            Map<Long, Contact> map = new HashMap<Long, Contact>();
            Contact contact = null;

            while (rs.next()) {
                Long id = rs.getLong("id");
                contact = map.get(id);

                if (contact == null) {
                    contact = new Contact();
                    contact.setId(id);
                    contact.setFirstName(rs.getString("first_name"));
                    contact.setBirthDate(rs.getDate("birth_date"));
                    contact.setLastName(rs.getString("last_name"));
                    contact.setContactTelDetails(new ArrayList<ContactTelDetail>());
                    map.put(id, contact);
                }

                Long contactTelDetailId = rs.getLong("contact_tel_id");

                if (contactTelDetailId > 0) {
                    ContactTelDetail contactTelDetail = new ContactTelDetail();
                    contactTelDetail.setId(contactTelDetailId);
                    contactTelDetail.setContactId(id);
                    contactTelDetail.setTelNumber(rs.getString("tel_number"));
                    contactTelDetail.setTelType(rs.getString("tel_type"));
                    contact.getContactTelDetails().add(contactTelDetail);
                }
            }

            return new ArrayList<Contact> (map.values());
        });
    }
}
