public class fullrecursion{
    static int numDenom = 4;
    static int[] Denom = {1,7,17,37};
    
    static CoinPurse NumCoins(Integer n){
        CoinPurse BestPurse = new CoinPurse();
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
        return BestPurse;
    }
    public static void main(String[] args) {
        CoinPurse totalPurse = NumCoins(22);
        System.out.println("Total Coins: " + totalPurse.totalCoins);
        for(int i = 0; i < numDenom; i ++){
            System.out.println(Denom[i] + ": " + totalPurse.purse[i]);
        }
    }
}