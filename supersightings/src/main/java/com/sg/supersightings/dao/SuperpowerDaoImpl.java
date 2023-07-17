package com.sg.supersightings.dao;

import com.sg.supersightings.dto.Superpower;
import com.sg.supersightings.mapper.SuperpowerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository //-Indicates that it's a repository class responsible for data access.

public class SuperpowerDaoImpl implements SuperpowerDao {
    @Autowired
    JdbcTemplate jdbc; //JdbcTemplate is autowired to perform database operations.


    //-------------- getSuperpowerById --------------//
    @Override
    public Superpower getSuperpowerById(int superpowerId) {
        try{
            // Executes a SQL query to retrieve a superpower by its ID from the "superpowers" table.
            final String GET_SUPERPOWER_BY_ID = "SELECT * FROM superpowers WHERE superpowerID = ?";
            // Uses a custom "SuperpowerMapper" class to map the query result to a Superpower object.
            return jdbc.queryForObject(GET_SUPERPOWER_BY_ID , new SuperpowerMapper(), superpowerId);

        } catch (DataAccessException ex){
            return null;
            // In case of any exception or data access error, returns null.
        }
    }


    //-------------- getAllPowers() --------------//
    @Override
    public List<Superpower> getAllPowers() {
        // Executes a SQL query to retrieve all superpowers from the "superpowers" table.
        final String GET_ALL_SUPERPOWER_BY_ID = "SELECT * FROM superpowers";
        // Uses a custom "SuperpowerMapper" class to map the query result to a list of Superpower objects.
        return jdbc.query(GET_ALL_SUPERPOWER_BY_ID, new SuperpowerMapper());

    }


    //-------------- addSuperpower() --------------//
    @Override
    @Transactional
    public Superpower addSuperpower(Superpower superpower) {
        // Executes an SQL INSERT statement to add a new superpower to the "superpowers" table.
        final String INSERT_SUPERPOWER = "INSERT INTO superpowers(superpowerName)"  + "VALUES(?)";
        jdbc.update(INSERT_SUPERPOWER, superpower.getSuperpowerName());
        // Uses the value of superpowerName from the provided Superpower object as a parameter for the INSERT statement.

        //-Retrieves the ID of the newly inserted superpower.
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        //-Sets the newly generated ID in the Superpower object.
        superpower.setSuperpowerId(newId);

        //-Returns the Superpower object with the updated ID.
        return superpower;

    }

    //------------------ updateSuperpower() -----------------//
    @Override
    public void updateSuperpower(Superpower superpower) {
        final String UPDATE_SUPERPOWER = "UPDATE superpowers SET superpowerName = ? WHERE superpowerID = ?";
        jdbc.update(UPDATE_SUPERPOWER, superpower.getSuperpowerName(), superpower.getSuperpowerId());
        // Executes an SQL UPDATE statement to update the superpower's name in the "superpowers" table.
        // Uses the values of superpowerName and superpowerId from the
        // provided Superpower object as parameters for the UPDATE statement.
    }

    //------------------ deleteSuperpowerById() -----------------//
    @Override
    @Transactional
    public void deleteSuperpowerById(int deleteId) {
        final String DELETE_SUPERPOWER = "DELETE FROM superpowers WHERE superpowerID = ?;";
        jdbc.update(DELETE_SUPERPOWER, deleteId);
        // Executes an SQL DELETE statement to delete a superpower from the "superpowers" table based on its ID.
        // Uses the deleteId parameter as a parameter for the DELETE statement.
    }

}
