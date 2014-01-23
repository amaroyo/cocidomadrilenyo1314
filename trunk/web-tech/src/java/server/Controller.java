/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author Asier
 */
public class Controller {
    
    private ArrayList <User> user;
    private Parser parser;
    private DB db;
    
    public static ArrayList <Recipe> dbpedia;
    
    public Controller () {
        dbpedia = new ArrayList <> ();
        dbpedia.clear();
        
        readFromFile ();
        
        user = new ArrayList ();
        user.clear ();
        
        parser = new Parser ();
        
        db = new DB ();
    }
    
    
    public String putIngredient (String userID, String content) {
        Ingredient ingr;
        ingr = parser.unmarshal(content, false).get(0);
        ingr.setIngredientName(upperCaseFirst(ingr.getIngredientName()));
        int id = Integer.parseInt(userID);
        boolean existence = user.get(id).ingredientExists(ingr);
        //marshal ingredient
        return parser.marshalIngredient(ingr.getIngredientName(), existence);
    }
    
    public void putRecipe (String userID, String content) {
        ArrayList <Ingredient> ingrList;
        ingrList = parser.unmarshal(content, true);
        int id = Integer.parseInt(userID);
        user.get(id).lookFor(ingrList);
    }
    
    public String getRecipe (String userID) {
        int id = Integer.parseInt(userID);
        ArrayList <Recipe> recipes;
        recipes = user.get(id).getRecipes();
        
        return parser.marshalRecipe(recipes);
    }
    
    public String getRecommendations (String userID) {
        int id = Integer.parseInt(userID);
        ArrayList <Recipe> recommendations;
        recommendations = user.get(id).getRecommendations();
        return parser.marshalRecipe(recommendations);
    }
    
    public String getRelatedMeals (String userID) {
        int id = Integer.parseInt(userID);
        ArrayList <Recipe> relatedMeals;
        relatedMeals = user.get(id).getRelatedMeals();
        return parser.marshalRecipe(relatedMeals);
    }
    
    public String newUser () {
        int pos = user.size();
        String content = parser.newUser(pos);
        user.add(new User(pos));
        return content;
    }
    
    
    public String getTops () {
        ArrayList <String> tops = new ArrayList <> ();
        tops = db.getTops();
        return parser.marshalTops(tops);
    }
    
    public void deleteUser (String userID) {
        int id = Integer.parseInt(userID);
        user.remove(id);//save db with this particular user's content?
    }
    
    public void likedRecipe (String content) {
        String recipeName = parser.likedRecipe(content);
        if(!recipeName.equals("")) {
            db.setRecipeName(recipeName);
            db.accessDataBase();
        }
    }
    public String upperCaseFirst(String input){
        String a = input.substring(0, 1).toUpperCase();
        String b = input.substring(1);
        return a.concat(b);
    }

    private void readFromFile() {
        try {
            BufferedReader br = new BufferedReader (new FileReader ("db.csv"));
            String line;
            while((line = br.readLine()) != null) {
                String[] split = line.split("\";\"");
                String name = split[0].replace("\"", "");
                String image = split[1].replace("\"", "");
                String snippet = split[2].replace("\"", "");
                split[3] = split[3].replace("\"", "");
                String[] ingr = split[3].split(",");
                ArrayList <String> tmpIngr = new ArrayList <> ();
                for(String ingred: ingr) 
                    tmpIngr.add(ingred);
                
                dbpedia.add(new Recipe(name, tmpIngr, snippet, image));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}