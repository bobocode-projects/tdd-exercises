package com.bobocode;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<E>}.
 *
 * @param <E> generic type parameter
 */
public class LinkedList<E> implements List<E> {

    private static class Node<E> {
        E element;
        Node<E> next;

        private Node(E element) {
            this.element = element;
        }

        static <E> Node<E> valueOf(E element) {
            return new Node<>(element);
        }
    }

    private Node<E> first;
    private Node<E> last;
    private int size;

    public LinkedList() {
        first = last = null;
        size = 0;
    }

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <E>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    @SafeVarargs
    static <E> List<E> of(E... elements) {
        List<E> list = new LinkedList<>();
        for (E element : elements) {
            list.add(element);
        }
        return list;
    }

    /**
     * Adds an element to the end of the list. This operation is performed in constant time O(1)
     *
     * @param element element to add
     */
    @Override
    public void add(E element) {
        Node<E> node = new Node<>(element);
        if (isEmpty()) {
            first = last = node;
        } else {
            last.next = node;
            last = last.next;
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
        checkBoundsToAdd(index);
        Node<E> newNode = Node.valueOf(element);
        if (index == 0) {
            newNode.next = first;
            first = newNode;
        } else {
            Node<E> node = findNodeByIndex(index - 1);
            newNode.next = node.next;
            node.next = newNode;
        }
        size++;
    }

    private void checkBoundsToAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
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
        verifyElementExistAt(index);
        Node<E> node = findNodeByIndex(index);
        node.element = element;
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
        return findNodeByIndex(index).element;
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     */
    @Override
    public E getFirst() {
        return first.element;

    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     */
    @Override
    public E getLast() {
        return last.element;
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
        verifyElementExistAt(index);
        if (index == 0) {
            first = first.next;
        } else {
            Node<E> prevNode = findNodeByIndex(index - 1);
            prevNode.next = prevNode.next.next;
        }
        size--;
    }

    private void verifyElementExistAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private Node<E> findNodeByIndex(int index) {
        verifyElementExistAt(index);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(E element) {
        if (first == null) {
            return false;
        } else {
            return exists(element);
        }
    }

    private boolean exists(E element) {
        Node<E> node = first;
        while (node != null) {
            if (node.element.equals(element)) {
                return true;
            }
            node = node.next;
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
        return first == null;
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
        first = last = null;
        size = 0;
    }
}
