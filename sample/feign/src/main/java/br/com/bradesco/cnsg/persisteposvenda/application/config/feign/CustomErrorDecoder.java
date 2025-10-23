package br.com.bradesco.cnsg.persisteposvenda.application.config.feign;

import feign.Response;
import feign.codec.ErrorDecoder;

import java.net.HttpRetryException;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        return new HttpRetryException("Error occurred with status code: " + response.status(), response.status());
    }

}
