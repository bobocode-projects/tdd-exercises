package com.bobocode.avl;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class RecursiveAvlTree<T extends Comparable> implements AvlTree<T> {
    Node<T> root;
    private int size;

    static class Node<T> {
        T element;
        Node<T> left, right, parent;
        int height;

        private Node(T element) {
            this.element = element;
        }

        static <T> Node<T> valueOf(T element) {
            return new Node(element);
        }

        public void setRight(Node<T> rightNode) {
            if (rightNode != null) {
                right = rightNode;
                rightNode.parent = this;
            } else {
                right = null;
            }
        }

        public void setLeft(Node<T> leftNode) {
            if (leftNode != null) {
                left = leftNode;
                leftNode.parent = this;
            } else {
                left = null;
            }
        }
    }

    public static <T extends Comparable> RecursiveAvlTree<T> of(T... elements) {
        RecursiveAvlTree<T> tree = new RecursiveAvlTree<>();
        Arrays.stream(elements).forEach(tree::insert);
        return tree;
    }

    @Override
    public boolean insert(T element) {
        Objects.requireNonNull(element);
        if (root == null) {
            root = Node.valueOf(element);
            size++;
            return true;
        } else {
            return insertIntoSubTree(root, element);
        }
    }

    private boolean insertIntoSubTree(Node<T> theNode, T element) {
        boolean inserted = false;
        if (theNode.element.compareTo(element) > 0) {
            inserted = insertIntoLeftSubTree(theNode, element);
            tryToRebalanceAtNode(theNode, element);
            return inserted;
        } else if (theNode.element.compareTo(element) < 0) {
            inserted = insertIntoRightSubTree(theNode, element);
            tryToRebalanceAtNode(theNode, element);
            return inserted;
        } else {
            return false;
        }
    }

    public int balanceFactorAt(Node<T> theNode) {
        if (theNode == null) {
            return 0;
        } else {
            return height(theNode.right) - height(theNode.left);
        }
    }

    private boolean insertIntoLeftSubTree(Node<T> theNode, T element) {
        if (theNode.left != null) {
            return insertIntoSubTree(theNode.left, element);
            //check balance factor for rebalancing
        } else {
            Node<T> newNode = Node.valueOf(element);
            newNode.parent = theNode;
            theNode.left = newNode;
            size++;
            return true;
        }
    }

    private boolean insertIntoRightSubTree(Node<T> theNode, T element) {
        if (theNode.right != null) {
            return insertIntoSubTree(theNode.right, element);
        } else {
            Node<T> newNode = Node.valueOf(element);
            newNode.parent = theNode;
            theNode.right = newNode;
            //check balance factor for rebalancing
            size++;
            return true;
        }
    }

    private void tryToRebalanceAtNode(Node<T> theNode, T element) {
        if (balanceFactorAt(theNode) > 1 && theNode.right.element.compareTo(element) < 0) {
            leftRotateAt(theNode);
        }
        if (balanceFactorAt(theNode) < -1 && theNode.left.element.compareTo(element) > 0) {
            rightRotateAt(theNode);
        }
        if (balanceFactorAt(theNode) > 1 && theNode.right.element.compareTo(element) > 0) {
            rightRotateWithLeftParentAt(theNode.right);
            leftRotateAt(theNode);
        }
        if (balanceFactorAt(theNode) < -1 && theNode.left.element.compareTo(element) < 0) {
//            rightRotateAt(theNode);
        }
    }

    void rightRotateAt(Node<T> targetNode) {
        Node<T> leftChild = targetNode.left;
        Node<T> rightGrandChild = leftChild.right;
        Node<T> parent = targetNode.parent;

        leftChild.setRight(targetNode);
        targetNode.setLeft(rightGrandChild);
        if (targetNode != root) {
            parent.setLeft(leftChild);
        } else {
            root = leftChild;
            leftChild.parent = null;
            targetNode.left = null;
        }

    }

    void leftRotateAt(Node<T> targetNode) {
        Node<T> rightChild = targetNode.right;
        Node<T> leftGrandChild = rightChild.left;
        Node<T> parent = targetNode.parent;

        rightChild.setLeft(targetNode);
        targetNode.setRight(leftGrandChild);
        if (targetNode != root) {
            parent.setRight(rightChild);
        } else {
            root = rightChild;
            rightChild.parent = null;
            targetNode.right = null;
        }
    }

    private void rightRotateWithLeftParentAt(Node<T> targetNode) {
        Node<T> leftChild = targetNode.left;
        Node<T> rightGrandChild = leftChild.right;
        Node<T> parent = targetNode.parent;

        leftChild.setRight(targetNode);
        targetNode.setLeft(rightGrandChild);

        parent.setRight(leftChild);
    }

    private void leftRotateIWithRightParentAt(Node<T> targetNode) {
        Node<T> rightChild = targetNode.right;
        Node<T> leftGrandChild = rightChild.left;
        Node<T> parent = targetNode.parent;

        rightChild.setLeft(targetNode);
        targetNode.setRight(leftGrandChild);

        parent.setLeft(rightChild);
    }

    @Override
    public boolean search(T element) {
        Objects.requireNonNull(element);
        return searchInSubtree(root, element) != null;
    }

    Node<T> searchInSubtree(Node<T> node, T element) {
        if (node == null) {
            return null;
        } else if (node.element.compareTo(element) > 0) {
            return searchInSubtree(node.left, element);
        } else if (node.element.compareTo(element) < 0) {
            return searchInSubtree(node.right, element);
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
        return root != null ? height(root) - 1 : 0;
    }

    int height(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + Math.max(height(node.left), height(node.right));
        }
    }

    @Override
    public void traverse(Consumer<T> consumer) {
        inOrderTraversal(root, consumer);
    }

    private void inOrderTraversal(Node<T> node, Consumer<T> consumer) {
        if (node != null) {
            inOrderTraversal(node.left, consumer);
            consumer.accept(node.element);
            inOrderTraversal(node.right, consumer);
        }
    }

}
