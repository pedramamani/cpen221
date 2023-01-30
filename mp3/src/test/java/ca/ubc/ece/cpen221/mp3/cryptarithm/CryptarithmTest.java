package ca.ubc.ece.cpen221.mp3.cryptarithm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class CryptarithmTest {

    @Test
    public void test1SENDMOREMONEY() {
        String[] testArr = {"SEND", "+", "MORE", "=", "MONEY"};

        List<Map<Character, Integer>> expectedSolve = new ArrayList<>();
        expectedSolve.add(new HashMap<>());
        Character[] letters = {'S', 'E', 'N', 'D', 'M', 'O', 'R', 'Y'};
        Integer[] digits  = {9, 5, 6, 7, 1, 0, 8, 2};
        for (int i = 0; i < letters.length; i++) {
            expectedSolve.get(0).put(letters[i], digits[i]);
        }

        try {
            Cryptarithm cryp = new Cryptarithm(testArr);
            try {
                List<Map<Character, Integer>> solution;
                solution = cryp.solve();
                assertEquals(expectedSolve, solution);
            } catch (NoSolutionException e) {
                System.out.println("No Solution");
                fail();
            }
        } catch (InvalidCryptarithm e) {
            System.out.println("Invalid Cryp");
            fail();
        }
    }

    @Test
    public void test2WINTERISWINDIER() {
        String[] testArr = {"WINTER", "+", "IS", "+", "WINDIER", "+", "SUMMER",
                            "+", "IS", "=", "SUNNIER"};

        List<Map<Character, Integer>> expectedSolve = new ArrayList<>();
        expectedSolve.add(new HashMap<>());
        Character[] letters = {'W', 'I', 'N', 'T', 'E', 'R', 'S', 'D', 'U', 'M'};
        Integer[] digits  = {7, 6, 0, 2, 8, 1, 9, 4, 3, 5};
        for (int i = 0; i < letters.length; i++) {
            expectedSolve.get(0).put(letters[i], digits[i]);
        }

        try {
            Cryptarithm cryp = new Cryptarithm(testArr);
            try {
                List<Map<Character, Integer>> solution;
                solution = cryp.solve();
                assertEquals(expectedSolve, solution);
            } catch (NoSolutionException e) {
                System.out.println("No Solution");
                fail();
            }
        } catch (InvalidCryptarithm e) {
            System.out.println("Invalid Cryp");
            fail();
        }
    }

    @Test
    public void test3NORTHSOUTH() {
        String[] testArr = {"NORTH", "/", "SOUTH", "=", "EAST", "/", "WEST"};

        List<Map<Character, Integer>> expectedSolve = new ArrayList<>();
        expectedSolve.add(new HashMap<>());
        Character[] letters = {'N', 'O', 'R', 'T', 'H', 'S', 'U', 'E', 'A', 'W'};
        Integer[] digits  = {5, 1, 3, 0, 4, 6, 9, 7, 2, 8};
        for (int i = 0; i < letters.length; i++) {
            expectedSolve.get(0).put(letters[i], digits[i]);
        }

        try {
            Cryptarithm cryp = new Cryptarithm(testArr);
            try {
                List<Map<Character, Integer>> solution;
                solution = cryp.solve();
                assertEquals(expectedSolve, solution);
            } catch (NoSolutionException e) {
                System.out.println("No Solution");
                fail();
            }
        } catch (InvalidCryptarithm e) {
            System.out.println("Invalid Cryp");
            fail();
        }
    }

    @Test
    public void test4JEDERLIEBTBERLIN() {
        String[] testArr = {"JEDER", "+", "LIEBT", "=", "BERLIN"};

        List<Map<Character, Integer>> expectedSolve = new ArrayList<>();
        expectedSolve.add(new HashMap<>());
        expectedSolve.add(new HashMap<>());
        Character[] letters1 = {'J', 'E', 'D', 'R', 'L', 'I', 'B', 'T', 'N'};
        Integer[] digits1  = {6, 3, 4, 8, 7, 5, 1, 2, 0};
        Integer[] digits2 = {4, 3, 6, 8, 9, 5, 1, 2, 0};
        for (int i = 0; i < letters1.length; i++) {
            expectedSolve.get(0).put(letters1[i], digits1[i]);
            expectedSolve.get(1).put(letters1[i], digits2[i]);
        }

        try {
            Cryptarithm cryp = new Cryptarithm(testArr);
            try {
                Set<Map<Character, Integer>> solution = new HashSet<>(cryp.solve());
                Set<Map<Character, Integer>> expectedSolution = new HashSet<>(expectedSolve);
                assertEquals(expectedSolution, solution);
            } catch (NoSolutionException e) {
                System.out.println("No Solution");
                fail();
            }
        } catch (InvalidCryptarithm e) {
            System.out.println("Invalid Cryp");
            fail();
        }
    }

    @Test(expected = NoSolutionException.class)
    public void test5ICANTGET() throws NoSolutionException {
        String[] testArr = {"I", "+", "CANT", "+", "GET", "=", "NO", "+", "SATISFACTION"};

        try {
            Cryptarithm cryp = new Cryptarithm(testArr);
            cryp.solve();
        } catch (InvalidCryptarithm e) {
            System.out.println("Invalid Cryp");
            fail();
        }
    }

    @Test
    public void test6NORA() {
        String[] testArr = {"NORA", "*", "L", "=", "ARON"};

        List<Map<Character, Integer>> expectedSolve = new ArrayList<>();
        expectedSolve.add(new HashMap<>());
        Character[] letters1 = {'A', 'L', 'N', 'O', 'R'};
        Integer[] digits1  = {8, 4, 2, 1, 7};
        for (int i = 0; i < letters1.length; i++) {
            expectedSolve.get(0).put(letters1[i], digits1[i]);
        }

        try {
            Cryptarithm cryp = new Cryptarithm(testArr);
            try {
                Set<Map<Character, Integer>> solution = new HashSet<>(cryp.solve());
                Set<Map<Character, Integer>> expectedSolution = new HashSet<>(expectedSolve);
                assertEquals(expectedSolution, solution);
            } catch (NoSolutionException e) {
                System.out.println("No Solution");
                fail();
            }
        } catch (InvalidCryptarithm e) {
            System.out.println("Invalid Cryp");
            fail();
        }
    }


    @Test
    public void test6ABBA() {
        String[] testArr = {"AB", "-", "BA", "=", "A"};

        List<Map<Character, Integer>> expectedSolve = new ArrayList<>();
        expectedSolve.add(new HashMap<>());
        Character[] letters1 = {'A', 'B'};
        Integer[] digits1  = {9, 8};
        for (int i = 0; i < letters1.length; i++) {
            expectedSolve.get(0).put(letters1[i], digits1[i]);
        }

        try {
            Cryptarithm cryp = new Cryptarithm(testArr);
            try {
                Set<Map<Character, Integer>> solution = new HashSet<>(cryp.solve());
                Set<Map<Character, Integer>> expectedSolution = new HashSet<>(expectedSolve);
                assertEquals(expectedSolution, solution);
            } catch (NoSolutionException e) {
                System.out.println("No Solution");
                fail();
            }
        } catch (InvalidCryptarithm e) {
            System.out.println("Invalid Cryp");
            fail();
        }
    }


    @Test (expected = InvalidCryptarithm.class)
    public void test7Invalid1() throws InvalidCryptarithm {
        String[] testArr = {"I", "+", "CANT", "+", "GET", "+", "NO", "+", "SATISFACTION"};
        new Cryptarithm(testArr);
    }

    @Test (expected = InvalidCryptarithm.class)
    public void test8Invalid2() throws InvalidCryptarithm {
        String[] testArr = {"I", "+", "CANT", "=", "GET", "+", "NO", "+", "SATISFACTION", "NO"};
        new Cryptarithm(testArr);
    }

    @Test (expected = InvalidCryptarithm.class)
    public void test9Invalid3() throws InvalidCryptarithm {
        String[] testArr = {"ABC", "+", "DFG", "=", "IKL", "+", "QWE"};
        new Cryptarithm(testArr);
    }

    @Test (expected = InvalidCryptarithm.class)
    public void test10() throws InvalidCryptarithm {
        String[] testArr = {"ABC", "+", "DFG", "IKL", "+", "QWE", "="};
        new Cryptarithm(testArr);
    }

    @Test (expected = InvalidCryptarithm.class)
    public void test11() throws InvalidCryptarithm {
        String[] testArr = {"ABC", "+", "+", "DFG", "=", "IKL", "QWE"};
        new Cryptarithm(testArr);
    }
}
