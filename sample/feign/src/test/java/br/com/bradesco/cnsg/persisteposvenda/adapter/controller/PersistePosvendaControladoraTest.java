//package br.com.bradesco.cnsg.persisteposvenda.adapter.controller;
//
//import br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.entrada.AtualizacoesPosVendaDTO;
//import br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.entrada.ClienteDTO;
//import br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.entrada.InclusoesPosVendaDTO;
//import br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.entrada.ProdutoDTO;
//import br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.saida.ConsultasPosVendaDTO;
//import br.com.bradesco.cnsg.persisteposvenda.application.usecase.BasePosVendaCasoUso;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//
//import java.time.LocalDate;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class PersistePosvendaControladoraTest {
//
//    @Mock
//    private BasePosVendaCasoUso basePosVendaCasoUso;
//
//    @InjectMocks
//    private PersistePosvendaControladora controladora;
//
//    private Map<String, String> cabecalho;
//    private InclusoesPosVendaDTO inclusoesPosVendaDTO;
//    private AtualizacoesPosVendaDTO atualizacoesPosVendaDTO;
//    private String numeroContrato;
//    private ConsultasPosVendaDTO consultasPosVendaDTO;
//
//    @BeforeEach
//    void setUp() {
//        cabecalho = new HashMap<>();
//        cabecalho.put("correlation_id", UUID.randomUUID().toString());
//
//        ClienteDTO clienteDTO = new ClienteDTO(
//                "12345678900",
//                "MAT123456",
//                "CONV123");
//
//        ProdutoDTO produtoDTO = new ProdutoDTO("1001", "123");
//
//        inclusoesPosVendaDTO = new InclusoesPosVendaDTO(
//                "1234567890",
//                clienteDTO,
//                produtoDTO,
//                "12345678000199",
//                LocalDate.now(),
//                "ENC001",
//                "PENDENTE"
//        );
//
//        atualizacoesPosVendaDTO = new AtualizacoesPosVendaDTO(
//                "PROCESSADO"
//        );
//
//        numeroContrato = "1234567890";
//        consultasPosVendaDTO = new ConsultasPosVendaDTO("PROCESSADO");
//    }
//
//    @Test
//    void incluirRegistroNaBase_DeveRetornarCreated_QuandoOperacaoForBemSucedida() {
//        doNothing().when(basePosVendaCasoUso).incluirRegistro(any(InclusoesPosVendaDTO.class));
//
//        var response = controladora.incluirRegistroNaBase(inclusoesPosVendaDTO);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        verify(basePosVendaCasoUso, times(1)).incluirRegistro(inclusoesPosVendaDTO);
//    }
//
//    @Test
//    void consultarRegistroNaBase_DeveRetornarOk_QuandoRegistroExistir() {
//        when(basePosVendaCasoUso.consultarRegistro(numeroContrato)).thenReturn(consultasPosVendaDTO);
//
//        var response = controladora.consultarRegistroNaBase(cabecalho, numeroContrato);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(consultasPosVendaDTO, response.getBody());
//        verify(basePosVendaCasoUso, times(1)).consultarRegistro(numeroContrato);
//    }
//
//    @Test
//    void logRequest_DeveGerarCorrelationId_QuandoNaoForFornecido() {
//        Map<String, String> cabecalhoSemCorrelationId = new HashMap<>();
//
//        var response = controladora.incluirRegistroNaBase(cabecalhoSemCorrelationId, inclusoesPosVendaDTO);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//    }
//
//    @Test
//    void atualizarRegistroNaBase_DeveRetornarOk_QuandoAtualizacaoForBemSucedida() {
//        doNothing().when(basePosVendaCasoUso).atualizarRegistro(numeroContrato, atualizacoesPosVendaDTO);
//
//        var response = controladora.atualizarRegistroNaBase(cabecalho, numeroContrato, atualizacoesPosVendaDTO);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        verify(basePosVendaCasoUso, times(1)).atualizarRegistro(numeroContrato, atualizacoesPosVendaDTO);
//    }
//
//    @Test
//    void atualizarRegistroNaBase_DevePropagarExcecao_QuandoRegistroNaoExistir() {
//        doThrow(new IllegalArgumentException("Registro não encontrado"))
//            .when(basePosVendaCasoUso).atualizarRegistro(numeroContrato, atualizacoesPosVendaDTO);
//
//        try {
//            controladora.atualizarRegistroNaBase(cabecalho, numeroContrato, atualizacoesPosVendaDTO);
//        } catch (IllegalArgumentException e) {
//            assertEquals("Registro não encontrado", e.getMessage());
//        }
//        verify(basePosVendaCasoUso, times(1)).atualizarRegistro(numeroContrato, atualizacoesPosVendaDTO);
//    }
//}
