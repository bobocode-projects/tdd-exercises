package com.bobocode;

public class OwnStack<T> implements Stack<T> {

    private static class Node<T> {
        private T element;
        private Node<T> next;

        private Node(T element) {
            this.element = element;
        }

        public static Node<T> valueOf(T element) {
            return new Node<>(element);
        }
    }

    private Node<T> head;

    @Override
    public void push(T element) {

    }

    @Override
    public T pop() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
