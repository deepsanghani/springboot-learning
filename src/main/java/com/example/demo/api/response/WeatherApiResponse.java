package com.example.demo.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherApiResponse {
    private Current current;

    @Getter
    @Setter
    public class Current{
        @JsonProperty("localtime_epoch")
        private int lastUpdatedEpoch;
        @JsonProperty("last_updated")
        private String lastUpdated;
        @JsonProperty("temp_c")
        private double tempC;
        @JsonProperty("temp_f")
        private double tempF;
        @JsonProperty("is_day")
        private int isDay;
        @JsonProperty("wind_mph")
        private double windMph;
        @JsonProperty("wind_kph")
        private double windKph;
        @JsonProperty("wind_degree")
        private int windDegree;
        @JsonProperty("wind_dir")
        private String windDir;
        @JsonProperty("pressure_mb")
        private double pressureMb;
        @JsonProperty("pressure_in")
        private double pressureIn;
        @JsonProperty("precip_mm")
        private double precipMm;
        @JsonProperty("precip_in")
        private double precipIn;
        private int humidity;
        private int cloud;
        @JsonProperty("feelslike_c")
        private double feelslikeC;
        @JsonProperty("feelslike_f")
        private double feelslikeF;
        @JsonProperty("vis_km")
        private double visKm;
        @JsonProperty("vis_miles")
        private double visMiles;
        private double uv;
        @JsonProperty("gust_mph")
        private double gustMph;
        @JsonProperty("gust_kph")
        private double gustKph;

        @Override
        public String toString() {
            return "Current{" +
                    "lastUpdatedEpoch=" + lastUpdatedEpoch +
                    ", lastUpdated='" + lastUpdated + '\'' +
                    ", tempC=" + tempC +
                    ", tempF=" + tempF +
                    ", isDay=" + isDay +
                    ", windMph=" + windMph +
                    ", windKph=" + windKph +
                    ", windDegree=" + windDegree +
                    ", windDir='" + windDir + '\'' +
                    ", pressureMb=" + pressureMb +
                    ", pressureIn=" + pressureIn +
                    ", precipMm=" + precipMm +
                    ", precipIn=" + precipIn +
                    ", humidity=" + humidity +
                    ", cloud=" + cloud +
                    ", feelslikeC=" + feelslikeC +
                    ", feelslikeF=" + feelslikeF +
                    ", visKm=" + visKm +
                    ", visMiles=" + visMiles +
                    ", uv=" + uv +
                    ", gustMph=" + gustMph +
                    ", gustKph=" + gustKph +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "WeatherApiResponse{" +
                "current=" + current +
                '}';
    }
}
