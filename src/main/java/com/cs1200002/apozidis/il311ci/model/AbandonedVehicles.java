package com.cs1200002.apozidis.il311ci.model;

import javax.persistence.*;

@Entity
@Table(name = "abandonedVehicles", schema = "public")
public class AbandonedVehicles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @Lob
    String licensePlate;
    String vehicleMadeModel;
    String vehicleColor;
    String currentActivity;
    String mostRecentAction;
    double daysParked;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "report", referencedColumnName = "reportId", nullable = false)
    private Report report;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getVehicleMadeModel() {
        return vehicleMadeModel;
    }

    public void setVehicleMadeModel(String vehicleMadeModel) {
        this.vehicleMadeModel = vehicleMadeModel;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public String getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(String currentActivity) {
        this.currentActivity = currentActivity;
    }

    public String getMostRecentAction() {
        return mostRecentAction;
    }

    public void setMostRecentAction(String mostRecentAction) {
        this.mostRecentAction = mostRecentAction;
    }

    public double getDaysParked() {
        return daysParked;
    }

    public void setDaysParked(double daysParked) {
        this.daysParked = daysParked;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}
