class CoinPurse {
    int totalCoins = 0;
    int[] purse;
    //a purse that holds coins of n types
    CoinPurse(int n){
        totalCoins = 0;
        purse = new int[n];
        for(int i = 0; i < n; i++){
            purse[i] = 0;
        }
    }
    //we don't use this but it's good to have a default
    CoinPurse(){
        this(0);
    }

}