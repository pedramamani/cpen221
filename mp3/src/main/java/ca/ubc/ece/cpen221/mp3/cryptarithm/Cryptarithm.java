package ca.ubc.ece.cpen221.mp3.cryptarithm;

import ca.ubc.ece.cpen221.mp3.expression.BinaryOperationExpression;
import ca.ubc.ece.cpen221.mp3.operator.BinaryOperator;
import ca.ubc.ece.cpen221.mp3.expression.Expression;
import ca.ubc.ece.cpen221.mp3.expression.NumberExpression;
import ca.ubc.ece.cpen221.mp3.operator.AdditionOperator;
import ca.ubc.ece.cpen221.mp3.operator.SubtractionOperator;
import ca.ubc.ece.cpen221.mp3.operator.MultiplicationOperator;
import ca.ubc.ece.cpen221.mp3.operator.DivisionOperator;
import ca.ubc.ece.cpen221.mp3.permutation.Permutation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Cryptarithm - a datatype that represents a cryptarithm
 */
public class Cryptarithm {

    /*
     ** Rep Invariant:
     *      - The input String array cannot contain any null values
     *      - The input String array cannot be a null value
     ** Abstraction Function:
     *      - The domain is any array of Strings
     *      - The range is all valid solutions to the cryptarithm, assuming there is a solution
     *      - A valid cryptatrithm is of the form "var op var op ... op var =  var op ... op var"
     *             - var is a String of characters
     *             - op is a legal operation value of "-", "+", "*", "/" or "="
     *             - A maximum of ten and minimum of one distinct character be found in all vars
     *             - The "=" op must appear in the cryptarithm
     */

    private final double EPSILON = 1e-10;
    private List<String> NUMBERS = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
    private ArrayList<String> vars = new ArrayList<>();
    private ArrayList<Integer> varsNum = new ArrayList<>();
    private ArrayList<String> ops = new ArrayList<>();
    private Set<Character> firstLetters = new HashSet<>();
    private List<String> letters = new ArrayList<>();
    private List<Integer> lettersNum = new ArrayList<>();
    private Set<String> solutions = new HashSet<>();

    /**
     * Cryptarithm constructor
     *
     * @param cryptarithm where each element is a String that
     *                    represents part of the cryptarithm
     *                    The characters '=', '/', '*', '-' and '+'
     *                    are valid operators. All other characters are
     *                    considered variables (including whitespace)
     * @throws InvalidCryptarithm when cryptarithm supplied does not meet
     *                            "var op var op ... op var =  var op.. op var"
     *                            structure. There must always be 1 more var than op
     *                            and equals must always appear somewhere as an op.
     *                            An exception is also thrown when more
     *                            than 10 characters or no letters appear in the
     *                            cryptarithm.
     */
    public Cryptarithm(String[] cryptarithm) throws InvalidCryptarithm {
        for (int i = 0; i < cryptarithm.length; i++) {
            String index = cryptarithm[i];
            if (index.equals("=") || index.equals("/") || index.equals("*")
                    || index.equals("+") || index.equals("-")) {
                if (index.equals("=") && (ops.contains("=") || i == cryptarithm.length - 1)) {
                    throw new InvalidCryptarithm();
                }
                ops.add(index);
                if (i > 0 && (cryptarithm[i - 1].equals("/")
                        || cryptarithm[i - 1].equals("=")
                        || cryptarithm[i - 1].equals("*")
                        || cryptarithm[i - 1].equals("+")
                        || cryptarithm[i - 1].equals("-"))) {
                    throw new InvalidCryptarithm();
                }
            } else {
                vars.add(index);
                for (int j = 0; j < index.length(); j++) {
                    if (j == 0) {
                        firstLetters.add(index.charAt(j));
                    }
                    if (!letters.contains(Character.toString(index.charAt(j)))) {
                        letters.add(Character.toString(index.charAt(j)));
                    }
                }
            }
        }
        if (letters.size() > 10 || letters.size() == 0) {
            throw new InvalidCryptarithm();
        } else if (!ops.contains("=")) {
            throw new InvalidCryptarithm();
        } else if (vars.size() - ops.size() != 1) {
            throw new InvalidCryptarithm();
        }
    }

