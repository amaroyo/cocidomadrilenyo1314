/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serverSide;

import java.util.ArrayList;

/**
 *
 * @author Asier
 */
public class Controller {
    
    private ArrayList <User> user;
    private Parser parser;
    private Recommender recommender;
    
    public Controller () {
        user = new ArrayList ();
        user.clear ();
        
        parser = new Parser ();
        
        recommender = new Recommender ();
        
    }
    
}
