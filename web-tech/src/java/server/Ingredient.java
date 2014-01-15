/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

/**
 * This class is oriented to perform the related meals task.
 * Every ingredient has a counter to indicate how many times it has been loook 
 * for, and this is taken into acount to search for new recipes
 * @author Plasavall
 */
public class Ingredient {
    
    private final int MUST=1;
    private final int CAN=0;
    private final int NOT=-1;
    
    private String ingName;
    private int count;
    private int priority;
    
    
    public Ingredient(String name, int priority){
        this.ingName = name;
        this.count = 0;
        this.priority = priority;
    }
    /**
     * This constructor is developed for when the ingedients priority is not of
     * relevance.
     * @param name 
     */
    public Ingredient(String name){
        this.ingName = name;
        this.count = 0;
        this.priority = CAN;
    }
    /**
     * 
     * @return the String of the ingredient
     */
    public String getIngredientName(){
        return ingName;        
    }
    /**
     * Increments the number of times this ingredient has been introduced
     */
    public void increment(){
        this.count++;
    }
    public int getPriority(){
        return priority;
    }
    public int getCount(){
        return count;
    }
}
