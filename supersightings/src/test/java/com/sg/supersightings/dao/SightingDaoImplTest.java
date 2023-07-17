
package com.sg.supersightings.dao;

import com.sg.supersightings.dto.Location;
import com.sg.supersightings.dto.Sighting;
import com.sg.supersightings.dto.Super;
import java.time.LocalDateTime;
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
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of recordSuperSighted method, of class SightingDaoImpl.
     */
    @Test
    public void testRecordSuperSighted() {
        Super character = new Super();
        character.setSuperDescription("super Description");
        character.setSuperName("Capitain America");
        character.setSuperpowerID(1);
        
        Location location = new Location();
        location.setLocationName("Location Name");
        location.setLatitude("425");
        location.setLongitude("424");
        location.setLocationAddress("Address Test");
        
        Sighting s = new Sighting();
        s.setCharacter(character);
        s.setDateSighted(LocalDateTime.now());
        s.setLocation(location);
        
        Sighting SightingAdded = sightingDao.recordSuperSighted(s);
        
        assertNotNull(SightingAdded, "The Sighting object is null");
        assertEquals(s, SightingAdded, "Object added is not equal");
        
    }

    /**
     * Test of getAllSightedByLocation method, of class SightingDaoImpl.
     */
    @Test
    public void testGetAllSightedByLocation() {
    }

    /**
     * Test of getAllSightedByDate method, of class SightingDaoImpl.
     */
    @Test
    public void testGetAllSightedByDate() {
    }
    
}
