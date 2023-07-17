package com.sg.supersightings.dao;

import com.sg.supersightings.dto.Location;
import com.sg.supersightings.dto.Sighting;
import com.sg.supersightings.dto.Super;
import com.sg.supersightings.mapper.SightingsMapper;
import com.sg.supersightings.mapper.SuperMapper;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

public class SightingDaoImpl implements SightingDao{
    @Autowired
    private JdbcTemplate jdbc;
    
    //A user must be able to record a superhero/supervillain sighting for a particular location and date.
    @Override
    public Sighting recordSuperSighted(Sighting sighting) {
        final String sqlInsertSighting = "INSERT INTO sighting(dateSighted, locationID, superID) VALUES(?, ?, ?);";

        jdbc.update(sqlInsertSighting, 
                new SightingsMapper(), 
                sighting.getDateSighted(),
                sighting.getLocation().getLocationID(),
                sighting.getCharacter().getSuperID());
        
        int lastID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setSightingID(lastID);
        
        return sighting;
    }
    //The system must be able to report all of the superheroes sighted at a particular location.
    @Override
    public List<Super> getAllSightedByLocation(Location location) {
        try{
            final String sql = "SELECT * \n" +
                                "FROM super \n" +
                                "INNER JOIN sighting on super.superID = sighting.superID\n" +
                                "WHERE locationID = ?;";
            return jdbc.query(sql, new SuperMapper(), location.getLocationID());
        }
        catch (DataAccessException ex){
            return null;
        }
    }
    //The system must be able to report all sightings (hero and location) for a particular date.
    @Override
    @Transactional
    public Map<Location, Super> getAllSightedByDate(LocalDateTime date) {
        
        Map<Location, Super> listSightings = new TreeMap<>();
        
        final String selectListSighting = "SELECT * FROM sighting WHERE dateSighted = ?;";
        List<Sighting> listSighting = jdbc.query(selectListSighting, new SightingsMapper(), date);
        
        /*for(Sighting s: listSighting){
            
        }
        final String sql = "SELECT superName, locationName, locationAddress, latitude, longitude\n" +
                            "FROM super\n" +
                            "INNER JOIN sighting on super.superID = sighting.superID \n" +
                            "INNER JOIN location on sighting.locationID  = location.locationID \n" +
                            "WHERE dateSighted = ?";
        */
        return listSightings;
    }
    
}
