/**
 * This generic collection class uses a singly-linked data structure to store
 * store elements. Many operations are already implemented using recursion. Use
 * them as examples of public methods that send the first to private helper
 * methods. The private helpers must use recursion instead of loops.
 * 
 * E get(int) void removeAll(E) void duplicateAll(E)
 * 
 * @author Rick Mercer and Akshith Thumma
 */
public class LinkedList<E extends Comparable<E>> {
	// extends Comparable<E> means the type must be comparable to avoid CT
	// errors

	// Inner class
	private class Node {
		private E data;
		private Node next;

		public Node(E element) {
			data = element;
			next = null;
		}

		public Node(E element, Node reference) {
			data = element;
			next = reference;
		}
	}

	// Instance variables
	private Node first;
	private int n;

	// Construct an empty list
	public LinkedList() {
		first = null;
		n = 0;
	}

	// Add an element at the end of this list O(1)
	public void addLast(E el) {
		if (first == null)
			first = new Node(el);
		else // call a recursive helper method that needs the ref to first
			addLast(el, first);
		n++;
	}

	// This recursive method runs O(n). A stack push is like a loop
	// iteration
	private void addLast(E el, Node ref) {
		if (ref.next == null)
			ref.next = new Node(el, ref.next);
		else {
			addLast(el, ref.next);
		}
	}

	// Find out how many elements are in the list O(1)
	public int size() {
		return n;
	}

	// Find the maximum value in this list using the element's compareTo method
	public E max() {
		if (size() == 0)
			return null;
		// else, call the recursive method that needs a reference to the first
		// element
		return max(first, first.data);
	}

	private E max(Node ref, E largest) {
		if (ref.next == null)
			return largest;
		else {
			if (ref.data.compareTo(largest) > 0)
				largest = ref.data;
			return max(ref.next, largest);
		}
	}

	// Find out how often the same element exists using the type's equals method
	public int occurencesOf(E search) {
		// Call the recursive helper method
		return count(search, first);
	}

	// This method runs O(n), the same runtime as a loop
	private int count(E search, Node ref) {
		if (ref == null)
			return 0;
		else if (search.equals(ref.data))
			return 1 + count(search, ref.next);
		else
			return 0 + count(search, ref.next);
	}

	// Return a reference to the element at the given index.
	// Precondition: 0 >= index < size
	public E get(int index) {
		// This public method requires a private helper method with first
		// as an argument. Here is an example with the helper immediately below
		return get(first, 0, index);
	}

	private E get(Node ref, int startIndex, int stopIndex) {
		int count = 0;
		if (count == stopIndex) {
			return ref.data;
		}
		return get(ref.next, startIndex, stopIndex - 1);
	}

	// Complete method removeAll(E el) so all elements that
	// equals el are removed from this LinkedList<E>.
	public void removeAll(E el) {
		// This public method requires a call to a private helper method
		// with first as an argument. It must be recursive, no loop allowed.
		removeAll(first, el);
	}

	/*
	 * if (ref == null) { return null; } if (ref.data.equals(el)) { return
	 * removeAll(ref.next, el); } else { ref.next = removeAll(ref.next, el);
	 * return ref; }
	 */
	public Node removeAll(Node ref, E el) {
		if (ref == null) {
			return null;
		}
		if (ref.data.equals(el)) {
			return removeAll(ref.next, el);
		} else {
			ref.next = removeAll(ref.next, el);
			return ref;
		}
	}

	// Duplicate el next to each occurrence of el in this list.
	public void duplicateAll(E el) {
		// This public method requires a call to a private helper method
		// with first as an argument. It must be recursive, no loop allowed.
		duplicateEntriesHelper(first, el);
	}

	public void duplicateEntriesHelper(Node ref, E el) {
		// Walk to the end of the list
		if (ref == null) {
			return;
		}
		if (ref.next != null) {
			duplicateEntriesHelper(ref.next, el);
		}
		if (el.equals(ref.data)) {
			Node duplicatedEntry = new Node(ref.data);
			duplicatedEntry.data = ref.data;

			// Insert element after current node
			duplicatedEntry.next = ref.next;
			ref.next = duplicatedEntry;
			n++;
		}
	}

}