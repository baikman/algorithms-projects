package sortcomparison;

public class InsertionSort {
    public static void Sort(int[] array){
        for(int j = 1; j <array.length; j++){
            int key = array[j];
            int i = j-1;
            while(i >= 0 && array[i] > key){
                array[i+1] = array[i];
                i--;
            }
            array[i+1] = key;
        }
    }
    public static void main(String[] args){
        int[] a = {0,4,5,6,8,7,4,2};
        Sort(a);
        for(int i = 0; i < a.length; i++){
            System.out.println(a[i]);
        }
    }
}