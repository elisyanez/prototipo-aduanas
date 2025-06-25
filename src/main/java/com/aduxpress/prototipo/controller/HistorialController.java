package com.aduxpress.prototipo.controller;

import com.aduxpress.prototipo.model.Usuario;
import com.aduxpress.prototipo.service.HistorialService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HistorialController {

    @Autowired
    private HistorialService historialService;

    @GetMapping("/historial")
    public String verHistorial(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            model.addAttribute("sesionInactiva", true);
            return "historial";
        }
        try {
            var historial = historialService.obtenerHistorialPorUsuario(usuario);
            model.addAttribute("historial", historial);
        } catch (Exception e) {
            model.addAttribute("error", true);
        }
        return "historial";
    }

    
}