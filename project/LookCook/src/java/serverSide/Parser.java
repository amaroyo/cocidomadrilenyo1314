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
    
    protected void unmarshal (String content) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource (new StringReader(content));
            Document doc = builder.parse(is);
            
            /* validate xml */
            
            NodeList ingList = doc.getElementsByTagName("ingredient");
            ArrayList <Ingredient> ingredients = new ArrayList ();
            for(int i = 0; i < ingList.getLength(); i++) {
                Element ingredient = (Element) ingList.item(i);
                NodeList nameList = ingredient.getElementsByTagName("name");
                Element nameElement = (Element) nameList.item(0);
                String name = nameElement.getTextContent().trim();
                NodeList statusList = ingredient.getElementsByTagName("priority");
                Element statusElement = (Element) statusList.item(0);
                String status = statusElement.getTextContent().trim();
                //ingredients.add(new Ingredient(name, status));
                System.out.println("Ing: "+name+"\nStatus:"+status);
            }
            
            
        } catch (Exception ex) {
            
        }
    }
    
}
