package com.bobocode.bst;

import java.util.function.Consumer;

public class RecursiveBinarySearchTree<T extends Comparable> implements BinarySearchTree<T> {

    private Node<T> root;
    private int size = 0;

    @Override
    public boolean insert(T element) {
        boolean isInserted = insertElement(element);
        if (isInserted) {
            size++;
        }
        return isInserted;
    }

    boolean insertElement(T element) {
        if (root == null) {
            root = Node.valueOf(element);
            return true;
        } else {
            return insertIntoNode(root, element);
        }
    }

    private boolean insertIntoNode(Node<T> node, T element) {
        if (node.getElement().compareTo(element) > 0) {
            return insertIntoLeftSubtree(node, element);
        } else if (node.getElement().compareTo(element) < 0) {
            return insertIntoRightSubtree(node, element);
        } else {
            return false;
        }
    }

    private boolean insertIntoLeftSubtree(Node<T> node, T element) {
        if (node.getLeft() != null) {
            return insertIntoNode(node.getLeft(), element);
        } else {
            node.setLeft(Node.valueOf(element));
            return true;
        }
    }

    private boolean insertIntoRightSubtree(Node<T> node, T element) {
        if (node.getRight() != null) {
            return insertIntoNode(node.getRight(), element);
        } else {
            node.setRight(Node.valueOf(element));
            return true;
        }
    }


    @Override
    public boolean search(T element) {
        return findByElement(root, element) != null;
    }

    private Node<T> findByElement(Node<T> node, T element) {
        if (node == null) {
            return null;
        } else if (node.getElement().compareTo(element) > 0) {
            return findByElement(node.getLeft(), element);
        } else if (node.getElement().compareTo(element) < 0) {
            return findByElement(node.getRight(), element);
        } else {
            return node;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return root != null ? height(root) : 0;
    }

    private int height(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
        }
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        inOrderTraversal(root, consumer);
    }

    private void inOrderTraversal(Node<T> node, Consumer<T> consumer) {
        if (node != null) {
            inOrderTraversal(node.getLeft(), consumer);
            consumer.accept(node.getElement());
            inOrderTraversal(node.getRight(), consumer);
        }
    }
}
