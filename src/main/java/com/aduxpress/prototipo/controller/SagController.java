package com.aduxpress.prototipo.controller;

import com.aduxpress.prototipo.model.SAGDeclaracion;
import com.aduxpress.prototipo.service.SAGDeclaracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SagController {

    @Autowired
    private SAGDeclaracionService sagDeclaracionService;

    @GetMapping("/sag")
    public String mostrarFormularioSag() {
        return "sag";
    }

    @PostMapping("/sag/registrar")
    public String registrarDeclaracion(@ModelAttribute SAGDeclaracion declaracion) {
        sagDeclaracionService.save(declaracion);
        return "redirect:/sag?exito";
    }
}
