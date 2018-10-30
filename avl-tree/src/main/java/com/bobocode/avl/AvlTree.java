package com.bobocode.avl;

import java.util.function.Consumer;

/**
 * AVL tree should have an API that allow provides the following functionality:
 * - insert an element and return true if it was inserted successfully
 * - search an element and return true it element exists
 * - get tree size
 * - get tree height
 * - perform in-order traversal by passing element @{@link java.util.function.Consumer} as a parameter
 */
public interface AvlTree<T extends Comparable> {
    boolean insert(T element);

    boolean search(T element);

    int size();

    int height();

    void traverse(Consumer<T> consumer);
}
