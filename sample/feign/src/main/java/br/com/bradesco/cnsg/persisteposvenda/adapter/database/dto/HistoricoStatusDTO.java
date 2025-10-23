package br.com.bradesco.cnsg.persisteposvenda.adapter.database.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record HistoricoStatusDTO (

        @JsonProperty("rErroTrans")
        String rErroTrans,

        @JsonProperty("rEstTrans")
        String rEstTrans,

        @JsonProperty("dEstTrans")
        LocalDateTime dEstTrans
){
        @JsonCreator
        public HistoricoStatusDTO(
                @JsonProperty("rErroTrans")
                String rErroTrans,

                @JsonProperty("rEstTrans")
                String rEstTrans,

                @JsonProperty("dEstTrans")
                LocalDateTime dEstTrans
        ){
                this.rErroTrans = rErroTrans;
                this.rEstTrans = rEstTrans;
                this.dEstTrans = LocalDateTime.now();
        }
}
