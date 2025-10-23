package br.com.bradesco.cnsg.persisteposvenda.application.domain.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * Exceção customizada para erros de negócio relacionados ao Pós-Venda
 * Permite especificar apenas o código do erro, enquanto a mensagem
 * é recuperada automaticamente do arquivo de mensagens
 */
public class PosVendaException extends RuntimeException {

    private static final String ERRO_INTERNO = "5001143";
    private static final long serialVersionUID = 1L;
    private static MessageSource messageSource;
    private final String codigo;
    private final String mensagem;

    /**
     * Construtor que recebe apenas o código do erro
     * A mensagem será buscada do arquivo de propriedades usando o código
     *
     * @param codigo código do erro
     */
    public PosVendaException(String codigo) {
        super(getMessageFromProperties(codigo));
        this.codigo = codigo;
        this.mensagem = getMessageFromProperties(codigo);
    }

    /**
     * Construtor que recebe o código do erro e a causa raiz
     *
     * @param codigo código do erro
     * @param cause  causa raiz da exceção
     */
    public PosVendaException(String codigo, Throwable cause) {
        super(getMessageFromProperties(codigo), cause);
        this.codigo = codigo;
        this.mensagem = getMessageFromProperties(codigo);
    }

    /**
     * Método para configurar o MessageSource usado para recuperar mensagens
     * Este método deve ser chamado durante a inicialização da aplicação
     *
     * @param msgSource o MessageSource do Spring
     */
    public static void setMessageSource(MessageSource msgSource) {
        messageSource = msgSource;
    }

    /**
     * Recupera a mensagem do arquivo de propriedades com base no código
     *
     * @param codigo código do erro
     * @return mensagem de erro correspondente ou uma mensagem padrão se o código não for encontrado
     */
    private static String getMessageFromProperties(String codigo) {
        if (messageSource == null) {
            return ERRO_INTERNO;
        }

        try {
            return messageSource.getMessage(codigo, null, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return ERRO_INTERNO;
        }
    }

    /**
     * @return o código de erro
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @return a mensagem de erro
     */
    public String getMensagem() {
        return mensagem;
    }
}