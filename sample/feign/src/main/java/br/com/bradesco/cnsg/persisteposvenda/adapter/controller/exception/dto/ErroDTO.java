package br.com.bradesco.cnsg.persisteposvenda.adapter.controller.exception.dto;

/**
 * DTO para padronização das respostas de erro da API
 */
public record ErroDTO(
    String codigo,
    String mensagem
) {
}
