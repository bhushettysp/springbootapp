package edu.tamuc.project.sort.performance;

import java.util.LinkedList;
import java.util.Random;

import edu.tamuc.project.sort.algorithms.BubbleSort;
import edu.tamuc.project.sort.algorithms.HeapSort;
import edu.tamuc.project.sort.algorithms.InsertionSort;
import edu.tamuc.project.sort.algorithms.QuickSort;
import edu.tamuc.project.sort.algorithms.SelectionSort;
import edu.tamuc.project.sort.queue.NodeQueue;
import edu.tamuc.project.sort.queue.state.QueueStatus;

/*
 * 
 * @author Siva Prasad Bhushetty CWID:50145947
 * 
 * */
public class CompareAlgorithms {
	public static void main(String[] args) throws Exception {

		// Creating linked lists
		LinkedList<Integer> linkedList = new LinkedList<Integer>();

		Random r = new Random();

		for (int i = 0; i < 100000; i++) {
			linkedList.add( r.nextInt(1000));
		}
		
		System.gc();
		final NodeQueue queue = new NodeQueue();
		// Creating the common queue
		final QueueStatus state = new QueueStatus();
		long agorithmsDuration[]=new long[5];
		final AlgorithmsTimeHolder record = new AlgorithmsTimeHolder(1000000, agorithmsDuration);
		final Object lock = new Object();
		// Thread, which runs quickSort
		Thread quickThread = new Thread(new Runnable() {
			public void run() {
				while (!state.isQueuePopulationStatus() || !queue.isEmpty()) {
					LinkedList<Integer> list = new LinkedList<Integer>();
					int i = 0;
					while (!state.isQueuePopulationStatus() && i < 200) {
						synchronized (lock) {
							if (!queue.isEmpty()) {
								list.add(i, Integer.valueOf(queue.remove()));
								i++;
							}
						}
					}
					synchronized (lock) {
						while (!queue.isEmpty() && i < 200) {
							list.add(i, Integer.valueOf(queue.remove()));
							i++;
						}
					}
					// System.out.println("Size Of quick array is " + i);
					long startTime = System.nanoTime();

					QuickSort quickSort = new QuickSort();
					quickSort.sort(list);
					long duration = System.nanoTime() - startTime;

					record.setQuick(duration/1000);
					System.out.println("Quick duration --> " + duration/1000);
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
					}
				}
			}
		});
		// Thread, which runs bubbleSort
		Thread bubbleThread = new Thread(new Runnable() {

			public void run() {
				while (!state.isQueuePopulationStatus() || !queue.isEmpty()) {
					LinkedList<Integer> list = new LinkedList<Integer>();
					int i = 0;
					while (!state.isQueuePopulationStatus() && i < 200) {
						synchronized (lock) {
							if (!queue.isEmpty()) {
								list.add(i, Integer.valueOf(queue.remove()));
								i++;
							}
						}
					}
					synchronized (lock) {
						while (!queue.isEmpty() && i < 200) {
							list.add(i, Integer.valueOf(queue.remove()));
							i++;
						}
					}
					// System.out.println("Size Of Bubble array is " + i);
					long startTime = System.nanoTime();
					BubbleSort bubbleSort = new BubbleSort();
					bubbleSort.sort(list);
					long duration = System.nanoTime() - startTime;
					record.setBubble(duration/1000);
					System.out.println("Bubble duration --> " + duration/1000);
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
					}
				}
			}
		});

		// Thread, which runs heapSort
		Thread heapThread = new Thread(new Runnable() {
			public void run() {
				while (!state.isQueuePopulationStatus() || !queue.isEmpty()) {
					LinkedList<Integer> list = new LinkedList<Integer>();
					int i = 0;
					while (!state.isQueuePopulationStatus() && i < 200) {
						synchronized (lock) {
							if (!queue.isEmpty()) {
								list.add(i, Integer.valueOf(queue.remove()));
								i++;
							}
						}
					}

					synchronized (lock) {
						while (!queue.isEmpty() && i < 200) {
							list.add(i, Integer.valueOf(queue.remove()));
							i++;
						}
					}
					// System.out.println("Size Of heap array is " + i);
					long startTime = System.nanoTime();
					HeapSort.sort(list);
					long duration = System.nanoTime() - startTime;
					
					record.setHeap(duration/1000);
					System.out.println("Heap duration --> " + duration/1000);
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
					}
				}
			}
		});
		
		// Thread, which runs insertionSort
				Thread insertionThread = new Thread(new Runnable() {
					public void run() {
						while (!state.isQueuePopulationStatus() || !queue.isEmpty()) {
							LinkedList<Integer> list = new LinkedList<Integer>();
							int i = 0;
							while (!state.isQueuePopulationStatus() && i < 200) {
								synchronized (lock) {
									if (!queue.isEmpty()) {
										list.add(i, Integer.valueOf(queue.remove()));
										i++;
									}
								}
							}
							synchronized (lock) {
								while (!queue.isEmpty() && i < 200) {
									list.add(i, Integer.valueOf(queue.remove()));
									i++;
								}
							}
							// System.out.println("Size Of quick array is " + i);
							long startTime = System.nanoTime();

							InsertionSort insertionSort = new InsertionSort();
							insertionSort.sort(list);
							long duration = System.nanoTime() - startTime;

							record.setInsertion(duration/1000);
							System.out.println("Insertion duration --> " + duration/1000);
							try {
								//Thread.sleep(1000);
							} catch (Exception e) {
							}
						}
					}
				});

				Thread selectionThread = new Thread(new Runnable() {
					public void run() {
						while (!state.isQueuePopulationStatus() || !queue.isEmpty()) {
							LinkedList<Integer> list = new LinkedList<Integer>();
							int i = 0;
							while (!state.isQueuePopulationStatus() && i < 200) {
								synchronized (lock) {
									if (!queue.isEmpty()) {
										list.add(i, Integer.valueOf(queue.remove()));
										i++;
									}
								}
							}
							synchronized (lock) {
								while (!queue.isEmpty() && i < 200) {
									list.add(i, Integer.valueOf(queue.remove()));
									i++;
								}
							}
							// System.out.println("Size Of quick array is " + i);
							long startTime = System.nanoTime();

							SelectionSort selectionSort = new SelectionSort();
							selectionSort.sort(list);
							long duration = System.nanoTime() - startTime;

							record.setSelection(duration/1000);
							System.out.println("Selection duration --> " + duration/1000);
							try {
								//Thread.sleep(1000);
							} catch (Exception e) {
							}
						}
					}
				});
				
		
		Thread t = new Thread(new Runnable() {
			public void run() {
				ComparisonChart.draw(record);
			}
		});
		quickThread.start();
		bubbleThread.start();
		heapThread.start();
		insertionThread.start();
		selectionThread.start();
		t.start();

		// Populating queue with elements from linkedList.
		for (int i = 0; i < linkedList.size(); i++) {
			synchronized (lock) {
				queue.insert( linkedList.get(i));
			}
		}
		// Indicating that population has finished.
		state.queuePopulationStatus(true);

	}
}
