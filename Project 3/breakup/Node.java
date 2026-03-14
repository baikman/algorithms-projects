public class Node {
    int key;
    Node parent;
    public Node(int i, Node p){
        key=i;
        parent = p;
    }
    public Node(int i){
        this(i,null);
        this.parent = this;
    }
    public Node(){
        
    }
}
