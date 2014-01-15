package server;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;

/**
 *
 * @author Asier
 */
@Path("lookandcook")
public class OldController {
    
    @Context
    private UriInfo context;
    
    private static ArrayList <User> user=new ArrayList();
    private static Parser parser = new Parser();
    private static DB db = new DB();
    
    
    public OldController () {
    
    }
    
    /*@PUT
    @Path("/ingredient")
    @Consumes("application/xml")*/
    public String putIngredient (String userID, String content) {
        Ingredient ingr;
        ingr = parser.unmarshal(content, false).get(0);
        int id = Integer.parseInt(userID);
        boolean existence = user.get(id).ingredientExists(ingr);
        //marshal ingredient
        return parser.marshalIngredient(ingr.getIngredientName(), existence);
    }
    
    /*@PUT
    @Path("/recipe")
    @Consumes("application/xml")*/
    public void putRecipe (String userID, String content) {
        ArrayList <Ingredient> ingrList;
        ingrList = parser.unmarshal(content, true);
        int id = Integer.parseInt(userID);
        user.get(id).lookFor(ingrList);
    }
    
    /*@GET
    @Path("/recipe")
    @Produces("application/xml")*/
    public String getRecipe (String userID) {
        int id = Integer.parseInt(userID);
        ArrayList <Recipe> recipes;
        recipes = user.get(id).getRecipes();
        
        return parser.marshalRecipe(recipes);
    }
    
    /*@GET
    @Path("/recomendations")
    @Produces("application/xml")*/
    public String getRecommendations (String userID) {
        int id = Integer.parseInt(userID);
        ArrayList <Recipe> recommendations;
        recommendations = user.get(id).getRecommendations();
        return parser.marshalRecipe(recommendations);
    }
    
    /*@GET
    @Path("/related")
    @Produces("application/xml")*/
    public String getRelatedMeals (String userID) {
        int id = Integer.parseInt(userID);
        ArrayList <Recipe> relatedMeals;
        relatedMeals = user.get(id).getRelatedMeals();
        return parser.marshalRecipe(relatedMeals);
    }
    
    @GET
    @Path("/user")
    @Produces("application/xml")
    public String newUser () {
        int pos = user.size();
        String content = parser.newUser(pos);
        user.add(new User(pos));
        
        return content;
    }
    
    public void deleteUser (String userID) {
        int id = Integer.parseInt(userID);
        user.remove(id);//save db with this particular user's content?
    }
    
    public void doSomethingWithDB (String content) {
        
    }
    
}
