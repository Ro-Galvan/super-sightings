
package com.sg.supersightings.dao;

import com.sg.supersightings.dto.Location;
import com.sg.supersightings.dto.Sighting;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;


public class LocationDaoImplTest {
    
    @Autowired
    LocationDao locationDao;
    
    @Autowired
    SightingDao sightingDao;
    
    @Autowired
    SuperDao superDao;
    
    public LocationDaoImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<Location> locationToDelete = locationDao.getAllLocations();
        for (Location l: locationToDelete) {
            locationDao.deleteLocationById(l.getLocationID());
        }

        List<Sighting> sightings = sightingDao.getAllSighting();
        for(Sighting sighting : sightings) {
            sightingDao.deleteSightingById(sighting.getSightingID());
        }

//        List<Super> supers = superDao.getAllSupers();
//        for(Super heroVillain : supers) {
//            superDao.deleteSuperById(heroVillain.getId());
//        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    void testAddAndGetLocation() {
        Location location = new Location();
        location.setLocationName("Test Location Name");
        location.setLocationDesc("Test Location Description");
        location.setLocationAddress("Test Location Address");
        location.setLatitude("40.6892");
        location.setLongitude("-74.0445");

        Location fromDao = locationDao.getLocationById(location.getLocationID());

        assertEquals(location, fromDao);
    }

    @Test
    void testGetAllLocations() {
        Location location1 = new Location();
        location1.setLocationName("Test Location Name1");
        location1.setLocationDesc("Test Location Description1");
        location1.setLocationAddress("Test Location Address1");
        location1.setLatitude("40.6892");
        location1.setLongitude("-74.0445");
        location1 = locationDao.addLocation(location1);

        Location location2 = new Location();
        location2.setLocationName("Test Location Name2");
        location2.setLocationDesc("Test Location Description2");
        location2.setLocationAddress("Test Location Address2");
        location2.setLatitude("42.9876");
        location2.setLongitude("-76.8374");
        location2 = locationDao.addLocation(location2);

        List<Location> locations = locationDao.getAllLocations();

        assertEquals(2, locations.size());
        assertTrue(locations.contains(location1));
        assertTrue(locations.contains(location2));
    }

    @Test
    void testUpdateLocation() {
        Location location = new Location();
        location.setLocationName("Test Location Name");
        location.setLocationDesc("Test Location Description");
        location.setLocationAddress("Test Location Address");
        location.setLatitude("40.6892");
        location.setLongitude("-74.0445");
        location = locationDao.addLocation(location);

        Location fromDao = locationDao.getLocationById(location.getLocationID());
        assertEquals(location, fromDao);

        location.setLocationName("New Location Name");
        locationDao.updateLocation(location);

        assertNotEquals(location, fromDao);

        fromDao = locationDao.getLocationById(location.getLocationID());

        assertEquals(location, fromDao);
    }

    @Test
    void testDeleteLocationById() {
//        Location location = new Location();
//        location.setLocationName("Test Location Name");
//        location.setLocationDesc("Test Location Description");
//        location.setLocationAddress("Test Location Address");
    }
    
}
