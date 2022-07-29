package com.ifba.postnotes.requests;

import lombok.Data;

@Data
public class NoteAlter {
    private Long id;
    private String title;
    private String text;
    private String image;
    private String color;

}
