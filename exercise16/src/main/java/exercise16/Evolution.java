package exercise16;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.*;

public class Evolution {

    /**
     * Compute the number of cells that survive the evolutionary process as
     * described in the problem statement assuming that a given cell dies during
     * the process.
     *
     * @param evolutionaryTree
     *            array that represents the normal evolution of the organism
     * @param deadCell
     *            the index in evolutionaryTree of the cell that dies
     * @requires - evolutionaryTree will contain exactly N elements, where N is
     *           an odd integer between 1 and 50, inclusive.
     *           - There will be exactly one "-1" element in evolutionaryTree.
     *           - Every element of evolutionaryTree will be between -1 and N-1, inclusive.
     *           - evolutionaryTree will form a binary tree.
     *           - deadCell will be between 0 and N-1, inclusive.
     *
     * @return the number of surviving cells in the mature organism
     */
    public static int numSurvivingCells(int[] evolutionaryTree, int deadCell) {
        return getSurvivingCells(evolutionaryTree, deadCell).size();
    }

    /**
     * Compute the number of cells that survive the evolutionary process as
     * described in the problem statement assuming that a given cell dies during
     * the process.
     *
     * @param evolutionaryTree
     *            array that represents the normal evolution of the organism
     * @param deadCell
     *            the index in evolutionaryTree of the cell that dies
     * @requires - evolutionaryTree will contain exactly N elements, where N is
     *           an odd integer between 1 and 50, inclusive.
     *           - There will be exactly one "-1" element in evolutionaryTree.
     *           - Every element of evolutionaryTree will be between -1 and N-1, inclusive.
     *           - evolutionaryTree will form a binary tree.
     *           - deadCell will be between 0 and N-1, inclusive.
     *
     * @return the set of surviving cells in the mature organism
     */
    public static Set<Integer> getSurvivingCells(int[] evolutionaryTree,
                                                 int deadCell) {
        Map<Integer, Integer> cells = new HashMap<>();

        for (int cell = 0; cell < evolutionaryTree.length; cell++) {
            cells.put(cell, evolutionaryTree[cell]);
        }

        cells.remove(deadCell);
        killMoreCells(cells, new HashSet<>(Collections.singleton(deadCell)));
        Set<Integer> finalSet = getFinalRow(cells);

        return finalSet;
    }

    private static void killMoreCells(Map<Integer, Integer> parents, Set<Integer> deadCells) {
        if (deadCells.isEmpty())
            return;

        Set<Integer> newDeadCells = new HashSet<>();

        for (Integer dead : deadCells) {
            for (Integer cell : parents.keySet()) {
                if (parents.get(cell).equals(dead)) {
                    newDeadCells.add(cell);
                }
            }
        }

        for (Integer cell : newDeadCells) {
            parents.remove(cell);
        }

        killMoreCells(parents, newDeadCells);
    }

    private static Set<Integer> getFinalRow(Map<Integer, Integer> parents) {
        Set<Integer> finalSet = new HashSet<>();
        for (Integer cell : parents.keySet()) {
            if (!parents.containsValue(cell)) {
                finalSet.add(cell);
            }
        }

        return finalSet;
    }
}
