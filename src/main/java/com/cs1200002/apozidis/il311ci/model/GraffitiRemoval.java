package com.cs1200002.apozidis.il311ci.model;

import javax.persistence.*;

@Entity
@Table(name = "graffitiRemoval", schema = "public")
public class GraffitiRemoval {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String typeOfSurface;
    String graffitiLocation;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "report", referencedColumnName = "reportId", nullable = false)
    private Report report;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTypeOfSurface() {
        return typeOfSurface;
    }

    public void setTypeOfSurface(String typeOfSurface) {
        this.typeOfSurface = typeOfSurface;
    }

    public String getGraffitiLocation() {
        return graffitiLocation;
    }

    public void setGraffitiLocation(String graffitiLocation) {
        this.graffitiLocation = graffitiLocation;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}
