package com.example.demo.api.response;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.*;

public class WeatherApiResponseSerializer implements RedisSerializer<WeatherApiResponse> {

    @Override
    public byte[] serialize(WeatherApiResponse weatherApiResponse) throws SerializationException {
        if (weatherApiResponse == null) {
            return null;
        }
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(weatherApiResponse);
            System.out.println("Weather api serializer"+ weatherApiResponse);
            return bos.toByteArray();
        } catch (IOException e) {
            throw new SerializationException("Error serializing WeatherApiResponse", e);
        }
    }

    @Override
    public WeatherApiResponse deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null) {
            return null;
        }
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
//            WeatherApiResponse ws = (WeatherApiResponse) ois.readObject();
            System.out.println("Weather api deserializer");
            return (WeatherApiResponse) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new SerializationException("Error deserializing WeatherApiResponse", e);
        }
    }
}
