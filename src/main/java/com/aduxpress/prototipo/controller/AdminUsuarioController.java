package com.aduxpress.prototipo.controller;

import com.aduxpress.prototipo.model.Usuario;
import com.aduxpress.prototipo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/usuarios")
public class AdminUsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "admin-usuarios";
    }

    @PostMapping("/eliminar")
    public String eliminarUsuario(@RequestParam Long id) {
        usuarioService.deleteUsuario(id);
        return "redirect:/admin/usuarios";
    }
}