#!/usr/bin/env python3
"""
Partition Algorithm Comparison
Compares 1-pointer vs 2-pointer partitioning methods for QuickSort
"""

import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import glob
import os
from pathlib import Path

sns.set_style("whitegrid")

def load_latest_csv():
    """Load the most recently generated CSV file"""
    csv_files = glob.glob('benchmark_results_*.csv')
    if not csv_files:
        print("Error: No benchmark_results_*.csv files found!")
        return None
    latest_file = max(csv_files, key=os.path.getctime)
    return pd.read_csv(latest_file)

def main():
    df = load_latest_csv()
    if df is None:
        return
    
    os.makedirs('graphs', exist_ok=True)
    os.chdir('graphs')
    
    random_data = df[df['DataType'] == 'Random']
    avg_data = random_data.groupby(['ArraySize', 'Algorithm'])['TimeMS'].mean().reset_index()
    
    # Create partition algorithm comparison
    fig, axes = plt.subplots(2, 2, figsize=(16, 12))
    fig.suptitle('Partition Algorithm Comparison: 1-Pointer vs 2-Pointer', fontsize=16, fontweight='bold')
    
    # Full range - all algorithms
    ax = axes[0, 0]
    quicksort_algos = ['LastQuickSort', 'MiddleQuickSort', 'MedianOf3QuickSort', 'TwoPointerQuickSort']
    for algo in quicksort_algos:
        algo_data = avg_data[avg_data['Algorithm'] == algo]
        ax.plot(algo_data['ArraySize'], algo_data['TimeMS'], 
               marker='o', label=algo, linewidth=2.5, markersize=7)
    
    ax.set_xlabel('Array Size', fontsize=12, fontweight='bold')
    ax.set_ylabel('Time (milliseconds)', fontsize=12, fontweight='bold')
    ax.set_title('All QuickSort Variants (Full Range)', fontsize=12, fontweight='bold')
    ax.legend(fontsize=10)
    ax.grid(True, alpha=0.3)
    
    # 1-pointer variants
    ax = axes[0, 1]
    one_pointer = ['LastQuickSort', 'MiddleQuickSort', 'MedianOf3QuickSort']
    for algo in one_pointer:
        algo_data = avg_data[avg_data['Algorithm'] == algo]
        ax.plot(algo_data['ArraySize'], algo_data['TimeMS'], 
               marker='s', label=algo, linewidth=2.5, markersize=7)
    
    ax.set_xlabel('Array Size', fontsize=12, fontweight='bold')
    ax.set_ylabel('Time (milliseconds)', fontsize=12, fontweight='bold')
    ax.set_title('1-Pointer Partition (Pivot Strategies)', fontsize=12, fontweight='bold')
    ax.legend(fontsize=10)
    ax.grid(True, alpha=0.3)
    
    textstr = 'Last: Pivot at last element\nMiddle: Pivot at middle element\nMedianOf3: Pivot = median of (first, middle, last)'
    props = dict(boxstyle='round', facecolor='lightblue', alpha=0.7)
    ax.text(0.02, 0.98, textstr, transform=ax.transAxes, fontsize=9,
            verticalalignment='top', bbox=props)
    
    # 1-pointer vs 2-pointer on same plot
    ax = axes[1, 0]
    # Average of 1-pointer methods
    one_pointer_avg = avg_data[avg_data['Algorithm'].isin(one_pointer)].groupby('ArraySize')['TimeMS'].mean()
    two_pointer_data = avg_data[avg_data['Algorithm'] == 'TwoPointerQuickSort']
    
    ax.plot(one_pointer_avg.index, one_pointer_avg.values, 
           marker='o', label='1-Pointer (Avg)', linewidth=3, markersize=8, color='#ff7f0e')
    ax.plot(two_pointer_data['ArraySize'], two_pointer_data['TimeMS'], 
           marker='s', label='2-Pointer', linewidth=3, markersize=8, color='#2ca02c')
    
    ax.set_xlabel('Array Size', fontsize=12, fontweight='bold')
    ax.set_ylabel('Time (milliseconds)', fontsize=12, fontweight='bold')
    ax.set_title('1-Pointer (Average) vs 2-Pointer Partition', fontsize=12, fontweight='bold')
    ax.legend(fontsize=11)
    ax.grid(True, alpha=0.3)
    
    # Zoomed in for smaller sizes
    ax = axes[1, 1]
    zoomed_data = avg_data[avg_data['ArraySize'] <= 100000]
    for algo in quicksort_algos:
        algo_data = zoomed_data[zoomed_data['Algorithm'] == algo]
        ax.plot(algo_data['ArraySize'], algo_data['TimeMS'], 
               marker='o', label=algo, linewidth=2.5, markersize=8)
    
    ax.set_xlabel('Array Size', fontsize=12, fontweight='bold')
    ax.set_ylabel('Time (milliseconds)', fontsize=12, fontweight='bold')
    ax.set_title('Zoomed View (Up to 100K) - Asymptotic Behavior', fontsize=12, fontweight='bold')
    ax.legend(fontsize=10)
    ax.grid(True, alpha=0.3)
    
    plt.tight_layout()
    plt.savefig('07_partition_algorithm_comparison.png', dpi=300, bbox_inches='tight')
    print("Saved: 07_partition_algorithm_comparison.png")
    plt.close()
    
    print("\nPartition Algorithm Comparison graph generated successfully!")

if __name__ == '__main__':
    main()
