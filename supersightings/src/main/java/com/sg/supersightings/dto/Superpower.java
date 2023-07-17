package com.sg.supersightings.dto;

import java.util.Objects;

public class Superpower {
    private int superpowerId;
    private String superpowerName;

    public int getSuperpowerId() {
        return superpowerId;
    }

    public void setSuperpowerId(int superpowerId) {
        this.superpowerId = superpowerId;
    }

    public String getSuperpowerName() {
        return superpowerName;
    }

    public void setSuperpowerName(String superpowerName) {
        this.superpowerName = superpowerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Superpower that = (Superpower) o;

        if (superpowerId != that.superpowerId) return false;
        return superpowerName.equals(that.superpowerName);
    }

    @Override
    public int hashCode() {
        int result = superpowerId;
        result = 31 * result + superpowerName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Superpower{" +
                "superpowerId=" + superpowerId +
                ", superpowerName='" + superpowerName + '\'' +
                '}';
    }
}
