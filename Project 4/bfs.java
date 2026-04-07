import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;

public class bfs {
    static String convertArrayToString(int[][] currentArray){
        String convertedString = "";
        for(int i = 1; i < 7; i++){
            for(int j = 1; j < 7; j++){
                convertedString+= currentArray[i][j] + " ";
            }
        }
        return convertedString;
    }
    static int[][] convertStringToArray(String currentString){
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
        scan.close();
        return convertedArray;
    }
    
    public static void main(String[] args) {
        int[][] stateArray = new int[7][7];
        String currentString = "";
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
        //statesToCheck hold the states that we still need to check
        Queue<Node> statesToCheck = new LinkedList<Node>();

        //foundStates stores the states that are adjacent to some state we've visited
        Map<String, String> foundStates = new HashMap<String, String>();
        Node firstNode = new Node(currentString, null, 0, null);
        statesToCheck.add(firstNode);
        foundStates.put(currentString, currentString);


        while(!statesToCheck.isEmpty()){
            Node currentState = statesToCheck.remove();
            currentString = currentState.key;
            int currentDistance = currentState.height;
            int[][] currentStringArray = convertStringToArray(currentString);
            if(currentStringArray[3][6] == 0){
                System.out.println("Solution found");
                if(currentDistance == 1){
                    System.out.println("1 move");
                }
                else{
                    System.out.println(currentDistance + " moves");
                }
                break;
            }
            for(int currentCarNumber = 0; currentCarNumber < numCars; currentCarNumber++){
                //we need to find where the current car is
                int currentrow = 0;
                int currentcol = 0;
                for(int i = 1; i < 7; i++){
                    Boolean found = false;
                    for(int j = 1; j < 7; j++){
                        if(currentStringArray[i][j] == currentCarNumber){
                            found = true;
                            currentrow = i;
                            currentcol = j;
                            break;
                        }
                    }
                    if(found){
                        break;
                    }
                }
                //currentrow and currentcol currently store the leftmost or highest end of the car.
                //now we need to find where the car can move
                boolean currentVertical = listOfCars[currentCarNumber].isVertical;
                int currentlength = listOfCars[currentCarNumber].length;
                if(currentVertical){
                    //the car is currently vertical
                    int[][] tempState = convertStringToArray(currentString);
                    for(int i = 0; i < currentlength; i++){
                        
                        tempState[currentrow+i][currentcol] = -1;
                    }
                    String zeroedString = convertArrayToString(tempState);
                    int i = currentrow - 1;
                    while(i > 0 && tempState[i][currentcol] < 0){
                        //we may move the car up
                        int[][] addingState = convertStringToArray(zeroedString);
                        for(int j = 0; j < currentlength; j++){
                            //move the car
                            addingState[i + j][currentcol] = currentCarNumber;
                        }
                        //car moved; add it to the map and maybe the queue
                        String newString = convertArrayToString(addingState);
                        if(!foundStates.containsKey(newString)){
                            //we need to add this to the queue
                            //we create a node containing the key, height, the move, and the parent
                            Node nodeToAdd = new Node(newString,currentState,currentDistance+1,"");
                            statesToCheck.add(nodeToAdd);
                            //System.out.println("Adding state" + newString);
                        }
                        foundStates.put(newString,newString);
                        i--;
                    }
                    i = currentrow +currentlength;
                    while(i <= 7-currentlength && tempState[i][currentcol] < 0){
                        //we may move the car down
                        int[][] addingState = convertStringToArray(zeroedString);
                        for(int j = 0; j < currentlength; j++){
                            //move the car
                            addingState[i + j-currentlength + 1][currentcol] = currentCarNumber;
                        }
                        //car moved; add it to the map and maybe the queue
                        String newString = convertArrayToString(addingState);
                        if(!foundStates.containsKey(newString)){
                            //we need to add this to the queue
                            //we create a node containing the key, height, the move, and the parent
                            Node nodeToAdd = new Node(newString,currentState,currentDistance+1,"");
                            statesToCheck.add(nodeToAdd);
                            //System.out.println("Adding state" + newString);
                        }
                        foundStates.put(newString,newString);
                        i++;
                    }
                }

                else{
                    //in this case, we have a horizontal car
                    int[][] tempState = convertStringToArray(currentString);
                    for(int i = 0; i < currentlength; i++){
                        
                        tempState[currentrow][currentcol+i] = -1;
                    }
                    String zeroedString = convertArrayToString(tempState);
                    int i = currentcol - 1;
                    while(i > 0 && tempState[currentrow][i] < 0){
                        //we may move the car up
                        int[][] addingState = convertStringToArray(zeroedString);
                        for(int j = 0; j < currentlength; j++){
                            //move the car
                            addingState[currentrow][i+j] = currentCarNumber;
                        }
                        //car moved; add it to the map and maybe the queue
                        String newString = convertArrayToString(addingState);
                        if(!foundStates.containsKey(newString)){
                            //we need to add this to the queue
                            //we create a node containing the key, height, the move, and the parent
                            Node nodeToAdd = new Node(newString,currentState,currentDistance+1,"");
                            statesToCheck.add(nodeToAdd);
                            //System.out.println("Adding state" + newString);
                        }
                        foundStates.put(newString,newString);
                        
                        i--;
                    }
                    i = currentcol +currentlength;
                    while(i <= 7-currentlength && tempState[currentrow][i] < 0){
                        //we may move the car down
                        int[][] addingState = convertStringToArray(zeroedString);
                        for(int j = 0; j < currentlength; j++){
                            //move the car
                            addingState[currentrow][i+j-currentlength + 1] = currentCarNumber;
                        }
                        //car moved; add it to the map and maybe the queue
                        String newString = convertArrayToString(addingState);
                        if(!foundStates.containsKey(newString)){
                            //we need to add this to the queue
                            //we create a node containing the key, height, the move, and the parent
                            Node nodeToAdd = new Node(newString,currentState,currentDistance+1,"");
                            statesToCheck.add(nodeToAdd);
                            //System.out.println("Adding state" + newString);
                        }
                        foundStates.put(newString,newString);
                        i++;
                    }
                }
            }
        }
    }
}
