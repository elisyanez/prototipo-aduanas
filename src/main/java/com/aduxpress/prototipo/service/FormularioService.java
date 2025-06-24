package com.aduxpress.prototipo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aduxpress.prototipo.model.Formulario;
import com.aduxpress.prototipo.repository.FormularioRepository;

@Service
public class FormularioService {
    @Autowired
    private FormularioRepository formularioRepository;

    public Formulario saveFormulario(Formulario formulario) {
        return formularioRepository.save(formulario);
    }

    public Formulario getFormularioById(Long id) {
        return formularioRepository.findById(id).orElse(null);
    }

    public void deleteFormulario(Long id) {
        formularioRepository.deleteById(id);
    }

    public Formulario updateFormulario(Long id, Formulario formulario) {
        if (formularioRepository.existsById(id)) {
            formulario.setId(id);
            return formularioRepository.save(formulario);
        }
        return null;
    }

    public List<Formulario> getAllFormularios() {
        return formularioRepository.findAll();
    }


}
