/**
* Container
*
* @author Emmett Bicknell, Brandon Aikman
* @version 1.0
* File: Puzzle.java
* Created: April 2026
* Summary of Modifications: First version
* ©Copyright Cedarville University, its Computer Science faculty, and the author.
*
* Description: Container class.
*/
public class Container {
    String key;
    Double distance;
    public Container(String newPredecessor, double newDistance){
        key = newPredecessor;
        distance = newDistance;
    }
    public Container(String newPredecessor){
        this(newPredecessor, Double.POSITIVE_INFINITY);
    }
    public Container(){
        this(null);
    }

}
