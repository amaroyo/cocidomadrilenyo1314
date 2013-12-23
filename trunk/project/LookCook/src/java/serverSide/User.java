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
public class User {
    
    
    private Object id;
    private ArrayList <String> ingredient;
    private ArrayList <Recipe> recipe;
    
    public User (Object id) {
        this.id = id;
        
        ingredient = new ArrayList ();
        ingredient.clear ();
        
        recipe = new ArrayList ();
        recipe.clear ();
        
    }
    
    protected ArrayList getIngredients () {
        return ingredient;
    }
    
}
