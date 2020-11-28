package com.cs1200002.apozidis.il311ci.model;

import javax.persistence.*;

@Entity
@Table(name = "report", schema = "public")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;
    private String creationDate;
    private String completionDate;
    private String status;
    private String serviceRequestNumber;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "type", referencedColumnName = "typeId", nullable = false)
    private Type type;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "location", referencedColumnName = "locationId", nullable = false)
    private Location location;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "authority", referencedColumnName = "authorityId", nullable = false)
    private Authority authority;

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getServiceRequestNumber() {
        return serviceRequestNumber;
    }

    public void setServiceRequestNumber(String serviceRequestNumber) {
        this.serviceRequestNumber = serviceRequestNumber;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }
}
