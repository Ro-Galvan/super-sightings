package com.sg.supersightings.mapper;


import com.sg.supersightings.dto.Location;
import com.sg.supersightings.dto.Sighting;
import com.sg.supersightings.dto.Super;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import org.springframework.jdbc.core.RowMapper;


public class SightingsMapper implements RowMapper<Sighting>{

    @Override
    public Sighting mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        Super character =  new Super();
        character.setSuperID(rs.getInt("superID"));
        
        Location location = new Location();
        location.setLocationID(rs.getInt("locationID"));
        
        Sighting sighting = new Sighting();
        
        sighting.setSightingID(rs.getInt("sightingID"));
        sighting.setDateSighted(rs.getObject("dateSighted", LocalDateTime.class));     
        sighting.setCharacter(character);
        sighting.setLocation(location);

        return sighting;
    }
    
}
