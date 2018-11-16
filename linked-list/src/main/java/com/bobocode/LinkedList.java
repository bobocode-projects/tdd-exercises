package com.bobocode;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<E>}.
 *
 * @param <E> generic type parameter
 */
public class LinkedList<E> implements List<E> {
    static class Node<T> {
        Node<T> next;
        T element;

        Node(T element) {
            this.element = element;
        }
    }

    private Node<E> tail;
    private Node<E> head;
    private int size;

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <E>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <E> List<E> of(E... elements) {
        List<E> list = new LinkedList<>();
        Stream.of(elements).forEach(list::add);
        return list;
    }

    /**
     * Adds an element to the end of the list. This operation is performed in constant time O(1)
     *
     * @param element element to add
     */
    @Override
    public void add(E element) {
        Node<E> newNode = new Node<>(element);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, E element) {
        Node<E> newNode = new Node<>(element);
        if (index == size) {
            add(element);
        } else {
            Objects.checkIndex(index, size);
            Node<E> currentNode = nodeByIndex(index - 1);
            currentNode.next = newNode;
            size++;
        }
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, E element) {
        Node<E> currentNode = nodeByIndex(index);
        currentNode.element = element;
    }

    private Node<E> nodeByIndex(int index) {
        Objects.checkIndex(index, size);
        Node<E> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public E get(int index) {
        return nodeByIndex(index).element;
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public E getFirst() {
        if (head == null) {
            return null;
        }
        return head.element;
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public E getLast() {
        if (tail == null) {
            return null;
        }
        return tail.element;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public void remove(int index) {
        if (index == 0) {
            head = head.next;
        } else {
            Node<E> previousElement = nodeByIndex(index - 1);
            Node<E> currentElement = previousElement.next;
            previousElement.next = currentElement.next;
        }
        size--;
    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(E element) {
        Node<E> currentNode = head;
        while (currentNode.next != null) {
            if (currentNode.element.equals(element)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }
}
