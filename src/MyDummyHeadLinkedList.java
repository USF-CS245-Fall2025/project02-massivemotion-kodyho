/*
* Author: Kody Ho
* Project 02
* ----------
* 10/20/25
* 
* Dummy Head LinkedList implementation of the List interface
*
*/

/**
 * MyDummyHeadLinkedList.java
 * --------------------------
 * A simple singly-linked list implementation that uses a dummy (sentinel) head node.
 *
 * <p>The dummy head simplifies insertion and removal logic by ensuring that
 * every element in the list has a previous node. This eliminates special cases
 * for operations at the beginning of the list.</p>
 *
 * @param <T> the type of elements stored in this list
 *
 * @author Kody
 * @version 1.0
 * @since 2025-10-20
 */
public class MyDummyHeadLinkedList<T> implements List<T> {

    /**
     * Inner class representing a node in the linked list.
     */
    private static class Node<T> {
        T data;
        Node<T> next;

        /**
         * Constructs a new node.
         *
         * @param data the data to store in this node
         */
        Node(T data) {
            this.data = data;
        }
    }

    /** Dummy (sentinel) head node — never stores real data. */
    private final Node<T> dummy;

    /** Number of elements currently in the list. */
    private int size;

    /**
     * Constructs an empty linked list with a dummy head.
     */
    public MyDummyHeadLinkedList() {
        dummy = new Node<>(null); // dummy head
        size = 0;
    }

    /**
     * Inserts an element at a specific position.
     *
     * @param index   the position to insert at (0-based)
     * @param element the element to insert
     * @throws IndexOutOfBoundsException if index < 0 or index > size
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

        Node<T> prev = getNodeBefore(index);
        Node<T> newNode = new Node<>(element);

        newNode.next = prev.next;
        prev.next = newNode;
        size++;
    }

    /**
     * Appends an element to the end of the list.
     *
     * @param element the element to add
     * @return {@code true} (the operation always succeeds)
     */
    @Override
    public boolean add(T element) {
        add(size, element);
        return true;
    }

    /**
     * Returns the element at the specified index.
     *
     * @param index the position to retrieve
     * @return the element at that position
     * @throws IndexOutOfBoundsException if index < 0 or index >= size
     */
    @Override
    public T get(int index) {
        checkIndex(index);
        Node<T> node = getNode(index);
        return node.data;
    }

    /**
     * Removes and returns the element at the given index.
     *
     * @param index the position of the element to remove
     * @return the removed element
     * @throws IndexOutOfBoundsException if index < 0 or index >= size
     */
    @Override
    public T remove(int index) {
        checkIndex(index);
        Node<T> prev = getNodeBefore(index);
        Node<T> target = prev.next;

        prev.next = target.next;
        size--;
        return target.data;
    }

    /**
     * Returns the number of elements currently in the list.
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns a string representation of the list contents.
     *
     * @return a string in the form [elem1, elem2, ...]
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = dummy.next;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null)
                sb.append(", ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Helper method to retrieve the node at a specific index.
     * This method skips the dummy node.
     *
     * @param index the index of the node to retrieve
     * @return the node at the specified index
     */
    private Node<T> getNode(int index) {
        Node<T> current = dummy.next;
        for (int i = 0; i < index; i++)
            current = current.next;
        return current;
    }

    /**
     * Helper method to get the node immediately before a given index.
     * This method can safely return the dummy head for index 0.
     *
     * @param index the index position to find the previous node for
     * @return the node before the given index
     */
    private Node<T> getNodeBefore(int index) {
        Node<T> current = dummy;
        for (int i = 0; i < index; i++)
            current = current.next;
        return current;
    }

    /**
     * Verifies that an index is within valid range.
     *
     * @param index the index to check
     * @throws IndexOutOfBoundsException if index < 0 or index >= size
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
}
