/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.temp.mitochondria;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import org.json.JSONObject;

/**
 *
 * @author brettalcox
 */
public class TemperatureClient {
    
    public static final short MAX_RETRIES = 3;
    
    private boolean nestRetry = true;

    private boolean WundergroundRetry = true;

    public void TemperatureClient() {

    }

    public NestObject retrieveNestData(String urlToRead) {
        StringBuilder result = null;

        try {
            URL url = null;
            url = new URL(urlToRead);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("content-type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + System.getProperty("nest_token"));
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            short retries = 0;
            while(nestRetry) {
                if(conn.getResponseCode() <= 300) {
                    String line;
                    result = new StringBuilder();
                    while ((line = rd.readLine()) != null) {
                        result.append(line);
                    }
                    nestRetry = false;
                } else {
                    if(retries != MAX_RETRIES) {
                        retries++;
                    } else {
                        nestRetry = false;
                    }
                }
            }

            rd.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (result != null) {
            JSONObject jsonObject = new JSONObject(result.toString());
            jsonObject = jsonObject.getJSONObject("devices").getJSONObject("thermostats").getJSONObject("pKHxaukFQR46OMJ-IwrOrw2sRKVI60pl");

            return new NestObject(jsonObject.getDouble("ambient_temperature_f"),
                    jsonObject.getDouble("humidity"), jsonObject.getDouble("target_temperature_f"), 
                    jsonObject.getString("hvac_mode"), jsonObject.getString("hvac_state"), 
                    jsonObject.getBoolean("has_leaf"));
        } else {
            return null;
        }
    }

    public WundergroundObject retrieveWundergroundData(String urlToRead) {
        StringBuilder result = null;

        try {
            URL url = null;
            url = new URL(urlToRead);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("content-type", "application/json");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            short retries = 0;
            while(WundergroundRetry) {
                if(conn.getResponseCode() <= 300) {
                    String line;
                    result = new StringBuilder();
                    while ((line = rd.readLine()) != null) {
                        result.append(line);
                    }
                    WundergroundRetry = false;
                } else {
                    if(retries != MAX_RETRIES) {
                        retries++;
                    } else {
                        WundergroundRetry = false;
                    }
                }
            }
                        
            rd.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (result != null) {
            JSONObject jsonObject = new JSONObject(result.toString());
            jsonObject = jsonObject.getJSONObject("current_observation");

            return new WundergroundObject(jsonObject.getDouble("temp_f"), jsonObject.getString("relative_humidity").split("%")[0], jsonObject.getString("weather"));
        } else {
            return null;
        }
    }

    public HashMap<String, String> packageTemperatureData(NestObject nestObject, WundergroundObject wundergroundObject) {
        HashMap<String, String> messageObject = new HashMap<String, String>();

        if (nestObject != null && wundergroundObject != null) {
            messageObject.put("ambient_temperature", Double.toString(nestObject.getAmbientTemperature()));
            messageObject.put("ambient_humidity", Double.toString(nestObject.getAmbientHumidity()));
            messageObject.put("target_temperature", Double.toString(nestObject.getTargetTemperature()));
            messageObject.put("hvac_mode", nestObject.getHvacMode());
            messageObject.put("hvac_state", nestObject.getHvacState());
            messageObject.put("has_leaf", Boolean.toString(nestObject.getHasLeaf()));
            messageObject.put("outside_temperature", Double.toString(wundergroundObject.getOutsideTemperature()));
            messageObject.put("outside_humidity", wundergroundObject.getOutsideHumidity());
            messageObject.put("current_weather", wundergroundObject.getCurrentWeather());
        } else {
            return null;
        }

        return messageObject;
    }
}
