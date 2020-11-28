package com.cs1200002.apozidis.il311ci.service;

import com.cs1200002.apozidis.il311ci.model.Report;
import com.cs1200002.apozidis.il311ci.repository.ReportRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public Report addReport(Report report) {
        reportRepository.save(report);
        return report;
    }

    public Optional<Report> getReportById(Long id) {
        return reportRepository.findById(id);
    }

    public Page<Report> getReports(Pageable pageable)
    {
        return reportRepository.findAll(pageable);
    }

    public void deleteById(long id){
        reportRepository.deleteById(id);
    }

    public int getTotalReports() {
        return reportRepository.countTotalReportRecords();
    }

    public List<Object[]> query1(Date startDate, Date endDate) {
        return reportRepository.query1(startDate, endDate);
    }

    public List<Object[]> query2(Date startDate, Date endDate, int typeId) {
        return reportRepository.query2(startDate, endDate, typeId);
    }

    public List<Object[]> query3(Date date) {
        return reportRepository.query3(date);
    }

    public List<Object[]> query4(Date startDate, Date endDate) {
        return reportRepository.query4(startDate, endDate);
    }

    public List<Object[]> query5(Date date, Double upperLat, Double upperLong, Double lowerLat, Double lowerLong) {
        return reportRepository.query5(date, upperLat, upperLong, lowerLat, lowerLong);
    }

    public List<Object[]> query6(Date startDate, Date endDate) {
        return reportRepository.query6(startDate, endDate);
    }

    public List<Object []> query7(int numOfTimesInvolved) {
        return reportRepository.query7(numOfTimesInvolved);
    }

    public List<Object []> query8(int rank) {
        return reportRepository.query8(rank);
    }

    public List<Object []> query9(int numOfPremises) {
        return reportRepository.query9(numOfPremises);
    }

    public List<Object []> query10(int numOfPremises){
        return reportRepository.query10(numOfPremises);
    }

    public List<Object []> query11(int numOfPremises){
        return reportRepository.query11(numOfPremises);
    }

    public List<Object []> query12(Date date, int numOfPotHoles, int numOfPremises){
        return reportRepository.query12(date, numOfPotHoles, numOfPremises);
    }
}
