package br.com.bradesco.cnsg.persisteposvenda.application.config.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.util.ObjectUtils.isEmpty;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    private static final String ORGANIZATIONAL_API = "organizational";
    private static final String USER_IDENTITY_API = "user-identity";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String APPLICATION_JSON = "application/json";

    @Override
    public void apply(RequestTemplate requestTemplate) {

        if (Objects.equals(ORGANIZATIONAL_API, requestTemplate.feignTarget().name()) ||
                Objects.equals(USER_IDENTITY_API, requestTemplate.feignTarget().name())) {
            clientTokenInterception(requestTemplate);
        }

        if (isEmpty(requestTemplate.body()) && isNotAGetRequest(requestTemplate.method())) {
            requestTemplate.body("{}");
        }
    }

    private boolean isNotAGetRequest(String method) {
        return !method.equalsIgnoreCase(GET.toString());
    }

    private void clientTokenInterception(RequestTemplate requestTemplate) {
        requestTemplate.header(CONTENT_TYPE_HEADER, APPLICATION_JSON);
        // Get the token from the security context or any other source
        // For demonstration, we'll use a hardcoded token
        // In a real application, replace this with actual token retrieval logic
        String jwtToken = "Bearer token_value";
        System.out.println("BEARER TOKEN");
        requestTemplate.header(AUTHORIZATION_HEADER, jwtToken);
    }
}
