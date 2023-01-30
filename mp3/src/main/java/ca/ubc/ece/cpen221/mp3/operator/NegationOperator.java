package ca.ubc.ece.cpen221.mp3.operator;

public class NegationOperator implements UnaryOperator {

    /*
     ** Rep Invariant: N/A
     ** Abstraction Function: N/A
     */

    /**
     * Take the negative of a number
     *
     * @param arg the number to be negated
     * @return the negative of the number
     */
    public double apply(double arg) {
        return -arg;
    }

    /**
     * Represent the operator as a String
     *
     * @return a String representation of the operator
     */
    public String toString() {
        return "~";
    }
}
