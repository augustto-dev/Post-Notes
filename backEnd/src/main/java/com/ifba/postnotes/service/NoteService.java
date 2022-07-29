package com.ifba.postnotes.service;

import com.ifba.postnotes.domain.Note;
import com.ifba.postnotes.exception.BadRequestException;
import com.ifba.postnotes.mapper.NoteMapper;
import com.ifba.postnotes.repository.NoteRepository;
import com.ifba.postnotes.requests.NoteAlter;
import com.ifba.postnotes.requests.NoteSave;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public List<Note> listAll() {
        return noteRepository.findAll();
    }

    public List<Note> findByTitle(String title) {
        return noteRepository.findByTitle(title);
    }

    public Note findByIdOrThrowBadRequestException(Long id) {
        return noteRepository.findById(id).orElseThrow(() -> new BadRequestException("Não foi encontrado o id da nota"));
    }

    @Transactional
    public Note save(NoteSave noteSave) {
        return noteRepository.save(NoteMapper.INSTANCE.toNote(noteSave));
    }

    public void replace(NoteAlter noteAlter) {
        Note noteSave = findByIdOrThrowBadRequestException(noteAlter.getId());
        Note noteAlterFinal = NoteMapper.INSTANCE.toNote(noteAlter);
        noteAlterFinal.setId(noteSave.getId());
        noteRepository.save(noteAlterFinal);
    }

    public void delete(Long id) {
        noteRepository.delete(findByIdOrThrowBadRequestException(id));
    }
}
