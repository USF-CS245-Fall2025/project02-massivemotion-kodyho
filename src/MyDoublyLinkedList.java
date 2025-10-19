/*
* Author: Kody Ho
* Project 02
* ----------
* 10/20/25
* 
* DoublyLinkedList implementation of the List interface
*
*/


/**
 * MyDoublyLinkedList.java
 * -----------------------
 * A simple generic doubly-linked list implementation for dynamically storing elements.
 *
 * <p>This class implements the {@code List<T>} interface and provides efficient insertions and removals
 * at both ends of the list. Each node stores references to both its previous and next neighbors,
 * allowing traversal in either direction.</p>
 *
 * @param <T> the type of elements stored in this list
 *
 * @author Kody
 * @version 1.0
 * @since 2025-10-20
 */
public class MyDoublyLinkedList<T> implements List<T> {

    /**
     * Inner class representing a node in the doubly linked list.
     * Each node maintains references to both its previous and next nodes.
     */
    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        /**
         * Constructs a new node with the specified data.
         *
         * @param data the data to store in this node
         */
        Node(T data) {
            this.data = data;
        }
    }

    /** Reference to the first node in the list. */
    private Node<T> head;

    /** Reference to the last node in the list. */
    private Node<T> tail;

    /** Number of elements currently in the list. */
    private int size;

    /**
     * Constructs an empty doubly linked list.
     */
    public MyDoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Adds an element at a specific position in the list.
     * Shifts elements at or after the position to the right.
     *
     * @param index   the position to insert the element at (0-based)
     * @param element the element to insert
     * @throws IndexOutOfBoundsException if index < 0 or index > size
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

        Node<T> newNode = new Node<>(element);

        if (index == 0) {
            // Insert at the head
            newNode.next = head;
            if (head != null) {
                head.prev = newNode;
            }
            head = newNode;
            if (tail == null) tail = newNode; // First element
        } else if (index == size) {
            // Insert at the tail
            newNode.prev = tail;
            if (tail != null) {
                tail.next = newNode;
            }
            tail = newNode;
            if (head == null) head = newNode; // First element
        } else {
            // Insert in the middle
            Node<T> nextNode = getNode(index);
            Node<T> prevNode = nextNode.prev;

            newNode.next = nextNode;
            newNode.prev = prevNode;
            prevNode.next = newNode;
            nextNode.prev = newNode;
        }

        size++;
    }

    /**
     * Appends an element to the end of the list.
     *
     * @param element the element to add
     * @return {@code true}, as the operation always succeeds
     */
    @Override
    public boolean add(T element) {
        add(size, element);
        return true;
    }

    /**
     * Retrieves the element at the given index.
     *
     * @param index the index of the element to return
     * @return the element at the given index
     * @throws IndexOutOfBoundsException if index < 0 or index >= size
     */
    @Override
    public T get(int index) {
        checkIndex(index);
        return getNode(index).data;
    }

    /**
     * Removes and returns the element at the specified index.
     *
     * @param index the index of the element to remove
     * @return the element that was removed
     * @throws IndexOutOfBoundsException if index < 0 or index >= size
     */
    @Override
    public T remove(int index) {
        checkIndex(index);
        Node<T> target = getNode(index);
        T removedData = target.data;

        if (target.prev != null) {
            target.prev.next = target.next;
        } else {
            head = target.next; // removing head
        }

        if (target.next != null) {
            target.next.prev = target.prev;
        } else {
            tail = target.prev; // removing tail
        }

        size--;
        return removedData;
    }

    /**
     * Returns the number of elements currently in the list.
     *
     * @return the number of elements
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
     * Helper method to get the node at a specific index.
     * Traverses from the head or tail depending on which is closer.
     *
     * @param index index of the node to retrieve
     * @return the node at the given index
     */
    private Node<T> getNode(int index) {
        Node<T> current;

        // Choose traversal direction for efficiency
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }

        return current;
    }

    /**
     * Validates that an index is within the bounds of the list.
     *
     * @param index the index to validate
     * @throws IndexOutOfBoundsException if index < 0 or index >= size
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
}
