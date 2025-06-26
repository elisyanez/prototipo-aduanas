package com.aduxpress.prototipo.repository;

import com.aduxpress.prototipo.model.Historial;
import com.aduxpress.prototipo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface HistorialRepository extends JpaRepository<Historial, Long> {
    List<Historial> findByUsuarioOrderByFechaDesc(Usuario usuario);
    List<Historial> findByUsuarioAndFechaBetweenOrderByFechaDesc(Usuario usuario, LocalDateTime inicio, LocalDateTime fin);
    List<Historial> findByFechaBetweenOrderByFechaDesc(LocalDateTime inicio, LocalDateTime fin);
}