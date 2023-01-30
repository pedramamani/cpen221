package ca.ubc.ece.cpen221.mp3.operator;

public class DivisionOperator implements BinaryOperator {

    /*
     ** Rep Invariant: N/A
     ** Abstraction Function: N/A
     */

    /**
     * Divide the first number by the second number
     *
     * @param arg1 the dividend
     * @param arg2 the divisor
     * @return the dividend divided by the divisor
     *         infinity if arg2 == 0
     */
    public double apply(double arg1, double arg2) {
        return arg1 / arg2;
    }

    /**
     * Represent the operator as a String
     *
     * @return a String representation of the operator
     */
    public String toString() {
        return "/";
    }
}
