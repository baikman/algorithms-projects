package sortcomparison;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class LastMiddleMiddleOfThreeQuickSortBenchmark {
    private static final int START_SIZE = 10000;
    private static final int END_SIZE = 1000000;
    private static final int STEP = 10000;
    private static final Random RAND = new Random(42);

    public static void main(String[] args) {
        runBenchmark("last_middle_middleofthree_random.csv");
        System.out.println("Done. CSV file created.");
    }

    private static void runBenchmark(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("ArraySize,LastQuickSortTimeNs,MiddleQuickSortTimeNs,MiddleOfThreeQuickSortTimeNs\n");

            // Warm-up (not timed) to reduce first-run overhead
            int[] warmBase = generateRandomArray(START_SIZE);
            int[] warmLast = Arrays.copyOf(warmBase, warmBase.length);
            LastQuickSort.Quicksort(warmLast, 0, warmLast.length - 1);
            int[] warmMiddle = Arrays.copyOf(warmBase, warmBase.length);
            MiddleQuickSort.Quicksort(warmMiddle, 0, warmMiddle.length - 1);
            int[] warmMiddleOfThree = Arrays.copyOf(warmBase, warmBase.length);
            MiddleOfThreeQuickSort.Quicksort(warmMiddleOfThree, 0, warmMiddleOfThree.length - 1);

            for (int size = START_SIZE; size <= END_SIZE; size += STEP) {
                int[] baseArray = generateRandomArray(size);

                int[] lastArray = Arrays.copyOf(baseArray, baseArray.length);
                long lastTime = timeLastQuickSort(lastArray);

                int[] middleArray = Arrays.copyOf(baseArray, baseArray.length);
                long middleTime = timeMiddleQuickSort(middleArray);

                int[] middleOfThreeArray = Arrays.copyOf(baseArray, baseArray.length);
                long middleOfThreeTime = timeMiddleOfThreeQuickSort(middleOfThreeArray);

                writer.write(size + "," + lastTime + "," + middleTime + "," + middleOfThreeTime + "\n");
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

    private static long timeMiddleQuickSort(int[] array) {
        long start = System.nanoTime();
        MiddleQuickSort.Quicksort(array, 0, array.length - 1);
        long end = System.nanoTime();
        return end - start;
    }

    private static long timeMiddleOfThreeQuickSort(int[] array) {
        long start = System.nanoTime();
        MiddleOfThreeQuickSort.Quicksort(array, 0, array.length - 1);
        long end = System.nanoTime();
        return end - start;
    }
}
