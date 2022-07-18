package com.ifba.postnotes.repositorio;

import com.ifba.postnotes.dominio.NotaDominio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepositorio extends JpaRepository<NotaDominio, Long> {
    List<NotaDominio> findByTitulo(String titulo);
}
