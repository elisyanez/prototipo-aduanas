package com.aduxpress.prototipo.controller;

import com.aduxpress.prototipo.model.Usuario;
import com.aduxpress.prototipo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class OlvidoContrasenaController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/olvido-contrasena")
    public String mostrarFormulario() {
        return "olvido-contrasena";
    }

    @PostMapping("/olvido-contrasena")
    public String procesarOlvidoContrasena(@RequestParam String email, Model model) {
        Usuario usuario = usuarioService.findByEmail(email);
        if (usuario == null) {
            model.addAttribute("emailNoExiste", true);
        } else {
            // Generar token único
            String token = UUID.randomUUID().toString();
            usuario.setTokenRecuperacion(token);
            usuarioService.saveUsuario(usuario);

            String asunto = "Recuperación de contraseña - Aduanas de Chile";
            String mensaje = "Hola " + usuario.getNombre() + ",\n\n"
                    + "Para restablecer tu contraseña, haz clic en el siguiente enlace:\n"
                    + "http://localhost:8080/restablecer-contrasena?token=" + token + "\n\n"
                    + "Si no solicitaste este cambio, ignora este mensaje.";

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email);
            mailMessage.setSubject(asunto);
            mailMessage.setText(mensaje);

            mailSender.send(mailMessage);

            model.addAttribute("exito", true);
        }
        return "olvido-contrasena";
    }
}