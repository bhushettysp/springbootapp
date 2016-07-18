package edu.tamuc.project.sort.algorithms;

import java.util.LinkedList;
import java.util.Random;

/*
 * 
 * @author Siva Prasad Bhushetty CWID:50145947
 * 
 * */
public class QuickSort {
	private LinkedList<Integer> list;
	private int length;

	public void sort(LinkedList<Integer> inputArr) {
		if (inputArr == null || inputArr.size() == 0) {
			System.out.println("LinkedList is empty");
			return;
		}
		// +int MB = 1024 * 1024;
		Runtime runtime = Runtime.getRuntime();
		long allocatedMemoryBefore = runtime.totalMemory();
		this.list = inputArr;
		length = inputArr.size();
		int quickSwaps=quickSort(0, length - 1);
		//quickSort(0, length - 1);
		long allocatedMemoryAfter = runtime.totalMemory();
		System.out.println("Quick Memory --> "
				+ (allocatedMemoryAfter - allocatedMemoryBefore));
		System.out.println("Number of Quick sort Swaps --> "+ quickSwaps);
	}

	private int quickSort(int L, int R) {

		int i = L;
		int j = R;
		int pivot = ((Integer) list.get(L + (R - L) / 2)).intValue();/* guessmedian */
		int quickSwaps=0;
		while (i <= j) {
			while (((Integer) list.get(i)).intValue() < pivot) {
				i++;
			}
			while (((Integer) list.get(j)).intValue() > pivot) {
				j--;
			}
			if (i <= j) {
				exchangeNumbers(i, j);/* exchangeNumbers */
				quickSwaps++;
				i++;
				j--;
			}
		}
		if (L < j)
			quickSwaps+=quickSort(L, j); // do partition for lower array //quickSwaps+=quickSort(L, j);
		if (i < R)
			quickSwaps+=quickSort(i, R); // do partition for upper array //quickSwaps+=quickSort(i, R);
		return quickSwaps;
	}

	private void exchangeNumbers(int i, int j) {
		int temp = ((Integer) list.get(i)).intValue();
		list.set(i, ((Integer) list.get(j)));
		list.set(j, Integer.valueOf(temp));
	}

	public static void main(String a[]) {
		QuickSort quickSort = new QuickSort();
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
		quickSort.sort(list);
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
