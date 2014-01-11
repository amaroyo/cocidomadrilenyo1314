/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serverSide;

import java.util.ArrayList;

/**
 *
 * @author Asier
 */
public class Recommender {
    
    private final static int MUST=1;
    private final static int CAN=0;
    private final static int NOT=-1;
    private ArrayList <Recipe> recipes;
    private ArrayList <Ingredient> ingredients;
    
  
    
        /**
     * Adds the given ingredients to the user's personal list of most used
     * ingredients.
     * 
     * It also updates the recommenders list of ingredients.
     * 
     * @param ingredients 
     * @seeAlso addNewIngredient
     */
    public void updateIngredients(ArrayList <Ingredient> ingredients){
        for(int i = 0; i<ingredients.size(); i++){
            if(ingredients.get(i).getPriority() != NOT){
                addNewIngredient(ingredients.get(i).getIngredientName());
            }
        }
    }
    /**
     * Adds one ingredient to user's personal list of most used ingredients
     * @param newIngredient 
     */
    private void addNewIngredient(String newIngredient){
        boolean exists = false;
        for(int i = 0; i < this.ingredients.size(); i++){
            if(ingredients.get(i).getIngredientName().equals(newIngredient)){
                ingredients.get(i).increment();
                exists = true;
            }
        }
        if(!exists) ingredients.add(new Ingredient(newIngredient, 0));
    }
    
    private void sortList(){
        
    }
    
    
}
