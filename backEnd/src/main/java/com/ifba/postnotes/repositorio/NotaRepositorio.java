package com.ifba.postnotes.repositorio;

import com.ifba.postnotes.dominio.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepositorio extends JpaRepository<Nota, Long> {
}
