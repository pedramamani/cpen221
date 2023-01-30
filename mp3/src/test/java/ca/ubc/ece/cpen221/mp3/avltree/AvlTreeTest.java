package ca.ubc.ece.cpen221.mp3.avltree;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AvlTreeTest {

    private final AvlTreeSet tree, treeTrimmed, treeCleared;

    public AvlTreeTest() {
        tree = new AvlTreeSet();
        treeTrimmed = new AvlTreeSet();
        treeCleared = new AvlTreeSet();
        final int[] numbers = {0, -4, 3, 2, 1, 2, 2, 6, 4, -2, -1, 5};
        final int[] trim = {2, -4, 6, -1};
        final int[] clear = {0, -4, 3, 2, 1, 6, 4, -2, -1, 5};

        for (int num : numbers) {
            tree.insert(num);
            treeTrimmed.insert(num);
            treeCleared.insert(num);
        }

        for (int num : trim) {
            treeTrimmed.remove(num);
        }

        for (int num : clear) {
            treeCleared.remove(num);
        }
    }

    @Test (expected = IllegalStateException.class)
    public void test1Contains() {
        assert (tree.contains(-4));
        assert (tree.contains(0));
        assert (tree.contains(2));
        assert (!tree.contains(-3));
        assert (!treeTrimmed.contains(-4));
        assert (!treeTrimmed.contains(2));
        assert (!treeCleared.contains(1));

        treeCleared.remove(-4);
        fail("Exception expected");
    }

    @Test
    public void test2IsEmpty() {
        assert (!tree.isEmpty());
        assert (!treeTrimmed.isEmpty());
        assert (treeCleared.isEmpty());
    }

    @Test (expected = IllegalStateException.class)
    public void test3GetMin() {
        assertEquals(-4, tree.getMin());
        assertEquals(-2, treeTrimmed.getMin());

        treeCleared.getMin();
        fail("Exception expected");
    }

    @Test (expected = IllegalStateException.class)
    public void test4GetMax() {
        assertEquals(6, tree.getMax());
        assertEquals(5, treeTrimmed.getMax());

        treeCleared.getMax();
        fail("Exception expected");
    }

    @Test
    public void test5Size() {
        assertEquals(10, tree.size());
        assertEquals(6, treeTrimmed.size());
        assertEquals(0, treeCleared.size());
    }

    @Test
    public void test6GetHeight() {
        assertEquals(3, tree.getHeight());
        assertEquals(2, treeTrimmed.getHeight());
        assertEquals(-1, treeCleared.getHeight());
    }
}
