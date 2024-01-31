package com.example.homelink.dto;

public class WeatherDataDTO {

    private double maxTempFahrenheit;
    private double minTempFahrenheit;

    public WeatherDataDTO(double maxTempFahrenheit, double minTempFahrenheit) {
        this.maxTempFahrenheit = maxTempFahrenheit;
        this.minTempFahrenheit = minTempFahrenheit;
    }

    public double getMaxTempFahrenheit() {
        return maxTempFahrenheit;
    }

    public void setMaxTempFahrenheit(double maxTempFahrenheit) {
        this.maxTempFahrenheit = maxTempFahrenheit;
    }

    public double getMinTempFahrenheit() {
        return minTempFahrenheit;
    }

    public void setMinTempFahrenheit(double minTempFahrenheit) {
        this.minTempFahrenheit = minTempFahrenheit;
    }

    // Optionally, override toString(), equals(), and hashCode() methods as needed
}
