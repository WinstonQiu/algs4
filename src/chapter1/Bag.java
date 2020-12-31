package chapter1;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<Item> implements Iterable<Item> {
	private Node first;
	private int N = 0;

	private class Node {
		Item item;
		Node next;
	}

	public void add(Item item) {
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		++N;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return N;
	}
	
	public Iterator<Item> iterator() {
		return new ListIterator(first);
	}
	
	private class ListIterator implements Iterator<Item> {
		private Node current;

		public ListIterator(Node first) {
			current = first;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
}
