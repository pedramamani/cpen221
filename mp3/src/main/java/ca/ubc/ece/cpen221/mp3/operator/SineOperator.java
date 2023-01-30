package ca.ubc.ece.cpen221.mp3.operator;

public class SineOperator implements UnaryOperator {

    /*
     ** Rep Invariant: N/A
     ** Abstraction Function: N/A
     */

    /**
     * Take sine of a number
     *
     * @param arg the number
     * @return the sine of the number
     */
    public double apply(double arg) {
        return Math.sin(arg);
    }

    /**
     * Represent the operator as a String
     *
     * @return a String representation of the operator
     */
    public String toString() {
        return "sin";
    }
}
