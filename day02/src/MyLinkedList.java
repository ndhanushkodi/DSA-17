import java.util.NoSuchElementException;

public class MyLinkedList<T> {

	private Node head;
	private Node tail;
	private int size;

	private class Node {
		T val;
		Node prev;
		Node next;

		private Node(T d, Node prev, Node next) {
			this.val = d;
			this.prev = prev;
			this.next = next;
		}
	}

	public MyLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void add(T c) {
		addLast(c);
	}

	public T pop() {
		return removeLast();
	}

	public void addLast(T c) {
		Node n = new Node(c, tail, null);
		if(tail != null) {
			tail.next = n;
		}
		tail = n;
		if(head == null){
			head = n;
		}
		size++;
	}

	public void addFirst(T c) {
		Node n = new Node(c, null, head);
		if(head != null){
			head.prev = n;
		}
		head = n;
		if(tail == null){
			tail = n;
		}
		size++;
	}

	private Node getNode(int index) {
		if(index >= size && index < 0) throw new IndexOutOfBoundsException();
		Node cur = head;
		for(int i=0; i<index; i++){
			cur = cur.next;
		}
		return cur;
	}

	public T get(int index) {
		return getNode(index).val;
	}

	public T remove(int index) {
		if(index >= size && index < 0) throw new IndexOutOfBoundsException();
		if(index == 0){return removeFirst();} //must have a return statement here
		if(index== size-1){return removeLast();}

		Node toRemove = getNode(index);
		toRemove.prev.next = toRemove.next;
		toRemove.next.prev = toRemove.prev;
		size--;
		return toRemove.val;
	}

	public T removeFirst() {
		if(isEmpty())
			throw new NoSuchElementException();

		Node toRemove = head;
		if(size == 1){
			head = null;
			tail = null;
		}
		else {
			head = toRemove.next;
			head.prev = null;
		}
		size--;
		return toRemove.val;

	}

	public T removeLast() {
		if(isEmpty())
			throw new NoSuchElementException();
		Node toRemove = tail;
		if(size == 1) {
			head = null;
			tail = null;
		}
		else{
			tail = toRemove.prev;
			tail.next = null;
		}
		size--;
		return toRemove.val;
	}
}