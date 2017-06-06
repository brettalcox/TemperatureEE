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
public class NestObject {

    private double ambientTemperature;

    private double ambientHumidity;
    
    private double targetTemperature;

    private String hvacMode;

    private String hvacState;
    
    private boolean hasLeaf;

    public NestObject(double ambientTemperature, double ambientHumidity, double targetTemperature, String hvacMode, String hvacState, boolean hasLeaf) {
        this.ambientTemperature = ambientTemperature;
        this.ambientHumidity = ambientHumidity;
        this.targetTemperature = targetTemperature;
        this.hvacMode = hvacMode;
        this.hvacState = hvacState;
        this.hasLeaf = hasLeaf;
    }

    public void NestObject() {

    }

    public double getAmbientTemperature() { return ambientTemperature; }

    public double getAmbientHumidity() { return ambientHumidity; }
    
    public double getTargetTemperature() { return targetTemperature; }

    public String getHvacMode() { return hvacMode; }

    public String getHvacState() { return hvacState; }
    
    public boolean getHasLeaf() { return hasLeaf; }

    public void setAmbientTemperature(double ambientTemperature) {
        this.ambientTemperature = ambientTemperature;
    }

    public void setAmbientHumidity(double ambientHumidity) {
        this.ambientHumidity = ambientHumidity;
    }
    
    public void setTargetTemperature(double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public void setHvacMode(String hvacMode) {
        this.hvacMode = hvacMode;
    }

    public void setHvacState(String hvacState) {
        this.hvacState = hvacState;
    }
    
    public void setHasLEaf(boolean hasLeaf) { 
        this.hasLeaf = hasLeaf;
    }
}
