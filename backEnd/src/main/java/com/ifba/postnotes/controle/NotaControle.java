package com.ifba.postnotes.controle;

import com.ifba.postnotes.dominio.NotaDominio;
import com.ifba.postnotes.servico.NotaServico;
import com.ifba.postnotes.solicitacoes.NotaAlteracao;
import com.ifba.postnotes.solicitacoes.NotaSalvamento;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notaListItem")
@AllArgsConstructor
public class NotaControle {
    private NotaServico notaServicoVar;

    @GetMapping
    public ResponseEntity<List<NotaDominio>> list() {
        return ResponseEntity.ok(notaServicoVar.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<NotaDominio> findById(@PathVariable Long id) {
        return ResponseEntity.ok(notaServicoVar.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<NotaDominio>> findByTitulo(@RequestParam String titulo) {
        return ResponseEntity.ok(notaServicoVar.findByTitulo(titulo));
    }

    @PostMapping
    public ResponseEntity<NotaDominio> save(@RequestBody NotaSalvamento notaSalvamentoVar) {
        return new ResponseEntity<>(notaServicoVar.save(notaSalvamentoVar), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody NotaAlteracao notaAlteracaoVar) {
        notaServicoVar.replace(notaAlteracaoVar);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        notaServicoVar.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
