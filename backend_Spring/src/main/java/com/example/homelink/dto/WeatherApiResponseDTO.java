package com.example.homelink.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherApiResponseDTO {

    private List<DailyTimeline> timelines;

    public List<DailyTimeline> getTimelines() {
        return timelines;
    }

    public void setTimelines(List<DailyTimeline> timelines) {
        this.timelines = timelines;
    }

    public static class DailyTimeline {
    @JsonProperty("values")
    private DailyValues values;

    public DailyValues getValues() {
        return values;
    }

    public void setValues(DailyValues values) {
        this.values = values;
    }

    public static class DailyValues {
        @JsonProperty("temperatureMin")
        private double temperatureMin;

        @JsonProperty("temperatureMax")
        private double temperatureMax;

        public double getTemperatureMin() {
            return temperatureMin;
        }

        public void setTemperatureMin(double temperatureMin) {
            this.temperatureMin = temperatureMin;
        }

        public double getTemperatureMax() {
            return temperatureMax;
        }

        public void setTemperatureMax(double temperatureMax) {
            this.temperatureMax = temperatureMax;
        }
    }
    }
}
