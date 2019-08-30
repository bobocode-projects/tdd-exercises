package com.bobocode.bst;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;

class BinarySearchTreeTest {

    private BinarySearchTree<Integer> bst = new RecursiveBinarySearchTree<>();

    @Test
    void testInsertIntoEmptyTree() {
        boolean inserted = bst.insert(123);

        assertThat(inserted, is(true));
    }

    @Test
    void testInsertTwoElementsWithSameValue() {
        bst = RecursiveBinarySearchTree.of(25);

        boolean inserted = bst.insert(25);

        assertThat(inserted, is(false));
    }

    @Test
    void testInsertElements() {
        bst = RecursiveBinarySearchTree.of(10, 9, 11, 8, 12, 7);

        assertThat(bst.search(10), is(true));
        assertThat(bst.search(9), is(true));
        assertThat(bst.search(11), is(true));
        assertThat(bst.search(8), is(true));
        assertThat(bst.search(12), is(true));
        assertThat(bst.search(7), is(true));

    }

    @Test
    void testSearchRootElement() {
        bst = RecursiveBinarySearchTree.of(44);

        boolean foundExistingElement = bst.search(44);
        boolean foundNotExistingElement = bst.search(23423);

        assertThat(foundExistingElement, is(true));
        assertThat(foundNotExistingElement, is(false));
    }

    @Test
    void testSearchInEmptyTree() {
        boolean found = bst.search(55);

        assertThat(found, is(false));
    }

    @Test
    void testSearchElements() {
        bst = RecursiveBinarySearchTree.of(234, 54, 12, 544, 21, 10);

        assertThat(bst.search(234), is(true));
        assertThat(bst.search(54), is(true));
        assertThat(bst.search(12), is(true));
        assertThat(bst.search(544), is(true));
        assertThat(bst.search(21), is(true));
        assertThat(bst.search(10), is(true));
        assertThat(bst.search(1000), is(false));
    }

    @Test
    void testSizeOfEmptyTree() {
        int actualTreeSize = bst.size();

        assertThat(actualTreeSize, is(0));
    }

    @Test
    void testSize() {
        bst = RecursiveBinarySearchTree.of(1, 2, 3, 4, 1);

        int actualTreeSize = bst.size();

        assertThat(actualTreeSize, is(4));
    }

    @Test
    void testHeightOfEmptyTree() {
        int actualHeight = bst.height();

        assertThat(actualHeight, is(0));
    }

    @Test
    void testHeightOfOneElementTree() {
        bst = RecursiveBinarySearchTree.of(24);

        int actualHeight = bst.height();

        assertThat(actualHeight, is(0));
    }

    /**
     * .......10
     * ....../  \
     * .....5   15
     * ..../      \
     * ...1       20
     */
    @Test
    void testHeight() {
        bst = RecursiveBinarySearchTree.of(10, 5, 15, 1, 20);

        int actualHeight = bst.height();

        assertThat(actualHeight, is(2));
    }

    /**
     * ..1
     * ...\
     * ....2
     * .....\
     * ..... 3
     * .......\
     * ........4
     * .........\
     * ..........5
     */
    @Test
    void testHeightOfLikedListTree() {
        bst = RecursiveBinarySearchTree.of(1, 2, 3, 4, 5);

        int actualHeight = bst.height();

        assertThat(actualHeight, is(4));
    }

    @Test
    void testHeightOfSingleElementTree() {
        bst = RecursiveBinarySearchTree.of(1);

        int actualHeight = bst.height();

        assertThat(actualHeight, is(0));
    }

    @Test
    void testInorderTraversalOfEmptyTree() {
        List<Integer> treeElementsList = new ArrayList<>(bst.size());
        bst.inOrderTraversal(treeElementsList::add);

        assertThat(treeElementsList, empty());
    }

    @Test
    void testInorderTraversal() {
        bst = RecursiveBinarySearchTree.of(324, 23, 14, 1551, 2);

        List<Integer> treeElementsList = new ArrayList<>(bst.size());
        bst.inOrderTraversal(treeElementsList::add);

        assertThat(treeElementsList.size(), is(bst.size()));
        assertThat(treeElementsList, contains(2, 14, 23, 324, 1551));
    }
}
