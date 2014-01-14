    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Server;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;
import java.util.ArrayList;

/**
 * Class to use all the query methods from all the project.
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
        ArrayList <String> nonDesired = getIByP(ingredients,NOT);
        ArrayList <String> mandatory = getIByP(ingredients,MUST);
        recipes.clear();
        
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
            auxRec = new Recipe(nameOfRecipe.getString(), ingredientsOfRecipe(recipeFound), snippetOfRecipe(recipeFound));
            if(!auxRec.hasIngredients(nonDesired) && auxRec.hasIngredients(mandatory)){
                recipes.add(auxRec);
            }
        }
        return recipes;
    }
    /**
     * This method returns the recipes of the result of querying with the combinations
     * of 'hits' ingredients.
     * 
     * @param ingredients
     * @param hits
     * @return 
     */
    public static ArrayList <Recipe> recipes(ArrayList<Ingredient> ingredients, int hits){
        
        if(hits > ingredients.size()) return null;
        if(hits == ingredients.size()) return recipes(ingredients);
        ArrayList <Recipe> recipesFound = new ArrayList<>();
        ArrayList <Ingredient> auxIngr = new ArrayList<>();
        recipesFound.clear();
        if(hits == 1){
            for(int i = 0; i < ingredients.size(); i++){
                auxIngr.clear();
                auxIngr.add(ingredients.get(i));
                recipesFound.addAll(recipes(auxIngr));
            }
            return recipesFound;
        }
        else{
            ArrayList <int []> indexes = giveCombinations(ingredients.size(), hits);
            for(int i = 0; i < indexes.size();i++){
                auxIngr.clear();
                for(int j = 0; j < indexes.get(i).length;j++){
                    auxIngr.add(ingredients.get(indexes.get(i)[j]));
                }
                recipesFound.addAll(recipes(auxIngr));
            }
            return recipesFound;
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
    
    public static String snippetOfRecipe(Resource recipe){
        String snippet = "No description available.";
        String query=PREFIXES+"select ?snippet where{"
                + recipe.toString() + "dbo:abstract ?snippet.\n"
                + "FILTER (langMatches(lang(?snippet),\"en\"))\n"
                + "}";
        QueryExecution qe=QueryExecutionFactory.sparqlService(SERVICE, query);
        ResultSet rs=qe.execSelect();
        while(rs.hasNext()){
            QuerySolution s=rs.nextSolution();
            Literal snipLiteral = s.getLiteral("?snippet");
            snippet = snipLiteral.getString();
        }
        return snippet;
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

    /**
     * It returns the indexes of the ingredients in each combination.
     * 
     * @param elements number of ingredients
     * @param hits number of elements per combination
     * @return 
     */
    private static ArrayList<int[]> giveCombinations(int elements, int hits) {
        
        return null;
    }

}
