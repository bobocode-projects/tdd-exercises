package com.bobocode.avl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class AvlTreeTest {
    private RecursiveAvlTree<Integer> testAvlTree = new RecursiveAvlTree<>();

    @Test
    public void insertElementToEmptyTree() {
        boolean insertElement = testAvlTree.insert(55);

        assertTrue(insertElement);
    }

    @Test
    public void insertElementToNonEmptyTree() {
        testAvlTree = RecursiveAvlTree.of(2, 4, 13, 8, 17);

        System.out.println(testAvlTree.size());
        boolean insertElement = testAvlTree.insert(10);

        assertTrue(insertElement);
        assertEquals(6, testAvlTree.size());
    }

    @Test(expected = NullPointerException.class)
    public void testInsertNull(){
        testAvlTree.insert(null);
    }

    @Test
    public void testSearch(){
        testAvlTree = RecursiveAvlTree.of(3, 2, 1);

        assertTrue(testAvlTree.search(2));
        assertTrue(testAvlTree.search(1));
    }

    @Test
    public void testSearchInEmprtyTree(){
        assertFalse(testAvlTree.search(4));
    }

    @Test
    public void testSearchForNotExistingElement(){
        testAvlTree = RecursiveAvlTree.of(3, 2, 1);

        assertFalse(testAvlTree.search(4));
    }

    @Test
    public void testHeightOfEmptyTree(){
        assertEquals(0, testAvlTree.height());
    }

    @Test
    public void testHeightOfSingleElementTree(){
        testAvlTree.insert(55);
        assertEquals(0, testAvlTree.height());
    }

    @Test
    public void testBalanceFactor(){
        testAvlTree = RecursiveAvlTree.of(33);
        RecursiveAvlTree<Integer> leftHeavyAvlTree = RecursiveAvlTree.of(33, 20);
        RecursiveAvlTree<Integer> rightHeavyAvlTree = RecursiveAvlTree.of(33, 40);

        RecursiveAvlTree.Node<Integer> rootOfLeftHeavy = leftHeavyAvlTree.root;
        RecursiveAvlTree.Node<Integer> rootOfRightHeavy = rightHeavyAvlTree.root;

        assertEquals(0, testAvlTree.balanceFactorAt(testAvlTree.root));
        assertEquals( -1, leftHeavyAvlTree.balanceFactorAt(rootOfLeftHeavy));
        assertEquals( 1, rightHeavyAvlTree.balanceFactorAt(rootOfRightHeavy));
    }

    /**
     *              3
     *               \                      9
     *                9         =>        /   \
     *                 \                 3    13
     *                  13
     */
    @Test
    public void testLeftRorationAtRoot(){
        testAvlTree = RecursiveAvlTree.of(3, 9, 13);

        RecursiveAvlTree.Node<Integer> nodeOfThree = testAvlTree.searchInSubtree(testAvlTree.root, 3);
        RecursiveAvlTree.Node<Integer> nodeOfNine = testAvlTree.searchInSubtree(testAvlTree.root, 9);

        assertEquals( 0, testAvlTree.balanceFactorAt(nodeOfThree));
        assertEquals( 0, testAvlTree.balanceFactorAt(nodeOfNine));
//        root after rotation
        assertEquals(9, testAvlTree.root.element.intValue());
    }

    /**
     *                  6
     *                 /                    4
     *               4          =>        /  \
     *              /                    2    6
     *            2
     */
    @Test
    public void testRightRorationAtRoot(){
        testAvlTree = RecursiveAvlTree.of(6, 4, 2);

        RecursiveAvlTree.Node<Integer> nodeOfSix = testAvlTree.searchInSubtree(testAvlTree.root, 6);
        RecursiveAvlTree.Node<Integer> nodeOfFour = testAvlTree.searchInSubtree(testAvlTree.root, 4);

        assertEquals( 0, testAvlTree.balanceFactorAt(nodeOfSix));
        assertEquals( 0, testAvlTree.balanceFactorAt(nodeOfFour));
        assertEquals(4, testAvlTree.root.element.intValue());
    }

    /**
     *                  7
     *                 / \                         7
     *               5    8                       / \
     *              / \    \                    3    8
     *            3    6    9     =>          /  \    \
     *           / \                        2     5    9
     *         2    4                     /     /  \
     *        /                         1      4    6
     *      1
     */

    @Test
    public void testRightRorationInTheMiddle(){
        testAvlTree = RecursiveAvlTree.of(7, 5, 8, 3, 6, 9, 2, 4, 1);

        RecursiveAvlTree.Node<Integer> rootNode = testAvlTree.root;
        RecursiveAvlTree.Node<Integer> targetNode = testAvlTree.searchInSubtree(rootNode, 5);
        RecursiveAvlTree.Node<Integer> parentNode = testAvlTree.searchInSubtree(rootNode, 7);
        RecursiveAvlTree.Node<Integer> leftChildNode = testAvlTree.searchInSubtree(rootNode, 3);
        RecursiveAvlTree.Node<Integer> rightGrandChildNode = testAvlTree.searchInSubtree(rootNode, 4);

//        testAvlTree.rightRotateAt(targetNode);

        assertEquals(5, leftChildNode.right.element.intValue());
        assertEquals(3, targetNode.parent.element.intValue());
        assertEquals(4, targetNode.left.element.intValue());
        assertEquals(5, rightGrandChildNode.parent.element.intValue());
        assertEquals(7, leftChildNode.parent.element.intValue());
        assertEquals(3, parentNode.left.element.intValue());
    }

    /**
     *            3
     *           / \                        3
     *         2    5                      / \
     *        /    / \           =>      2    7
     *      1    4    7                 /    / \
     *               / \              1    5    8
     *             6    8                 / \    \
     *                   \              4    6    9
     *                    9
     */

    @Test
    public void testLeftRorationInTheMiddle(){
        testAvlTree = RecursiveAvlTree.of(3, 2, 5, 1, 4, 7, 6, 8, 9);

        RecursiveAvlTree.Node<Integer> rootNode = testAvlTree.root;
        RecursiveAvlTree.Node<Integer> targetNode = testAvlTree.searchInSubtree(rootNode, 5);
        RecursiveAvlTree.Node<Integer> parentNode = testAvlTree.searchInSubtree(rootNode, 3);
        RecursiveAvlTree.Node<Integer> rightChildNode = testAvlTree.searchInSubtree(rootNode, 7);
        RecursiveAvlTree.Node<Integer> leftGrandChildNode = testAvlTree.searchInSubtree(rootNode, 6);

        assertEquals(3, testAvlTree.root.element.intValue());
        assertEquals(7, targetNode.parent.element.intValue());
        assertEquals(6, targetNode.right.element.intValue());
        assertEquals(5, leftGrandChildNode.parent.element.intValue());
    }

    /**
     *            5                          5                         5
     *           / \                        / \                       / \
     *         3    8                     3    8                    3    11
     *        /\    /\           =>      /\    /\          =>      /\    / \
     *       2  4  7  13                2  4  7  11               2  4  8   13
     *      /     /   /\               /     /   /\              /     /\    /\
     *     1     6  11  14            1     6  10  13           1     7 10  12 14
     *              /\    \                    /   /\                /   /      \
     *            10  12   15                 9   12 14             6   9        15
     *            /                                   \
     *           9                                     15
     */

    @Test
    public void testRightLeftRorationInTheMiddle(){
        testAvlTree = RecursiveAvlTree.of(5, 3, 8, 2, 4, 7, 13, 1, 6, 11, 14, 10, 12, 15, 9);

        RecursiveAvlTree.Node<Integer> rootNode = testAvlTree.root;
        RecursiveAvlTree.Node<Integer> rightChildNode = testAvlTree.searchInSubtree(rootNode, 11);
        RecursiveAvlTree.Node<Integer> leftGrandChildNode = testAvlTree.searchInSubtree(rootNode, 7);

        assertEquals(5, rootNode.element.intValue());
        assertEquals(11, rightChildNode.element.intValue());
        assertEquals(8, rightChildNode.left.element.intValue());
        assertEquals(13, rightChildNode.right.element.intValue());
        assertEquals(10, rightChildNode.left.right.element.intValue());
        assertEquals(12, rightChildNode.right.left.element.intValue());
        assertEquals(5, rightChildNode.parent.element.intValue());
        assertEquals(11, rightChildNode.right.parent.element.intValue());
        assertEquals(11, rightChildNode.left.parent.element.intValue());
        assertEquals(8, rightChildNode.left.right.parent.element.intValue());
        assertEquals(13, rightChildNode.right.left.parent.element.intValue());

    }

    /**
     *            8                          5                         5
     *           / \                        / \                       / \
     *         3    9                     3    8                    3    11
     *        /\     \           =>      /\    /\          =>      /\    / \
     *      2   6     10                2  4  7  11               2  4  8   13
     *     /   /                       /     /   /\              /     /\    /\
     *   1   5                        1     6  10  13           1     7 10  12 14
     *      /
     *    4
     */

    @Test
    public void testLeftRightRorationInTheRoot(){
        testAvlTree = RecursiveAvlTree.of(8, 3, 9, 2, 6, 10, 1, 5, 7, 4);

        RecursiveAvlTree.Node<Integer> rootNode = testAvlTree.root;
        RecursiveAvlTree.Node<Integer> rightChildNode = testAvlTree.searchInSubtree(rootNode, 11);
        RecursiveAvlTree.Node<Integer> leftGrandChildNode = testAvlTree.searchInSubtree(rootNode, 7);

        assertEquals(5, rootNode.element.intValue());
        assertEquals(11, rightChildNode.element.intValue());
        assertEquals(8, rightChildNode.left.element.intValue());
        assertEquals(13, rightChildNode.right.element.intValue());
        assertEquals(10, rightChildNode.left.right.element.intValue());
        assertEquals(12, rightChildNode.right.left.element.intValue());
        assertEquals(5, rightChildNode.parent.element.intValue());
        assertEquals(11, rightChildNode.right.parent.element.intValue());
        assertEquals(11, rightChildNode.left.parent.element.intValue());
        assertEquals(8, rightChildNode.left.right.parent.element.intValue());
        assertEquals(13, rightChildNode.right.left.parent.element.intValue());

    }

        @Test
    public void testSizeOfEmptyTree() {
        int actualTreeSize = testAvlTree.size();

        assertThat(actualTreeSize, is(0));
    }

    @Test
    public void testSize() {
        testAvlTree = RecursiveAvlTree.of(98, 97, 99, 100, 94);

        int actualTreeSize = testAvlTree.size();

        assertThat(actualTreeSize, is(5));
    }


    @Test
    public void testInorderTraversalOfEmptyTree() {
        List<Integer> treeElementsList = new ArrayList<>(testAvlTree.size());
        testAvlTree.traverse(treeElementsList::add);

        assertThat(treeElementsList, empty());
    }

    @Test
    public void testInOrderTraversal(){
        testAvlTree = RecursiveAvlTree.of(19, 17, 13, 11);

        List<Integer> treeElementsList = new ArrayList<>(testAvlTree.size());
        testAvlTree.traverse(treeElementsList::add);

        assertThat(treeElementsList.size(), is(testAvlTree.size()));
        assertThat(treeElementsList, contains(11, 13, 17, 19));
    }
}
