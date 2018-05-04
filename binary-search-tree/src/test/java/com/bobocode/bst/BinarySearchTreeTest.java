package com.bobocode.bst;

import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;


public class BinarySearchTreeTest {

    private BinarySearchTree<Integer> intTree = new BinarySearchTreeImpl<>();

    @Test
    public void testSizeOfEmptyTree() {
        int actualTreeSize = intTree.size();

        assertEquals(0, actualTreeSize);
    }

    @Test
    public void testSizeOfOneElementTree() {
        intTree.insert(12);

        int actualTreeSize = intTree.size();

        assertEquals(1, actualTreeSize);
    }

    @Test
    public void testHeightOfEmptyTree() {
        int actualHeight = intTree.height();

        assertEquals(0, actualHeight);
    }

    @Test
    public void testHeightOfOneElementTree() {
        intTree.insert(34);

        int actualHeight = intTree.height();

        assertEquals(1, actualHeight);
    }

    /** An example of balanced tree
     *         45
     *      /     \
     *    26       61
     *   / \      /  \
     * 11  32    54  80
     */
    @Test
    public void testHeightOfBalancedTree() {
        intTree.insert(45);
        intTree.insert(26);
        intTree.insert(61);
        intTree.insert(11);
        intTree.insert(32);
        intTree.insert(54);
        intTree.insert(80);


        int height = intTree.height();

        assertEquals(3, height);
    }

    /** An example of a liked list tree
     *  1
     *   \
     *    2
     *     \
     *      3
     *       \
     *        4
     *         \
     *          5
     */
    @Test
    public void testHeightOfLinkedListTree() {
        intTree.insert(1);
        intTree.insert(2);
        intTree.insert(3);
        intTree.insert(4);
        intTree.insert(5);


        int height = intTree.height();

        assertEquals(5, height);
    }

    @Test
    public void testInsertOneElement() {
        boolean isAdded = intTree.insert(25);

        assertTrue(isAdded);
    }

    @Test
    public void testInsertSameElementTwoTimesIntoEmptyTree() {
        boolean isAddedFirstTime = intTree.insert(25);
        int treeSizeAfterFirstInsert = intTree.size();
        boolean isAddedSecondTime = intTree.insert(25);
        int treeSizeAfterSecondInsert = intTree.size();

        assertTrue(isAddedFirstTime);
        assertFalse(isAddedSecondTime);
        assertEquals(treeSizeAfterFirstInsert, treeSizeAfterSecondInsert);
    }

    @Test
    public void testInsertSameElementTwoTimesIntoNonEmptyTree() {
        intTree.insert(24);
        intTree.insert(45);
        intTree.insert(14);
        int initialTreeSize = intTree.size();

        boolean isAddedSecondTime = intTree.insert(24);
        int treeSizeAfterSecondInsert = intTree.size();

        assertFalse(isAddedSecondTime);
        assertEquals(initialTreeSize, treeSizeAfterSecondInsert);
    }

    @Test
    public void testPlaceSizeWhenThreeDifferentElementsAdded() {
        intTree.insert(4);
        intTree.insert(6);
        intTree.insert(9);

        int treeSize = intTree.size();

        assertEquals(treeSize, 3);
    }

    @Test
    public void testInorderTraversalOnEmptyTree() {
        List<Integer> list = intTree.inOrderTraversal();

        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testPostOrderTraversalOnEmptyTree() {
        List<Integer> list = intTree.postOrderTraversal();

        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testPreOrderTraversalOnEmptyTree() {
        List<Integer> list = intTree.preOrderTraversal();

        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testElementsOrderAfterInorderTraversal() {
        intTree.insert(32);
        intTree.insert(23);
        intTree.insert(8);
        intTree.insert(4);
        intTree.insert(15);

        List<Integer> list = intTree.inOrderTraversal();
        List<Integer> sortedList = list.stream().sorted().collect(Collectors.toList());

        assertEquals(sortedList, list);
    }

    @Test
    public void testPreOrderTraversal() {
        intTree.insert(10);
        intTree.insert(5);
        intTree.insert(15);
        intTree.insert(1);

        List<Integer> list = intTree.preOrderTraversal();
        int firstElement = list.get(0);
        int lastElement = list.get(list.size() - 1);

        assertEquals(10, firstElement);
        assertEquals(15, lastElement);
    }

    @Test
    public void testPostOrderTraversal() {
        intTree.insert(100);
        intTree.insert(50);
        intTree.insert(25);
        intTree.insert(200);
        intTree.insert(150);

        List<Integer> list = intTree.postOrderTraversal();
        int firstElement = list.get(0);
        int lastElement = list.get(list.size() - 1);

        assertEquals(25, firstElement);
        assertEquals(100, lastElement);
    }

}
