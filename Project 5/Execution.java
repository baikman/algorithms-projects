import java.util.Scanner;
/**
* Execution
*
* @author Emmett Bicknell, Brandon Aikman
* @version 1.0
* File: Execution.java
* Created: April 2026
* Summary of Modifications: First version
* Copyright Cedarville University, its Computer Science faculty, and the author.
*
* Description: Execution class. Equations and page numbers
* listed are from the 4th Edition of Introduction to Algorithms by CLRS.
*/
public class Execution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();                         // n represents the number of intersections (vertices)
        int m = scan.nextInt();                         // m represents the total number of roads
        int k1 = scan.nextInt();                        // k1 is the number of cities
        double[][] weightMatrix = new double[n][n];     // weightMatrix stores the weights of each road
        int[][] predecessorMatrix = new int[n][n];      // predecessorMatrix stores predecessors
        
        // Set all of weightMatrix and predecessorMatrix to "infinity"
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                weightMatrix[i][j] = (i == j) ? 0 : Double.MAX_VALUE;
                predecessorMatrix[i][j] = Integer.MAX_VALUE;
            }
        }

        // Set weightMatrix^(0) and predecessorMatrix^(0)
        for (int i = 0; i < m; i++) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            double abWeight = scan.nextDouble();

            weightMatrix[a][b] = abWeight;
            weightMatrix[b][a] = abWeight;

            predecessorMatrix[a][b] = a;                // Eqn. 23.7
            predecessorMatrix[b][a] = b;
        }

        City[] cityArray = new City[k1];                // cityArray stores all cities

        for (int i = 0; i < k1; i++) {
            int index = scan.nextInt();
            String name = scan.next();
            cityArray[i] = new City(name, index);
        }

        int s = scan.nextInt();                         // s represents the number of signs
        Sign[] signs = new Sign[s];                     // signs stores signs
        for (int i = 0; i < s; i++) {
            int firstInter = scan.nextInt();
            int secondInter = scan.nextInt();
            double dist = scan.nextDouble();
            signs[i] = new Sign(firstInter, secondInter, dist, k1);
        }
        
        scan.close();

        // Modified Floyd-Warshall implementation of APSP which includes the predecessorMatrix (p657)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    double min = Math.min(weightMatrix[i][j], weightMatrix[i][k] + weightMatrix[k][j]); // Eqn. 23.6
                    if (weightMatrix[i][j] > weightMatrix[i][k] + weightMatrix[k][j]) {
                        predecessorMatrix[i][j] = predecessorMatrix[k][j]; // Eqn. 23.8
                    }
                    weightMatrix[i][j] = min;
                }
            }
        }

        for (int i = 0; i < s; i++) {
            Sign sign = signs[i];
            int firstInter = sign.firstIntersection;
            int secondInter = sign.secondIntersection;
            int cityCount = 0;                          // cityCount tracks the amount of cities posted on a sign

            for (int j = 0; j < k1; j++) {
                City city = cityArray[j];               // city which we evaluate adding to sign

                // If path exists from the first intersection to the city
                if ((predecessorMatrix[firstInter][city.index] != Integer.MAX_VALUE)) {
                    int temp = city.index;
                    int predecessor = predecessorMatrix[firstInter][temp];

                    // If road is part of optimal path, add to sign
                    if (predecessorMatrix[predecessor][firstInter] == secondInter || secondInter == temp) {
                        sign.cityArray[cityCount] = city;
                        // Distance is shortest path from firstInter to city minus the distance of the sign from firstInter
                        sign.distanceArray[cityCount] = weightMatrix[firstInter][city.index] - sign.distance;
                        sign.cityCount = ++cityCount; // Pre-increment and update cityCount
                    }
                }
            }
        }
        
        // Print signs
        for (Sign sign : signs) {
            sign.printSign();
            if (sign != signs[signs.length - 1]) System.out.print("\n\n");
        }
    }
}