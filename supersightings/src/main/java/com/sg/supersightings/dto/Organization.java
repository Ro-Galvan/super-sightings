package com.sg.supersightings.dto;

import java.util.Objects;

public class Organization {
    private int orgId;
    private String orgName;
    private String orgDes;
    private String orgAddress;
    private String orgNumber;
    private boolean isEvil;

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgDes() {
        return orgDes;
    }

    public void setOrgDes(String orgDes) {
        this.orgDes = orgDes;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getOrgNumber() {
        return orgNumber;
    }

    public void setOrgNumber(String orgNumber) {
        this.orgNumber = orgNumber;
    }

    public boolean isEvil() {
        return isEvil;
    }

    public void setEvil(boolean evil) {
        isEvil = evil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return getOrgId() == that.getOrgId() && isEvil() == that.isEvil() && Objects.equals(getOrgName(), that.getOrgName()) && Objects.equals(getOrgDes(), that.getOrgDes()) && Objects.equals(getOrgAddress(), that.getOrgAddress()) && Objects.equals(getOrgNumber(), that.getOrgNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrgId(), getOrgName(), getOrgDes(), getOrgAddress(), getOrgNumber(), isEvil());
    }
}