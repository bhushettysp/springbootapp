package edu.tamuc.project.sort.algorithms;

import java.util.LinkedList;
import java.util.Random;

/*
 * 
 * @author Siva Prasad Bhushetty CWID:50145947
 * 
 * */
public class HeapSort {
	private static int n;

	public static void sort(LinkedList<Integer> linkedList) {
		// int MB = 1024 * 1024;
		if (linkedList == null || linkedList.size() == 0) {
			System.out.println("LinkedList is empty");
			return;
		}
		Runtime runtime = Runtime.getRuntime();
		long allocatedMemoryBefore = runtime.totalMemory();
		heapify(linkedList);
		for (int i = n; i > 0; i--) {
			swap(linkedList, 0, i);
			n--;
			maxHeap(linkedList, 0);
		}
		long allocatedMemoryAfter = runtime.totalMemory();
		System.out.println("Heap Memory --> "
				+ (allocatedMemoryAfter - allocatedMemoryBefore));
	}

	public static void heapify(LinkedList<Integer> list) {
		n = list.size() - 1;
		for (int i = ((n + 1) / 2) - 1; i >= 0; i--) {
			maxHeap(list, i);
		}
	}

	public static void maxHeap(LinkedList<Integer> list, int i) {
		if (i > n / 2) {
			return;
		}
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		int max = i;
		int listLeft = -1;
		if (left <= n) {
			listLeft = ((Integer) list.get(left)).intValue();
		}
		int listRight = -1;
		if (right <= n) {
			listRight = ((Integer) list.get(right)).intValue();
		}
		int listI = ((Integer) list.get(i)).intValue();
		int listMax = ((Integer) list.get(max)).intValue();
		if (left <= n && ((listLeft) > (listI))) {
			max = left;
			listMax = ((Integer) list.get(max)).intValue();
		}
		if (right <= n && listRight > listMax) {
			max = right;
			listMax = ((Integer) list.get(max)).intValue();
		}

		if (max != i) {
			swap(list, i, max);
			maxHeap(list, max);
		}
	}

	public static void swap(LinkedList<Integer> list, int i, int j) {
		int tmp = ((Integer) list.get(i)).intValue();
		int listJ = ((Integer) list.get(j)).intValue();
		// arr[i] = listJ;
		list.set(i, Integer.valueOf(listJ));
		// arr[j] = tmp;
		list.set(j, Integer.valueOf(tmp));
	}

	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		Random r = new Random();
		for (int i = 0; i < 100000; i++) {
			list.add(Integer.valueOf(r.nextInt(1000)));
		}
		System.out.println("Unsorted List --> " + list);
		long startTime = System.nanoTime();// System.currentTimeMillis();
		sort(list);
		long endTime = System.nanoTime();// System.currentTimeMillis();
		System.out.println("Time for Sorting(micro sec) : "
				+ (endTime - startTime) / 1000);
		System.out.println("Sorted List --> " + list);
	}
}
