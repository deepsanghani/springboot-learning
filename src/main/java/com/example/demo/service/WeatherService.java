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

    @Autowired
    private RedisService redisService;

//    @Cacheable(value = "weatherCache", key = "#city")
    public WeatherApiResponse getWeather(String city){
        WeatherApiResponse weatherApiResponse = redisService.get("weather_of_" + city, WeatherApiResponse.class);
        if(weatherApiResponse != null){
            return weatherApiResponse;
        }
        else {
            String finalapi = appCacheEntity.appCache.get(AppCache.keys.weather_api.toString()).replace(Placeholders.CITY, city).replace(Placeholders.API_KEY, api_key);
            ResponseEntity<WeatherApiResponse> response = restTemplate.exchange(finalapi, HttpMethod.GET, null, WeatherApiResponse.class);
            WeatherApiResponse body = response.getBody();
            if(body!=null){
                redisService.set("weather_of_"+city, body, 300l);
            }
            return body;
        }
    }
}
