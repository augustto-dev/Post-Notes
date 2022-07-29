package com.ifba.postnotes.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class NoteSave {
    @NotEmpty(message = "Os campos não podem estar em branco")
    private String title;
    private String text;
    private String image;
    private String color;
}
