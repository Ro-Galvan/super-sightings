package com.sg.supersightings.dao;

import com.sg.supersightings.dto.Superpower;

import java.util.List;

public interface SuperpowerDao {

    // This method retrieves a superpower based on its ID.
    Superpower getSuperpowerById(int superpowerId);


    // This method retrieves a list of all available superpowers.
    List<Superpower> getAllPowers();

    // This method adds a new superpower to the system and returns the added superpower.
    Superpower addSuperpower(Superpower superpower);

    // This method updates an existing superpower with new information.
    void  updateSuperpower(Superpower superpower);

    // This method deletes a superpower based on its ID.
    void deleteSuperpowerById(int deleteId);
}
