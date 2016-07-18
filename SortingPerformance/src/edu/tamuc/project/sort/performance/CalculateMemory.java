package edu.tamuc.project.sort.performance;

import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.Random;

public class CalculateMemory {
	public static void main(String args[]){
		// Creating 5 linked lists
		LinkedList<Integer> list1 = new LinkedList<Integer>();
		LinkedList<Integer> list2 = new LinkedList<Integer>();
		LinkedList<Integer> list3 = new LinkedList<Integer>();
		LinkedList<Integer> list4 = new LinkedList<Integer>();
		LinkedList<Integer> list5 = new LinkedList<Integer>();
		Random r = new Random();
		Runtime runtime = Runtime.getRuntime();
		NumberFormat format = NumberFormat.getInstance();
		StringBuilder sb = new StringBuilder();
		int mb = 1024 * 1024;
		long freeMemory = runtime.freeMemory();
		long maxMemory = runtime.maxMemory();
		long allocatedMemoryBefore = runtime.totalMemory();
		for (int i = 0; i < 1000000; i++) {
			list1.add( r.nextInt(1000));
			list2.add( r.nextInt(1000));
			list3.add( r.nextInt(1000));
			list4.add( r.nextInt(1000));
			list5.add( r.nextInt(1000));
		}

		long allocatedMemoryAfter = runtime.totalMemory();
		System.out.println("AllocatedMemory : "+(allocatedMemoryAfter-allocatedMemoryBefore)/ mb);
		long allocatedMemory = runtime.totalMemory();
		
		sb.append("Free memory: " + format.format(freeMemory / mb) + "\n");
		sb.append("Allocated memory: " + format.format(allocatedMemory / mb) + "\n");
		sb.append("Max memory: " + format.format(maxMemory / mb) + "\n");
		sb.append("Total free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / mb) + "\n");
		System.out.println("StringBuilder:\n"+sb);
	}
}
