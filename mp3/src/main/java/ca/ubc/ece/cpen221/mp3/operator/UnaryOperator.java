package ca.ubc.ece.cpen221.mp3.operator;

/** 
 * UnaryOperator - an arithmetic operator with a single operand.
 *
 */
public interface UnaryOperator extends Operator {

    /**
     * Applies the Operator on the number given.
     *
     * @param arg a number to apply the operator on
     * @return the number outputted by the operation given input arg
     * @throws IllegalArgumentException if the operation is not supported
     * on the passed arguments
     */
    double apply(double arg) throws IllegalArgumentException;

}
