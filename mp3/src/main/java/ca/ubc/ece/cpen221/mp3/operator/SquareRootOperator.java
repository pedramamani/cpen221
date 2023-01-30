package ca.ubc.ece.cpen221.mp3.operator;

public class SquareRootOperator implements UnaryOperator {

    /*
     ** Rep Invariant: N/A
     ** Abstraction Function: N/A
     */

    /**
     * Take the square root of a number
     * Precondition: The argument is non-negative
     *
     * @param arg the number
     * @return the square root of the number
     */
    public double apply(double arg) throws IllegalArgumentException {
        return Math.sqrt(arg);
    }

    /**
     * Represent the operator as a String
     *
     * @return a String representation of the operator
     */
    public String toString() {
        return "sqrt";
    }
}
