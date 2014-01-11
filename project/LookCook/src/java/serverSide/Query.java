/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serverSide;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;
import java.util.ArrayList;

/**
 *
 * @author Plasavall
 */
public class Query {
    private final static int MUST=1;
    private final static int CAN=0;
    private final static int NOT=-1;
    static String SERVICE = "http://dbpedia.org/sparql";
    static String PREFIXES = "PREFIX dbo:<http://dbpedia.org/ontology/> "
                      +"PREFIX dbres: <http://dbpedia.org/resource/> "
                      +"PREFIX dbpprop: <http://dbpedia.org/property/> ";
    
    

    
    
    /**
     * Queries a list of recipes given a list of ingredients.
     * It's exclusive (Only the recipes containing at least that exact 
     * ingredients are retrieved.
     * @param ingredients
     * @return a ResultSet of recipes containing the given list of ingredients
     */
    public static ArrayList <Recipe> recipes(ArrayList<Ingredient> ingredients){
        String i;
        ArrayList <Recipe> recipes = new ArrayList<>();
        ArrayList <String> auxIngr = new ArrayList<>();
        ArrayList <String> nonDesired = getIByP(ingredients,NOT);
        ArrayList <String> mandatory = getIByP(ingredients,MUST);
        recipes.clear();
        auxIngr.clear();
        
        String query=PREFIXES+"select ?recipe ?name_of_recipe where{";
        for (Ingredient ing : ingredients) {
            i=ing.getIngredientName();
            if(ing.getPriority() != NOT){
                query += "?recipe dbo:ingredient dbres:" + ing + ".\n";
            }
        }
        query+="?recipe dbpprop:name ?name_of_recipe.\n";
        query+="}";
        QueryExecution qe=QueryExecutionFactory.sparqlService(SERVICE, query);
        ResultSet rs=qe.execSelect();
        Recipe auxRec;
        while(rs.hasNext()){
            QuerySolution s=rs.nextSolution();
            Resource recipeFound = s.getResource("?recipe");
            Literal nameOfRecipe = s.getLiteral("?name_of_recipe");
            auxIngr = ingredientsOfRecipe(recipeFound);
            auxRec = new Recipe(nameOfRecipe.getString(), auxIngr);
            if(!auxRec.hasIngredients(nonDesired) && auxRec.hasIngredients(mandatory)){
                recipes.add(auxRec);
            }
        }
        return recipes;
    }
    public static ArrayList <Recipe> recipes(ArrayList<Ingredient> ingredients, int hits){
        
        if(hits > ingredients.size()) return null;
        if(hits == ingredients.size()) return recipes(ingredients);
        ArrayList <Recipe> recipesFound = new ArrayList<>();
        ArrayList <Ingredient> auxIngr = new ArrayList<>();
        if(hits == 1){
            recipesFound.clear();
            for(int i = 0; i < ingredients.size(); i++){
                auxIngr.clear();
                auxIngr.add(ingredients.get(i));
                recipesFound.addAll(recipes(auxIngr));
            }
            return recipesFound;
        }
        else{
            ArrayList <Recipe> recipes = new ArrayList<>();
            recipes.clear();
            ArrayList <Ingredient> tmpIng = new ArrayList<>();
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
    public static ArrayList <String> ingredientsOfRecipe(Resource recipe){
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
    
    
    /**
     * getIByP stands for Get ingredients by priority.
     * It returns a list of the ingredient names in that list that have the
     * given priority
     * @param ingredients
     * @param priority
     * @return 
     */
    private static ArrayList <String> getIByP(ArrayList<Ingredient> ingredients, int priority){
        ArrayList <String> result = new ArrayList<>();
        result.clear();
        for(Ingredient ing : ingredients){
            if(ing.getPriority()==priority) result.add(ing.getIngredientName());
        }
        return result;
    }

}
