import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;
public class Puzzle {
    
    static Boolean testState(String currstate){
        //test to see if red car is at the exit
        return currstate.contains("red35");
    }
    private static void addAdjacencies(int curr, Car[] carArray, boolean[][] occupiedArray, Queue<String> statesToCheck, Map<String, Container> visitedStates){
        Car currentCar = carArray[curr];
        if(currentCar.isVertical){
            int i = currentCar.row-1;
            while(i >= 0){
                if(occupiedArray[i][currentCar.col]){
                    break;
                }
                //in this case, we could move the car up.
                
            }
            
        }
        else{

        }
    }

    public static void main(String[] args){
        Container[][] array = new Container[6][6];
        boolean[][] occupiedArray = new boolean[6][6];
        //input
        Scanner scan = new Scanner(System.in);
        Integer numCars = scan.nextInt();
        Car[] carArray = new Car[numCars];
        String currentString = "";
        Queue<String> statesToCheck = new LinkedList<String>();
        Map<String, Container> visitedStates = new HashMap<String, Container>();
        for(int i = 0; i < numCars; i++){
            Car newCar = new Car();
            String type = scan.next();
            if(type.equals("car")){
                newCar.length = 2;
            }
            else{
                newCar.length = 3;
            }
            newCar.colour = scan.next();
            if(scan.next().equals("h")){
                newCar.isVertical = false;
            }
            else{
                newCar.isVertical = true;
            }
            newCar.row = scan.nextInt();
            newCar.col = scan.nextInt();
            carArray[i] = newCar;
            for(int j =0; j < newCar.length; j++){
                if(newCar.isVertical){
                    occupiedArray[newCar.col - 1][newCar.row - 1+j] = true;
                }
                else{
                    occupiedArray[newCar.col - 1+j][newCar.row - 1] = true;
                }
            }
            currentString = currentString + newCar.colour + Integer.toString(newCar.row) + Integer.toString(newCar.col);
            
        }
        statesToCheck.add(currentString);
        Container newContainer = new Container(currentString, 0);
        visitedStates.put(currentString,newContainer);
        //at this point we've loaded the initial board state. Now, we need to solve it.
        scan.close();
        while(!statesToCheck.isEmpty()){
            String currentState = statesToCheck.remove();
//            System.out.println(currentState);
            if(testState(currentState)){
                break;
            }
            //we haven't solved it yet
            //we need to find all adjacencies and add them to the queue
            for(int i = 0; i < numCars; i++){
                addAdjacencies(i, carArray, occupiedArray, statesToCheck, visitedStates);
            }
        }
    }
}
