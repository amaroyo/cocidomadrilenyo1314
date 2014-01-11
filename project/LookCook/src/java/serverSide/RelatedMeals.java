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
    //public void updateRecipes(ArrayList <String> ingredients, int hits){ //Esta linea es paa la opcion 1 o 2
    public void updateRecipes(ArrayList<Recipe> newRecipes){
       this.relatedRecipes = newRecipes;
    }
    
}
