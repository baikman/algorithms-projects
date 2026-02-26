# CS-3410 Making Change Problem
**Project #2**  
**CS-3410 SP 26**  
**Emmett Bicknell & Brandon Aikman**  
**21 November 2025**

---

## I. Requirements

The project required us to compare the runtime-performance of multiple sorting algorithms across different input sizes. We needed to implement and compare Insertion Sort against various QuickSort pivot selection strategies (Last Element, Middle Element, and Median-of-Three), measuring execution time and generating CSV output for analysis. The benchmark was designed to see expected algorithm performance by reusing the same array for multiple trials and testing with random, forward-sorted, and reverse-sorted data patterns.

## II. Design

Our design implements five scenarios: (1) Insertion vs Last QuickSort on medium-sized arrays (100-10k), (2) Insertion vs Last QuickSort on small arrays (10-1k) to observe crossover points, (3) Last vs OnePointer QuickSort on large arrays (10k-1M), (4) QuickSort pivot comparison (10k-1M), and (5) LastQuickSort behavior across different data orderings. Each test generates arrays once per size and reuses them across multiple algorithm runs to ensure fair comparison.

## III. Security Analysis

This project does not contain any vulnerable operations; it is purely a performance analysis tool without file system writes beyond result logging. We do not have any concerns.

## IV. Implementation

We implemented five sorting algorithms in separate Java classes: InsertionSort, LastQuickSort with last-element pivot selection, MiddleQuickSort selecting the middle element as pivot, MedianOf3QuickSort using the median-of-three, and OnePointerQuicksort with a modified partitioning scheme. The main benchmark class runs these comparisons through a series of parameterized test methods that generate arrays, execute sorts multiple times, and record timing data.

## V. Testing

The benchmark outputs both console-formatted average times and individual trial times in CSV format for analysis. We observe expected algorithm behavior: InsertionSort dominates at larger sizes with O(n^2) complexity, while QuickSort variants perform better asymptotically. Notably, in Benchmark 2 (10-1k range) we observed several performance spikes in both algorithms, particularly pronounced in InsertionSort around every 10 increments in array size.

## VI. Summary/Conclusion

This project successfully demonstrated that pivot selection strategy is the dominant factor in QuickSort performance, with the median-of-three and middle element selections providing more stable results than last-element pivot on random data. The crossover point between Insertion Sort and QuickSort occurs around 250 elements, below which simpler algorithms remain competitive despite worse asymptotic complexity. Lastly, the worst-case behavior of last-element pivot on pre-sorted data (forward and reverse) is clearly evident in Benchmark 5, with execution times increasing quadratically compared to random data performance.