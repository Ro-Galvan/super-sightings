package com.sg.supersightings.dao;

import com.sg.supersightings.dto.Organization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrganizationDaoImplTest {
    @Autowired
    OrganizationDao orgDao;

    @BeforeEach
    public void setUp() {
        List<Organization> orgs = orgDao.getAllOrganizations();
        for (Organization org: orgs) {
            orgDao.deleteOrganizationById(org.getOrgId());
        }
    }

    @Test
    public void testAddAndGetNewOrganization(){
        Organization org = new Organization();
        org.setOrgName("test name");
        org.setOrgDes("test des");
        org.setOrgAddress("test address");
        org.setOrgNumber("123-123-1234");
        org.setEvil(false);
        Organization addedOrg = orgDao.addOrganization(org);

        Organization fetchedOrg = orgDao.getOrganizationById(addedOrg.getOrgId());

        assertEquals(addedOrg, fetchedOrg);
    }


    @Test
    public void testGetAllOrganizations(){
        Organization heroOrg = new Organization();
        heroOrg.setOrgName("hero org");
        heroOrg.setOrgDes("hero des");
        heroOrg.setOrgAddress("hero address");
        heroOrg.setOrgNumber("123-123-1234");
        heroOrg.setEvil(false);
        Organization addedHero = orgDao.addOrganization(heroOrg);

        Organization villainOrg = new Organization();
        villainOrg.setOrgName("villain org");
        villainOrg.setOrgDes("villain des");
        villainOrg.setOrgAddress("villain address");
        villainOrg.setOrgNumber("321-321-4321");
        villainOrg.setEvil(true);
        Organization addedVillain = orgDao.addOrganization(villainOrg);

        List<Organization> orgs = orgDao.getAllOrganizations();

        assertEquals(2, orgs.size());
        assertTrue(orgs.contains(addedHero));
        assertTrue(orgs.contains(addedVillain));
    }

    @Test
    public void testUpdateOrganizationById(){
        Organization org = new Organization();
        org.setOrgName("test name");
        org.setOrgDes("test des");
        org.setOrgAddress("test address");
        org.setOrgNumber("123-123-1234");
        org.setEvil(false);
        Organization addedOrg = orgDao.addOrganization(org);

        Organization fetchedOrg = orgDao.getOrganizationById(addedOrg.getOrgId());

        // check that the original org was not evil
        assertFalse(fetchedOrg.isEvil());

        // set org's update to be evil
        org.setEvil(true);
        orgDao.updateOrganization(org);

        fetchedOrg = orgDao.getOrganizationById(addedOrg.getOrgId());

        // check that the organization updated to be evil
        assertTrue(fetchedOrg.isEvil());
    }

    @Test
    public void testDeleteOrganizationById(){
        Organization org = new Organization();
        org.setOrgName("test name");
        org.setOrgDes("test des");
        org.setOrgAddress("test address");
        org.setOrgNumber("123-123-1234");
        org.setEvil(false);
        Organization addedOrg = orgDao.addOrganization(org);

        List<Organization> orgs = orgDao.getAllOrganizations();

        assertEquals(1, orgs.size());

        orgDao.deleteOrganizationById(addedOrg.getOrgId());

        orgs = orgDao.getAllOrganizations();

        assertEquals(0, orgs.size());
    }
}