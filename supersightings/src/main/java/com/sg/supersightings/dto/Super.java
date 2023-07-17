package com.sg.supersightings.dto;

import java.util.Objects;

public class Super {
    private int superID;
    private String superName;
    private String superDescription;
    private Boolean isEvil;
    private int superpowerID;

    public int getSuperID() {
        return superID;
    }

    public void setSuperID(int superID) {
        this.superID = superID;
    }

    public String getSuperName() {
        return superName;
    }

    public void setSuperName(String superName) {
        this.superName = superName;
    }

    public String getSuperDescription() {
        return superDescription;
    }

    public void setSuperDescription(String superDescription) {
        this.superDescription = superDescription;
    }

    public Boolean getEvil() {
        return isEvil;
    }

    public void setEvil(Boolean evil) {
        isEvil = evil;
    }

    public int getSuperpowerID() {
        return superpowerID;
    }

    public void setSuperpowerID(int superpowerID) {
        this.superpowerID = superpowerID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Super aSuper = (Super) o;

        if (superID != aSuper.superID) return false;
        if (superpowerID != aSuper.superpowerID) return false;
        if (!Objects.equals(superName, aSuper.superName)) return false;
        if (!Objects.equals(superDescription, aSuper.superDescription))
            return false;
        return Objects.equals(isEvil, aSuper.isEvil);
    }

    @Override
    public int hashCode() {
        int result = superID;
        result = 31 * result + (superName != null ? superName.hashCode() : 0);
        result = 31 * result + (superDescription != null ? superDescription.hashCode() : 0);
        result = 31 * result + (isEvil != null ? isEvil.hashCode() : 0);
        result = 31 * result + superpowerID;
        return result;
    }
}
