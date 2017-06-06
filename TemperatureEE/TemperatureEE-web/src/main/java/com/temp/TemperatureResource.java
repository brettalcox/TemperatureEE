/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.temp;

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
@Path("/temperature")
@RequestScoped
public class TemperatureResource {

    @Context
    private UriInfo context;
    
    @EJB
    private TemperatureBeanLocal temperatureBean;

    /**
     * Creates a new instance of TemperatureResource
     */
    public TemperatureResource() {
    }
    
    @Path("/intervals/data.json")
    @GET
    @Produces("application/json")
    public List<Temperature> getTemperatureByInterval() {
        return temperatureBean.getTemperatureByInterval();
    }
}
