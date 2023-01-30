package ca.ubc.ece.cpen221.mp3.expression;
import ca.ubc.ece.cpen221.mp3.operator.BinaryOperator;

public class BinaryOperationExpression implements Expression {

    /*
     ** Rep Invariant: N/A
     ** Abstraction Function: N/A
     */

    private final Expression operand1;
    private final Expression operand2;
    private final BinaryOperator operator;

    /**
     * Construct the expression from a unary operator and
     * an operand in form of an expression.
     *
     * @param operator the unary operator
     * @param operand1 the first expression before operator
     * @param operand2 the second expression after operator
     */
    public BinaryOperationExpression(BinaryOperator operator,
                                     Expression operand1, Expression operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operator = operator;
    }

    /**
     * Evaluate the value of the expression.
     *
     * @return the value of expression
     */
    public double eval() {
        return operator.apply(operand1.eval(), operand2.eval());
    }

    /**
     * Creates a String representation of the expression.
     *
     * @return the expression represented as a String object
     */
    public String toString() {
        return ("(" + operand1.toString() + ") " + operator.toString()
                + " (" + operand2.toString() + ")");
    }
}
