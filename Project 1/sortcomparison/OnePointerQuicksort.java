package sortcomparison;

public class OnePointerQuicksort {
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
    
    /**
     * Two-pointer partition algorithm
     * Pointers start from both ends and move toward each other
     */
    private static int Partition(int[] array, int first, int last){
        int pivot = array[last];
        int lowPointer = first - 1;
        for(int ptr = first; ptr < last; ptr++){
            if(array[ptr] <= pivot){
                lowPointer = lowPointer + 1;
                swap(array, lowPointer, ptr); 
            }
        }
        lowPointer++;
        swap(array, lowPointer, last);
        return lowPointer;
    }
    
	// Just for testing
    public static void main(String[] args){
        int[] a = {9,4,5,6,8,7,4,2};
        Quicksort(a,0,a.length-1);
        for(int i = 0; i < a.length; i++){
            System.out.println(a[i]);
        }
    }
}
