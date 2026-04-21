import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
/**
* Sign
*
* @author Emmett Bicknell, Brandon Aikman
* @version 1.0
* File: Sign.java
* Created: April 2026
* Summary of Modifications: First version
* Copyright Cedarville University, its Computer Science faculty, and the author.
*
* Description: Sign class.
*/
public class Sign {
    int firstIntersection;
    int secondIntersection;
    double distance;
    int cityCount;
    City[] cityArray;
    double[] distanceArray;

    /**
     * @param firstInter First intersection of sign
     * @param secondInter Second intersection of sign
     * @param distance Distance from first intersection
     * 
     * Sign constructor
     * 
     */
    public Sign(int firstInter, int secondInter, double dist, int numCities) {
        firstIntersection = firstInter;
        secondIntersection = secondInter;
        distance = dist;
        cityArray = new City[numCities];
        distanceArray = new double[numCities];
        for (int i = 0; i < numCities; i++) distanceArray[i] = Integer.MAX_VALUE;
    }

    /**
     * 
     * Prints all cities off from sign in order of distance using a HashMap
     * 
     */
    public void printSign() {
        Map<Integer, String> signMap = new HashMap<>();
        for (int i = 0; i < cityCount; i++) {
            signMap.put((int) Math.round(distanceArray[i]), cityArray[i].name);
        }

        Arrays.sort(distanceArray);
        
        for (int i = 0; i < cityCount; i++) {
            System.out.printf("%-20s%d", signMap.get((int) Math.round(distanceArray[i])), (int) Math.round(distanceArray[i]));
            if (i != cityCount - 1) System.out.println();
        }
    }
}
