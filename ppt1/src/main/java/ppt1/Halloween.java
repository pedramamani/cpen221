package ppt1;

import java.util.ArrayList;
import java.util.List;

public class Halloween {

    /**
     * Returns the total number of student meetings that happen when students go
     * from a source to a destination. All sources are on the same street.
     * All destinations are on the same street, but different from the source street.
     * The source street and destination street are parallel to each other.
     * @param students is not null
     * @return the total number of student meetings that happen when students
     *          go from a source to a destination.
     */
    public static int bottleCount(List<SDPair> students) {
        int count = 0;

        for(SDPair std1 : students) {
            for(SDPair std2 : students) {
                if(doMeet(std1, std2)) {
                    count++;
                }
            }
        }
        return count/2;
    }

    /**
     * Returns the index from the given list of the student that <strong>meets</strong> the maximum
     * number of other students when students go from a source to a destination.
     * All sources are on the same street. All destinations are on the same street,
     * but different from the source street. The source street and destination
     * street are parallel to each other.
     * @param students is not null
     * @return an index between 0 and students.size()-1 that represents the student
     *          that meets most other students, an in case of ties the smaller index
     *          is returned.
     */
    public static int highPoint(List<SDPair> students) {
        int countMax = 0;
        int indexMax = 0;
        int i = 0;
        int tempCount = 0;

        while(i < students.size()) {
            SDPair std1 = students.get(i);
            for(SDPair std2 : students) {
                if(doMeet(std1, std2)) {
                    tempCount++;
                }
            }

            if(tempCount > countMax) {
                countMax = tempCount;
                indexMax = i;
            }

            tempCount = 0;
            i++;
        }
        return indexMax;
    }

    private static boolean doMeet(SDPair std1, SDPair std2){
        if(((std1.getSrc() < std2.getSrc()) && (std1.getDst() > std2.getDst()))
        || ((std1.getSrc() > std2.getSrc()) && (std1.getDst() < std2.getDst()))) {
            return true;
        }
        return false;
    }
}
