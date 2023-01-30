package zerosumSubsequence;
import java.util.*;
import java.lang.Math;

public class ZeroSumSubsequence {

    public static int getMaximumLength(List<Integer> sequence) {
        if(sumElements(sequence) == 0)
            return sequence.size();

        List<Integer> seqCp;
        int maxLength = 0;
        for (int i = 0; i < sequence.size(); i++) {
            seqCp = new ArrayList<>(sequence);
            seqCp.remove(i);
            maxLength = Math.max(maxLength, getMaximumLength(seqCp));
        }
        return maxLength;
    }

    private static int sumElements(List<Integer> list) {
        int sum = 0;
        for(Integer element : list) {
            sum += element;
        }
        return sum;
    }
}