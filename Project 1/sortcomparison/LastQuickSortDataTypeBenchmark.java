package sortcomparison;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class LastQuickSortDataTypeBenchmark {
    private static final int START_SIZE = 10000;
    private static final int END_SIZE = 1000000;
    private static final int STEP = 10000;
    private static final Random RAND = new Random(42);

    enum DataType {
        FORWARD_SORTED,
        REVERSE_SORTED,
        RANDOM
    }

    public static void main(String[] args) {
        runBenchmark("lastquicksort_datatype_comparison.csv");
        System.out.println("Done. CSV file created.");
    }

    private static void runBenchmark(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("ArraySize,ForwardSortedTimeNs,RandomTimeNs,ReverseSortedTimeNs\n");

            // Warm-up (not timed) to reduce first-run overhead
            int[] warmBase = generateArray(START_SIZE, DataType.RANDOM);
            int[] warmForward = Arrays.copyOf(warmBase, warmBase.length);
            LastQuickSort.Quicksort(warmForward, 0, warmForward.length - 1);
            int[] warmRandom = Arrays.copyOf(warmBase, warmBase.length);
            LastQuickSort.Quicksort(warmRandom, 0, warmRandom.length - 1);
            int[] warmReverse = Arrays.copyOf(warmBase, warmBase.length);
            LastQuickSort.Quicksort(warmReverse, 0, warmReverse.length - 1);

            for (int size = START_SIZE; size <= END_SIZE; size += STEP) {
                // Forward sorted
                int[] forwardArray = generateArray(size, DataType.FORWARD_SORTED);
                long forwardTime = timeLastQuickSort(forwardArray);

                // Random
                int[] randomArray = generateArray(size, DataType.RANDOM);
                long randomTime = timeLastQuickSort(randomArray);

                // Reverse sorted
                int[] reverseArray = generateArray(size, DataType.REVERSE_SORTED);
                long reverseTime = timeLastQuickSort(reverseArray);

                writer.write(size + "," + forwardTime + "," + randomTime + "," + reverseTime + "\n");
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

    private static long timeLastQuickSort(int[] array) {
        long start = System.nanoTime();
        LastQuickSort.Quicksort(array, 0, array.length - 1);
        long end = System.nanoTime();
        return end - start;
    }
}
