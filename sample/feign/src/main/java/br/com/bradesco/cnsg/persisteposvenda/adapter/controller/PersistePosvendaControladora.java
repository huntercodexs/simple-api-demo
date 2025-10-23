package br.com.bradesco.cnsg.persisteposvenda.adapter.controller;

import br.com.bradesco.cnsg.integration.user_identity.api.BusinessGroupApiClient;
import br.com.bradesco.cnsg.integration.user_identity.model.AttachBusinessGroupRequest;
import br.com.bradesco.cnsg.integration.user_identity.model.BusinessAccountsRequest;
import br.com.bradesco.cnsg.integration.user_identity.model.ControllerUserRequest;
import br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.entrada.AtualizacoesPosVendaDTO;
import br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.entrada.InclusoesPosVendaDTO;
import br.com.bradesco.cnsg.persisteposvenda.api.ContratosApi;
import br.com.bradesco.cnsg.persisteposvenda.api.model.CnsgPosVendaAtualizacaoRequisicao;
import br.com.bradesco.cnsg.persisteposvenda.api.model.CnsgPosVendaContratoPorIdResposta;
import br.com.bradesco.cnsg.persisteposvenda.api.model.CnsgPosVendaIncluirRegistroRequisicao;
import br.com.bradesco.cnsg.persisteposvenda.api.model.CnsgPosVendaIncluirRegistroResposta;
import br.com.bradesco.cnsg.persisteposvenda.application.usecase.BasePosVendaCasoUso;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static br.com.bradesco.cnsg.persisteposvenda.application.util.ParserUtil.convertValue;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
public class PersistePosvendaControladora implements BaseController, ContratosApi {

    private static final Logger log = LoggerFactory.getLogger(PersistePosvendaControladora.class);

    private final BasePosVendaCasoUso basePosVendaCasoUso;
    //private final ServerHttpRequest httpServletRequest;
    private final BusinessGroupApiClient integrationTest;

    @CircuitBreaker(name = "externalApi", fallbackMethod = "fallbackTestCircuitBreaker")
    @GetMapping("/test")
    public void test() {
        System.out.println("test start");

        ControllerUserRequest controllerUserRequest = new ControllerUserRequest();
        controllerUserRequest.setType(ControllerUserRequest.TypeEnum.BASIC);
        controllerUserRequest.setUserId("123");

        BusinessAccountsRequest businessAccountsRequest = new BusinessAccountsRequest();
        businessAccountsRequest.setBusinessId("1234");

        try {
            integrationTest.attachBusinessGroup("1234567", new AttachBusinessGroupRequest()
                    .action(AttachBusinessGroupRequest.ActionEnum.ATTACH_BUSINESS_ACCOUNT)
                    .businessAccounts(Set.of(businessAccountsRequest))
                    .controllerUsers(Set.of(controllerUserRequest)));
            System.out.println("fim");
        } catch (CallNotPermittedException ex) {
            System.out.println(ex.getCausingCircuitBreakerName());
        }
    }

    private String fallbackTestCircuitBreaker(Throwable t) {
        return "ESTA ACONTENCENDO ALGUMA COISA NO CIRCUIT BREAKER";
    }

    @PostMapping("/business-group-identities/business-groups/{id}/attach")
    public void test2(@PathVariable("id") String id) {
        System.out.println("test2 start");
        System.out.println("fim");
        throw new RuntimeException("Test");
    }

    @Override
    public ResponseEntity<CnsgPosVendaIncluirRegistroResposta> incluirRegistroNaBase(
            @Valid @RequestBody CnsgPosVendaIncluirRegistroRequisicao cnsgPosVendaIncluirRegistroRequisicao
    ) {
        logRequest(cnsgPosVendaIncluirRegistroRequisicao, "POST /contratos");
        var inclusoesPosVendaDTO = convertValue(cnsgPosVendaIncluirRegistroRequisicao, InclusoesPosVendaDTO.class);
        basePosVendaCasoUso.incluirRegistro(inclusoesPosVendaDTO);
        return new ResponseEntity<>(new CnsgPosVendaIncluirRegistroResposta(), CREATED);
    }

    @Override
    public ResponseEntity<CnsgPosVendaContratoPorIdResposta> consultarRegistroNaBase(
            @PathVariable("idContrato") String idContrato
    ) {
        logRequest(idContrato, "GET /contratos/{id_contrato}");
        final var response = basePosVendaCasoUso.consultarRegistro(idContrato);
        return new ResponseEntity<>(convertValue(response, CnsgPosVendaContratoPorIdResposta.class), OK);
    }

    @Override
    public ResponseEntity<Void> atualizarRegistroNaBase(
            @PathVariable("idContrato") String idContrato,
            CnsgPosVendaAtualizacaoRequisicao cnsgPosVendaAtualizacaoRequisicao
    ) {
        logRequest(cnsgPosVendaAtualizacaoRequisicao, "PATCH /contratos/{id_contrato}");
        var atualizacoesPosVendaDTO = convertValue(cnsgPosVendaAtualizacaoRequisicao, AtualizacoesPosVendaDTO.class);
        basePosVendaCasoUso.atualizarRegistro(idContrato, atualizacoesPosVendaDTO);
        return new ResponseEntity<>(NO_CONTENT);
    }

    private void logRequest(Object body, String url) {
//        Map<String, String> cabecalho = new HashMap<>();
//        HttpHeaders headers = httpServletRequest.getHeaders();
//        headers.forEach((name, value) -> {
//            cabecalho.put(name, String.valueOf(value));
//        });
//
//        MDC.put("rota", url);
//        MDC.put("correlation_id", cabecalho.getOrDefault("correlation_id", UUID.randomUUID().toString()));
//        log.info("Requisicao recebida", kv("corpo", body));
    }
}
