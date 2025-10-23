package br.com.bradesco.cnsg.persisteposvenda.adapter.controller.exception;

import br.com.bradesco.cnsg.persisteposvenda.adapter.controller.exception.dto.ErroDTO;
import br.com.bradesco.cnsg.persisteposvenda.application.domain.exception.PosVendaException;
import io.swagger.v3.oas.annotations.Hidden;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

/**
 * Handler global de exceções da aplicação
 * Responsável por interceptar exceções e retornar respostas padronizadas
 */
@Hidden
@RestControllerAdvice
public class TratamentoGlobalExcecao {

    private static final Logger log = LoggerFactory.getLogger(TratamentoGlobalExcecao.class);
    private static final String ERRO_GENERICO_CODIGO = "5001143";
    private static final String ERRO_GENERICO_MENSAGEM = "ERRO INTERNO NO SERVICO";
    private static final String ERRO_VALIDACAO_CODIGO = "4000623";

    /**
     * Handler para PosVendaException - exceção customizada com códigos de erro
     */
    @ExceptionHandler(PosVendaException.class)
    public ResponseEntity<ErroDTO> handlePosVendaException(PosVendaException ex) {

        log.error("Erro de pós-venda: código={}, mensagem={}", ex.getCodigo(), ex.getMensagem(), ex);

        int codigoStatus = Optional.ofNullable(ex.getCodigo())
                .filter(codigo -> codigo.length() >= 3)
                .map(codigo -> {
                    try {
                        return Integer.parseInt(codigo.substring(0, 3));
                    } catch (NumberFormatException e) {
                        return 500;
                    }
                })
                .orElse(500);

        ErroDTO errorResponse = new ErroDTO(
            ex.getCodigo(),
            ex.getMensagem()
        );

        return ResponseEntity.status(codigoStatus).body(errorResponse);
    }

    /**
     * Handler para erros de validação de argumentos do método
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("Erro de validação: {}", ex.getMessage(), ex);

        String mensagemErro = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .reduce((msg1, msg2) -> msg1 + "; " + msg2)
                .orElse("Erro de validação nos campos da requisição");

        ErroDTO errorResponse = new ErroDTO(
            ERRO_VALIDACAO_CODIGO,
            mensagemErro
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Handler genérico para exceções não tratadas
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroDTO> handleGenericException(Exception ex) {
        log.error("Erro não tratado: {}", ex.getMessage(), ex);

        ErroDTO errorResponse = new ErroDTO(
            ERRO_GENERICO_CODIGO,
            ERRO_GENERICO_MENSAGEM
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
