package com.bobocode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.TestCase.*;

@RunWith(JUnit4.class)
public class LinkedListTest {

    private LinkedList<Integer> someList = new LinkedList<>();

    @Test
    public void testAddToEmptyList() {
        someList.add(5);

        assertEquals(1, someList.size());
    }

    @Test
    public void testGetFirstElement() {
        someList.add(33);
        int value = someList.getFirst();
        assertEquals(33, value);
    }

    @Test
    public void testAddElements() {
        someList = (LinkedList<Integer>) LinkedList.of(34, 35, 36, 37);
        assertEquals(4, someList.size());
    }

    @Test
    public void testGetElements() {
        someList = (LinkedList<Integer>) LinkedList.of(34, 51, 53);

        int first = someList.get(0);
        int second = someList.get(1);
        int third = someList.get(2);

        assertEquals(34, first);
        assertEquals(51, second);
        assertEquals(53, third);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetFirstElementFromEmpty() {
        someList.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetElementByNegativeIndex() {
        someList.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetElementByIndexEqualToSize() {
        someList = (LinkedList<Integer>) LinkedList.of(3, 4, 5);
        someList.get(3);
    }

    @Test
    public void testAddElementByZeroIndexIntoEmptyList() {
        someList.add(0, 45);

        assertEquals(1, someList.size());
    }

    @Test
    public void testAddElementByIndexToTheEndOfList() {
        someList = (LinkedList<Integer>) LinkedList.of(98, 64, 23, 1, 3, 4);

        int newElementIndex = someList.size();
        someList.add(newElementIndex, 44);

        assertEquals(44, someList.get(newElementIndex).intValue());
    }

    @Test
    public void testAddElementToTheHeadOfNonEmptyList() {
        someList = (LinkedList<Integer>) LinkedList.of(4, 6, 8, 9, 0, 2);

        someList.add(0, 53);

        assertEquals(53, someList.get(0).intValue());
        assertEquals(4, someList.get(1).intValue());
        assertEquals(7, someList.size());
    }

    @Test
    public void testAddElementByIndex() {
        someList = (LinkedList<Integer>) LinkedList.of(43, 5, 6, 8);

        int newElementIdx = 2;
        someList.add(newElementIdx, 66);

        assertEquals(66, someList.get(newElementIdx).intValue());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddElementByNegativeIndex() {
        someList.add(-1, 66);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddElementByIndexLargerThanListSize() {
        someList = (LinkedList<Integer>) LinkedList.of(4, 6, 11, 9);

        int newElementIdx = 5;
        someList.add(newElementIdx, 88);
    }

    @Test
    public void testAddElementByIndexEqualToSize() {
        someList = (LinkedList<Integer>) LinkedList.of(1, 2, 3, 4, 5); // size = 5

        someList.add(5, 111);

        assertEquals(6, someList.size());
        assertEquals(111, someList.get(5).intValue());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetFirstElementOnEmptyTree() {
        someList.set(0, 34);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetElementByIndexEqualToSize() {
        someList = (LinkedList<Integer>) LinkedList.of(2, 3, 4); // size = 3

        someList.set(3, 222);
    }

    @Test
    public void testSetElement() {
        someList = (LinkedList<Integer>) LinkedList.of(34, 78, 9, 8);

        int index = 2; //element = 78
        someList.set(index, 99);

        assertEquals(99, someList.get(index).intValue());

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveElementFromEmptyList() {
        someList.remove(234);
    }

    @Test
    public void testRemoveFirstElement() {
        someList = (LinkedList<Integer>) LinkedList.of(4, 6, 8, 9);

        someList.remove(0);

        assertEquals(6, someList.get(0).intValue());
    }

    @Test
    public void testRemoveLastElement() {
        someList = (LinkedList<Integer>) LinkedList.of(4, 6, 8, 9);

        someList.remove(someList.size() - 1);

        assertEquals(8, someList.get(someList.size() - 1).intValue());
    }

    @Test
    public void testRemoveElement() {
        someList = (LinkedList<Integer>) LinkedList.of(1, 2, 3, 4, 5);

        int elementIndex = 2;
        someList.remove(elementIndex); // element = 3

        assertEquals(4, someList.get(elementIndex).intValue());
    }

    @Test
    public void testContainsOnEmptyList() {
        boolean contains = someList.contains(34);

        assertFalse(contains);
    }

    @Test
    public void testContains() {
        someList = (LinkedList<Integer>) LinkedList.of(45, 6, 3, 6);

        boolean containsExistingElement = someList.contains(3);
        boolean containsNotExistingElement = someList.contains(54);

        assertTrue(containsExistingElement);
        assertFalse(containsNotExistingElement);
    }

    @Test
    public void testIsEmptyOnEmptyList() {
        boolean empty = someList.isEmpty();

        assertTrue(empty);
    }

    @Test
    public void testIsEmpty() {
        someList = (LinkedList<Integer>) LinkedList.of(34, 5, 6);

        boolean empty = someList.isEmpty();

        assertFalse(empty);
    }

    @Test
    public void testSizeOnEmptyList() {
        int size = someList.size();

        assertEquals(0, size);
    }

    @Test
    public void testSize() {
        someList = (LinkedList<Integer>) LinkedList.of(4, 7, 9, 0, 7);

        int size = someList.size();

        assertEquals(5, size);
    }

    @Test
    public void testClearOnEmptyList() {
        someList.clear();

        assertEquals(0, someList.size());
    }

    @Test
    public void testClearChangesTheSize() {
        someList = (LinkedList<Integer>) LinkedList.of(4, 5, 6);

        someList.clear();

        assertEquals(0, someList.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testClearRemovesElements() {
        someList = (LinkedList<Integer>) LinkedList.of(4, 5, 6);

        someList.clear();
        someList.get(0);
    }

}
