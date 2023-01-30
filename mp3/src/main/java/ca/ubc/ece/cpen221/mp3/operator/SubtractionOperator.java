package ca.ubc.ece.cpen221.mp3.operator;

public class SubtractionOperator implements BinaryOperator {

    /*
     ** Rep Invariant: N/A
     ** Abstraction Function: N/A
     */

    /**
     * Subtract the second number from the first number
     * @param arg1 the number to subtract from
     * @param arg2 the number to subtract
     * @return the first number minus the second number
     */
    public double apply(double arg1, double arg2) {
        return arg1 - arg2;
    }

    /**
     * Represent the operator as a String
     *
     * @return a String representation of the operator
     */
    public String toString() {
        return "-";
    }
}
