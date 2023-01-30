/**
 * A simple command line parser for arithmetic expressions
 */
package ca.ubc.ece.cpen221.mp3.parser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import ca.ubc.ece.cpen221.mp3.expression.Expression;
import ca.ubc.ece.cpen221.mp3.operator.AdditionOperator;
import ca.ubc.ece.cpen221.mp3.operator.SubtractionOperator;
import ca.ubc.ece.cpen221.mp3.operator.MultiplicationOperator;
import ca.ubc.ece.cpen221.mp3.operator.DivisionOperator;
import ca.ubc.ece.cpen221.mp3.operator.NaturalLogOperator;
import ca.ubc.ece.cpen221.mp3.operator.ExponentiationOperator;
import ca.ubc.ece.cpen221.mp3.operator.SineOperator;
import ca.ubc.ece.cpen221.mp3.operator.CosineOperator;
import ca.ubc.ece.cpen221.mp3.operator.SquareRootOperator;
import ca.ubc.ece.cpen221.mp3.operator.AbsoluteValOperator;
import ca.ubc.ece.cpen221.mp3.operator.NegationOperator;
import ca.ubc.ece.cpen221.mp3.operator.Operator;

/**
 * CommandLineParser - a command line calculator.
 * 
 * You will need to add any new Operators you create to the operatorSet or they
 * will not be usable in the command line calculator.
 *
 */
public class CommandLineParser {

    /**
     * @param args program arguments
     */
    public static void main(String[] args) {

        Set<Operator> operatorSet = new HashSet<>(Arrays.asList(
                new AdditionOperator(), new SubtractionOperator(),
                new MultiplicationOperator(), new DivisionOperator(),
                new NegationOperator(), new AbsoluteValOperator(),
                new ExponentiationOperator(), new NaturalLogOperator(),
                new SineOperator(), new CosineOperator(), new SquareRootOperator()));

        ExpressionParser parser = new ExpressionParser(operatorSet, new ExpressionMaker());

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Enter an expression");
            String expression = scanner.nextLine();
            try {
                Expression exp = parser.parse(expression);
                System.out.println("Result: " + exp.eval());
            } catch (Exception e) {
                System.out.println("Input format not accepted. Please try again.");
            }
        } while (true);

    }

}
