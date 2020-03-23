package com.example.WeatherApi.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WeatherNow {
    private double temperature;
    private double rainAmount;
    private double humidity;
    private String rainType;

    public void setTemperature(String temperature) {
        this.temperature = Double.parseDouble(temperature);
    }

    public void setRainType(String rainType) {
        this.rainType = rainType;
    }

    public void setRainAmount(String rainAmount) {
        this.rainAmount = Double.parseDouble(rainAmount);
    }

    public void setHumidity(String humidity) {
        this.humidity = Double.parseDouble(humidity);
    }
}
