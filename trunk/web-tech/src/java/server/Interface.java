/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

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
@Path("lookandcook")
public class Interface {

//    @Context
//    private UriInfo context;
    private final String XML_MIME = "application/xml";
    private final static Controller controller = new Controller ();

    /**
     * Creates a new instance of Interface
     */
    public Interface() {
    }
    @PUT
    @Path("/ingredient/{id}")
    @Consumes(XML_MIME)
    @Produces(XML_MIME)
    public String checkIngredient (@PathParam ("id") String userID, String content) {
        return controller.putIngredient(userID, content);
    }
    
    @GET
    @Path("/recipe/{id}")
    @Produces(XML_MIME)
    public String getRecipe (@PathParam ("id") String userID) {
        return "get recipe done";
    }
    
    @PUT
    @Path("/recipe/{id}")
    @Consumes(XML_MIME)
    public void putRecipe (@PathParam ("id") String userID,
                           String content) {
        controller.putRecipe(userID, content);
    }
    
    @GET
    @Path("/recommendations/{id}")
    @Produces(XML_MIME)
    public String getRecommendations (String content) {
        
        return null;
    }
    
    @PUT
    @Path("/recommendations/{id}")
    @Consumes(XML_MIME)
    public void putRecommendations (@PathParam ("id") String userID) {
        
    }
    
    @GET
    @Path("/related/{id}")
    @Produces(XML_MIME)
    public String getRelatedMeals (@PathParam ("id") String userID) {
        return null;
    }
    
    @PUT
    @Path("/related/{id}")
    @Consumes(XML_MIME)
    public void putRelatedMeals (@PathParam ("id") String userID,
                                 String content) {
        
    }
    @GET
    @Path("/newUser/")
    @Produces(XML_MIME)
    public String newUser () {
        return controller.newUser();
    }
    
    @PUT
    @Path("/db/{id}")
    @Consumes(XML_MIME)
    public void doSomethingWithDB (@PathParam ("id") String userID,
                                   String content) {
        controller.likedRecipe(content);
    }
    
    @GET
    @Path("top")
    @Produces(XML_MIME) 
    public String getTop () {
        return controller.getTops();
    }
    
}
