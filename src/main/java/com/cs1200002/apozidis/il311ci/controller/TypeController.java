package com.cs1200002.apozidis.il311ci.controller;

import com.cs1200002.apozidis.il311ci.model.Type;
import com.cs1200002.apozidis.il311ci.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    @ResponseStatus(value = HttpStatus.OK)
    public Iterable<Type> getTypes() {
        return typeService.getTypes();
    }

    @GetMapping("/type/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Optional<Type> getTypes(@PathVariable Long id) {
        return typeService.getTypeById(id);
    }

    @PostMapping("/types")
    public Type addType(Type type) {
        return typeService.addType(type);
    }
}
