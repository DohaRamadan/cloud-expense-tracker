package com.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements GatewayFilterFactory<AuthenticationFilter.Config> {

    private static final String USER_SERVICE_URL = "http://user-service:8001";

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String path = exchange.getRequest().getURI().getPath();

            if (path.startsWith("/api/users/register") || path.startsWith("/api/users/login")) {
                return chain.filter(exchange);
            }

            String authToken = exchange.getRequest().getHeaders().getFirst("Authorization");

            if (authToken != null && authToken.startsWith("Bearer ")) {
                String jwtToken = authToken.substring(7);

                return webClientBuilder.build()
                                       .get()
                                       .uri(USER_SERVICE_URL + "/api/users/get-username")
                                       .header(HttpHeaders.AUTHORIZATION, authToken)
                                       .retrieve()
                                       .bodyToMono(String.class)
                                       .flatMap(username -> {
                                           if (username != null && !username.isEmpty()) {
                                               ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
                                                                                           .header("X-Auth-Username", username)
                                                                                           .build();
                                               return chain.filter(exchange.mutate().request(modifiedRequest).build());
                                           } else {
                                               exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                                               return Mono.empty();
                                           }
                                       })
                                       .onErrorResume(ex -> {
                                           // Handle errors, set appropriate status code
                                           exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                                           return Mono.empty();
                                       });
            }

            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return Mono.empty();
        };
    }

    @Override
    public Config newConfig() {
        return new Config();
    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }


    public static class Config {

    }
}
