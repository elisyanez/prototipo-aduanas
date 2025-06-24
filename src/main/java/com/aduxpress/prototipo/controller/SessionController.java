package com.aduxpress.prototipo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class SessionController {
    
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
        session.invalidate();
        return "redirect:/login";
    }
}