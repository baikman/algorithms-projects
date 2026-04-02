import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;

public class bfs {
    String convertArrayToString(int[][] currentArray){
        String convertedString = "";
        for(int i = 1; i < 7; i++){
            for(int j = 1; j < 7; j++){
                convertedString+= currentArray[i][j] + " ";
            }
        }
        return convertedString;
    }
    int[][] convertStringToArray(String currentString){
        Scanner scan = new Scanner(currentString);
        int[][] convertedArray = new int[7][7];
        for(int i = 0; i < 7; i++){
            convertedArray[0][i] = -1;
            convertedArray[i][0] = -1;
        }
        for(int i = 1; i < 7; i++){
            for(int j = 1; j < 7; j++){
                convertedArray[i][j] = scan.nextInt();
            }
        }
        return convertedArray;
    }
    static int[][] stateArray = new int[7][7];
    static String currentString = "";
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int numCars = scan.nextInt();
        
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                stateArray[i][j] = -1;
            }
        }
        Car[] listOfCars = new Car[numCars];
        for(int i = 0; i < numCars; i++){
            Car newCar = new Car();
            String type = scan.next();
            newCar.length = 3;
            if(type.equals("car")){
                newCar.length = 2;
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
            listOfCars[i] = newCar; 
        }
        for(int carNum = 0; carNum < numCars; carNum++){
            Car currentCar = listOfCars[carNum];
            if(currentCar.isVertical){
                for(int i = 0; i < currentCar.length; i++){
                    stateArray[currentCar.row+i][currentCar.col] = carNum;
                }
            }
            else{
                for(int i = 0; i < currentCar.length; i++){
                    stateArray[currentCar.row][currentCar.col + i] = carNum;
                }
            }
        }
        for(int i = 1; i < 7; i++){
            for(int j = 1; j < 7; j++){
                currentString += stateArray[i][j] + " ";
            }
        }
        System.out.println(currentString);
        
    }
}
