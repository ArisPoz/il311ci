package com.cs1200002.apozidis.il311ci.model;

import javax.persistence.*;

@Entity
@Table(name = "potHoles", schema = "public")
public class PotHoles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String currentActivity;
    String mostRecentAction;
    double numOfPotHolesFilledOnBlock;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "report", referencedColumnName = "reportId", nullable = false)
    private Report report;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public double getNumOfPotHolesFilledOnBlock() {
        return numOfPotHolesFilledOnBlock;
    }

    public void setNumOfPotHolesFilledOnBlock(double numOfPotHolesFilledOnBlock) {
        this.numOfPotHolesFilledOnBlock = numOfPotHolesFilledOnBlock;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}
