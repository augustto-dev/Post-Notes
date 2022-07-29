package com.ifba.postnotes.mapper;

import com.ifba.postnotes.domain.Note;
import com.ifba.postnotes.requests.NoteAlter;
import com.ifba.postnotes.requests.NoteSave;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class NoteMapper {
    public static final NoteMapper INSTANCE = Mappers.getMapper(NoteMapper.class);

    public abstract Note toNote(NoteSave noteSave);

    public abstract Note toNote(NoteAlter noteAlter);
}
