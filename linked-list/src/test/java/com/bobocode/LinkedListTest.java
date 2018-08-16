package com.bobocode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LinkedListTest {

    private LinkedList<Integer> list = new LinkedList<>();

    @Test
    public void testAddElement() {
        list.add(34);
    }


}
