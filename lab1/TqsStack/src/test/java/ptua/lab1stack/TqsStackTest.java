package ptua.lab1stack;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TqsStackTest {

    private TqsStack<String> stack;

    @BeforeEach
    void setUp() {
        stack = new TqsStack<String>();
    }


    @DisplayName("A stack is empty on construction.")
    @Test
    void isEmpty() {
        assertTrue(stack.isEmpty());
    }

    @DisplayName("A stack has size 0 on construction.")
    @Test
    void sizeOnConstruction() {
        assertEquals(0, stack.size());
    }


    @DisplayName("After n pushes to an empty stack, n > 0, the stack is not empty and its size is n")
    @Test
    void sizeAfterPush() {

        for (int i = 0; i < 5; i++) {
            stack.push(String.valueOf(i));
        }

        assertFalse(stack.isEmpty());
        assertEquals(5, stack.size());
    }

    @DisplayName("If one pushes x then pops, the value popped is x.")
    @Test
    void pushThenPop() {
        stack.push("5");
        assertEquals("5", stack.pop());
    }

    @DisplayName("If one pushes x then peeks, the value returned is x, but the size stays the same")
    @Test
    void pushThenPeek() {
        stack.push("5");
        int size = stack.size();
        assertEquals("5", stack.peek());
        assertEquals(size, stack.size());
    }

    @DisplayName("If the size is n, then after n pops, the stack is empty and has a size 0")
    @Test
    void pushSizeTimes() {
        stack.push("String1");
        stack.push("String2");
        stack.push("String3");

        stack.pop();
        stack.pop();
        stack.pop();

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }


    @DisplayName("Popping from an empty stack does throw a NoSuchElementException")
    @Test
    void popEmpty() {
        assertThrows(NoSuchElementException.class, () -> {
            stack.pop();
        });
    }

    @DisplayName("Peeking into an empty stack does throw a NoSuchElementException")
    @Test
    void peekEmpty() {
        assertThrows(NoSuchElementException.class, () -> {
            stack.peek();
        });
    }

    @DisplayName("For bounded stacks only, pushing onto a full stack does throw an IllegalStateException")
    @Test
    void pushFullStack() {
        stack = new TqsStack<String>(3);

        stack.push("String1");
        stack.push("String2");
        stack.push("String3");

        assertThrows(IllegalStateException.class, () -> {
            stack.push("String4"); 
        });
    }
}