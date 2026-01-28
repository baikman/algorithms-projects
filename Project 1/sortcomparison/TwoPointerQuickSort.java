package sortcomparison;

public class TwoPointerQuickSort {
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
        int pivot = array[first];
        int left = first + 1;
        int right = last;
        
        while(left <= right) {
            // Find element from left that is >= pivot
            while(left <= right && array[left] < pivot) {
                left++;
            }
            // Find element from right that is <= pivot
            while(left <= right && array[right] > pivot) {
                right--;
            }
            // If pointers haven't crossed, swap
            if(left <= right) {
                swap(array, left, right);
                left++;
                right--;
            }
        }
        
        // Place pivot in correct position
        swap(array, first, right);
        return right;
    }
    
    public static void main(String[] args){
        int[] a = {9,4,5,6,8,7,4,2};
        Quicksort(a,0,a.length-1);
        for(int i = 0; i < a.length; i++){
            System.out.println(a[i]);
        }
    }
}
