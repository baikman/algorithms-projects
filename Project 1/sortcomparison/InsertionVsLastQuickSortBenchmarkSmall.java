package sortcomparison;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class InsertionVsLastQuickSortBenchmarkSmall {
    private static final int START_SIZE = 10;
    private static final int END_SIZE = 1000;
    private static final int STEP = 10;
    private static final Random RAND = new Random(42);

    enum DataType {
        FORWARD_SORTED,
        REVERSE_SORTED,
        RANDOM
    }

    public static void main(String[] args) {
        runBenchmark(DataType.FORWARD_SORTED, "insertion_vs_last_forward_sorted_small.csv");
        runBenchmark(DataType.REVERSE_SORTED, "insertion_vs_last_reverse_sorted_small.csv");
        runBenchmark(DataType.RANDOM, "insertion_vs_last_random_small.csv");
        System.out.println("Done. CSV files created.");
    }

    private static void runBenchmark(DataType type, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("ArraySize,InsertionSortTimeNs,LastQuickSortTimeNs\n");

            // Warm-up (not timed) to reduce first-run overhead
            int[] warmBase = generateArray(START_SIZE, type);
            int[] warmInsertion = Arrays.copyOf(warmBase, warmBase.length);
            InsertionSort.Sort(warmInsertion);
            int[] warmQuick = Arrays.copyOf(warmBase, warmBase.length);
            LastQuickSort.Quicksort(warmQuick, 0, warmQuick.length - 1);

            for (int size = START_SIZE; size <= END_SIZE; size += STEP) {
                int[] baseArray = generateArray(size, type);

                // InsertionSort
                int[] insertionArray = Arrays.copyOf(baseArray, baseArray.length);
                long insertionTime = timeInsertionSort(insertionArray);

                // LastQuickSort
                int[] quickArray = Arrays.copyOf(baseArray, baseArray.length);
                long quickTime = timeLastQuickSort(quickArray);

                writer.write(size + "," + insertionTime + "," + quickTime + "\n");
            }
        } catch (IOException e) {
            System.err.println("Failed to write " + fileName + ": " + e.getMessage());
        }
    }

    private static int[] generateArray(int size, DataType type) {
        int[] array = new int[size];
        switch (type) {
            case FORWARD_SORTED:
                for (int i = 0; i < size; i++) {
                    array[i] = i;
                }
                break;
            case REVERSE_SORTED:
                for (int i = 0; i < size; i++) {
                    array[i] = size - i;
                }
                break;
            case RANDOM:
                for (int i = 0; i < size; i++) {
                    array[i] = RAND.nextInt(100000);
                }
                break;
        }
        return array;
    }

    private static long timeInsertionSort(int[] array) {
        long start = System.nanoTime();
        InsertionSort.Sort(array);
        long end = System.nanoTime();
        return end - start;
    }

    private static long timeLastQuickSort(int[] array) {
        long start = System.nanoTime();
        LastQuickSort.Quicksort(array, 0, array.length - 1);
        long end = System.nanoTime();
        return end - start;
    }
}
