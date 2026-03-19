public class Node {
    static int count;
    int key;
    int rank;
    Node parent;

    // Node constructor with key and parent
    public Node(int i, Node p){
        key = i;
        parent = p;
        rank = 0;
    }

    // Node constructor with key (set parent to self)
    public Node(int i){
        this(i,null);
        this.parent = this;
    }

    // Default Node constructor
    public Node(){}

    // Set parent of Node to a
    void setParent(Node a){
        this.parent = a;
    }

    // Find set of current node
    static Node findSet(Node current){
        if (current == null)
            return null;

        if(current.parent != current)
            current.setParent(findSet(current.parent));

        return current.parent;
    }

    // Union sets of two nodes
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

    // Create new set
    static Node makeSet(int i){
        count++;
        return new Node(i);
    }

    // 
    static void checkAdjacencies(Node a, int n, int m, int k, Node[]array){
        int[] adjacencies = findAdjacencies(a, n, m, k);
        
        for(int i: adjacencies){
            if(i >= 0 && i < n * m * k){
                Node neighbour = array[i];
                Node representative = findSet(a);
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
        if(val % (m*n) < n){
            values[2] = -1;
        }
        if(val % (m*n) >= n* (m-1)){
            values[3] = -1;
        }
        if(val < n*m){
            values[4] = -1;
        }
        if(val >= n*m*(k-1)){
            values[5] = -1;
        }
        return values;
    }
}
