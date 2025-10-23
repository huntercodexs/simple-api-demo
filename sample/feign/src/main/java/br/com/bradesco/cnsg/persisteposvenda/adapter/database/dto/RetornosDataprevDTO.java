package br.com.bradesco.cnsg.persisteposvenda.adapter.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RetornosDataprevDTO(
        @JsonProperty("codRetorno")
        String codRetorno,

        @JsonProperty("msgRetorno")
        String msgRetorno
) {
}
