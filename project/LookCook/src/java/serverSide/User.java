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
    private ArrayList <Recipe> recipe; 
    private Recommender recommender;
    private RelatedMeals relatedMeal;
    
    public User (int id) {
        this.id = id;
        
        ingredient = new ArrayList ();
        ingredient.clear ();
        
        recipe = new ArrayList ();
        recipe.clear ();
        
        recommender = new Recommender ();
        relatedMeal = new RelatedMeals ();
        
    }
    
    protected ArrayList getIngredients () {
        
        return ingredient;
    }
    /**
     * Adds the given ingredients to the user's personal list of most used
     * ingredients.
     * 
     * It also updates the recommenders list of ingredients.
     * 
     * @param ingredients 
     * @seeAlso addNewIngredient
     */
    public void addNewIngredients(ArrayList <String> ingredients){
        for(int i = 0; i<ingredients.size(); i++){
            addNewIngredient(ingredients.get(i));
        }
        this.recommender.updateIngredients(this.ingredient);
    }
    /**
     * Adds one ingredient to user's personal list of most used ingredients
     * @param newIngredient 
     */
    public void addNewIngredient(String newIngredient){
        boolean exists = false;
        for(int i = 0; i < this.ingredient.size(); i++){
            if(ingredient.get(i).getIngredient().equals(newIngredient)){
                ingredient.get(i).increment();
                exists = true;
            }
        }
        if(!exists) ingredient.add(new Ingredient(newIngredient));
    }
    /**
     * This method is called every time a client sends new ingredients.
     * @param clientIngredients 
     */
    public void lookFor(ArrayList <String> clientIngredients){
        //First thing we add the new ingredients to the personal list.
        addNewIngredients(clientIngredients);
        //Now we update the recipes section of the user using queryRecipes
        ArrayList <Recipe> recipesFound = new ArrayList<>();
        recipesFound.clear();
        //First we try the query with all the ingredients
        recipesFound = queryRecipes(clientIngredients);
        if(recipesFound == null){
            recipesFound = new ArrayList<>();
            recipesFound.clear();
        }
        //If it doesn't retrieve anything, we try querying combinations of those
        // until we get the recipes that contain the biggest quantity of the 
        //requested ingredients possible.
        int hits = clientIngredients.size()-1;
        if(recipesFound.isEmpty()){
            while(recipesFound.isEmpty()){
                recipesFound = queryRecipes(clientIngredients, hits);
                hits--;
                if(recipesFound == null){
                    recipesFound = new ArrayList<>();
                    recipesFound.clear();
                }
            }
        }
        this.recipe = recipesFound;
        //Now we send to the relatedmeals object the list of ingredients with
        //the starting number of hits to calculate the even smaller combinations 
        //of those ingredients and show them.
        //this.relatedMeal.updateRecipes(clientIngredients, hits);
        updateRelated(clientIngredients, hits);
    }
    
    public void updateRelated(ArrayList <String> ingredients, int startHits){
        ArrayList <Recipe> relatedRecipes = new ArrayList<>();
        relatedRecipes.clear();
        
        for(int hits = startHits; hits>0;hits--){
            //This is supposed to concatenate both ArrayLists.
            relatedRecipes.addAll(queryRecipes(ingredients,hits));
        }
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
    
    
    
    
    //Below this line there are the methods that make direct queries to dbPedia.
    
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
        if(hits == ingredients.size()) return queryRecipes(ingredients);
        ArrayList <Recipe> recipesFound = new ArrayList<>();
        ArrayList <String> auxIngr = new ArrayList<>();
        if(hits == 1){
            recipesFound.clear();
            for(int i = 0; i < ingredients.size(); i++){
                auxIngr.clear();
                auxIngr.add(ingredients.get(i));
                recipesFound.addAll(queryRecipes(auxIngr));
            }
            return recipesFound;
        }
        else{
            ArrayList <Recipe> recipes = new ArrayList<>();
            recipes.clear();
            ArrayList <String> tmpIng = new ArrayList<>();
            tmpIng.clear();
            int i,j;
            for (i = 0; i < ingredients.size() - hits + 1; i++){
             /**
             * Code goes here.
             * We use queryRecipes(ArrayList<String> ingredients) with all the 
             * possible combinations of as many ingredients as int hints 
             * indicates.
             */
            }
            
            //Example of algorithm defined recursively in javascript:
//var i, j, combs(recipes), head, tailcombs, set (ingredients), k (hits);
//            if (k == 1) {
//                    combs = [];
//                    for (i = 0; i < set.length; i++) {
//                            combs.push([set[i]]);
//                    }
//                    return combs;
//            }
//
//            // Assert {1 < k < set.length}
//
//            combs = [];
//            for (i = 0; i < set.length - k + 1; i++) {
//                    head = set.slice(i, i+1);
//                    tailcombs = k_combinations(set.slice(i + 1), k - 1);
//                    for (j = 0; j < tailcombs.length; j++) {
//                            combs.push(head.concat(tailcombs[j]));
//                    }
//            }
//            return combs;
            
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
