package com.bobocode.bst;

class Node<T> {
    private T element;
    private Node<T> left;
    private Node<T> right;

    private Node(T element) {
        this.element = element;
    }

    public static <T> Node valueOf(T element) {
        return new Node(element);
    }

    public T getElement() {
        return element;
    }

    public void setLeft(Node<T> node) {
        this.left = node;
    }

    public void setRight(Node<T> node) {
        this.right = node;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }
}
