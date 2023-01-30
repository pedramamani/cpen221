package ca.ubc.ece.cpen221.mp3.cryptarithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SolveCryptarithm {

    /**
     * CLI for solving cryparithm puzzles, where
     * the argument to the function call is the
     * cryparithm
     *
     * @param args the elements of the cryparithm
     *            (varibles words and operatros)
     *             in correct order
     */

    public static void main(String[] args) {
        try {
            Cryptarithm cryp = new Cryptarithm(args);
            List<Map<Character, Integer>> solutions;
            solutions = cryp.solve();
            System.out.print(solutions.size() + " solution(s):\n");

            for (Map<Character, Integer> solution : solutions) {
                System.out.print("{");
                List<Character> printList = new ArrayList<>(solution.keySet());

                for (int j = 0; j < printList.size(); j++) {
                    System.out.print(printList.get(j) + "="
                            + solution.get(printList.get(j)));
                    if (j != printList.size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.print("}\n");
            }
        } catch (InvalidCryptarithm e) {
            System.out.print("Invalid Cryptarithm\n");
        } catch (NoSolutionException e) {
            System.out.print("0 solution(s)\n");
        }
    }
}
