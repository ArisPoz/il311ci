package com.cs1200002.apozidis.il311ci.model;

import javax.persistence.*;

@Entity
@Table(name = "sanitationCode", schema = "public")
public class SanitationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String natureOfCodeViolation;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "report", referencedColumnName = "reportId", nullable = false)
    private Report report;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNatureOfCodeViolation() {
        return natureOfCodeViolation;
    }

    public void setNatureOfCodeViolation(String natureOfCodeViolation) {
        this.natureOfCodeViolation = natureOfCodeViolation;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}
