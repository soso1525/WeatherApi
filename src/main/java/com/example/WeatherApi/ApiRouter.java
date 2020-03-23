package com.example.WeatherApi;

import com.example.WeatherApi.handler.WeatherHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
public class ApiRouter {
    @Bean
    public RouterFunction<ServerResponse> router(WeatherHandler weatherHandler) {
        return RouterFunctions.route(POST("/now"), weatherHandler::now);
    }

}