package edu.tamuc.project.sort.algorithms;

import java.util.LinkedList;
import java.util.Random;

public class SelectionSort {
	public void sort(LinkedList<Integer> linkedList) {
		if (linkedList == null || linkedList.size() == 0) {
			System.out.println("LinkedList is empty");
			return;
		}
		int n = linkedList.size();
		Runtime runtime = Runtime.getRuntime();
		long allocatedMemoryBefore = runtime.totalMemory();
		int selectionSwap=0;
		for (int i = 0; i < n - 1; i++) {
			int index = i;
			for (int j = i + 1; j < n; j++)
				if (linkedList.get(j) < linkedList.get(index))
					index = j;
			exchangeNumbers(linkedList, index, i);
			selectionSwap++;
		}
		long allocatedMemoryAfter = runtime.totalMemory();
		System.out.println("Bubble Memory --> "
				+ (allocatedMemoryAfter - allocatedMemoryBefore));
		System.out.println("Number of Selection sort Swaps --> "+ selectionSwap);
	}

	private void exchangeNumbers(LinkedList<Integer> list, int i, int j) {
		int temp = ((Integer) list.get(i)).intValue();
		list.set(i, ((Integer) list.get(j)));
		list.set(j, Integer.valueOf(temp));
	}

	public static void main(String a[]) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			list.add(Integer.valueOf(r.nextInt(1000)));
		}
		for (int i = 0; i < list.size(); i++) {
			int element = ((Integer) list.get(i)).intValue();
			System.out.print(element);
			System.out.print(" ");
		}
		System.out.println();
		long startTime = System.nanoTime();// System.currentTimeMillis();
		SelectionSort selectionSort = new SelectionSort();
		selectionSort.sort(list);
		long endTime = System.nanoTime();// System.currentTimeMillis();
		for (int i = 0; i < list.size(); i++) {
			int element = ((Integer) list.get(i)).intValue();
			System.out.print(element);
			System.out.print(" ");
		}
		System.out.println();
		System.out.println("Time for Sorting(micro sec) : "
				+ (endTime - startTime) / 1000);
	}

}
