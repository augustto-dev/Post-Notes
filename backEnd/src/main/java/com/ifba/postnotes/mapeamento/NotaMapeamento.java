package com.ifba.postnotes.mapeamento;

import com.ifba.postnotes.dominio.NotaDominio;
import com.ifba.postnotes.solicitacoes.NotaAlteracao;
import com.ifba.postnotes.solicitacoes.NotaSalvamento;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class NotaMapeamento {
    public static final NotaMapeamento INSTANCE = Mappers.getMapper(NotaMapeamento.class);

    public abstract NotaDominio toNota(NotaSalvamento notaSalvamentoVar);

    public abstract NotaDominio toNota(NotaAlteracao notaAlteracaoVar);
}
