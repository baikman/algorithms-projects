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
    }

    public void printSign() {
        for (int i = 0; i < cityCount; i++) {
            System.out.println(cityArray[i].name + " " + (int) Math.round(distanceArray[i]));
        }
        System.out.println();
    }
}
