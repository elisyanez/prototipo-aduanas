package com.aduxpress.prototipo.service;

import com.aduxpress.prototipo.model.Menor;
import com.aduxpress.prototipo.repository.MenorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenorService {
    @Autowired
    private MenorRepository menorRepository;

    public Menor save(Menor menor) {
        return menorRepository.save(menor);
    }
}
