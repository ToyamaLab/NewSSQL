// Generated from query.g4 by ANTLR 4.5

package supersql.parser;

import java.util.*;
import java.io.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link queryParser}.
 */
public interface queryListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link queryParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(queryParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(queryParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(queryParser.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(queryParser.RootContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#media}.
	 * @param ctx the parse tree
	 */
	void enterMedia(queryParser.MediaContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#media}.
	 * @param ctx the parse tree
	 */
	void exitMedia(queryParser.MediaContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#operand}.
	 * @param ctx the parse tree
	 */
	void enterOperand(queryParser.OperandContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#operand}.
	 * @param ctx the parse tree
	 */
	void exitOperand(queryParser.OperandContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(queryParser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(queryParser.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#as_pair}.
	 * @param ctx the parse tree
	 */
	void enterAs_pair(queryParser.As_pairContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#as_pair}.
	 * @param ctx the parse tree
	 */
	void exitAs_pair(queryParser.As_pairContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#grouper}.
	 * @param ctx the parse tree
	 */
	void enterGrouper(queryParser.GrouperContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#grouper}.
	 * @param ctx the parse tree
	 */
	void exitGrouper(queryParser.GrouperContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#composite_iterator}.
	 * @param ctx the parse tree
	 */
	void enterComposite_iterator(queryParser.Composite_iteratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#composite_iterator}.
	 * @param ctx the parse tree
	 */
	void exitComposite_iterator(queryParser.Composite_iteratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(queryParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(queryParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#t_exp}.
	 * @param ctx the parse tree
	 */
	void enterT_exp(queryParser.T_expContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#t_exp}.
	 * @param ctx the parse tree
	 */
	void exitT_exp(queryParser.T_expContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#concat_exp}.
	 * @param ctx the parse tree
	 */
	void enterConcat_exp(queryParser.Concat_expContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#concat_exp}.
	 * @param ctx the parse tree
	 */
	void exitConcat_exp(queryParser.Concat_expContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#d_exp}.
	 * @param ctx the parse tree
	 */
	void enterD_exp(queryParser.D_expContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#d_exp}.
	 * @param ctx the parse tree
	 */
	void exitD_exp(queryParser.D_expContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#v_exp}.
	 * @param ctx the parse tree
	 */
	void enterV_exp(queryParser.V_expContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#v_exp}.
	 * @param ctx the parse tree
	 */
	void exitV_exp(queryParser.V_expContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#h_exp}.
	 * @param ctx the parse tree
	 */
	void enterH_exp(queryParser.H_expContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#h_exp}.
	 * @param ctx the parse tree
	 */
	void exitH_exp(queryParser.H_expContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#n_exp}.
	 * @param ctx the parse tree
	 */
	void enterN_exp(queryParser.N_expContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#n_exp}.
	 * @param ctx the parse tree
	 */
	void exitN_exp(queryParser.N_expContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#sorting}.
	 * @param ctx the parse tree
	 */
	void enterSorting(queryParser.SortingContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#sorting}.
	 * @param ctx the parse tree
	 */
	void exitSorting(queryParser.SortingContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(queryParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(queryParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#sqlfunc}.
	 * @param ctx the parse tree
	 */
	void enterSqlfunc(queryParser.SqlfuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#sqlfunc}.
	 * @param ctx the parse tree
	 */
	void exitSqlfunc(queryParser.SqlfuncContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#sqlprocess}.
	 * @param ctx the parse tree
	 */
	void enterSqlprocess(queryParser.SqlprocessContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#sqlprocess}.
	 * @param ctx the parse tree
	 */
	void exitSqlprocess(queryParser.SqlprocessContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#aggregate}.
	 * @param ctx the parse tree
	 */
	void enterAggregate(queryParser.AggregateContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#aggregate}.
	 * @param ctx the parse tree
	 */
	void exitAggregate(queryParser.AggregateContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#ggplot}.
	 * @param ctx the parse tree
	 */
	void enterGgplot(queryParser.GgplotContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#ggplot}.
	 * @param ctx the parse tree
	 */
	void exitGgplot(queryParser.GgplotContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#if_then_else}.
	 * @param ctx the parse tree
	 */
	void enterIf_then_else(queryParser.If_then_elseContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#if_then_else}.
	 * @param ctx the parse tree
	 */
	void exitIf_then_else(queryParser.If_then_elseContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#arithmetics}.
	 * @param ctx the parse tree
	 */
	void enterArithmetics(queryParser.ArithmeticsContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#arithmetics}.
	 * @param ctx the parse tree
	 */
	void exitArithmetics(queryParser.ArithmeticsContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#arith}.
	 * @param ctx the parse tree
	 */
	void enterArith(queryParser.ArithContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#arith}.
	 * @param ctx the parse tree
	 */
	void exitArith(queryParser.ArithContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#from_where}.
	 * @param ctx the parse tree
	 */
	void enterFrom_where(queryParser.From_whereContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#from_where}.
	 * @param ctx the parse tree
	 */
	void exitFrom_where(queryParser.From_whereContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#error}.
	 * @param ctx the parse tree
	 */
	void enterError(queryParser.ErrorContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#error}.
	 * @param ctx the parse tree
	 */
	void exitError(queryParser.ErrorContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#sql_stmt_list}.
	 * @param ctx the parse tree
	 */
	void enterSql_stmt_list(queryParser.Sql_stmt_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#sql_stmt_list}.
	 * @param ctx the parse tree
	 */
	void exitSql_stmt_list(queryParser.Sql_stmt_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#sql_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSql_stmt(queryParser.Sql_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#sql_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSql_stmt(queryParser.Sql_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#factored_select_stmt}.
	 * @param ctx the parse tree
	 */
	void enterFactored_select_stmt(queryParser.Factored_select_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#factored_select_stmt}.
	 * @param ctx the parse tree
	 */
	void exitFactored_select_stmt(queryParser.Factored_select_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#select_core}.
	 * @param ctx the parse tree
	 */
	void enterSelect_core(queryParser.Select_coreContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#select_core}.
	 * @param ctx the parse tree
	 */
	void exitSelect_core(queryParser.Select_coreContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#where_clause}.
	 * @param ctx the parse tree
	 */
	void enterWhere_clause(queryParser.Where_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#where_clause}.
	 * @param ctx the parse tree
	 */
	void exitWhere_clause(queryParser.Where_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#result_column}.
	 * @param ctx the parse tree
	 */
	void enterResult_column(queryParser.Result_columnContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#result_column}.
	 * @param ctx the parse tree
	 */
	void exitResult_column(queryParser.Result_columnContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#table_or_subquery}.
	 * @param ctx the parse tree
	 */
	void enterTable_or_subquery(queryParser.Table_or_subqueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#table_or_subquery}.
	 * @param ctx the parse tree
	 */
	void exitTable_or_subquery(queryParser.Table_or_subqueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#keyword}.
	 * @param ctx the parse tree
	 */
	void enterKeyword(queryParser.KeywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#keyword}.
	 * @param ctx the parse tree
	 */
	void exitKeyword(queryParser.KeywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#select_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSelect_stmt(queryParser.Select_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#select_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSelect_stmt(queryParser.Select_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#select_or_values}.
	 * @param ctx the parse tree
	 */
	void enterSelect_or_values(queryParser.Select_or_valuesContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#select_or_values}.
	 * @param ctx the parse tree
	 */
	void exitSelect_or_values(queryParser.Select_or_valuesContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#compound_operator}.
	 * @param ctx the parse tree
	 */
	void enterCompound_operator(queryParser.Compound_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#compound_operator}.
	 * @param ctx the parse tree
	 */
	void exitCompound_operator(queryParser.Compound_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#join_clause}.
	 * @param ctx the parse tree
	 */
	void enterJoin_clause(queryParser.Join_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#join_clause}.
	 * @param ctx the parse tree
	 */
	void exitJoin_clause(queryParser.Join_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#join_operator}.
	 * @param ctx the parse tree
	 */
	void enterJoin_operator(queryParser.Join_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#join_operator}.
	 * @param ctx the parse tree
	 */
	void exitJoin_operator(queryParser.Join_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#join_constraint}.
	 * @param ctx the parse tree
	 */
	void enterJoin_constraint(queryParser.Join_constraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#join_constraint}.
	 * @param ctx the parse tree
	 */
	void exitJoin_constraint(queryParser.Join_constraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#common_table_expression}.
	 * @param ctx the parse tree
	 */
	void enterCommon_table_expression(queryParser.Common_table_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#common_table_expression}.
	 * @param ctx the parse tree
	 */
	void exitCommon_table_expression(queryParser.Common_table_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#ordering_term}.
	 * @param ctx the parse tree
	 */
	void enterOrdering_term(queryParser.Ordering_termContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#ordering_term}.
	 * @param ctx the parse tree
	 */
	void exitOrdering_term(queryParser.Ordering_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(queryParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(queryParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#literal_value}.
	 * @param ctx the parse tree
	 */
	void enterLiteral_value(queryParser.Literal_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#literal_value}.
	 * @param ctx the parse tree
	 */
	void exitLiteral_value(queryParser.Literal_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#unary_operator}.
	 * @param ctx the parse tree
	 */
	void enterUnary_operator(queryParser.Unary_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#unary_operator}.
	 * @param ctx the parse tree
	 */
	void exitUnary_operator(queryParser.Unary_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(queryParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(queryParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#type_name}.
	 * @param ctx the parse tree
	 */
	void enterType_name(queryParser.Type_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#type_name}.
	 * @param ctx the parse tree
	 */
	void exitType_name(queryParser.Type_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#function_name}.
	 * @param ctx the parse tree
	 */
	void enterFunction_name(queryParser.Function_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#function_name}.
	 * @param ctx the parse tree
	 */
	void exitFunction_name(queryParser.Function_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#ag_function_name}.
	 * @param ctx the parse tree
	 */
	void enterAg_function_name(queryParser.Ag_function_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#ag_function_name}.
	 * @param ctx the parse tree
	 */
	void exitAg_function_name(queryParser.Ag_function_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#ag_keyword}.
	 * @param ctx the parse tree
	 */
	void enterAg_keyword(queryParser.Ag_keywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#ag_keyword}.
	 * @param ctx the parse tree
	 */
	void exitAg_keyword(queryParser.Ag_keywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#gg_function_name}.
	 * @param ctx the parse tree
	 */
	void enterGg_function_name(queryParser.Gg_function_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#gg_function_name}.
	 * @param ctx the parse tree
	 */
	void exitGg_function_name(queryParser.Gg_function_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#gg_keyword}.
	 * @param ctx the parse tree
	 */
	void enterGg_keyword(queryParser.Gg_keywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#gg_keyword}.
	 * @param ctx the parse tree
	 */
	void exitGg_keyword(queryParser.Gg_keywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#collation_name}.
	 * @param ctx the parse tree
	 */
	void enterCollation_name(queryParser.Collation_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#collation_name}.
	 * @param ctx the parse tree
	 */
	void exitCollation_name(queryParser.Collation_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#database_name}.
	 * @param ctx the parse tree
	 */
	void enterDatabase_name(queryParser.Database_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#database_name}.
	 * @param ctx the parse tree
	 */
	void exitDatabase_name(queryParser.Database_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#table_name}.
	 * @param ctx the parse tree
	 */
	void enterTable_name(queryParser.Table_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#table_name}.
	 * @param ctx the parse tree
	 */
	void exitTable_name(queryParser.Table_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#column_alias}.
	 * @param ctx the parse tree
	 */
	void enterColumn_alias(queryParser.Column_aliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#column_alias}.
	 * @param ctx the parse tree
	 */
	void exitColumn_alias(queryParser.Column_aliasContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#column_name}.
	 * @param ctx the parse tree
	 */
	void enterColumn_name(queryParser.Column_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#column_name}.
	 * @param ctx the parse tree
	 */
	void exitColumn_name(queryParser.Column_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#table_alias}.
	 * @param ctx the parse tree
	 */
	void enterTable_alias(queryParser.Table_aliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#table_alias}.
	 * @param ctx the parse tree
	 */
	void exitTable_alias(queryParser.Table_aliasContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#index_name}.
	 * @param ctx the parse tree
	 */
	void enterIndex_name(queryParser.Index_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#index_name}.
	 * @param ctx the parse tree
	 */
	void exitIndex_name(queryParser.Index_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#any_name}.
	 * @param ctx the parse tree
	 */
	void enterAny_name(queryParser.Any_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#any_name}.
	 * @param ctx the parse tree
	 */
	void exitAny_name(queryParser.Any_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#sl}.
	 * @param ctx the parse tree
	 */
	void enterSl(queryParser.SlContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#sl}.
	 * @param ctx the parse tree
	 */
	void exitSl(queryParser.SlContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#signed_number}.
	 * @param ctx the parse tree
	 */
	void enterSigned_number(queryParser.Signed_numberContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#signed_number}.
	 * @param ctx the parse tree
	 */
	void exitSigned_number(queryParser.Signed_numberContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#raise_function}.
	 * @param ctx the parse tree
	 */
	void enterRaise_function(queryParser.Raise_functionContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#raise_function}.
	 * @param ctx the parse tree
	 */
	void exitRaise_function(queryParser.Raise_functionContext ctx);
	/**
	 * Enter a parse tree produced by {@link queryParser#error_message}.
	 * @param ctx the parse tree
	 */
	void enterError_message(queryParser.Error_messageContext ctx);
	/**
	 * Exit a parse tree produced by {@link queryParser#error_message}.
	 * @param ctx the parse tree
	 */
	void exitError_message(queryParser.Error_messageContext ctx);
}