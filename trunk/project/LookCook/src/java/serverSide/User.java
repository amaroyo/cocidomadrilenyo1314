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
    
    
    private int id;
    private ArrayList <String> ingredient;
    private ArrayList <Recipe> recipe; //Any recipe that the querys return
    private Recommender recommender;
    private RelatedMeal relatedMeal;
    
    public User (int id) {
        this.id = id;
        
        ingredient = new ArrayList ();
        ingredient.clear ();
        
        recipe = new ArrayList ();
        recipe.clear ();
        
        recommender = new Recommender (recipe);
        relatedMeal = new RelatedMeal (recipe);
        
    }
    
    protected ArrayList getIngredients () {
        
        return ingredient;
    }
    
    
}
