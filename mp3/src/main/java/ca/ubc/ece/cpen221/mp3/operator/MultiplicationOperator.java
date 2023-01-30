package ca.ubc.ece.cpen221.mp3.operator;

public class MultiplicationOperator implements BinaryOperator {

    /*
     ** Rep Invariant: N/A
     ** Abstraction Function: N/A
     */

    /**
     * Multiply two number together
     *
     * @param arg1 the first number
     * @param arg2 the second number
     * @return the product of the two numbers
     */
    public double apply(double arg1, double arg2) {
        return arg1 * arg2;
    }

    /**
     * Represent the operator as a String
     *
     * @return a String representation of the operator
     */
    public String toString() {
        return "*";
    }
}
