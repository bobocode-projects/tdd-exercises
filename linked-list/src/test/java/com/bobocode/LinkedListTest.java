package com.bobocode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class LinkedListTest {

    private List<Integer> testLinkedList = new LinkedList<>();

    @Test
    public void testCreateLinkedList() {
        testLinkedList = LinkedList.of(1, 2, 3);
    }

    @Test
    public void testAddElement() {
        testLinkedList.add(7);
        Integer expectedSeven = testLinkedList.get(0);

        assertNotNull(expectedSeven);
        assertThat(testLinkedList.size(), is(1));
    }

    @Test
    public void testAddElements() {
        testLinkedList = LinkedList.of(1, 2, 3, 5, 8, 13);

        Integer[] actualArray = toArrayOfIntegers(testLinkedList);
        Integer[] expectedArray = {1, 2, 3, 5, 8, 13};

        assertArrayEquals(expectedArray, actualArray);
        assertThat(testLinkedList.size(), is(6));
    }

    private static Integer[] toArrayOfIntegers(List<Integer> testList) {
        Integer[] newArray = new Integer[testList.size()];
        for (int i = 0; i < testList.size(); i++) {
            newArray[i] = testList.get(i);
        }
        return newArray;
    }

    @Test
    public void testAddLastElementByIndex() {
        testLinkedList = LinkedList.of(1, 2, 4, 8, 16);
        testLinkedList.add(5, 32);

        Integer[] actualArray = toArrayOfIntegers(testLinkedList);
        Integer[] expectedArray = {1, 2, 4, 8, 16, 32};

        assertArrayEquals(expectedArray, actualArray);
        assertThat(testLinkedList.size(), is(6));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddElementByIndexExeedingSize() {
        testLinkedList = LinkedList.of(1, 2, 3, 4, 5);

        testLinkedList.add(7, 66);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddElementByNegativeIndex() {
        testLinkedList = LinkedList.of(1, 2, 3, 4, 5);

        testLinkedList.add(-1, 13);
    }

    @Test
    public void testAddFirstElementByIndex() {
        testLinkedList = LinkedList.of(5, 25, 125);

        testLinkedList.add(0, 1);
        Integer[] actualArray = toArrayOfIntegers(testLinkedList);
        Integer[] expectedArray = {1, 5, 25, 125};

        assertArrayEquals(expectedArray, actualArray);
        assertThat(testLinkedList.size(), is(4));
    }

    @Test
    public void testAddFirstElementByIndexToEmptyList() {
        testLinkedList = LinkedList.of();

        testLinkedList.add(0, 99);
        Integer[] actualArray = toArrayOfIntegers(testLinkedList);
        Integer[] expectedArray = {99};

        assertArrayEquals(expectedArray, actualArray);
        assertThat(testLinkedList.size(), is(1));
    }

    @Test
    public void testAddElementByIndexAndValue() {
        testLinkedList = LinkedList.of(1, 9, 27);

        testLinkedList.add(1, 3);
        Integer[] actualArray = toArrayOfIntegers(testLinkedList);
        Integer[] expectedArray = {1, 3, 9, 27};

        assertArrayEquals(expectedArray, actualArray);
        assertThat(testLinkedList.size(), is(4));
    }

    @Test
    public void testChangeElement() {
        testLinkedList = LinkedList.of(11, 24, 33);

        testLinkedList.set(1, 22);

        assertSame(22, testLinkedList.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testChangeElementExeedingSize() {
        testLinkedList = LinkedList.of(1, 5, 9);

        testLinkedList.set(3, 68);
    }

    @Test
    public void testGetElement(){
        testLinkedList = LinkedList.of(5, 5, 5);

        assertSame(5, testLinkedList.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetElementExeedingSize(){
        testLinkedList = LinkedList.of(5, 5, 5);

        testLinkedList.get(11);
    }

    @Test
    public void testGetFirst() {
        testLinkedList = LinkedList.of(9, 8, 7, 6, 5, 4);

        assertSame(9, testLinkedList.getFirst());
    }

    @Test(expected = NullPointerException.class)
    public void testGetFirstFromEmptyList() {
        testLinkedList = LinkedList.of();

        testLinkedList.getFirst();
    }

    @Test
    public void testGetLast() {
        testLinkedList = LinkedList.of(9, 8, 7, 6, 5, 4);

        assertSame(4, testLinkedList.getLast());
    }

    @Test(expected = NullPointerException.class)
    public void testGetLastFromEmptyList() {
        testLinkedList = LinkedList.of();

        testLinkedList.getLast();
    }

    @Test
    public void testRemove() {
        testLinkedList = LinkedList.of(3, 6, 9);

        testLinkedList.remove(1);
        Integer[] actualArray = toArrayOfIntegers(testLinkedList);
        Integer[] expectedArray = {3, 9};

        assertArrayEquals(expectedArray, actualArray);
        assertThat(testLinkedList.size(), is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveFromEmpty() {
        testLinkedList = LinkedList.of();

        testLinkedList.remove(4);
    }

    @Test
    public void testRemoveHead() {
        testLinkedList = LinkedList.of(8, 5, 2);

        testLinkedList.remove(0);
        Integer[] actualArray = toArrayOfIntegers(testLinkedList);
        Integer[] expectedArray = {5, 2};

        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void testContainsElement(){
        testLinkedList = LinkedList.of(7, 5, 3);

        assertTrue(testLinkedList.contains(5));
    }

    @Test
    public void testContainsElementInEmpty(){
        testLinkedList = LinkedList.of();

        assertFalse(testLinkedList.contains(5));
    }

    @Test
    public void testIsEmptyOnNonEmpty(){
        testLinkedList = LinkedList.of(7, 7, 7);

        assertFalse(testLinkedList.isEmpty());
    }

    @Test
    public void testIsEmptyOnEmpty(){
        testLinkedList = LinkedList.of();

        assertTrue(testLinkedList.isEmpty());
    }

    @Test
    public void testClear(){
        testLinkedList = LinkedList.of(2, 4, 8, 16);

        testLinkedList.clear();
        Integer[] actualArray = toArrayOfIntegers(testLinkedList);
        Integer[] expectedArray = {};

        assertArrayEquals(expectedArray, actualArray);
        assertTrue(testLinkedList.isEmpty());
    }
}
