package br.com.bradesco.cnsg.persisteposvenda.application.usecase;

import br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.entrada.AtualizacoesPosVendaDTO;
import br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.entrada.InclusoesPosVendaDTO;
import br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.saida.ConsultasPosVendaDTO;
import br.com.bradesco.cnsg.persisteposvenda.adapter.database.entity.TabelaMongoDbEntidade;
import br.com.bradesco.cnsg.persisteposvenda.adapter.database.repository.secondary.BasePosVendaRepositorio;
import br.com.bradesco.cnsg.persisteposvenda.application.domain.exception.PosVendaException;
import org.codehaus.commons.nullanalysis.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BasePosVendaCasoUso {

    private static final Logger log = LoggerFactory.getLogger(BasePosVendaCasoUso.class);

    private final BasePosVendaRepositorio basePosVendaRepositorio;

    public BasePosVendaCasoUso(BasePosVendaRepositorio basePosVendaRepositorio) {
        this.basePosVendaRepositorio = basePosVendaRepositorio;
    }

    public void incluirRegistro(final InclusoesPosVendaDTO inclusoesPosVendaDTO) {

        TabelaMongoDbEntidade entidade = new TabelaMongoDbEntidade(inclusoesPosVendaDTO);

        log.info("Incluindo registro na base de dados: {}", entidade);

        try {
            basePosVendaRepositorio.save(entidade);

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

    public void atualizarRegistro(final String numeroContrato, final AtualizacoesPosVendaDTO atualizacoesPosVendaDTO) {

        log.info("Buscando registro na base de dados para atualização: {}", atualizacoesPosVendaDTO);

        final var entidadeOpcional = basePosVendaRepositorio.findByNumeroContrato(numeroContrato);

        log.info("Busca finalizada: {}", entidadeOpcional);

        if (entidadeOpcional.isPresent()) {

            final var entidade = entidadeOpcional.get();

            entidade.setSituacaoRegistro(atualizacoesPosVendaDTO.situacaoRegistro());

            log.info("Atualizando registro na base de dados: {}", entidade);

            basePosVendaRepositorio.save(entidade);

            log.info("Registro atualizado com sucesso na base de dados");

        } else {
            throw new PosVendaException("4040312");
        }
    }

    public ConsultasPosVendaDTO consultarRegistro(@NotNull final String numeroContrato) {
        log.info("Consultando registro na base de dados para o contrato: {}", numeroContrato);

        final var entidadeOpcional = basePosVendaRepositorio.findByNumeroContrato(numeroContrato);

        log.info("Consulta finalizada: {}", entidadeOpcional);

        if (entidadeOpcional.isPresent()) {
            return new ConsultasPosVendaDTO(entidadeOpcional.get().getSituacaoRegistro());
        } else {
            throw new PosVendaException("4040312");
        }
    }
}
