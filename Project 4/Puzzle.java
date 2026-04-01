import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;
public class Puzzle {
    
    static Boolean testState(String currstate){
        //test to see if red car is at the exit
        return currstate.contains("red 3 5");
    }
    private static void addAdjacencies(int curr, Car[] carArray, boolean[][] occupiedArray, Queue<String> statesToCheck, Map<String, Container> visitedStates){
        Car currentCar = carArray[curr];
        if(currentCar.isVertical){
            int i = currentCar.row-1;
            while(i >= 0){
                if(occupiedArray[currentCar.col-1][i]){
                    break;
                }
                //in this case, we could move the car up.
                String newState = "";
                for(int k = 0; k < curr; k++){
                    Car tempCar = carArray[k];
                    newState = newState + " " + tempCar.colour + " " +  Integer.toString(tempCar.row) + " " +  Integer.toString(tempCar.col);
                }
                newState = newState + " " + currentCar.colour + " " +  i + " " +  Integer.toString(currentCar.col);
                for(int k = curr+1; k < 6; k++){
                    Car tempCar = carArray[k];
                    newState = newState + " " + tempCar.colour + " " +  Integer.toString(tempCar.row) + " " +  Integer.toString(tempCar.col);
                }
                if(!visitedStates.containsKey(newState)){
                    statesToCheck.add(newState);
                }
                i--;
            }
            i = currentCar.row+1;
            while(i < 6){
                if(occupiedArray[currentCar.col-1][i]){
                    break;
                }
                //in this case, we could move the car up.
                String newState = "";
                for(int k = 0; k < curr; k++){
                    Car tempCar = carArray[k];
                    newState = newState + " " + tempCar.colour + " " +  Integer.toString(tempCar.row) + " " +  Integer.toString(tempCar.col);
                }
                newState = newState + " " + currentCar.colour + " " +  i + " " +  Integer.toString(currentCar.col);
                for(int k = curr+1; k < carArray.length; k++){
                    Car tempCar = carArray[k];
                    newState = newState + " " + tempCar.colour + " " +  Integer.toString(tempCar.row) + " " +  Integer.toString(tempCar.col);
                }
                if(!visitedStates.containsKey(newState)){
                    statesToCheck.add(newState);
                }
                i++;
            }
            
        }
        else{
            int i = currentCar.col-1;
            while(i >= 0){
                if(occupiedArray[i][currentCar.row-1]){
                    break;
                }
                //in this case, we could move the car left.
                String newState = "";
                for(int k = 0; k < curr; k++){
                    Car tempCar = carArray[k];
                    newState = newState + " " + tempCar.colour + " " +  Integer.toString(tempCar.row) + " " +  Integer.toString(tempCar.col);
                }
                newState = newState + " " + currentCar.colour + " " +  Integer.toString(currentCar.row) + " " +  i;
                for(int k = curr+1; k < carArray.length; k++){
                    Car tempCar = carArray[k];
                    newState = newState + " " + tempCar.colour + " " +  Integer.toString(tempCar.row) + " " +  Integer.toString(tempCar.col);
                }
                if(!visitedStates.containsKey(newState)){
                    statesToCheck.add(newState);
                }
                i--;
            }
            i = currentCar.col+1;
            while(i < 6){
                if(occupiedArray[i][currentCar.row-1]){
                    break;
                }
                //in this case, we could move the car up.
                String newState = "";
                for(int k = 0; k < curr; k++){
                    Car tempCar = carArray[k];
                    newState = newState + " " + tempCar.colour + " " +  Integer.toString(tempCar.row) + " " +  Integer.toString(tempCar.col);
                }
                newState = newState + " " + currentCar.colour + " " +  Integer.toString(currentCar.row) + " " +  i;
                for(int k = curr+1; k < carArray.length; k++){
                    Car tempCar = carArray[k];
                    newState = newState + " " + tempCar.colour + " " +  Integer.toString(tempCar.row) + " " +  Integer.toString(tempCar.col);
                }
                if(!visitedStates.containsKey(newState)){
                    statesToCheck.add(newState);
                }
                i++;
            }
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
            currentString = currentString + " " + newCar.colour + " " +  Integer.toString(newCar.row) + " " +  Integer.toString(newCar.col);
            
        }
        statesToCheck.add(currentString);
        Container newContainer = new Container(currentString, 0);
        visitedStates.put(currentString,newContainer);
        //at this point we've loaded the initial board state. Now, we need to solve it.
        scan.close();
        while(!statesToCheck.isEmpty()){
            String currentState = statesToCheck.remove();
            for(int i = 0; i < 6; i++){
                for(int j = 0; j < 6; j++){
                    occupiedArray[i][j] = false;
                }
            }
            for(int i = 0; i < numCars; i++){
                Car newCar = carArray[i];
                for(int j =0; j < newCar.length; j++){
                    if(newCar.isVertical){
                        occupiedArray[newCar.col - 1][newCar.row - 1+j] = true;
                    }
                    else{
                        occupiedArray[newCar.col - 1+j][newCar.row - 1] = true;
                    }
                }
            }   
//            System.out.println(currentState);
            if(testState(currentState)){
                System.out.println("Solution found");
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
