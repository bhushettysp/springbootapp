package edu.tamuc.project.sort.queue;

import edu.tamuc.project.sort.node.Node;

public class NodeQueue {
	protected Node front, rear;
	public int size;

	public NodeQueue() {
		front = null;
		rear = null;
		size = 0;
	}

	public boolean isEmpty() {
		return front == null;
	}

	public int getSize() {
		return size;
	}

	public void insert(int data) {
		Node nptr = new Node(data, null);
		if (rear == null) {
			front = nptr;
			rear = nptr;
		} else {
			rear.setLink(nptr);
			rear = rear.getLink();
		}
		size++;
	}

	public int remove() {
		if (isEmpty())
			throw new RuntimeException("Underflow Exception");
		Node ptr = front;
		front = ptr.getLink();
		if (front == null)
			rear = null;
		size--;
		return ptr.getData();
	}

	public int peek() {
		if (isEmpty())
			throw new RuntimeException("Underflow Exception");
		return front.getData();
	}

	public void display() {
		System.out.print("\nQueue = ");
		if (size == 0) {
			System.out.print("Empty\n");
			return;
		}
		Node ptr = front;
		while (ptr != rear.getLink()) {
			System.out.print(ptr.getData() + " ");
			ptr = ptr.getLink();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		NodeQueue queue = new NodeQueue();
		queue.insert(1);
		queue.insert(2);
		queue.insert(3);
		queue.insert(4);
		queue.remove();
		queue.display();
	}
}
