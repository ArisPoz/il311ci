package com.cs1200002.apozidis.il311ci.controller;

import com.cs1200002.apozidis.il311ci.model.Authority;
import com.cs1200002.apozidis.il311ci.model.Report;
import com.cs1200002.apozidis.il311ci.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorityController {
    @Autowired
    private AuthorityService authorityService;

    @GetMapping("/authority/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Optional<Authority> getAuthoritiesById(@PathVariable Long id) {
        return authorityService.getAuthorityById(id);
    }

    @GetMapping("/authorities")
    public ResponseEntity<List<Authority>> getAuthorities(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize)
    {
        List<Authority> list = authorityService.getAuthorities(pageNo, pageSize);

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/authorities")
    public Authority addReport(Authority authority) {
        return authorityService.addAuthority(authority);
    }
}
