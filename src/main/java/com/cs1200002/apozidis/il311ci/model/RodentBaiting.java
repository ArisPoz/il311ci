package com.cs1200002.apozidis.il311ci.model;

import javax.persistence.*;

@Entity
@Table(name = "rodentBaiting", schema = "public")
public class RodentBaiting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    double numOfPremisesBaited;
    double numOfPremisesWithGarbage;
    double numOfPremisesWithRats;
    String currentActivity;
    String mostRecentAction;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "report", referencedColumnName = "reportId", nullable = false)
    private Report report;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getNumOfPremisesBaited() {
        return numOfPremisesBaited;
    }

    public void setNumOfPremisesBaited(double numOfPremisesBaited) {
        this.numOfPremisesBaited = numOfPremisesBaited;
    }

    public double getNumOfPremisesWithGarbage() {
        return numOfPremisesWithGarbage;
    }

    public void setNumOfPremisesWithGarbage(double numOfPremisesWithGarbage) {
        this.numOfPremisesWithGarbage = numOfPremisesWithGarbage;
    }

    public double getNumOfPremisesWithRats() {
        return numOfPremisesWithRats;
    }

    public void setNumOfPremisesWithRats(double numOfPremisesWithRats) {
        this.numOfPremisesWithRats = numOfPremisesWithRats;
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

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}
