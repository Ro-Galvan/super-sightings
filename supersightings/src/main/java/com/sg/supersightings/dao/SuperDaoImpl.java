package com.sg.supersightings.dao;

import com.sg.supersightings.dto.Super;
import com.sg.supersightings.mapper.SuperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class SuperDaoImpl implements SuperDao{

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public SuperDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Super createSuper(Super superCharacter) {
        final String INSERT_SUPER = "INSERT INTO super(superName, superDesc, isEvil, superpowerID) VALUES(?,?,?,?)";
        jdbcTemplate.update(INSERT_SUPER,
                superCharacter.getSuperName(),
                superCharacter.getSuperDescription(),
                superCharacter.getEvil(),
                superCharacter.getSuperpowerID());

        // Retrieve the last inserted ID
        String selectLastIdQuery = "SELECT LAST_INSERT_ID()";
        int id = jdbcTemplate.queryForObject(selectLastIdQuery, Integer.class);

        // Set the game ID
        superCharacter.setSuperID(id);
        return superCharacter;
    }

    @Override
    public List<Super> getAllSuperCharacters() {
        final String SQL = "SELECT * FROM super";
        return jdbcTemplate.query(SQL, new SuperMapper());
    }

    @Override
    public Super getSuperByID(int superId) {
        try {
            final String SQL = "SELECT * FROM super WHERE superID = ?";
            return jdbcTemplate.queryForObject(SQL, new SuperMapper(), superId);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public void updateSuper(Super superCharacter) {
        final String SQL = "UPDATE super SET superName = ?, superDesc = ?, isEvil = ?, superpowerID = ? WHERE superID = ?";
        jdbcTemplate.update(SQL,
                superCharacter.getSuperName(),
                superCharacter.getSuperDescription(),
                superCharacter.getEvil(),
                superCharacter.getSuperpowerID());
    }

//   Need to remove all superID associations from other tables before deleting super
    @Override
    @Transactional
    public void deleteSuper(int id) {
        final String DELETE_SUPER_ORG = "DELETE FROM superOrg WHERE superID = ?";
        jdbcTemplate.update(DELETE_SUPER_ORG, id);

        final String DELETE_SIGHTING = "DELETE FROM sighting WHERE superID = ?";
        jdbcTemplate.update(DELETE_SIGHTING, id);

        final String DELETE_SUPER = "DELETE FROM super WHERE superID=?";
        jdbcTemplate.update(DELETE_SUPER, id);
    }

}
