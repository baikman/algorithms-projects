# Project 1: Sorting Algorithm Benchmarks

**Date**: January 28, 2026  
**Total Records**: 3,425 benchmark samples  
**Test Range**: 100 to 1,000,000 elements

---

## Algorithms Tested

### Insertion Sort
- **Time Complexity**: O(n²) average/worst, O(n) best case
- **Tested To**: 50,000 elements (skipped larger due to O(n²) behavior)
- **Best For**: Small datasets (< 1000 elements)

### QuickSort Variants (1-Pointer Partition)

1. **LastQuickSort** - Pivot: last element
2. **MiddleQuickSort** - Pivot: middle element  
3. **MedianOf3QuickSort** - Pivot: median of (first, middle, last)

### QuickSort Variant (2-Pointer Partition)

4. **TwoPointerQuickSort** - Pivot: first element, 2-pointer partition

---

## Test Configuration

**Array Sizes**: 100 data points
- 100, 500, 1K, 5K, 10K, 20K, 30K, 40K, 50K (Insertion Sort limit)
- Then 60K to 1M in 10K increments (QuickSort only)

**Data Types Tested**:
- **Random**: Unsorted data (full 1M range)
- **Forward-Sorted**: Already ascending (up to 50K)
- **Reverse-Sorted**: Descending order (up to 50K)

**Trials**: 5 runs per configuration  
**Timing**: Java `System.nanoTime()` (sort only, excludes array generation)

---

## Generated Outputs

**6 Comparison Graphs** (linear scale, no log transforms):
1. 01_algorithm_comparison_by_datatype.png - All algorithms, all data types
2. 02_random_data_detailed.png - Full range + zoomed crossover closeup
3. 03_insertion_vs_quicksort.png - O(n²) vs O(n log n) direct comparison
4. 04_quicksort_variants.png - All 4 QuickSort methods on random data
5. 05_data_type_sensitivity.png - Input pattern sensitivity analysis
6. 06_partition_algorithm_comparison.png - 1-pointer vs 2-pointer methods

**Summary Statistics**: benchmark_summary.csv (mean, std, min, max per algorithm/size)

---

## Key Results

### Insertion Sort
- **Small arrays (< 500 elements)**: Actually faster than QuickSort
- **Crossover point**: ~500-1000 elements (where QuickSort becomes better)
- **On sorted data**: Extremely fast O(n) - almost flat line
- **Practical limit**: 50K tested; becomes unusable beyond 10-20K

### QuickSort on Random Data
- **Consistent O(n log n)**: Scales predictably across all sizes
- **All variants**: Within 5-10% of each other on random data
- **Winner**: MiddleQuickSort and MedianOf3QuickSort slightly faster

### Pivot Selection Impact
- **LastQuickSort on sorted data**: Catastrophic - O(n²) worst case
- **MiddleQuickSort on sorted data**: Much better - avoids unbalanced partitions
- **MedianOf3QuickSort**: Best overall robustness
- **Insight**: Pivot selection matters FAR MORE than partition method (1-pointer vs 2-pointer)

### Partition Method Comparison
- **1-Pointer vs 2-Pointer**: Performance within 10-15%
- **Winner**: 1-pointer methods slightly faster on modern CPUs
- **Note**: Differences are small; pivot selection dominates

---

## CSV Format

`benchmark_results_YYYY-MM-DD_HH-mm-ss.csv`

```
DataType,ArraySize,Algorithm,TrialNumber,TimeMS
Random,100,InsertionSort,1,0.0657
Random,100,LastQuickSort,1,0.1166
Forward-Sorted,100,MiddleQuickSort,1,0.1478
```

---

## Conclusions

1. **Theoretical meets practice**: O(n²) vs O(n log n) is visually obvious on linear scales
2. **Pivot selection > partition method**: Choice of pivot dominates performance differences
3. **Data-sensitive**: Algorithm robustness to input patterns varies dramatically
4. **Practical threshold**: Use Insertion Sort up to ~1000 elements, QuickSort beyond
5. **On pre-sorted data**: Middle/median pivots are critical; last pivot is disaster

All linear-scale graphs clearly show asymptotic growth, directly demonstrating the project requirements.
