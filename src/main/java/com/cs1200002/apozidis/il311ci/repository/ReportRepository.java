package com.cs1200002.apozidis.il311ci.repository;

import com.cs1200002.apozidis.il311ci.model.Report;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ReportRepository extends PagingAndSortingRepository<Report, Long> {
    @Query(value = "SELECT COUNT(*) FROM public.Report", nativeQuery = true)
    int countTotalReportRecords();

    @Query(value = "SELECT COUNT(*) as total_requests, service_type " +
            "FROM public.report as r, public.type as t " +
            "WHERE r.type = t.type_id AND TO_DATE(creation_date,'YYYY-MM-DD') BETWEEN :startDate AND :endDate " +
            "GROUP BY service_type " +
            "ORDER BY total_requests DESC;", nativeQuery = true)
    List<Object[]> query1(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "SELECT count(*) as total_requests, type, service_type, " +
            "TO_DATE(creation_date,'YYYY-MM-DD') as cDate " +
            "FROM public.report as r, public.type as t " +
            "WHERE r.type = t.type_id AND t.type_id = :typeId AND " +
            "TO_DATE(creation_date,'YYYY-MM-DD') BETWEEN :startDate AND :endDate " +
            "GROUP BY service_type, type, cDate;", nativeQuery = true)
    List<Object[]> query2(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("typeId") int typeId);

    @Query(value = "WITH most_Common AS " +
            "(SELECT zip_code, count(*), service_type, " +
            "ROW_NUMBER() OVER (PARTITION BY zip_code ORDER BY Count(*) DESC) as tops " +
            "FROM public.report AS r, public.type AS t, public.location AS l " +
            "WHERE r.type = t.type_Id AND r.location = l.location_Id AND " +
            "TO_DATE(creation_date,'YYYY-MM-DD') = :date " +
            "GROUP BY zip_code, service_type)" +
            "  " +
            "SELECT zip_code, service_type " +
            "FROM most_Common " +
            "WHERE tops = 1 " +
            "GROUP BY zip_code, service_type " +
            "ORDER BY zip_code DESC;", nativeQuery = true)
    List<Object[]> query3(@Param("date") Date date);

    @Query(value = "SELECT service_type, " +
            "ROUND(AVG(TO_DATE(completion_date,'YYYY-MM-DD') - " +
            "TO_DATE(creation_date,'YYYY-MM-DD')), 1) as avg_comp_time " +
            "FROM public.report as r, public.type as t " +
            "WHERE r.type = t.type_id AND TO_DATE(creation_date,'YYYY-MM-DD') " +
            "BETWEEN :startDate AND :endDate " +
            "GROUP BY service_type " +
            "HAVING ROUND(AVG(TO_DATE(completion_date,'YYYY-MM-DD') - " +
            "TO_DATE(creation_date,'YYYY-MM-DD')), 1) > 0", nativeQuery = true)
    List<Object[]> query4(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "SELECT service_type, Count(*) as total " +
            "FROM public.report AS r, public.type AS t, public.location AS l " +
            "WHERE r.type = t.type_id AND r.location = l.location_id AND " +
            ":upperLat <= latitude AND latitude <= :lowerLat AND " +
            ":upperLong <= longitude AND longitude <= :lowerLong AND " +
            "TO_DATE(creation_date,'YYYY-MM-DD') = :date " +
            "GROUP BY service_type " +
            "ORDER BY COUNT(*) DESC " +
            "LIMIT 1", nativeQuery = true)
    List<Object[]> query5(@Param("date") Date date, @Param("upperLat") Double upperLat,
                          @Param("upperLong") Double upperLong, @Param("lowerLat") Double lowerLat,
                          @Param("lowerLong") Double lowerLong);

    @Query(value = "SELECT SSA, COUNT(*) " +
            "FROM public.report AS r, public.type AS t, public.authority AS a " +
            "WHERE r.authority = a.authority_id AND r.type = t.type_id AND SSA != 0 AND " +
            "TO_DATE(creation_date,'YYYY-MM-DD') BETWEEN :startDate AND :endDate " +
            "GROUP BY service_type, SSA " +
            "ORDER BY COUNT(*) DESC " +
            "LIMIT 5", nativeQuery = true)
    List<Object[]> query6(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "SELECT COUNT(*), REGEXP_MATCHES(license_plate, '^[A-Z]{1,3}[A-Z]{1,2}[0-9]{1,4}$', 'i') " +
            "FROM public.abandoned_vehicles " +
            "GROUP BY license_plate " +
            "HAVING COUNT(*) > :numOfTimesInvolved " +
            "ORDER BY COUNT(*) DESC", nativeQuery = true)
    List<Object[]> query7(@Param("numOfTimesInvolved") int numOfTimesInvolved);

    @Query(value = "SELECT vehicle_color " +
            "FROM ( SELECT COUNT(*), vehicle_color, " +
            "DENSE_RANK() OVER (ORDER BY COUNT(*) DESC) AS rank " +
            "FROM public.abandoned_vehicles " +
            "GROUP BY vehicle_color) AS ordered_colors_rank " +
            "WHERE rank = :rank", nativeQuery = true)
    List<Object[]> query8(@Param("rank") int rank);

    @Query(value = "SELECT report_id, service_request_number, num_of_premises_baited " +
            "FROM public.report AS r, public.type AS t, public.rodent_baiting AS rb " +
            "WHERE r.type = t.type_id AND t.service_type = 'Rodent Baiting/Rat Complaint' AND " +
            "rb.report = r.report_id AND num_of_premises_baited <= :numOfPremises", nativeQuery = true)
    List<Object[]> query9(@Param("numOfPremises") int numOfPremises);

    @Query(value = "SELECT report_id, service_request_number, num_of_premises_with_garbage " +
            "FROM public.report AS r, public.type AS t, public.rodent_baiting AS rb " +
            "WHERE r.type = t.type_id AND t.service_type = 'Rodent Baiting/Rat Complaint' AND " +
            "rb.report = r.report_id AND num_of_premises_with_garbage <= :numOfPremises", nativeQuery = true)
    List<Object[]> query10(@Param("numOfPremises") int numOfPremises);

    @Query(value = "SELECT report_id, service_request_number, num_of_premises_with_rats " +
            "FROM public.report AS r, public.type AS t, public.rodent_baiting AS rb " +
            "WHERE r.type = t.type_id AND t.service_type = 'Rodent Baiting/Rat Complaint' AND " +
            "rb.report = r.report_id AND num_of_premises_with_rats <= :numOfPremises", nativeQuery = true)
    List<Object[]> query11(@Param("numOfPremises") int numOfPremises);

    @Query(value = "SELECT police_district " +
            "FROM report AS r, public.type AS t, public.authority AS a, public.rodent_baiting AS rb " +
            "WHERE r.authority = a.authority_id AND r.type = t.type_id AND " +
            "rb.report = r.report_id AND service_type = 'Rodent Baiting/Rat Complaint' AND " +
            "police_district > 0 AND num_of_premises_baited > :numOfPremises AND TO_DATE(creation_date,'YYYY-MM-DD') = :date " +
            "INTERSECT " +
            "SELECT police_district " +
            "FROM report AS r, public.type AS t, public.authority AS a, public.pot_holes AS pt " +
            "WHERE r.authority = a.authority_id AND r.type = t.type_id AND " +
            "pt.report = r.report_id AND service_type = 'Pothole in Street' AND " +
            "police_district > 0 AND num_of_pot_holes_filled_on_block > :numOfPotHoles AND TO_DATE(creation_date,'YYYY-MM-DD') = :date ", nativeQuery = true)
    List<Object[]> query12(@Param("date") Date date, @Param("numOfPotHoles") int numOfPotHoles, @Param("numOfPremises") int numOfPremises);
}