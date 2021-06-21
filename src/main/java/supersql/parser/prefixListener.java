// Generated from prefix.g4 by ANTLR 4.5

package supersql.parser;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link prefixParser}.
 */
public interface prefixListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link prefixParser#prefix}.
	 * @param ctx the parse tree
	 */
	void enterPrefix(prefixParser.PrefixContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#prefix}.
	 * @param ctx the parse tree
	 */
	void exitPrefix(prefixParser.PrefixContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#fix}.
	 * @param ctx the parse tree
	 */
	void enterFix(prefixParser.FixContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#fix}.
	 * @param ctx the parse tree
	 */
	void exitFix(prefixParser.FixContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#operand}.
	 * @param ctx the parse tree
	 */
	void enterOperand(prefixParser.OperandContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#operand}.
	 * @param ctx the parse tree
	 */
	void exitOperand(prefixParser.OperandContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#exdef}.
	 * @param ctx the parse tree
	 */
	void enterExdef(prefixParser.ExdefContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#exdef}.
	 * @param ctx the parse tree
	 */
	void exitExdef(prefixParser.ExdefContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#foreach}.
	 * @param ctx the parse tree
	 */
	void enterForeach(prefixParser.ForeachContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#foreach}.
	 * @param ctx the parse tree
	 */
	void exitForeach(prefixParser.ForeachContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(prefixParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(prefixParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(prefixParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(prefixParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#function_name}.
	 * @param ctx the parse tree
	 */
	void enterFunction_name(prefixParser.Function_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#function_name}.
	 * @param ctx the parse tree
	 */
	void exitFunction_name(prefixParser.Function_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#database_name}.
	 * @param ctx the parse tree
	 */
	void enterDatabase_name(prefixParser.Database_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#database_name}.
	 * @param ctx the parse tree
	 */
	void exitDatabase_name(prefixParser.Database_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#table_name}.
	 * @param ctx the parse tree
	 */
	void enterTable_name(prefixParser.Table_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#table_name}.
	 * @param ctx the parse tree
	 */
	void exitTable_name(prefixParser.Table_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#column_alias}.
	 * @param ctx the parse tree
	 */
	void enterColumn_alias(prefixParser.Column_aliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#column_alias}.
	 * @param ctx the parse tree
	 */
	void exitColumn_alias(prefixParser.Column_aliasContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#column_name}.
	 * @param ctx the parse tree
	 */
	void enterColumn_name(prefixParser.Column_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#column_name}.
	 * @param ctx the parse tree
	 */
	void exitColumn_name(prefixParser.Column_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#table_alias}.
	 * @param ctx the parse tree
	 */
	void enterTable_alias(prefixParser.Table_aliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#table_alias}.
	 * @param ctx the parse tree
	 */
	void exitTable_alias(prefixParser.Table_aliasContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#index_name}.
	 * @param ctx the parse tree
	 */
	void enterIndex_name(prefixParser.Index_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#index_name}.
	 * @param ctx the parse tree
	 */
	void exitIndex_name(prefixParser.Index_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#any_name}.
	 * @param ctx the parse tree
	 */
	void enterAny_name(prefixParser.Any_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#any_name}.
	 * @param ctx the parse tree
	 */
	void exitAny_name(prefixParser.Any_nameContext ctx);
}