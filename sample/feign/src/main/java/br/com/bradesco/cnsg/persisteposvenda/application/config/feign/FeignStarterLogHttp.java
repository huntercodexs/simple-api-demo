package br.com.bradesco.cnsg.persisteposvenda.application.config.feign;

import feign.Logger;
import feign.Request;
import feign.Response;
import feign.Util;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class FeignStarterLogHttp extends Logger {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(FeignStarterLogHttp.class);

    @Override
    protected void log(String s, String s1, Object... objects) {
        //nao usar
    }

    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {

        Map<String, String> cabecalhos = request.headers()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> String.join(",", entry.getValue())
                ));

        final var corpo = request.body() != null ? new String(request.body()) : "";

        final var metodo = request.httpMethod().name();

        final var url = request.url();

        log.info("Requisicao enviada - Metodo: {} | URL: {} | cabecalho: {} | corpo: {}", metodo, url, cabecalhos, corpo);
    }

    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime) {

        Map<String, String> cabecalhos = response.headers()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> String.join(",", entry.getValue())
                ));

        final var status = response.status();

        String corpo = "";
        try {
            if (response.body() != null) {
                byte[] bodyData = Util.toByteArray(response.body().asInputStream());
                corpo = new String(bodyData);
            }
        } catch (Exception e) {
            log.warn("Erro ao ler o corpo da resposta: {}", e.getMessage());
        }

        log.info("Requisicao recebida - Status: {} | Tempo de Resposta: {}ms | cabecalho: {} | corpo: {}", status, elapsedTime, cabecalhos, corpo);

        return response;
    }
}
