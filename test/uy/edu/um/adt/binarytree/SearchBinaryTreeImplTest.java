package uy.edu.um.adt.binarytree;

import org.junit.jupiter.api.Test;
import uy.edu.um.adt.linkedlist.MyList;

import static org.junit.jupiter.api.Assertions.*;

class SearchBinaryTreeImplTest {
    @Test
    public void testAddFlujoNormal() {
        MySearchBinaryTree<Integer, Integer> oTree = new MySearchBinaryTreeImpl<>();

        oTree.add(3, 3);
        oTree.add(21, 21);
        oTree.add(11, 11);
        oTree.add(-1, -1);
        oTree.add(4, 4);
        oTree.add(18, 18);

        MyList<Integer> colValues = oTree.inOrder();

        assertEquals(-1, colValues.get(0));
        assertEquals(3, colValues.get(1));
        assertEquals(4, colValues.get(2));
        assertEquals(11, colValues.get(3));
        assertEquals(18, colValues.get(4));
        assertEquals(21, colValues.get(5));
    }

    @Test
    public void testRemoveFlujoNormal() {
        MySearchBinaryTree<Integer, Integer> oTree = new MySearchBinaryTreeImpl<>();

        oTree.add(3, 3);
        oTree.add(21, 21);
        oTree.add(11, 11);
        oTree.add(-1, -1);
        oTree.add(4, 4);
        oTree.add(18, 18);

        oTree.remove(3);
        oTree.remove(-1);
        oTree.remove(18);

        MyList<Integer> colValues = oTree.inOrder();

        assertEquals(4, colValues.get(0));
        assertEquals(11, colValues.get(1));
        assertEquals(21, colValues.get(2));
    }

    @Test
    public void testRemoveFlujoParticular() {
        MySearchBinaryTree<Integer, Integer> oTree = new MySearchBinaryTreeImpl<>();

        oTree.add(3, 3);
        oTree.add(-1, -1);
        oTree.add(4, 4);
        oTree.add(18, 18);

        oTree.remove(3);
        oTree.remove(-1);
        oTree.remove(18);
        oTree.remove(4);

        MyList<Integer> colValues = oTree.inOrder();

        assertEquals(0, colValues.size());
    }

    @Test
    public void testFindFlujoNormal() {
        MySearchBinaryTree<Integer, Integer> oTree = new MySearchBinaryTreeImpl<>();

        oTree.add(3, 3);
        oTree.add(21, 21);
        oTree.add(11, 11);
        oTree.add(-1, -1);
        oTree.add(4, 4);
        oTree.add(18, 18);

        oTree.remove(3);
        oTree.remove(-1);
        oTree.remove(18);

        Integer oTemp = oTree.find(3);

        assertNull(oTemp);

        oTemp = oTree.find(21);
// used to be new Integer(21)
        assertEquals(21, oTemp);

    }

    @Test
    public void testContainFlujoNormal() {
        MySearchBinaryTree<Integer, Integer> oTree = new MySearchBinaryTreeImpl<>();

        oTree.add(3, 3);
        oTree.add(21, 21);
        oTree.add(11, 11);
        oTree.add(-1, -1);
        oTree.add(4, 4);
        oTree.add(18, 18);

        assertFalse(oTree.contains(33));
        assertTrue(oTree.contains(-1));
    }

}