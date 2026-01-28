# Benchmark Analysis Guide

## Quick Start

```bash
python3 analyze_benchmarks.py
```

This automatically:
1. Loads the most recent `benchmark_results_*.csv`
2. Generates 6 comparison graphs
3. Creates `benchmark_summary.csv` with statistics

## Generated Graphs

All use **linear scales** to clearly show O(n²) vs O(n log n) growth.

### 01_algorithm_comparison_by_datatype.png
Three side-by-side plots showing all 5 algorithms on Random, Forward-Sorted, and Reverse-Sorted data.

### 02_random_data_detailed.png
- **Full range** (100 to 1M): Overall performance trend
- **Zoomed view** (100 to 100K): Crossover point where QuickSort beats Insertion Sort

### 03_insertion_vs_quicksort.png
Direct O(n²) vs O(n log n) comparison showing dramatic performance gap.

### 04_quicksort_variants.png
Compares all 4 QuickSort variants:
- LastQuickSort, MiddleQuickSort, MedianOf3QuickSort (1-pointer partition)
- TwoPointerQuickSort (2-pointer partition)

### 05_data_type_sensitivity.png
Bar charts at size 1,000 showing algorithm sensitivity to input patterns (random, sorted, reverse).

### 06_partition_algorithm_comparison.png
4-panel comparison:
- Top left: All QuickSort variants (full range)
- Top right: 1-pointer methods only (pivot selection)
- Bottom left: 1-pointer average vs 2-pointer
- Bottom right: Zoomed view (up to 100K) showing asymptotic growth

## Summary Statistics

**benchmark_summary.csv** contains:
- Algorithm, ArraySize, mean, std, min, max (milliseconds)
- All random data (sorted data excluded for clarity)

## Key Findings

- **Insertion Sort**: O(n²), efficient up to ~500 elements, tested to 50K
- **QuickSort variants**: O(n log n), dominates above ~1000 elements, tested to 1M
- **Crossover point**: ~500-1000 elements (hardware dependent)
- **Pivot selection > Partitioning**: Middle pivot handling sorted data much better than last
- **1-pointer vs 2-pointer**: Comparable performance, pivot selection more impactful