package com.cs1200002.apozidis.il311ci.service;

import com.cs1200002.apozidis.il311ci.model.Authority;
import com.cs1200002.apozidis.il311ci.repository.AuthorityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public Authority addAuthority(Authority authority) {
        authorityRepository.save(authority);
        return authority;
    }

    public Optional<Authority> getAuthorityById(Long id) {
        return authorityRepository.findById(id);
    }


    public List<Authority> getAuthorities(Integer pageNo, Integer pageSize)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Authority> pagedResult = authorityRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }
}
