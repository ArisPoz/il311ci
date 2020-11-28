package com.cs1200002.apozidis.il311ci.controller;

import com.cs1200002.apozidis.il311ci.model.Report;
import com.cs1200002.apozidis.il311ci.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/report/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Optional<Report> getReportById(@PathVariable Long id) {
        return reportService.getReportById(id);
    }

    @GetMapping("/reports")
    public Page<Report> getReports(Pageable pageable)
    {
        return reportService.getReports(pageable);
    }

    @PostMapping(value = "/addReport")
    public Report addReport(Report report) {
        return reportService.addReport(report);
    }

    @DeleteMapping("/deleteReport/{id}")
    public void deleteById(@PathVariable Long id){
        reportService.deleteById(id);
    }

    @GetMapping(value = "/countReports")
    public int countTotalReports() {
        return reportService.getTotalReports();
    }

    @GetMapping(value = "/reports/query1")
    public List<Object[]> query1(@RequestParam Date startDate, @RequestParam Date endDate) {
        return reportService.query1(startDate, endDate);
    }

    @GetMapping(value = "/reports/query2")
    public List<Object[]> query2(@RequestParam Date startDate, @RequestParam Date endDate, @RequestParam int typeId) {
        return reportService.query2(startDate, endDate, typeId);
    }

    @GetMapping(value = "/reports/query3")
    public List<Object[]> query3(@RequestParam Date date) {
        return reportService.query3(date);
    }

    @GetMapping(value = "/reports/query4")
    public List<Object[]> query4(@RequestParam Date startDate, @RequestParam Date endDate) {
        return reportService.query4(startDate, endDate);
    }

    @GetMapping(value = "/reports/query5")
    public List<Object[]> query5(@RequestParam Date date, @RequestParam Double upperLat, @RequestParam Double upperLong,
                                 @RequestParam Double lowerLat, @RequestParam Double lowerLong) {
        return reportService.query5(date, upperLat, upperLong, lowerLat, lowerLong);
    }

    @GetMapping(value = "/reports/query6")
    public List<Object[]> query6(@RequestParam Date startDate, @RequestParam Date endDate) {
        return reportService.query6(startDate, endDate);
    }

    @GetMapping(value = "/reports/query7")
    public List<Object []> query7(@RequestParam int numOfTimesInvolved) {
        return reportService.query7(numOfTimesInvolved);
    }

    @GetMapping(value = "/reports/query8")
    public List<Object []> query8(@RequestParam int rank) {
        return reportService.query8(rank);
    }

    @GetMapping(value = "/reports/query9")
    public List<Object []> query9(@RequestParam int numOfPremises) {
        return reportService.query9(numOfPremises);
    }

    @GetMapping(value = "/reports/query10")
    public List<Object []> query10(@RequestParam int numOfPremises){
        return reportService.query10(numOfPremises);
    }

    @GetMapping(value = "/reports/query11")
    public List<Object []> query11(@RequestParam int numOfPremises){
        return reportService.query11(numOfPremises);
    }

    @GetMapping(value = "/reports/query12")
    public List<Object []> query12(@RequestParam Date date, @RequestParam int numOfPotHoles, @RequestParam int numOfPremises){
        return reportService.query12(date, numOfPotHoles, numOfPremises);
    }
}
