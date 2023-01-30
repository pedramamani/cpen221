package ranges;

import java.util.*;

public class OverlappingRanges {

	/**
	 * Returns the smallest integer that is present in the maximum number of
	 * input ranges
	 * 
	 * @param startPoints
	 *            the list of starting points for the ranges
	 * @param endPoints
	 *            the list of end points for the ranges
	 * @return the smallest integer that is present in the maximum number of
	 *         ranges
	 * @throws NoOverlapException
	 *             when there is no overlap between any of the ranges
	 */
	public static Integer maxOverlapInt(ArrayList<Integer> startPoints,
			ArrayList<Integer> endPoints) throws NoOverlapException {
		Map<Integer, Integer> countNums = new HashMap<>();

		for (int i = 0; i < startPoints.size(); i++) {
			for (int num = startPoints.get(i); num <= endPoints.get(i); num++) {
				if (countNums.keySet().contains(num)) {
					countNums.replace(num, countNums.get(num) + 1);
				} else {
					countNums.put(num, 1);
				}
			}
		}

		int countMax = 0;
		int minNumber = 100000;
		for (int num : countNums.keySet()) {
			if (countNums.get(num) > countMax || countNums.get(num) == countMax && num < minNumber) {
				countMax = countNums.get(num);
				minNumber = num;
			}
		}

		if (countMax == 0) {
			throw new NoOverlapException();
		}

		return minNumber;
	}

}
