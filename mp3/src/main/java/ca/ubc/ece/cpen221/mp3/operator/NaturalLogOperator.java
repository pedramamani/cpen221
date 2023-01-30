package ca.ubc.ece.cpen221.mp3.operator;

public class NaturalLogOperator implements UnaryOperator {

    /*
     ** Rep Invariant: N/A
     ** Abstraction Function: N/A
     */

    /**
     * Take the natural logarithm of a number
     * Precondition: The argument is positive
     *
     * @param arg the number to find natural logarithm of
     * @return the natural logarithm of the number
     */
    public double apply(double arg) throws IllegalArgumentException {
        return Math.log(arg);
    }

    /**
     * Represent the operator as a String
     *
     * @return a String representation of the operator
     */
    public String toString() {
        return "log";
    }
}
