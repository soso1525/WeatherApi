package com.example.WeatherApi.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Slf4j
@Component
public class UrlBuilder {
    @Value("${api.key}") private String apiKey;
    @Value("${api.url.now}") private String nowUrl;

    @Autowired
    public UrlBuilder() {}

    public String getNowUrl(String date, String time, String x, String y) throws UnsupportedEncodingException {
        return
                new StringBuilder(nowUrl)
                        .append("?").append(URLEncoder.encode("ServiceKey", "UTF-8")).append("=").append(URLEncoder.encode(apiKey, "UTF-8"))
                        .append("&").append(URLEncoder.encode("pageNo", "UTF-8")).append("=").append(URLEncoder.encode("1", "UTF-8"))
                        .append("&").append(URLEncoder.encode("numOfRows", "UTF-8")).append("=").append(URLEncoder.encode("10", "UTF-8"))
                        .append("&").append(URLEncoder.encode("dataType", "UTF-8")).append("=").append(URLEncoder.encode("JSON", "UTF-8"))
                        .append("&").append(URLEncoder.encode("base_date", "UTF-8")).append("=").append(URLEncoder.encode(date, "UTF-8"))
                        .append("&").append(URLEncoder.encode("base_time", "UTF-8")).append("=").append(URLEncoder.encode(time, "UTF-8"))
                        .append("&").append(URLEncoder.encode("nx", "UTF-8")).append("=").append(URLEncoder.encode(x, "UTF-8"))
                        .append("&").append(URLEncoder.encode("ny", "UTF-8")).append("=").append(URLEncoder.encode(y, "UTF-8"))
                        .toString();
    }

}
