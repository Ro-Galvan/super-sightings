package com.sg.supersightings.dao;

import com.sg.supersightings.dto.Location;
import com.sg.supersightings.dto.Sighting;
import com.sg.supersightings.dto.Super;
import com.sg.supersightings.mapper.SightingsMapper;
import com.sg.supersightings.mapper.SuperMapper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SightingDaoImpl implements SightingDao{
    
    @Autowired
    JdbcTemplate jdbc;
    
    //A user must be able to record a superhero/supervillain sighting for a particular location and date.
    @Override
    @Transactional
    public Sighting recordSuperSighted(Sighting sighting) {
        try{
            final String sqlInsertSighting = "INSERT INTO sighting(dateSighted, locationID, superID) VALUES(?, ?, ?);";
            
            jdbc.update(sqlInsertSighting, 
                    sighting.getDateSighted(),
                    sighting.getLocation().getLocationID(),
                    sighting.getCharacter().getSuperID());
            
            int lastID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
            
            Sighting sightingInserted = getSightedById(lastID);
            
            return sightingInserted;
        }
        catch (DataAccessException ex){
            return null;
        }
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
    public List<Sighting> getAllSightedByDate(LocalDateTime date) {
        
        //Map<Location, Super> listSightings = new TreeMap<>();
        
        final String selectListSighting = "SELECT * FROM sighting WHERE dateSighted = ?;";
        List<Sighting> listSighting = jdbc.query(selectListSighting, new SightingsMapper(), date);
        
        return listSighting;
    }

    @Override
    public Sighting getSightedById(int id) {
        return jdbc.queryForObject("SELECT * FROM sighting WHERE sightingID = ?", new SightingsMapper(), id);
    }

    @Override
    public List<Sighting> getAllSighting() {
        final String sql = "SELECT * FROM sighting";
        return jdbc.query(sql, new SightingsMapper());
    }

    @Override
    public void deleteSightingById(int id) {
        final String sql = "DELETE FROM sighting WHERE sightingID = ?";
        jdbc.update(sql, id);
    }
    
}
