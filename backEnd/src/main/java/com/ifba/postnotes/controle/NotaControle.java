package com.ifba.postnotes.controle;

import com.ifba.postnotes.dominio.Nota;
import com.ifba.postnotes.servico.NotaServico;
import com.ifba.postnotes.solicitacoes.NotaAlterar;
import com.ifba.postnotes.solicitacoes.NotaSalvar;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notaListItem")
@AllArgsConstructor
public class NotaControle {
    private NotaServico notaServico;

    @GetMapping
    public ResponseEntity<List<Nota>> list() {
        return ResponseEntity.ok(notaServico.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Nota> findById(@PathVariable Long id) {
        return ResponseEntity.ok(notaServico.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<Nota> save(@RequestBody NotaSalvar notaSalvar) {
        return new ResponseEntity<>(notaServico.save(notaSalvar), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        notaServico.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody NotaAlterar notaAlterar) {
        notaServico.replace(notaAlterar);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
