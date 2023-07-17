package com.sg.supersightings.dao;

import com.sg.supersightings.dto.Organization;

import java.util.List;

public interface OrganizationDao {
    List<Organization> getAllOrganizations();
    Organization getOrganizationById(int orgId);
    Organization addOrganization(Organization org);
    void updateOrganization(Organization org);
    void deleteOrganizationById(int orgId);
}
