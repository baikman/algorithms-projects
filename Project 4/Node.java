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
