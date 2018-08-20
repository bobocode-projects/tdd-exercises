package com.bobocode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.EmptyStackException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(JUnit4.class)
public class StackTest {
    private Stack<Integer> stack = new OwnStack<>();

    @Test
    public void validateEmptyMethod() {
        stack.push(12);
        stack.push(14);
        stack.push(16);

        assertThat("Stack should not be empty", !stack.isEmpty());
    }

    @Test
    public void validateSizeAddingMethod() {
        stack.push(12);
        stack.push(14);

        assertThat("Size method is wrong", stack.size(), equalTo(2));
    }

    @Test
    public void validateSizeDeletingMethod() {
        stack = OwnStack.valueOf(12, 14);
        stack.pop();

        assertThat("Size method is wrong", stack.size(), equalTo(1));
    }

    @Test
    public void validatePushWithOneElementMethod() {
        stack.push(12);

        Integer element = stack.pop();
        assertThat("Wrong number", element, equalTo(12));
    }

    @Test
    public void validatePushWithManyAddingMethod() {
        stack = OwnStack.valueOf(12, 14, 16);

        Integer element = stack.pop();
        assertThat("Wrong number", element, equalTo(16));
    }

    @Test
    public void validatePopWithManyAddingMethod() {
        stack = OwnStack.valueOf(12, 14, 16);

        stack.pop();
        Integer element = stack.pop();
        assertThat("Wrong number", element, equalTo(14));
    }

    @Test(expected = EmptyStackException.class)
    public void validatePopEmptyStack() {
        stack.pop();
    }

    @Test(expected = EmptyStackException.class)
    public void validatePopWithNotEmptyStack() {
        stack = OwnStack.valueOf(12, 14);
        stack.pop();
        stack.pop();
        stack.pop();
    }

    @Test
    public void validatePushWithEmptyValuesForEmptyStack() {
        stack.push(null);
        stack.push(null);
        stack.push(null);

        assertThat("Stack should be empty", stack.isEmpty());
        assertThat("Size for pushed null values should be 0", stack.size(), equalTo(0));
    }

    @Test
    public void validatePushWithEmptyValues() {
        stack.push(12);
        stack.push(10);
        stack.push(null);

        assertThat("Stack should not be empty", !stack.isEmpty());
        assertThat("Size for pushed null values should be 0", stack.size(), equalTo(2));
    }

    @Test
    public void validateEmptyStack() {
        assertThat("Stack should be empty", stack.isEmpty());
        assertThat("Size of empty stack should be 0", stack.size(), equalTo(0));
    }

}
