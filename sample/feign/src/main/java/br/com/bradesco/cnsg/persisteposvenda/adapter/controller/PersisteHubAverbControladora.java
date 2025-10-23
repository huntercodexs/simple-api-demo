package br.com.bradesco.cnsg.persisteposvenda.adapter.controller;

import br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.entrada.*;
import br.com.bradesco.cnsg.persisteposvenda.adapter.database.dto.ConteudoRespostaTransacaoDTO;
import br.com.bradesco.cnsg.persisteposvenda.adapter.database.dto.HistoricoStatusDTO;
import br.com.bradesco.cnsg.persisteposvenda.adapter.database.dto.VrEntradaDTO;
import br.com.bradesco.cnsg.persisteposvenda.application.usecase.HubAverbCasoUso;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Tag(name = "Ecossistema de Averbadoras", description = "Operações de persistência e consulta de contratos no Ecossistema de Averbadoras")
@RestController
@RequestMapping("/cnsg-posvenda/v1/hub")
public class PersisteHubAverbControladora {

    private static final Logger log = LoggerFactory.getLogger(PersisteHubAverbControladora.class);

    private final HubAverbCasoUso hubAverbCasoUso;

    public PersisteHubAverbControladora(HubAverbCasoUso hubAverbCasoUso) {
        this.hubAverbCasoUso = hubAverbCasoUso;
    }

    private void logRequest(Map<String, String> cabecalho, Object body, String url) {
        MDC.put("rota", url);
        MDC.put("correlation_id", cabecalho.getOrDefault("correlation_id", UUID.randomUUID().toString()));
        log.info("Requisicao recebida", kv("corpo", body));
    }

    @Operation(summary = "Incluir transação na base de Ecossistema de Averbadoras", description = "Inclui uma nova transação de controle na base do Ecossistema de Averbadoras.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro criado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Registro já existe", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/incluir-transacao")
    public ResponseEntity<?> incluirTransacaoNaBase(@RequestHeader Map<String, String> cabecalho,
                                                    @RequestBody @Valid IncluirTransacaoHubAverbDTO incluirTransacaoHubAverb) {

        logRequest(cabecalho, incluirTransacaoHubAverb, "Post/hub/incluir-transacao");

        hubAverbCasoUso.incluirTransacao(incluirTransacaoHubAverb);

        log.info("Requisicao processada com sucesso");

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @Operation(summary = "Consultar transação de controle", description = "Consulta uma transação de controle pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado", content = @Content(schema = @Schema(implementation = Object.class))),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/obter-transacao/{id_transacao}")
    public ResponseEntity<?> consultarRegistroNaBase(@RequestHeader Map<String, String> cabecalho,
                                                                     @PathVariable("id_transacao") String numeroTransacao) {

        logRequest(cabecalho, numeroTransacao, "GET/hub/obter-transacao/{id_transacao}");

        final var response = hubAverbCasoUso.consultaTransacaoControle(numeroTransacao);

        log.info("Requisicao processada com sucesso");

        return ResponseEntity.status(200).body(response);

    }

    @Operation(summary = "Consultar transação de controle", description = "Consulta uma transação de controle pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado", content = @Content(schema = @Schema(implementation = Object.class))),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/consultar-retorno-hub/{cd_protocolo}")
    public ResponseEntity<?> consultarRetornoHub(@RequestHeader Map<String, String> cabecalho,
                                                     @PathVariable("cd_protocolo") String codProtocolo) {

        logRequest(cabecalho, codProtocolo, "GET/hub/consultar-retorno-hub/{id_transacao}");

        final var response = hubAverbCasoUso.consultaRetornoHub(codProtocolo);

        log.info("Requisicao processada com sucesso");

        return ResponseEntity.status(200).body(response);

    }

    @Operation(summary = "Incluir id de transação na base de Ecossistema de Averbadoras", description = "Inclui um novo id de transação de controle na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro criado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Registro já existe", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/registrar-retorno/{cd_protocolo}")
    public ResponseEntity<?> registrarRetornoSolicitacaoDesaverb(@RequestHeader Map<String, String> cabecalho,
                                                    @RequestBody @Valid ConteudoRespostaTransacaoDTO conteudoRespostaTransacao,
                                                    @PathVariable("cd_protocolo") String codProtocolo) {

        logRequest(cabecalho, conteudoRespostaTransacao, "Post/hub/registrar-retorno");

        hubAverbCasoUso.registraRetornoSolicitacaoDesaverb(codProtocolo, conteudoRespostaTransacao);

        log.info("Requisicao processada com sucesso");

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @Operation(summary = "Atualizar quantidade de tentativas transação", description = "Atualiza quantidade de tentativas de uma transação existente na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PatchMapping("/atualizar-retent-transacao/{id_transacao}")
    public ResponseEntity<?> atualizaTentTransacao(@RequestHeader Map<String, String> cabecalho,
                                                           @PathVariable("id_transacao") String numeroTransacao) {
        logRequest(cabecalho, numeroTransacao, "Patch/hub/atualizar-tent-transacao/{id_transacao}");

           hubAverbCasoUso.subtraiTentTransacao(numeroTransacao);

        log.info("Requisicao processada com sucesso");

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Rgistra o status da transação na base de Ecossistema de Averbadoras", description = "Registra o status da transação de controle na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro criado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Registro já existe", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/registrar-status-transacao-hub-averb/{id_transacao}")
    public ResponseEntity<?> registrarStatusTransacaoHub(@RequestHeader Map<String, String> cabecalho,
                                                    @RequestBody @Valid HistoricoStatusDTO historicoStatus,
                                                    @PathVariable("id_transacao") String transacao    ) {

        logRequest(cabecalho, historicoStatus, "Post/hub/registrar-retorno");

            hubAverbCasoUso.registraStatusTransacaoHub(transacao,historicoStatus);

        log.info("Requisicao processada com sucesso");

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}
