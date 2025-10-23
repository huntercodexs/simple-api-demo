package br.com.bradesco.cnsg.persisteposvenda.application.usecase;

import br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.entrada.IncluirTransacaoHubAverbDTO;
import br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.saida.ObterTransacaoHubDTO;
import br.com.bradesco.cnsg.persisteposvenda.adapter.database.dto.ConteudoRespostaTransacaoDTO;
import br.com.bradesco.cnsg.persisteposvenda.adapter.database.dto.HistoricoStatusDTO;
import br.com.bradesco.cnsg.persisteposvenda.adapter.database.entity.TabelaMongoDbHubAverb;
import br.com.bradesco.cnsg.persisteposvenda.adapter.database.repository.primary.HubAverbRepositorio;
import br.com.bradesco.cnsg.persisteposvenda.application.domain.exception.PosVendaException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codehaus.commons.nullanalysis.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HubAverbCasoUso {

    private static final Logger log = LoggerFactory.getLogger(HubAverbCasoUso.class);

    private final HubAverbRepositorio hubAverbRepositorio;

    public HubAverbCasoUso(HubAverbRepositorio hubAverbRepositorio) {
        this.hubAverbRepositorio = hubAverbRepositorio;
    }

    public void incluirTransacao(final IncluirTransacaoHubAverbDTO incluirTransacaoHubAverbDTO) {

        TabelaMongoDbHubAverb entidade = new TabelaMongoDbHubAverb(incluirTransacaoHubAverbDTO);

        log.info("Incluindo registro na base de dados Averbadoras: {}", entidade);

        try {

            hubAverbRepositorio.save(entidade);

        } catch (Exception erro) {

            // Erro de chave duplicada no MongoDB
            if (erro.getMessage().contains("E11000")) {
                log.error("Erro de chave duplicada ao incluir registro na base de dados: {}", erro.getMessage());
                throw new PosVendaException("4001100");
            }
            log.error("Erro ao incluir registro na base de dados: {}", erro.getMessage());
            throw new PosVendaException("5001143");

        }

        log.info("Registro incluído com sucesso na base de dados");
    }

    public ObterTransacaoHubDTO consultaTransacaoControle(@NotNull final String numeroProtocolo) {
        log.info("Consultando transação de controle na base Averbadoras. protocolo: {}", numeroProtocolo);

        final var entidadeOpcional = hubAverbRepositorio.findByNumeroProtocoloAgg(numeroProtocolo);

        log.info("Consulta finalizada: {}", entidadeOpcional);

        if (entidadeOpcional.isPresent()) {
            return new ObterTransacaoHubDTO(
                    entidadeOpcional.get().getNumContrBrad(),
                    entidadeOpcional.get().getSTentvTrans(),
                    entidadeOpcional.get().getNPriorTrans(),
                    entidadeOpcional.get().getUltimoStatus());
        } else {
            throw new PosVendaException("4040312");
        }
    }

    public ConteudoRespostaTransacaoDTO consultaRetornoHub(@NotNull final String numeroProtocolo) {
        log.info("Consultando retorno de solicitação do hub averbadoras. protocolo: {}", numeroProtocolo);

        final var entidadeOpcional = hubAverbRepositorio.findContRespByNumeroProtocolo(numeroProtocolo);

        log.info("Consulta finalizada: {}", entidadeOpcional);

        if (entidadeOpcional.isPresent()) {
            final ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.convertValue(entidadeOpcional.get().getSContdRespTrans(), ConteudoRespostaTransacaoDTO.class);
        } else {
            throw new PosVendaException("4040312");
        }
    }

    public void subtraiTentTransacao(@NotNull final String numeroProtocolo) {
        log.info("Atualizando quantidades de tentativa de transação. protocolo: {}", numeroProtocolo);
        try{
            hubAverbRepositorio.subtraiTentativa(numeroProtocolo);

            log.info("Consulta finalizada: {}");
        }catch(Exception e){
            throw new PosVendaException("4040312");
        }


    }

    public void registraStatusTransacaoHub(@NotNull final String cdProtocolo, HistoricoStatusDTO historicoStatus) {
        log.info("Consultando registro na base de dados para o contrato: {}", cdProtocolo);
        try{

            hubAverbRepositorio.registraStatusTransacaoHub(cdProtocolo, historicoStatus);

            log.info("Consulta finalizada: {}");
        }catch(Exception e){
            throw new PosVendaException("4040312");
        }


    }

    public void registraRetornoSolicitacaoDesaverb(@NotNull final String cdProtocolo, ConteudoRespostaTransacaoDTO conteudoRespostaTransacao) {
        log.info("Consultando registro na base de dados para o contrato: {}", cdProtocolo);
        try{

            hubAverbRepositorio.registraRetornoSolicitacaoDesaverb(cdProtocolo, conteudoRespostaTransacao);

            log.info("Consulta finalizada: {}");
        }catch(Exception e){
            throw new PosVendaException("4040312");
        }


    }
}
