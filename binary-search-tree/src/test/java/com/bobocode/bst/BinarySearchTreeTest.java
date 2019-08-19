package com.bobocode.bst;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertThat;

public class BinarySearchTreeTest {

    private BinarySearchTree<Integer> bst = new RecursiveBinarySearchTree<>();

    @Test
    public void testInsertIntoEmptyTree() {
        boolean inserted = bst.insert(123);

        assertThat(inserted, is(true));
    }

    @Test
    public void testInsertTwoElementsWithSameValue() {
        bst = RecursiveBinarySearchTree.of(25);

        boolean inserted = bst.insert(25);

        assertThat(inserted, is(false));
    }

    @Test
    public void testInsertElements() {
        bst = RecursiveBinarySearchTree.of(10, 9, 11, 8, 12, 7);

        assertThat(bst.search(10), is(true));
        assertThat(bst.search(9), is(true));
        assertThat(bst.search(11), is(true));
        assertThat(bst.search(8), is(true));
        assertThat(bst.search(12), is(true));
        assertThat(bst.search(7), is(true));

    }

    @Test
    public void testSearchRootElement() {
        bst = RecursiveBinarySearchTree.of(44);

        boolean foundExistingElement = bst.search(44);
        boolean foundNotExistingElement = bst.search(23423);

        assertThat(foundExistingElement, is(true));
        assertThat(foundNotExistingElement, is(false));
    }

    @Test
    public void testSearchInEmptyTree() {
        boolean found = bst.search(55);

        assertThat(found, is(false));
    }

    @Test
    public void testSearchElements() {
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
    public void testSizeOfEmptyTree() {
        int actualTreeSize = bst.size();

        assertThat(actualTreeSize, is(0));
    }

    @Test
    public void testSize() {
        bst = RecursiveBinarySearchTree.of(1, 2, 3, 4, 1);

        int actualTreeSize = bst.size();

        assertThat(actualTreeSize, is(4));
    }

    @Test
    public void testHeightOfEmptyTree() {
        int actualHeight = bst.height();

        assertThat(actualHeight, is(0));
    }

    @Test
    public void testHeightOfOneElementTree() {
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
    public void testHeight() {
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
    public void testHeightOfLikedListTree() {
        bst = RecursiveBinarySearchTree.of(1, 2, 3, 4, 5);

        int actualHeight = bst.height();

        assertThat(actualHeight, is(4));
    }

    @Test
    public void testHeightOfSingleElementTree() {
        bst = RecursiveBinarySearchTree.of(1);

        int actualHeight = bst.height();

        assertThat(actualHeight, is(0));
    }

    @Test
    public void testInorderTraversalOfEmptyTree() {
        List<Integer> treeElementsList = new ArrayList<>(bst.size());
        bst.inOrderTraversal(treeElementsList::add);

        assertThat(treeElementsList, empty());
    }

    @Test
    public void testInorderTraversal() {
        bst = RecursiveBinarySearchTree.of(324, 23, 14, 1551, 2);

        List<Integer> treeElementsList = new ArrayList<>(bst.size());
        bst.inOrderTraversal(treeElementsList::add);

        assertThat(treeElementsList.size(), is(bst.size()));
        assertThat(treeElementsList, contains(2, 14, 23, 324, 1551));
    }
}
