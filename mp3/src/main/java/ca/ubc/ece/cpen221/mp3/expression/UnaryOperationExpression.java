package ca.ubc.ece.cpen221.mp3.expression;
import ca.ubc.ece.cpen221.mp3.operator.UnaryOperator;

public class UnaryOperationExpression implements Expression {

    /*
     ** Rep Invariant: N/A
     ** Abstraction Function: N/A
     */

    private final Expression operand;
    private final UnaryOperator operator;

    /**
     * Construct the expression from a unary operator and
     * an operand in form of an expression
     *
     * @param operator the unary operator
     * @param operand the expression that is being acted on by the operator
     */
    public UnaryOperationExpression(UnaryOperator operator, Expression operand) {
        this.operand = operand;
        this.operator = operator;
    }

    /**
     * Evaluate the value of the expression
     *
     * @return the value of expression
     */
    public double eval() {
        return operator.apply(operand.eval());
    }

    /**
     * Creates a String representation of the expression
     *
     * @return the expression represented as a String object
     */
    public String toString() {
        return (operator.toString() + " (" + operand.toString() + ")");
    }
}
