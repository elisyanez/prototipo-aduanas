package com.aduxpress.prototipo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aduxpress.prototipo.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsernameAndContrasena(String username, String contrasena);
    Usuario findByEmail(String email);
    Usuario findByTokenRecuperacion(String tokenRecuperacion);
}
