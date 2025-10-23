package br.com.bradesco.cnsg.persisteposvenda.adapter.database.entity;

import br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.entrada.ClienteDTO;
import br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.entrada.InclusoesPosVendaDTO;
import br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.entrada.ProdutoDTO;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TabelaMongoDbEntidadeTest {

    @Test
    void construtor_DeveMapearCorretamente_QuandoReceberDTO() {
        // Arrange
        String cpfCliente = "12345678900";
        String matriculaCliente = "MAT123456";
        String numeroConvenio = "123";
        String codigoProduto = "1001";
        String codigoFamiliaProduto = "123";
        String numeroContrato = "1234567890";
        String cnpjEmpregador = "12345678000199";
        LocalDate dataCelebracao = LocalDate.now();
        String codTipoEncerramento = "ENC001";
        String nomeArquivo = "arquivo_teste.txt";
        String situacaoRegistro = "PENDENTE";

        ClienteDTO clienteDTO = new ClienteDTO(cpfCliente, matriculaCliente, numeroConvenio);
        ProdutoDTO produtoDTO = new ProdutoDTO(codigoProduto, codigoFamiliaProduto);

        InclusoesPosVendaDTO dto = new InclusoesPosVendaDTO(
                numeroContrato,
                clienteDTO,
                produtoDTO,
                cnpjEmpregador,
                dataCelebracao,
                codTipoEncerramento,
                situacaoRegistro
        );

        // Act
        TabelaMongoDbEntidade entidade = new TabelaMongoDbEntidade(dto);

        // Assert
        assertNotNull(entidade);
        assertEquals(numeroContrato, entidade.getNumeroContrato());
        assertEquals(cpfCliente, entidade.getCpfCliente());
        assertEquals(matriculaCliente, entidade.getMatriculaCliente());
        assertEquals(numeroConvenio, entidade.getNumeroConvenio());
        assertEquals(codigoProduto, entidade.getCodigoProduto());
        assertEquals(codigoFamiliaProduto, entidade.getCodigoFamiliaProduto());
        assertEquals(cnpjEmpregador, entidade.getCnpjEmpregador());
        assertEquals(dataCelebracao, entidade.getDataCelebracao());
        assertEquals(codTipoEncerramento, entidade.getCodigoTipoEncerramento());
    }

    @Test
    void gettersESetters_DeveFuncionarCorretamente() {
        // Arrange
        TabelaMongoDbEntidade entidade = new TabelaMongoDbEntidade(criarDtoBasico());

        String novoNumeroContrato = "123423451";
        String novoCpf = "98765432100";
        String novaMatricula = "MAT98765";
        String novoConvenio = "987";
        String novoCodigoProduto = "9999";
        String novoCodigoFamilia = "999";
        String novoCnpj = "98765432000199";
        LocalDate novaData = LocalDate.of(2025, 1, 1);
        String novoCodigoEncerramento = "ENC999";
        String novaSituacao = "PROCESSADO";
        LocalDateTime novaDtProcmReg = LocalDateTime.now();

        // Act - Testar todos os setters
        entidade.setNumeroContrato(novoNumeroContrato);
        entidade.setCpfCliente(novoCpf);
        entidade.setMatriculaCliente(novaMatricula);
        entidade.setNumeroConvenio(novoConvenio);
        entidade.setCodigoProduto(novoCodigoProduto);
        entidade.setCodigoFamiliaProduto(novoCodigoFamilia);
        entidade.setCnpjEmpregador(novoCnpj);
        entidade.setDataCelebracao(novaData);
        entidade.setCodigoTipoEncerramento(novoCodigoEncerramento);
        entidade.setSituacaoRegistro(novaSituacao);
        entidade.setTtl(novaDtProcmReg);

        // Assert - Verificar todos os getters
        assertEquals(novoNumeroContrato, entidade.getNumeroContrato());
        assertEquals(novoCpf, entidade.getCpfCliente());
        assertEquals(novaMatricula, entidade.getMatriculaCliente());
        assertEquals(novoConvenio, entidade.getNumeroConvenio());
        assertEquals(novoCodigoProduto, entidade.getCodigoProduto());
        assertEquals(novoCodigoFamilia, entidade.getCodigoFamiliaProduto());
        assertEquals(novoCnpj, entidade.getCnpjEmpregador());
        assertEquals(novaData, entidade.getDataCelebracao());
        assertEquals(novoCodigoEncerramento, entidade.getCodigoTipoEncerramento());
        assertEquals(novaSituacao, entidade.getSituacaoRegistro());
        assertEquals(novaDtProcmReg, entidade.getTtl());
    }

    private InclusoesPosVendaDTO criarDtoBasico() {
        ClienteDTO clienteDTO = new ClienteDTO("12345678900", "MAT123456", "123");
        ProdutoDTO produtoDTO = new ProdutoDTO("1001", "123");
        return new InclusoesPosVendaDTO(
                "1234567890",
                clienteDTO,
                produtoDTO,
                "12345678000199",
                LocalDate.now(),
                "ENC001",
                "PENDENTE"
        );
    }
}
