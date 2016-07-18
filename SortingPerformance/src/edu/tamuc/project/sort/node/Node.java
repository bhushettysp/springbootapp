package edu.tamuc.project.sort.node;

public class Node {
	int data;
	Node link;
	
	public Node(){
		data=0;
		link=null;
	}
	
	public Node(int data,Node link){
		this.data=data;
		this.link=link;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Node getLink() {
		return link;
	}

	public void setLink(Node link) {
		this.link = link;
	}
	
	
}
