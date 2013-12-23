/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serverSide;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author Asier
 */
@Path("server")
public class Interface {

    //@Context
    //private UriInfo context;
    private final String MIME = "application/xml";
    private final static Controller controller = new Controller ();

    public Interface() {
        
    }
    
    @GET
    @Path("/ingredient/")
    @Produces(MIME)
    public String getIngredient (String content) {
        
        return null;
    }
    
    @GET
    @Path("/recipe/")
    @Produces(MIME)
    public String getRecipe (String content) {
        
        return null;
    }
    
    @GET
    @Path("/recommendations/")
    @Produces(MIME)
    public String getRecommendations (String content) {
        
        return null;
    }
    
    @GET
    @Path("/related/")
    @Produces(MIME)
    public String getRelatedMeals (String content) {
        
        return null;
    }
    
    @PUT
    @Path("/newUser/")
    @Consumes(MIME)
    public void newUser (String content) {
        //un nuevo cliente requiere nuevas recomendaciones
    }
    
    @PUT
    @Path("/db/")
    @Consumes(MIME)
    public void doSomethingWithDB (String content) {
        
    }
    
}