package com.ifba.postnotes.controller;

import com.ifba.postnotes.domain.Note;
import com.ifba.postnotes.requests.NoteAlter;
import com.ifba.postnotes.requests.NoteSave;
import com.ifba.postnotes.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("notes")
@AllArgsConstructor
public class NoteController {
    private NoteService noteService;

    @GetMapping
    public ResponseEntity<List<Note>> list() {
        return ResponseEntity.ok(noteService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Note> findById(@PathVariable Long id) {
        return ResponseEntity.ok(noteService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Note>> findByTitle(@RequestParam String title) {
        return ResponseEntity.ok(noteService.findByTitle(title));
    }

    @PostMapping
    public ResponseEntity<Note> save(@RequestBody @Valid NoteSave noteSave) {
        return new ResponseEntity<>(noteService.save(noteSave), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody NoteAlter noteAlter) {
        noteService.replace(noteAlter);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        noteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
