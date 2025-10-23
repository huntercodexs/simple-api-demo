package br.com.bradesco.cnsg.persisteposvenda;

import br.com.bradesco.enge.logcloud.spring.EnableLogCloud;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import static net.logstash.logback.argument.StructuredArguments.v;

@EnableLogCloud
@SpringBootApplication
@EnableFeignClients("br.com.bradesco")
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {

        final var run = SpringApplication.run(Application.class, args);

        log.info("Aplicação iniciada com sucesso {}", v("porta", run.getEnvironment().getProperty("server.port")));

    }
}
