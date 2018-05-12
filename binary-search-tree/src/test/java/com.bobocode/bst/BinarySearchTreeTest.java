package com.bobocode.bst;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class BinarySearchTreeTest {

    @Test
    public void testInsertIntoEmptyTree() {
        BinarySearchTree<Integer> binarySearchTree = new RecursiveBinarySearchTree<>();

        boolean inserted = binarySearchTree.insert(123);

        assertThat(inserted, is(true));
    }

    @Test
    public void testSearchRootElement() {
        BinarySearchTree<Integer> binarySearchTree = new RecursiveBinarySearchTree<>();
        binarySearchTree.insert(44);

        boolean foundExistingElement = binarySearchTree.search(44);
        boolean foundNotExistingElement = binarySearchTree.search(23423);

        assertThat(foundExistingElement, is(true));
        assertThat(foundNotExistingElement, is(false));
    }

    @Test
    public void testInsertTwoElementsWithSameValue() {
        BinarySearchTree<Integer> binarySearchTree = new RecursiveBinarySearchTree<>();
        binarySearchTree.insert(25);

        boolean inserted = binarySearchTree.insert(25);

        assertThat(inserted, is(false));
    }

    @Test
    public void testInsertElements() {
        BinarySearchTree<Integer> binarySearchTree = new RecursiveBinarySearchTree<>();

        binarySearchTree.insert(10);
        binarySearchTree.insert(9);
        binarySearchTree.insert(11);
        binarySearchTree.insert(8);
        binarySearchTree.insert(12);
        binarySearchTree.insert(7);

        assertThat(binarySearchTree.search(10), is(true));
        assertThat(binarySearchTree.search(9), is(true));
        assertThat(binarySearchTree.search(11), is(true));
        assertThat(binarySearchTree.search(8), is(true));
        assertThat(binarySearchTree.search(12), is(true));
        assertThat(binarySearchTree.search(7), is(true));

    }

    @Test
    public void testSizeOfEmptyTree() {
        BinarySearchTree<Integer> binarySearchTree = new RecursiveBinarySearchTree<>();

        int actualTreeSize = binarySearchTree.size();

        assertThat(actualTreeSize, is(0));
    }

    @Test
    public void testSize() {
        BinarySearchTree<Integer> binarySearchTree = new RecursiveBinarySearchTree<>();
        binarySearchTree.insert(1);
        binarySearchTree.insert(2);
        binarySearchTree.insert(3);
        binarySearchTree.insert(4);
        binarySearchTree.insert(1); // already exist

        int actualTreeSize = binarySearchTree.size();

        assertThat(actualTreeSize, is(4));
    }

    @Test
    public void testHeightOfEmptyTree() {
        BinarySearchTree<Integer> binarySearchTree = new RecursiveBinarySearchTree<>();

        int actualHeight = binarySearchTree.height();

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
        BinarySearchTree<Integer> binarySearchTree = new RecursiveBinarySearchTree<>();
        binarySearchTree.insert(10);
        binarySearchTree.insert(5);
        binarySearchTree.insert(15);
        binarySearchTree.insert(1);
        binarySearchTree.insert(20);

        int actualHeight = binarySearchTree.height();

        assertThat(actualHeight, is(3));
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
        BinarySearchTree<Integer> binarySearchTree = new RecursiveBinarySearchTree<>();
        binarySearchTree.insert(1);
        binarySearchTree.insert(2);
        binarySearchTree.insert(3);
        binarySearchTree.insert(4);
        binarySearchTree.insert(5);

        int actualHeight = binarySearchTree.height();

        assertThat(actualHeight, is(5));
    }

    @Test
    public void testHeightOfSingleElementTree() {
        BinarySearchTree<Integer> binarySearchTree = new RecursiveBinarySearchTree<>();
        binarySearchTree.insert(1);

        int actualHeight = binarySearchTree.height();

        assertThat(actualHeight, is(1));
    }

    @Test
    public void testInorderTraversal() {
        BinarySearchTree<Integer> binarySearchTree = new RecursiveBinarySearchTree<>();
        binarySearchTree.insert(324);
        binarySearchTree.insert(23);
        binarySearchTree.insert(14);
        binarySearchTree.insert(1551);
        binarySearchTree.insert(2);

        List<Integer> treeElementsList = new ArrayList<>(binarySearchTree.size());
        binarySearchTree.inOrderTraversal(treeElementsList::add);

        assertThat(treeElementsList.size(), is(binarySearchTree.size()));
        assertThat(treeElementsList, contains(2, 14, 23, 324, 1551));
    }
}
