package com.aduxpress.prototipo.controller;

import com.aduxpress.prototipo.model.Usuario;
import com.aduxpress.prototipo.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ConfiguracionController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/configuracion")
    public String mostrarConfiguracion(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login";
        }
        model.addAttribute("usuario", usuario);
        return "configuracion";
    }

    @PostMapping("/configuracion")
    public String actualizarConfiguracion(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String contrasena,
            @RequestParam String accion,
            HttpSession session,
            Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login";
        }

        if ("cambiarEmail".equals(accion)) {
            // Verifica si el email ya existe y no es el del usuario actual
            Usuario usuarioExistente = usuarioService.findByEmail(email);
            if (usuarioExistente != null && !usuarioExistente.getId().equals(usuario.getId())) {
                model.addAttribute("usuario", usuario);
                model.addAttribute("emailExistente", true);
                return "configuracion";
            }
            usuario.setEmail(email);
            usuarioService.saveUsuario(usuario);
            session.setAttribute("usuario", usuario);
            model.addAttribute("usuario", usuario);
            model.addAttribute("exito", true);
        } else if ("cambiarContrasena".equals(accion)) {
            if (contrasena != null && !contrasena.isEmpty()) {
                usuario.setContrasena(contrasena);
                usuarioService.saveUsuario(usuario);
                session.setAttribute("usuario", usuario);
                model.addAttribute("usuario", usuario);
                model.addAttribute("exito", true);
            }
        }

        return "configuracion";
    }
}
