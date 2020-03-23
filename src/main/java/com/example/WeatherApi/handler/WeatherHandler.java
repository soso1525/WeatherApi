package com.example.WeatherApi.handler;

import com.example.WeatherApi.helper.WeatherApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Slf4j
@Component
public class WeatherHandler {

    @Value("${zone.id}")
    private String zoneId;

    @Autowired WeatherApi weatherApi;

    public Mono<ServerResponse> now(ServerRequest request) {

        return request.bodyToMono(Map.class)
                .flatMap(body -> {
                    double latitude = (Double) body.get("latitude");
                    double longitude = (Double) body.get("longitude");

                    log.info("latitude >> " + latitude);
                    log.info("longitude >> " + longitude);

                    ZonedDateTime seoulDateTime = ZonedDateTime.now(ZoneId.of(zoneId));
                    String date = seoulDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                    String time = String.format("%02d00", seoulDateTime.getHour() - 1);

                    // Wirye
                    String x = "63", y = "125";
                    return weatherApi.getNow(date, time, x, y);
                });
    }
}
