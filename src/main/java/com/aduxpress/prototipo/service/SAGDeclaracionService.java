package com.aduxpress.prototipo.service;

import com.aduxpress.prototipo.model.SAGDeclaracion;
import com.aduxpress.prototipo.repository.SAGDeclaracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SAGDeclaracionService {
    @Autowired
    private SAGDeclaracionRepository repository;

    public SAGDeclaracion save(SAGDeclaracion declaracion) {
        return repository.save(declaracion);
    }
}
