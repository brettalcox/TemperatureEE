/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.temp.mitochondria;

/**
 *
 * @author brettalcox
 */
public class WundergroundObject {

    private double outsideTemperature;

    private String outsideHumidity;
    
    private String currentWeather;

    public WundergroundObject(double outsideTemperature, String outsideHumidity, String currentWeather) {
        this.outsideTemperature = outsideTemperature;
        this.outsideHumidity = outsideHumidity;
        this.currentWeather = currentWeather;
    }

    public double getOutsideTemperature() {
        return outsideTemperature;
    }

    public void setOutsideTemperature(double outsideTemperature) {
        this.outsideTemperature = outsideTemperature;
    }

    public String getOutsideHumidity() {
        return outsideHumidity;
    }

    public void setOutsideHumidity(String outsideHumidity) {
        this.outsideHumidity = outsideHumidity;
    }
    
    public String getCurrentWeather() {
        return currentWeather;
    }
    
    public void setCurrentWeather(String currentWeather) {
        this.currentWeather = currentWeather;
    }
}
