package com.example.demo.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class WeatherApiResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Current current;

    @Getter
    @Setter
    public class Current implements Serializable { // Make it implement Serializable
        private static final long serialVersionUID = 1L;

        @JsonProperty("temp_c")
        private double tempC;
        @JsonProperty("temp_f")
        private double tempF;

        @Override
        public String toString() {
            return "tempC=" + tempC +
                    ", tempF=" + tempF;
        }
    }

    @Override
    public String toString() {
        return current + " ";
    }
}
