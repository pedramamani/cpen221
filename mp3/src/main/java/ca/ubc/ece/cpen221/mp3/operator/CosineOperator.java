package ca.ubc.ece.cpen221.mp3.operator;

public class CosineOperator implements UnaryOperator {

    /*
     ** Rep Invariant: N/A
     ** Abstraction Function: N/A
     */

    /**
     * Take cosine of a number
     *
     * @param arg the number
     * @return the cosine of the number
     */
    public double apply(double arg) {
        return Math.cos(arg);
    }

    /**
     * Represent the operator as a String
     *
     * @return a String representation of the operator
     */
    public String toString() {
        return "cos";
    }
}
