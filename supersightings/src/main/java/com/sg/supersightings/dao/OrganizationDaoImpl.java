package com.sg.supersightings.dao;

import com.sg.supersightings.dto.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrganizationDaoImpl implements OrganizationDao {
    private final JdbcTemplate jdbc;
    @Autowired
    public OrganizationDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }
    @Override
    public List<Organization> getAllOrganizations() {
        return null;
    }

    @Override
    public Organization getOrganizationById(int orgId) {
        return null;
    }

    @Override
    public Organization addOrganization(Organization org) {
        return null;
    }

    @Override
    public void updateOrganization(Organization org) {

    }

    @Override
    public void deleteOrganizationById(int orgId) {

    }
}

