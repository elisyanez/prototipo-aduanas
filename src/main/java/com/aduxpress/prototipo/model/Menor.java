package com.aduxpress.prototipo.model;

import jakarta.persistence.*;

@Entity
public class Menor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreMenor;
    private String rutMenor;
    private String acompanante;
    private String autorizacion;
    private String nombrePadre;
    private String rutPadre;
    private String tipoAutorizacion; // "notarial" o "judicial"

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreMenor() { return nombreMenor; }
    public void setNombreMenor(String nombreMenor) { this.nombreMenor = nombreMenor; }

    public String getRutMenor() { return rutMenor; }
    public void setRutMenor(String rutMenor) { this.rutMenor = rutMenor; }

    public String getAcompanante() { return acompanante; }
    public void setAcompanante(String acompanante) { this.acompanante = acompanante; }

    public String getAutorizacion() { return autorizacion; }
    public void setAutorizacion(String autorizacion) { this.autorizacion = autorizacion; }

    public String getNombrePadre() { return nombrePadre; }
    public void setNombrePadre(String nombrePadre) { this.nombrePadre = nombrePadre; }

    public String getRutPadre() { return rutPadre; }
    public void setRutPadre(String rutPadre) { this.rutPadre = rutPadre; }

    public String getTipoAutorizacion() { return tipoAutorizacion; }
    public void setTipoAutorizacion(String tipoAutorizacion) { this.tipoAutorizacion = tipoAutorizacion; }
}
