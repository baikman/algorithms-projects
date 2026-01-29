package sortcomparison;

import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class main {
    private static final int TRIALS = 5;
    private static final Random rand = new Random(42);

    enum DataType { RANDOM, FORWARD_SORTED, REVERSE_SORTED }

    static int[] generateArray(int size, DataType type) {
        int[] arr = new int[size];
        switch(type) {
            case RANDOM -> { for(int i = 0; i < size; i++) arr[i] = rand.nextInt(10000); }
            case FORWARD_SORTED -> { for(int i = 0; i < size; i++) arr[i] = i; }
            case REVERSE_SORTED -> { for(int i = 0; i < size; i++) arr[i] = size - i; }
        }
        return arr;
    }

    static int[] copyArray(int[] arr) { return Arrays.copyOf(arr, arr.length); }

    static boolean isSorted(int[] arr) {
        for(int i = 0; i < arr.length - 1; i++) if(arr[i] > arr[i+1]) return false;
        return true;
    }

    static long timeSortAlgorithm(int[] arr, String algo) {
        int[] test = copyArray(arr);
        long start = System.nanoTime();
        switch(algo) {
            case "InsertionSort" -> InsertionSort.Sort(test);
            case "LastQuickSort" -> LastQuickSort.Quicksort(test, 0, test.length - 1);
            case "MiddleQuickSort" -> MiddleQuickSort.Quicksort(test, 0, test.length - 1);
            case "MedianOf3QuickSort" -> MiddleOfThreeQuickSort.Quicksort(test, 0, test.length - 1);
            case "TwoPointerQuickSort" -> OnePointerQuicksort.Quicksort(test, 0, test.length - 1);
        }
        long end = System.nanoTime();
        if(!isSorted(test)) { System.err.println("ERROR: " + algo); return -1; }
        return end - start;
    }

    static void writeCSVRecord(FileWriter w, String type, int size, String algo, int trial, double ms) throws IOException {
        w.write(String.format("%s,%d,%s,%d,%.6f\n", type, size, algo, trial, ms));
    }

    static void runBenchmark(FileWriter w, String[] algos, DataType type, int start, int end, int step, String name) throws IOException {
        System.out.println("\n" + name.toUpperCase());
        System.out.println("-".repeat(80));
        System.out.printf("%-15s", "Array Size");
        for(String a : algos) System.out.printf("%-20s", a);
        System.out.println();
        
        for(int size = start; size <= end; size += step) {
            int[] arr = generateArray(size, type);
            System.out.printf("%-15d", size);
            
            for(String algo : algos) {
                long total = 0;
                boolean err = false;
                for(int t = 1; t <= TRIALS; t++) {
                    try {
                        long time = timeSortAlgorithm(arr, algo);
                        if(time == -1) { err = true; break; }
                        total += time;
                        writeCSVRecord(w, type.toString(), size, algo, t, time / 1e6);
                    } catch (Exception e) { err = true; break; }
                }
                double avg = total / (double)TRIALS / 1e6;
                System.out.printf("%-20s", err ? "ERROR" : (avg < 1.0 ? String.format("%.4f", avg) : String.format("%.2f", avg)));
            }
            System.out.println();
        }
    }

    static void runDataTypeBenchmark(FileWriter w, String algo, int start, int end, int step) throws IOException {
        String[] types = {"Forward-Sorted", "Random", "Reverse-Sorted"};
        DataType[] typeEnum = {DataType.FORWARD_SORTED, DataType.RANDOM, DataType.REVERSE_SORTED};
        
        System.out.println("\nBENCHMARK 5: " + algo + " - Forward vs Random vs Reverse (" + start + "-" + end + ", step " + step + ")");
        System.out.println("-".repeat(80));
        System.out.printf("%-15s", "Array Size");
        for(String t : types) System.out.printf("%-20s", t);
        System.out.println();
        
        for(int size = start; size <= end; size += step) {
            System.out.printf("%-15d", size);
            for(int t = 0; t < typeEnum.length; t++) {
                int[] arr = generateArray(size, typeEnum[t]);
                long total = 0;
                boolean err = false;
                for(int trial = 1; trial <= TRIALS; trial++) {
                    try {
                        long time = timeSortAlgorithm(arr, algo);
                        if(time == -1) { err = true; break; }
                        total += time;
                        writeCSVRecord(w, types[t], size, algo, trial, time / 1e6);
                    } catch (Exception e) { err = true; break; }
                }
                double avg = total / (double)TRIALS / 1e6;
                System.out.printf("%-20s", err ? "ERROR" : (avg < 1.0 ? String.format("%.4f", avg) : String.format("%.2f", avg)));
            }
            System.out.println();
        }
    }

    static void quickTest() {
        System.out.println("Running quick verification tests...\n");
        int[] data = {9, 4, 5, 6, 8, 7, 4, 2};
        
        String[] algos = {"InsertionSort", "LastQuickSort", "MiddleQuickSort", "MiddleOfThreeQuickSort"};
        for(String algo : algos) {
            int[] arr = copyArray(data);
            switch(algo) {
                case "InsertionSort" -> InsertionSort.Sort(arr);
                case "LastQuickSort" -> LastQuickSort.Quicksort(arr, 0, arr.length - 1);
                case "MiddleQuickSort" -> MiddleQuickSort.Quicksort(arr, 0, arr.length - 1);
                case "MiddleOfThreeQuickSort" -> MiddleOfThreeQuickSort.Quicksort(arr, 0, arr.length - 1);
            }
            System.out.println(algo + ": " + (isSorted(arr) ? "PASS" : "FAIL"));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        quickTest();
        
        System.out.println("====================================================");
        System.out.println("SORTING ALGORITHM PERFORMANCE ANALYSIS");
        System.out.println("====================================================");
        
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String csv = "benchmark_results_" + now.format(fmt) + ".csv";
        
        try (FileWriter w = new FileWriter(csv)) {
            w.write("DataType,ArraySize,Algorithm,TrialNumber,TimeMS\n");
            
            runBenchmark(w, new String[]{"InsertionSort", "LastQuickSort"}, DataType.RANDOM, 100, 10000, 100, "BENCHMARK 1: Insertion vs Last (100-10k, step 100)");
            runBenchmark(w, new String[]{"InsertionSort", "LastQuickSort"}, DataType.RANDOM, 10, 1000, 10, "BENCHMARK 2: Insertion vs Last (10-1k, step 10)");
            runBenchmark(w, new String[]{"LastQuickSort", "TwoPointerQuickSort"}, DataType.RANDOM, 10000, 1000000, 10000, "BENCHMARK 3: Last vs OnePointer (10k-1M, step 10k)");
            runBenchmark(w, new String[]{"LastQuickSort", "MiddleQuickSort", "MedianOf3QuickSort"}, DataType.RANDOM, 10000, 1000000, 10000, "BENCHMARK 4: Last vs Middle vs MedianOf3 (10k-1M, step 10k)");
            runDataTypeBenchmark(w, "LastQuickSort", 10000, 1000000, 10000);
            
            System.out.println("\n" + "=".repeat(80));
            System.out.println("Benchmark Complete! Results saved to: " + csv);
            System.out.println("=".repeat(80));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
