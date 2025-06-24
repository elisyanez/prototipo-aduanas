package com.aduxpress.prototipo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aduxpress.prototipo.model.Usuario;
import com.aduxpress.prototipo.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario updateUsuario(Long id, Usuario usuario) {
        if (usuarioRepository.existsById(id)) {
            usuario.setId(id);
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario findByUsernameAndContrasena(String username, String contrasena) {
        return usuarioRepository.findByUsernameAndContrasena(username, contrasena);
    }

    public Usuario obtenerUsuarioPorUsername(String username, String contrasena) {
        return usuarioRepository.findByUsernameAndContrasena(username, contrasena);
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario findByTokenRecuperacion(String token) {
        return usuarioRepository.findByTokenRecuperacion(token);
    }
}
