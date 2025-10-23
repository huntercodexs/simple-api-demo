package br.com.bradesco.cnsg.persisteposvenda.adapter.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ConteudoRespostaTransacaoDTO(


        @NotNull(message = "O campo situacaoRetorno é obrigatório")
        @JsonProperty("situacaoRetorno")
        String situacaoRetorno,

        @NotNull(message = "O campo codRetornoHttp é obrigatório")
        @JsonProperty("codRetornoHttp")
        String codRetornoHttp,

        @NotNull(message = "O campo msgRetornoHttp é obrigatório")
        @JsonProperty("msgRetornoHttp")
        String msgRetornoHttp,

        @JsonProperty("retornosDataprev")
        List<RetornosDataprevDTO> retornosDataprev
) {
}
