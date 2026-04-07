/**
* Node
*
* @author Emmett Bicknell, Brandon Aikman
* @version 1.0
* File: Puzzle.java
* Created: April 2026
* Summary of Modifications: First version
* ©Copyright Cedarville University, its Computer Science faculty, and the author.
*
* Description: Node class.
*/
public class Node {
    String key;
    Node parent;
    Integer height;
    String move;

    /**
     * @param newkey Key for node
     * @param newparent Parent of node
     * @param newheight Height of node
     * @param newmove Move for node
     * 
     * Node constructor
     * 
     */
    public Node(String newkey, Node newparent, Integer newheight, String newmove){
        key = newkey;
        parent = newparent;
        height = newheight;
        move = newmove;
    }
    
    /**
     * @param newkey Key for node
     * @param newparent Parent of node
     * 
     * Node constructor
     * 
     */
    public Node(String newkey, Node newparent){
        this(newkey, newparent, newparent.height, null);
    }
}
