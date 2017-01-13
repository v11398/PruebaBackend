package pruebabackend;


import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alfredo
 */
@Stateless
@Path("/word")
public class WordResource {
    
    @POST
    @Path("word")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response word(String data)
    {   
        try
        {
            JSONObject jsonObject = new JSONObject(data);
            if(jsonObject.has("data"))
            {
                String palabra = jsonObject.getString("data");
                if(palabra.matches("[a-zA-Z]+") && (palabra.length() <= 4))
                {
                    return Response.
                            status(Response.Status.OK).
                            entity("{\"code\":200,\"description\":\"OK\",\"data\":\"" + palabra.toUpperCase() + "\"}").
                            build();
                }
                else
                {
                    return Response.
                            status(Response.Status.BAD_REQUEST).                            
                            entity("{\"code\":400,\"description\":\"Data no es válida\"}").
                            build();
                }
            }
            else
            {
                return Response.
                        status(Response.Status.BAD_REQUEST).
                        entity("{\"code\":400,\"description\":\"No existe llave data\"}").
                        build();
            }
        }
        catch(JSONException ex)
        {
            return Response.
                    status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("{\"code\":500,\"description\":\"Excepción en parsear JSON\"}").
                    build();
        }        
    }
}
