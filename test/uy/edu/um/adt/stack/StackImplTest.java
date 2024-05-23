package uy.edu.um.adt.stack;

import org.junit.jupiter.api.Test;
import uy.edu.um.adt.linkedlist.MyLinkedListImpl;

import static org.junit.jupiter.api.Assertions.*;

class StackImplTest {
    @Test
    public void testFlujoCompleto() {
        MyStack<Integer> colStack = new MyLinkedListImpl<>();

        colStack.push(2);
        colStack.push(4);
        colStack.push(7);

        assertEquals(7, colStack.peek());

        try {

            assertEquals(7, colStack.pop());

        } catch (EmptyStackException e) {

            fail(e.getMessage());

        }

        assertEquals(4, colStack.peek());

        try {

            assertEquals(4, colStack.pop());

        } catch (EmptyStackException e) {

            fail(e.getMessage());

        }

        try {

            assertEquals(2, colStack.pop());

        } catch (EmptyStackException e) {

            fail(e.getMessage());

        }
        try {

            colStack.pop();

            fail("El stack deberia estar vacio");

        } catch (EmptyStackException e) {

            assertTrue(true);

        }
    }


}