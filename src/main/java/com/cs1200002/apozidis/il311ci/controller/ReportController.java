package com.cs1200002.apozidis.il311ci.controller;

import com.cs1200002.apozidis.il311ci.model.Report;
import com.cs1200002.apozidis.il311ci.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/report/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Optional<Report> getReportById(@PathVariable Long id) {
        return reportService.getReportById(id);
    }

    @GetMapping("/reports")
    public ResponseEntity<List<Report>> getReports(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize)
    {
        List<Report> list = reportService.getReports(pageNo, pageSize);

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/reports")
    public Report addReport(Report report) {
        return reportService.addReport(report);
    }
}
