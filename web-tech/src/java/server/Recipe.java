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
public class Recipe {
    private String recipeName;
    private ArrayList <String> ingredient;
    private String snippet;
    private String image;
    
    public Recipe () {
        this.recipeName = "";
        this.snippet = "";
        this.image = "";
        this.ingredient = new ArrayList ();
        this.ingredient.clear ();
    }
    
    public Recipe(String name, ArrayList <String> ingredient, String snippet, String image){
        this.recipeName = name;
        this.ingredient = ingredient;
        this.snippet = snippet;
        this.image = image;
    }
    
    public String getRecipeImage(){
        return image;
    }
    
    public void setRecipeImage (String image) {
        this.image = image;
    }
    
    public ArrayList getRecipeIngredients () {
        return ingredient;
    }
    
    public void setRecipeIngredients (ArrayList <String> ingredient) {
        this.ingredient = ingredient;
    }
    
    public String getRecipeSnippet () {
        return snippet;
    }
    
    public void setRecipeSnippet (String snippet) {
        this.snippet = snippet;
    }
    
    public String getRecipeName(){
        return recipeName;
    }
    
    public void setRecipeName(String name){
        this.recipeName = name;
    }
    public boolean hasIngredients(ArrayList <String> ings){
        for(int i = 0; i<ings.size();i++){
            if(!this.hasIngredient(ings.get(i)))
                return false;
        }
        return true;
    }
    
    public boolean hasIngredient(String ing){
        boolean contained = false;
        for(int i = 0; i< this.ingredient.size(); i++){
                if(ing.equals(this.ingredient.get(i)) || this.ingredient.get(i).equals(ing)){
                    contained = true;
            }
        }
        return contained;        
    }
    
    
}
