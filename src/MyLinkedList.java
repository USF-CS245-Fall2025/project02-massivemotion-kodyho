/*
* Author: Kody Ho
* Project 02
* ----------
* 10/20/25
* 
* LinkedList implementation of the List interface
*
*/


/**
 * MyLinkedList.java
 * ------------------
 * A simple generic singly-linked list implementation for storing elements dynamically.
 *
 * <p>This class implements the {@code List<T>} interface provided in the starter code.
 * It supports basic list operations including add, get, remove, and size.
 * Nodes are linked sequentially, and the list dynamically grows and shrinks as elements are added or removed.</p>
 *
 * @param <T> the type of elements stored in this linked list
 *
 * @author Kody Ho
 * @version 1.0
 * @since 2025-10-20
 */
 
public class MyLinkedList<T> implements List<T> {

    /** Node class representing an element in the linked list. */
    private static class Node<T> {
        T data;
        Node<T> next;

        /**
         * Constructs a new node containing the given element.
         *
         * @param data the element to store in this node
         */
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    /** The head (first) node of the linked list. */
    private Node<T> head;

    /** The number of elements currently stored in the list. */
    private int size;

    /**
     * Constructs an empty linked list.
     */
    public MyLinkedList() {
        head = null;
        size = 0;
    }

    /**
     * Adds an element at the specified index.
     * Shifts subsequent elements (if any) to the right.
     *
     * @param index the position to insert the element at (0-based)
     * @param element the element to insert
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size)
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index);

        Node<T> newNode = new Node<>(element);

        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> prev = getNode(index - 1);
            newNode.next = prev.next;
            prev.next = newNode;
        }

        size++;
    }

    /**
     * Appends an element to the end of the list.
     *
     * @param element the element to add
     * @return {@code true} always, as the operation succeeds
     */
    @Override
    public boolean add(T element) {
        add(size, element);
        return true;
    }

    /**
     * Returns the element at the specified index.
     *
     * @param index the index of the element to retrieve
     * @return the element at the given index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    @Override
    public T get(int index) {
        checkIndex(index);
        return getNode(index).data;
    }

    /**
     * Removes the element at the specified index and returns it.
     * Shifts subsequent elements to the left.
     *
     * @param index the index of the element to remove
     * @return the element previously at the given index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedData;

        if (index == 0) {
            removedData = head.data;
            head = head.next;
        } else {
            Node<T> prev = getNode(index - 1);
            removedData = prev.next.data;
            prev.next = prev.next.next;
        }

        size--;
        return removedData;
    }

    /**
     * Returns the number of elements currently stored in the list.
     *
     * @return the list size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns a string representation of the list and its contents.
     *
     * @return a string containing all elements in the list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) sb.append(", ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Helper method to retrieve the node at a given index.
     *
     * @param index index of node to retrieve
     * @return the node at the specified index
     */
    private Node<T> getNode(int index) {
        Node<T> current = head;
        for (int i = 0; i < index; i++)
            current = current.next;
        return current;
    }

    /**
     * Helper method to validate that an index is within bounds.
     *
     * @param index the index to check
     * @throws IndexOutOfBoundsException if index < 0 or index >= size
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
}
