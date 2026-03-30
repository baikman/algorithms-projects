import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;
public class Puzzle {
    Container[][] array = new Container[6][6];
    Boolean testState(String currstate){
        //test to see if red car is at the exit
        return true;
        //FIXME needs to be adjusted
    }
    public static void main(String[] args){
        //input
        Scanner scan = new Scanner(System.in);
        Integer numCars = scan.nextInt();

        Queue<String> statesToCheck = new LinkedList<String>();
        Map visitedStates = new HashMap();
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

        }
        scan.close();
    }
}
