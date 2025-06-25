package com.aduxpress.prototipo.controller;

import com.aduxpress.prototipo.model.Usuario;
import com.aduxpress.prototipo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RestablecerContrasenaController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/restablecer-contrasena")
    public String mostrarFormulario(@RequestParam String token, Model model) {
        Usuario usuario = usuarioService.findByTokenRecuperacion(token);
        if (usuario == null) {
            model.addAttribute("tokenInvalido", true);
            return "restablecer-contrasena";
        }
        model.addAttribute("token", token);
        return "restablecer-contrasena";
    }

    @PostMapping("/restablecer-contrasena")
    public String procesarRestablecimiento(@RequestParam String token, @RequestParam String contrasena, Model model) {
        Usuario usuario = usuarioService.findByTokenRecuperacion(token);
        if (usuario == null) {
            model.addAttribute("tokenInvalido", true);
            return "restablecer-contrasena";
        }
        usuario.setContrasena(contrasena);
        usuario.setTokenRecuperacion(null);
        usuarioService.saveUsuario(usuario);
        model.addAttribute("exito", true);
        return "restablecer-contrasena";
    }
}