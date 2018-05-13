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
        BinarySearchTree<Integer> bst = new RecursiveBinarySearchTree<>();

        boolean inserted = bst.insert(123);

        assertThat(inserted, is(true));
    }

    @Test
    public void testSearchRootElement() {
        BinarySearchTree<Integer> bst = new RecursiveBinarySearchTree<>();
        bst.insert(44);

        boolean foundExistingElement = bst.search(44);
        boolean foundNotExistingElement = bst.search(23423);

        assertThat(foundExistingElement, is(true));
        assertThat(foundNotExistingElement, is(false));
    }

    @Test
    public void testInsertTwoElementsWithSameValue() {
        BinarySearchTree<Integer> bst = new RecursiveBinarySearchTree<>();
        bst.insert(25);

        boolean inserted = bst.insert(25);

        assertThat(inserted, is(false));
    }

    @Test
    public void testInsertElements() {
        BinarySearchTree<Integer> bst = new RecursiveBinarySearchTree<>();

        bst.insert(10);
        bst.insert(9);
        bst.insert(11);
        bst.insert(8);
        bst.insert(12);
        bst.insert(7);

        assertThat(bst.search(10), is(true));
        assertThat(bst.search(9), is(true));
        assertThat(bst.search(11), is(true));
        assertThat(bst.search(8), is(true));
        assertThat(bst.search(12), is(true));
        assertThat(bst.search(7), is(true));

    }

    @Test
    public void testSizeOfEmptyTree() {
        BinarySearchTree<Integer> bst = new RecursiveBinarySearchTree<>();

        int actualTreeSize = bst.size();

        assertThat(actualTreeSize, is(0));
    }

    @Test
    public void testSize() {
        BinarySearchTree<Integer> bst = new RecursiveBinarySearchTree<>();
        bst.insert(1);
        bst.insert(2);
        bst.insert(3);
        bst.insert(4);
        bst.insert(1); // already exist

        int actualTreeSize = bst.size();

        assertThat(actualTreeSize, is(4));
    }

    @Test
    public void testHeightOfEmptyTree() {
        BinarySearchTree<Integer> bst = new RecursiveBinarySearchTree<>();

        int actualHeight = bst.height();

        assertThat(actualHeight, is(0));
    }

    @Test
    public void testHeightOfOneElementTree() {
        BinarySearchTree<Integer> bst = new RecursiveBinarySearchTree<>();
        bst.insert(24);

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
        BinarySearchTree<Integer> bst = new RecursiveBinarySearchTree<>();
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(1);
        bst.insert(20);

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
        BinarySearchTree<Integer> bst = new RecursiveBinarySearchTree<>();
        bst.insert(1);
        bst.insert(2);
        bst.insert(3);
        bst.insert(4);
        bst.insert(5);

        int actualHeight = bst.height();

        assertThat(actualHeight, is(4));
    }

    @Test
    public void testHeightOfSingleElementTree() {
        BinarySearchTree<Integer> bst = new RecursiveBinarySearchTree<>();
        bst.insert(1);

        int actualHeight = bst.height();

        assertThat(actualHeight, is(0));
    }

    @Test
    public void testInorderTraversal() {
        BinarySearchTree<Integer> bst = new RecursiveBinarySearchTree<>();
        bst.insert(324);
        bst.insert(23);
        bst.insert(14);
        bst.insert(1551);
        bst.insert(2);

        List<Integer> treeElementsList = new ArrayList<>(bst.size());
        bst.inOrderTraversal(treeElementsList::add);

        assertThat(treeElementsList.size(), is(bst.size()));
        assertThat(treeElementsList, contains(2, 14, 23, 324, 1551));
    }
}
