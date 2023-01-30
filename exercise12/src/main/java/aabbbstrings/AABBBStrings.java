package aabbbstrings;
import java.util.*;

public class AABBBStrings {

    /**
     * @param n >= 0
     * @return the number of words in the AABBB language of length n
     */
    public static long countStrings(int n) {
        return (long) getPossibilities(n).size();
    }

    /**
     * @param n
     *            >= 0
     * @return the set of words in the AABBB language of length n
     * @throws TooManyWordsException
     *             when the length of a word exceeds 30
     */
    public static Set<String> getWords(int n) throws TooManyWordsException {
        if (n > 30)
            throw new TooManyWordsException();

        Set<String> words = new HashSet<>();
        StringBuilder newWord;

        for (List word : getPossibilities(n)) {
            newWord = new StringBuilder();
            for (Object letter : word) {
                if ((boolean) letter)
                    newWord.append("aa");
                else
                    newWord.append("bbb");
            }
            words.add(newWord.toString());
        }
        return words;
    }

    /**
     * Get a set of all possible words of length n as lists of Booleans
     * indicating which character is used. "true" corresponds to aa and
     * "false" corresponds to bbb.
     */
    private static Set<List<Boolean>> getPossibilities(int n) {
        Set<List<Boolean>> prevPos;
        Set<List<Boolean>> curPos = new HashSet();

        if (n <= 1)
            return curPos;
        else if (n == 2) {
            curPos.add(new ArrayList<>(Arrays.asList(true)));
            return curPos;
        } else if (n == 3) {
            curPos.add(new ArrayList<>(Arrays.asList(false)));
            return curPos;
        }

        prevPos = getPossibilities(n-2);
        if (prevPos.size() != 0) {
            for (List word : prevPos)
                word.add(true);
            curPos.addAll(prevPos);
        }

        prevPos = getPossibilities(n-3);
        if (prevPos.size() != 0) {
            for (List word : prevPos)
                word.add(false);
            curPos.addAll(prevPos);
        }

        return curPos;
    }

}
