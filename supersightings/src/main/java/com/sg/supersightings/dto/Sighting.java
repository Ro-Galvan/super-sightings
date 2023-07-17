package com.sg.supersightings.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class Sighting {
    private int sightingID;
    private LocalDateTime dateSighted;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.sightingID;
        hash = 59 * hash + Objects.hashCode(this.dateSighted);
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
        return Objects.equals(this.dateSighted, other.dateSighted);
    }

    @Override
    public String toString() {
        return "Sighting{" + "sightingID=" + sightingID + ", dateSighted=" + dateSighted + '}';
    }

   
    
    
}
