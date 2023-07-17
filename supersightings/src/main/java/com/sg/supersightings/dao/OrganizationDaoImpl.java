package com.sg.supersightings.dao;

import com.sg.supersightings.dto.Organization;
import com.sg.supersightings.mapper.OrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
        final String sql = "SELECT * FROM org";
        return jdbc.query(sql, new OrganizationMapper());
    }

    @Override
    public Organization getOrganizationById(int orgId) {
        try {
            final String sql = "SELECT orgID, orgName, orgDes, orgAddress, orgNumber, isEvil "
                    +"FROM org "
                    +"WHERE orgID = ?";
            return jdbc.queryForObject(sql, new OrganizationMapper(), orgId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
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

