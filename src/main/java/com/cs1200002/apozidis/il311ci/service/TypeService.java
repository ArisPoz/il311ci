package com.cs1200002.apozidis.il311ci.service;

import com.cs1200002.apozidis.il311ci.model.Type;
import com.cs1200002.apozidis.il311ci.repository.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeService {

    private final TypeRepository typeRepository;

    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public Type addType(Type type) {
        typeRepository.save(type);
        return type;
    }

    public Iterable<Type> getTypes(){
        return typeRepository.findAll();
    }

    public Optional<Type> getTypeById(Long id) {
        return typeRepository.findById(id);
    }
}
