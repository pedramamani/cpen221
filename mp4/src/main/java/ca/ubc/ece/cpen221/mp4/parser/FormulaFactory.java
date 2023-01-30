package formula;

import java.util.Stack;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class FormulaFactory {

    /**
     * @param string must contain a well-formed formula string of boolean literals and operators..
     * @return Formula corresponding to the string
     */
    public static Formula parse(String string) {
        CharStream stream = new ANTLRInputStream(string);
        FormulaLexer lexer = new FormulaLexer(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);

        FormulaParser parser = new FormulaParser(tokens);
        parser.reportErrorsAsExceptions();

        ParseTree tree = parser.formula();

        // debugging option #1: print the tree to the console
//        System.err.println(tree.toStringTree(parser));

        // debugging option #2: show the tree in a window
//        ((RuleContext)tree).inspect(parser);

        // debugging option #3: walk the tree with a listener
//        new ParseTreeWalker().walk(new FormulaListener_PrintEverything(), tree);

        ParseTreeWalker walker = new ParseTreeWalker();
        FormulaListener_FormulaCreator listener = new FormulaListener_FormulaCreator();
        walker.walk(listener, tree);

        return listener.printResult();
    }

    private static class FormulaListener_FormulaCreator extends FormulaBaseListener {

        @Override
        public void exitLiteral(FormulaParser.LiteralContext ctx) {
            Formula literal = new BooleanLiteral(ctx.start.getType() == FormulaLexer.TRUE);
            stack.push(literal);
        }

        @Override
        public void exitConjunction(FormulaParser.ConjunctionContext ctx) {
            if (ctx.AND() != null) {
                // we matched the AND rule
                Formula right = stack.pop();
                Formula left = stack.pop();
                Formula and = new And(left, right);
                stack.push(and);
            } else {
                // do nothing, because we just matched a literal and its BooleanLiteral is already on the stack
            }
        }

        @Override
        public void exitFormula(FormulaParser.FormulaContext ctx) {
            // do nothing, because the top of the stack should have the node already in it
            assert stack.size() == 1;
        }

        public void printResult() {
            System.out.println();
        }
    }


    private static class FormulaListener_PrintEverything extends FormulaBaseListener {
        public void enterFormula(FormulaParser.FormulaContext ctx) { System.err.println("entering formula " + ctx.getText()); }
        public void exitFormula(FormulaParser.FormulaContext ctx) { System.err.println("exiting formula " + ctx.getText()); }

        public void enterConjunction(FormulaParser.ConjunctionContext ctx) { System.err.println("entering conjunction " + ctx.getText()); }
        public void exitConjunction(FormulaParser.ConjunctionContext ctx) { System.err.println("exiting conjunction " + ctx.getText()); }

        public void enterLiteral(FormulaParser.LiteralContext ctx) { System.err.println("entering literal " + ctx.getText()); }
        public void exitLiteral(FormulaParser.LiteralContext ctx) { System.err.println("exiting literal " + ctx.getText()); }
    }

}
