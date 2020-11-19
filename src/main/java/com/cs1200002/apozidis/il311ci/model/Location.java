package com.cs1200002.apozidis.il311ci.model;

import javax.persistence.*;

@Entity
@Table(name = "location", schema = "public")
public class Location {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    long locationId;
    String streetAddress;
    String zipCode;
    double xCoordinate;
    double yCoordinate;
    float latitude;
    float longitude;
    String locationLogLat;

    public Location() {
    }

    public Location(String streetAddress, String zipCode, long xCoordinate, long yCoordinate, float latitude, float longitude, String locationLogLat) {
        this.streetAddress = streetAddress;
        this.zipCode = zipCode;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationLogLat = locationLogLat;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public double getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getLocationLogLat() {
        return locationLogLat;
    }

    public void setLocationLogLat(String location) {
        this.locationLogLat = location;
    }
}
