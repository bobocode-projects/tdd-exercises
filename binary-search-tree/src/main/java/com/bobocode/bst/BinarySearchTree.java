package com.bobocode.bst;

import java.util.List;

public interface BinarySearchTree<T extends Comparable> {
    boolean insert(T element);

    int size();

    int height();

    List<T> inOrderTraversal();

    List<T> preOrderTraversal();

    List<T> postOrderTraversal();
}
