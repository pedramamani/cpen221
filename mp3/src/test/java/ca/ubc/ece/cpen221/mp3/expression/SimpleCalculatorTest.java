package ca.ubc.ece.cpen221.mp3.expression;

import static org.junit.Assert.assertEquals;
import ca.ubc.ece.cpen221.mp3.operator.AdditionOperator;
import ca.ubc.ece.cpen221.mp3.operator.DivisionOperator;
import ca.ubc.ece.cpen221.mp3.operator.NegationOperator;
import org.junit.Test;

public class SimpleCalculatorTest {

    private final Expression one = new NumberExpression(1);
    private final Expression two = new NumberExpression(2);
    private final VariableExpression x = new VariableExpression("x");
    private final VariableExpression y = new VariableExpression("y");

    private final Expression func = new UnaryOperationExpression(
            new NegationOperator(), new BinaryOperationExpression(
            new AdditionOperator(), two, new BinaryOperationExpression(
            new DivisionOperator(), one, x)));
    private final DerivativeExpression der = new DerivativeExpression(func, x);
    private final DerivativeExpression derFault = new DerivativeExpression(func, y);

    private final double EPSILON = 1e-5;

    @Test
    public void test1Derivative() {
        x.store(2);
        assertEquals(0.25, der.eval(), EPSILON);

        x.store(-1);
        assertEquals(1.0, der.eval(), EPSILON);
    }

    @Test
    public void test2ToString() {
        assertEquals("x", x.name());
        assertEquals("~ ((2.0) + ((1.0) / (x)))", func.toString());
    }

    @Test
    public void test3GetZero() {
        assertEquals(-0.5, der.getZero(-0.2, EPSILON), EPSILON);
        assertEquals(1.0, derFault.getZero(1.0, EPSILON), EPSILON);
    }
}
