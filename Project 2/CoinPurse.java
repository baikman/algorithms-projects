class CoinPurse {
    int totalCoins = 0;
    int[] purse;
    CoinPurse(int n){
        totalCoins = 0;
        purse = new int[n];
        for(int i = 0; i < n; i++){
            purse[i] = 0;
        }
    }
    CoinPurse(){
        this(0);
    }

}
