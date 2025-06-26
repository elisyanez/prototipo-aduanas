package com.aduxpress.prototipo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SagController {

    @GetMapping("/sag")
    public String mostrarFormularioSag() {
        return "sag";
    }
}
