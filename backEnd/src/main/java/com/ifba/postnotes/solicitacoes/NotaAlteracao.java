package com.ifba.postnotes.solicitacoes;

import lombok.Data;

@Data
public class NotaAlteracao {
    private Long id;
    private String titulo;
    private String texto;
    private String imagem;
    private String cor;

}
