package com.aduxpress.prototipo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    private String username;
    
    @Column(name = "token_recuperacion")
    private String tokenRecuperacion;

    @Column(name = "rol", nullable = false)
    private String rol = "USER"; // Valores posibles: "ADMIN", "USER"

    public String getTokenRecuperacion() { return tokenRecuperacion; }
    public void setTokenRecuperacion(String tokenRecuperacion) { this.tokenRecuperacion = tokenRecuperacion; }

}
