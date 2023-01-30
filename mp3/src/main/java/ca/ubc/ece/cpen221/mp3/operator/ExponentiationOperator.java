package ca.ubc.ece.cpen221.mp3.operator;

public class ExponentiationOperator implements BinaryOperator {

    /*
     ** Rep Invariant: N/A
     ** Abstraction Function: N/A
     */

    /**
     * Raise the first number to the power of the second number
     *
     * @param arg1 the number to be exponentiated
     * @param arg2 the exponent
     * @return the first number raised to the second number
     */
    public double apply(double arg1, double arg2) {
        return Math.pow(arg1, arg2);
    }

    /**
     * Represent the operator as a String
     *
     * @return a String representation of the operator
     */
    public String toString() {
        return "^";
    }
}
