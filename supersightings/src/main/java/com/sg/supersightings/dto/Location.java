package com.sg.supersightings.dto;

public class Location {
    private int locationID;
    private String locationName;
    private String locationDesc;
    private String locationAddress;
    private String latitude;
    private String longitude;

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationDesc() {
        return locationDesc;
    }

    public void setLocationDesc(String locationDesc) {
        this.locationDesc = locationDesc;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (locationID != location.locationID) return false;
        if (!locationName.equals(location.locationName)) return false;
        if (!locationDesc.equals(location.locationDesc)) return false;
        if (!locationAddress.equals(location.locationAddress)) return false;
        if (!latitude.equals(location.latitude)) return false;
        return longitude.equals(location.longitude);
    }

    @Override
    public int hashCode() {
        int result = locationID;
        result = 31 * result + locationName.hashCode();
        result = 31 * result + locationDesc.hashCode();
        result = 31 * result + locationAddress.hashCode();
        result = 31 * result + latitude.hashCode();
        result = 31 * result + longitude.hashCode();
        return result;
    }
}
