import java.util.List;
import java.util.Scanner;
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
            for(int i = 0; i < l; i++){
                int numDominions = scan.nextInt();
                for(int j = 0; j < numDominions; j++){
                    int newKey = scan.nextInt();
                    Node newNode = Node.makeSet(newKey);
                    array[newKey] = newNode;
                    Node.checkAdjacencies(newNode, n, m, k, array);
                    if(Node.count > 1){
                        numMonths++;
                    }
                }
            }
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
