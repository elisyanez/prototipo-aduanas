package com.aduxpress.prototipo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.aduxpress.prototipo.model.Usuario;
import com.aduxpress.prototipo.service.HistorialService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SessionController {

    @Autowired
    private HistorialService hs;
    
    @GetMapping("/")
    public String redireccionarLogin() {
        return "redirect:/login";
    }
    
    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        // Verificar si el usuario está logueado
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        
        // Agregar datos del usuario al modelo
        model.addAttribute("usuario", session.getAttribute("usuario"));
        return "home";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            hs.registrarAccion(usuario, "Cierre de sesión", "El usuario cerró sesión correctamente.");
        }
        session.invalidate();
        return "redirect:/login";
    }

}