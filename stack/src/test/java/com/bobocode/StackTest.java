package com.bobocode;

import com.bobocode.exception.EmptyStackException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class StackTest {

    @Test
    public void testPushElementOntoEmptyStack() {
        Stack<Integer> intStack = new NodeStack<>();

        intStack.push(234);
    }

    @Test(expected = EmptyStackException.class)
    public void testPopElementFromEmptyStack() {
        Stack<Integer> intStack = new NodeStack<>();

        intStack.pop();
    }

    @Test
    public void testPushElements() {
        Stack<Integer> intStack = NodeStack.of(23, 35, 72);

        intStack.push(55);

        assertThat(intStack.pop(), is(55));
    }

    @Test
    public void testPopElements() {
        Stack<Integer> intStack = NodeStack.of(87, 53, 66);

        intStack.pop();
        intStack.push(234);
        Integer lastElement = intStack.pop();

        assertThat(lastElement, is(234));
    }

    @Test
    public void testSize(){
        Stack<Integer> intStack = NodeStack.of(87, 53, 66);

        int actualSize = intStack.size();

        assertThat(actualSize, is(3));
    }

    @Test
    public void testSizeOnEmptyStack(){
        Stack<Integer> intStack = new NodeStack<>();

        int actualSize = intStack.size();

        assertThat(actualSize, is(0));
    }

    @Test
    public void testIsEmpty(){
        Stack<Integer> intStack = NodeStack.of(87, 53, 66);

        boolean stackEmpty = intStack.isEmpty();

        assertThat(stackEmpty, is(false));
    }

    @Test
    public void testIsEmptyOnEmptyStack(){
        Stack<Integer> intStack = new NodeStack<>();

        boolean stackEmpty = intStack.isEmpty();

        assertThat(stackEmpty, is(true));
    }
}
