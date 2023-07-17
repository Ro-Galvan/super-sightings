package com.sg.supersightings.dao;

import com.sg.supersightings.dto.Location;
import com.sg.supersightings.dto.Sighting;
import com.sg.supersightings.dto.Super;
import java.time.LocalDateTime;
import java.util.List;

public interface SightingDao {
    //A user must be able to record a superhero/supervillain sighting for a particular location and date.
    Sighting recordSuperSighted(Sighting sighting);
    
    Sighting getSightedById(int lastID) ;
    
    List<Sighting> getAllSighting();
    
    void deleteSightingById(int id);
    
    //The system must be able to report all of the superheroes sighted at a particular location.
    List<Super> getAllSightedByLocation(Location location);
    
    //The system must be able to report all sightings (hero and location) for a particular date.
    List<Sighting> getAllSightedByDate(LocalDateTime date);
    
}
