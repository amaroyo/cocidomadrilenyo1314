/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.util.ArrayList;

/**
 *
 * @author Plasavall
 */
public class RelatedMeals {
    
    private ArrayList <Recipe> relatedRecipes;
    
    
    public RelatedMeals(){
        relatedRecipes = new ArrayList<>();
        relatedRecipes.clear();
    }
        /**
         * 1 Vamos a copiar y pegar las funciones de query a esta clase?
         * 
         * 2 ¿Creamos una clase a parte para llamar a todos los query methods 
         * estaticamente?
         * 
         * 3 ¿Updateamos la lista de recetas en el user y la mandamos directamente
         * aqui sin guardar copia en el user?
         * 
         * Ahora está desarrollada la opcion 2.
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
        System.out.println("Tamaño ingredients related: "+ingredients.size());
        for(int hits = startHits; hits>0;hits--){
            //This is supposed to concatenate both ArrayLists.
            relatedRecipes.addAll(Query.recipes(ingredients,hits));
        }
    }

    ArrayList<Recipe> getRelatedMeals() {
        return this.relatedRecipes;
    }
    
}
