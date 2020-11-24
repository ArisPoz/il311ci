package com.cs1200002.apozidis.il311ci.repository;

import com.cs1200002.apozidis.il311ci.model.Report;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends PagingAndSortingRepository<Report, Long> {
    @Query(value = "SELECT COUNT(*) FROM public.Report", nativeQuery = true)
    int countTotalReportRecords();
}
