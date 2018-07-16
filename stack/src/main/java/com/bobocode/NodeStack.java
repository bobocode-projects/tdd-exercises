package com.bobocode;

import com.bobocode.exception.EmptyStackException;

import java.util.stream.Stream;

public class NodeStack<T> implements Stack<T> {
    private Node<T> head;
    private int size=0;

    public static <T> NodeStack<T> of(T... elements) {
        NodeStack<T> nodeStack = new NodeStack<>();
        Stream.of(elements).forEach(nodeStack::push);
        return nodeStack;
    }

    @Override
    public void push(T element) {
        Node<T> newNode = Node.valueOf(element);
        if (head != null) {
            newNode.setNext(head);
        }
        head = newNode;
        size++;
    }

    @Override
    public T pop() {
        if (!isEmpty()) {
            size--;
            return popHead();
        } else {
            throw new EmptyStackException();
        }
    }

    private T popHead() {
        T element = head.getElement();
        this.head = head.getNext();
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }
}
