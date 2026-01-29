#!/usr/bin/env python3
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import glob
import os

sns.set_style("whitegrid")

def load_latest_csv():
    csv_files = glob.glob('benchmark_results_*.csv')
    if not csv_files:
        print("Error: No benchmark_results_*.csv files found!")
        return None
    latest_file = max(csv_files, key=os.path.getctime)
    print(f"Loading: {latest_file}")
    return pd.read_csv(latest_file)

def main():
    df = load_latest_csv()
    if df is None:
        return
    
    os.makedirs('graphs', exist_ok=True)
    
    # Benchmark 1: Insertion vs Last (100-10k)
    b1 = df[(df['DataType'] == 'RANDOM') & (df['ArraySize'] <= 10000) & (df['Algorithm'].isin(['InsertionSort', 'LastQuickSort']))]
    if not b1.empty:
        b1_avg = b1.groupby(['ArraySize', 'Algorithm'])['TimeMS'].mean().reset_index()
        fig, ax = plt.subplots(figsize=(12, 7))
        for algo in ['InsertionSort', 'LastQuickSort']:
            data = b1_avg[b1_avg['Algorithm'] == algo]
            ax.plot(data['ArraySize'], data['TimeMS'], marker='o', label=algo, linewidth=2.5, markersize=8)
        ax.set_xlabel('Array Size', fontsize=12, fontweight='bold')
        ax.set_ylabel('Time (ms)', fontsize=12, fontweight='bold')
        ax.set_title('Benchmark 1: Insertion Sort vs Last QuickSort (100-10k)', fontsize=13, fontweight='bold')
        ax.legend(fontsize=11)
        ax.grid(True, alpha=0.3)
        plt.tight_layout()
        plt.savefig('graphs/01_insertion_vs_last_100_10k.png', dpi=300, bbox_inches='tight')
        plt.close()
        print("✓ Saved: 01_insertion_vs_last_100_10k.png")
        
        # Benchmark 1 Zoom: Crossover point
        b1_zoom = b1[b1['ArraySize'] <= 400]
        b1_zoom_avg = b1_zoom.groupby(['ArraySize', 'Algorithm'])['TimeMS'].mean().reset_index()
        fig, ax = plt.subplots(figsize=(12, 7))
        for algo in ['InsertionSort', 'LastQuickSort']:
            data = b1_zoom_avg[b1_zoom_avg['Algorithm'] == algo]
            ax.plot(data['ArraySize'], data['TimeMS'], marker='o', label=algo, linewidth=2.5, markersize=8)
        ax.set_xlabel('Array Size', fontsize=12, fontweight='bold')
        ax.set_ylabel('Time (ms)', fontsize=12, fontweight='bold')
        ax.set_title('Benchmark 1 Zoom: Insertion vs Last QuickSort Crossover (0-400)', fontsize=13, fontweight='bold')
        ax.legend(fontsize=11)
        ax.grid(True, alpha=0.3)
        plt.tight_layout()
        plt.savefig('graphs/01b_insertion_vs_last_crossover.png', dpi=300, bbox_inches='tight')
        plt.close()
        print("✓ Saved: 01b_insertion_vs_last_crossover.png")
    
    # Benchmark 2: Insertion vs Last (10-1k)
    b2 = df[(df['DataType'] == 'RANDOM') & (df['ArraySize'] <= 1000) & (df['ArraySize'] >= 10) & (df['Algorithm'].isin(['InsertionSort', 'LastQuickSort']))]
    if not b2.empty:
        b2_avg = b2.groupby(['ArraySize', 'Algorithm'])['TimeMS'].mean().reset_index()
        fig, ax = plt.subplots(figsize=(12, 7))
        for algo in ['InsertionSort', 'LastQuickSort']:
            data = b2_avg[b2_avg['Algorithm'] == algo]
            ax.plot(data['ArraySize'], data['TimeMS'], marker='s', label=algo, linewidth=2.5, markersize=8)
        ax.set_xlabel('Array Size', fontsize=12, fontweight='bold')
        ax.set_ylabel('Time (ms)', fontsize=12, fontweight='bold')
        ax.set_title('Benchmark 2: Insertion Sort vs Last QuickSort (10-1k)', fontsize=13, fontweight='bold')
        ax.legend(fontsize=11)
        ax.grid(True, alpha=0.3)
        plt.tight_layout()
        plt.savefig('graphs/02_insertion_vs_last_10_1k.png', dpi=300, bbox_inches='tight')
        plt.close()
        print("✓ Saved: 02_insertion_vs_last_10_1k.png")
    
    # Benchmark 3: Last vs OnePointer (10k-1M)
    b3 = df[(df['DataType'] == 'RANDOM') & (df['ArraySize'] >= 10000) & (df['Algorithm'].isin(['LastQuickSort', 'TwoPointerQuickSort']))]
    if not b3.empty:
        b3_avg = b3.groupby(['ArraySize', 'Algorithm'])['TimeMS'].mean().reset_index()
        fig, ax = plt.subplots(figsize=(12, 7))
        for algo in ['LastQuickSort', 'TwoPointerQuickSort']:
            data = b3_avg[b3_avg['Algorithm'] == algo]
            ax.plot(data['ArraySize'], data['TimeMS'], marker='o', label=algo, linewidth=2.5, markersize=8)
        ax.set_xlabel('Array Size', fontsize=12, fontweight='bold')
        ax.set_ylabel('Time (ms)', fontsize=12, fontweight='bold')
        ax.set_title('Benchmark 3: Last QuickSort vs OnePointer QuickSort (10k-1M)', fontsize=13, fontweight='bold')
        ax.legend(fontsize=11)
        ax.grid(True, alpha=0.3)
        plt.tight_layout()
        plt.savefig('graphs/03_last_vs_onepointer_10k_1m.png', dpi=300, bbox_inches='tight')
        plt.close()
        print("✓ Saved: 03_last_vs_onepointer_10k_1m.png")
    
    # Benchmark 4: Last vs Middle vs MedianOf3 (10k-1M)
    b4 = df[(df['DataType'] == 'RANDOM') & (df['ArraySize'] >= 10000) & (df['Algorithm'].isin(['LastQuickSort', 'MiddleQuickSort', 'MedianOf3QuickSort']))]
    if not b4.empty:
        b4_avg = b4.groupby(['ArraySize', 'Algorithm'])['TimeMS'].mean().reset_index()
        fig, ax = plt.subplots(figsize=(12, 7))
        for algo in ['LastQuickSort', 'MiddleQuickSort', 'MedianOf3QuickSort']:
            data = b4_avg[b4_avg['Algorithm'] == algo]
            ax.plot(data['ArraySize'], data['TimeMS'], marker='o', label=algo, linewidth=2.5, markersize=8)
        ax.set_xlabel('Array Size', fontsize=12, fontweight='bold')
        ax.set_ylabel('Time (ms)', fontsize=12, fontweight='bold')
        ax.set_title('Benchmark 4: Last vs Middle vs MedianOf3 QuickSort (10k-1M)', fontsize=13, fontweight='bold')
        ax.legend(fontsize=11)
        ax.grid(True, alpha=0.3)
        plt.tight_layout()
        plt.savefig('graphs/04_last_vs_middle_vs_medianof3_10k_1m.png', dpi=300, bbox_inches='tight')
        plt.close()
        print("✓ Saved: 04_last_vs_middle_vs_medianof3_10k_1m.png")
    
    # Benchmark 5: LastQuickSort with different data types (100-10k)
    b5 = df[(df['Algorithm'] == 'LastQuickSort') & (df['ArraySize'] <= 10000) & (df['ArraySize'] >= 100)]
    if not b5.empty:
        b5_avg = b5.groupby(['ArraySize', 'DataType'])['TimeMS'].mean().reset_index()
        fig, ax = plt.subplots(figsize=(12, 7))
        for dtype in ['Forward-Sorted', 'Random', 'Reverse-Sorted']:
            data = b5_avg[b5_avg['DataType'] == dtype]
            ax.plot(data['ArraySize'], data['TimeMS'], marker='o', label=dtype, linewidth=2.5, markersize=8)
        ax.set_xlabel('Array Size', fontsize=12, fontweight='bold')
        ax.set_ylabel('Time (ms)', fontsize=12, fontweight='bold')
        ax.set_title('Benchmark 5: LastQuickSort - Forward vs Random vs Reverse (100-10k)', fontsize=13, fontweight='bold')
        ax.legend(fontsize=11)
        ax.grid(True, alpha=0.3)
        plt.tight_layout()
        plt.savefig('graphs/05_lastqs_data_types_100_10k.png', dpi=300, bbox_inches='tight')
        plt.close()
        print("✓ Saved: 05_lastqs_data_types_100_10k.png")
    
    # Benchmark 3 Zoom: Last vs OnePointer around 250k
    b3_zoom = df[(df['DataType'] == 'RANDOM') & (df['ArraySize'] >= 150000) & (df['ArraySize'] <= 350000) & (df['Algorithm'].isin(['LastQuickSort', 'TwoPointerQuickSort']))]
    if not b3_zoom.empty:
        b3_zoom_avg = b3_zoom.groupby(['ArraySize', 'Algorithm'])['TimeMS'].mean().reset_index()
        fig, ax = plt.subplots(figsize=(12, 7))
        for algo in ['LastQuickSort', 'TwoPointerQuickSort']:
            data = b3_zoom_avg[b3_zoom_avg['Algorithm'] == algo]
            ax.plot(data['ArraySize'], data['TimeMS'], marker='o', label=algo, linewidth=2.5, markersize=8)
        ax.set_xlabel('Array Size', fontsize=12, fontweight='bold')
        ax.set_ylabel('Time (ms)', fontsize=12, fontweight='bold')
        ax.set_title('Benchmark 3 Zoom: Last vs OnePointer QuickSort (150k-350k) - Divergence Point', fontsize=13, fontweight='bold')
        ax.legend(fontsize=11)
        ax.grid(True, alpha=0.3)
        plt.tight_layout()
        plt.savefig('graphs/03b_last_vs_onepointer_zoom_250k.png', dpi=300, bbox_inches='tight')
        plt.close()
        print("✓ Saved: 03b_last_vs_onepointer_zoom_250k.png")
    
    # Benchmark 4 Zoom: Last vs Middle vs MedianOf3 around 250k
    b4_zoom = df[(df['DataType'] == 'RANDOM') & (df['ArraySize'] >= 150000) & (df['ArraySize'] <= 350000) & (df['Algorithm'].isin(['LastQuickSort', 'MiddleQuickSort', 'MedianOf3QuickSort']))]
    if not b4_zoom.empty:
        b4_zoom_avg = b4_zoom.groupby(['ArraySize', 'Algorithm'])['TimeMS'].mean().reset_index()
        fig, ax = plt.subplots(figsize=(12, 7))
        for algo in ['LastQuickSort', 'MiddleQuickSort', 'MedianOf3QuickSort']:
            data = b4_zoom_avg[b4_zoom_avg['Algorithm'] == algo]
            ax.plot(data['ArraySize'], data['TimeMS'], marker='o', label=algo, linewidth=2.5, markersize=8)
        ax.set_xlabel('Array Size', fontsize=12, fontweight='bold')
        ax.set_ylabel('Time (ms)', fontsize=12, fontweight='bold')
        ax.set_title('Benchmark 4 Zoom: Last vs Middle vs MedianOf3 (150k-350k) - Strategy Split', fontsize=13, fontweight='bold')
        ax.legend(fontsize=11)
        ax.grid(True, alpha=0.3)
        plt.tight_layout()
        plt.savefig('graphs/04b_last_vs_middle_vs_medianof3_zoom_250k.png', dpi=300, bbox_inches='tight')
        plt.close()
        print("✓ Saved: 04b_last_vs_middle_vs_medianof3_zoom_250k.png")
    
    # Benchmark 5 Zoom: LastQuickSort data types around 600-700
    b5_zoom = df[(df['Algorithm'] == 'LastQuickSort') & (df['ArraySize'] >= 400) & (df['ArraySize'] <= 900)]
    if not b5_zoom.empty:
        b5_zoom_avg = b5_zoom.groupby(['ArraySize', 'DataType'])['TimeMS'].mean().reset_index()
        fig, ax = plt.subplots(figsize=(12, 7))
        for dtype in ['Forward-Sorted', 'Random', 'Reverse-Sorted']:
            data = b5_zoom_avg[b5_zoom_avg['DataType'] == dtype]
            ax.plot(data['ArraySize'], data['TimeMS'], marker='o', label=dtype, linewidth=2.5, markersize=8)
        ax.set_xlabel('Array Size', fontsize=12, fontweight='bold')
        ax.set_ylabel('Time (ms)', fontsize=12, fontweight='bold')
        ax.set_title('Benchmark 5 Zoom: LastQuickSort Data Types (400-900) - Performance Divergence', fontsize=13, fontweight='bold')
        ax.legend(fontsize=11)
        ax.grid(True, alpha=0.3)
        plt.tight_layout()
        plt.savefig('graphs/05b_lastqs_data_types_zoom_600_700.png', dpi=300, bbox_inches='tight')
        plt.close()
        print("✓ Saved: 05b_lastqs_data_types_zoom_600_700.png")
    
    print("\n✓ All benchmark graphs generated successfully!")

if __name__ == '__main__':
    main()
