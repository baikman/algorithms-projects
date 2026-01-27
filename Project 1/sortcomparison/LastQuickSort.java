package sortcomparison;
public class LastQuickSort {
    static void Quicksort(int[] array, int first, int last){
        if(first < last){
            int mid = Partition(array, first, last);
            Quicksort(array, first, mid-1);
            Quicksort(array, mid+1, last);
        }
    }
    private static void swap(int[] array, int a, int b){
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
    private static int Partition(int[] array, int first, int last){
        int pivot = array[last];
        int lower = first;
        int upper = last - 1;

        while(lower < upper){
            while(lower <= upper && array[upper] >= pivot){
                upper--;
            }
            while(lower <= upper && array[lower] <= pivot){
                lower++;
            }
            if(lower < upper){
                swap(array, lower, upper);
            }
        }
        swap(array, lower, last);
        return lower;
    }
    public static void main(String[] args){
        int[] a = {0,4,5,6,8,7,4,2};
        Quicksort(a,0,a.length-1);
        for(int i = 0; i < a.length; i++){
            System.out.println(a[i]);
        }
    }
}