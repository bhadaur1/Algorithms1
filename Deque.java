/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;

    private class Node {
        Item item;
        Node next;
    }

    public Deque()                           // construct an empty deque
    {
        first = null;
        last = null;
    }

    public boolean isEmpty()                 // is the deque empty?
    {
        return (first == null);
    }

    public int size()                        // return the number of items on the deque
    {
        return 0;
    }

    public void addFirst(Item item)          // add the item to the front
    {
        Node p = new Node();
        p.item = item;
        p.next = first;
        if (isEmpty())
            last = p;
        first = p;
    }

    public void addLast(Item item)           // add the item to the end
    {
        Node p = new Node();
        p.next = last;
        p.item = item;
        last = p;
        if (isEmpty())
            first = p;

    }

    public Item removeFirst()                // remove and return the item from the front
    {
        if (!isEmpty()) {
            Item item = first.item;
            first = first.next;
            return item;
        }
        else {
            return java.util.NoSuchElementException("No items left");
        }
    }

    public Item removeLast()                 // remove and return the item from the end
    {
        if (!isEmpty()) {
            Item item = last.item;
            first = first.next;
            return item;
        }
        else {
            return java.util.NoSuchElementException("No items left");
        }
    }

    public Iterator<Item> iterator()         // return an iterator over items in order from front to end

    public static void main(String[] args) {

    }
}
