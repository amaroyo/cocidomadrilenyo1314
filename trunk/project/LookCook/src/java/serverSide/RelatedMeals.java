/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serverSide;

import java.util.ArrayList;

/**
 *
 * @author Plasavall
 */
public class RelatedMeals {
    
    private ArrayList <Recipe> relatedRecipes;
    
        /**
         * Vamos a copiar y pegar las funciones de query a esta clase?
         * 
         * ¿Creamos una clase a parte para llamar a todos los query methods 
         * estaticamente?
         * 
         * ¿Updateamos la lista de recetas en el user y la mandamos directamente
         * aqui sin guardar copia en el user?
         * 
         * De momento voy a hacer esta ultima y ya veremos si la cambiamos o no
         */
    /**
     * This functions uses the list of ingredients from the last search to 
     * calculate a list of related recipes to the ones stored in User.
     * For this, it uses startHits, which is the next biggest combination of
     * ingredients to the one calculated in the user.
     * 
     * @param ingredients
     * @param startHits 
     */
    public void updateRecipes(ArrayList <Ingredient> ingredients, int startHits){ 
        for(int hits = startHits; hits>0;hits--){
            //This is supposed to concatenate both ArrayLists.
            relatedRecipes.addAll(Query.recipes(ingredients,hits));
        }
    }
    
}
