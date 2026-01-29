package sortcomparison;

public class MiddleQuickSort {
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
    
        int middle = (first + last) / 2;
        
        //this swap places the middle element at the pivot position
        swap(array, last, middle);
        int pivot = array[last];
        int i = first - 1;

        for(int j = first; j < last; j++){
            if(array[j] <= pivot){
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, last);
        return i + 1;
    }
	
	// Just for testing
    public static void main(String[] args){
        int[] a = {0,4,5,6,8,7,4,2};
        Quicksort(a,0,a.length-1);
        for(int i = 0; i < a.length; i++){
            System.out.println(a[i]);
        }
    }
}
