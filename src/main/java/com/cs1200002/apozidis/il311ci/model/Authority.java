package com.cs1200002.apozidis.il311ci.model;

import javax.persistence.*;

@Entity
@Table(name = "authority", schema = "public")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long authorityId;
    int ward;
    int policeDistrict;
    int communityArea;
    int ssa;
    int historicalWards;
    int zipCodes;
    int communityAreas;
    int censusTracts;
    int wards;

    public Authority() {
    }

    public Authority(int ward, int policeDistrict, int communityArea, int ssa, int historicalWards, int zipCodes, int communityAreas, int censusTracts, int wards, Report report) {
        this.ward = ward;
        this.policeDistrict = policeDistrict;
        this.communityArea = communityArea;
        this.ssa = ssa;
        this.historicalWards = historicalWards;
        this.zipCodes = zipCodes;
        this.communityAreas = communityAreas;
        this.censusTracts = censusTracts;
        this.wards = wards;
    }

    public long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(long authorityId) {
        this.authorityId = authorityId;
    }

    public int getWard() {
        return ward;
    }

    public void setWard(int ward) {
        this.ward = ward;
    }

    public int getPoliceDistrict() {
        return policeDistrict;
    }

    public void setPoliceDistrict(int policeDistrict) {
        this.policeDistrict = policeDistrict;
    }

    public int getCommunityArea() {
        return communityArea;
    }

    public void setCommunityArea(int communityArea) {
        this.communityArea = communityArea;
    }

    public int getSsa() {
        return ssa;
    }

    public void setSsa(int ssa) {
        this.ssa = ssa;
    }

    public int getHistoricalWards() {
        return historicalWards;
    }

    public void setHistoricalWards(int historicalWards) {
        this.historicalWards = historicalWards;
    }

    public int getZipCodes() {
        return zipCodes;
    }

    public void setZipCodes(int zipCodes) {
        this.zipCodes = zipCodes;
    }

    public int getCommunityAreas() {
        return communityAreas;
    }

    public void setCommunityAreas(int communityAreas) {
        this.communityAreas = communityAreas;
    }

    public int getCensusTracts() {
        return censusTracts;
    }

    public void setCensusTracts(int censusTracts) {
        this.censusTracts = censusTracts;
    }

    public int getWards() {
        return wards;
    }

    public void setWards(int wards) {
        this.wards = wards;
    }
}
