package com.aduxpress.prototipo.controller;

import com.aduxpress.prototipo.model.Menor;
import com.aduxpress.prototipo.service.MenorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MenorController {

    @Autowired
    private MenorService menorService;

    @PostMapping("/menores/registrar")
    public String registrarMenor(@ModelAttribute Menor menor) {
        menorService.save(menor);
        return "redirect:/menores?exito";
    }

    @GetMapping("/menores")
    public String mostrarFormularioMenores() {
        return "menores";
    }
}
