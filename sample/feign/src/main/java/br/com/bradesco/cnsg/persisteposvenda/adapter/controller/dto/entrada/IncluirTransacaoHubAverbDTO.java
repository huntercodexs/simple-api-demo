package br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.entrada;

import br.com.bradesco.cnsg.persisteposvenda.adapter.database.dto.ConteudoRespostaTransacaoDTO;
import br.com.bradesco.cnsg.persisteposvenda.adapter.database.dto.HistoricoStatusDTO;
import br.com.bradesco.cnsg.persisteposvenda.adapter.database.dto.VrEntradaDTO;
import br.com.bradesco.cnsg.persisteposvenda.adapter.database.dto.RetentativasDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

public record IncluirTransacaoHubAverbDTO(


        @NotNull(message = "O campo sContdRquisTrans é obrigatório")
        @JsonProperty("sContdRquisTrans")
        VrEntradaDTO sContdRquisTrans,

        @NotNull(message = "O campo cProtTrans é obrigatório")
        @JsonProperty("cProtTrans")
        String cProtTrans,

        @NotNull(message = "O campo iJorndTrans é obrigatório")
        @JsonProperty("iJorndTrans")
        String iJorndTrans,

        @NotNull(message = "O campo aHistEstTrans é obrigatório")
        @JsonProperty("aHistEstTrans")
        List<HistoricoStatusDTO> aHistEstTrans,


        @NotNull(message = "O campo sTentvTrans é obrigatório")
        @JsonProperty("sTentvTrans")
        RetentativasDTO sTentvTrans,

        @NotNull(message = "O campo cRetorTrans é obrigatório")
        @JsonProperty("cRetorTrans")
        String cRetorTrans,

        @NotNull(message = "O campo iProdtTrans é obrigatório")
        @JsonProperty("iProdtTrans")
        String iProdtTrans,

        @NotNull(message = "O campo rProcmTrans é obrigatório")
        @JsonProperty("rProcmTrans")
        String rProcmTrans,

        @NotNull(message = "O campo nPriorTrans é obrigatório")
        @JsonProperty("nPriorTrans")
        Integer nPriorTrans,

        @JsonProperty("sContdRespTrans")
        Object sContdRespTrans
) {
}
