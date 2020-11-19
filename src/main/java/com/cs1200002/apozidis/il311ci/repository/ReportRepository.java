package com.cs1200002.apozidis.il311ci.repository;

import com.cs1200002.apozidis.il311ci.model.Report;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends PagingAndSortingRepository<Report, Long> {
}
