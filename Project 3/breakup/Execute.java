import java.util.*;

public class Execute {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Node.count = 0;

            int numMonths = 0;
            int n = scan.nextInt();
            int m = scan.nextInt();
            int k = scan.nextInt();
            int l = scan.nextInt();
            Node[] array = new Node[n*m*k];
            Stack<int[]> dominionStack = new Stack<int[]>();
            for(int i = 0; i < l; i++){
                int numDominions = scan.nextInt();
                int[] storedDominions = new int[numDominions];
                for(int j = 0; j < numDominions; j++){
                    storedDominions[j] = scan.nextInt();
                }
                dominionStack.push(storedDominions);
            }
            while(!dominionStack.isEmpty()){
                int[] dominionArray = dominionStack.pop();
                for(int val: dominionArray){
                    Node newNode = Node.makeSet(val);
                    array[val] = newNode;
                    Node.checkAdjacencies(newNode, n, m, k, array);
                }
                if(Node.count > 1){
                        numMonths++;
                }
            }



            
                //FIXME We'll use this
                    //int newKey = scan.nextInt();
                    //Node newNode = Node.makeSet(newKey);
                    //array[newKey] = newNode;
                    //Node.checkAdjacencies(newNode, n, m, k, array);
            //This shows the number of months things were not connected.
            System.out.println(numMonths);
            
            
 //           Node[][][] array = new Node[n][m][l];
   //         ArrayList<Integer>[] monarchies = new ArrayList[l];
 //           for(int j = l-1;  j > -1; j--){
 //               monarchies[j] = new ArrayList<Integer>();
 //               int p = scan.nextInt();
 //               for(int o = 0; o < p; o++){
 //                   monarchies[j].add(scan.nextInt());
 //               }
 //           }
        
        
        
    }
}
