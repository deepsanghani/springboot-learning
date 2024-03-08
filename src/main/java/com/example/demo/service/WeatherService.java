package com.example.demo.service;

import com.example.demo.api.response.WeatherApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    private static final String api_key = System.getenv("WEATHER_API_KEY");
    private static final String api = "http://api.weatherapi.com/v1/current.json?key=api_key&q=CITY";
    @Autowired
    private RestTemplate restTemplate;

    public WeatherApiResponse getWeather(String city){
        String finalapi = api.replace("CITY", city).replace("api_key", api_key);
        ResponseEntity<WeatherApiResponse> response = restTemplate.exchange(finalapi, HttpMethod.GET, null, WeatherApiResponse.class);
        WeatherApiResponse body = response.getBody();
        return body;
    }
}
