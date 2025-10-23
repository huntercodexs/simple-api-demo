package br.com.bradesco.cnsg.persisteposvenda.application.usecase;

import br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.entrada.AtualizacoesPosVendaDTO;
import br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.entrada.ClienteDTO;
import br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.saida.ConsultasPosVendaDTO;
import br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.entrada.InclusoesPosVendaDTO;
import br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.entrada.ProdutoDTO;
import br.com.bradesco.cnsg.persisteposvenda.adapter.database.entity.TabelaMongoDbEntidade;
import br.com.bradesco.cnsg.persisteposvenda.adapter.database.repository.secondary.BasePosVendaRepositorio;
import br.com.bradesco.cnsg.persisteposvenda.application.domain.exception.PosVendaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BasePosVendaCasoUsoTest {

    @Mock
    private BasePosVendaRepositorio repositorio;

    @InjectMocks
    private BasePosVendaCasoUso casoUso;

    private InclusoesPosVendaDTO inclusoesPosVendaDTO;
    private AtualizacoesPosVendaDTO atualizacoesPosVendaDTO;
    private String numeroContrato;
    private TabelaMongoDbEntidade entidadeExistente;

    @BeforeEach
    void setUp() {
        // Setup DTO de inclusão
        ClienteDTO clienteDTO = new ClienteDTO(
                "12345678900",
                "MAT123456",
                "123");

        ProdutoDTO produtoDTO = new ProdutoDTO("1001", "123");

        inclusoesPosVendaDTO = new InclusoesPosVendaDTO(
                "1234567890",
                clienteDTO,
                produtoDTO,
                "12345678000199",
                LocalDate.now(),
                "ENC001",
                "PENDENTE"
        );

        // Setup DTO de atualização
        atualizacoesPosVendaDTO = new AtualizacoesPosVendaDTO(
                "PROCESSADO");

        // Setup número de contrato para consulta
        numeroContrato = "1234567890";

        // Setup entidade existente para testes de atualização e consulta
        entidadeExistente = new TabelaMongoDbEntidade(inclusoesPosVendaDTO);
        entidadeExistente.setSituacaoRegistro("PENDENTE");
    }

    @Test
    void incluirRegistro_DeveSalvarEntidade_QuandoDadosForemValidos() {
        // Arrange
        ArgumentCaptor<TabelaMongoDbEntidade> entidadeCaptor = ArgumentCaptor.forClass(TabelaMongoDbEntidade.class);
        when(repositorio.save(any(TabelaMongoDbEntidade.class))).thenReturn(new TabelaMongoDbEntidade(inclusoesPosVendaDTO));

        // Act
        casoUso.incluirRegistro(inclusoesPosVendaDTO);

        // Assert
        verify(repositorio, times(1)).save(entidadeCaptor.capture());

        TabelaMongoDbEntidade entidadeSalva = entidadeCaptor.getValue();
        assertEquals(inclusoesPosVendaDTO.numeroContrato(), entidadeSalva.getNumeroContrato().toString());
      //  assertEquals(inclusoesPosVendaDTO.cliente().cpfCliente(), entidadeSalva.getCpfCliente());
        assertEquals(inclusoesPosVendaDTO.cliente().matriculaCliente(), entidadeSalva.getMatriculaCliente());
        //assertEquals(inclusoesPosVendaDTO.cliente().numeroConvenio(), entidadeSalva.getNumeroConvenio());
        assertEquals(inclusoesPosVendaDTO.produto().codigoProduto(), entidadeSalva.getCodigoProduto().toString());
        assertEquals(inclusoesPosVendaDTO.produto().codigoFamiliaProduto(), entidadeSalva.getCodigoFamiliaProduto().toString());
        assertEquals(inclusoesPosVendaDTO.cnpjEmpregador(), entidadeSalva.getCnpjEmpregador());
        assertEquals(inclusoesPosVendaDTO.dataCelebracao(), entidadeSalva.getDataCelebracao());
        assertEquals(inclusoesPosVendaDTO.codigoTipoEncerramento(), entidadeSalva.getCodigoTipoEncerramento());
    }

    @Test
    void atualizarRegistro_DeveAtualizarEntidade_QuandoRegistroExistir() {
        // Arrange
        when(repositorio.findByNumeroContrato(numeroContrato))
                .thenReturn(Optional.of(entidadeExistente));
        ArgumentCaptor<TabelaMongoDbEntidade> entidadeCaptor = ArgumentCaptor.forClass(TabelaMongoDbEntidade.class);
        when(repositorio.save(any(TabelaMongoDbEntidade.class))).thenReturn(entidadeExistente);

        // Act
        assertDoesNotThrow(() -> casoUso.atualizarRegistro(numeroContrato, atualizacoesPosVendaDTO));

        // Assert
        verify(repositorio, times(1)).findByNumeroContrato(numeroContrato);
        verify(repositorio, times(1)).save(entidadeCaptor.capture());
        TabelaMongoDbEntidade entidadeAtualizada = entidadeCaptor.getValue();
        assertEquals(atualizacoesPosVendaDTO.situacaoRegistro(), entidadeAtualizada.getSituacaoRegistro());
    }

    @Test
    void atualizarRegistro_DeveLancarExcecao_QuandoRegistroNaoExistir() {
        // Arrange
        when(repositorio.findByNumeroContrato(numeroContrato))
                .thenReturn(Optional.empty());

        // Act & Assert
        PosVendaException exception = assertThrows(PosVendaException.class, () -> casoUso.atualizarRegistro(numeroContrato, atualizacoesPosVendaDTO));
        assertEquals("4040312", exception.getCodigo());
        verify(repositorio, times(1)).findByNumeroContrato(numeroContrato);
        verify(repositorio, never()).save(any());
    }

    @Test
    void consultarRegistro_DeveRetornarDTO_QuandoRegistroExistir() {
        // Arrange
        when(repositorio.findByNumeroContrato(numeroContrato))
                .thenReturn(Optional.of(entidadeExistente));

        // Act
        assertDoesNotThrow(() -> {
            ConsultasPosVendaDTO resultado = casoUso.consultarRegistro(numeroContrato);
            assertNotNull(resultado);
            assertEquals(entidadeExistente.getSituacaoRegistro(), resultado.situacaoRegistro());
        });
        verify(repositorio, times(1)).findByNumeroContrato(numeroContrato);
    }

    @Test
    void consultarRegistro_DeveLancarExcecao_QuandoRegistroNaoExistir() {
        // Arrange
        when(repositorio.findByNumeroContrato(numeroContrato))
                .thenReturn(Optional.empty());

        // Act & Assert
        PosVendaException exception = assertThrows(PosVendaException.class, () -> casoUso.consultarRegistro(numeroContrato));
        assertEquals("4040312", exception.getCodigo());
        verify(repositorio, times(1)).findByNumeroContrato(numeroContrato);
    }

    @Test
    void incluirRegistro_DeveLancarPosVendaException_4001100_QuandoChaveDuplicadaMongo() {
        doThrow(new RuntimeException("E11000 duplicate key error collection: test.collection"))
                .when(repositorio).save(any(TabelaMongoDbEntidade.class));

        PosVendaException exception = assertThrows(PosVendaException.class, () -> {
            casoUso.incluirRegistro(inclusoesPosVendaDTO);
        });

        assertEquals("4001100", exception.getCodigo());
    }

    @Test
    void incluirRegistro_DeveLancarPosVendaException_5001143_QuandoErroGenerico() {
        // Arrange
        doThrow(new RuntimeException("Erro inesperado no MongoDB"))
                .when(repositorio).save(any(TabelaMongoDbEntidade.class));

        // Act & Assert
        PosVendaException exception = assertThrows(PosVendaException.class, () -> {
            casoUso.incluirRegistro(inclusoesPosVendaDTO);
        });
        assertEquals("5001143", exception.getMessage());
    }
}
