package com.sg.supersightings.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class Sighting {
    private int sightingID;
    private LocalDateTime dateSighted;
    private Location location;
    private Super character;

    public int getSightingID() {
        return sightingID;
    }

    public void setSightingID(int sightingID) {
        this.sightingID = sightingID;
    }

    public LocalDateTime getDateSighted() {
        return dateSighted;
    }

    public void setDateSighted(LocalDateTime dateSighted) {
        this.dateSighted = dateSighted;
    }

    public Location getLocation() {
        return location;
    }

    public Super getCharacter() {
        return character;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setCharacter(Super character) {
        this.character = character;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.sightingID;
        hash = 31 * hash + Objects.hashCode(this.dateSighted);
        hash = 31 * hash + Objects.hashCode(this.location);
        hash = 31 * hash + Objects.hashCode(this.character);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sighting other = (Sighting) obj;
        if (this.sightingID != other.sightingID) {
            return false;
        }
        if (!Objects.equals(this.dateSighted, other.dateSighted)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        return Objects.equals(this.character, other.character);
    }

    @Override
    public String toString() {
        return "Sighting{" + "sightingID=" + sightingID + ", dateSighted=" + dateSighted + ", location=" + location + ", character=" + character + '}';
    }
    
    
    
    
}
