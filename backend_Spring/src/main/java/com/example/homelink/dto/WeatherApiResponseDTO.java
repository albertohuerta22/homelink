package com.example.homelink.dto;

import java.util.List;

public class WeatherApiResponseDTO {

    // Assume each 'DailyTimeline' represents a set of daily forecasts within the JSON response
    private List<DailyTimeline> timelines;

    // Getter and setter for the list of 'DailyTimeline' objects
    public List<DailyTimeline> getTimelines() {
        return timelines;
    }

    public void setTimelines(List<DailyTimeline> timelines) {
        this.timelines = timelines;
    }

    // Static inner class representing a 'timeline' in the JSON response
    public static class DailyTimeline {
        // Assume 'entries' represent individual forecast entries within a timeline
        private List<DailyData> entries;

        // Getter and setter for the list of 'DailyData' objects
        public List<DailyData> getEntries() {
            return entries;
        }

        public void setEntries(List<DailyData> entries) {
            this.entries = entries;
        }
    }

    // Another static inner class representing individual forecast data points within a 'DailyTimeline'
    public static class DailyData {
        private double temperatureMax;
        private double temperatureMin;

        // Getters and setters for temperature data
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

