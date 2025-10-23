package br.com.bradesco.cnsg.persisteposvenda.adapter.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record VrEntradaDTO(


        @NotNull(message = "O id da transacao é obrigatório")
        @JsonProperty("jornada")
        String jornada,

        @NotNull(message = "O nome da averbadora é obrigatório")
        @JsonProperty("codConvenio")
        String codConvenio,

        @NotNull(message = "O número do contrato bradesco é obrigatório")
        @JsonProperty("numContratoBradesco")
        String numContratoBradesco,

        @NotNull(message = "O número do contrato dataprev é obrigatório")
        @JsonProperty("numContratoDataprev")
        String numContratoDataprev,

        @NotNull(message = "O código de inscrição do empregador é obrigatório")
        @JsonProperty("codInscricaoEmpregador")
        String codInscricaoEmpregador,

        @NotNull(message = "O cpf do trabalhador é obrigatório")
        @JsonProperty("cpfTrabalhador")
        String cpfTrabalhador,

        @NotNull(message = "A matrícula do trabalhador é obrigatório")
        @JsonProperty("matriculaTrabalhador")
        String matriculaTrabalhador,

        @NotNull(message = "O motivo de exclusão é obrigatório")
        @JsonProperty("motivoExclusao")
        String motivoExclusao,

        @JsonProperty("numInscricaoEmpregador")
        String numInscricaoEmpregador
) {
}
