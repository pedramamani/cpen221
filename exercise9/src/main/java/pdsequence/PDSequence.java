package pdsequence;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class PDSequence {

	/**
	 * Find all elements in inList that belong to the sequence PD(m) and return
	 * them as a list sorted in ascending order. If there are duplicates in
	 * inList, return only one.
	 * 
	 * @param inList
	 *            is not null
	 * @param m
	 *            is > 0
	 * @return a list, in ascending order, of the elements in PD(m) that are
	 *         also in inList. The returned list does not contain duplicate
	 *         entries.
	 */
	public static List<Integer> getPDsequence(List<Integer> inList, int m) {
		Collections.sort(inList);
        ArrayList PDSequence = new ArrayList<Integer>();
        ArrayList outList = new ArrayList<Integer>();
		int limit = 0;
        int i = 0;

		if (inList.size() > 0) {
            limit = inList.get(inList.size() - 1);
        }

		PDSequence.add(m);
		while ((int) PDSequence.get(i) <= limit) {
		    PDSequence.add((int) PDSequence.get(i) + productDigits((int) PDSequence.get(i)));
		    if (inList.contains(PDSequence.get(i))) {
		        outList.add(PDSequence.get(i));
            }
		    i++;
        }

		return outList;
	}

	private static int productDigits(int num) {
	    int product = 1;
	    int temp_num = num;

	    while (temp_num != 0) {
	        if (temp_num % 10 != 0) {
                product *= temp_num % 10;
            }
	        temp_num = temp_num / 10;
        }

        return product;
    }
}
