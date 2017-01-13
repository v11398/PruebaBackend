/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebabackend;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Alfredo
 */
@Stateless
@Path("/time")
public class TimeResource {
    
    private static final SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    
    @GET
    @Path("time")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response word(@QueryParam("value") String value)
    {
        try
        {
            long hora = Long.parseLong(value);
            String horaFormateada = formato.format(new Date(hora)).toString();            
            return Response.
                    status(Response.Status.OK).
                    entity("{\"code\":200,\"description\":\"OK\",\"data\":\"" + horaFormateada + "\"}").
                    build();
        }
        catch(NumberFormatException ex)
        {
            return Response.
                    status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("{\"code\":500,\"description\":\"Excepción de formato del número\"}").
                    build();
        }        
    }
}
