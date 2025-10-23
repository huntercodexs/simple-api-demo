package br.com.bradesco.cnsg.persisteposvenda.adapter.database.entity;

import br.com.bradesco.cnsg.persisteposvenda.adapter.controller.dto.entrada.InclusoesPosVendaDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "tBaseExclAverbDprev")
public class TabelaMongoDbEntidade {

    @Id
    private String id;

    @Field("nContrCli")
    @Indexed(unique = true)
    private String numeroContrato;

    @Field("nCpfCli")
    private String cpfCliente;

    @Field("cMatrCli")
    private String matriculaCliente;

    @Field("nConve")
    private String numeroConvenio;

    @Field("cProdt")
    private String codigoProduto;

    @Field("cFamlProdt")
    private String codigoFamiliaProduto;

    @Field("nCnpjEmprg")
    private String cnpjEmpregador;

    @Field("dCeleb")
    private LocalDate dataCelebracao;

    @Field("cTpoEncrr")
    private String codigoTipoEncerramento;

    @Field("cSitReg")
    private String situacaoRegistro;

    @Field("dProcmReg")
    @Indexed(name = "dProcmReg_1", expireAfter = "90d") // expira 90 dias
    private LocalDateTime ttl = LocalDateTime.now();

    public LocalDateTime getTtl() {
        return ttl;
    }

    public void setTtl(LocalDateTime ttl) {
        this.ttl = ttl;
    }

    public String getSituacaoRegistro() {
        return situacaoRegistro;
    }

    public void setSituacaoRegistro(String situacaoRegistro) {
        this.situacaoRegistro = situacaoRegistro;
    }

    public String getCodigoTipoEncerramento() {
        return codigoTipoEncerramento;
    }

    public void setCodigoTipoEncerramento(String codigoTipoEncerramento) {
        this.codigoTipoEncerramento = codigoTipoEncerramento;
    }

    public LocalDate getDataCelebracao() {
        return dataCelebracao;
    }

    public void setDataCelebracao(LocalDate dataCelebracao) {
        this.dataCelebracao = dataCelebracao;
    }

    public String getCnpjEmpregador() {
        return cnpjEmpregador;
    }

    public void setCnpjEmpregador(String cnpjEmpregador) {
        this.cnpjEmpregador = cnpjEmpregador;
    }

    public String getCodigoFamiliaProduto() {
        return codigoFamiliaProduto;
    }

    public void setCodigoFamiliaProduto(String codigoFamiliaProduto) {
        this.codigoFamiliaProduto = codigoFamiliaProduto;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getNumeroConvenio() {
        return numeroConvenio;
    }

    public void setNumeroConvenio(String numeroConvenio) {
        this.numeroConvenio = numeroConvenio;
    }

    public String getMatriculaCliente() {
        return matriculaCliente;
    }

    public void setMatriculaCliente(String matriculaCliente) {
        this.matriculaCliente = matriculaCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TabelaMongoDbEntidade(InclusoesPosVendaDTO inclusoesPosVendaDTO) {
        this.numeroContrato = inclusoesPosVendaDTO.numeroContrato();
        this.cpfCliente = inclusoesPosVendaDTO.cliente().cpfCliente();
        this.matriculaCliente = inclusoesPosVendaDTO.cliente().matriculaCliente();
        this.numeroConvenio = inclusoesPosVendaDTO.cliente().numeroConvenio();
        this.codigoProduto = inclusoesPosVendaDTO.produto().codigoProduto();
        this.codigoFamiliaProduto = inclusoesPosVendaDTO.produto().codigoFamiliaProduto();
        this.cnpjEmpregador = inclusoesPosVendaDTO.cnpjEmpregador();
        this.dataCelebracao = inclusoesPosVendaDTO.dataCelebracao();
        this.codigoTipoEncerramento = inclusoesPosVendaDTO.codigoTipoEncerramento();
        this.situacaoRegistro = inclusoesPosVendaDTO.situacaoRegistro();
    }

    public TabelaMongoDbEntidade() {
        // Construtor padrão necessário para o Spring Data MongoDB
    }

}
