package com.bobocode;

class Node<T> {
    private T element;
    private Node<T> next;

    public static <T> Node<T> valueOf(T element) {
        return new Node<>(element);
    }

    private Node(T element) {
        this.element = element;
    }

    public T getElement() {
        return element;
    }

    public void setNext(Node<T> node) {
        this.next = node;
    }

    public Node<T> getNext() {
        return next;
    }
}
