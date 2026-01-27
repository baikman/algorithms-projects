package sortcomparison;

public class MiddleOfThreeQuickSort {
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
    
        int lower = first;
        int upper = last - 1;
        int middle = (first+last) / 2;
        int smallest = first;
        if(array[middle] < array[smallest]){
            smallest = middle;
        }
        if(array[last] < array[smallest]){
            smallest = last;
        }


        //this swap places the element selected to be the pivot at the pivot position
        swap(array, last, smallest);
        int pivot = array[last];

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
        int[] a = {9,4,5,6,8,7,4,2};
        Quicksort(a,0,a.length-1);
        for(int i = 0; i < a.length; i++){
            System.out.println(a[i]);
        }
    }
}
