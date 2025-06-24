package com.aduxpress.prototipo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "formulario")
public class Formulario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "contenido")
    private String contenido;

}
