package com.cs1200002.apozidis.il311ci.service;

import com.cs1200002.apozidis.il311ci.model.Report;
import com.cs1200002.apozidis.il311ci.repository.ReportRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Report> getReports(Integer pageNo, Integer pageSize)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Report> pagedResult = reportRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    public void deleteById(long id){
        reportRepository.deleteById(id);
    }

    public int getTotalReports() {
        return reportRepository.countTotalReportRecords();
    }
}
