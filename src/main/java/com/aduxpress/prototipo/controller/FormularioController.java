package com.aduxpress.prototipo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aduxpress.prototipo.model.Formulario;
import com.aduxpress.prototipo.service.FormularioService;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/formularios")
public class FormularioController {

    @Autowired
    private FormularioService formularioService;

    @PostMapping
    public Formulario createFormulario(@RequestBody Formulario formulario) {
        return formularioService.saveFormulario(formulario);
    }
    
    @GetMapping("/{id}")
    public Formulario getFormularioById(@PathVariable Long id) {
        return formularioService.getFormularioById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteFormulario(@PathVariable Long id) {
        formularioService.deleteFormulario(id);
    }

    @PutMapping("/{id}")
    public Formulario updateFormulario(@PathVariable Long id, @RequestBody Formulario formulario) {
        return formularioService.updateFormulario(id, formulario);
    }
    

}
