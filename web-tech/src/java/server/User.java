/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 

package server;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.*;
import java.util.ArrayList;

/**
 *
 * @author Asier
 */
public class User {
    
    
    private int id;
    private ArrayList <Recipe> recipe; 
    private Recommender recommender;
    private RelatedMeals relatedMeal;
    
    public User (int id) {
        this.id = id;
                
        recipe = new ArrayList ();
        recipe.clear ();
        
        recommender = new Recommender ();
        relatedMeal = new RelatedMeals ();
        
    }
    
    ArrayList<Recipe> getRecommendations() {
        return this.recommender.getRecommendedRecipes();
    }

    ArrayList<Recipe> getRelatedMeals() {
        return this.relatedMeal.getRelatedMeals();
    }
    
    /**
     * Function to use from the interface to get the curent list of recipes.
     * @return 
     */
    public ArrayList <Recipe> getRecipes(){
        return recipe;
    }
    /**
     * This method is called every time a client sends new ingredients.
     * @param clientIngredients 
     */
    public void lookFor(ArrayList <Ingredient> clientIngredients){
        //First thing we add the new ingredients to the personal list.
        this.recommender.updateIngredients(clientIngredients);
        //Now we update the recipes section of the user using queryRecipes
        ArrayList <Recipe> recipesFound = new ArrayList<>();
        recipesFound.clear();
        //First we try the query with all the ingredients
        recipesFound = Query.recipes(clientIngredients);
        if(recipesFound == null){
            recipesFound = new ArrayList<>();
            recipesFound.clear();
        }
        //If it doesn't retrieve anything, we try querying combinations of those
        //until we get the recipes that contain the biggest quantity of the 
        //requested ingredients possible.
        int hits = clientIngredients.size()-1;
        if(recipesFound.isEmpty()){
            while(recipesFound.isEmpty()){
                recipesFound = Query.recipes(clientIngredients, hits);
                hits--;
                if(recipesFound == null){
                    recipesFound = new ArrayList<>();
                    recipesFound.clear();
                }
            }
        }
        this.recipe = recipesFound;
        //Now we send to the relatedmeals object the list of ingredients with
        //the starting number of hits to calculate the even smaller combinations 
        //of those ingredients and show them.
        this.relatedMeal.updateRecipes(clientIngredients, hits);
    }
    
 
    
    /**
     * Checks if the ingredient introduced exists in dbpedia.
     * It makes a search for recipes containing that ingredient.
     * @param ing
     * @return true if there exists at least one recipe with the given ingredient
     */
    public boolean ingredientExists(Ingredient ing){
        ArrayList<Ingredient> temp = new ArrayList<>();
        temp.clear();
        temp.add(ing);
        ArrayList<Recipe> recipes = Query.recipes(temp);
        
        return !recipes.isEmpty();
    }
}
