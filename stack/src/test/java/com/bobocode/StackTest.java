package com.bobocode;

import com.bobocode.exception.EmptyStackException;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StackTest {

    private Stack<Integer> intStack = new NodeStack<>();

    @Test
    public void testPushElementOntoEmptyStack() {
        intStack.push(234);
    }

    @Test
    public void testPopElementFromEmptyStack() {
        assertThrows(EmptyStackException.class, () -> intStack.pop());
    }

    @Test
    public void testPushElements() {
        intStack = NodeStack.of(23, 35, 72);

        intStack.push(55);

        assertThat(intStack.pop(), is(55));
    }

    @Test
    public void testPopElements() {
        intStack = NodeStack.of(87, 53, 66);

        intStack.pop();
        intStack.push(234);
        Integer lastElement = intStack.pop();

        assertThat(lastElement, is(234));
    }

    @Test
    public void testSize(){
        intStack = NodeStack.of(87, 53, 66);

        int actualSize = intStack.size();

        assertThat(actualSize, is(3));
    }

    @Test
    public void testSizeOnEmptyStack(){
        int actualSize = intStack.size();

        assertThat(actualSize, is(0));
    }

    @Test
    public void testIsEmpty(){
        intStack = NodeStack.of(87, 53, 66);

        boolean stackEmpty = intStack.isEmpty();

        assertThat(stackEmpty, is(false));
    }

    @Test
    public void testIsEmptyOnEmptyStack(){
        boolean stackEmpty = intStack.isEmpty();

        assertThat(stackEmpty, is(true));
    }
}
