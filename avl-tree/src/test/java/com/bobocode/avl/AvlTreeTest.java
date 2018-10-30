package com.bobocode.avl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class AvlTreeTest {
    AvlTree<Integer> testAvlTree = new RecursiveAvlTree<>();

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
    public void testHeight(){
        testAvlTree = RecursiveAvlTree.of(33, 20, 11, 15);

        assertEquals(2, testAvlTree.height());
    }

    @Test
    public void testSizeOfEmptyTree() {
        int actualTreeSize = testAvlTree.size();

        assertThat(actualTreeSize, is(0));
    }

    @Test
    public void testSize() {
        testAvlTree = RecursiveAvlTree.of(98, 97, 96, 95, 94);

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

    @Test
    public void testRebalancing(){
        testAvlTree = RecursiveAvlTree.of(77, 44, 22, 30);

        assertEquals(3, testAvlTree.height());
    }
}
