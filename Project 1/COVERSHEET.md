# CS-3410 Sorting Algorithm Performance
**Project #1**  
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

The benchmark outputs individual trial times in CSV format for analysis. We observe expected algorithm behavior: InsertionSort 

## VI. Summary/Conclusion



## VII. AI Usage

AI was used to generate the test method structure and CSV output formatting, which helped with the speed of the assignment. Additionally, we used AI to help us debug our implementations of the sorting algorithms