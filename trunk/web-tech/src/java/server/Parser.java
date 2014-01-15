/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.io.StringReader;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author Asier
 */
public class Parser {
    
    private final String _LINE_SEPARATOR = "line.separator";
    
    public Parser () {
        
    }
    
    protected ArrayList <Ingredient> unmarshal (String content, boolean hasAttr) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource (new StringReader(content));
            Document document = builder.parse(is);
            
            /* validate xml(content, hasAttr*/
            
            NodeList ingrNodeList = document.getElementsByTagName("INGREDIENTS");
            ArrayList <Ingredient> ingrList = new ArrayList ();
            
            for(int i = 0; i < ingrNodeList.getLength(); i++) {
                Element ingredientElement = (Element) ingrNodeList.item(i);
                
                unmarshalIngredient(ingredientElement, hasAttr, ingrList);
            }
            return ingrList;
        } catch(Exception ex) {
            
        }
        
        return null;
    }
    
    private void unmarshalIngredient (Element ingredientElement, boolean hasPriority, ArrayList <Ingredient> holder) {
        int priority = 0;
        
        if(hasPriority) {
            String priorityValue = ingredientElement.getAttribute("PRIORITY").trim();
            priority = Integer.parseInt(priorityValue);
        }
        NodeList productNodeList = ingredientElement.getElementsByTagName("PRODUCT");
        Node productNode = productNodeList.item(0);
        String product = productNode.getTextContent().trim();
        
        holder.add(new Ingredient(product, priority));
    }
    
    
    protected String marshalRecipe (ArrayList <Recipe> recipes) {
        StringBuilder sb = new StringBuilder ();
        sb.append("<USER>").append(_LINE_SEPARATOR);
        for(int i = 0; i < recipes.size(); i++) {
            Recipe singleRecipe = recipes.get(i);
            sb.append("<RECIPE NAME=\"");
            String recipeName = singleRecipe.getRecipeName();
            sb.append(recipeName).append("\">").append(_LINE_SEPARATOR);
            sb.append("<INGREDIENTS>").append(_LINE_SEPARATOR);
            sb.append(marshalProduct(singleRecipe));
            String snippet = singleRecipe.getRecipeSnippet();
            sb.append("<SNIPPET>").append(snippet).append("</SNIPPET>");
            String imgUrl = singleRecipe.getRecipeImage();
            sb.append("<IMG>").append(imgUrl).append("</IMG>");
            sb.append("</RECIPE>");
        }
        sb.append("</USER>");
        return sb.toString();
    }
        
    private String marshalProduct (Recipe recipe) {
        StringBuilder sb = new StringBuilder();
        ArrayList <String> recipeProducts;
        recipeProducts = recipe.getRecipeIngredients();
        for(String product: recipeProducts) 
            sb.append("<PRODUCT>").append(product).append("</PRODUCT>").append(_LINE_SEPARATOR);
            //<PRODUCT>+product</PRODUCT> LINE SEPARATOR
        sb.append("</INGREDIENTS>");
        return sb.toString();
    }
    
    protected String marshalIngredient (String product, boolean existence) {
        StringBuilder sb = new StringBuilder ();
        sb.append("<USER>").append(_LINE_SEPARATOR);
        sb.append("<INGREDIENTS>").append(_LINE_SEPARATOR);
        sb.append("<PRODUCT>");
        sb.append((existence)?product:"invalid");
        sb.append("</PRODUCT>").append(_LINE_SEPARATOR);
        sb.append("</INGREDIENTS>").append(_LINE_SEPARATOR);
        sb.append("</USER>");
        return sb.toString();
    }
    
    protected String newUser (int id) {
        StringBuilder sb = new StringBuilder ();
        sb.append("<USER>");
        sb.append("<ID>").append(id).append("</ID>");
        sb.append("</USER>");
        return sb.toString();
    }
    
    protected String likedRecipe (String content) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource (new StringReader(content));
            Document document = builder.parse(is);
            
            /* validate xml(content, hasAttr*/
            
            NodeList recipeNodeList = document.getElementsByTagName("RECIPE");
            Node recipeNode = recipeNodeList.item(0);
            String recipeName = recipeNode.getTextContent().trim();
            
            return recipeName;
        } catch(Exception ex) {
            
        }
        
        return "";
    }
    
}
