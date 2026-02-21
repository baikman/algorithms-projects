import java.util.Scanner;

public class bottomup {
    static int numDenom;
    static int[] Denom;
    static CoinPurse[] array;
    static int numProblems;
    static int[] problems;
    static int size = 0;

    static CoinPurse NumCoins(Integer n){
        if (array[n] != null){
            return array[n];
        }
        CoinPurse BestPurse = new CoinPurse();
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
            CoinPurse newPurse = NumCoins(newVal);
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
    public static void main(String[] args) {
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
        array = (CoinPurse[]) new Object[size + 1];
        array[0] = new CoinPurse();
        for(int i = 1; i < size + 2; i++){
            array[i] = null;
        }
    }
}
