package com.cs1200002.apozidis.il311ci.repository;

import com.cs1200002.apozidis.il311ci.model.Authority;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends PagingAndSortingRepository<Authority, Long> {
}
