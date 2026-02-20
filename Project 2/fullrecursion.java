public class fullrecursion{
    static int numDenom = 4;
    static int[] Denom = {1,7,17,37};
    static int NumCoins(Integer n){
        if(n == 0){
            return 0;
        }
        int bestNum = (int) Double.POSITIVE_INFINITY;
        Integer bestK = 0;
        for(int k = 0; k < numDenom; k++){
            Integer newVal = n - Denom[k];
            if(newVal < 0){
                break;
            }
            Integer newCoins = NumCoins(newVal) + 1;
            if(newCoins < bestNum){
                bestNum = newCoins;
                bestK = k;
            }
        }
        return bestNum;
    }
    public static void main(String[] args) {
        System.out.println(NumCoins(22));
    }
}