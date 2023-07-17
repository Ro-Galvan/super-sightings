package com.sg.supersightings.dao;

import com.sg.supersightings.dto.Super;

import java.util.List;

public interface SuperDao {
    Super createSuper(Super superCharacter);
    List<Super> getAllSuperCharacters();
    Super getSuperByID(int superId);
    void updateSuper(Super superCharacter);
//    FK association with superpower Table
    void deleteAllSuperpowersFromSuper(int superpowerId);
    void deleteSuper(int superId);
}
