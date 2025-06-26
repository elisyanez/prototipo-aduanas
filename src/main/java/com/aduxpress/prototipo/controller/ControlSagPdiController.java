package com.aduxpress.prototipo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControlSagPdiController {

    @GetMapping("/control-sag-pdi")
    public String mostrarPanelControl() {
        return "control-sag-pdi";
    }
}
