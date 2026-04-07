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
    public Node(String newkey, Node newparent, Integer newheight, String newmove){
        key = newkey;
        parent = newparent;
        height = newheight;
        move = newmove;
    }
    public Node(String newkey, Node newparent){
        this(newkey, newparent, newparent.height, null);
    }
}
