/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.temp;

import com.temp.model.Humidity;
import com.temp.model.Hvac;
import com.temp.model.Temperature;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;

/**
 * REST Web Service
 *
 * @author brettalcox
 */
@Path("/data")
@RequestScoped
public class DataResource {

    @Context
    private UriInfo context;
    
    @EJB
    private TemperatureBeanLocal temperatureBean;
    
    @EJB
    private HumidityBeanLocal humidityBean;
    
    @EJB
    private HvacBeanLocal hvacBean;

    /**
     * Creates a new instance of TemperatureResource
     */
    public DataResource() {
    }
    
    @Path("/temperature/intervals/data.json")
    @GET
    @Produces("application/json")
    public List<Temperature> getAllTempIntervals() {
        return temperatureBean.getAllIntervals();
    }
    
    @Path("/temperature/intervals/day/data.json")
    @GET
    @Produces("application/json")
    public List<Temperature> getLastDayTempIntervals() {
        return temperatureBean.getLastDayIntervals();
    }
    
    @Path("/humidity/intervals/data.json")
    @GET
    @Produces("application/json")
    public List<Humidity> getAllHumidIntervals() {
        return humidityBean.getAllIntervals();
    }
    
    @Path("/humidity/intervals/day/data.json")
    @GET
    @Produces("application/json")
    public List<Humidity> getLastDayHumidIntervals() {
        return humidityBean.getLastDayIntervals();
    }
    
    @Path("/hvac/intervals/data.json")
    @GET
    @Produces("application/json")
    public List<Hvac> getAllIntervals() {
        return hvacBean.getAllIntervals();
    }
    
    @Path("/hvac/intervals/day/data.json")
    @GET
    @Produces("application/json")
    public List<Hvac> getLastDayIntervals() {
        return hvacBean.getLastDayIntervals();
    }
}
