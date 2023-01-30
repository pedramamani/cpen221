package ca.ubc.ece.cpen221.mp3.permutation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import org.junit.Test;

public class PermutationTest {

    @Test
    public void test0TwoNum() {
        List<Integer> testList = new ArrayList<>();
        testList.add(1);
        testList.add(2);

        Permutation<Integer> permutation = new Permutation<>(testList);
        assertTrue(permutation.hasNext());

        Set<List<Integer>> actual = new HashSet<>();
        while (permutation.hasNext()) {
            actual.add(permutation.next());
        }
        Set<List<Integer>> expected = new HashSet<>();
        expected.add(Arrays.asList(1, 2));
        expected.add(Arrays.asList(2, 1));
        assertEquals(expected, actual);
    }

    @Test
    public void test1ListofLists() {
        List<List<String>> testList = new ArrayList<>();
        testList.add(Arrays.asList("List1"));
        testList.add(Arrays.asList("List2"));

        Permutation<List<String>> permutation = new Permutation<>(testList);
        assertTrue(permutation.hasNext());

        Set<List> actual = new HashSet<>();
        while (permutation.hasNext()) {
            actual.add(permutation.next());
        }
        Set<List> expected = new HashSet<>();
        expected.add(Arrays.asList(Arrays.asList("List1"), Arrays.asList("List2")));
        expected.add(Arrays.asList(Arrays.asList("List2"), Arrays.asList("List1")));

        assertEquals(expected, actual);
    }

    @Test
    public void test2Size0() {
        List<String> testList = new ArrayList<>();

        Permutation<String> permutation = new Permutation<>(testList);
        assert (!permutation.hasNext());

        Set<List> actual = new HashSet<>();
        while (permutation.hasNext()) {
            actual.add(permutation.next());
        }

        Set expected = new HashSet();
        assertEquals(expected, actual);
    }

    @Test
    public void test3Size1() {
        List<Integer> testList = new ArrayList<>();
        testList.add(1);
        Permutation<Integer> permutation = new Permutation<>(testList);
        assert (permutation.hasNext());

        Set<List> actual = new HashSet<>();
        while (permutation.hasNext()) {
            actual.add(permutation.next());
        }
        Set<List> expected = new HashSet<>();
        expected.add(Arrays.asList(1));
        assertEquals(expected, actual);
    }

    @Test
    public void test4ThreeNum() {
        List<Integer> testList = new ArrayList<>();
        testList.add(1);
        testList.add(2);
        testList.add(3);

        Permutation<Integer> permutation = new Permutation<>(testList);
        Set<List> actual = new HashSet<>();

        while (permutation.hasNext()) {
            actual.add(permutation.next());
        }

        Set<List> expected = new HashSet<>();
        expected.add(Arrays.asList(1, 2, 3));
        expected.add(Arrays.asList(1, 3, 2));
        expected.add(Arrays.asList(2, 1, 3));
        expected.add(Arrays.asList(2, 3, 1));
        expected.add(Arrays.asList(3, 2, 1));
        expected.add(Arrays.asList(3, 1, 2));

        assertEquals(expected, actual);
        assertEquals(6, permutation.numPermutations());
    }

    @Test
    public void test5TwoNumLoop() {
        List<Integer> testList = new ArrayList<>();
        testList.add(1);
        testList.add(2);

        Permutation<Integer> permutation = new Permutation<>(testList);
        List<Integer> firstPerm = permutation.next();

        while (permutation.hasNext()) {
            permutation.next();
        }
        List<Integer> secondPerm = permutation.next();
        assertEquals(firstPerm, secondPerm);
    }
}
