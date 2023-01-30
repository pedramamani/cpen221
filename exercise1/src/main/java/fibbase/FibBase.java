package fibbase;
import java.util.*;
import java.lang.Math;

public class FibBase {

	/**
	 * 
	 * @param a
	 *            > 0
	 * @param b
	 *            >= a
	 * @return y(a) + y(a+1) + y(a+2) + ... + y(b-1) + y(b)
	 */
	public static long ySum(long a, long b) {
		long count = a;
		long sum = 0;

		while (count <= b) {
			sum += getDecimalRep(count);
			count++;
		}

		return sum;
	}

	/**
	 * Precondition: Number is positive
	 * @param num
	 * @return x(num)
	 */
	private static long getDecimalRep(long num) {
		long fibNum = 0;
		List<Integer> fibList = convertToFibBase(num);

		for (int i = 0; i < fibList.size(); i++) {
			fibNum += fibList.get(i) * Math.pow(2, fibList.size() - i - 1);
		}

		return fibNum;
	}

	/**
	 * Precondition: Number is positive
	 * @param num
	 * @return x(num)
	 */
	private static long getBinaryRep(long num) {
		long fibNum = 0;
		List<Integer> fibList = convertToFibBase(num);

		for (int i = 0; i < fibList.size(); i++) {
			fibNum += fibList.get(i) * Math.pow(10, fibList.size() - i - 1);
		}

		return fibNum;
	}

	/**
	 * Get fib base number as a list of ints
	 * @param num
	 * @return
	 */
	private static List<Integer> convertToFibBase(long num) {
		int count = 1;
		 long remainder;
		List<Integer> fibNum = new ArrayList<>();

		while (num % getFibNumber(count) != num) {
			count++;
		}

		for (int i = 1; i < count - 1; i++) {
			remainder = num % getFibNumber(count - i);
			if (remainder == num) {
				fibNum.add(0);
			} else {
				fibNum.add(1);
				num = remainder;
			}
		}

		return fibNum;
	}

	/**
	 * Get Fibonnaci number
	 * Fib(2) = 1, Fib(3) = 2, Fib(4) = 3
	 * Precondition: n >= 1
	 * @param n
	 * @return Fibonnaci number
	 */
	private static int getFibNumber(int n) {
		if (n == 1 || n == 2) {
			return 1;
		}
		return (getFibNumber(n - 1) + getFibNumber(n - 2));
	}
}
