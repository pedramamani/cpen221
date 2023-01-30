package ca.ubc.ece.cpen221.mp3.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.SwingUtilities;
import ca.ubc.ece.cpen221.mp3.operator.NegationOperator;
import ca.ubc.ece.cpen221.mp3.operator.AbsoluteValOperator;
import ca.ubc.ece.cpen221.mp3.operator.AdditionOperator;
import ca.ubc.ece.cpen221.mp3.operator.SubtractionOperator;
import ca.ubc.ece.cpen221.mp3.operator.MultiplicationOperator;
import ca.ubc.ece.cpen221.mp3.operator.DivisionOperator;
import ca.ubc.ece.cpen221.mp3.operator.NaturalLogOperator;
import ca.ubc.ece.cpen221.mp3.operator.ExponentiationOperator;
import ca.ubc.ece.cpen221.mp3.operator.SineOperator;
import ca.ubc.ece.cpen221.mp3.operator.CosineOperator;
import ca.ubc.ece.cpen221.mp3.operator.SquareRootOperator;
import ca.ubc.ece.cpen221.mp3.operator.Operator;

/**
 * Main - creates and runs a new Calculator GUI.
 */
public class Main {

    /**
     * Runs the calculator GUI.
     *
     * @param args arguments to the main function
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowSetupScreen());
    }

    private static void createAndShowSetupScreen() {
        List<Operator> operators = new ArrayList<>(Arrays.asList(
                new AdditionOperator(), new SubtractionOperator(),
                new MultiplicationOperator(), new DivisionOperator(),
                new NegationOperator(), new AbsoluteValOperator(),
                new ExponentiationOperator(), new NaturalLogOperator(),
                new SineOperator(), new CosineOperator(), new SquareRootOperator()));

        Calculator calculator = new Calculator(operators);
        calculator.launch();
    }
}
