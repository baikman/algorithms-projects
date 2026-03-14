public class Node {
    static int count;
    int key;
    int rank;
    Node parent;
    public Node(int i, Node p){
        key=i;
        parent = p;
        rank = 0;
    }
    public Node(int i){
        this(i,null);
        this.parent = this;
    }
    public Node(){

    }
    void setParent(Node a){
        this.parent = a;
    }
    Node findSet(Node current){
        if(current.parent != current){
            current.parent = findSet(current.parent);
        }
        return current.parent;
    }
    void unionSets(Node a, Node b){
        if (a.rank > b.rank){
            (b).setParent(a);
        }
        else{
            if(a.rank == b.rank){
                b.rank ++;
            }
            a.setParent(b);
        }
        count--;
    }
    Node makeSet(int i){
        count++;
        return new Node(i);


        //we then need to check for unions
    }
}
