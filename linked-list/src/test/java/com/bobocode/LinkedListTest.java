package com.bobocode;


import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LinkedListTest {

    private List<Integer> intList = new LinkedList<>();

    @Test
    public void testAddElementIntoEmptyList() {
        intList.add(11);

        assertEquals(11, intList.getFirst().intValue());
        assertEquals(1, intList.size());
    }

    @Test
    public void testAddElements() {
        intList.add(10);
        intList.add(11);
        intList.add(12);

        assertEquals(10, intList.get(0).intValue());
        assertEquals(11, intList.get(1).intValue());
        assertEquals(12, intList.get(2).intValue());
        assertEquals(3, intList.size());
    }

    @Test
    public void testCreateListOfElements() {
        intList = LinkedList.of(21, 22);

        assertEquals(21, intList.get(0).intValue());
        assertEquals(22, intList.get(1).intValue());
        assertEquals(2, intList.size());
    }

    @Test
    public void testSize() {
        intList = LinkedList.of(1, 2, 3, 4, 5);

        assertEquals(5, intList.size());
    }

    @Test
    public void testGetFirstElement() {
        intList = LinkedList.of(31, 32);

        assertEquals(31, intList.getFirst().intValue());
    }

    @Test
    public void testGetLastElement() {
        intList = LinkedList.of(41, 42);

        assertEquals(42, intList.getLast().intValue());
    }

    @Test
    public void testGetFirstOfEmptyList() {
        assertThrows(NoSuchElementException.class, () -> intList.getFirst());
    }

    @Test
    public void testGetLastOfEmptyList() {
        assertThrows(NoSuchElementException.class, () -> intList.getLast());
    }

    @Test
    public void testGetElementByIndex() {
        intList = LinkedList.of(50, 51, 52);

        assertEquals(50, intList.get(0).intValue());
        assertEquals(51, intList.get(1).intValue());
        assertEquals(52, intList.get(2).intValue());
    }

    @Test
    public void testGetElementByIndexOfEmptyTree() {
        assertThrows(IndexOutOfBoundsException.class, () -> intList.get(1));
    }

    @Test
    public void testGetElementByNegativeIndex() {
        intList = LinkedList.of(1, 2, 3);

        assertThrows(IndexOutOfBoundsException.class, () -> intList.get(-2));
    }

    @Test
    public void testGetElementBySizeIndex() {
        intList = LinkedList.of(1, 2, 3);

        assertThrows(IndexOutOfBoundsException.class, () -> intList.get(3));
    }

    @Test
    public void testAddElementByIndex() {
        intList = LinkedList.of(60, 62);

        intList.add(1, 61);

        assertEquals(60, intList.get(0).intValue());
        assertEquals(61, intList.get(1).intValue());
        assertEquals(62, intList.get(2).intValue());
        assertEquals(3, intList.size());
    }

    @Test
    public void testAddElementByZeroIndex() {
        intList = LinkedList.of(71);

        intList.add(0, 70);

        assertEquals(70, intList.get(0).intValue());
        assertEquals(71, intList.get(1).intValue());
        assertEquals(2, intList.size());
    }

    @Test
    public void testAddElementByZeroIndexToEmptyList() {
        intList.add(0, 80);

        assertEquals(80, intList.get(0).intValue());
        assertEquals(1, intList.size());
    }

    @Test
    public void testAddElementByIndexToTail() {
        intList = LinkedList.of(90, 91);

        intList.add(2, 92);

        assertEquals(90, intList.get(0).intValue());
        assertEquals(91, intList.get(1).intValue());
        assertEquals(92, intList.get(2).intValue());
        assertEquals(92, intList.getLast().intValue());
    }

    @Test
    public void testSetElementByIndex() {
        intList = LinkedList.of(100, 101, 102);

        intList.set(1, 99);

        assertEquals(100, intList.get(0).intValue());
        assertEquals(99, intList.get(1).intValue());
        assertEquals(102, intList.get(2).intValue());
        assertEquals(3, intList.size());
    }

    @Test
    public void testSetElementByInvalidIndex() {
        intList = LinkedList.of(110, 111);

        assertThrows(IndexOutOfBoundsException.class, () -> intList.set(3, 114));
    }

    @Test
    public void testRemoveElementByIndex() {
        intList = LinkedList.of(120, 121, 122);

        intList.remove(1);

        assertEquals(120, intList.get(0).intValue());
        assertEquals(122, intList.get(1).intValue());
        assertEquals(2, intList.size());
    }

    @Test
    public void testRemoveHeadElement() {
        intList = LinkedList.of(130, 131, 132);

        intList.remove(0);

        assertEquals(131, intList.get(0).intValue());
        assertEquals(131, intList.getFirst().intValue());
        assertEquals(132, intList.get(1).intValue());
        assertEquals(132, intList.getLast().intValue());
        assertEquals(2, intList.size());
    }


    @Test
    public void testRemoveTailElement() {
        intList = LinkedList.of(140, 141, 142);

        intList.remove(2);

        assertEquals(140, intList.get(0).intValue());
        assertEquals(141, intList.get(1).intValue());
        assertEquals(141, intList.getLast().intValue());
        assertEquals(2, intList.size());
    }

    @Test
    public void testRemoveSingleElementByIndex() {
        intList = LinkedList.of(150);

        intList.remove(0);

        assertTrue(intList.isEmpty());
        assertEquals(0, intList.size());
    }

    @Test
    public void testRemoveElementOfEmptyList() {
        assertThrows(IndexOutOfBoundsException.class, () -> intList.remove(0));
    }

    @Test
    public void testIsEmpty() {
        assertFalse(LinkedList.of(1).isEmpty());
        assertTrue(new LinkedList<>().isEmpty());
    }

    @Test
    public void testContains() {
        intList = LinkedList.of(1, 2, 3);

        assertTrue(intList.contains(2));
        assertFalse(intList.contains(5));
    }

    @Test
    public void testClear() {
        intList = LinkedList.of(1, 2);

        intList.clear();

        assertTrue(intList.isEmpty());
        assertEquals(0, intList.size());
    }
}
