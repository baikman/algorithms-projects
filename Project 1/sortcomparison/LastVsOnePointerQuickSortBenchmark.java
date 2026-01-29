package sortcomparison;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class LastVsOnePointerQuickSortBenchmark {
    private static final int START_SIZE = 10000;
    private static final int END_SIZE = 1000000;
    private static final int STEP = 10000;
    private static final Random RAND = new Random(42);

    public static void main(String[] args) {
        runBenchmark("last_vs_onepointer_random.csv");
        System.out.println("Done. CSV file created.");
    }

    private static void runBenchmark(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("ArraySize,LastQuickSortTimeNs,OnePointerQuickSortTimeNs\n");

            // Warm-up (not timed) to reduce first-run overhead
            int[] warmBase = generateRandomArray(START_SIZE);
            int[] warmLast = Arrays.copyOf(warmBase, warmBase.length);
            LastQuickSort.Quicksort(warmLast, 0, warmLast.length - 1);
            int[] warmOnePointer = Arrays.copyOf(warmBase, warmBase.length);
            OnePointerQuicksort.Quicksort(warmOnePointer, 0, warmOnePointer.length - 1);

            for (int size = START_SIZE; size <= END_SIZE; size += STEP) {
                int[] baseArray = generateRandomArray(size);

                int[] lastArray = Arrays.copyOf(baseArray, baseArray.length);
                long lastTime = timeLastQuickSort(lastArray);

                int[] onePointerArray = Arrays.copyOf(baseArray, baseArray.length);
                long onePointerTime = timeOnePointerQuickSort(onePointerArray);

                writer.write(size + "," + lastTime + "," + onePointerTime + "\n");
            }
        } catch (IOException e) {
            System.err.println("Failed to write " + fileName + ": " + e.getMessage());
        }
    }

    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = RAND.nextInt(100000);
        }
        return array;
    }

    private static long timeLastQuickSort(int[] array) {
        long start = System.nanoTime();
        LastQuickSort.Quicksort(array, 0, array.length - 1);
        long end = System.nanoTime();
        return end - start;
    }

    private static long timeOnePointerQuickSort(int[] array) {
        long start = System.nanoTime();
        OnePointerQuicksort.Quicksort(array, 0, array.length - 1);
        long end = System.nanoTime();
        return end - start;
    }
}
