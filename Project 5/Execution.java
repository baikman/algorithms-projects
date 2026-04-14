import java.util.Scanner;

public class Execution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //n represents the number of intersections
        int n = scan.nextInt();
        //m represents the total number of roads;
        int m = scan.nextInt();
        //k is the number of cities
        int k = scan.nextInt();
        //weightMatrix stores the weights of each road
        Double[][] weightMatrix = new Double[n][n];
        for(int i = 0; i < m; i++){
            int a = scan.nextInt();
            int b = scan.nextInt();
            Double abWeight = scan.nextDouble();
            weightMatrix[a][b] = abWeight;
            weightMatrix[b][a] = abWeight;
        }
        City[] cityArray = new City[k];
        for(int i = 0; i < k; i++){
            int number = scan.nextInt();
            String name = scan.next();
            cityArray[i] = new City(name, number);
        }
        //now we need to find the all-pairs shortest path and also store the signpost locations.
    }
}
