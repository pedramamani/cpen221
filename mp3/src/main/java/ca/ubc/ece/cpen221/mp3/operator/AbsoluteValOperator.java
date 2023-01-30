package ca.ubc.ece.cpen221.mp3.operator;

public class AbsoluteValOperator implements UnaryOperator {

    /*
     ** Rep Invariant: N/A
     ** Abstraction Function: N/A
     */

    /**
     * Take the absolute value of a number
     *
     * @param arg the number to find absolute value of
     * @return the absolute value of the number
     */
    public double apply(double arg) {
        if (arg < 0) {
            return -arg;
        }
        return arg;
    }

    /**
     * Represent the operator as a String
     *
     * @return a String representation of the operator
     */
    public String toString() {
        return "abs";
    }
}
