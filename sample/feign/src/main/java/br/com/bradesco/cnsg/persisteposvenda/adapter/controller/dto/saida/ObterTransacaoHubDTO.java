package br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.saida;

import br.com.bradesco.cnsg.persisteposvenda.adapter.database.dto.HistoricoStatusDTO;
import br.com.bradesco.cnsg.persisteposvenda.adapter.database.dto.RetentativasDTO;

public record ObterTransacaoHubDTO(

        String numContratoBradesco,
        RetentativasDTO sTentvTrans,
        Integer nPriorTrans,
        HistoricoStatusDTO ultimoStatus
){
}
