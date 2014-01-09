/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serverSide;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.*;
import java.util.ArrayList;

/**
 *
 * @author Asier
 */
public class User {
    
    String SERVICE = "http://dbpedia.org/sparql";
    String PREFIXES = "PREFIX dbo:<http://dbpedia.org/ontology/> "
                      +"PREFIX dbres: <http://dbpedia.org/resource/> "
                      +"PREFIX dbpprop: <http://dbpedia.org/property/> ";
    
    
    private int id;
    private ArrayList <Ingredient> ingredient;
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
    
    /**
     * Checks if the ingredient introduced exists in dbpedia.
     * It makes a search for recipes containing that ingredient.
     * @param ing
     * @return true if there exists at least one recipe with the given ingredient
     */
    public boolean ingredientExists(String ing){
        ArrayList<String> temp = new ArrayList<>();
        temp.clear();
        temp.add(ing);
        ArrayList<Recipe> recipes = queryRecipes(temp);
        
        return !recipes.isEmpty();
    }
    
    /**
     * Queries a list of recipes given a list of ingredients.
     * It's exclusive (Only the recipes containing at least that exact 
     * ingredients are retrieved.
     * @param ingredients
     * @return a ResultSet of recipes containing the given list of ingredients
     */
    public ArrayList <Recipe> queryRecipes(ArrayList<String> ingredients){
        ArrayList <Recipe> recipes = new ArrayList<>();
        ArrayList <String> auxIngr = new ArrayList<>();
        recipes.clear();
        auxIngr.clear();
        
        String query=PREFIXES+"select ?recipe ?name_of_recipe where{";
        for (String ing : ingredients) {
            query += "?recipe dbo:ingredient dbres:" + ing + ".\n";
        }
        query+="?recipe dbpprop:name ?name_of_recipe.\n";
        query+="}";
        QueryExecution qe=QueryExecutionFactory.sparqlService(SERVICE, query);
        ResultSet rs=qe.execSelect();
        while(rs.hasNext()){
            QuerySolution s=rs.nextSolution();
            Resource recipeFound = s.getResource("?recipe");
            Literal nameOfRecipe = s.getLiteral("?name_of_recipe");
            auxIngr = queryIngredientsOfRecipe(recipeFound);
            recipes.add(new Recipe(nameOfRecipe.getString(), auxIngr));
        }
        return recipes;
    }
    public ArrayList <Recipe> queryRecipes(ArrayList<String> ingredients, int hits){
        if(hits > ingredients.size()) return null;
        else if(hits == ingredients.size()) return queryRecipes(ingredients);
        else{
            ArrayList <Recipe> recipes = new ArrayList<>();
            recipes.clear();
            ArrayList <String> tmpIng = new ArrayList<>();
            tmpIng.clear();
            
            /**
             * Code goes here.
             * We use queryRecipes(ArrayList<String> ingredients) with all the 
             * possible combinations of as many ingredients as int hints 
             * indicates.
             */
            
            return recipes;
        }
    }
    /**
     * 
     * @param recipe is a dbresource
     * @return an arrayList of Strings (ingredient names)
     */
    public ArrayList <String> queryIngredientsOfRecipe(Resource recipe){
        ArrayList <String> ingredients = new ArrayList<>();
        ingredients.clear();
        String query=PREFIXES+"select ?ingredient_names where{";
        query += recipe.toString()+" dbo:ingredient ?ingredients.\n"
                    + "?ingredients dbpprop:name ?ingredient_names.\n";
        query+="}";
        QueryExecution qe=QueryExecutionFactory.sparqlService(SERVICE, query);
        ResultSet rs=qe.execSelect();
        while(rs.hasNext()){
            QuerySolution s=rs.nextSolution();
            Literal name=s.getLiteral("?ingredient_names");
            ingredients.add(name.getString());            
        }
        return ingredients;
            
    }
}
