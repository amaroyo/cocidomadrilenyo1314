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
public class RelatedMeal {
    
    private ArrayList <Recipe> recipe;
    
    public RelatedMeal (ArrayList recipe) {
        this.recipe = recipe;
    }
}
