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
    static Node findSet(Node current){
        if(current == null){
            return null;
        }
        if(current.parent != current){
            current.setParent(findSet(current.parent));
        }
        return current.parent;
    }
    static void unionSets(Node a, Node b){
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
    static Node makeSet(int i){
        count++;
        return new Node(i);


        //we then need to check for unions
    }

    //boolean areAdjacent(Node a, Node b, int n, int m, int k){
    //    int distance = Math.abs(a.key - b.key);
    //    return(distance ==1 || distance == n || distance == n*m);
    //}

    static void checkAdjacencies(Node a, int n, int m, int k, Node[]array){
        int[] adjacencies = findAdjacencies(a, n, m, k);
        Node representative = findSet(a);
        for(int i: adjacencies){
            if(i >= 0 && i < n * m * k){
                Node neighbour = findNode(i, array);
                
                if(neighbour != null && representative != findSet(neighbour)){
                    unionSets(representative, findSet(neighbour));
                }
            }
        }
    }

    static int[] findAdjacencies(Node a, int n, int m, int k){
        int val = a.key;
        int[] values = {val-1,val+1,val-n,val+n,val-(n*m),val+(n*m)};
        if(val % n == 0){
            values[0] = -1;
        }
        if(val % n == n-1){
            values[1] = -1;
        }
        
        if(val < n*m){
            values[4] = -1;
        }
        if(val >= n*m*(k-1)){
            values[5] = -1;
        }
        return values;
    }

    static Node findNode(int n, Node[] array){
        return array[n];
    }
}
