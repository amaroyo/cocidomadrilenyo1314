/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 

package serverSide;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.*;
import java.util.ArrayList;

/**
 *
 * @author Asier
 */
public class User {
    
    
    
    
    private int id;
    private ArrayList <Ingredient> ingredient;
    private ArrayList <Recipe> recipe; 
    private Recommender recommender;
    private RelatedMeals relatedMeal;
    
    public User (int id) {
        this.id = id;
        
        ingredient = new ArrayList ();
        ingredient.clear ();
        
        recipe = new ArrayList ();
        recipe.clear ();
        
        recommender = new Recommender ();
        relatedMeal = new RelatedMeals ();
        
    }
    
    protected ArrayList getIngredients () {
        
        return ingredient;
    }
    /**
     * Adds the given ingredients to the user's personal list of most used
     * ingredients.
     * 
     * It also updates the recommenders list of ingredients.
     * 
     * @param ingredients 
     * @seeAlso addNewIngredient
     */
    public void addNewIngredients(ArrayList <String> ingredients){
        for(int i = 0; i<ingredients.size(); i++){
            addNewIngredient(ingredients.get(i));
        }
        this.recommender.updateIngredients(this.ingredient);
    }
    /**
     * Adds one ingredient to user's personal list of most used ingredients
     * @param newIngredient 
     */
    public void addNewIngredient(String newIngredient){
        boolean exists = false;
        for(int i = 0; i < this.ingredient.size(); i++){
            if(ingredient.get(i).getIngredient().equals(newIngredient)){
                ingredient.get(i).increment();
                exists = true;
            }
        }
        if(!exists) ingredient.add(new Ingredient(newIngredient));
    }
    /**
     * This method is called every time a client sends new ingredients.
     * @param clientIngredients 
     */
    public void lookFor(ArrayList <String> clientIngredients){
        //First thing we add the new ingredients to the personal list.
        addNewIngredients(clientIngredients);
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
        // until we get the recipes that contain the biggest quantity of the 
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
        //this.relatedMeal.updateRecipes(clientIngredients, hits);
        updateRelated(clientIngredients, hits);
    }
    
    public void updateRelated(ArrayList <String> ingredients, int startHits){
        ArrayList <Recipe> relatedRecipes = new ArrayList<>();
        relatedRecipes.clear();
        
        for(int hits = startHits; hits>0;hits--){
            //This is supposed to concatenate both ArrayLists.
            relatedRecipes.addAll(Query.recipes(ingredients,hits));
        }
        this.relatedMeal.updateRecipes(relatedRecipes);
    }
    
    /**
     * Checks if the ingredient introduced exists in dbpedia.
     * It makes a search for recipes containing that ingredient.
     * @param ing
     * @return true if there exists at least one recipe with the given ingredient
     */
    public boolean ingredientExists(String ing){
        ArrayList<String> temp = new ArrayList<>();
        temp.clear();
        temp.add(ing);
        ArrayList<Recipe> recipes = Query.recipes(temp);
        
        return !recipes.isEmpty();
    }
    
    
    
    
    //Below this line there are the methods that make direct queries to dbPedia.
    
    
}
