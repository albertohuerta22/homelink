package com.example.homelink.dto;

import java.util.List;

public class WeatherApiResponseDTO {

    private List<DailyTimeline> timelines;

    public List<DailyTimeline> getTimelines() {
        return timelines;
    }

    public void setTimelines(List<DailyTimeline> timelines) {
        this.timelines = timelines;
    }

    public static class DailyTimeline {
        private List<DailyData> entries;

        public List<DailyData> getEntries() {
            return entries;
        }

        public void setEntries(List<DailyData> entries) {
            this.entries = entries;
        }
    }

    public static class DailyData {
        private double temperatureMax;
        private double temperatureMin;

        public double getTemperatureMax() {
            return temperatureMax;
        }

        public void setTemperatureMax(double temperatureMax) {
            this.temperatureMax = temperatureMax;
        }

        public double getTemperatureMin() {
            return temperatureMin;
        }

        public void setTemperatureMin(double temperatureMin) {
            this.temperatureMin = temperatureMin;
        }
    }
}
