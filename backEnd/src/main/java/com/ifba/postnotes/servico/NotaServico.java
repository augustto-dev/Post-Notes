package com.ifba.postnotes.servico;

import com.ifba.postnotes.dominio.NotaDominio;
import com.ifba.postnotes.mapeamento.NotaMapeamento;
import com.ifba.postnotes.repositorio.NotaRepositorio;
import com.ifba.postnotes.solicitacoes.NotaAlteracao;
import com.ifba.postnotes.solicitacoes.NotaSalvamento;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotaServico {
    private final NotaRepositorio notaRepositorioVar;

    public List<NotaDominio> listAll() {
        return notaRepositorioVar.findAll();
    }

    public List<NotaDominio> findByTitulo(String titulo) {
        return notaRepositorioVar.findByTitulo(titulo);
    }

    public NotaDominio findByIdOrThrowBadRequestException(Long id) {
        return notaRepositorioVar.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi encontrado o id da nota"));
    }

    public NotaDominio save(NotaSalvamento notaSalvamentoVar) {
        return notaRepositorioVar.save(NotaMapeamento.INSTANCE.toNota(notaSalvamentoVar));
    }

    public void replace(NotaAlteracao notaAlteracaoVar) {
        NotaDominio notaDominioSalva = findByIdOrThrowBadRequestException(notaAlteracaoVar.getId());
        NotaDominio notaDominioAlterada = NotaMapeamento.INSTANCE.toNota(notaAlteracaoVar);
        notaDominioAlterada.setId(notaDominioSalva.getId());
        notaRepositorioVar.save(notaDominioAlterada);
    }

    public void delete(Long id) {
        notaRepositorioVar.delete(findByIdOrThrowBadRequestException(id));
    }
}
