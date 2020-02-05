import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int m_N;

    private class Node {
        Node prev;
        Item item;
        Node next;
    }

    private class ItemIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){
            return (current != null);
        }
        public Item next() {
            if (current == null) throw new java.util.NoSuchElementException("No elements to iterate.");
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove() { 
            throw new UnsupportedOperationException("remove operation is not supported in this implementation");
        }
    }

    private void assertItemNotNull(Item item) {
        if (item == null) throw new IllegalArgumentException("Item to be inserted cannot be null.");
    }

    private void assertDequeNotEmpty() {
        if (m_N == 0) throw new java.util.NoSuchElementException("Deque is empty.");
    }

    // construct an empty deque
    public Deque() {
        m_N = 0; // no items
        last = first; // last and first are the same in the beginning
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (m_N == 0);
    }

    // return the number of items on the deque
    public int size() {
        return m_N;
    }

    // add the item to the front
    public void addFirst(Item item) {
        assertItemNotNull(item);
        // Create a new node and stage it as to be first node
        Node oldfirst = first;
        first = new Node();
        first.prev = null;
        first.item = item;
        first.next = oldfirst;
        // Special case when N=0
        if (m_N == 0)
            last = first;
        else
            oldfirst.prev = first;
        m_N = m_N + 1;
    }

    // add the item to the back
    public void addLast(Item item) {
        assertItemNotNull(item);
        // Create a new node and stage it as to be last node
        Node oldlast = last;
        last = new Node();
        last.prev = oldlast;
        last.item = item;
        last.next = null;
        // Special case when N=0
        if (m_N == 0)
            first = last;
        else
            oldlast.next = last;
        m_N = m_N + 1;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        assertDequeNotEmpty();
        // Store item to return
        Item item = first.item;
        // Advance first to next item
        first = first.next;
        // Set prev of new first to null
        if (m_N > 1)
            first.prev = null;
        else
            last = first;
        m_N = m_N - 1;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        assertDequeNotEmpty();
        // Store item to return
        Item item = last.item;
        // Advance last to previous item
        last = last.prev;
        // Set next of new last to null
        if (m_N > 1)
            last.next = null;
        else
            first = last;
        m_N = m_N - 1;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ItemIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> s = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            //StdOut.println(item);
            if (!item.equals("-"))
                s.addLast(item);
            else if (!s.isEmpty())
                StdOut.print(s.removeFirst() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");
        StdOut.println("Testing iterator");
        for (String ss: s) {
            StdOut.println(ss);
        }
    }
}
