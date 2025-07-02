package com.aduxpress.prototipo.model;

import jakarta.persistence.*;

@Entity
public class SAGDeclaracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String productos; // "si" o "no"
    private String detalle;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getProductos() { return productos; }
    public void setProductos(String productos) { this.productos = productos; }

    public String getDetalle() { return detalle; }
    public void setDetalle(String detalle) { this.detalle = detalle; }
}
