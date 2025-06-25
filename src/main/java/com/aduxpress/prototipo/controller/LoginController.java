package com.aduxpress.prototipo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aduxpress.prototipo.service.UsuarioService;
import com.aduxpress.prototipo.service.HistorialService;

import jakarta.servlet.http.HttpSession;

import com.aduxpress.prototipo.model.Usuario;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private HistorialService hs;

    @GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {}

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

        Usuario usuario = usuarioService.findByUsername(username);
        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
            session.setAttribute("usuario", usuario);
            hs.registrarAccion(usuario, "Inicio de sesión", "El usuario inició sesión correctamente.");
            return "redirect:/home";
        } else {
            model.addAttribute("error", true);
            return "login";
        }
    }

}
