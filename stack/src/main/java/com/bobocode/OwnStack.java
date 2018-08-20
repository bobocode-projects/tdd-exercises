package com.bobocode;

import java.util.EmptyStackException;
import java.util.Objects;
import java.util.stream.Stream;

public class OwnStack<T> implements Stack<T> {
    private static class Node<T> {
        private T element;
        private Node<T> prev;

        private Node(T element) {
            this.element = element;
        }

        static <T> Node<T> valueOf(T element) {
            return new Node<>(element);
        }
    }

    private int size;

    private Node<T> head;

    @Override
    public void push(T element) {
        if (Objects.isNull(element)){
            return;
        }
        Node<T> newNode = Node.valueOf(element);
        if (head == null) {
            head = newNode;
        } else {
            newNode.prev = head;
            head = newNode;
        }

        size++;
    }

    @Override
    public T pop() {
        if (Objects.isNull(head)) {
            throw new EmptyStackException();
        }

        T element = head.element;
        head = head.prev;
        size--;

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

    public static <T> OwnStack<T> valueOf(T... elements) {
        OwnStack<T> stack = new OwnStack<>();
        Stream.of(elements).forEach(stack::push);
        return stack;
    }
}
