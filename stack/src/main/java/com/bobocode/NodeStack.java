package com.bobocode;

import com.bobocode.exception.EmptyStackException;

import java.util.Objects;
import java.util.stream.Stream;

public class NodeStack<T> implements Stack<T> {
    private static class Node<T> {
        T element;
        Node<T> next;

        public static <T> Node<T> valueOf(T element) {
            return new Node<>(element);
        }

        private Node(T element) {
            this.element = element;
        }
    }

    private Node<T> head;
    private int size = 0;

    public static <T> NodeStack<T> of(T... elements) {
        NodeStack<T> nodeStack = new NodeStack<>();
        Stream.of(elements).forEach(nodeStack::push);
        return nodeStack;
    }

    @Override
    public void push(T element) {
        Objects.requireNonNull(element);
        Node<T> newNode = Node.valueOf(element);
        if (head != null) {
            newNode.next = head;
        }
        head = newNode;
        size++;
    }

    @Override
    public T pop() {
        if (head != null) {
            size--;
            return retrieveHead();
        } else {
            throw new EmptyStackException();
        }
    }

    private T retrieveHead() {
        T element = head.element;
        this.head = head.next;
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
