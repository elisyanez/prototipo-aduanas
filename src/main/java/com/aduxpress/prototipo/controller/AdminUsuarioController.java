package com.aduxpress.prototipo.controller;

import com.aduxpress.prototipo.model.Usuario;
import com.aduxpress.prototipo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/usuarios")
public class AdminUsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.getAllUsuarios());
        return "admin-usuarios";
    }

    @PostMapping("/crear")
    public String crearUsuario(
            @RequestParam String nombre,
            @RequestParam String rut,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String contrasena,
            @RequestParam String rol
    ) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setRut(rut);
        usuario.setUsername(username);
        usuario.setEmail(email);
        usuario.setContrasena(contrasena);
        usuario.setRol(rol);
        usuarioService.saveUsuario(usuario);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        model.addAttribute("usuario", usuario);
        return "editar-usuario";
    }

    @PostMapping("/editar")
    public String editarUsuario(
            @RequestParam Long id,
            @RequestParam String nombre,
            @RequestParam String rut,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String contrasena,
            @RequestParam String rol
    ) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        usuario.setNombre(nombre);
        usuario.setRut(rut);
        usuario.setUsername(username);
        usuario.setEmail(email);
        usuario.setContrasena(contrasena);
        usuario.setRol(rol);
        usuarioService.saveUsuario(usuario);
        return "redirect:/admin/usuarios";
    }

    @PostMapping("/eliminar")
    public String eliminarUsuario(@RequestParam Long id) {
        usuarioService.deleteUsuario(id);
        return "redirect:/admin/usuarios";
    }
}