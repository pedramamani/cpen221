// Generated from Formula.g4 by ANTLR 4.5

    package lex;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FormulaParser}.
 */
public interface FormulaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FormulaParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(FormulaParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(FormulaParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(FormulaParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(FormulaParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#create}.
	 * @param ctx the parse tree
	 */
	void enterCreate(FormulaParser.CreateContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#create}.
	 * @param ctx the parse tree
	 */
	void exitCreate(FormulaParser.CreateContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#exit}.
	 * @param ctx the parse tree
	 */
	void enterExit(FormulaParser.ExitContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#exit}.
	 * @param ctx the parse tree
	 */
	void exitExit(FormulaParser.ExitContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#insert}.
	 * @param ctx the parse tree
	 */
	void enterInsert(FormulaParser.InsertContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#insert}.
	 * @param ctx the parse tree
	 */
	void exitInsert(FormulaParser.InsertContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#load}.
	 * @param ctx the parse tree
	 */
	void enterLoad(FormulaParser.LoadContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#load}.
	 * @param ctx the parse tree
	 */
	void exitLoad(FormulaParser.LoadContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(FormulaParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(FormulaParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#select}.
	 * @param ctx the parse tree
	 */
	void enterSelect(FormulaParser.SelectContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#select}.
	 * @param ctx the parse tree
	 */
	void exitSelect(FormulaParser.SelectContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#store}.
	 * @param ctx the parse tree
	 */
	void enterStore(FormulaParser.StoreContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#store}.
	 * @param ctx the parse tree
	 */
	void exitStore(FormulaParser.StoreContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(FormulaParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(FormulaParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#definition}.
	 * @param ctx the parse tree
	 */
	void enterDefinition(FormulaParser.DefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#definition}.
	 * @param ctx the parse tree
	 */
	void exitDefinition(FormulaParser.DefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#rows}.
	 * @param ctx the parse tree
	 */
	void enterRows(FormulaParser.RowsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#rows}.
	 * @param ctx the parse tree
	 */
	void exitRows(FormulaParser.RowsContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(FormulaParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(FormulaParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#column}.
	 * @param ctx the parse tree
	 */
	void enterColumn(FormulaParser.ColumnContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#column}.
	 * @param ctx the parse tree
	 */
	void exitColumn(FormulaParser.ColumnContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#table}.
	 * @param ctx the parse tree
	 */
	void enterTable(FormulaParser.TableContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#table}.
	 * @param ctx the parse tree
	 */
	void exitTable(FormulaParser.TableContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#tables}.
	 * @param ctx the parse tree
	 */
	void enterTables(FormulaParser.TablesContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#tables}.
	 * @param ctx the parse tree
	 */
	void exitTables(FormulaParser.TablesContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(FormulaParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(FormulaParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#row}.
	 * @param ctx the parse tree
	 */
	void enterRow(FormulaParser.RowContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#row}.
	 * @param ctx the parse tree
	 */
	void exitRow(FormulaParser.RowContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#bool}.
	 * @param ctx the parse tree
	 */
	void enterBool(FormulaParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#bool}.
	 * @param ctx the parse tree
	 */
	void exitBool(FormulaParser.BoolContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#relation}.
	 * @param ctx the parse tree
	 */
	void enterRelation(FormulaParser.RelationContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#relation}.
	 * @param ctx the parse tree
	 */
	void exitRelation(FormulaParser.RelationContext ctx);
}