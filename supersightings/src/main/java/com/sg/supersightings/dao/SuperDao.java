package com.sg.supersightings.dao;

import com.sg.supersightings.dto.Super;

import java.util.List;

public interface SuperDao {
    Super createSuper(Super superCharacter);
    List<Super> getAllSuperCharacters();
    Super getSuperByID(int superId);
    void updateSuper(Super superCharacter);
    void deleteSuper(int id);
}
