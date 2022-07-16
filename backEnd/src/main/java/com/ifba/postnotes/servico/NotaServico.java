package com.ifba.postnotes.servico;

import com.ifba.postnotes.dominio.Nota;
import com.ifba.postnotes.repositorio.NotaRepositorio;
import com.ifba.postnotes.solicitacoes.NotaAlterar;
import com.ifba.postnotes.solicitacoes.NotaSalvar;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotaServico {
    private final NotaRepositorio notaRepositorio;

    public List<Nota> listAll() {
        return notaRepositorio.findAll();
    }

    public Nota findByIdOrThrowBadRequestException(Long id) {
        return notaRepositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi encontrado o id do cartão"));
    }

    public Nota save(@NotNull NotaSalvar notaSalvar) {
        Nota notaListItem = Nota.builder().titulo(notaSalvar.getTitulo()).build();
        return notaRepositorio.save(notaListItem);
    }

    public void delete(Long id) {
        notaRepositorio.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(@NotNull NotaAlterar notaAlterar) {
        Nota notaSalva = findByIdOrThrowBadRequestException(notaAlterar.getId());
        Nota notaListItem = Nota.builder()
                .id(notaSalva.getId())
                .titulo(notaAlterar.getTitulo()).build();
        notaRepositorio.save(notaListItem);
    }
}
