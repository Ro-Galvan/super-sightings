
package com.sg.supersightings.dao;

import com.sg.supersightings.dto.Location;
import com.sg.supersightings.dto.Sighting;
import com.sg.supersightings.dto.Super;
import com.sg.supersightings.dto.Superpower;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SightingDaoImplTest {
    
    @Autowired
    SightingDao sightingDao;
    
    @Autowired
    LocationDao locationDao;
    
    @Autowired
    SuperDao superDao;
    
    @Autowired
    SuperpowerDao superpowerDao;
    
    public SightingDaoImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<Sighting> sightingToDelete = sightingDao.getAllSighting();
        for (Sighting s: sightingToDelete) {
            sightingDao.deleteSightingById(s.getSightingID());
        }
        List<Location> locationToDelete = locationDao.getAllLocations();
        for (Location l: locationToDelete) {
            locationDao.deleteLocationById(l.getLocationID());
        }
        List<Super> superToDelete = superDao.getAllSuperCharacters();
        for (Super character: superToDelete) {
            superDao.deleteSuper(character.getSuperID());
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testRecordSuperSighted() {
        Superpower sp = new Superpower();
        sp.setSuperpowerId(1);
        sp.setSuperpowerName("Superpower Name Test");
        superpowerDao.addSuperpower(sp);        
        
        Super character = new Super();
        character.setSuperID(1);
        character.setSuperDescription("super Description");
        character.setSuperName("Captain America");
        character.setEvil(Boolean.FALSE);
        character.setSuperpowerID(sp.getSuperpowerId());
        superDao.createSuper(character);
        
        Location location = new Location();
        location.setLocationID(1);
        location.setLocationName("Location Test");
        location.setLocationDesc("locationDesc");
        location.setLocationAddress("Location Address");
        location.setLatitude("latitude test");
        location.setLongitude("longitude test");
        locationDao.addLocation(location);
        
        Sighting s = new Sighting();
        s.setSightingID(200);
        s.setCharacter(character);
        s.setDateSighted(LocalDateTime.now());
        s.setLocation(location);
        
        Sighting sightingAdded = sightingDao.recordSuperSighted(s);
        Sighting sightingFromDao = sightingDao.getSightedById(sightingAdded.getSightingID());
        
        assertNotNull(sightingAdded, "The Sighting object is null");
        assertNotNull(sightingFromDao, "The Sighting object from DAO is null");
        
        assertEquals(sightingAdded, sightingFromDao, "Object added is not equal");        
    }

    @Test
    public void testGetAllSightedByLocation() {
        
        Superpower sp = new Superpower();
        sp.setSuperpowerId(1);
        sp.setSuperpowerName("Superpower Name Test");
        superpowerDao.addSuperpower(sp);
        
        Super character = new Super();
        character.setSuperID(1);
        character.setSuperDescription("super Description");
        character.setSuperName("Captain America");
        character.setEvil(Boolean.FALSE);
        character.setSuperpowerID(sp.getSuperpowerId());
        superDao.createSuper(character);
        
        Location location = new Location();
        location.setLocationID(1);
        location.setLocationName("Location Test");
        location.setLocationDesc("locationDesc");
        location.setLocationAddress("Location Address");
        location.setLatitude("latitude test");
        location.setLongitude("longitude test");
        locationDao.addLocation(location);
        
        Sighting s1 = new Sighting();
        s1.setSightingID(200);
        s1.setCharacter(character);
        s1.setDateSighted(LocalDateTime.now());
        s1.setLocation(location);
        
        Sighting s2 = new Sighting();
        s2.setSightingID(201);
        s2.setCharacter(character);
        s2.setDateSighted(LocalDateTime.now());
        s2.setLocation(location);
        
        Sighting s3 = new Sighting();
        s3.setSightingID(202);
        s3.setCharacter(character);
        s3.setDateSighted(LocalDateTime.now());
        s3.setLocation(location);
        
        Sighting sightingAdded1 = sightingDao.recordSuperSighted(s1);
        Sighting sightingAdded2 = sightingDao.recordSuperSighted(s2);
        Sighting sightingAdded3 = sightingDao.recordSuperSighted(s3);
        
        List<Super> listSuper;
        listSuper = sightingDao.getAllSightedByLocation(location);
        
        assertEquals(3, listSuper.size());
        
        assertEquals(location.getLocationID(), sightingAdded1.getLocation().getLocationID());
        assertEquals(location.getLocationID(), sightingAdded2.getLocation().getLocationID());
        assertEquals(location.getLocationID(), sightingAdded3.getLocation().getLocationID());
    }

    @Test
    public void testGetAllSightedByDate() {
        Superpower sp = new Superpower();
        sp.setSuperpowerId(1);
        sp.setSuperpowerName("Superpower Name Test");
        superpowerDao.addSuperpower(sp);
        
        Super character = new Super();
        character.setSuperID(1);
        character.setSuperDescription("super Description");
        character.setSuperName("Captain America");
        character.setEvil(Boolean.FALSE);
        character.setSuperpowerID(sp.getSuperpowerId());
        superDao.createSuper(character);
        
        Location location = new Location();
        location.setLocationID(1);
        location.setLocationName("Location Test");
        location.setLocationDesc("locationDesc");
        location.setLocationAddress("Location Address");
        location.setLatitude("latitude test");
        location.setLongitude("longitude test");
        locationDao.addLocation(location);
        
        LocalDateTime ld = LocalDateTime.now();
        
        Sighting s1 = new Sighting();
        s1.setSightingID(200);
        s1.setCharacter(character);
        s1.setDateSighted(ld);
        s1.setLocation(location);
        
        Sighting s2 = new Sighting();
        s2.setSightingID(201);
        s2.setCharacter(character);        
        s2.setDateSighted(ld);
        s2.setLocation(location);
        
        Sighting s3 = new Sighting();
        s3.setSightingID(202);
        s3.setCharacter(character);
        s3.setDateSighted(ld);
        s3.setLocation(location);
        
        sightingDao.recordSuperSighted(s1);
        sightingDao.recordSuperSighted(s2);
        sightingDao.recordSuperSighted(s3);
        
        List<Sighting> listSighting;
        listSighting = sightingDao.getAllSightedByDate(ld);
        
        assertNotNull(listSighting, "The list can't be empty");
        
        for(Sighting sig: listSighting){
            assertEquals(ld, sig.getDateSighted(), "The date doesn't match");
        }
   
    }
    
}
