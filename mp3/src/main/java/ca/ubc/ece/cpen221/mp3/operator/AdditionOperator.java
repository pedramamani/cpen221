package ca.ubc.ece.cpen221.mp3.operator;

public class AdditionOperator implements BinaryOperator {

    /*
     ** Rep Invariant: N/A
     ** Abstraction Function: N/A
     */

    /**
     * Add two numbers together
     *
     * @param arg1 the first number
     * @param arg2 the second number
     * @return the sum of the two numbers
     */
    public double apply(double arg1, double arg2) {
        return arg1 + arg2;
    }

    /**
     * Represent the operator as a String
     *
     * @return a String representation of the operator
     */
    public String toString() {
        return "+";
    }
}
