// Generated from query_dv.g4 by ANTLR 4.8

package supersql.parser;

import java.util.*;
import java.io.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link query_dvParser}.
 */
public interface query_dvListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link query_dvParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(query_dvParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(query_dvParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(query_dvParser.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(query_dvParser.RootContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#media}.
	 * @param ctx the parse tree
	 */
	void enterMedia(query_dvParser.MediaContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#media}.
	 * @param ctx the parse tree
	 */
	void exitMedia(query_dvParser.MediaContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#operand}.
	 * @param ctx the parse tree
	 */
	void enterOperand(query_dvParser.OperandContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#operand}.
	 * @param ctx the parse tree
	 */
	void exitOperand(query_dvParser.OperandContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(query_dvParser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(query_dvParser.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#join_string}.
	 * @param ctx the parse tree
	 */
	void enterJoin_string(query_dvParser.Join_stringContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#join_string}.
	 * @param ctx the parse tree
	 */
	void exitJoin_string(query_dvParser.Join_stringContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#grouper}.
	 * @param ctx the parse tree
	 */
	void enterGrouper(query_dvParser.GrouperContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#grouper}.
	 * @param ctx the parse tree
	 */
	void exitGrouper(query_dvParser.GrouperContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#composite_iterator}.
	 * @param ctx the parse tree
	 */
	void enterComposite_iterator(query_dvParser.Composite_iteratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#composite_iterator}.
	 * @param ctx the parse tree
	 */
	void exitComposite_iterator(query_dvParser.Composite_iteratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(query_dvParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(query_dvParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#t_exp}.
	 * @param ctx the parse tree
	 */
	void enterT_exp(query_dvParser.T_expContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#t_exp}.
	 * @param ctx the parse tree
	 */
	void exitT_exp(query_dvParser.T_expContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#d_exp}.
	 * @param ctx the parse tree
	 */
	void enterD_exp(query_dvParser.D_expContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#d_exp}.
	 * @param ctx the parse tree
	 */
	void exitD_exp(query_dvParser.D_expContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#v_exp}.
	 * @param ctx the parse tree
	 */
	void enterV_exp(query_dvParser.V_expContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#v_exp}.
	 * @param ctx the parse tree
	 */
	void exitV_exp(query_dvParser.V_expContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#h_exp}.
	 * @param ctx the parse tree
	 */
	void enterH_exp(query_dvParser.H_expContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#h_exp}.
	 * @param ctx the parse tree
	 */
	void exitH_exp(query_dvParser.H_expContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#n_exp}.
	 * @param ctx the parse tree
	 */
	void enterN_exp(query_dvParser.N_expContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#n_exp}.
	 * @param ctx the parse tree
	 */
	void exitN_exp(query_dvParser.N_expContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#sorting}.
	 * @param ctx the parse tree
	 */
	void enterSorting(query_dvParser.SortingContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#sorting}.
	 * @param ctx the parse tree
	 */
	void exitSorting(query_dvParser.SortingContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(query_dvParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(query_dvParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#sqlfunc}.
	 * @param ctx the parse tree
	 */
	void enterSqlfunc(query_dvParser.SqlfuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#sqlfunc}.
	 * @param ctx the parse tree
	 */
	void exitSqlfunc(query_dvParser.SqlfuncContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#aggregate}.
	 * @param ctx the parse tree
	 */
	void enterAggregate(query_dvParser.AggregateContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#aggregate}.
	 * @param ctx the parse tree
	 */
	void exitAggregate(query_dvParser.AggregateContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#if_then_else}.
	 * @param ctx the parse tree
	 */
	void enterIf_then_else(query_dvParser.If_then_elseContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#if_then_else}.
	 * @param ctx the parse tree
	 */
	void exitIf_then_else(query_dvParser.If_then_elseContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#arithmetics}.
	 * @param ctx the parse tree
	 */
	void enterArithmetics(query_dvParser.ArithmeticsContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#arithmetics}.
	 * @param ctx the parse tree
	 */
	void exitArithmetics(query_dvParser.ArithmeticsContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#arith}.
	 * @param ctx the parse tree
	 */
	void enterArith(query_dvParser.ArithContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#arith}.
	 * @param ctx the parse tree
	 */
	void exitArith(query_dvParser.ArithContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#from_where}.
	 * @param ctx the parse tree
	 */
	void enterFrom_where(query_dvParser.From_whereContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#from_where}.
	 * @param ctx the parse tree
	 */
	void exitFrom_where(query_dvParser.From_whereContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#error}.
	 * @param ctx the parse tree
	 */
	void enterError(query_dvParser.ErrorContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#error}.
	 * @param ctx the parse tree
	 */
	void exitError(query_dvParser.ErrorContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#sql_stmt_list}.
	 * @param ctx the parse tree
	 */
	void enterSql_stmt_list(query_dvParser.Sql_stmt_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#sql_stmt_list}.
	 * @param ctx the parse tree
	 */
	void exitSql_stmt_list(query_dvParser.Sql_stmt_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#sql_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSql_stmt(query_dvParser.Sql_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#sql_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSql_stmt(query_dvParser.Sql_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#factored_select_stmt}.
	 * @param ctx the parse tree
	 */
	void enterFactored_select_stmt(query_dvParser.Factored_select_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#factored_select_stmt}.
	 * @param ctx the parse tree
	 */
	void exitFactored_select_stmt(query_dvParser.Factored_select_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#select_core}.
	 * @param ctx the parse tree
	 */
	void enterSelect_core(query_dvParser.Select_coreContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#select_core}.
	 * @param ctx the parse tree
	 */
	void exitSelect_core(query_dvParser.Select_coreContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#where_clause}.
	 * @param ctx the parse tree
	 */
	void enterWhere_clause(query_dvParser.Where_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#where_clause}.
	 * @param ctx the parse tree
	 */
	void exitWhere_clause(query_dvParser.Where_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#result_column}.
	 * @param ctx the parse tree
	 */
	void enterResult_column(query_dvParser.Result_columnContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#result_column}.
	 * @param ctx the parse tree
	 */
	void exitResult_column(query_dvParser.Result_columnContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#table_or_subquery}.
	 * @param ctx the parse tree
	 */
	void enterTable_or_subquery(query_dvParser.Table_or_subqueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#table_or_subquery}.
	 * @param ctx the parse tree
	 */
	void exitTable_or_subquery(query_dvParser.Table_or_subqueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#keyword}.
	 * @param ctx the parse tree
	 */
	void enterKeyword(query_dvParser.KeywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#keyword}.
	 * @param ctx the parse tree
	 */
	void exitKeyword(query_dvParser.KeywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#select_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSelect_stmt(query_dvParser.Select_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#select_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSelect_stmt(query_dvParser.Select_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#select_or_values}.
	 * @param ctx the parse tree
	 */
	void enterSelect_or_values(query_dvParser.Select_or_valuesContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#select_or_values}.
	 * @param ctx the parse tree
	 */
	void exitSelect_or_values(query_dvParser.Select_or_valuesContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#compound_operator}.
	 * @param ctx the parse tree
	 */
	void enterCompound_operator(query_dvParser.Compound_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#compound_operator}.
	 * @param ctx the parse tree
	 */
	void exitCompound_operator(query_dvParser.Compound_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#join_clause}.
	 * @param ctx the parse tree
	 */
	void enterJoin_clause(query_dvParser.Join_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#join_clause}.
	 * @param ctx the parse tree
	 */
	void exitJoin_clause(query_dvParser.Join_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#join_operator}.
	 * @param ctx the parse tree
	 */
	void enterJoin_operator(query_dvParser.Join_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#join_operator}.
	 * @param ctx the parse tree
	 */
	void exitJoin_operator(query_dvParser.Join_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#join_constraint}.
	 * @param ctx the parse tree
	 */
	void enterJoin_constraint(query_dvParser.Join_constraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#join_constraint}.
	 * @param ctx the parse tree
	 */
	void exitJoin_constraint(query_dvParser.Join_constraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#common_table_expression}.
	 * @param ctx the parse tree
	 */
	void enterCommon_table_expression(query_dvParser.Common_table_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#common_table_expression}.
	 * @param ctx the parse tree
	 */
	void exitCommon_table_expression(query_dvParser.Common_table_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#ordering_term}.
	 * @param ctx the parse tree
	 */
	void enterOrdering_term(query_dvParser.Ordering_termContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#ordering_term}.
	 * @param ctx the parse tree
	 */
	void exitOrdering_term(query_dvParser.Ordering_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(query_dvParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(query_dvParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#literal_value}.
	 * @param ctx the parse tree
	 */
	void enterLiteral_value(query_dvParser.Literal_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#literal_value}.
	 * @param ctx the parse tree
	 */
	void exitLiteral_value(query_dvParser.Literal_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#unary_operator}.
	 * @param ctx the parse tree
	 */
	void enterUnary_operator(query_dvParser.Unary_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#unary_operator}.
	 * @param ctx the parse tree
	 */
	void exitUnary_operator(query_dvParser.Unary_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(query_dvParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(query_dvParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#type_name}.
	 * @param ctx the parse tree
	 */
	void enterType_name(query_dvParser.Type_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#type_name}.
	 * @param ctx the parse tree
	 */
	void exitType_name(query_dvParser.Type_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#function_name}.
	 * @param ctx the parse tree
	 */
	void enterFunction_name(query_dvParser.Function_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#function_name}.
	 * @param ctx the parse tree
	 */
	void exitFunction_name(query_dvParser.Function_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#ag_function_name}.
	 * @param ctx the parse tree
	 */
	void enterAg_function_name(query_dvParser.Ag_function_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#ag_function_name}.
	 * @param ctx the parse tree
	 */
	void exitAg_function_name(query_dvParser.Ag_function_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#ag_keyword}.
	 * @param ctx the parse tree
	 */
	void enterAg_keyword(query_dvParser.Ag_keywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#ag_keyword}.
	 * @param ctx the parse tree
	 */
	void exitAg_keyword(query_dvParser.Ag_keywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#collation_name}.
	 * @param ctx the parse tree
	 */
	void enterCollation_name(query_dvParser.Collation_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#collation_name}.
	 * @param ctx the parse tree
	 */
	void exitCollation_name(query_dvParser.Collation_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#database_name}.
	 * @param ctx the parse tree
	 */
	void enterDatabase_name(query_dvParser.Database_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#database_name}.
	 * @param ctx the parse tree
	 */
	void exitDatabase_name(query_dvParser.Database_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#table_name}.
	 * @param ctx the parse tree
	 */
	void enterTable_name(query_dvParser.Table_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#table_name}.
	 * @param ctx the parse tree
	 */
	void exitTable_name(query_dvParser.Table_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#column_alias}.
	 * @param ctx the parse tree
	 */
	void enterColumn_alias(query_dvParser.Column_aliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#column_alias}.
	 * @param ctx the parse tree
	 */
	void exitColumn_alias(query_dvParser.Column_aliasContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#column_name}.
	 * @param ctx the parse tree
	 */
	void enterColumn_name(query_dvParser.Column_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#column_name}.
	 * @param ctx the parse tree
	 */
	void exitColumn_name(query_dvParser.Column_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#table_alias}.
	 * @param ctx the parse tree
	 */
	void enterTable_alias(query_dvParser.Table_aliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#table_alias}.
	 * @param ctx the parse tree
	 */
	void exitTable_alias(query_dvParser.Table_aliasContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#index_name}.
	 * @param ctx the parse tree
	 */
	void enterIndex_name(query_dvParser.Index_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#index_name}.
	 * @param ctx the parse tree
	 */
	void exitIndex_name(query_dvParser.Index_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#any_name}.
	 * @param ctx the parse tree
	 */
	void enterAny_name(query_dvParser.Any_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#any_name}.
	 * @param ctx the parse tree
	 */
	void exitAny_name(query_dvParser.Any_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#sl}.
	 * @param ctx the parse tree
	 */
	void enterSl(query_dvParser.SlContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#sl}.
	 * @param ctx the parse tree
	 */
	void exitSl(query_dvParser.SlContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#signed_number}.
	 * @param ctx the parse tree
	 */
	void enterSigned_number(query_dvParser.Signed_numberContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#signed_number}.
	 * @param ctx the parse tree
	 */
	void exitSigned_number(query_dvParser.Signed_numberContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#raise_function}.
	 * @param ctx the parse tree
	 */
	void enterRaise_function(query_dvParser.Raise_functionContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#raise_function}.
	 * @param ctx the parse tree
	 */
	void exitRaise_function(query_dvParser.Raise_functionContext ctx);
	/**
	 * Enter a parse tree produced by {@link query_dvParser#error_message}.
	 * @param ctx the parse tree
	 */
	void enterError_message(query_dvParser.Error_messageContext ctx);
	/**
	 * Exit a parse tree produced by {@link query_dvParser#error_message}.
	 * @param ctx the parse tree
	 */
	void exitError_message(query_dvParser.Error_messageContext ctx);
}