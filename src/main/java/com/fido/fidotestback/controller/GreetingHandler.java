package com.fido.fidotestback.controller;


import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class GreetingHandler {

    public Mono<ServerResponse> index(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .render("index.html");
    }
}
