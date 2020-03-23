package com.example.WeatherApi.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerErrorException;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class WeatherApi {

    private UrlBuilder urlBuilder;

    @Autowired
    public WeatherApi(UrlBuilder urlBuilder) {
        this.urlBuilder = urlBuilder;
    }

    public Mono<ServerResponse> getNow(String date, String time, String x, String y) {
        try {
            URL url = new URL(urlBuilder.getNowUrl(date, time, x, y));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                }

                rd.close();
                conn.disconnect();
                return ServerResponse.ok().body(Mono.just(sb.toString()), String.class);

            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                }

                rd.close();
                conn.disconnect();
                return ServerResponse.status(conn.getResponseCode()).body(Mono.just(conn.getResponseMessage()), String.class);
            }
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage(), e);
        }
    }

}
