package com.bobocode.bst;

import java.util.function.Consumer;

public interface BinarySearchTree<T extends Comparable> {

    boolean insert(T element);

    boolean search(T element);

    int size();

    int height();

    void inOrderTraversal(Consumer<T> consumer);
}
