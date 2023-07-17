package com.sg.supersightings.dao;

import com.sg.supersightings.dto.Location;
import com.sg.supersightings.mapper.LocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class LocationDaoImpl implements LocationDao {
    @Autowired
    JdbcTemplate jdbc;

    //retrieves a location object by its ID from the database
    @Override
    public Location getLocationById(int id) {
        try {
            //executes SQL query and maps result to a Location object using the LocationMapper class
            final String sql = "SELECT * FROM location WHERE locationID = ?";
            return jdbc.queryForObject(sql, new LocationMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    //retrieves list of all Location objects from the database
    @Override
    public List<Location> getAllLocations() {
        //executes SQL query and map the results to a list of Location objects using the LocationMapper class
        final String sql = "SELECT * FROM location";
        return jdbc.query(sql, new LocationMapper());
    }

    //adds new Location object to the database
    @Override
    @Transactional
    public Location addLocation(Location location) {
        //executes the SQL statement to insert new location, using provided values from the Location object
        final String sql = "INSERT INTO location(locationName, locationDesc, locationAddress, latitude, longitude) " + "VALUES(?,?,?,?)";
        jdbc.update(sql, location.getLocationName(), location.getLocationDesc(), location.getLocationAddress(), location.getLatitude(), location.getLongitude());

        //retrieves the auto-generated ID of the newly inserted location
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        //sets the ID of the object to the newly generated ID
        location.setLocationID(newId);
        //return the modified Location object
        return location;
    }

    //updates an existing Location object in the database
    @Override
    @Transactional
    public void updateLocation(Location location) {
        //executes the SQL statement to update the location with provided values from Location object
        final String sql = "UPDATE location SET locationName = ?, locationDesc = ?, locationAddress = ?, latitude = ?, longitude = ? WHERE locationID = ?";
        jdbc.update(sql, location.getLocationName(), location.getLocationDesc(), location.getLocationAddress(), location.getLatitude(), location.getLongitude());
    }

    //deletes a Location object from the database by its ID
    @Override
    @Transactional
    public void deleteLocationById(int id) {
        //executes SQL statement to delete any sightings associated with the location
        final String deleteSighting = "DELETE FROM sighting WHERE locationID = ?";
        jdbc.update(deleteSighting, id);

        //executes the SQL statement to delete the location itself
        final String deleteLocation = "DELETE FROM location where id = ?";
        jdbc.update(deleteLocation, id);
    }
}