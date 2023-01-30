package ca.ubc.ece.cpen221.mp3.expression;

public class NumberExpression implements Expression {

    /*
     ** Rep Invariant: N/A
     ** Abstraction Function: N/A
     */

    private final double number;

    /**
     * Construct the NumberExpression from a number
     *
     * @param number the number represented
     */
    public NumberExpression(double number) {
        this.number = number;
    }

    /**
     * Get the value of number
     *
     * @return attribute number
     */
    public double eval() {
        return number;
    }

    /**
     * Creates a String representation of the number.
     *
     * @return the number as a String object
     */
    public String toString() {
        return Double.toString(number);
    }
}
