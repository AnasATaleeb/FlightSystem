package application;

public class LinkedList<T extends Comparable<T>> {
	private Node<T> head;

	public LinkedList() {

	}

	public LinkedList(Node<T> head) {
		this.head = head;
	}

	public Node<T> getHead() {
		return head;
	}

	public void setHead(Node<T> head) {
		this.head = head;
	}

	public void traverseList() {
		traverseList(head);
	}

	public void traverseList(Node<T> node) {
		if (node == head)
			System.out.print("Head -->");
		if (node == null)
			System.out.print("Null");
		else {
			System.out.print(node + "-->");
			traverseList(node.getNext());
		}
	}

	public String traverseListReceve() {
		return traverseListReceve(head);
	}
	
	public String traverseListReceve(Node<T> node) {
		if(head == null)
			return "Head --> Null";
		if(node == head)
			return "Head -->" + node  + traverseListReceve(node.getNext());
		if(node == null)
			return "Null";
		else
			return node + "-->" +traverseListReceve(node.getNext());
	}
	
	public String traverseToString() {
		return traverseToString(head);
	}
	
	public String traverseToString(Node<T> node) {
		if(head == null)
			return "Null";
		if(node == head)
			return node  + "\n" + traverseToString(node.getNext());
		if(node == null)
			return "";
		else
			return node + "\n" +traverseToString(node.getNext());
	}
	
	public void insert(T data) {
		Node<T> newNode = new Node<T>(data);
		Node<T> curr = head;
		Node<T> prev = null;
		for (; (curr != null) && curr.getData().compareTo(data) < 0; prev = curr, curr = curr.getNext())
			;

		if (head == null) // case0 add to empty link list
			head = newNode;
		else if (prev == null) { // case1 add to empty first element
			newNode.setNext(head);
			head = newNode;
		} else if (curr == null) { // case2: add the last
			prev.setNext(newNode);
		} else { // case3: insert between two node (curr and prev )
			newNode.setNext(curr);
			prev.setNext(newNode);
		}

	}
	
	public Node<T> serch(T data) {
		Node<T> curr = head;
		for ( ; curr != null && curr.getData().compareTo(data) < 0; curr = curr.getNext());
		if (curr == null)
			return null;
		if (curr.getData().equals(data))
			return curr;

		return null;
	}
	
	public Node<T> searches(T data) {
		Node<T> n = head;
		while (n != null) {
			if (n.getData().compareTo(data) == 0) {
				return n;
			} else
				n = n.getNext();
		}
		return null;
	}

	public void delete(T data) {
		if (head == null) {
			System.out.println("error: is empty List");
			return;
		}
		Node<T> curr = head;
		Node<T> prev = null;
		for (; (curr != null) && curr.getData().compareTo(data) < 0; prev = curr, curr = curr.getNext())
			;

		if (curr.getData().equals(data)) {
			if (curr == head) {

				head = head.getNext();
			} else {
				prev.setNext(curr.getNext());
			}
		}
		else {
			System.out.println(curr);
			System.out.println("error: not found");

		}

	}

}
