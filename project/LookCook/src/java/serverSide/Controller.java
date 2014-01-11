/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serverSide;

import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Asier
 */
public class Controller {
    
    private ArrayList <User> user;
    private Parser parser;
    private DB db;
    
    public Controller () {
        user = new ArrayList ();
        user.clear ();
        
        parser = new Parser ();
        
        db = new DB ();
    }
    
    public String putIngredient (String userID, String content) {
        Ingredient ingr;
        ingr = parser.unmarshal(content, false).get(0);
        int id = Integer.parseInt(userID);
        boolean existence = user.get(id).ingredientExists(ingr);
        //marshal ingredient
        return parser.marshalIngredient(ingr.getIngredientName(), existence);
    }
    
    public void putRecipe (String userID, String content) {
        ArrayList <Ingredient> ingrList;
        ingrList = parser.unmarshal(content, true);
        //user.get(id).new recipes(ingrList)
    }
    
    public String getRecipe (String userID) {
        ArrayList <Recipe> recipes;
        int id = Integer.parseInt(userID);
        recipes = user.get(id).getRecipes();
        
        return parser.marshalRecipe(recipes);
    }
    
    public String getRecommendations (String content) {
        
        return null;
    }
    
    public String getRelatedMeals (String content) {
        
        return null;
    }
    
    public void newUser (String content) {
        //un nuevo cliente requiere nuevas recomendaciones
        //el cliente nos enviará el id del usuario.
    }
    
    public void doSomethingWithDB (String content) {
        
    }
    
}
