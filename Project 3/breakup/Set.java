public class Set {
    private int rank;
    private Node representative;
    int getRank(){
        return rank;
    }
    public Set(int i){
        representative = new Node(i);
    }
    public Set(){
        
    }
    Set makeSet(int i){
        return new Set(i);

        //we then need to check for unions
    }
    Node findSet(Node current){
        if(current.parent != current){
            current.parent = findSet(current.parent);
        }
        return current.parent;
    }
    
}
