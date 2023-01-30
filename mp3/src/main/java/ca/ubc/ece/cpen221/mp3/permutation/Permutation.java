package ca.ubc.ece.cpen221.mp3.permutation;

import java.util.List;
import java.util.ArrayList;

public class Permutation<T> implements AbstractPermutation<T> {
    /*
     ** Rep Invariant: N/A
     ** Abstraction Function:
     *      - The domain is any list with members of generic type T
     *      - The range is all permutations of the list
     */

    private List<T> tempConfig = new ArrayList<>();
    private List<List<T>> allPerms = new ArrayList<>();

    /**
     * Create a permutation generator with a given list that will be
     * permuted. Note identical elements in the list will be treated
     * as unique elements. For example, an input list of {1, 1} returns
     * the two permutations {1, 1} and {1, 1}.
     *
     * @param baseList is not empty or null
     */
    public Permutation(List<T> baseList) {
        tempConfig.addAll(baseList);
        recursiveHeap(tempConfig.size());
    }

    private void recursiveHeap(int size) {
        for (int i = 0; i < size; i++) {
            if (size == 1) {
                List<T> temp = new ArrayList<>(tempConfig);
                allPerms.add(temp);
            } else {
                recursiveHeap(size - 1);
                if (size % 2 == 0) {
                    T temp = tempConfig.get(size - 1);
                    tempConfig.set(size - 1, tempConfig.get(i));
                    tempConfig.set(i, temp);
                } else if (size % 2 == 1) {
                    T temp = tempConfig.get(size - 1);
                    tempConfig.set(size - 1, tempConfig.get(0));
                    tempConfig.set(0, temp);
                }
            }
        }
    }

    private int permCounter = 0;

    /**
     * checks whether the next permutation exists
     *
     * @return Once all permutations have been
     * returned once by next(), hasNext() will always
     * return false
     */

    @Override
    public boolean hasNext() {
        return permCounter < allPerms.size();
    }

    /**
     * finds the next permutation
     *
     * @return the next permutation. If no more permutations remain,
     * the method will return the first permutation and continue to loop over
     * all permutations again.
     */

    @Override
    public List<T> next() {
        if (permCounter >= allPerms.size()) {
            permCounter = 0;
        }
        List<T> getPerm = allPerms.get(permCounter);
        permCounter++;
        return getPerm;
    }

    /**
     * calculates the number of permutations
     *
     * @return the number of permutations
     */

    public int numPermutations() {
        return allPerms.size();
    }
}
