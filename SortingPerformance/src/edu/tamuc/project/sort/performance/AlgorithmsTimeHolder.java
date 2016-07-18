package edu.tamuc.project.sort.performance;

/*
 * 
 * @author Siva Prasad Bhushetty CWID:50145947
 * 
 * */
public class AlgorithmsTimeHolder {
	private int size;
	private volatile long quick;
	private volatile long heap;
	private volatile long bubble;
	private volatile long insertion;
	private volatile long selection;
	private volatile long algorithmDurations[]=new long[5];
	private volatile int algorithmsSize;
	
	public AlgorithmsTimeHolder(int size,long algorithmDurations[]) {
		this.size = size;
		
		for(int i=0;i<algorithmDurations.length;i++)
			this.algorithmDurations[i] = algorithmDurations[i];
		
		this.setQuick(algorithmDurations[0]);
		this.setHeap(algorithmDurations[1]);
		this.setBubble(algorithmDurations[2]);
		this.setInsertion(algorithmDurations[3]);
		this.setSelection(algorithmDurations[4]);
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setQuick(long quick) {
		this.quick = quick;
	}

	public void setHeap(long heap) {
		this.heap = heap;
	}

	public void setBubble(long bubble) {
		this.bubble = bubble;
	}

	public int getSize() {
		return size;
	}

	public long getQuick() {
		return quick;
	}

	public long getHeap() {
		return heap;
	}

	public long getBubble() {
		return bubble;
	}

	public long getInsertion() {
		return insertion;
	}

	public void setInsertion(long insertion) {
		this.insertion = insertion;
	}

	public long[] getAlgorithmDurations() {
		return algorithmDurations;
	}

	public void setAlgorithmDurations(long[] algorithmDurations) {
		this.algorithmDurations = algorithmDurations;
	}

	public int getAlgorithmsSize() {
		this.algorithmsSize = this.algorithmDurations.length;
		return this.algorithmsSize;
	}

	public void setAlgorithmsSize(int algorithmsSize) {
		this.algorithmsSize = this.algorithmDurations.length;
	}

	public long getSelection() {
		return selection;
	}

	public void setSelection(long selection) {
		this.selection = selection;
	}
}



