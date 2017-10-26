package com.bobocode.bst;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTreeImpl<T extends Comparable> implements BinarySearchTree<T> {
    private int counter = 0;
    private Node<T> root;

    @Override
    public boolean insert(T element) {
        if (root == null) {
            return insertToRoot(element);
        } else {
            return insertToTree(element);
        }
    }

    private boolean insertToRoot(T element) {
        root = new Node<>(element);
        counter++;
        return true;
    }

    private boolean insertToTree(T element){
        boolean isAdded = insert(root, element);
        if (isAdded) {
            counter++;
        }
        return isAdded;
    }

    private boolean insert(Node<T> node, T element) {
        if (node.getElement().compareTo(element) > 0) {
            if (node.getLeft() != null) {
                return insert(node.getLeft(), element);
            } else {
                node.setLeft(new Node<>(element));
                return true;
            }
        } else if (node.getElement().compareTo(element) < 0) {
            if (node.getRight() != null) {
                return insert(node.getRight(), element);
            } else {
                node.setRight(new Node<>(element));
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node<T> node) {
        if (node != null) {
            return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
        } else {
            return 0;
        }
    }

    @Override
    public List<T> inOrderTraversal() {
        List<T> elementList = new ArrayList<>(counter);
        inOrderTraversal(elementList, root);
        return elementList;
    }

    private void inOrderTraversal(List<T> elementList, Node<T> node) {
        if (node != null) {
            inOrderTraversal(elementList, node.getLeft());
            elementList.add(node.getElement());
            inOrderTraversal(elementList, node.getRight());
        }
    }

    @Override
    public List<T> preOrderTraversal() {
        List<T> elementList = new ArrayList<>(counter);
        preOrderTraversal(elementList, root);
        return elementList;
    }

    private void preOrderTraversal(List<T> elementList, Node<T> node) {
        if (node != null) {
            elementList.add(node.getElement());
            inOrderTraversal(elementList, node.getLeft());
            inOrderTraversal(elementList, node.getRight());
        }
    }

    @Override
    public List<T> postOrderTraversal() {
        List<T> elementList = new ArrayList<>(counter);
        postOrderTraversal(elementList, root);
        return elementList;
    }

    private void postOrderTraversal(List<T> elementList, Node<T> node) {
        if (node != null) {
            inOrderTraversal(elementList, node.getLeft());
            inOrderTraversal(elementList, node.getRight());
            elementList.add(node.getElement());
        }
    }

    class Node<T extends Comparable> {
        private T element;
        Node<T> left;
        Node<T> right;

        public Node(T element) {
            this.element = element;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
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

}
