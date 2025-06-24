package com.aduxpress.prototipo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aduxpress.prototipo.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

import com.aduxpress.prototipo.model.Usuario;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(
            @RequestParam String username,
            @RequestParam String contrasena,
            HttpSession session,
            Model model) {

        Usuario usuario = usuarioService.obtenerUsuarioPorUsername(username, contrasena);
        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
            // Guardar el usuario en la sesión
            session.setAttribute("usuario", usuario);
            return "redirect:/home";
        } else {
            model.addAttribute("error", true);
            return "login";
        }
    }

}
