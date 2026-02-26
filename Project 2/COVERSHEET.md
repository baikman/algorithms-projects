# CS-3410 Making Change Problem
**Project #2**  
**CS-3410 SP 26**  
**Emmett Bicknell & Brandon Aikman**  
**25 February 2026**

---

## I. Requirements

The project required us to compare the runtime-performance of multiple sorting algorithms across different input sizes. We needed to implement and compare Insertion Sort against various QuickSort pivot selection strategies (Last Element, Middle Element, and Median-of-Three), measuring execution time and generating CSV output for analysis. The benchmark was designed to see expected algorithm performance by reusing the same array for multiple trials and testing with random, forward-sorted, and reverse-sorted data patterns.

## II. Design

Our design implements five scenarios: (1) Insertion vs Last QuickSort on medium-sized arrays (100-10k), (2) Insertion vs Last QuickSort on small arrays (10-1k) to observe crossover points, (3) Last vs OnePointer QuickSort on large arrays (10k-1M), (4) QuickSort pivot comparison (10k-1M), and (5) LastQuickSort behavior across different data orderings. Each test generates arrays once per size and reuses them across multiple algorithm runs to ensure fair comparison.

## III. Security Analysis

This project does not contain any vulnerable operations; it is purely a performance analysis tool without file system writes beyond result logging. We do not have any concerns.

## IV. Implementation

We implemented three different approaches to the making change problem: top-down recursion, recursion with memoization, and bottom-up construction. Each approach uses a similar core loop, but applies it slightly differently. We made a separate file for each strategy.

## V. Testing

We tested using the Gradel page, and found that the files passed all tests. We also did testing of our own. Emmett mainly used the denomination set {1, 7, 17, 37} along with random values to see if it worked as expected, since these values are easy to check by hand. These tests should be sufficient, because we covered several different assortments of denominations, as well as a wide range of values.

## VI. Summary/Conclusion

Our code worked exactly as intended. Brandon did testing on how long each algorithm took. We temporarily inserted time checks into our NumCoins method and tested a range of values. We used the data we found to create our graph. The graph shows that bottum-up and memoization both had runtimes that were $O(n).$ This is to be expected, since NumCoins should only run calculations once for each value from $1$ to $n.$ Hence a constant number of operations performed $n$ times should given $O(n)$ time, as we found.
The runtime for recursion was abysmal. Our graph shows that the runtime gets incredibly large, even for relatively small values of $n.$ This is to be expected, as the number of operations required for a fully recursive algorithm is very large.