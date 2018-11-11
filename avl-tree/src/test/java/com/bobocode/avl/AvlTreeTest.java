package com.bobocode.avl;

import com.bobocode.bst.BinarySearchTree;
import com.bobocode.bst.RecursiveBinarySearchTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class AvlTreeTest {
    private RecursiveAvlTree<Integer> testAvlTree = new RecursiveAvlTree<>();
    private RecursiveBinarySearchTree<Integer> bst = new RecursiveBinarySearchTree<>();

    private int countOfConsistentNodes;
    private int countOfBalanceFactors;

    private List<Integer> listOfPreOrderTraversed = new ArrayList<>();
    private List<Integer> listOfPostOrderTraversed = new ArrayList<>();

    @Test
    public void insertElementToEmptyTree() {
        boolean insertElement = testAvlTree.insert(55);

        assertTrue(insertElement);
    }

    @Test
    public void insertElementToNonEmptyTree() {
        testAvlTree = RecursiveAvlTree.of(2, 4, 13, 8, 17);

        System.out.println(testAvlTree.getSize());
        boolean insertElement = testAvlTree.insert(10);

        assertTrue(insertElement);
        assertEquals(6, testAvlTree.getSize());
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
        assertEquals(0, testAvlTree.getHeight());
    }

    @Test
    public void testHeightOfSingleElementTree(){
        testAvlTree.insert(55);
        assertEquals(0, testAvlTree.getHeight());
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
        assertTrue(isConsistentTree(testAvlTree));
    }

    @Test
    public void testSizeOfEmptyTree() {
        int actualTreeSize = testAvlTree.getSize();

        assertThat(actualTreeSize, is(0));
            assertTrue(isConsistentTree(testAvlTree));
    }

    @Test
    public void testSize() {
        testAvlTree = RecursiveAvlTree.of(98, 97, 99, 96, 94);

        int actualTreeSize = testAvlTree.getSize();

        assertThat(actualTreeSize, is(5));
        assertTrue(isConsistentTree(testAvlTree));
        assertTrue(isAVL(testAvlTree));

        System.out.println("getSize" + testAvlTree.getSize());
        System.out.println("countOfBalanceFactors" + countOfBalanceFactors);
        System.out.println("countOfConsistentNodes" + countOfConsistentNodes);
    }


    @Test
    public void testInorderTraversalOfEmptyTree() {
        List<Integer> treeElementsList = new ArrayList<>(testAvlTree.getSize());

        testAvlTree.makeInOrderTraversal(treeElementsList::add);

        assertThat(treeElementsList, empty());
    }

    @Test
    public void testInOrderTraversal(){
        testAvlTree = RecursiveAvlTree.of(19, 17, 13, 11);

        List<Integer> treeElementsList = new ArrayList<>(testAvlTree.getSize());
        testAvlTree.makeInOrderTraversal(treeElementsList::add);

        assertThat(treeElementsList.size(), is(testAvlTree.getSize()));
        assertThat(treeElementsList, contains(11, 13, 17, 19));
    }

    private boolean isConsistentTree(RecursiveAvlTree<Integer> testAvlTree){
        testAvlTree.makeInOrderTraversal(this::hasConsistentRelations);
        return testAvlTree.getSize()==countOfConsistentNodes;
    }

    private void hasConsistentRelations(Integer element){
        RecursiveAvlTree.Node<Integer> root = testAvlTree.root;
        RecursiveAvlTree.Node<Integer> targetNode  = testAvlTree.searchInSubtree(root, element);
        RecursiveAvlTree.Node<Integer> parent = targetNode.parent;
        RecursiveAvlTree.Node<Integer> left = targetNode.left;
        RecursiveAvlTree.Node<Integer> right = targetNode.right;

        if(isParentConsistent(targetNode, parent)
                && isLeftConsistent(targetNode, left)
                && isRightConsistent(targetNode, right)){
            countOfConsistentNodes++;
        }
    }

    private boolean isParentConsistent(RecursiveAvlTree.Node<Integer> targetNode, RecursiveAvlTree.Node<Integer> parentNode) {
        if(parentNode!=null) {
            if (parentNode.element > targetNode.element) {
                return parentNode == targetNode.parent && parentNode.left == targetNode;
            } else {
                return parentNode == targetNode.parent && parentNode.right == targetNode;
            }
        }
        return true;
    }

    private boolean isLeftConsistent(RecursiveAvlTree.Node<Integer> targetNode, RecursiveAvlTree.Node<Integer> leftNode) {
        if(leftNode!=null) {
            return leftNode.parent == targetNode && leftNode == targetNode.left;
        }
        return true;
    }

    private boolean isRightConsistent(RecursiveAvlTree.Node<Integer> targetNode, RecursiveAvlTree.Node<Integer> rightNode) {
        if(rightNode!=null) {
            return rightNode.parent == targetNode && rightNode == targetNode.right;
        }
        return true;
    }

    private boolean isAVL(RecursiveAvlTree<Integer> testAvlTree){
        testAvlTree.makeInOrderTraversal(element -> checkBalanceFactorAtElement(element));
        return countOfBalanceFactors==testAvlTree.getSize();
    }

        private void checkBalanceFactorAtElement(Integer element){
        RecursiveAvlTree.Node<Integer> root = testAvlTree.root;
        RecursiveAvlTree.Node<Integer> targetNode = testAvlTree.searchInSubtree(root, element);
        if(testAvlTree.balanceFactorAt(targetNode) < 2 && testAvlTree.balanceFactorAt(targetNode) > -2){
            countOfBalanceFactors++;
        }
    }

    @Test
    public void testTraverse(){
        testAvlTree = RecursiveAvlTree.of(9, 5, 20, 3, 7, 18, 40, 1, 16, 30, 50, 28, 38, 100, 22);

        testAvlTree.makePreOrderTraversal(listOfPreOrderTraversed::add);
        testAvlTree.makePostOrderTraversal(listOfPostOrderTraversed::add);

        System.out.println(listOfPreOrderTraversed);
        System.out.println(listOfPostOrderTraversed);
    }


    /**
     *              3                       9
     *               \                    /   \
     *                9         =>       3    13
     *                 \
     *                  13
     */
    @Test
    public void testLeftRorationAtRoot(){
        testAvlTree = RecursiveAvlTree.of(3, 9, 13);

        testAvlTree.makePreOrderTraversal(listOfPreOrderTraversed::add);

        assertEquals(1, testAvlTree.getHeight());
        assertEquals(3, testAvlTree.getSize());
        //Expected listOfPreOrderTraversed - [9, 3, 13]
        assertEquals(9, listOfPreOrderTraversed.get(0).intValue());
        assertEquals(3, listOfPreOrderTraversed.get(1).intValue());
        assertEquals(13, listOfPreOrderTraversed.get(2).intValue());
    }

    /**
     *                  6                   4
     *                 /                  /  \
     *               4          =>       2    6
     *              /
     *            2
     */
    @Test
    public void testRightRorationAtRoot(){
        testAvlTree = RecursiveAvlTree.of(6, 4, 2);
        bst = RecursiveBinarySearchTree.of(6, 4, 2);

        testAvlTree.makePreOrderTraversal(listOfPreOrderTraversed::add);

        assertEquals(1, testAvlTree.getHeight());
        assertEquals(3, testAvlTree.getSize());
        //Expected listOfPreOrderTraversed - [4, 2, 6]
        assertEquals(4, listOfPreOrderTraversed.get(0).intValue());
        assertEquals(2, listOfPreOrderTraversed.get(1).intValue());
        assertEquals(6, listOfPreOrderTraversed.get(2).intValue());
    }

    /**
     *                  7                          7
     *                 / \                        / \
     *               5    8                     3    8
     *              / \    \                  /  \    \
     *            3    6    9     =>        2     5    9
     *           / \                      /     /  \
     *         2    4                   1      4    6
     *        /
     *      1
     */
    @Test
    public void testRightRorationInTheMiddle(){
        testAvlTree = RecursiveAvlTree.of(7, 5, 8, 3, 6, 9, 2, 4, 1);

        testAvlTree.makePreOrderTraversal(listOfPreOrderTraversed::add);

        assertEquals(3, testAvlTree.getHeight());
        assertEquals(9, testAvlTree.getSize());
        //Expected listOfPreOrderTraversed - [7, 3, 2, 1, 5, 4, 6, 8, 9]
        assertEquals(7, listOfPreOrderTraversed.get(0).intValue());
        assertEquals(3, listOfPreOrderTraversed.get(1).intValue());
        assertEquals(2, listOfPreOrderTraversed.get(2).intValue());
        assertEquals(1, listOfPreOrderTraversed.get(3).intValue());
        assertEquals(5, listOfPreOrderTraversed.get(4).intValue());
        assertEquals(4, listOfPreOrderTraversed.get(5).intValue());
        assertEquals(6, listOfPreOrderTraversed.get(6).intValue());
        assertEquals(8, listOfPreOrderTraversed.get(7).intValue());
        assertEquals(9, listOfPreOrderTraversed.get(8).intValue());
    }

    /**
     *            3                         3
     *           / \                       / \
     *         2    5                    2    7
     *        /    / \           =>     /    / \
     *      1    4    7               1    5    8
     *               / \                  / \    \
     *             6    8               4    6    9
     *                   \
     *                    9
     */
    @Test
    public void testLeftRorationInTheMiddle(){
        testAvlTree = RecursiveAvlTree.of(3, 2, 5, 1, 4, 7, 6, 8, 9);

        testAvlTree.makePreOrderTraversal(listOfPreOrderTraversed::add);
        testAvlTree.makePostOrderTraversal(listOfPostOrderTraversed::add);

        assertEquals(3, testAvlTree.getHeight());
        assertEquals(9, testAvlTree.getSize());
        //Expected listOfPreOrderTraversed - [3, 2, 1, 7, 5, 4, 6, 8, 9]
        assertEquals(3, listOfPreOrderTraversed.get(0).intValue());
        assertEquals(2, listOfPreOrderTraversed.get(1).intValue());
        assertEquals(1, listOfPreOrderTraversed.get(2).intValue());
        assertEquals(7, listOfPreOrderTraversed.get(3).intValue());
        assertEquals(5, listOfPreOrderTraversed.get(4).intValue());
        assertEquals(4, listOfPreOrderTraversed.get(5).intValue());
        assertEquals(6, listOfPreOrderTraversed.get(6).intValue());
        assertEquals(8, listOfPreOrderTraversed.get(7).intValue());
        assertEquals(9, listOfPreOrderTraversed.get(8).intValue());
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

        testAvlTree.makePreOrderTraversal(listOfPreOrderTraversed::add);

        assertEquals(4, testAvlTree.getHeight());
        assertEquals(15, testAvlTree.getSize());
        //Expected listOfPreOrderTraversed - [5, 3, 2, 1, 4, 11, 8, 7, 6, 10, 9, 13, 12, 14, 15]
        assertEquals(5, listOfPreOrderTraversed.get(0).intValue());
        assertEquals(3, listOfPreOrderTraversed.get(1).intValue());
        assertEquals(2, listOfPreOrderTraversed.get(2).intValue());
        assertEquals(1, listOfPreOrderTraversed.get(3).intValue());
        assertEquals(4, listOfPreOrderTraversed.get(4).intValue());
        assertEquals(11, listOfPreOrderTraversed.get(5).intValue());
        assertEquals(8, listOfPreOrderTraversed.get(6).intValue());
        assertEquals(7, listOfPreOrderTraversed.get(7).intValue());
        assertEquals(6, listOfPreOrderTraversed.get(8).intValue());
        assertEquals(10, listOfPreOrderTraversed.get(9).intValue());
        assertEquals(9, listOfPreOrderTraversed.get(10).intValue());
        assertEquals(13, listOfPreOrderTraversed.get(11).intValue());
        assertEquals(12, listOfPreOrderTraversed.get(12).intValue());
        assertEquals(14, listOfPreOrderTraversed.get(13).intValue());
        assertEquals(15, listOfPreOrderTraversed.get(14).intValue());
    }

    /**
     *            8                          8                         6
     *           / \                        / \                       / \
     *         3    10                    6    10                   3    8
     *        /\    /\           =>      /\    /\          =>      /\    /\
     *      2   6  9  11                3  7  9  11               2  5  7  10
     *     /   /\                      / \                       /  /      /\
     *   1   5   7                    2   5                     1  4      9  11
     *      /                        /   /
     *    4                         1   4
     */
    @Test
    public void testLeftRightRorationInTheRoot(){
        testAvlTree = RecursiveAvlTree.of(8, 3, 10, 2, 6, 9, 11, 1, 5, 7, 4);

        testAvlTree.makePreOrderTraversal(listOfPreOrderTraversed::add);

        assertEquals(3, testAvlTree.getHeight());
        assertEquals(11, testAvlTree.getSize());
        //Expected listOfPreOrderTraversed - [6, 3, 2, 1, 5, 4, 8, 7, 10, 9, 11]
        assertEquals(6, listOfPreOrderTraversed.get(0).intValue());
        assertEquals(3, listOfPreOrderTraversed.get(1).intValue());
        assertEquals(2, listOfPreOrderTraversed.get(2).intValue());
        assertEquals(1, listOfPreOrderTraversed.get(3).intValue());
        assertEquals(5, listOfPreOrderTraversed.get(4).intValue());
        assertEquals(4, listOfPreOrderTraversed.get(5).intValue());
        assertEquals(8, listOfPreOrderTraversed.get(6).intValue());
        assertEquals(7, listOfPreOrderTraversed.get(7).intValue());
        assertEquals(10, listOfPreOrderTraversed.get(8).intValue());
        assertEquals(9, listOfPreOrderTraversed.get(9).intValue());
        assertEquals(11, listOfPreOrderTraversed.get(10).intValue());
    }

}
