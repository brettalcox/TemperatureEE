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
public class DataInterval {
    public static void main(String[] args) {
        Thread thread = new Thread(){
            public void run(){
                System.out.println("Thread Running");
                try {
                    while(true) {
                        TemperatureClient temperatureClient = new TemperatureClient();
                        NestObject nestObject = temperatureClient.retrieveNestData("https://developer-api.nest.com/");
                        WundergroundObject wundergroundObject = temperatureClient.retrieveWundergroundData("http://api.wunderground.com/api/1e53ae47cd4ad0e7/conditions/q/MO/Lee's_Summit.json");

                        MQFactory mqFactory = new MQFactory();
                        mqFactory.init("TEMPQUEUE");
                        mqFactory.sendMessage(temperatureClient.packageTemperatureData(nestObject, wundergroundObject));
                        mqFactory.destroy();
                        
                        mqFactory.init("HUMIDQUEUE");
                        mqFactory.sendMessage(temperatureClient.packageHumidityData(nestObject, wundergroundObject));
                        mqFactory.destroy();
                        
                        mqFactory.init("HVACQUEUE");
                        mqFactory.sendMessage(temperatureClient.packageHvacData(nestObject, wundergroundObject));
                        mqFactory.destroy();

                        Thread.sleep(1000 * 60 * 15);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
