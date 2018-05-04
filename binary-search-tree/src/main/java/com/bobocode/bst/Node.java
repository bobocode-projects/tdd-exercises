package com.bobocode.bst;

class Node<T extends Comparable> {
    private T element;
    private Node<T> left;
    private Node<T> right;

    private Node(T element) {
        this.element = element;
    }

    public static<T extends Comparable> Node<T> valueOf(T element){
        return new Node<>(element);
    }

    public T getElement() {
        return element;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }
}