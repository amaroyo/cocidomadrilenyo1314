/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.util.ArrayList;

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
    
    public Recommender(){
        recipes = new ArrayList<>();
        recipes.clear();
        
        ingredients = new ArrayList<>();
        ingredients.clear();
    }
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
        System.out.println("Tamaño ingredients recom: "+ingredients.size());
        for(int i = 0; i < this.ingredients.size(); i++){
            if(ingredients.get(i).getIngredientName().equals(newIngredient)){
                ingredients.get(i).increment();
                exists = true;
                break;
            }
        }
        if(!exists) ingredients.add(new Ingredient(newIngredient, 0));
    }
  
    /**
     * Sorts the list of ingredients by the value of their counters.
     */
    private void sortList(){
        ArrayList <Ingredient> newList = new ArrayList<>();
        newList.clear();
        Ingredient mostCommon = ingredients.get(0);
        for(int i=0;i<ingredients.size()-1;i++){
            for(int j=i+1;j<ingredients.size();j++){ 
                if(i != j) {
                    if(ingredients.get(j).getCount()>mostCommon.getCount()){
                        mostCommon=ingredients.get(j);
                    }
                }
            }
            newList.add(mostCommon);
            ingredients.remove(mostCommon);
        }
        ingredients=newList;
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
