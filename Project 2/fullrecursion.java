import java.util.Scanner;

public class fullrecursion{
    static int numDenom = 4;
    static int[] Denom = {1,7,17,37};
    static int numProblems;
    static int[] problems;
    static CoinPurse NumCoins(Integer n, int max){
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
        return BestPurse;
    }
    public static void printOutput(int n, int max){
        CoinPurse total = NumCoins(n, max);
        System.out.print(n + " Cents = ");
        for(int i = 0; i < numDenom; i ++){
            if(total.purse[i] > 0){
                System.out.print(Denom[i] + ":" + total.purse[i] + " ");
            }
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int size = 0;
        numDenom = scan.nextInt();
        Denom = new int[numDenom];
        for(int i = 0; i < numDenom; i++){
            Denom[i] = scan.nextInt();
        }
        numProblems = scan.nextInt();
        problems = new int[numProblems];
        for(int i = 0; i < numProblems; i++){
            problems[i] = scan.nextInt();
            if( problems[i] > size){
                size = problems[i];
            }
        }
        scan.close();
        for(int i = 0; i < numProblems; i++){
            printOutput(problems[i], size);
        }
    }
}