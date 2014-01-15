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
    public boolean hasIngredients(ArrayList <String> ing){
        for(int i = 0; i< this.ingredient.size(); i++){
            for(int j=0;j<ing.size();j++){
                if(ing.get(j).equals(this.ingredient.get(i))){
                return true;
                }
            }
        }
        return false;        
    }
    
    
}
