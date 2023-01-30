package ca.ubc.ece.cpen221.mp3.permutation;

import java.util.List;

/**
 * Abstract Permutation - an interface
 *
 * @param <T>
 *            generic Array
 */
public interface AbstractPermutation<T> {

    /**
     * Check if there is another permutation that has not yet been explored
     * @return true if there is a permutation that has not been returned yet,
     *          and false if all permutations have been returned
     */
    boolean hasNext();

    /**
     * Obtain the next permutation to explore,
     * with the precondition that hasNext() returns true
     *
     * @return the next permutation to explore
     */
    List<T> next();
}
