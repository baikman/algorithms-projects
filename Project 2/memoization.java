import java.util.Scanner;

public class memoization {
    static int numDenom;
    static int[] Denom;
    static CoinPurse[] array;
    static int numProblems;
    static int[] problems;
   

    static CoinPurse NumCoins(Integer n, int max){
        if (array[n] != null){
            return array[n];
        }
        CoinPurse BestPurse = new CoinPurse(max);
        //if(n == 0){
        //    return BestPurse;
        //}
        int bestNum = (int) Double.POSITIVE_INFINITY;
        Integer bestK = 0;
        for(int k = 0; k < numDenom; k++){
            Integer newVal = n - Denom[k];
            if(newVal < 0){
                break;
            }
            CoinPurse newPurse = NumCoins(newVal, max);
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
        //NumCoins(n - bestK);
        BestPurse.purse[bestK]++;
        array[n] = BestPurse;
        return BestPurse;
    }
     public static void printOutput(int n, int max){
        CoinPurse total = NumCoins(n, max);
        System.out.print(n + " cents =");
        for(int i = numDenom - 1; i >=0; i --){
            if(total.purse[i] > 0){
                System.out.print(" " + Denom[i] + ":" + total.purse[i]);
            }
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int size = 0;
        Scanner scan = new Scanner(System.in);
        numDenom = scan.nextInt();
        Denom = new int[numDenom];
        for(int i = 0; i < numDenom; i++){
            Denom[i] = scan.nextInt();
        }
        numProblems = scan.nextInt();
        problems = new int[numProblems];
        for(int i = 0; i < numProblems; i++){
            problems[i] = scan.nextInt();
            if(size < problems[i]){
                size = problems[i];
            }
        }
        scan.close();
        array =  new CoinPurse[size + 1];
        array[0] = new CoinPurse(size);
        for(int i = 1; i < size + 1; i++){
            array[i] = null;
        }
        for(int i = 0; i < numProblems; i++){
            printOutput(problems[i], size);
        }
    }
}

