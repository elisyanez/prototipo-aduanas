package com.aduxpress.prototipo.repository;

import com.aduxpress.prototipo.model.Formulario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormularioRepository extends JpaRepository <Formulario, Long> {

}
