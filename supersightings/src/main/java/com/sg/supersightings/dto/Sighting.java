package com.sg.supersightings.dto;

import java.time.LocalDateTime;

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

   
    
    
}
