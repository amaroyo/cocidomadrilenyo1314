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
public class Recipe {
    private String recipeName;
    private ArrayList <String> ingredient;
    private String snippet;
    
    public Recipe () {
        this.recipeName = "";
        this.ingredient = new ArrayList ();
        this.ingredient.clear ();
    }
    
    public Recipe(String name, ArrayList <String> ingredient){
        this.recipeName = name;
        this.ingredient = ingredient;
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
    
    
    
}
