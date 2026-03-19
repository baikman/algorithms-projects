import java.util.*;

public class Execute {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Node.count = 0;

        int numMonths = 0;

        // n * m * k empire
        int n = scan.nextInt();
        int m = scan.nextInt();
        int k = scan.nextInt();

        // Number of secessions
        int l = scan.nextInt();

        // Arrays for nodes
        Node[] array = new Node[n*m*k];
        int[] unused = new int[n * m * k];

        // Initialize array to be null
        for (int i = 0; i < n * m * k; i++) {
            array[i] = null;
            unused[i] = i;
        }     
        
        // Stack for dominion removal
        Stack<int[]> dominionStack = new Stack<int[]>();

        // Push list of dominions to be removed onto dominionStack
        for (int i = 0; i < l; i++) {
            int numDominions = scan.nextInt();
            int[] storedDominions = new int[numDominions];
            for (int j = 0; j < numDominions; j++) {
                int input = scan.nextInt();
                storedDominions[j] = input;
                unused[input] = -1;
            }
            dominionStack.push(storedDominions);
        }
        
        // TODO: Comment
        for (int val : unused) {
            if (val >= 0) {
                Node newNode = Node.makeSet(val);
                array[val] = newNode;
                Node.checkAdjacencies(newNode, n, m, k, array);
            }
        }

        // Build empire structure from last to leave to first to leave
        while (!dominionStack.isEmpty()) {
            if (Node.count > 1)
                numMonths++;
    
            int[] dominionArray = dominionStack.pop();

            for (int val : dominionArray) {
                Node newNode = Node.makeSet(val);
                array[val] = newNode;
                Node.checkAdjacencies(newNode, n, m, k, array);
            }            
        }
        
        // Print number of months that empire is disconnected
        System.out.println(numMonths);
        scan.close();      
    }
}
