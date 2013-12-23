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
    
    private ArrayList <String> ingredient;
    private String snippet;
    
    public Recipe () {
        ingredient = new ArrayList ();
        ingredient.clear ();
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
    
    
    
}
