/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Server;

import java.util.ArrayList;

/**
 *
 * @author Asier
 */
public class Controller {
    
    private ArrayList <User> user;
    private Parser parser;
    private DB db;
    
    public String variable = "";
    
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
        int id = Integer.parseInt(userID);
        user.get(id).lookFor(ingrList);
    }
    
    public String getRecipe (String userID) {
        int id = Integer.parseInt(userID);
        ArrayList <Recipe> recipes;
        recipes = user.get(id).getRecipes();
        
        return parser.marshalRecipe(recipes);
    }
    
    public String getRecommendations (String userID) {
        int id = Integer.parseInt(userID);
        ArrayList <Recipe> recommendations;
        recommendations = user.get(id).getRecommendations();
        return parser.marshalRecipe(recommendations);
    }
    
    public String getRelatedMeals (String userID) {
        int id = Integer.parseInt(userID);
        ArrayList <Recipe> relatedMeals;
        relatedMeals = user.get(id).getRelatedMeals();
        return parser.marshalRecipe(relatedMeals);
    }
    
    public String newUser () {
        int pos = user.size();
        String content = parser.newUser(pos);
        variable = content;
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
