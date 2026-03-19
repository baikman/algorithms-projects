public class Node {
    static int count;
    int key;
    int rank;
    Node parent;

    /**
     * @param i Node to create
     * @param p Parent of Node
     * 
     * Node Constructor with key and parent
     * 
     */
    public Node(int i, Node p){
        key = i;
        parent = p;
        rank = 0;
    }

    /**
     * @param i Node to create
     * 
     * Node Constructor with key; sets parent to self
     * 
     */
    public Node(int i){
        this(i,null);
        this.parent = this;
    }

    /**
     * 
     * Default Node Constructor
     * 
     */
    public Node(){}

    /**
     * @param a node to set parent to
     * 
     * Sets parent of Node to a
     * 
     */
    void setParent(Node a){
        this.parent = a;
    }

    /**
     * @param i node to create
     * @return Node set of current node
     * 
     * Find set of current node
     */
    static Node findSet(Node current){
        if (current == null)
            return null;

        if(current.parent != current)
            current.setParent(findSet(current.parent));

        return current.parent;
    }

    /**
     * @param a first node to union
     * @param b second node to union
     * 
     * Union two different sets
     * 
     */
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

    /**
     * @param i int to create set on
     * 
     * Create set of i
     * 
     */
    static Node makeSet(int i){
        count++;
        return new Node(i);
    }

    /**
     * @param a
     * @param n
     * @param m
     * @param k
     * @param array
     * 
     * Check for adjacencies
     * 
     */ 
    static void checkAdjacencies(Node a, int n, int m, int k, Node[]array){
        int[] adjacencies = findAdjacencies(a, n, m, k);
        
        for(int i: adjacencies){
            //A negative number means that it's not a valid neighbour
            //if it's bigger than n*m*k - 1, it's too big
            if(i >= 0 && i < n * m * k){
                //store the neighbour
                Node neighbour = array[i];

                //representative is a's current representative
                Node representative = findSet(a);

                //two different valid sets
                if(neighbour != null && representative != findSet(neighbour)){
                    unionSets(representative, findSet(neighbour));
                }
            }
        }
    }

    /**
     * @param a
     * @param n
     * @param m
     * @param k
     * 
     * Find adjacencies
     * Because of the single-dimensional array structure, we must increment/decrement by specific values
     * Incrementing or decrementing by 1 moves in the n direction
     * Incrementing or decrementing by n moves in the m direction
     * Incrementing or decrementing by n*m moves in the k direction
     */ 
    static int[] findAdjacencies(Node a, int n, int m, int k){
        int val = a.key;
        int[] values = {val-1,val+1,val-n,val+n,val-(n*m),val+(n*m)};
        //each if statement checks for an overflow error. If one would occur the value is set to -1 (null)
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