    /**
     * Find solutions to the cryptarithm
     *
     * @return a list of all possible solutions to the given cryptarithm. A
     * solution is a map that provides the value for each Character in
     * the cryptarithm.
     * @throws NoSolutionException if no solutions are found for the cryptarithm
     */
    public List<Map<Character, Integer>> solve() throws NoSolutionException {
        Permutation<String> perms = new Permutation<>(NUMBERS);
        while (perms.hasNext()) {
            List<String> order = perms.next();
            setWordsToNumbers(order);
            if (crypExpEval() && checkFirstLetters()) {
                StringBuilder seq = new StringBuilder();
                for (int i = 0; i < letters.size(); i++) {
                    seq.append(order.get(i));
                }
                solutions.add(seq.toString());
            }
        }
        if (solutions.size() == 0) {
            throw new NoSolutionException();
        }

        List<Map<Character, Integer>> mapSolution = new ArrayList<>();
        List<String> solutionsList = new ArrayList<>(solutions);
        for (int i = 0; i < solutionsList.size(); i++) {
            mapSolution.add(new HashMap<>());
            for (int j = 0; j < letters.size(); j++) {
                mapSolution.get(i).put(letters.get(j).charAt(0),
                        Character.getNumericValue(solutionsList.get(i).charAt(j)));
            }
        }
        return mapSolution;
    }

    private boolean checkFirstLetters() {
        for (Character c : firstLetters) {
            int letterIndex = letters.indexOf(Character.toString(c));
            if (lettersNum.get(letterIndex) == 0) {
                return false;
            }
        }
        return true;
    }

    private void setWordsToNumbers(List<String> order) {
        varsNum.clear();
        lettersNum.clear();
        for (int i = 0; i < letters.size(); i++) {
            lettersNum.add(Integer.parseInt(order.get(i)));
        }
        for (int k = 0; k < vars.size(); k++) {
            double number = 0.0;
            for (int i = 0; i < vars.get(k).length(); i++) {
                int indexLetters = letters.indexOf(Character.toString(vars.get(k).charAt(i)));
                double coeff = (double) lettersNum.get(indexLetters);
                double exponent = (double) vars.get(k).length() - 1 - i;
                number += coeff * Math.pow(10.0, exponent);
            }
            varsNum.add((int) number);
        }
    }

    private boolean crypExpEval() {
        double lhs = varsNum.get(0);
        int equalsIndex = 0;
        for (int i = 0; true; i++) {
            String nextOp = ops.get(i);
            if (nextOp.equals("=")) {
                equalsIndex = i;
                break;
            }
            Expression exp = new BinaryOperationExpression(
                    getOp(nextOp),
                    new NumberExpression(lhs),
                    new NumberExpression(varsNum.get(i + 1)));
            lhs = exp.eval();
        }
        double rhs = varsNum.get(equalsIndex + 1);
        for (int i = equalsIndex + 1; i < ops.size(); i++) {
            String nextOp = ops.get(i);
            Expression exp = new BinaryOperationExpression(
                    getOp(nextOp),
                    new NumberExpression(rhs),
                    new NumberExpression(varsNum.get(i + 1)));
            rhs = exp.eval();
        }

        return Math.abs(rhs - lhs) < EPSILON;
    }

    private BinaryOperator getOp(String op) {
        BinaryOperator returnOp;
        switch (op) {
            case "+":
                returnOp = new AdditionOperator();
                break;
            case "-":
                returnOp = new SubtractionOperator();
                break;
            case "*":
                returnOp = new MultiplicationOperator();
                break;
            default:
                returnOp = new DivisionOperator();
                break;
        }
        return returnOp;
    }
}
