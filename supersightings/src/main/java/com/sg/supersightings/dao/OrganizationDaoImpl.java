package com.sg.supersightings.dao;

import com.sg.supersightings.dto.Organization;
import com.sg.supersightings.mapper.OrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
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
        final String sql = "INSERT INTO org(orgName, orgDes, orgAddress, orgNumber, isEvil) "
                +"VALUES(?, ?, ?, ?, ?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update((Connection conn ) -> {
            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, org.getOrgName());
            statement.setString(2, org.getOrgDes());
            statement.setString(3, org.getOrgAddress());
            statement.setString(4, org.getOrgNumber());
            statement.setBoolean(5, org.isEvil());
            return statement;
        }, keyHolder);

        org.setOrgId(keyHolder.getKey().intValue());

        return org;
    }

    @Override
    public void updateOrganization(Organization org) {

    }

    @Override
    public void deleteOrganizationById(int orgId) {

    }
}

