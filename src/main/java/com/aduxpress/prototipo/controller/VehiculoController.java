package com.aduxpress.prototipo.controller;

import com.aduxpress.prototipo.model.Vehiculo;
import com.aduxpress.prototipo.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @PostMapping("/vehiculos/registrar")
    public String registrarVehiculo(@ModelAttribute Vehiculo vehiculo) {
        vehiculoService.save(vehiculo);
        return "redirect:/vehiculos?exito";
    }

    @GetMapping("/vehiculos")
    public String mostrarFormularioVehiculos() {
        return "vehiculos";
    }
}
