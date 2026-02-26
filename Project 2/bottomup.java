import java.util.Scanner;

public class bottomup {
    static int numDenom;
    static int[] Denom;
    static CoinPurse[] array;
    static int numProblems;
    static int[] problems;
    static int size = 0;

    static CoinPurse NumCoins(Integer n, int max){
        // Check if array[n] is already computed
        if(array[n] != null){
            return array[n];
        }

        // Base case for n = 0
        CoinPurse BestPurse = new CoinPurse(max);
        if(n == 0){
            return BestPurse;
        }

        int bestNum = (int) Double.POSITIVE_INFINITY;
        Integer bestK = 0;
        for(int k = 0; k < numDenom; k++){
            Integer newVal = n - Denom[k];
            if(newVal < 0){
                break;
            }
            CoinPurse newPurse = NumCoins(newVal,max);
            Integer newCoins = newPurse.totalCoins + 1;
            if(newCoins < bestNum){
                bestNum = newCoins;
                bestK = k;
                for(int i = 0; i < numDenom; i++){
                    BestPurse.purse[i] = newPurse.purse[i];
                    BestPurse.totalCoins = newPurse.totalCoins + 1;
                }           
            }
        }
        // NumCoins(n - bestK);
        BestPurse.purse[bestK]++;

        // Best purse stored in array[n]
        array[n] = BestPurse;
        return BestPurse;
    }
    public static void printOutput(int n, int max){
        array = new CoinPurse[n + 1];
        array[0] = new CoinPurse(max);
        for (int i = 1; i <= n; i++) {
            array[i] = NumCoins(i, max);
        }
        CoinPurse total = array[n];

        System.out.print(n + " cents =");
        for(int i = numDenom - 1; i >=0; i --){
            if(total.purse[i] > 0){
                System.out.print(" " + Denom[i] + ":" + total.purse[i]);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Read in number of denominations
        numDenom = scan.nextInt();
        Denom = new int[numDenom];

        // Read denominations
        for(int i = 0; i < numDenom; i++){
            Denom[i] = scan.nextInt();
        }

        // Read number of problems
        numProblems = scan.nextInt();
        problems = new int[numProblems];
        for(int i = 0; i < numProblems; i++){
            // Read target amounts
            problems[i] = scan.nextInt();
            if(size < problems[i]){
                size = problems[i];
            }
        }
        scan.close();

        array =  new CoinPurse[size + 1];
        array[0] = new CoinPurse(size);

        // Compute subproblems
        for(int i = 0; i < size + 1; i++){
            array[i] = NumCoins(i,size);
        }

        // Print results
        for(int i = 0; i < numProblems; i++){
            printOutput(problems[i],size);
        }
    }
}
