package com.aduxpress.prototipo.service;

import com.aduxpress.prototipo.model.Historial;
import com.aduxpress.prototipo.model.Usuario;
import com.aduxpress.prototipo.repository.HistorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistorialService {

    @Autowired
    private HistorialRepository historialRepository;

    public List<Historial> obtenerHistorialPorUsuario(Usuario usuario) {
        return historialRepository.findByUsuarioOrderByFechaDesc(usuario);
    }

    public void guardarHistorial(Historial historial) {
        historialRepository.save(historial);
    }

    public void registrarAccion(Usuario usuario, String accion, String detalle) {
        Historial historial = new Historial();
        historial.setUsuario(usuario);
        historial.setFecha(LocalDateTime.now());
        historial.setAccion(accion);
        historial.setDetalle(detalle);
        historialRepository.save(historial);
    }
}