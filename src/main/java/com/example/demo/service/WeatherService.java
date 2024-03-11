package com.example.demo.service;

import com.example.demo.api.response.WeatherApiResponse;
import com.example.demo.cache.AppCache;
import com.example.demo.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

@Service
public class WeatherService implements Serializable {
    @Value("${weather.api.key}")
    private String api_key;

    @Autowired
    private AppCache appCacheEntity;

    @Autowired
    private RestTemplate restTemplate;

//    @Cacheable(value = "weatherCache", key = "#city")
    public WeatherApiResponse getWeather(String city){
        String finalapi = appCacheEntity.appCache.get(AppCache.keys.weather_api.toString()).replace(Placeholders.CITY, city).replace(Placeholders.API_KEY, api_key);
        ResponseEntity<WeatherApiResponse> response = restTemplate.exchange(finalapi, HttpMethod.GET, null, WeatherApiResponse.class);
        WeatherApiResponse body = response.getBody();
        return body;
    }
}
