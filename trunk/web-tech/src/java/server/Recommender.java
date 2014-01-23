/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.util.*;

/**
 *
 * @author Asier
 */
public class Recommender {
    
    private final static int MAX = 5;
    
    private final static int MUST=1;
    private final static int CAN=0;
    private final static int NOT=-1;
    private ArrayList <Recipe> recipes;
    private ArrayList <Ingredient> ingredients;
    
    public ArrayList <Recipe> getRecommendedRecipes(){
        return recipes;
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
    public void updateIngredients(ArrayList <Ingredient> ingredients){
        for(int i = 0; i<ingredients.size(); i++){
            if(ingredients.get(i).getPriority() != NOT){
                addNewIngredient(ingredients.get(i).getIngredientName());
            }
        }
        sortList();
        recommend();
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
                break;
            }
        }
        if(!exists) ingredients.add(new Ingredient(newIngredient, 0));
    }
  
    public class CustomComparator implements Comparator<Ingredient> {
        public int compare(Ingredient o1, Ingredient o2) {
            return o1.getCount()-o2.getCount();
        }
    }
    /**
     * Sorts the list of ingredients by the value of their counters.
     */
    private void sortList(){
        Collections.sort(recipes, new CustomComparator());
    }
    
    private void recommend(){
        ArrayList <Ingredient> aux =ingredients;
        while(recipes.size()<MAX && aux.size() > 0){
            recipes.addAll(Query.recipes(aux));
            //Puede que tengamos problemas con recetas repetidas.
            aux.remove(aux.size()-1); //Remove the last one
        }
    }
    
}
