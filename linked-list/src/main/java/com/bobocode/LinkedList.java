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

    private Node<E> head;
    private int size;

    final static class Node<E> {
        E element;
        Node<E> next;

        private Node(E element) {
            this.element = element;
        }

        static <E> Node<E> valueOf(E element) {
            return new Node<>(element);
        }
    }

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <E>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <E> List<E> of(E... elements) {
        List<E> linkedList = new LinkedList<>();
        Stream.of(elements).forEach(linkedList::add);
        return linkedList;
    }

    /**
     * Adds an element to the end of the list. This operation is performed in constant time O(1)
     *
     * @param element element to add
     */
    @Override
    public void add(E element) {
        Objects.requireNonNull(element);
        Node<E> newNode = Node.valueOf(element);
        if (head == null) {
            head = newNode;
        } else {
            Node<E> tail = findTail();
            tail.next = newNode;
        }

        size++;
    }

    private Node<E> findTail() {
        Node<E> followingNode = Objects.requireNonNull(head);
        while (followingNode.next != null) {
            followingNode = followingNode.next;
        }
        return followingNode;
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
        if (index == size) {
            this.add(element);
        } else {
            addInside(index, element);
        }
    }

    private void addInside(int index, E element) {
        Node<E> newNode = Node.valueOf(element);
        if (index == 0) {
            addInsideAsNewHead(newNode);
        } else {
            addInsideByIndex(index, newNode);
        }
        size++;
    }

    private void addInsideAsNewHead(Node<E> newNode) {
        newNode.next = head;
        head = newNode;
    }

    private void addInsideByIndex(int index, Node<E> newNode) {
        checkIfIndexInBounds(index);
        Node<E> precedingNode = findNodeAt(index - 1);

        newNode.next = precedingNode.next;
        precedingNode.next = newNode;
    }

    private Node<E> findNodeAt(int index) {
        Node<E> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
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
        checkIfIndexInBounds(index);
        Node<E> precedingNode = findNodeAt(index - 1);
        Node<E> newNode = Node.valueOf(element);

        newNode.next = precedingNode.next.next;
        precedingNode.next = newNode;
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
        checkIfIndexInBounds(index);
        Node<E> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.element;
    }

    private void checkIfIndexInBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }


    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     */
    @Override
    public E getFirst() {
        return findNodeAt(0).element;
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     */
    @Override
    public E getLast() {
        return findNodeAt(size - 1).element;
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
            checkIfIndexInBounds(index);
            Node<E> precedingNode = findNodeAt(index - 1);
            precedingNode.next = precedingNode.next.next;
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
        if (head == null) {
            return false;
        }
        return LookForElement(element);
    }

    private boolean LookForElement(E element) {
        Node<E> currentNode = head;
        while (currentNode.next != null) {
            if (currentNode.element == element) {
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
        return size ==0;
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
        head=null;
        size=0;
    }
}
