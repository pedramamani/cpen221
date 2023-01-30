package ca.ubc.ece.cpen221.mp3.parser;

import ca.ubc.ece.cpen221.mp3.expression.Expression;
import ca.ubc.ece.cpen221.mp3.operator.BinaryOperator;
import ca.ubc.ece.cpen221.mp3.operator.UnaryOperator;
import ca.ubc.ece.cpen221.mp3.expression.NumberExpression;
import ca.ubc.ece.cpen221.mp3.expression.BinaryOperationExpression;
import ca.ubc.ece.cpen221.mp3.expression.UnaryOperationExpression;

/**
 * ExpressionMaker - a helper class for creating Expressions given various
 * Operators and Expressions
 */

public class ExpressionMaker {

    /**
     * Creates a binary operation expression.
     *
     * @param operator the operator
     * @param operand1 the operand1
     * @param operand2 the operand2
     * @return the expression
     */
    public Expression createBinaryOperationExpression(BinaryOperator operator, Expression operand1,
            Expression operand2) {
        return new BinaryOperationExpression(operator, operand1, operand2);
    }

    /**
     * Creates a unary operation expression.
     *
     * @param operator the operator
     * @param operand the operand
     * @return the expression
     */
    public Expression createUnaryOperationExpression(UnaryOperator operator, Expression operand) {
        return new UnaryOperationExpression(operator, operand);
    }

    /**
     * Creates a number expression.
     *
     * @param value the value
     * @return the expression
     */
    public Expression createNumberExpression(double value) {
        return new NumberExpression(value);
    }

}
