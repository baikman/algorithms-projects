package sortcomparison;

import java.util.Arrays;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class main {
    // Constants for testing
    private static final int[] SIZES = generateSizes();
    private static final int TRIALS = 5;  // Number of trials per test
    private static final Random rand = new Random(42);  // Seed for reproducibility
    
    /**
     * Generates size array: 100, 500, 1000, 5000, 10000, then 20000-1000000 by 10000 increments
     */
    static int[] generateSizes() {
        java.util.List<Integer> sizes = new java.util.ArrayList<>();
        sizes.add(100);
        sizes.add(500);
        sizes.add(1000);
        sizes.add(5000);
        sizes.add(10000);
        for(int i = 20000; i <= 1000000; i += 10000) {
            sizes.add(i);
        }
        int[] result = new int[sizes.size()];
        for(int i = 0; i < sizes.size(); i++) {
            result[i] = sizes.get(i);
        }
        return result;
    }

    // Data type enum for different sorting scenarios
    enum DataType {
        RANDOM,          // Random unsorted data
        FORWARD_SORTED,  // Already sorted in ascending order
        REVERSE_SORTED   // Sorted in reverse (descending) order
    }

    /**
     * Generates test array based on data type
     */
    static int[] generateArray(int size, DataType type) {
        int[] array = new int[size];
        
        switch(type) {
            case RANDOM:
                for(int i = 0; i < size; i++) {
                    array[i] = rand.nextInt(10000);
                }
                break;
                
            case FORWARD_SORTED:
                for(int i = 0; i < size; i++) {
                    array[i] = i;
                }
                break;
                
            case REVERSE_SORTED:
                for(int i = 0; i < size; i++) {
                    array[i] = size - i;
                }
                break;
        }
        return array;
    }

    /**
     * Creates a copy of an array
     */
    static int[] copyArray(int[] array) {
        return Arrays.copyOf(array, array.length);
    }

    /**
     * Verifies that array is sorted correctly
     */
    static boolean isSorted(int[] array) {
        for(int i = 0; i < array.length - 1; i++) {
            if(array[i] > array[i+1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Runs a single sort and returns time in nanoseconds
     */
    static long timeSortAlgorithm(int[] array, String algorithm) {
        int[] testArray = copyArray(array);
        
        long startTime = System.nanoTime();
        
        // Call the appropriate sort algorithm
        switch(algorithm) {
            case "InsertionSort":
                InsertionSort.Sort(testArray);
                break;
            case "LastQuickSort":
                LastQuickSort.Quicksort(testArray, 0, testArray.length - 1);
                break;
            case "MiddleQuickSort":
                MiddleQuickSort.Quicksort(testArray, 0, testArray.length - 1);
                break;
            case "MedianOf3QuickSort":
                MiddleOfThreeQuickSort.Quicksort(testArray, 0, testArray.length - 1);
                break;
            case "TwoPointerQuickSort":
                TwoPointerQuickSort.Quicksort(testArray, 0, testArray.length - 1);
                break;
        }
        
        long endTime = System.nanoTime();
        
        // Verify correctness
        if(!isSorted(testArray)) {
            System.err.println("ERROR: Array not properly sorted by " + algorithm);
            return -1;
        }
        
        return endTime - startTime;
    }

    /**
     * Runs multiple trials and returns average time in milliseconds
     */
    static double runBenchmark(int[] array, String algorithm, int trials) {
        long totalTime = 0;
        
        for(int i = 0; i < trials; i++) {
            long time = timeSortAlgorithm(array, algorithm);
            if(time == -1) return -1;
            totalTime += time;
        }
        
        return totalTime / (double) trials / 1_000_000;  // Convert to milliseconds
    }

    /**
     * Writes CSV header to file
     */
    static void writeCSVHeader(FileWriter writer) throws IOException {
        writer.write("DataType,ArraySize,Algorithm,TrialNumber,TimeMS\n");
    }

    /**
     * Writes a single CSV record
     */
    static void writeCSVRecord(FileWriter writer, String dataType, int size, String algorithm, int trial, double timeMS) throws IOException {
        writer.write(String.format("%s,%d,%s,%d,%.6f\n", dataType, size, algorithm, trial, timeMS));
    }

    /**
     * Runs a complete benchmark suite with CSV output
     */
    static void runCompleteBenchmark() {
        System.out.println("====================================================");
        System.out.println("SORTING ALGORITHM PERFORMANCE ANALYSIS");
        System.out.println("====================================================\n");

        String[] algorithms = {"InsertionSort", "LastQuickSort", "MiddleQuickSort", "MedianOf3QuickSort", "TwoPointerQuickSort"};
        DataType[] dataTypes = {DataType.RANDOM, DataType.FORWARD_SORTED, DataType.REVERSE_SORTED};
        String[] typeNames = {"Random", "Forward-Sorted", "Reverse-Sorted"};

        // Generate CSV filename with timestamp
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String csvFilename = "benchmark_results_" + now.format(formatter) + ".csv";
        
        try (FileWriter csvWriter = new FileWriter(csvFilename)) {
            writeCSVHeader(csvWriter);
            
            // For each data type
            for(int typeIdx = 0; typeIdx < dataTypes.length; typeIdx++) {
                DataType dataType = dataTypes[typeIdx];
                String typeName = typeNames[typeIdx];
                
                System.out.println("\n" + typeName.toUpperCase() + " DATA:");
                System.out.println("-".repeat(100));
                System.out.printf("%-15s", "Array Size");
                for(String algo : algorithms) {
                    System.out.printf("%-20s", algo);
                }
                System.out.println();
                System.out.println("-".repeat(100));

                // Test each size
                for(int size : SIZES) {
                    int[] testArray = generateArray(size, dataType);
                    System.out.printf("%-15d", size);
                    
                    // InsertionSort is O(n^2), skip after 10k
                    boolean skipInsertion = size > 50000;
                    // Only skip QuickSort variants on sorted data at extreme sizes to avoid worst case
                    boolean skipQSOnSorted = (dataType == DataType.FORWARD_SORTED || dataType == DataType.REVERSE_SORTED) && size > 50000;
                    
                    for(String algo : algorithms) {
                        // Skip InsertionSort after 10k
                        if(skipInsertion && algo.equals("InsertionSort")) {
                            System.out.printf("%-20s", "SKIPPED");
                            continue;
                        }
                        // Skip QuickSort variants only on sorted data at extreme sizes
                        if(skipQSOnSorted && (algo.equals("LastQuickSort") || algo.equals("MedianOf3QuickSort") || algo.equals("TwoPointerQuickSort"))) {
                            System.out.printf("%-20s", "SKIPPED");
                            continue;
                        }
                        
                        // Run trials and record individual times
                        long totalTime = 0;
                        boolean hasError = false;
                        for(int trial = 1; trial <= TRIALS; trial++) {
                            try {
                                long time = timeSortAlgorithm(testArray, algo);
                                if(time == -1) {
                                    hasError = true;
                                    break;
                                }
                                double timeMS = time / 1_000_000.0;
                                totalTime += time;
                                writeCSVRecord(csvWriter, typeName, size, algo, trial, timeMS);
                            } catch (StackOverflowError | Exception e) {
                                System.err.println("\nWarning: " + algo + " failed on " + typeName + " size=" + size);
                                hasError = true;
                                break;
                            }
                        }
                        
                        double avgTime = totalTime / (double) TRIALS / 1_000_000;
                        
                        if(hasError) {
                            System.out.printf("%-20s", "ERROR");
                        } else if(avgTime < 1.0) {
                            System.out.printf("%-20.4f", avgTime);
                        } else if(avgTime < 1000.0) {
                            System.out.printf("%-20.2f", avgTime);
                        } else {
                            System.out.printf("%-20.2f", avgTime);
                        }
                    }
                    System.out.println();
                }
            }
            
            csvWriter.flush();
            System.out.println("\n" + "=".repeat(100));
            System.out.println("Benchmark Complete!");
            System.out.println("Times shown in milliseconds (ms)");
            System.out.println("CSV results saved to: " + csvFilename);
            System.out.println("=".repeat(100));
            
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Quick test to verify algorithms work correctly
     */
    static void quickTest() {
        System.out.println("Running quick verification tests...\n");
        
        int[] testData = {9, 4, 5, 6, 8, 7, 4, 2};
        
        // Test InsertionSort
        int[] arr = copyArray(testData);
        InsertionSort.Sort(arr);
        System.out.println("InsertionSort: " + (isSorted(arr) ? "PASS" : "FAIL"));
        
        // Test LastQuickSort
        arr = copyArray(testData);
        LastQuickSort.Quicksort(arr, 0, arr.length - 1);
        System.out.println("LastQuickSort: " + (isSorted(arr) ? "PASS" : "FAIL"));
        
        // Test MiddleQuickSort
        arr = copyArray(testData);
        MiddleQuickSort.Quicksort(arr, 0, arr.length - 1);
        System.out.println("MiddleQuickSort: " + (isSorted(arr) ? "PASS" : "FAIL"));
        
        // Test MiddleOfThreeQuickSort
        arr = copyArray(testData);
        MiddleOfThreeQuickSort.Quicksort(arr, 0, arr.length - 1);
        System.out.println("MiddleOfThreeQuickSort: " + (isSorted(arr) ? "PASS" : "FAIL"));
        
        System.out.println("\nAll verification tests complete!\n");
    }

    public static void main(String[] args) {
        // Run quick tests first
        quickTest();
        
        // Run full benchmark
        runCompleteBenchmark();
    }
}
