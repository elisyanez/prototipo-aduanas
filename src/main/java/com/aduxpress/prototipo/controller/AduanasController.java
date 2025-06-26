package com.aduxpress.prototipo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AduanasController {

    @GetMapping("/aduanas")
    public String mostrarAduanas() {
        return "aduanas";
    }
}
