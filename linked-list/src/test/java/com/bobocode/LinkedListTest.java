package com.bobocode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LinkedListTest {
    private List<Integer> intList = new LinkedList<>();

    @Test
    public void addWhenListIsEmptyInsertsElementToHead() {
        Integer element = 1;
        intList.add(element);
        assertEquals(element, intList.getFirst());
    }

    @Test
    public void addWhenListExistsInsertsElementToTail() {
        Integer element1 = 1;
        Integer element2 = 2;
        intList.add(element1);
        intList.add(element2);
        assertEquals(element2, intList.getLast());
        assertEquals(element1, intList.getFirst());
    }

    @Test
    public void addElementIncreasesSizeOfList() {
        assertEquals(0, intList.size());
        Integer element1 = 1;
        Integer element2 = 2;
        intList.add(element1);
        assertEquals(1, intList.size());
        intList.add(element2);
        assertEquals(2, intList.size());
    }

    @Test
    public void sizeReturnsSizeOfList() {
        assertEquals(0, intList.size());
        intList.add(1);
        assertEquals(1, intList.size());
        intList.add(1);
        intList.add(1);
        assertEquals(3, intList.size());
    }

    @Test
    public void addByIndexWhenIndex0InsertsToHead() {
        Integer element = 1;
        intList.add(0, element);
        assertEquals(element, intList.getFirst());
    }

    @Test
    public void addByIndexWhenIndexBetween1AndSizeInsertsToIndexPosition() {
        intList = LinkedList.of(1, 2);
        Integer element3 = 3;
        intList.add(1, element3);
        assertEquals(Integer.valueOf(1), intList.getFirst());
        assertEquals(element3, intList.get(1));
        assertEquals(3, intList.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addByIndexWhenIndexOutOfBoundThrowsIndexOutOfBoundException() {
        intList.add(5, 5);
    }

    @Test
    public void addByIndexWhenIndexEqualsSizeInsertsElementToTail() {
        intList = LinkedList.of(1, 2, 3);
        ;
        intList.add(3, 4);
        assertEquals(Integer.valueOf(4), intList.getLast());
        assertEquals(4, intList.size());
    }

    @Test
    public void clearSetsHeadAndTailToNullAndSizeTo0() {
        intList.clear();
        assertEquals(0, intList.size());
        assertTrue(intList.isEmpty());
    }

    @Test
    public void isEmptyWhenListIsEmptyReturnsTrue() {
        assertTrue(intList.isEmpty());
    }

    @Test
    public void isEmptyWhenListIsNotEmptyReturnsFalse() {
        intList.add(0, 1);
        assertFalse(intList.isEmpty());
    }

    @Test
    public void getFirstWhenListInEmptyReturnsNull() {
        assertNull(intList.getFirst());
    }

    @Test
    public void getLastWhenListInEmptyReturnsNull() {
        assertNull(intList.getFirst());
    }

    @Test
    public void getByIndexReturnsElemenOnIndexPosition() {
        intList = LinkedList.of(1, 2, 3);
        ;
        Integer actual = intList.get(1);
        assertEquals(Integer.valueOf(2), actual);
    }

    @Test
    public void containsWhenElementExistsReturnsTrue() {
        intList = LinkedList.of(1, 2, 3);
        ;
        assertTrue(intList.contains(2));
    }

    @Test
    public void containsWhenElementNotExistsReturnsFalse() {
        intList = LinkedList.of(1, 2, 3);
        assertTrue(intList.contains(2));
    }

    @Test
    public void ofAddsEllementsToList() {
        List<Integer> list = LinkedList.of(1, 2, 3);
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(2), list.get(1));
        assertEquals(Integer.valueOf(3), list.get(2));
    }

    @Test
    public void ofWhenNoParamsReturnsEmptyList() {
        List<Integer> list = LinkedList.of();
        assertTrue(list.isEmpty());
    }

    @Test
    public void setByIndexSetsElementByIndex() {
        intList = LinkedList.of(1, 2, 3);
        intList.set(0, 3);
        assertEquals(Integer.valueOf(3), intList.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setByIndexWhenListIsEmptyThrowsOutOfBoundException() {
        intList.set(0, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setByIndexWhenIndexIsOutOfBoundThrowsOutOfBoundException() {
        intList = LinkedList.of(1, 2, 3);
        intList.set(3, 4);
    }

    @Test
    public void removeWhenElementByIndexExistsRemovesElementFromList() {
        intList = LinkedList.of(1, 2, 3);
        assertEquals(Integer.valueOf(2), intList.get(1));
        intList.remove(1);
        assertEquals(Integer.valueOf(3), intList.get(1));
    }

    @Test
    public void removeWhenIndex0RemovesHeadElementAndDecreaseSize() {
        intList = LinkedList.of(1, 2, 3);
        assertEquals(Integer.valueOf(2), intList.get(1));
        assertEquals(3, intList.size());
        intList.remove(0);
        assertEquals(Integer.valueOf(2), intList.get(0));
        assertEquals(2, intList.size());
    }

    @Test
    public void removeWhenIndexLastRemovesTailElementAndDecreaseSize() {
        intList = LinkedList.of(1, 2, 3);
        assertEquals(Integer.valueOf(3), intList.get(2));
        intList.remove(2);
        assertEquals(Integer.valueOf(2), intList.get(1));
        assertEquals(2, intList.size());
    }

}
