package br.com.bradesco.cnsg.persisteposvenda.adapter.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record RetentativasDTO (

        @JsonProperty("dProxTentvTrans")
        LocalDateTime dProxTentvTrans,

        @NotNull(message = "Campo qTentvRstteTrans é obrigatório")
        @JsonProperty("qTentvRstteTrans")
        Integer qTentvRstteTrans
){
        public RetentativasDTO(
                @JsonProperty("dProxTentvTrans")
                LocalDateTime dProxTentvTrans,

                @JsonProperty("qTentvRstteTrans")
                Integer qTentvRstteTrans
        ){
                this.qTentvRstteTrans = qTentvRstteTrans;
                this.dProxTentvTrans = LocalDateTime.now();
        }
}
