package br.com.bradesco.cnsg.persisteposvenda.application.config;

import br.com.bradesco.cnsg.persisteposvenda.application.domain.exception.PosVendaException;
import jakarta.annotation.PostConstruct;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração para inicializar o MessageSource na classe PosVendaException
 */
@Configuration
public class MensagensSpringConfiguracao {

    private final MessageSource messageSource;

    public MensagensSpringConfiguracao (MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Configura o MessageSource para a classe PosVendaException após a inicialização do Spring
     */
    @PostConstruct
    public void initializeMessageSource() {
        PosVendaException.setMessageSource(messageSource);
    }
}
