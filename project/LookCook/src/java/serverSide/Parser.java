/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serverSide;

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
    
    
    public Parser () {
        
    }
    
    protected ArrayList <Ingredient> unmarshal (String content, boolean hasAttr) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource (new StringReader(content));
            Document document = builder.parse(is);
            
            /* validate xml(content, hasAttr*/
            
            NodeList ingrNodeList = document.getElementsByTagName("INGREDIENT");
            ArrayList <Ingredient> ingrList = new ArrayList ();
            for(int i = 0; i < ingrNodeList.getLength(); i++) {
                Element ingredient = (Element) ingrNodeList.item(i);
                
                unmarshalIngredient(ingredient, hasAttr, ingrList);
            }
            return ingrList;
        } catch(Exception ex) {
            
        }
        
        return null;
    }
    
    private void unmarshalIngredient (Element ingredient, boolean hasPriority, ArrayList <Ingredient> holder) {
        int priority;
        
        if(hasPriority) {
            String priorityValue = ingredient.getAttribute("PRIORITY").trim();
            priority = Integer.parseInt(priorityValue);
        }
        NodeList productNodeList = ingredient.getElementsByTagName("PRODUCT");
        Node productNode = productNodeList.item(0);
        String product = productNode.getTextContent().trim();
        
        holder.add(new Ingredient(product, priority));
    }
    
}
