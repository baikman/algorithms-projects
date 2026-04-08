import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
/**
* BFS
*
* @author Emmett Bicknell, Brandon Aikman
* @version 1.0
* File: bfs.java
* Created: April 2026
* Summary of Modifications: First version
* ©Copyright Cedarville University, its Computer Science faculty, and the author.
*
* Description: BFS approach to the Rush Hour puzzle.
*/
public class bfs {
    /**
     * @param currentArray Array to convert to string
     * 
     * Convert array to string
     * 
     */
    static String convertArrayToString (int[][] currentArray) {
        String convertedString = "";
        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 7; j++) {
                convertedString += currentArray[i][j] + " ";
            }
        }
        return convertedString;
    }

    /**
     * @param currentString String to convert to array
     * 
     * Convert string to array
     * 
     */
    static int[][] convertStringToArray(String currentString){
        Scanner scan = new Scanner(currentString);
        int[][] convertedArray = new int[7][7];
        for (int i = 0; i < 7; i++) {
            convertedArray[0][i] = -1;
            convertedArray[i][0] = -1;
        }
        for (int i = 1; i < 7; i++) {
            for(int j = 1; j < 7; j++) {
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
        
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                stateArray[i][j] = -1;
            }
        }
        Car[] listOfCars = new Car[numCars];
        for (int i = 0; i < numCars; i++) {
            Car newCar = new Car();
            String type = scan.next();
            newCar.length = type.equals("car") ? 2 : 3;
            newCar.colour = scan.next();
            type = scan.next();
            newCar.isVertical = type.equals("h") ? false : true;
            newCar.row = scan.nextInt();
            newCar.col = scan.nextInt();
            listOfCars[i] = newCar; 
        }
        scan.close();

        for (int carNum = 0; carNum < numCars; carNum++) {
            Car currentCar = listOfCars[carNum];
            if (currentCar.isVertical) {
                for (int i = 0; i < currentCar.length; i++)
                    stateArray[currentCar.row+i][currentCar.col] = carNum;
            }
            else {
                for (int i = 0; i < currentCar.length; i++)
                    stateArray[currentCar.row][currentCar.col + i] = carNum;
            }
        }
        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 7; j++)
                currentString += stateArray[i][j] + " ";
        }

        // statesToCheck holds the states that we still need to check
        Queue<Node> statesToCheck = new LinkedList<Node>();

        // foundStates stores the states that are adjacent to some state we've visited
        Map<String, String> foundStates = new HashMap<String, String>();
        Node firstNode = new Node(currentString, null, 0, null);

        statesToCheck.add(firstNode);
        foundStates.put(currentString, currentString);

        while (!statesToCheck.isEmpty()) {
            Node currentState = statesToCheck.remove();
            currentString = currentState.key;
            int currentDistance = currentState.height;
            int[][] currentStringArray = convertStringToArray(currentString);

            // Did we reach the end?
            if(currentStringArray[3][6] == 0){
                if(currentDistance == 1)
                    System.out.println("1 move");
                else
                    System.out.println(currentDistance + " moves");

                Stack<String> moveStack = new Stack<>();
                while (currentState.parent != null) {
                    moveStack.push(currentState.move);
                    currentState = currentState.parent;
                }
                while (!moveStack.isEmpty()) System.out.println(moveStack.pop());

                break;
            }
            for(int currentCarNumber = 0; currentCarNumber < numCars; currentCarNumber++){
                // Find where current car is
                int currentrow = 0;
                int currentcol = 0;

                for (int i = 1; i < 7; i++) {
                    Boolean found = false;
                    for (int j = 1; j < 7; j++) {
                        if (currentStringArray[i][j] == currentCarNumber) {
                            found = true;
                            currentrow = i;
                            currentcol = j;
                            break;
                        }
                    }
                    if (found) break;
                }
                // currentrow and currentcol currently store the leftmost or highest end of the car.
                // Now, we need to find where the car can move
                boolean currentVertical = listOfCars[currentCarNumber].isVertical;
                int currentlength = listOfCars[currentCarNumber].length;
                if (currentVertical) {
                    // Car is vertical
                    int[][] tempState = convertStringToArray(currentString);
                    for(int i = 0; i < currentlength; i++){
                        
                        tempState[currentrow+i][currentcol] = -1;
                    }
                    String zeroedString = convertArrayToString(tempState);
                    int i = currentrow - 1;
                    while (i > 0 && tempState[i][currentcol] < 0) {
                        // We may move the car up
                        int[][] addingState = convertStringToArray(zeroedString);
                        for (int j = 0; j < currentlength; j++) {
                            // Move the car
                            addingState[i + j][currentcol] = currentCarNumber;
                        }
                        // Car moved; add it to the map and maybe the queue
                        String newString = convertArrayToString(addingState);
                        if (!foundStates.containsKey(newString)) {
                            // We need to add this to the queue
                            // We create a node containing the key, height, the move, and the parent
                            String move = listOfCars[currentCarNumber].colour + " " + Integer.toString(currentrow - i) + " U";
                            Node nodeToAdd = new Node(newString,currentState,currentDistance+1,move);
                            statesToCheck.add(nodeToAdd);
                        }
                        foundStates.put(newString,newString);
                        i--;
                    }
                    i = currentrow +currentlength;
                    while (i <= 6 && tempState[i][currentcol] < 0) {
                        // We may move the car down
                        int[][] addingState = convertStringToArray(zeroedString);
                        for (int j = 0; j < currentlength; j++) {
                            // Move the car
                            addingState[i + j-currentlength + 1][currentcol] = currentCarNumber;
                        }
                        // Car moved; add it to the map and maybe the queue
                        String newString = convertArrayToString(addingState);
                        if (!foundStates.containsKey(newString)) {
                            // We need to add this to the queue
                            // We create a node containing the key, height, the move, and the parent
                            String move = listOfCars[currentCarNumber].colour + " " + Integer.toString(i - currentlength + 1 - currentrow) + " D";
                            Node nodeToAdd = new Node(newString,currentState,currentDistance+1,move);
                            statesToCheck.add(nodeToAdd);
                        }
                        foundStates.put(newString,newString);
                        i++;
                    }
                }
                else{
                    // In this case, we have a horizontal car
                    int[][] tempState = convertStringToArray(currentString);
                    for (int i = 0; i < currentlength; i++) {
                        
                        tempState[currentrow][currentcol+i] = -1;
                    }
                    String zeroedString = convertArrayToString(tempState);
                    int i = currentcol - 1;
                    while (i > 0 && tempState[currentrow][i] < 0) {
                        // We may move the car
                        int[][] addingState = convertStringToArray(zeroedString);
                        for (int j = 0; j < currentlength; j++) {
                            // Move the car
                            addingState[currentrow][i+j] = currentCarNumber;
                        }
                        // Car moved; add it to the map and maybe the queue
                        String newString = convertArrayToString(addingState);
                        if (!foundStates.containsKey(newString)) {
                            // We need to add this to the queue
                            // We create a node containing the key, height, the move, and the parent
                            String move = listOfCars[currentCarNumber].colour + " " + Integer.toString(currentcol - i) + " L";
                            Node nodeToAdd = new Node(newString,currentState,currentDistance+1,move);
                            statesToCheck.add(nodeToAdd);
                        }
                        foundStates.put(newString,newString);
                        i--;
                    }
                    i = currentcol +currentlength;
                    while (i <= 6 && tempState[currentrow][i] < 0) {
                        // We may move the car
                        int[][] addingState = convertStringToArray(zeroedString);
                        for (int j = 0; j < currentlength; j++) {
                            // Move the car
                            addingState[currentrow][i+j-currentlength + 1] = currentCarNumber;
                        }
                        // Car moved; add it to the map and maybe the queue
                        String newString = convertArrayToString(addingState);
                        if (!foundStates.containsKey(newString)) {
                            // We need to add this to the queue
                            // We create a node containing the key, height, the move, and the parent
                            String move = listOfCars[currentCarNumber].colour + " " + Integer.toString(i - currentlength + 1 - currentcol) + " R";
                            Node nodeToAdd = new Node(newString,currentState,currentDistance+1,move);
                            statesToCheck.add(nodeToAdd);
                        }
                        foundStates.put(newString,newString);
                        i++;
                    }
                }
            }
        }
    }
}