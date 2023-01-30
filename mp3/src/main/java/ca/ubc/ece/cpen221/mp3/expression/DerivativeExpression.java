package ca.ubc.ece.cpen221.mp3.expression;

/**
 * DerivativeExpression - The derivative of specific function.
 */
public class DerivativeExpression implements Expression {

    /*
     ** Rep Invariant: N/A
     ** Abstraction Function: N/A
     */

    private final Expression function;
    private final VariableExpression variable;
    private final double EPSILON = 1e-10;

    /**
     * Create an expression representing the derivative of the specified
     * function with respect to the specified variable.
     *
     * @param fn the function whose derivative this expression represents
     * @param independentVar the variable with respect to which we're
     *                       differentiating
     */
    public DerivativeExpression(Expression fn, VariableExpression independentVar) {
        this.variable = independentVar;
        this.function = fn;
    }

    @Override
    public double eval() {
        double varValue = variable.eval();
        double value = function.eval();

        variable.store(varValue + EPSILON);
        double valueAfter = function.eval();
        variable.store(varValue);

        return (valueAfter - value) / EPSILON;
    }

    /**
     * Returns a zero of the specified function using
     * Newton’s method with guess as the initial estimate.
     *
     * @param guess initial approximation for the
     *        zero of the function
     * @param tolerance how close zero the returned
     *        zero has to be
     * @return the value of variable that evaluates function to zero
     *         initial guess if method diverges
     */
    public double getZero(double guess, double tolerance) {
        double varValue = variable.eval();
        double sol = guess;
        variable.store(sol);

        while (Math.abs(function.eval()) > tolerance) {
            if (Math.abs(this.eval()) > EPSILON) {
                sol -= function.eval() / this.eval();
                variable.store(sol);
            } else {
                variable.store(varValue);
                return guess;
            }
        }

        variable.store(varValue);
        return sol;
    }
}
