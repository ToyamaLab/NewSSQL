// Generated from query.g4 by ANTLR 4.5

package supersql.parser;

import java.util.*;
import java.io.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class queryParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, K_ABORT=22, K_ADD=23, K_ALL=24, 
		K_AND=25, K_AS=26, K_ASC=27, K_BETWEEN=28, K_BY=29, K_CASE=30, K_CAST=31, 
		K_COLLATE=32, K_CROSS=33, K_CURRENT_DATE=34, K_CURRENT_TIME=35, K_CURRENT_TIMESTAMP=36, 
		K_DESC=37, K_DISTINCT=38, K_ELSE=39, K_END=40, K_ESCAPE=41, K_EXCEPT=42, 
		K_EXISTS=43, K_FAIL=44, K_FULL=45, K_FROM=46, K_GLOB=47, K_GROUP=48, K_HAVING=49, 
		K_IF=50, K_IGNORE=51, K_IN=52, K_INDEXED=53, K_INNER=54, K_INTERSECT=55, 
		K_IS=56, K_ISNULL=57, K_JOIN=58, K_LEFT=59, K_LIKE=60, K_LIMIT=61, K_MATCH=62, 
		K_NATURAL=63, K_NO=64, K_NOT=65, K_NOTNULL=66, K_NULL=67, K_OFFSET=68, 
		K_ON=69, K_OR=70, K_ORDER=71, K_OUTER=72, K_RAISE=73, K_RECURSIVE=74, 
		K_REGEXP=75, K_RIGHT=76, K_ROLLBACK=77, K_SELECT=78, K_THEN=79, K_UNION=80, 
		K_USING=81, K_VALUES=82, K_WHEN=83, K_WHERE=84, K_WITH=85, K_GENERATE=86, 
		K_MAX=87, K_MIN=88, K_AVG=89, K_COUNT=90, K_SUM=91, K_GGPLOT=92, C0=93, 
		C1=94, C2=95, C3=96, C4=97, CIRCLE=98, DOT=99, OPEN_PARENTHESE=100, CLOSE_PARENTHESE=101, 
		OPEN_BRACKET=102, CLOSE_BRACKET=103, OPEN_BRACE=104, CLOSE_BRACE=105, 
		SEMICOLON=106, DECORATOR=107, NUMERIC_LITERAL=108, BLOB_LITERAL=109, BIND_PARAMETER=110, 
		IDENTIFIER=111, STRING_LITERAL=112, MULTI_LINE_COMMENT=113, SINGLE_LINE_COMMENT=114, 
		WS=115, UNEXPECTED_CHAR=116;
	public static final int
		RULE_query = 0, RULE_root = 1, RULE_media = 2, RULE_operand = 3, RULE_attribute = 4, 
		RULE_as_pair = 5, RULE_grouper = 6, RULE_composite_iterator = 7, RULE_exp = 8, 
		RULE_t_exp = 9, RULE_concat_exp = 10, RULE_d_exp = 11, RULE_v_exp = 12, 
		RULE_h_exp = 13, RULE_n_exp = 14, RULE_sorting = 15, RULE_function = 16, 
		RULE_sqlfunc = 17, RULE_aggregate = 18, RULE_ggplot = 19, RULE_if_then_else = 20, 
		RULE_arithmetics = 21, RULE_arith = 22, RULE_from_where = 23, RULE_error = 24, 
		RULE_sql_stmt_list = 25, RULE_sql_stmt = 26, RULE_factored_select_stmt = 27, 
		RULE_select_core = 28, RULE_where_clause = 29, RULE_result_column = 30, 
		RULE_table_or_subquery = 31, RULE_keyword = 32, RULE_select_stmt = 33, 
		RULE_select_or_values = 34, RULE_compound_operator = 35, RULE_join_clause = 36, 
		RULE_join_operator = 37, RULE_join_constraint = 38, RULE_common_table_expression = 39, 
		RULE_ordering_term = 40, RULE_expr = 41, RULE_literal_value = 42, RULE_unary_operator = 43, 
		RULE_name = 44, RULE_type_name = 45, RULE_function_name = 46, RULE_ag_function_name = 47, 
		RULE_ag_keyword = 48, RULE_gg_function_name = 49, RULE_gg_keyword = 50, 
		RULE_collation_name = 51, RULE_database_name = 52, RULE_table_name = 53, 
		RULE_column_alias = 54, RULE_column_name = 55, RULE_table_alias = 56, 
		RULE_index_name = 57, RULE_any_name = 58, RULE_sl = 59, RULE_signed_number = 60, 
		RULE_raise_function = 61, RULE_error_message = 62;
	public static final String[] ruleNames = {
		"query", "root", "media", "operand", "attribute", "as_pair", "grouper", 
		"composite_iterator", "exp", "t_exp", "concat_exp", "d_exp", "v_exp", 
		"h_exp", "n_exp", "sorting", "function", "sqlfunc", "aggregate", "ggplot", 
		"if_then_else", "arithmetics", "arith", "from_where", "error", "sql_stmt_list", 
		"sql_stmt", "factored_select_stmt", "select_core", "where_clause", "result_column", 
		"table_or_subquery", "keyword", "select_stmt", "select_or_values", "compound_operator", 
		"join_clause", "join_operator", "join_constraint", "common_table_expression", 
		"ordering_term", "expr", "literal_value", "unary_operator", "name", "type_name", 
		"function_name", "ag_function_name", "ag_keyword", "gg_function_name", 
		"gg_keyword", "collation_name", "database_name", "table_name", "column_alias", 
		"column_name", "table_alias", "index_name", "any_name", "sl", "signed_number", 
		"raise_function", "error_message"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'||'", "'$'", "'&'", "':'", "'*'", "'/'", "'+'", "'-'", "'\\'", 
		"'<<'", "'>>'", "'|'", "'<'", "'<='", "'>'", "'>='", "'='", "'=='", "'!='", 
		"'<>'", "'~'", null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "'?'", "','", "'!'", "'%'", "'#'", "'◯'", "'.'", "'('", "')'", "'['", 
		"']'", "'{'", "'}'", "';'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, "K_ABORT", 
		"K_ADD", "K_ALL", "K_AND", "K_AS", "K_ASC", "K_BETWEEN", "K_BY", "K_CASE", 
		"K_CAST", "K_COLLATE", "K_CROSS", "K_CURRENT_DATE", "K_CURRENT_TIME", 
		"K_CURRENT_TIMESTAMP", "K_DESC", "K_DISTINCT", "K_ELSE", "K_END", "K_ESCAPE", 
		"K_EXCEPT", "K_EXISTS", "K_FAIL", "K_FULL", "K_FROM", "K_GLOB", "K_GROUP", 
		"K_HAVING", "K_IF", "K_IGNORE", "K_IN", "K_INDEXED", "K_INNER", "K_INTERSECT", 
		"K_IS", "K_ISNULL", "K_JOIN", "K_LEFT", "K_LIKE", "K_LIMIT", "K_MATCH", 
		"K_NATURAL", "K_NO", "K_NOT", "K_NOTNULL", "K_NULL", "K_OFFSET", "K_ON", 
		"K_OR", "K_ORDER", "K_OUTER", "K_RAISE", "K_RECURSIVE", "K_REGEXP", "K_RIGHT", 
		"K_ROLLBACK", "K_SELECT", "K_THEN", "K_UNION", "K_USING", "K_VALUES", 
		"K_WHEN", "K_WHERE", "K_WITH", "K_GENERATE", "K_MAX", "K_MIN", "K_AVG", 
		"K_COUNT", "K_SUM", "K_GGPLOT", "C0", "C1", "C2", "C3", "C4", "CIRCLE", 
		"DOT", "OPEN_PARENTHESE", "CLOSE_PARENTHESE", "OPEN_BRACKET", "CLOSE_BRACKET", 
		"OPEN_BRACE", "CLOSE_BRACE", "SEMICOLON", "DECORATOR", "NUMERIC_LITERAL", 
		"BLOB_LITERAL", "BIND_PARAMETER", "IDENTIFIER", "STRING_LITERAL", "MULTI_LINE_COMMENT", 
		"SINGLE_LINE_COMMENT", "WS", "UNEXPECTED_CHAR"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "query.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public queryParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class QueryContext extends ParserRuleContext {
		public MediaContext media() {
			return getRuleContext(MediaContext.class,0);
		}
		public RootContext root() {
			return getRuleContext(RootContext.class,0);
		}
		public From_whereContext from_where() {
			return getRuleContext(From_whereContext.class,0);
		}
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitQuery(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_query);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			media();
			setState(127);
			root();
			setState(129);
			_la = _input.LA(1);
			if (_la==K_FROM || ((((_la - 78)) & ~0x3f) == 0 && ((1L << (_la - 78)) & ((1L << (K_SELECT - 78)) | (1L << (K_WITH - 78)) | (1L << (UNEXPECTED_CHAR - 78)))) != 0)) {
				{
				setState(128);
				from_where();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RootContext extends ParserRuleContext {
		public OperandContext operand() {
			return getRuleContext(OperandContext.class,0);
		}
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode DECORATOR() { return getToken(queryParser.DECORATOR, 0); }
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterRoot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitRoot(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_root);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(131);
				operand();
				}
				break;
			case 2:
				{
				setState(132);
				exp();
				}
				break;
			}
			setState(136);
			_la = _input.LA(1);
			if (_la==DECORATOR) {
				{
				setState(135);
				match(DECORATOR);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MediaContext extends ParserRuleContext {
		public TerminalNode K_GENERATE() { return getToken(queryParser.K_GENERATE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(queryParser.IDENTIFIER, 0); }
		public MediaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_media; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterMedia(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitMedia(this);
		}
	}

	public final MediaContext media() throws RecognitionException {
		MediaContext _localctx = new MediaContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_media);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(K_GENERATE);
			setState(139);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperandContext extends ParserRuleContext {
		public AttributeContext attribute() {
			return getRuleContext(AttributeContext.class,0);
		}
		public As_pairContext as_pair() {
			return getRuleContext(As_pairContext.class,0);
		}
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public SqlfuncContext sqlfunc() {
			return getRuleContext(SqlfuncContext.class,0);
		}
		public TerminalNode OPEN_BRACE() { return getToken(queryParser.OPEN_BRACE, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(queryParser.CLOSE_BRACE, 0); }
		public GrouperContext grouper() {
			return getRuleContext(GrouperContext.class,0);
		}
		public Composite_iteratorContext composite_iterator() {
			return getRuleContext(Composite_iteratorContext.class,0);
		}
		public If_then_elseContext if_then_else() {
			return getRuleContext(If_then_elseContext.class,0);
		}
		public AggregateContext aggregate() {
			return getRuleContext(AggregateContext.class,0);
		}
		public ArithmeticsContext arithmetics() {
			return getRuleContext(ArithmeticsContext.class,0);
		}
		public TerminalNode NUMERIC_LITERAL() { return getToken(queryParser.NUMERIC_LITERAL, 0); }
		public SlContext sl() {
			return getRuleContext(SlContext.class,0);
		}
		public GgplotContext ggplot() {
			return getRuleContext(GgplotContext.class,0);
		}
		public TerminalNode DECORATOR() { return getToken(queryParser.DECORATOR, 0); }
		public SortingContext sorting() {
			return getRuleContext(SortingContext.class,0);
		}
		public OperandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterOperand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitOperand(this);
		}
	}

	public final OperandContext operand() throws RecognitionException {
		OperandContext _localctx = new OperandContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_operand);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(142);
				_la = _input.LA(1);
				if (_la==OPEN_PARENTHESE) {
					{
					setState(141);
					sorting();
					}
				}

				setState(144);
				attribute();
				}
				break;
			case 2:
				{
				setState(146);
				_la = _input.LA(1);
				if (_la==OPEN_PARENTHESE) {
					{
					setState(145);
					sorting();
					}
				}

				setState(148);
				as_pair();
				}
				break;
			case 3:
				{
				setState(149);
				function();
				}
				break;
			case 4:
				{
				setState(150);
				sqlfunc();
				}
				break;
			case 5:
				{
				setState(151);
				match(OPEN_BRACE);
				setState(152);
				exp();
				setState(153);
				match(CLOSE_BRACE);
				}
				break;
			case 6:
				{
				setState(155);
				grouper();
				}
				break;
			case 7:
				{
				setState(156);
				composite_iterator();
				}
				break;
			case 8:
				{
				setState(157);
				if_then_else();
				}
				break;
			case 9:
				{
				setState(159);
				_la = _input.LA(1);
				if (_la==OPEN_PARENTHESE) {
					{
					setState(158);
					sorting();
					}
				}

				setState(161);
				aggregate();
				}
				break;
			case 10:
				{
				setState(162);
				arithmetics(0);
				}
				break;
			case 11:
				{
				setState(163);
				match(NUMERIC_LITERAL);
				}
				break;
			case 12:
				{
				setState(164);
				sl();
				}
				break;
			case 13:
				{
				setState(166);
				_la = _input.LA(1);
				if (_la==OPEN_PARENTHESE) {
					{
					setState(165);
					sorting();
					}
				}

				setState(168);
				ggplot();
				}
				break;
			}
			setState(172);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(171);
				match(DECORATOR);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributeContext extends ParserRuleContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Table_aliasContext table_alias() {
			return getRuleContext(Table_aliasContext.class,0);
		}
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitAttribute(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_attribute);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(174);
				table_alias();
				setState(175);
				match(DOT);
				}
				break;
			}
			setState(179);
			column_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class As_pairContext extends ParserRuleContext {
		public TerminalNode K_AS() { return getToken(queryParser.K_AS, 0); }
		public AttributeContext attribute() {
			return getRuleContext(AttributeContext.class,0);
		}
		public AggregateContext aggregate() {
			return getRuleContext(AggregateContext.class,0);
		}
		public SqlfuncContext sqlfunc() {
			return getRuleContext(SqlfuncContext.class,0);
		}
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public As_pairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_as_pair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterAs_pair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitAs_pair(this);
		}
	}

	public final As_pairContext as_pair() throws RecognitionException {
		As_pairContext _localctx = new As_pairContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_as_pair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(184);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(181);
				attribute();
				}
				break;
			case 2:
				{
				setState(182);
				aggregate();
				}
				break;
			case 3:
				{
				setState(183);
				sqlfunc();
				}
				break;
			}
			setState(186);
			match(K_AS);
			{
			setState(187);
			any_name();
			}
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GrouperContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACKET() { return getToken(queryParser.OPEN_BRACKET, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(queryParser.CLOSE_BRACKET, 0); }
		public TerminalNode C1() { return getToken(queryParser.C1, 0); }
		public TerminalNode C2() { return getToken(queryParser.C2, 0); }
		public TerminalNode C3() { return getToken(queryParser.C3, 0); }
		public TerminalNode C4() { return getToken(queryParser.C4, 0); }
		public TerminalNode CIRCLE() { return getToken(queryParser.CIRCLE, 0); }
		public GrouperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grouper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterGrouper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitGrouper(this);
		}
	}

	public final GrouperContext grouper() throws RecognitionException {
		GrouperContext _localctx = new GrouperContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_grouper);
		try {
			setState(206);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(189);
				match(OPEN_BRACKET);
				setState(190);
				exp();
				setState(191);
				match(CLOSE_BRACKET);
				setState(192);
				match(C1);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(194);
				match(OPEN_BRACKET);
				setState(195);
				exp();
				setState(196);
				match(CLOSE_BRACKET);
				setState(197);
				match(C2);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(199);
				match(OPEN_BRACKET);
				setState(200);
				exp();
				setState(201);
				match(CLOSE_BRACKET);
				setState(202);
				match(C3);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(204);
				match(C4);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(205);
				match(CIRCLE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Composite_iteratorContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACKET() { return getToken(queryParser.OPEN_BRACKET, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(queryParser.CLOSE_BRACKET, 0); }
		public TerminalNode C1() { return getToken(queryParser.C1, 0); }
		public List<TerminalNode> NUMERIC_LITERAL() { return getTokens(queryParser.NUMERIC_LITERAL); }
		public TerminalNode NUMERIC_LITERAL(int i) {
			return getToken(queryParser.NUMERIC_LITERAL, i);
		}
		public TerminalNode C2() { return getToken(queryParser.C2, 0); }
		public TerminalNode C3() { return getToken(queryParser.C3, 0); }
		public TerminalNode C4() { return getToken(queryParser.C4, 0); }
		public TerminalNode CIRCLE() { return getToken(queryParser.CIRCLE, 0); }
		public Composite_iteratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_composite_iterator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterComposite_iterator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitComposite_iterator(this);
		}
	}

	public final Composite_iteratorContext composite_iterator() throws RecognitionException {
		Composite_iteratorContext _localctx = new Composite_iteratorContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_composite_iterator);
		try {
			setState(272);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(208);
				match(OPEN_BRACKET);
				setState(209);
				exp();
				setState(210);
				match(CLOSE_BRACKET);
				setState(211);
				match(C1);
				setState(224);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(212);
					match(NUMERIC_LITERAL);
					setState(213);
					match(C2);
					setState(216);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						setState(214);
						match(NUMERIC_LITERAL);
						setState(215);
						match(C3);
						}
						break;
					}
					}
					break;
				case 2:
					{
					setState(218);
					match(NUMERIC_LITERAL);
					setState(219);
					match(C3);
					setState(222);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						setState(220);
						match(NUMERIC_LITERAL);
						setState(221);
						match(C2);
						}
						break;
					}
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(226);
				match(OPEN_BRACKET);
				setState(227);
				exp();
				setState(228);
				match(CLOSE_BRACKET);
				setState(229);
				match(C2);
				setState(242);
				switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
				case 1:
					{
					setState(230);
					match(NUMERIC_LITERAL);
					setState(231);
					match(C1);
					setState(234);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						setState(232);
						match(NUMERIC_LITERAL);
						setState(233);
						match(C3);
						}
						break;
					}
					}
					break;
				case 2:
					{
					setState(236);
					match(NUMERIC_LITERAL);
					setState(237);
					match(C3);
					setState(240);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						setState(238);
						match(NUMERIC_LITERAL);
						setState(239);
						match(C1);
						}
						break;
					}
					}
					break;
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(244);
				match(OPEN_BRACKET);
				setState(245);
				exp();
				setState(246);
				match(CLOSE_BRACKET);
				setState(247);
				match(C3);
				setState(260);
				switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
				case 1:
					{
					setState(248);
					match(NUMERIC_LITERAL);
					setState(249);
					match(C1);
					setState(252);
					switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
					case 1:
						{
						setState(250);
						match(NUMERIC_LITERAL);
						setState(251);
						match(C2);
						}
						break;
					}
					}
					break;
				case 2:
					{
					setState(254);
					match(NUMERIC_LITERAL);
					setState(255);
					match(C2);
					setState(258);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
					case 1:
						{
						setState(256);
						match(NUMERIC_LITERAL);
						setState(257);
						match(C1);
						}
						break;
					}
					}
					break;
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(262);
				match(OPEN_BRACKET);
				setState(263);
				exp();
				setState(264);
				match(CLOSE_BRACKET);
				setState(265);
				match(C4);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(267);
				match(OPEN_BRACKET);
				setState(268);
				exp();
				setState(269);
				match(CLOSE_BRACKET);
				setState(270);
				match(CIRCLE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpContext extends ParserRuleContext {
		public T_expContext t_exp() {
			return getRuleContext(T_expContext.class,0);
		}
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitExp(this);
		}
	}

	public final ExpContext exp() throws RecognitionException {
		ExpContext _localctx = new ExpContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_exp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			t_exp();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class T_expContext extends ParserRuleContext {
		public List<D_expContext> d_exp() {
			return getRuleContexts(D_expContext.class);
		}
		public D_expContext d_exp(int i) {
			return getRuleContext(D_expContext.class,i);
		}
		public List<TerminalNode> C4() { return getTokens(queryParser.C4); }
		public TerminalNode C4(int i) {
			return getToken(queryParser.C4, i);
		}
		public List<OperandContext> operand() {
			return getRuleContexts(OperandContext.class);
		}
		public OperandContext operand(int i) {
			return getRuleContext(OperandContext.class,i);
		}
		public T_expContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_t_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterT_exp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitT_exp(this);
		}
	}

	public final T_expContext t_exp() throws RecognitionException {
		T_expContext _localctx = new T_expContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_t_exp);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			d_exp();
			setState(284);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(277);
					match(C4);
					setState(280);
					switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
					case 1:
						{
						setState(278);
						d_exp();
						}
						break;
					case 2:
						{
						setState(279);
						operand();
						}
						break;
					}
					}
					} 
				}
				setState(286);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Concat_expContext extends ParserRuleContext {
		public List<OperandContext> operand() {
			return getRuleContexts(OperandContext.class);
		}
		public OperandContext operand(int i) {
			return getRuleContext(OperandContext.class,i);
		}
		public Concat_expContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_concat_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterConcat_exp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitConcat_exp(this);
		}
	}

	public final Concat_expContext concat_exp() throws RecognitionException {
		Concat_expContext _localctx = new Concat_expContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_concat_exp);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(287);
			operand();
			}
			setState(290); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(288);
					match(T__0);
					{
					setState(289);
					operand();
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(292); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class D_expContext extends ParserRuleContext {
		public List<V_expContext> v_exp() {
			return getRuleContexts(V_expContext.class);
		}
		public V_expContext v_exp(int i) {
			return getRuleContext(V_expContext.class,i);
		}
		public List<TerminalNode> C3() { return getTokens(queryParser.C3); }
		public TerminalNode C3(int i) {
			return getToken(queryParser.C3, i);
		}
		public List<OperandContext> operand() {
			return getRuleContexts(OperandContext.class);
		}
		public OperandContext operand(int i) {
			return getRuleContext(OperandContext.class,i);
		}
		public D_expContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_d_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterD_exp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitD_exp(this);
		}
	}

	public final D_expContext d_exp() throws RecognitionException {
		D_expContext _localctx = new D_expContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_d_exp);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(294);
			v_exp();
			setState(302);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(295);
					match(C3);
					setState(298);
					switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
					case 1:
						{
						setState(296);
						v_exp();
						}
						break;
					case 2:
						{
						setState(297);
						operand();
						}
						break;
					}
					}
					} 
				}
				setState(304);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class V_expContext extends ParserRuleContext {
		public List<H_expContext> h_exp() {
			return getRuleContexts(H_expContext.class);
		}
		public H_expContext h_exp(int i) {
			return getRuleContext(H_expContext.class,i);
		}
		public List<TerminalNode> C2() { return getTokens(queryParser.C2); }
		public TerminalNode C2(int i) {
			return getToken(queryParser.C2, i);
		}
		public List<OperandContext> operand() {
			return getRuleContexts(OperandContext.class);
		}
		public OperandContext operand(int i) {
			return getRuleContext(OperandContext.class,i);
		}
		public V_expContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_v_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterV_exp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitV_exp(this);
		}
	}

	public final V_expContext v_exp() throws RecognitionException {
		V_expContext _localctx = new V_expContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_v_exp);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(305);
			h_exp();
			setState(313);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(306);
					match(C2);
					setState(309);
					switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
					case 1:
						{
						setState(307);
						h_exp();
						}
						break;
					case 2:
						{
						setState(308);
						operand();
						}
						break;
					}
					}
					} 
				}
				setState(315);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class H_expContext extends ParserRuleContext {
		public List<OperandContext> operand() {
			return getRuleContexts(OperandContext.class);
		}
		public OperandContext operand(int i) {
			return getRuleContext(OperandContext.class,i);
		}
		public List<N_expContext> n_exp() {
			return getRuleContexts(N_expContext.class);
		}
		public N_expContext n_exp(int i) {
			return getRuleContext(N_expContext.class,i);
		}
		public List<TerminalNode> C1() { return getTokens(queryParser.C1); }
		public TerminalNode C1(int i) {
			return getToken(queryParser.C1, i);
		}
		public H_expContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_h_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterH_exp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitH_exp(this);
		}
	}

	public final H_expContext h_exp() throws RecognitionException {
		H_expContext _localctx = new H_expContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_h_exp);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(318);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				{
				setState(316);
				operand();
				}
				break;
			case 2:
				{
				setState(317);
				n_exp();
				}
				break;
			}
			setState(327);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(320);
					match(C1);
					setState(323);
					switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
					case 1:
						{
						setState(321);
						operand();
						}
						break;
					case 2:
						{
						setState(322);
						n_exp();
						}
						break;
					}
					}
					} 
				}
				setState(329);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class N_expContext extends ParserRuleContext {
		public List<OperandContext> operand() {
			return getRuleContexts(OperandContext.class);
		}
		public OperandContext operand(int i) {
			return getRuleContext(OperandContext.class,i);
		}
		public List<Concat_expContext> concat_exp() {
			return getRuleContexts(Concat_expContext.class);
		}
		public Concat_expContext concat_exp(int i) {
			return getRuleContext(Concat_expContext.class,i);
		}
		public List<TerminalNode> C0() { return getTokens(queryParser.C0); }
		public TerminalNode C0(int i) {
			return getToken(queryParser.C0, i);
		}
		public N_expContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_n_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterN_exp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitN_exp(this);
		}
	}

	public final N_expContext n_exp() throws RecognitionException {
		N_expContext _localctx = new N_expContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_n_exp);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(332);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				{
				setState(330);
				operand();
				}
				break;
			case 2:
				{
				setState(331);
				concat_exp();
				}
				break;
			}
			setState(341);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(334);
					match(C0);
					setState(337);
					switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
					case 1:
						{
						setState(335);
						operand();
						}
						break;
					case 2:
						{
						setState(336);
						concat_exp();
						}
						break;
					}
					}
					} 
				}
				setState(343);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SortingContext extends ParserRuleContext {
		public TerminalNode OPEN_PARENTHESE() { return getToken(queryParser.OPEN_PARENTHESE, 0); }
		public TerminalNode K_ASC() { return getToken(queryParser.K_ASC, 0); }
		public TerminalNode CLOSE_PARENTHESE() { return getToken(queryParser.CLOSE_PARENTHESE, 0); }
		public TerminalNode K_DESC() { return getToken(queryParser.K_DESC, 0); }
		public SortingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sorting; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterSorting(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitSorting(this);
		}
	}

	public final SortingContext sorting() throws RecognitionException {
		SortingContext _localctx = new SortingContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_sorting);
		try {
			setState(350);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(344);
				match(OPEN_PARENTHESE);
				setState(345);
				match(K_ASC);
				setState(346);
				match(CLOSE_PARENTHESE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(347);
				match(OPEN_PARENTHESE);
				setState(348);
				match(K_DESC);
				setState(349);
				match(CLOSE_PARENTHESE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionContext extends ParserRuleContext {
		public Function_nameContext function_name() {
			return getRuleContext(Function_nameContext.class,0);
		}
		public TerminalNode OPEN_PARENTHESE() { return getToken(queryParser.OPEN_PARENTHESE, 0); }
		public TerminalNode CLOSE_PARENTHESE() { return getToken(queryParser.CLOSE_PARENTHESE, 0); }
		public List<OperandContext> operand() {
			return getRuleContexts(OperandContext.class);
		}
		public OperandContext operand(int i) {
			return getRuleContext(OperandContext.class,i);
		}
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitFunction(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(353);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(352);
				match(T__1);
				}
			}

			setState(355);
			function_name();
			setState(356);
			match(OPEN_PARENTHESE);
			{
			setState(359);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				{
				setState(357);
				operand();
				}
				break;
			case 2:
				{
				setState(358);
				exp();
				}
				break;
			}
			setState(368);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==C1) {
				{
				{
				setState(361);
				match(C1);
				setState(364);
				switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
				case 1:
					{
					setState(362);
					operand();
					}
					break;
				case 2:
					{
					setState(363);
					exp();
					}
					break;
				}
				}
				}
				setState(370);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			setState(371);
			match(CLOSE_PARENTHESE);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SqlfuncContext extends ParserRuleContext {
		public Function_nameContext function_name() {
			return getRuleContext(Function_nameContext.class,0);
		}
		public TerminalNode OPEN_PARENTHESE() { return getToken(queryParser.OPEN_PARENTHESE, 0); }
		public TerminalNode CLOSE_PARENTHESE() { return getToken(queryParser.CLOSE_PARENTHESE, 0); }
		public List<OperandContext> operand() {
			return getRuleContexts(OperandContext.class);
		}
		public OperandContext operand(int i) {
			return getRuleContext(OperandContext.class,i);
		}
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public SqlfuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sqlfunc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterSqlfunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitSqlfunc(this);
		}
	}

	public final SqlfuncContext sqlfunc() throws RecognitionException {
		SqlfuncContext _localctx = new SqlfuncContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_sqlfunc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(373);
			match(T__2);
			setState(374);
			function_name();
			setState(375);
			match(OPEN_PARENTHESE);
			setState(392);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << K_ABORT) | (1L << K_ALL) | (1L << K_AND) | (1L << K_AS) | (1L << K_ASC) | (1L << K_BETWEEN) | (1L << K_BY) | (1L << K_CASE) | (1L << K_CAST) | (1L << K_COLLATE) | (1L << K_CROSS) | (1L << K_CURRENT_DATE) | (1L << K_CURRENT_TIME) | (1L << K_CURRENT_TIMESTAMP) | (1L << K_DESC) | (1L << K_DISTINCT) | (1L << K_ELSE) | (1L << K_END) | (1L << K_ESCAPE) | (1L << K_EXCEPT) | (1L << K_EXISTS) | (1L << K_FAIL) | (1L << K_FULL) | (1L << K_FROM) | (1L << K_GLOB) | (1L << K_GROUP) | (1L << K_HAVING) | (1L << K_IF) | (1L << K_IGNORE) | (1L << K_IN) | (1L << K_INDEXED) | (1L << K_INNER) | (1L << K_INTERSECT) | (1L << K_IS) | (1L << K_ISNULL) | (1L << K_JOIN) | (1L << K_LEFT) | (1L << K_LIKE) | (1L << K_LIMIT) | (1L << K_MATCH) | (1L << K_NATURAL))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (K_NO - 64)) | (1L << (K_NOT - 64)) | (1L << (K_NOTNULL - 64)) | (1L << (K_NULL - 64)) | (1L << (K_OFFSET - 64)) | (1L << (K_ON - 64)) | (1L << (K_OR - 64)) | (1L << (K_ORDER - 64)) | (1L << (K_OUTER - 64)) | (1L << (K_RAISE - 64)) | (1L << (K_RECURSIVE - 64)) | (1L << (K_REGEXP - 64)) | (1L << (K_RIGHT - 64)) | (1L << (K_ROLLBACK - 64)) | (1L << (K_SELECT - 64)) | (1L << (K_THEN - 64)) | (1L << (K_UNION - 64)) | (1L << (K_USING - 64)) | (1L << (K_VALUES - 64)) | (1L << (K_WHEN - 64)) | (1L << (K_WHERE - 64)) | (1L << (K_WITH - 64)) | (1L << (K_GENERATE - 64)) | (1L << (K_MAX - 64)) | (1L << (K_MIN - 64)) | (1L << (K_AVG - 64)) | (1L << (K_COUNT - 64)) | (1L << (K_SUM - 64)) | (1L << (K_GGPLOT - 64)) | (1L << (C4 - 64)) | (1L << (CIRCLE - 64)) | (1L << (OPEN_PARENTHESE - 64)) | (1L << (OPEN_BRACKET - 64)) | (1L << (OPEN_BRACE - 64)) | (1L << (NUMERIC_LITERAL - 64)) | (1L << (IDENTIFIER - 64)) | (1L << (STRING_LITERAL - 64)))) != 0)) {
				{
				{
				setState(378);
				switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
				case 1:
					{
					setState(376);
					operand();
					}
					break;
				case 2:
					{
					setState(377);
					exp();
					}
					break;
				}
				setState(387);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(380);
					match(C1);
					setState(383);
					switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
					case 1:
						{
						setState(381);
						operand();
						}
						break;
					case 2:
						{
						setState(382);
						exp();
						}
						break;
					}
					}
					}
					setState(389);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(394);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(395);
			match(CLOSE_PARENTHESE);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AggregateContext extends ParserRuleContext {
		public Ag_function_nameContext ag_function_name() {
			return getRuleContext(Ag_function_nameContext.class,0);
		}
		public TerminalNode OPEN_BRACKET() { return getToken(queryParser.OPEN_BRACKET, 0); }
		public AttributeContext attribute() {
			return getRuleContext(AttributeContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(queryParser.CLOSE_BRACKET, 0); }
		public AggregateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterAggregate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitAggregate(this);
		}
	}

	public final AggregateContext aggregate() throws RecognitionException {
		AggregateContext _localctx = new AggregateContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_aggregate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(397);
			ag_function_name();
			setState(398);
			match(OPEN_BRACKET);
			setState(399);
			attribute();
			setState(400);
			match(CLOSE_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GgplotContext extends ParserRuleContext {
		public Gg_function_nameContext gg_function_name() {
			return getRuleContext(Gg_function_nameContext.class,0);
		}
		public TerminalNode OPEN_BRACKET() { return getToken(queryParser.OPEN_BRACKET, 0); }
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(queryParser.CLOSE_BRACKET, 0); }
		public List<TerminalNode> C1() { return getTokens(queryParser.C1); }
		public TerminalNode C1(int i) {
			return getToken(queryParser.C1, i);
		}
		public GgplotContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ggplot; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterGgplot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitGgplot(this);
		}
	}

	public final GgplotContext ggplot() throws RecognitionException {
		GgplotContext _localctx = new GgplotContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_ggplot);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(402);
			gg_function_name();
			setState(403);
			match(OPEN_BRACKET);
			setState(404);
			attribute();
			setState(409);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==C1) {
				{
				{
				setState(405);
				match(C1);
				setState(406);
				attribute();
				}
				}
				setState(411);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(412);
			match(CLOSE_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class If_then_elseContext extends ParserRuleContext {
		public TerminalNode K_IF() { return getToken(queryParser.K_IF, 0); }
		public List<TerminalNode> OPEN_PARENTHESE() { return getTokens(queryParser.OPEN_PARENTHESE); }
		public TerminalNode OPEN_PARENTHESE(int i) {
			return getToken(queryParser.OPEN_PARENTHESE, i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<TerminalNode> CLOSE_PARENTHESE() { return getTokens(queryParser.CLOSE_PARENTHESE); }
		public TerminalNode CLOSE_PARENTHESE(int i) {
			return getToken(queryParser.CLOSE_PARENTHESE, i);
		}
		public TerminalNode K_THEN() { return getToken(queryParser.K_THEN, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode K_ELSE() { return getToken(queryParser.K_ELSE, 0); }
		public If_then_elseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_then_else; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterIf_then_else(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitIf_then_else(this);
		}
	}

	public final If_then_elseContext if_then_else() throws RecognitionException {
		If_then_elseContext _localctx = new If_then_elseContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_if_then_else);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(435);
			switch (_input.LA(1)) {
			case K_IF:
				{
				{
				setState(414);
				match(K_IF);
				setState(415);
				match(OPEN_PARENTHESE);
				setState(416);
				expr(0);
				setState(417);
				match(CLOSE_PARENTHESE);
				setState(418);
				match(K_THEN);
				setState(419);
				match(OPEN_PARENTHESE);
				setState(420);
				exp();
				setState(421);
				match(CLOSE_PARENTHESE);
				setState(422);
				match(K_ELSE);
				setState(423);
				match(OPEN_PARENTHESE);
				setState(424);
				exp();
				setState(425);
				match(CLOSE_PARENTHESE);
				}
				}
				break;
			case OPEN_PARENTHESE:
				{
				{
				setState(427);
				match(OPEN_PARENTHESE);
				setState(428);
				expr(0);
				setState(429);
				match(CLOSE_PARENTHESE);
				setState(430);
				match(C0);
				setState(431);
				exp();
				setState(432);
				match(T__3);
				setState(433);
				exp();
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArithmeticsContext extends ParserRuleContext {
		public TerminalNode OPEN_PARENTHESE() { return getToken(queryParser.OPEN_PARENTHESE, 0); }
		public List<ArithmeticsContext> arithmetics() {
			return getRuleContexts(ArithmeticsContext.class);
		}
		public ArithmeticsContext arithmetics(int i) {
			return getRuleContext(ArithmeticsContext.class,i);
		}
		public TerminalNode CLOSE_PARENTHESE() { return getToken(queryParser.CLOSE_PARENTHESE, 0); }
		public ArithContext arith() {
			return getRuleContext(ArithContext.class,0);
		}
		public ArithmeticsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmetics; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterArithmetics(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitArithmetics(this);
		}
	}

	public final ArithmeticsContext arithmetics() throws RecognitionException {
		return arithmetics(0);
	}

	private ArithmeticsContext arithmetics(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ArithmeticsContext _localctx = new ArithmeticsContext(_ctx, _parentState);
		ArithmeticsContext _prevctx = _localctx;
		int _startState = 42;
		enterRecursionRule(_localctx, 42, RULE_arithmetics, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(445);
			switch (_input.LA(1)) {
			case OPEN_PARENTHESE:
				{
				setState(438);
				match(OPEN_PARENTHESE);
				setState(439);
				arithmetics(0);
				setState(440);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7))) != 0) || _la==C3) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(441);
				arithmetics(0);
				setState(442);
				match(CLOSE_PARENTHESE);
				}
				break;
			case K_ABORT:
			case K_ALL:
			case K_AND:
			case K_AS:
			case K_ASC:
			case K_BETWEEN:
			case K_BY:
			case K_CASE:
			case K_CAST:
			case K_COLLATE:
			case K_CROSS:
			case K_CURRENT_DATE:
			case K_CURRENT_TIME:
			case K_CURRENT_TIMESTAMP:
			case K_DESC:
			case K_DISTINCT:
			case K_ELSE:
			case K_END:
			case K_ESCAPE:
			case K_EXCEPT:
			case K_EXISTS:
			case K_FAIL:
			case K_FULL:
			case K_FROM:
			case K_GLOB:
			case K_GROUP:
			case K_HAVING:
			case K_IF:
			case K_IGNORE:
			case K_IN:
			case K_INDEXED:
			case K_INNER:
			case K_INTERSECT:
			case K_IS:
			case K_ISNULL:
			case K_JOIN:
			case K_LEFT:
			case K_LIKE:
			case K_LIMIT:
			case K_MATCH:
			case K_NATURAL:
			case K_NO:
			case K_NOT:
			case K_NOTNULL:
			case K_NULL:
			case K_OFFSET:
			case K_ON:
			case K_OR:
			case K_ORDER:
			case K_OUTER:
			case K_RAISE:
			case K_RECURSIVE:
			case K_REGEXP:
			case K_RIGHT:
			case K_ROLLBACK:
			case K_SELECT:
			case K_THEN:
			case K_UNION:
			case K_USING:
			case K_VALUES:
			case K_WHEN:
			case K_WHERE:
			case K_WITH:
			case K_GENERATE:
			case K_MAX:
			case K_MIN:
			case K_AVG:
			case K_COUNT:
			case K_SUM:
			case K_GGPLOT:
			case NUMERIC_LITERAL:
			case IDENTIFIER:
			case STRING_LITERAL:
				{
				setState(444);
				arith();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(452);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ArithmeticsContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_arithmetics);
					setState(447);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(448);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7))) != 0) || _la==C3) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					setState(449);
					arithmetics(3);
					}
					} 
				}
				setState(454);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ArithContext extends ParserRuleContext {
		public AttributeContext attribute() {
			return getRuleContext(AttributeContext.class,0);
		}
		public TerminalNode NUMERIC_LITERAL() { return getToken(queryParser.NUMERIC_LITERAL, 0); }
		public ArithContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arith; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterArith(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitArith(this);
		}
	}

	public final ArithContext arith() throws RecognitionException {
		ArithContext _localctx = new ArithContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_arith);
		try {
			setState(457);
			switch (_input.LA(1)) {
			case K_ABORT:
			case K_ALL:
			case K_AND:
			case K_AS:
			case K_ASC:
			case K_BETWEEN:
			case K_BY:
			case K_CASE:
			case K_CAST:
			case K_COLLATE:
			case K_CROSS:
			case K_CURRENT_DATE:
			case K_CURRENT_TIME:
			case K_CURRENT_TIMESTAMP:
			case K_DESC:
			case K_DISTINCT:
			case K_ELSE:
			case K_END:
			case K_ESCAPE:
			case K_EXCEPT:
			case K_EXISTS:
			case K_FAIL:
			case K_FULL:
			case K_FROM:
			case K_GLOB:
			case K_GROUP:
			case K_HAVING:
			case K_IF:
			case K_IGNORE:
			case K_IN:
			case K_INDEXED:
			case K_INNER:
			case K_INTERSECT:
			case K_IS:
			case K_ISNULL:
			case K_JOIN:
			case K_LEFT:
			case K_LIKE:
			case K_LIMIT:
			case K_MATCH:
			case K_NATURAL:
			case K_NO:
			case K_NOT:
			case K_NOTNULL:
			case K_NULL:
			case K_OFFSET:
			case K_ON:
			case K_OR:
			case K_ORDER:
			case K_OUTER:
			case K_RAISE:
			case K_RECURSIVE:
			case K_REGEXP:
			case K_RIGHT:
			case K_ROLLBACK:
			case K_SELECT:
			case K_THEN:
			case K_UNION:
			case K_USING:
			case K_VALUES:
			case K_WHEN:
			case K_WHERE:
			case K_WITH:
			case K_GENERATE:
			case K_MAX:
			case K_MIN:
			case K_AVG:
			case K_COUNT:
			case K_SUM:
			case K_GGPLOT:
			case IDENTIFIER:
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(455);
				attribute();
				}
				break;
			case NUMERIC_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(456);
				match(NUMERIC_LITERAL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class From_whereContext extends ParserRuleContext {
		public Sql_stmt_listContext sql_stmt_list() {
			return getRuleContext(Sql_stmt_listContext.class,0);
		}
		public ErrorContext error() {
			return getRuleContext(ErrorContext.class,0);
		}
		public From_whereContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_from_where; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterFrom_where(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitFrom_where(this);
		}
	}

	public final From_whereContext from_where() throws RecognitionException {
		From_whereContext _localctx = new From_whereContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_from_where);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(461);
			switch (_input.LA(1)) {
			case K_FROM:
			case K_SELECT:
			case K_WITH:
				{
				setState(459);
				sql_stmt_list();
				}
				break;
			case UNEXPECTED_CHAR:
				{
				setState(460);
				error();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ErrorContext extends ParserRuleContext {
		public Token UNEXPECTED_CHAR;
		public TerminalNode UNEXPECTED_CHAR() { return getToken(queryParser.UNEXPECTED_CHAR, 0); }
		public ErrorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_error; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterError(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitError(this);
		}
	}

	public final ErrorContext error() throws RecognitionException {
		ErrorContext _localctx = new ErrorContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_error);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(463);
			((ErrorContext)_localctx).UNEXPECTED_CHAR = match(UNEXPECTED_CHAR);


			  throw new RuntimeException("UNEXPECTED_CHAR=" + (((ErrorContext)_localctx).UNEXPECTED_CHAR!=null?((ErrorContext)_localctx).UNEXPECTED_CHAR.getText():null));
			  
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Sql_stmt_listContext extends ParserRuleContext {
		public List<Sql_stmtContext> sql_stmt() {
			return getRuleContexts(Sql_stmtContext.class);
		}
		public Sql_stmtContext sql_stmt(int i) {
			return getRuleContext(Sql_stmtContext.class,i);
		}
		public Sql_stmt_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sql_stmt_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterSql_stmt_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitSql_stmt_list(this);
		}
	}

	public final Sql_stmt_listContext sql_stmt_list() throws RecognitionException {
		Sql_stmt_listContext _localctx = new Sql_stmt_listContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_sql_stmt_list);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(466);
			sql_stmt();
			setState(475);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(468); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(467);
						match(SEMICOLON);
						}
						}
						setState(470); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==SEMICOLON );
					setState(472);
					sql_stmt();
					}
					} 
				}
				setState(477);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
			}
			setState(481);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMICOLON) {
				{
				{
				setState(478);
				match(SEMICOLON);
				}
				}
				setState(483);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Sql_stmtContext extends ParserRuleContext {
		public Factored_select_stmtContext factored_select_stmt() {
			return getRuleContext(Factored_select_stmtContext.class,0);
		}
		public Select_stmtContext select_stmt() {
			return getRuleContext(Select_stmtContext.class,0);
		}
		public Sql_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sql_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterSql_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitSql_stmt(this);
		}
	}

	public final Sql_stmtContext sql_stmt() throws RecognitionException {
		Sql_stmtContext _localctx = new Sql_stmtContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_sql_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(486);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				{
				setState(484);
				factored_select_stmt();
				}
				break;
			case 2:
				{
				setState(485);
				select_stmt();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Factored_select_stmtContext extends ParserRuleContext {
		public List<Select_coreContext> select_core() {
			return getRuleContexts(Select_coreContext.class);
		}
		public Select_coreContext select_core(int i) {
			return getRuleContext(Select_coreContext.class,i);
		}
		public TerminalNode K_WITH() { return getToken(queryParser.K_WITH, 0); }
		public List<Common_table_expressionContext> common_table_expression() {
			return getRuleContexts(Common_table_expressionContext.class);
		}
		public Common_table_expressionContext common_table_expression(int i) {
			return getRuleContext(Common_table_expressionContext.class,i);
		}
		public List<Compound_operatorContext> compound_operator() {
			return getRuleContexts(Compound_operatorContext.class);
		}
		public Compound_operatorContext compound_operator(int i) {
			return getRuleContext(Compound_operatorContext.class,i);
		}
		public TerminalNode K_ORDER() { return getToken(queryParser.K_ORDER, 0); }
		public TerminalNode K_BY() { return getToken(queryParser.K_BY, 0); }
		public List<Ordering_termContext> ordering_term() {
			return getRuleContexts(Ordering_termContext.class);
		}
		public Ordering_termContext ordering_term(int i) {
			return getRuleContext(Ordering_termContext.class,i);
		}
		public TerminalNode K_LIMIT() { return getToken(queryParser.K_LIMIT, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode K_RECURSIVE() { return getToken(queryParser.K_RECURSIVE, 0); }
		public TerminalNode K_OFFSET() { return getToken(queryParser.K_OFFSET, 0); }
		public Factored_select_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factored_select_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterFactored_select_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitFactored_select_stmt(this);
		}
	}

	public final Factored_select_stmtContext factored_select_stmt() throws RecognitionException {
		Factored_select_stmtContext _localctx = new Factored_select_stmtContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_factored_select_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(500);
			_la = _input.LA(1);
			if (_la==K_WITH) {
				{
				setState(488);
				match(K_WITH);
				setState(490);
				switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
				case 1:
					{
					setState(489);
					match(K_RECURSIVE);
					}
					break;
				}
				setState(492);
				common_table_expression();
				setState(497);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(493);
					match(C1);
					setState(494);
					common_table_expression();
					}
					}
					setState(499);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(502);
			select_core();
			setState(508);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 42)) & ~0x3f) == 0 && ((1L << (_la - 42)) & ((1L << (K_EXCEPT - 42)) | (1L << (K_INTERSECT - 42)) | (1L << (K_UNION - 42)))) != 0)) {
				{
				{
				setState(503);
				compound_operator();
				setState(504);
				select_core();
				}
				}
				setState(510);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(521);
			_la = _input.LA(1);
			if (_la==K_ORDER) {
				{
				setState(511);
				match(K_ORDER);
				setState(512);
				match(K_BY);
				setState(513);
				ordering_term();
				setState(518);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(514);
					match(C1);
					setState(515);
					ordering_term();
					}
					}
					setState(520);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(529);
			_la = _input.LA(1);
			if (_la==K_LIMIT) {
				{
				setState(523);
				match(K_LIMIT);
				setState(524);
				expr(0);
				setState(527);
				_la = _input.LA(1);
				if (_la==K_OFFSET || _la==C1) {
					{
					setState(525);
					_la = _input.LA(1);
					if ( !(_la==K_OFFSET || _la==C1) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					setState(526);
					expr(0);
					}
				}

				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Select_coreContext extends ParserRuleContext {
		public TerminalNode K_FROM() { return getToken(queryParser.K_FROM, 0); }
		public TerminalNode K_SELECT() { return getToken(queryParser.K_SELECT, 0); }
		public List<Result_columnContext> result_column() {
			return getRuleContexts(Result_columnContext.class);
		}
		public Result_columnContext result_column(int i) {
			return getRuleContext(Result_columnContext.class,i);
		}
		public Where_clauseContext where_clause() {
			return getRuleContext(Where_clauseContext.class,0);
		}
		public List<Table_or_subqueryContext> table_or_subquery() {
			return getRuleContexts(Table_or_subqueryContext.class);
		}
		public Table_or_subqueryContext table_or_subquery(int i) {
			return getRuleContext(Table_or_subqueryContext.class,i);
		}
		public Join_clauseContext join_clause() {
			return getRuleContext(Join_clauseContext.class,0);
		}
		public TerminalNode K_DISTINCT() { return getToken(queryParser.K_DISTINCT, 0); }
		public TerminalNode K_ALL() { return getToken(queryParser.K_ALL, 0); }
		public Select_coreContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_core; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterSelect_core(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitSelect_core(this);
		}
	}

	public final Select_coreContext select_core() throws RecognitionException {
		Select_coreContext _localctx = new Select_coreContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_select_core);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(543);
			_la = _input.LA(1);
			if (_la==K_SELECT) {
				{
				setState(531);
				match(K_SELECT);
				setState(533);
				switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
				case 1:
					{
					setState(532);
					_la = _input.LA(1);
					if ( !(_la==K_ALL || _la==K_DISTINCT) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					break;
				}
				setState(535);
				result_column();
				setState(540);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(536);
					match(C1);
					setState(537);
					result_column();
					}
					}
					setState(542);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			{
			setState(545);
			match(K_FROM);
			setState(555);
			switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
			case 1:
				{
				setState(546);
				table_or_subquery();
				setState(551);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(547);
					match(C1);
					setState(548);
					table_or_subquery();
					}
					}
					setState(553);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				{
				setState(554);
				join_clause();
				}
				break;
			}
			}
			setState(558);
			_la = _input.LA(1);
			if (((((_la - 48)) & ~0x3f) == 0 && ((1L << (_la - 48)) & ((1L << (K_GROUP - 48)) | (1L << (K_VALUES - 48)) | (1L << (K_WHERE - 48)))) != 0)) {
				{
				setState(557);
				where_clause();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Where_clauseContext extends ParserRuleContext {
		public TerminalNode K_VALUES() { return getToken(queryParser.K_VALUES, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode K_WHERE() { return getToken(queryParser.K_WHERE, 0); }
		public TerminalNode K_GROUP() { return getToken(queryParser.K_GROUP, 0); }
		public TerminalNode K_BY() { return getToken(queryParser.K_BY, 0); }
		public TerminalNode K_HAVING() { return getToken(queryParser.K_HAVING, 0); }
		public Where_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_where_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterWhere_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitWhere_clause(this);
		}
	}

	public final Where_clauseContext where_clause() throws RecognitionException {
		Where_clauseContext _localctx = new Where_clauseContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_where_clause);
		int _la;
		try {
			setState(657);
			switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(607);
				switch (_input.LA(1)) {
				case K_WHERE:
					{
					{
					setState(560);
					match(K_WHERE);
					setState(561);
					expr(0);
					}
					setState(577);
					_la = _input.LA(1);
					if (_la==K_GROUP) {
						{
						setState(563);
						match(K_GROUP);
						setState(564);
						match(K_BY);
						setState(565);
						expr(0);
						setState(570);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==C1) {
							{
							{
							setState(566);
							match(C1);
							setState(567);
							expr(0);
							}
							}
							setState(572);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(575);
						_la = _input.LA(1);
						if (_la==K_HAVING) {
							{
							setState(573);
							match(K_HAVING);
							setState(574);
							expr(0);
							}
						}

						}
					}

					}
					break;
				case K_VALUES:
					{
					setState(579);
					match(K_VALUES);
					setState(580);
					match(OPEN_PARENTHESE);
					setState(581);
					expr(0);
					setState(586);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==C1) {
						{
						{
						setState(582);
						match(C1);
						setState(583);
						expr(0);
						}
						}
						setState(588);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(589);
					match(CLOSE_PARENTHESE);
					setState(604);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==C1) {
						{
						{
						setState(590);
						match(C1);
						setState(591);
						match(OPEN_PARENTHESE);
						setState(592);
						expr(0);
						setState(597);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==C1) {
							{
							{
							setState(593);
							match(C1);
							setState(594);
							expr(0);
							}
							}
							setState(599);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(600);
						match(CLOSE_PARENTHESE);
						}
						}
						setState(606);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(655);
				switch (_input.LA(1)) {
				case K_GROUP:
				case K_WHERE:
					{
					setState(611);
					_la = _input.LA(1);
					if (_la==K_WHERE) {
						{
						setState(609);
						match(K_WHERE);
						setState(610);
						expr(0);
						}
					}

					{
					setState(613);
					match(K_GROUP);
					setState(614);
					match(K_BY);
					setState(615);
					expr(0);
					setState(620);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==C1) {
						{
						{
						setState(616);
						match(C1);
						setState(617);
						expr(0);
						}
						}
						setState(622);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(625);
					_la = _input.LA(1);
					if (_la==K_HAVING) {
						{
						setState(623);
						match(K_HAVING);
						setState(624);
						expr(0);
						}
					}

					}
					}
					break;
				case K_VALUES:
					{
					setState(627);
					match(K_VALUES);
					setState(628);
					match(OPEN_PARENTHESE);
					setState(629);
					expr(0);
					setState(634);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==C1) {
						{
						{
						setState(630);
						match(C1);
						setState(631);
						expr(0);
						}
						}
						setState(636);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(637);
					match(CLOSE_PARENTHESE);
					setState(652);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==C1) {
						{
						{
						setState(638);
						match(C1);
						setState(639);
						match(OPEN_PARENTHESE);
						setState(640);
						expr(0);
						setState(645);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==C1) {
							{
							{
							setState(641);
							match(C1);
							setState(642);
							expr(0);
							}
							}
							setState(647);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(648);
						match(CLOSE_PARENTHESE);
						}
						}
						setState(654);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Result_columnContext extends ParserRuleContext {
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Column_aliasContext column_alias() {
			return getRuleContext(Column_aliasContext.class,0);
		}
		public TerminalNode K_AS() { return getToken(queryParser.K_AS, 0); }
		public Result_columnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_result_column; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterResult_column(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitResult_column(this);
		}
	}

	public final Result_columnContext result_column() throws RecognitionException {
		Result_columnContext _localctx = new Result_columnContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_result_column);
		int _la;
		try {
			setState(671);
			switch ( getInterpreter().adaptivePredict(_input,85,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(659);
				match(T__4);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(660);
				table_name();
				setState(661);
				match(DOT);
				setState(662);
				match(T__4);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(664);
				expr(0);
				setState(669);
				_la = _input.LA(1);
				if (_la==K_AS || _la==IDENTIFIER || _la==STRING_LITERAL) {
					{
					setState(666);
					_la = _input.LA(1);
					if (_la==K_AS) {
						{
						setState(665);
						match(K_AS);
						}
					}

					setState(668);
					column_alias();
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_or_subqueryContext extends ParserRuleContext {
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public Database_nameContext database_name() {
			return getRuleContext(Database_nameContext.class,0);
		}
		public Table_aliasContext table_alias() {
			return getRuleContext(Table_aliasContext.class,0);
		}
		public TerminalNode K_INDEXED() { return getToken(queryParser.K_INDEXED, 0); }
		public TerminalNode K_BY() { return getToken(queryParser.K_BY, 0); }
		public Index_nameContext index_name() {
			return getRuleContext(Index_nameContext.class,0);
		}
		public TerminalNode K_NOT() { return getToken(queryParser.K_NOT, 0); }
		public TerminalNode K_AS() { return getToken(queryParser.K_AS, 0); }
		public List<Table_or_subqueryContext> table_or_subquery() {
			return getRuleContexts(Table_or_subqueryContext.class);
		}
		public Table_or_subqueryContext table_or_subquery(int i) {
			return getRuleContext(Table_or_subqueryContext.class,i);
		}
		public Join_clauseContext join_clause() {
			return getRuleContext(Join_clauseContext.class,0);
		}
		public Select_stmtContext select_stmt() {
			return getRuleContext(Select_stmtContext.class,0);
		}
		public Table_or_subqueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_or_subquery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterTable_or_subquery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitTable_or_subquery(this);
		}
	}

	public final Table_or_subqueryContext table_or_subquery() throws RecognitionException {
		Table_or_subqueryContext _localctx = new Table_or_subqueryContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_table_or_subquery);
		int _la;
		try {
			setState(720);
			switch ( getInterpreter().adaptivePredict(_input,96,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(676);
				switch ( getInterpreter().adaptivePredict(_input,86,_ctx) ) {
				case 1:
					{
					setState(673);
					database_name();
					setState(674);
					match(DOT);
					}
					break;
				}
				setState(678);
				table_name();
				setState(683);
				switch ( getInterpreter().adaptivePredict(_input,88,_ctx) ) {
				case 1:
					{
					setState(680);
					switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
					case 1:
						{
						setState(679);
						match(K_AS);
						}
						break;
					}
					setState(682);
					table_alias();
					}
					break;
				}
				setState(690);
				switch (_input.LA(1)) {
				case K_INDEXED:
					{
					setState(685);
					match(K_INDEXED);
					setState(686);
					match(K_BY);
					setState(687);
					index_name();
					}
					break;
				case K_NOT:
					{
					setState(688);
					match(K_NOT);
					setState(689);
					match(K_INDEXED);
					}
					break;
				case EOF:
				case K_CROSS:
				case K_EXCEPT:
				case K_FULL:
				case K_GROUP:
				case K_INNER:
				case K_INTERSECT:
				case K_JOIN:
				case K_LEFT:
				case K_LIMIT:
				case K_NATURAL:
				case K_ON:
				case K_ORDER:
				case K_RIGHT:
				case K_UNION:
				case K_USING:
				case K_VALUES:
				case K_WHERE:
				case C1:
				case CLOSE_PARENTHESE:
				case SEMICOLON:
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(692);
				match(OPEN_PARENTHESE);
				setState(702);
				switch ( getInterpreter().adaptivePredict(_input,91,_ctx) ) {
				case 1:
					{
					setState(693);
					table_or_subquery();
					setState(698);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==C1) {
						{
						{
						setState(694);
						match(C1);
						setState(695);
						table_or_subquery();
						}
						}
						setState(700);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				case 2:
					{
					setState(701);
					join_clause();
					}
					break;
				}
				setState(704);
				match(CLOSE_PARENTHESE);
				setState(709);
				switch ( getInterpreter().adaptivePredict(_input,93,_ctx) ) {
				case 1:
					{
					setState(706);
					switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
					case 1:
						{
						setState(705);
						match(K_AS);
						}
						break;
					}
					setState(708);
					table_alias();
					}
					break;
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(711);
				match(OPEN_PARENTHESE);
				setState(712);
				select_stmt();
				setState(713);
				match(CLOSE_PARENTHESE);
				setState(718);
				switch ( getInterpreter().adaptivePredict(_input,95,_ctx) ) {
				case 1:
					{
					setState(715);
					switch ( getInterpreter().adaptivePredict(_input,94,_ctx) ) {
					case 1:
						{
						setState(714);
						match(K_AS);
						}
						break;
					}
					setState(717);
					table_alias();
					}
					break;
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeywordContext extends ParserRuleContext {
		public TerminalNode K_ABORT() { return getToken(queryParser.K_ABORT, 0); }
		public TerminalNode K_ALL() { return getToken(queryParser.K_ALL, 0); }
		public TerminalNode K_AND() { return getToken(queryParser.K_AND, 0); }
		public TerminalNode K_AS() { return getToken(queryParser.K_AS, 0); }
		public TerminalNode K_ASC() { return getToken(queryParser.K_ASC, 0); }
		public TerminalNode K_BETWEEN() { return getToken(queryParser.K_BETWEEN, 0); }
		public TerminalNode K_BY() { return getToken(queryParser.K_BY, 0); }
		public TerminalNode K_CASE() { return getToken(queryParser.K_CASE, 0); }
		public TerminalNode K_CAST() { return getToken(queryParser.K_CAST, 0); }
		public TerminalNode K_COLLATE() { return getToken(queryParser.K_COLLATE, 0); }
		public TerminalNode K_CROSS() { return getToken(queryParser.K_CROSS, 0); }
		public TerminalNode K_CURRENT_DATE() { return getToken(queryParser.K_CURRENT_DATE, 0); }
		public TerminalNode K_CURRENT_TIME() { return getToken(queryParser.K_CURRENT_TIME, 0); }
		public TerminalNode K_CURRENT_TIMESTAMP() { return getToken(queryParser.K_CURRENT_TIMESTAMP, 0); }
		public TerminalNode K_DESC() { return getToken(queryParser.K_DESC, 0); }
		public TerminalNode K_DISTINCT() { return getToken(queryParser.K_DISTINCT, 0); }
		public TerminalNode K_ELSE() { return getToken(queryParser.K_ELSE, 0); }
		public TerminalNode K_END() { return getToken(queryParser.K_END, 0); }
		public TerminalNode K_ESCAPE() { return getToken(queryParser.K_ESCAPE, 0); }
		public TerminalNode K_EXCEPT() { return getToken(queryParser.K_EXCEPT, 0); }
		public TerminalNode K_EXISTS() { return getToken(queryParser.K_EXISTS, 0); }
		public TerminalNode K_FAIL() { return getToken(queryParser.K_FAIL, 0); }
		public TerminalNode K_FROM() { return getToken(queryParser.K_FROM, 0); }
		public TerminalNode K_FULL() { return getToken(queryParser.K_FULL, 0); }
		public TerminalNode K_GLOB() { return getToken(queryParser.K_GLOB, 0); }
		public TerminalNode K_GROUP() { return getToken(queryParser.K_GROUP, 0); }
		public TerminalNode K_HAVING() { return getToken(queryParser.K_HAVING, 0); }
		public TerminalNode K_IF() { return getToken(queryParser.K_IF, 0); }
		public TerminalNode K_IGNORE() { return getToken(queryParser.K_IGNORE, 0); }
		public TerminalNode K_IN() { return getToken(queryParser.K_IN, 0); }
		public TerminalNode K_INDEXED() { return getToken(queryParser.K_INDEXED, 0); }
		public TerminalNode K_INNER() { return getToken(queryParser.K_INNER, 0); }
		public TerminalNode K_INTERSECT() { return getToken(queryParser.K_INTERSECT, 0); }
		public TerminalNode K_IS() { return getToken(queryParser.K_IS, 0); }
		public TerminalNode K_ISNULL() { return getToken(queryParser.K_ISNULL, 0); }
		public TerminalNode K_JOIN() { return getToken(queryParser.K_JOIN, 0); }
		public TerminalNode K_LEFT() { return getToken(queryParser.K_LEFT, 0); }
		public TerminalNode K_LIKE() { return getToken(queryParser.K_LIKE, 0); }
		public TerminalNode K_LIMIT() { return getToken(queryParser.K_LIMIT, 0); }
		public TerminalNode K_MATCH() { return getToken(queryParser.K_MATCH, 0); }
		public TerminalNode K_NATURAL() { return getToken(queryParser.K_NATURAL, 0); }
		public TerminalNode K_NO() { return getToken(queryParser.K_NO, 0); }
		public TerminalNode K_NOT() { return getToken(queryParser.K_NOT, 0); }
		public TerminalNode K_NOTNULL() { return getToken(queryParser.K_NOTNULL, 0); }
		public TerminalNode K_NULL() { return getToken(queryParser.K_NULL, 0); }
		public TerminalNode K_OFFSET() { return getToken(queryParser.K_OFFSET, 0); }
		public TerminalNode K_ON() { return getToken(queryParser.K_ON, 0); }
		public TerminalNode K_OR() { return getToken(queryParser.K_OR, 0); }
		public TerminalNode K_ORDER() { return getToken(queryParser.K_ORDER, 0); }
		public TerminalNode K_OUTER() { return getToken(queryParser.K_OUTER, 0); }
		public TerminalNode K_RAISE() { return getToken(queryParser.K_RAISE, 0); }
		public TerminalNode K_RECURSIVE() { return getToken(queryParser.K_RECURSIVE, 0); }
		public TerminalNode K_REGEXP() { return getToken(queryParser.K_REGEXP, 0); }
		public TerminalNode K_RIGHT() { return getToken(queryParser.K_RIGHT, 0); }
		public TerminalNode K_ROLLBACK() { return getToken(queryParser.K_ROLLBACK, 0); }
		public TerminalNode K_SELECT() { return getToken(queryParser.K_SELECT, 0); }
		public TerminalNode K_THEN() { return getToken(queryParser.K_THEN, 0); }
		public TerminalNode K_UNION() { return getToken(queryParser.K_UNION, 0); }
		public TerminalNode K_USING() { return getToken(queryParser.K_USING, 0); }
		public TerminalNode K_VALUES() { return getToken(queryParser.K_VALUES, 0); }
		public TerminalNode K_WHEN() { return getToken(queryParser.K_WHEN, 0); }
		public TerminalNode K_WHERE() { return getToken(queryParser.K_WHERE, 0); }
		public TerminalNode K_WITH() { return getToken(queryParser.K_WITH, 0); }
		public TerminalNode K_GENERATE() { return getToken(queryParser.K_GENERATE, 0); }
		public TerminalNode K_MAX() { return getToken(queryParser.K_MAX, 0); }
		public TerminalNode K_MIN() { return getToken(queryParser.K_MIN, 0); }
		public TerminalNode K_AVG() { return getToken(queryParser.K_AVG, 0); }
		public TerminalNode K_SUM() { return getToken(queryParser.K_SUM, 0); }
		public TerminalNode K_COUNT() { return getToken(queryParser.K_COUNT, 0); }
		public TerminalNode K_GGPLOT() { return getToken(queryParser.K_GGPLOT, 0); }
		public KeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterKeyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitKeyword(this);
		}
	}

	public final KeywordContext keyword() throws RecognitionException {
		KeywordContext _localctx = new KeywordContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_keyword);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(722);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_ABORT) | (1L << K_ALL) | (1L << K_AND) | (1L << K_AS) | (1L << K_ASC) | (1L << K_BETWEEN) | (1L << K_BY) | (1L << K_CASE) | (1L << K_CAST) | (1L << K_COLLATE) | (1L << K_CROSS) | (1L << K_CURRENT_DATE) | (1L << K_CURRENT_TIME) | (1L << K_CURRENT_TIMESTAMP) | (1L << K_DESC) | (1L << K_DISTINCT) | (1L << K_ELSE) | (1L << K_END) | (1L << K_ESCAPE) | (1L << K_EXCEPT) | (1L << K_EXISTS) | (1L << K_FAIL) | (1L << K_FULL) | (1L << K_FROM) | (1L << K_GLOB) | (1L << K_GROUP) | (1L << K_HAVING) | (1L << K_IF) | (1L << K_IGNORE) | (1L << K_IN) | (1L << K_INDEXED) | (1L << K_INNER) | (1L << K_INTERSECT) | (1L << K_IS) | (1L << K_ISNULL) | (1L << K_JOIN) | (1L << K_LEFT) | (1L << K_LIKE) | (1L << K_LIMIT) | (1L << K_MATCH) | (1L << K_NATURAL))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (K_NO - 64)) | (1L << (K_NOT - 64)) | (1L << (K_NOTNULL - 64)) | (1L << (K_NULL - 64)) | (1L << (K_OFFSET - 64)) | (1L << (K_ON - 64)) | (1L << (K_OR - 64)) | (1L << (K_ORDER - 64)) | (1L << (K_OUTER - 64)) | (1L << (K_RAISE - 64)) | (1L << (K_RECURSIVE - 64)) | (1L << (K_REGEXP - 64)) | (1L << (K_RIGHT - 64)) | (1L << (K_ROLLBACK - 64)) | (1L << (K_SELECT - 64)) | (1L << (K_THEN - 64)) | (1L << (K_UNION - 64)) | (1L << (K_USING - 64)) | (1L << (K_VALUES - 64)) | (1L << (K_WHEN - 64)) | (1L << (K_WHERE - 64)) | (1L << (K_WITH - 64)) | (1L << (K_GENERATE - 64)) | (1L << (K_MAX - 64)) | (1L << (K_MIN - 64)) | (1L << (K_AVG - 64)) | (1L << (K_COUNT - 64)) | (1L << (K_SUM - 64)) | (1L << (K_GGPLOT - 64)))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Select_stmtContext extends ParserRuleContext {
		public List<Select_or_valuesContext> select_or_values() {
			return getRuleContexts(Select_or_valuesContext.class);
		}
		public Select_or_valuesContext select_or_values(int i) {
			return getRuleContext(Select_or_valuesContext.class,i);
		}
		public TerminalNode K_WITH() { return getToken(queryParser.K_WITH, 0); }
		public List<Common_table_expressionContext> common_table_expression() {
			return getRuleContexts(Common_table_expressionContext.class);
		}
		public Common_table_expressionContext common_table_expression(int i) {
			return getRuleContext(Common_table_expressionContext.class,i);
		}
		public List<Compound_operatorContext> compound_operator() {
			return getRuleContexts(Compound_operatorContext.class);
		}
		public Compound_operatorContext compound_operator(int i) {
			return getRuleContext(Compound_operatorContext.class,i);
		}
		public TerminalNode K_ORDER() { return getToken(queryParser.K_ORDER, 0); }
		public TerminalNode K_BY() { return getToken(queryParser.K_BY, 0); }
		public List<Ordering_termContext> ordering_term() {
			return getRuleContexts(Ordering_termContext.class);
		}
		public Ordering_termContext ordering_term(int i) {
			return getRuleContext(Ordering_termContext.class,i);
		}
		public TerminalNode K_LIMIT() { return getToken(queryParser.K_LIMIT, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode K_RECURSIVE() { return getToken(queryParser.K_RECURSIVE, 0); }
		public TerminalNode K_OFFSET() { return getToken(queryParser.K_OFFSET, 0); }
		public Select_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterSelect_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitSelect_stmt(this);
		}
	}

	public final Select_stmtContext select_stmt() throws RecognitionException {
		Select_stmtContext _localctx = new Select_stmtContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_select_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(736);
			_la = _input.LA(1);
			if (_la==K_WITH) {
				{
				setState(724);
				match(K_WITH);
				setState(726);
				switch ( getInterpreter().adaptivePredict(_input,97,_ctx) ) {
				case 1:
					{
					setState(725);
					match(K_RECURSIVE);
					}
					break;
				}
				setState(728);
				common_table_expression();
				setState(733);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(729);
					match(C1);
					setState(730);
					common_table_expression();
					}
					}
					setState(735);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(738);
			select_or_values();
			setState(744);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 42)) & ~0x3f) == 0 && ((1L << (_la - 42)) & ((1L << (K_EXCEPT - 42)) | (1L << (K_INTERSECT - 42)) | (1L << (K_UNION - 42)))) != 0)) {
				{
				{
				setState(739);
				compound_operator();
				setState(740);
				select_or_values();
				}
				}
				setState(746);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(757);
			_la = _input.LA(1);
			if (_la==K_ORDER) {
				{
				setState(747);
				match(K_ORDER);
				setState(748);
				match(K_BY);
				setState(749);
				ordering_term();
				setState(754);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(750);
					match(C1);
					setState(751);
					ordering_term();
					}
					}
					setState(756);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(765);
			_la = _input.LA(1);
			if (_la==K_LIMIT) {
				{
				setState(759);
				match(K_LIMIT);
				setState(760);
				expr(0);
				setState(763);
				_la = _input.LA(1);
				if (_la==K_OFFSET || _la==C1) {
					{
					setState(761);
					_la = _input.LA(1);
					if ( !(_la==K_OFFSET || _la==C1) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					setState(762);
					expr(0);
					}
				}

				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Select_or_valuesContext extends ParserRuleContext {
		public TerminalNode K_FROM() { return getToken(queryParser.K_FROM, 0); }
		public TerminalNode K_SELECT() { return getToken(queryParser.K_SELECT, 0); }
		public List<Result_columnContext> result_column() {
			return getRuleContexts(Result_columnContext.class);
		}
		public Result_columnContext result_column(int i) {
			return getRuleContext(Result_columnContext.class,i);
		}
		public Where_clauseContext where_clause() {
			return getRuleContext(Where_clauseContext.class,0);
		}
		public List<Table_or_subqueryContext> table_or_subquery() {
			return getRuleContexts(Table_or_subqueryContext.class);
		}
		public Table_or_subqueryContext table_or_subquery(int i) {
			return getRuleContext(Table_or_subqueryContext.class,i);
		}
		public Join_clauseContext join_clause() {
			return getRuleContext(Join_clauseContext.class,0);
		}
		public TerminalNode K_DISTINCT() { return getToken(queryParser.K_DISTINCT, 0); }
		public TerminalNode K_ALL() { return getToken(queryParser.K_ALL, 0); }
		public Select_or_valuesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_or_values; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterSelect_or_values(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitSelect_or_values(this);
		}
	}

	public final Select_or_valuesContext select_or_values() throws RecognitionException {
		Select_or_valuesContext _localctx = new Select_or_valuesContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_select_or_values);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(779);
			_la = _input.LA(1);
			if (_la==K_SELECT) {
				{
				setState(767);
				match(K_SELECT);
				setState(769);
				switch ( getInterpreter().adaptivePredict(_input,105,_ctx) ) {
				case 1:
					{
					setState(768);
					_la = _input.LA(1);
					if ( !(_la==K_ALL || _la==K_DISTINCT) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					break;
				}
				setState(771);
				result_column();
				setState(776);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(772);
					match(C1);
					setState(773);
					result_column();
					}
					}
					setState(778);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			{
			setState(781);
			match(K_FROM);
			setState(791);
			switch ( getInterpreter().adaptivePredict(_input,109,_ctx) ) {
			case 1:
				{
				setState(782);
				table_or_subquery();
				setState(787);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(783);
					match(C1);
					setState(784);
					table_or_subquery();
					}
					}
					setState(789);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				{
				setState(790);
				join_clause();
				}
				break;
			}
			}
			setState(794);
			_la = _input.LA(1);
			if (((((_la - 48)) & ~0x3f) == 0 && ((1L << (_la - 48)) & ((1L << (K_GROUP - 48)) | (1L << (K_VALUES - 48)) | (1L << (K_WHERE - 48)))) != 0)) {
				{
				setState(793);
				where_clause();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Compound_operatorContext extends ParserRuleContext {
		public TerminalNode K_UNION() { return getToken(queryParser.K_UNION, 0); }
		public TerminalNode K_ALL() { return getToken(queryParser.K_ALL, 0); }
		public TerminalNode K_INTERSECT() { return getToken(queryParser.K_INTERSECT, 0); }
		public TerminalNode K_EXCEPT() { return getToken(queryParser.K_EXCEPT, 0); }
		public Compound_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compound_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterCompound_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitCompound_operator(this);
		}
	}

	public final Compound_operatorContext compound_operator() throws RecognitionException {
		Compound_operatorContext _localctx = new Compound_operatorContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_compound_operator);
		try {
			setState(801);
			switch ( getInterpreter().adaptivePredict(_input,111,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(796);
				match(K_UNION);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(797);
				match(K_UNION);
				setState(798);
				match(K_ALL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(799);
				match(K_INTERSECT);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(800);
				match(K_EXCEPT);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Join_clauseContext extends ParserRuleContext {
		public List<Table_or_subqueryContext> table_or_subquery() {
			return getRuleContexts(Table_or_subqueryContext.class);
		}
		public Table_or_subqueryContext table_or_subquery(int i) {
			return getRuleContext(Table_or_subqueryContext.class,i);
		}
		public List<Join_operatorContext> join_operator() {
			return getRuleContexts(Join_operatorContext.class);
		}
		public Join_operatorContext join_operator(int i) {
			return getRuleContext(Join_operatorContext.class,i);
		}
		public List<Join_constraintContext> join_constraint() {
			return getRuleContexts(Join_constraintContext.class);
		}
		public Join_constraintContext join_constraint(int i) {
			return getRuleContext(Join_constraintContext.class,i);
		}
		public List<Join_clauseContext> join_clause() {
			return getRuleContexts(Join_clauseContext.class);
		}
		public Join_clauseContext join_clause(int i) {
			return getRuleContext(Join_clauseContext.class,i);
		}
		public Join_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterJoin_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitJoin_clause(this);
		}
	}

	public final Join_clauseContext join_clause() throws RecognitionException {
		Join_clauseContext _localctx = new Join_clauseContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_join_clause);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(803);
			table_or_subquery();
			setState(813);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,113,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(804);
					join_operator();
					setState(807);
					switch ( getInterpreter().adaptivePredict(_input,112,_ctx) ) {
					case 1:
						{
						setState(805);
						join_clause();
						}
						break;
					case 2:
						{
						setState(806);
						table_or_subquery();
						}
						break;
					}
					setState(809);
					join_constraint();
					}
					} 
				}
				setState(815);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,113,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Join_operatorContext extends ParserRuleContext {
		public TerminalNode K_JOIN() { return getToken(queryParser.K_JOIN, 0); }
		public TerminalNode K_NATURAL() { return getToken(queryParser.K_NATURAL, 0); }
		public TerminalNode K_LEFT() { return getToken(queryParser.K_LEFT, 0); }
		public TerminalNode K_RIGHT() { return getToken(queryParser.K_RIGHT, 0); }
		public TerminalNode K_FULL() { return getToken(queryParser.K_FULL, 0); }
		public TerminalNode K_INNER() { return getToken(queryParser.K_INNER, 0); }
		public TerminalNode K_CROSS() { return getToken(queryParser.K_CROSS, 0); }
		public TerminalNode K_OUTER() { return getToken(queryParser.K_OUTER, 0); }
		public Join_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterJoin_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitJoin_operator(this);
		}
	}

	public final Join_operatorContext join_operator() throws RecognitionException {
		Join_operatorContext _localctx = new Join_operatorContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_join_operator);
		int _la;
		try {
			setState(837);
			switch (_input.LA(1)) {
			case C1:
				enterOuterAlt(_localctx, 1);
				{
				setState(816);
				match(C1);
				}
				break;
			case K_CROSS:
			case K_FULL:
			case K_INNER:
			case K_JOIN:
			case K_LEFT:
			case K_NATURAL:
			case K_RIGHT:
				enterOuterAlt(_localctx, 2);
				{
				setState(818);
				_la = _input.LA(1);
				if (_la==K_NATURAL) {
					{
					setState(817);
					match(K_NATURAL);
					}
				}

				setState(834);
				switch (_input.LA(1)) {
				case K_LEFT:
					{
					setState(820);
					match(K_LEFT);
					setState(822);
					_la = _input.LA(1);
					if (_la==K_OUTER) {
						{
						setState(821);
						match(K_OUTER);
						}
					}

					}
					break;
				case K_RIGHT:
					{
					setState(824);
					match(K_RIGHT);
					setState(826);
					_la = _input.LA(1);
					if (_la==K_OUTER) {
						{
						setState(825);
						match(K_OUTER);
						}
					}

					}
					break;
				case K_FULL:
					{
					setState(828);
					match(K_FULL);
					setState(830);
					_la = _input.LA(1);
					if (_la==K_OUTER) {
						{
						setState(829);
						match(K_OUTER);
						}
					}

					}
					break;
				case K_INNER:
					{
					setState(832);
					match(K_INNER);
					}
					break;
				case K_CROSS:
					{
					setState(833);
					match(K_CROSS);
					}
					break;
				case K_JOIN:
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(836);
				match(K_JOIN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Join_constraintContext extends ParserRuleContext {
		public TerminalNode K_ON() { return getToken(queryParser.K_ON, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode K_USING() { return getToken(queryParser.K_USING, 0); }
		public List<Column_nameContext> column_name() {
			return getRuleContexts(Column_nameContext.class);
		}
		public Column_nameContext column_name(int i) {
			return getRuleContext(Column_nameContext.class,i);
		}
		public Join_constraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterJoin_constraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitJoin_constraint(this);
		}
	}

	public final Join_constraintContext join_constraint() throws RecognitionException {
		Join_constraintContext _localctx = new Join_constraintContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_join_constraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(853);
			switch ( getInterpreter().adaptivePredict(_input,121,_ctx) ) {
			case 1:
				{
				setState(839);
				match(K_ON);
				setState(840);
				expr(0);
				}
				break;
			case 2:
				{
				setState(841);
				match(K_USING);
				setState(842);
				match(OPEN_PARENTHESE);
				setState(843);
				column_name();
				setState(848);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(844);
					match(C1);
					setState(845);
					column_name();
					}
					}
					setState(850);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(851);
				match(CLOSE_PARENTHESE);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Common_table_expressionContext extends ParserRuleContext {
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public TerminalNode K_AS() { return getToken(queryParser.K_AS, 0); }
		public Select_stmtContext select_stmt() {
			return getRuleContext(Select_stmtContext.class,0);
		}
		public List<Column_nameContext> column_name() {
			return getRuleContexts(Column_nameContext.class);
		}
		public Column_nameContext column_name(int i) {
			return getRuleContext(Column_nameContext.class,i);
		}
		public Common_table_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_common_table_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterCommon_table_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitCommon_table_expression(this);
		}
	}

	public final Common_table_expressionContext common_table_expression() throws RecognitionException {
		Common_table_expressionContext _localctx = new Common_table_expressionContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_common_table_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(855);
			table_name();
			setState(867);
			_la = _input.LA(1);
			if (_la==OPEN_PARENTHESE) {
				{
				setState(856);
				match(OPEN_PARENTHESE);
				setState(857);
				column_name();
				setState(862);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(858);
					match(C1);
					setState(859);
					column_name();
					}
					}
					setState(864);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(865);
				match(CLOSE_PARENTHESE);
				}
			}

			setState(869);
			match(K_AS);
			setState(870);
			match(OPEN_PARENTHESE);
			setState(871);
			select_stmt();
			setState(872);
			match(CLOSE_PARENTHESE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ordering_termContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode K_COLLATE() { return getToken(queryParser.K_COLLATE, 0); }
		public Collation_nameContext collation_name() {
			return getRuleContext(Collation_nameContext.class,0);
		}
		public TerminalNode K_ASC() { return getToken(queryParser.K_ASC, 0); }
		public TerminalNode K_DESC() { return getToken(queryParser.K_DESC, 0); }
		public Ordering_termContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ordering_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterOrdering_term(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitOrdering_term(this);
		}
	}

	public final Ordering_termContext ordering_term() throws RecognitionException {
		Ordering_termContext _localctx = new Ordering_termContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_ordering_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(874);
			expr(0);
			setState(877);
			_la = _input.LA(1);
			if (_la==K_COLLATE) {
				{
				setState(875);
				match(K_COLLATE);
				setState(876);
				collation_name();
				}
			}

			setState(880);
			_la = _input.LA(1);
			if (_la==K_ASC || _la==K_DESC) {
				{
				setState(879);
				_la = _input.LA(1);
				if ( !(_la==K_ASC || _la==K_DESC) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public Unary_operatorContext unary_operator() {
			return getRuleContext(Unary_operatorContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public OperandContext operand() {
			return getRuleContext(OperandContext.class,0);
		}
		public TerminalNode BIND_PARAMETER() { return getToken(queryParser.BIND_PARAMETER, 0); }
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Table_aliasContext table_alias() {
			return getRuleContext(Table_aliasContext.class,0);
		}
		public Database_nameContext database_name() {
			return getRuleContext(Database_nameContext.class,0);
		}
		public TerminalNode K_CAST() { return getToken(queryParser.K_CAST, 0); }
		public TerminalNode K_AS() { return getToken(queryParser.K_AS, 0); }
		public Type_nameContext type_name() {
			return getRuleContext(Type_nameContext.class,0);
		}
		public Select_stmtContext select_stmt() {
			return getRuleContext(Select_stmtContext.class,0);
		}
		public TerminalNode K_EXISTS() { return getToken(queryParser.K_EXISTS, 0); }
		public TerminalNode K_NOT() { return getToken(queryParser.K_NOT, 0); }
		public TerminalNode K_CASE() { return getToken(queryParser.K_CASE, 0); }
		public TerminalNode K_END() { return getToken(queryParser.K_END, 0); }
		public List<TerminalNode> K_WHEN() { return getTokens(queryParser.K_WHEN); }
		public TerminalNode K_WHEN(int i) {
			return getToken(queryParser.K_WHEN, i);
		}
		public List<TerminalNode> K_THEN() { return getTokens(queryParser.K_THEN); }
		public TerminalNode K_THEN(int i) {
			return getToken(queryParser.K_THEN, i);
		}
		public TerminalNode K_ELSE() { return getToken(queryParser.K_ELSE, 0); }
		public Raise_functionContext raise_function() {
			return getRuleContext(Raise_functionContext.class,0);
		}
		public TerminalNode K_IS() { return getToken(queryParser.K_IS, 0); }
		public TerminalNode K_IN() { return getToken(queryParser.K_IN, 0); }
		public TerminalNode K_LIKE() { return getToken(queryParser.K_LIKE, 0); }
		public TerminalNode K_GLOB() { return getToken(queryParser.K_GLOB, 0); }
		public TerminalNode K_MATCH() { return getToken(queryParser.K_MATCH, 0); }
		public TerminalNode K_REGEXP() { return getToken(queryParser.K_REGEXP, 0); }
		public TerminalNode K_AND() { return getToken(queryParser.K_AND, 0); }
		public TerminalNode K_OR() { return getToken(queryParser.K_OR, 0); }
		public TerminalNode K_BETWEEN() { return getToken(queryParser.K_BETWEEN, 0); }
		public TerminalNode K_COLLATE() { return getToken(queryParser.K_COLLATE, 0); }
		public Collation_nameContext collation_name() {
			return getRuleContext(Collation_nameContext.class,0);
		}
		public TerminalNode K_ESCAPE() { return getToken(queryParser.K_ESCAPE, 0); }
		public TerminalNode K_ISNULL() { return getToken(queryParser.K_ISNULL, 0); }
		public TerminalNode K_NOTNULL() { return getToken(queryParser.K_NOTNULL, 0); }
		public TerminalNode K_NULL() { return getToken(queryParser.K_NULL, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 82;
		enterRecursionRule(_localctx, 82, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(940);
			switch ( getInterpreter().adaptivePredict(_input,133,_ctx) ) {
			case 1:
				{
				setState(883);
				unary_operator();
				setState(884);
				expr(20);
				}
				break;
			case 2:
				{
				setState(886);
				operand();
				}
				break;
			case 3:
				{
				setState(887);
				match(BIND_PARAMETER);
				}
				break;
			case 4:
				{
				setState(896);
				switch ( getInterpreter().adaptivePredict(_input,127,_ctx) ) {
				case 1:
					{
					setState(891);
					switch ( getInterpreter().adaptivePredict(_input,126,_ctx) ) {
					case 1:
						{
						setState(888);
						database_name();
						setState(889);
						match(DOT);
						}
						break;
					}
					setState(893);
					table_alias();
					setState(894);
					match(DOT);
					}
					break;
				}
				setState(898);
				column_name();
				}
				break;
			case 5:
				{
				setState(899);
				match(OPEN_PARENTHESE);
				setState(900);
				expr(0);
				setState(901);
				match(CLOSE_PARENTHESE);
				}
				break;
			case 6:
				{
				setState(903);
				match(K_CAST);
				setState(904);
				match(OPEN_PARENTHESE);
				setState(905);
				expr(0);
				setState(906);
				match(K_AS);
				setState(907);
				type_name();
				setState(908);
				match(CLOSE_PARENTHESE);
				}
				break;
			case 7:
				{
				setState(914);
				_la = _input.LA(1);
				if (_la==K_EXISTS || _la==K_NOT) {
					{
					setState(911);
					_la = _input.LA(1);
					if (_la==K_NOT) {
						{
						setState(910);
						match(K_NOT);
						}
					}

					setState(913);
					match(K_EXISTS);
					}
				}

				setState(916);
				match(OPEN_PARENTHESE);
				setState(917);
				select_stmt();
				setState(918);
				match(CLOSE_PARENTHESE);
				}
				break;
			case 8:
				{
				setState(920);
				match(K_CASE);
				setState(922);
				switch ( getInterpreter().adaptivePredict(_input,130,_ctx) ) {
				case 1:
					{
					setState(921);
					expr(0);
					}
					break;
				}
				setState(929); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(924);
					match(K_WHEN);
					setState(925);
					expr(0);
					setState(926);
					match(K_THEN);
					setState(927);
					expr(0);
					}
					}
					setState(931); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==K_WHEN );
				setState(935);
				_la = _input.LA(1);
				if (_la==K_ELSE) {
					{
					setState(933);
					match(K_ELSE);
					setState(934);
					expr(0);
					}
				}

				setState(937);
				match(K_END);
				}
				break;
			case 9:
				{
				setState(939);
				raise_function();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(1042);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,146,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(1040);
					switch ( getInterpreter().adaptivePredict(_input,145,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(942);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(943);
						match(T__0);
						setState(944);
						expr(20);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(945);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(946);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__8))) != 0) || _la==C3) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(947);
						expr(19);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(948);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(949);
						_la = _input.LA(1);
						if ( !(_la==T__6 || _la==T__7) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(950);
						expr(18);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(951);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(952);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__9) | (1L << T__10) | (1L << T__11))) != 0)) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(953);
						expr(17);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(954);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(955);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15))) != 0)) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(956);
						expr(16);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(957);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(970);
						switch ( getInterpreter().adaptivePredict(_input,134,_ctx) ) {
						case 1:
							{
							setState(958);
							match(T__16);
							}
							break;
						case 2:
							{
							setState(959);
							match(T__17);
							}
							break;
						case 3:
							{
							setState(960);
							match(T__18);
							}
							break;
						case 4:
							{
							setState(961);
							match(T__19);
							}
							break;
						case 5:
							{
							setState(962);
							match(K_IS);
							}
							break;
						case 6:
							{
							setState(963);
							match(K_IS);
							setState(964);
							match(K_NOT);
							}
							break;
						case 7:
							{
							setState(965);
							match(K_IN);
							}
							break;
						case 8:
							{
							setState(966);
							match(K_LIKE);
							}
							break;
						case 9:
							{
							setState(967);
							match(K_GLOB);
							}
							break;
						case 10:
							{
							setState(968);
							match(K_MATCH);
							}
							break;
						case 11:
							{
							setState(969);
							match(K_REGEXP);
							}
							break;
						}
						setState(972);
						expr(15);
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(973);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(974);
						match(K_AND);
						setState(975);
						expr(14);
						}
						break;
					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(976);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(977);
						match(K_OR);
						setState(978);
						expr(13);
						}
						break;
					case 9:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(979);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(980);
						match(K_IS);
						setState(982);
						switch ( getInterpreter().adaptivePredict(_input,135,_ctx) ) {
						case 1:
							{
							setState(981);
							match(K_NOT);
							}
							break;
						}
						setState(984);
						expr(7);
						}
						break;
					case 10:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(985);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(987);
						_la = _input.LA(1);
						if (_la==K_NOT) {
							{
							setState(986);
							match(K_NOT);
							}
						}

						setState(989);
						match(K_BETWEEN);
						setState(990);
						expr(0);
						setState(991);
						match(K_AND);
						setState(992);
						expr(6);
						}
						break;
					case 11:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(994);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(995);
						match(K_COLLATE);
						setState(996);
						collation_name();
						}
						break;
					case 12:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(997);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(999);
						_la = _input.LA(1);
						if (_la==K_NOT) {
							{
							setState(998);
							match(K_NOT);
							}
						}

						setState(1001);
						_la = _input.LA(1);
						if ( !(((((_la - 47)) & ~0x3f) == 0 && ((1L << (_la - 47)) & ((1L << (K_GLOB - 47)) | (1L << (K_LIKE - 47)) | (1L << (K_MATCH - 47)) | (1L << (K_REGEXP - 47)))) != 0)) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(1002);
						expr(0);
						setState(1005);
						switch ( getInterpreter().adaptivePredict(_input,138,_ctx) ) {
						case 1:
							{
							setState(1003);
							match(K_ESCAPE);
							setState(1004);
							expr(0);
							}
							break;
						}
						}
						break;
					case 13:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(1007);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(1012);
						switch (_input.LA(1)) {
						case K_ISNULL:
							{
							setState(1008);
							match(K_ISNULL);
							}
							break;
						case K_NOTNULL:
							{
							setState(1009);
							match(K_NOTNULL);
							}
							break;
						case K_NOT:
							{
							setState(1010);
							match(K_NOT);
							setState(1011);
							match(K_NULL);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						}
						break;
					case 14:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(1014);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(1016);
						_la = _input.LA(1);
						if (_la==K_NOT) {
							{
							setState(1015);
							match(K_NOT);
							}
						}

						setState(1018);
						match(K_IN);
						setState(1038);
						switch (_input.LA(1)) {
						case OPEN_PARENTHESE:
							{
							setState(1019);
							match(OPEN_PARENTHESE);
							setState(1029);
							switch ( getInterpreter().adaptivePredict(_input,142,_ctx) ) {
							case 1:
								{
								setState(1020);
								select_stmt();
								}
								break;
							case 2:
								{
								setState(1021);
								expr(0);
								setState(1026);
								_errHandler.sync(this);
								_la = _input.LA(1);
								while (_la==C1) {
									{
									{
									setState(1022);
									match(C1);
									setState(1023);
									expr(0);
									}
									}
									setState(1028);
									_errHandler.sync(this);
									_la = _input.LA(1);
								}
								}
								break;
							}
							setState(1031);
							match(CLOSE_PARENTHESE);
							}
							break;
						case K_ABORT:
						case K_ALL:
						case K_AND:
						case K_AS:
						case K_ASC:
						case K_BETWEEN:
						case K_BY:
						case K_CASE:
						case K_CAST:
						case K_COLLATE:
						case K_CROSS:
						case K_CURRENT_DATE:
						case K_CURRENT_TIME:
						case K_CURRENT_TIMESTAMP:
						case K_DESC:
						case K_DISTINCT:
						case K_ELSE:
						case K_END:
						case K_ESCAPE:
						case K_EXCEPT:
						case K_EXISTS:
						case K_FAIL:
						case K_FULL:
						case K_FROM:
						case K_GLOB:
						case K_GROUP:
						case K_HAVING:
						case K_IF:
						case K_IGNORE:
						case K_IN:
						case K_INDEXED:
						case K_INNER:
						case K_INTERSECT:
						case K_IS:
						case K_ISNULL:
						case K_JOIN:
						case K_LEFT:
						case K_LIKE:
						case K_LIMIT:
						case K_MATCH:
						case K_NATURAL:
						case K_NO:
						case K_NOT:
						case K_NOTNULL:
						case K_NULL:
						case K_OFFSET:
						case K_ON:
						case K_OR:
						case K_ORDER:
						case K_OUTER:
						case K_RAISE:
						case K_RECURSIVE:
						case K_REGEXP:
						case K_RIGHT:
						case K_ROLLBACK:
						case K_SELECT:
						case K_THEN:
						case K_UNION:
						case K_USING:
						case K_VALUES:
						case K_WHEN:
						case K_WHERE:
						case K_WITH:
						case K_GENERATE:
						case K_MAX:
						case K_MIN:
						case K_AVG:
						case K_COUNT:
						case K_SUM:
						case K_GGPLOT:
						case IDENTIFIER:
						case STRING_LITERAL:
							{
							setState(1035);
							switch ( getInterpreter().adaptivePredict(_input,143,_ctx) ) {
							case 1:
								{
								setState(1032);
								database_name();
								setState(1033);
								match(DOT);
								}
								break;
							}
							setState(1037);
							table_name();
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						}
						break;
					}
					} 
				}
				setState(1044);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,146,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Literal_valueContext extends ParserRuleContext {
		public TerminalNode NUMERIC_LITERAL() { return getToken(queryParser.NUMERIC_LITERAL, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(queryParser.STRING_LITERAL, 0); }
		public TerminalNode BLOB_LITERAL() { return getToken(queryParser.BLOB_LITERAL, 0); }
		public TerminalNode K_NULL() { return getToken(queryParser.K_NULL, 0); }
		public TerminalNode K_CURRENT_TIME() { return getToken(queryParser.K_CURRENT_TIME, 0); }
		public TerminalNode K_CURRENT_DATE() { return getToken(queryParser.K_CURRENT_DATE, 0); }
		public TerminalNode K_CURRENT_TIMESTAMP() { return getToken(queryParser.K_CURRENT_TIMESTAMP, 0); }
		public Literal_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterLiteral_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitLiteral_value(this);
		}
	}

	public final Literal_valueContext literal_value() throws RecognitionException {
		Literal_valueContext _localctx = new Literal_valueContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_literal_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1045);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_CURRENT_DATE) | (1L << K_CURRENT_TIME) | (1L << K_CURRENT_TIMESTAMP))) != 0) || ((((_la - 67)) & ~0x3f) == 0 && ((1L << (_la - 67)) & ((1L << (K_NULL - 67)) | (1L << (NUMERIC_LITERAL - 67)) | (1L << (BLOB_LITERAL - 67)) | (1L << (STRING_LITERAL - 67)))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Unary_operatorContext extends ParserRuleContext {
		public TerminalNode K_NOT() { return getToken(queryParser.K_NOT, 0); }
		public Unary_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterUnary_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitUnary_operator(this);
		}
	}

	public final Unary_operatorContext unary_operator() throws RecognitionException {
		Unary_operatorContext _localctx = new Unary_operatorContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_unary_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1047);
			_la = _input.LA(1);
			if ( !(((((_la - 7)) & ~0x3f) == 0 && ((1L << (_la - 7)) & ((1L << (T__6 - 7)) | (1L << (T__7 - 7)) | (1L << (T__20 - 7)) | (1L << (K_NOT - 7)))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NameContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitName(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1049);
			any_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_nameContext extends ParserRuleContext {
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public List<Signed_numberContext> signed_number() {
			return getRuleContexts(Signed_numberContext.class);
		}
		public Signed_numberContext signed_number(int i) {
			return getRuleContext(Signed_numberContext.class,i);
		}
		public Type_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterType_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitType_name(this);
		}
	}

	public final Type_nameContext type_name() throws RecognitionException {
		Type_nameContext _localctx = new Type_nameContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_type_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1052); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1051);
				name();
				}
				}
				setState(1054); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_ABORT) | (1L << K_ALL) | (1L << K_AND) | (1L << K_AS) | (1L << K_ASC) | (1L << K_BETWEEN) | (1L << K_BY) | (1L << K_CASE) | (1L << K_CAST) | (1L << K_COLLATE) | (1L << K_CROSS) | (1L << K_CURRENT_DATE) | (1L << K_CURRENT_TIME) | (1L << K_CURRENT_TIMESTAMP) | (1L << K_DESC) | (1L << K_DISTINCT) | (1L << K_ELSE) | (1L << K_END) | (1L << K_ESCAPE) | (1L << K_EXCEPT) | (1L << K_EXISTS) | (1L << K_FAIL) | (1L << K_FULL) | (1L << K_FROM) | (1L << K_GLOB) | (1L << K_GROUP) | (1L << K_HAVING) | (1L << K_IF) | (1L << K_IGNORE) | (1L << K_IN) | (1L << K_INDEXED) | (1L << K_INNER) | (1L << K_INTERSECT) | (1L << K_IS) | (1L << K_ISNULL) | (1L << K_JOIN) | (1L << K_LEFT) | (1L << K_LIKE) | (1L << K_LIMIT) | (1L << K_MATCH) | (1L << K_NATURAL))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (K_NO - 64)) | (1L << (K_NOT - 64)) | (1L << (K_NOTNULL - 64)) | (1L << (K_NULL - 64)) | (1L << (K_OFFSET - 64)) | (1L << (K_ON - 64)) | (1L << (K_OR - 64)) | (1L << (K_ORDER - 64)) | (1L << (K_OUTER - 64)) | (1L << (K_RAISE - 64)) | (1L << (K_RECURSIVE - 64)) | (1L << (K_REGEXP - 64)) | (1L << (K_RIGHT - 64)) | (1L << (K_ROLLBACK - 64)) | (1L << (K_SELECT - 64)) | (1L << (K_THEN - 64)) | (1L << (K_UNION - 64)) | (1L << (K_USING - 64)) | (1L << (K_VALUES - 64)) | (1L << (K_WHEN - 64)) | (1L << (K_WHERE - 64)) | (1L << (K_WITH - 64)) | (1L << (K_GENERATE - 64)) | (1L << (K_MAX - 64)) | (1L << (K_MIN - 64)) | (1L << (K_AVG - 64)) | (1L << (K_COUNT - 64)) | (1L << (K_SUM - 64)) | (1L << (K_GGPLOT - 64)) | (1L << (IDENTIFIER - 64)) | (1L << (STRING_LITERAL - 64)))) != 0) );
			setState(1066);
			switch ( getInterpreter().adaptivePredict(_input,148,_ctx) ) {
			case 1:
				{
				setState(1056);
				match(OPEN_PARENTHESE);
				setState(1057);
				signed_number();
				setState(1058);
				match(CLOSE_PARENTHESE);
				}
				break;
			case 2:
				{
				setState(1060);
				match(OPEN_PARENTHESE);
				setState(1061);
				signed_number();
				setState(1062);
				match(C1);
				setState(1063);
				signed_number();
				setState(1064);
				match(CLOSE_PARENTHESE);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Function_nameContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Function_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterFunction_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitFunction_name(this);
		}
	}

	public final Function_nameContext function_name() throws RecognitionException {
		Function_nameContext _localctx = new Function_nameContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_function_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1068);
			any_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ag_function_nameContext extends ParserRuleContext {
		public Ag_keywordContext ag_keyword() {
			return getRuleContext(Ag_keywordContext.class,0);
		}
		public Ag_function_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ag_function_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterAg_function_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitAg_function_name(this);
		}
	}

	public final Ag_function_nameContext ag_function_name() throws RecognitionException {
		Ag_function_nameContext _localctx = new Ag_function_nameContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_ag_function_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1070);
			ag_keyword();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ag_keywordContext extends ParserRuleContext {
		public TerminalNode K_MAX() { return getToken(queryParser.K_MAX, 0); }
		public TerminalNode K_MIN() { return getToken(queryParser.K_MIN, 0); }
		public TerminalNode K_SUM() { return getToken(queryParser.K_SUM, 0); }
		public TerminalNode K_AVG() { return getToken(queryParser.K_AVG, 0); }
		public TerminalNode K_COUNT() { return getToken(queryParser.K_COUNT, 0); }
		public Ag_keywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ag_keyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterAg_keyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitAg_keyword(this);
		}
	}

	public final Ag_keywordContext ag_keyword() throws RecognitionException {
		Ag_keywordContext _localctx = new Ag_keywordContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_ag_keyword);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1072);
			_la = _input.LA(1);
			if ( !(((((_la - 87)) & ~0x3f) == 0 && ((1L << (_la - 87)) & ((1L << (K_MAX - 87)) | (1L << (K_MIN - 87)) | (1L << (K_AVG - 87)) | (1L << (K_COUNT - 87)) | (1L << (K_SUM - 87)))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Gg_function_nameContext extends ParserRuleContext {
		public Gg_keywordContext gg_keyword() {
			return getRuleContext(Gg_keywordContext.class,0);
		}
		public Gg_function_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gg_function_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterGg_function_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitGg_function_name(this);
		}
	}

	public final Gg_function_nameContext gg_function_name() throws RecognitionException {
		Gg_function_nameContext _localctx = new Gg_function_nameContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_gg_function_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1074);
			gg_keyword();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Gg_keywordContext extends ParserRuleContext {
		public TerminalNode K_GGPLOT() { return getToken(queryParser.K_GGPLOT, 0); }
		public Gg_keywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gg_keyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterGg_keyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitGg_keyword(this);
		}
	}

	public final Gg_keywordContext gg_keyword() throws RecognitionException {
		Gg_keywordContext _localctx = new Gg_keywordContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_gg_keyword);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1076);
			match(K_GGPLOT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Collation_nameContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Collation_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_collation_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterCollation_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitCollation_name(this);
		}
	}

	public final Collation_nameContext collation_name() throws RecognitionException {
		Collation_nameContext _localctx = new Collation_nameContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_collation_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1078);
			any_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Database_nameContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Database_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_database_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterDatabase_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitDatabase_name(this);
		}
	}

	public final Database_nameContext database_name() throws RecognitionException {
		Database_nameContext _localctx = new Database_nameContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_database_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1080);
			any_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_nameContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Table_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterTable_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitTable_name(this);
		}
	}

	public final Table_nameContext table_name() throws RecognitionException {
		Table_nameContext _localctx = new Table_nameContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_table_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1082);
			any_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Column_aliasContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(queryParser.IDENTIFIER, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(queryParser.STRING_LITERAL, 0); }
		public Column_aliasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_alias; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterColumn_alias(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitColumn_alias(this);
		}
	}

	public final Column_aliasContext column_alias() throws RecognitionException {
		Column_aliasContext _localctx = new Column_aliasContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_column_alias);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1084);
			_la = _input.LA(1);
			if ( !(_la==IDENTIFIER || _la==STRING_LITERAL) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Column_nameContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Column_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterColumn_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitColumn_name(this);
		}
	}

	public final Column_nameContext column_name() throws RecognitionException {
		Column_nameContext _localctx = new Column_nameContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_column_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1086);
			any_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_aliasContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Table_aliasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_alias; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterTable_alias(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitTable_alias(this);
		}
	}

	public final Table_aliasContext table_alias() throws RecognitionException {
		Table_aliasContext _localctx = new Table_aliasContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_table_alias);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1088);
			any_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Index_nameContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Index_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_index_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterIndex_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitIndex_name(this);
		}
	}

	public final Index_nameContext index_name() throws RecognitionException {
		Index_nameContext _localctx = new Index_nameContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_index_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1090);
			any_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Any_nameContext extends ParserRuleContext {
		public KeywordContext keyword() {
			return getRuleContext(KeywordContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(queryParser.IDENTIFIER, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(queryParser.STRING_LITERAL, 0); }
		public Any_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_any_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterAny_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitAny_name(this);
		}
	}

	public final Any_nameContext any_name() throws RecognitionException {
		Any_nameContext _localctx = new Any_nameContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_any_name);
		try {
			setState(1095);
			switch (_input.LA(1)) {
			case K_ABORT:
			case K_ALL:
			case K_AND:
			case K_AS:
			case K_ASC:
			case K_BETWEEN:
			case K_BY:
			case K_CASE:
			case K_CAST:
			case K_COLLATE:
			case K_CROSS:
			case K_CURRENT_DATE:
			case K_CURRENT_TIME:
			case K_CURRENT_TIMESTAMP:
			case K_DESC:
			case K_DISTINCT:
			case K_ELSE:
			case K_END:
			case K_ESCAPE:
			case K_EXCEPT:
			case K_EXISTS:
			case K_FAIL:
			case K_FULL:
			case K_FROM:
			case K_GLOB:
			case K_GROUP:
			case K_HAVING:
			case K_IF:
			case K_IGNORE:
			case K_IN:
			case K_INDEXED:
			case K_INNER:
			case K_INTERSECT:
			case K_IS:
			case K_ISNULL:
			case K_JOIN:
			case K_LEFT:
			case K_LIKE:
			case K_LIMIT:
			case K_MATCH:
			case K_NATURAL:
			case K_NO:
			case K_NOT:
			case K_NOTNULL:
			case K_NULL:
			case K_OFFSET:
			case K_ON:
			case K_OR:
			case K_ORDER:
			case K_OUTER:
			case K_RAISE:
			case K_RECURSIVE:
			case K_REGEXP:
			case K_RIGHT:
			case K_ROLLBACK:
			case K_SELECT:
			case K_THEN:
			case K_UNION:
			case K_USING:
			case K_VALUES:
			case K_WHEN:
			case K_WHERE:
			case K_WITH:
			case K_GENERATE:
			case K_MAX:
			case K_MIN:
			case K_AVG:
			case K_COUNT:
			case K_SUM:
			case K_GGPLOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(1092);
				keyword();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(1093);
				match(IDENTIFIER);
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(1094);
				match(STRING_LITERAL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SlContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(queryParser.STRING_LITERAL, 0); }
		public SlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterSl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitSl(this);
		}
	}

	public final SlContext sl() throws RecognitionException {
		SlContext _localctx = new SlContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_sl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1097);
			match(STRING_LITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Signed_numberContext extends ParserRuleContext {
		public TerminalNode NUMERIC_LITERAL() { return getToken(queryParser.NUMERIC_LITERAL, 0); }
		public Signed_numberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_signed_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterSigned_number(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitSigned_number(this);
		}
	}

	public final Signed_numberContext signed_number() throws RecognitionException {
		Signed_numberContext _localctx = new Signed_numberContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_signed_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1100);
			_la = _input.LA(1);
			if (_la==T__6 || _la==T__7) {
				{
				setState(1099);
				_la = _input.LA(1);
				if ( !(_la==T__6 || _la==T__7) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
			}

			setState(1102);
			match(NUMERIC_LITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Raise_functionContext extends ParserRuleContext {
		public TerminalNode K_RAISE() { return getToken(queryParser.K_RAISE, 0); }
		public TerminalNode K_IGNORE() { return getToken(queryParser.K_IGNORE, 0); }
		public Error_messageContext error_message() {
			return getRuleContext(Error_messageContext.class,0);
		}
		public TerminalNode K_ROLLBACK() { return getToken(queryParser.K_ROLLBACK, 0); }
		public TerminalNode K_ABORT() { return getToken(queryParser.K_ABORT, 0); }
		public TerminalNode K_FAIL() { return getToken(queryParser.K_FAIL, 0); }
		public Raise_functionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_raise_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterRaise_function(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitRaise_function(this);
		}
	}

	public final Raise_functionContext raise_function() throws RecognitionException {
		Raise_functionContext _localctx = new Raise_functionContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_raise_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1104);
			match(K_RAISE);
			setState(1105);
			match(OPEN_PARENTHESE);
			setState(1110);
			switch (_input.LA(1)) {
			case K_IGNORE:
				{
				setState(1106);
				match(K_IGNORE);
				}
				break;
			case K_ABORT:
			case K_FAIL:
			case K_ROLLBACK:
				{
				setState(1107);
				_la = _input.LA(1);
				if ( !(((((_la - 22)) & ~0x3f) == 0 && ((1L << (_la - 22)) & ((1L << (K_ABORT - 22)) | (1L << (K_FAIL - 22)) | (1L << (K_ROLLBACK - 22)))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(1108);
				match(C1);
				setState(1109);
				error_message();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1112);
			match(CLOSE_PARENTHESE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Error_messageContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(queryParser.STRING_LITERAL, 0); }
		public Error_messageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_error_message; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterError_message(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitError_message(this);
		}
	}

	public final Error_messageContext error_message() throws RecognitionException {
		Error_messageContext _localctx = new Error_messageContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_error_message);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1114);
			match(STRING_LITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 21:
			return arithmetics_sempred((ArithmeticsContext)_localctx, predIndex);
		case 41:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean arithmetics_sempred(ArithmeticsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 19);
		case 2:
			return precpred(_ctx, 18);
		case 3:
			return precpred(_ctx, 17);
		case 4:
			return precpred(_ctx, 16);
		case 5:
			return precpred(_ctx, 15);
		case 6:
			return precpred(_ctx, 14);
		case 7:
			return precpred(_ctx, 13);
		case 8:
			return precpred(_ctx, 12);
		case 9:
			return precpred(_ctx, 6);
		case 10:
			return precpred(_ctx, 5);
		case 11:
			return precpred(_ctx, 9);
		case 12:
			return precpred(_ctx, 8);
		case 13:
			return precpred(_ctx, 7);
		case 14:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3v\u045f\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\3\2\3\2\3\2\5\2\u0084\n\2\3\3\3\3\5\3\u0088\n\3\3\3"+
		"\5\3\u008b\n\3\3\4\3\4\3\4\3\5\5\5\u0091\n\5\3\5\3\5\5\5\u0095\n\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00a2\n\5\3\5\3\5\3\5\3\5"+
		"\3\5\5\5\u00a9\n\5\3\5\5\5\u00ac\n\5\3\5\5\5\u00af\n\5\3\6\3\6\3\6\5\6"+
		"\u00b4\n\6\3\6\3\6\3\7\3\7\3\7\5\7\u00bb\n\7\3\7\3\7\3\7\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00d1\n\b\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00db\n\t\3\t\3\t\3\t\3\t\5\t\u00e1"+
		"\n\t\5\t\u00e3\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00ed\n\t\3\t\3"+
		"\t\3\t\3\t\5\t\u00f3\n\t\5\t\u00f5\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\5\t\u00ff\n\t\3\t\3\t\3\t\3\t\5\t\u0105\n\t\5\t\u0107\n\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u0113\n\t\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\5\13\u011b\n\13\7\13\u011d\n\13\f\13\16\13\u0120\13\13\3\f\3\f\3\f\6"+
		"\f\u0125\n\f\r\f\16\f\u0126\3\r\3\r\3\r\3\r\5\r\u012d\n\r\7\r\u012f\n"+
		"\r\f\r\16\r\u0132\13\r\3\16\3\16\3\16\3\16\5\16\u0138\n\16\7\16\u013a"+
		"\n\16\f\16\16\16\u013d\13\16\3\17\3\17\5\17\u0141\n\17\3\17\3\17\3\17"+
		"\5\17\u0146\n\17\7\17\u0148\n\17\f\17\16\17\u014b\13\17\3\20\3\20\5\20"+
		"\u014f\n\20\3\20\3\20\3\20\5\20\u0154\n\20\7\20\u0156\n\20\f\20\16\20"+
		"\u0159\13\20\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u0161\n\21\3\22\5\22\u0164"+
		"\n\22\3\22\3\22\3\22\3\22\5\22\u016a\n\22\3\22\3\22\3\22\5\22\u016f\n"+
		"\22\7\22\u0171\n\22\f\22\16\22\u0174\13\22\3\22\3\22\3\23\3\23\3\23\3"+
		"\23\3\23\5\23\u017d\n\23\3\23\3\23\3\23\5\23\u0182\n\23\7\23\u0184\n\23"+
		"\f\23\16\23\u0187\13\23\7\23\u0189\n\23\f\23\16\23\u018c\13\23\3\23\3"+
		"\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\7\25\u019a\n\25"+
		"\f\25\16\25\u019d\13\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5"+
		"\26\u01b6\n\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u01c0\n\27"+
		"\3\27\3\27\3\27\7\27\u01c5\n\27\f\27\16\27\u01c8\13\27\3\30\3\30\5\30"+
		"\u01cc\n\30\3\31\3\31\5\31\u01d0\n\31\3\32\3\32\3\32\3\33\3\33\6\33\u01d7"+
		"\n\33\r\33\16\33\u01d8\3\33\7\33\u01dc\n\33\f\33\16\33\u01df\13\33\3\33"+
		"\7\33\u01e2\n\33\f\33\16\33\u01e5\13\33\3\34\3\34\5\34\u01e9\n\34\3\35"+
		"\3\35\5\35\u01ed\n\35\3\35\3\35\3\35\7\35\u01f2\n\35\f\35\16\35\u01f5"+
		"\13\35\5\35\u01f7\n\35\3\35\3\35\3\35\3\35\7\35\u01fd\n\35\f\35\16\35"+
		"\u0200\13\35\3\35\3\35\3\35\3\35\3\35\7\35\u0207\n\35\f\35\16\35\u020a"+
		"\13\35\5\35\u020c\n\35\3\35\3\35\3\35\3\35\5\35\u0212\n\35\5\35\u0214"+
		"\n\35\3\36\3\36\5\36\u0218\n\36\3\36\3\36\3\36\7\36\u021d\n\36\f\36\16"+
		"\36\u0220\13\36\5\36\u0222\n\36\3\36\3\36\3\36\3\36\7\36\u0228\n\36\f"+
		"\36\16\36\u022b\13\36\3\36\5\36\u022e\n\36\3\36\5\36\u0231\n\36\3\37\3"+
		"\37\3\37\3\37\3\37\3\37\3\37\3\37\7\37\u023b\n\37\f\37\16\37\u023e\13"+
		"\37\3\37\3\37\5\37\u0242\n\37\5\37\u0244\n\37\3\37\3\37\3\37\3\37\3\37"+
		"\7\37\u024b\n\37\f\37\16\37\u024e\13\37\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\7\37\u0256\n\37\f\37\16\37\u0259\13\37\3\37\3\37\7\37\u025d\n\37\f\37"+
		"\16\37\u0260\13\37\5\37\u0262\n\37\3\37\3\37\5\37\u0266\n\37\3\37\3\37"+
		"\3\37\3\37\3\37\7\37\u026d\n\37\f\37\16\37\u0270\13\37\3\37\3\37\5\37"+
		"\u0274\n\37\3\37\3\37\3\37\3\37\3\37\7\37\u027b\n\37\f\37\16\37\u027e"+
		"\13\37\3\37\3\37\3\37\3\37\3\37\3\37\7\37\u0286\n\37\f\37\16\37\u0289"+
		"\13\37\3\37\3\37\7\37\u028d\n\37\f\37\16\37\u0290\13\37\5\37\u0292\n\37"+
		"\5\37\u0294\n\37\3 \3 \3 \3 \3 \3 \3 \5 \u029d\n \3 \5 \u02a0\n \5 \u02a2"+
		"\n \3!\3!\3!\5!\u02a7\n!\3!\3!\5!\u02ab\n!\3!\5!\u02ae\n!\3!\3!\3!\3!"+
		"\3!\5!\u02b5\n!\3!\3!\3!\3!\7!\u02bb\n!\f!\16!\u02be\13!\3!\5!\u02c1\n"+
		"!\3!\3!\5!\u02c5\n!\3!\5!\u02c8\n!\3!\3!\3!\3!\5!\u02ce\n!\3!\5!\u02d1"+
		"\n!\5!\u02d3\n!\3\"\3\"\3#\3#\5#\u02d9\n#\3#\3#\3#\7#\u02de\n#\f#\16#"+
		"\u02e1\13#\5#\u02e3\n#\3#\3#\3#\3#\7#\u02e9\n#\f#\16#\u02ec\13#\3#\3#"+
		"\3#\3#\3#\7#\u02f3\n#\f#\16#\u02f6\13#\5#\u02f8\n#\3#\3#\3#\3#\5#\u02fe"+
		"\n#\5#\u0300\n#\3$\3$\5$\u0304\n$\3$\3$\3$\7$\u0309\n$\f$\16$\u030c\13"+
		"$\5$\u030e\n$\3$\3$\3$\3$\7$\u0314\n$\f$\16$\u0317\13$\3$\5$\u031a\n$"+
		"\3$\5$\u031d\n$\3%\3%\3%\3%\3%\5%\u0324\n%\3&\3&\3&\3&\5&\u032a\n&\3&"+
		"\3&\7&\u032e\n&\f&\16&\u0331\13&\3\'\3\'\5\'\u0335\n\'\3\'\3\'\5\'\u0339"+
		"\n\'\3\'\3\'\5\'\u033d\n\'\3\'\3\'\5\'\u0341\n\'\3\'\3\'\5\'\u0345\n\'"+
		"\3\'\5\'\u0348\n\'\3(\3(\3(\3(\3(\3(\3(\7(\u0351\n(\f(\16(\u0354\13(\3"+
		"(\3(\5(\u0358\n(\3)\3)\3)\3)\3)\7)\u035f\n)\f)\16)\u0362\13)\3)\3)\5)"+
		"\u0366\n)\3)\3)\3)\3)\3)\3*\3*\3*\5*\u0370\n*\3*\5*\u0373\n*\3+\3+\3+"+
		"\3+\3+\3+\3+\3+\3+\5+\u037e\n+\3+\3+\3+\5+\u0383\n+\3+\3+\3+\3+\3+\3+"+
		"\3+\3+\3+\3+\3+\3+\3+\5+\u0392\n+\3+\5+\u0395\n+\3+\3+\3+\3+\3+\3+\5+"+
		"\u039d\n+\3+\3+\3+\3+\3+\6+\u03a4\n+\r+\16+\u03a5\3+\3+\5+\u03aa\n+\3"+
		"+\3+\3+\5+\u03af\n+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3"+
		"+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\5+\u03cd\n+\3+\3+\3+\3+\3+\3+\3+\3"+
		"+\3+\3+\5+\u03d9\n+\3+\3+\3+\5+\u03de\n+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3"+
		"+\5+\u03ea\n+\3+\3+\3+\3+\5+\u03f0\n+\3+\3+\3+\3+\3+\5+\u03f7\n+\3+\3"+
		"+\5+\u03fb\n+\3+\3+\3+\3+\3+\3+\7+\u0403\n+\f+\16+\u0406\13+\5+\u0408"+
		"\n+\3+\3+\3+\3+\5+\u040e\n+\3+\5+\u0411\n+\7+\u0413\n+\f+\16+\u0416\13"+
		"+\3,\3,\3-\3-\3.\3.\3/\6/\u041f\n/\r/\16/\u0420\3/\3/\3/\3/\3/\3/\3/\3"+
		"/\3/\3/\5/\u042d\n/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64"+
		"\3\65\3\65\3\66\3\66\3\67\3\67\38\38\39\39\3:\3:\3;\3;\3<\3<\3<\5<\u044a"+
		"\n<\3=\3=\3>\5>\u044f\n>\3>\3>\3?\3?\3?\3?\3?\3?\5?\u0459\n?\3?\3?\3@"+
		"\3@\3@\2\4,TA\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64"+
		"\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\2\21\4\2\7\nbb\4\2FF``\4\2\32"+
		"\32((\4\2\30\30\32^\4\2\35\35\'\'\5\2\7\b\13\13bb\3\2\t\n\4\2\5\5\f\16"+
		"\3\2\17\22\6\2\61\61>>@@MM\6\2$&EEnorr\5\2\t\n\27\27CC\3\2Y]\3\2qr\5\2"+
		"\30\30..OO\u04f3\2\u0080\3\2\2\2\4\u0087\3\2\2\2\6\u008c\3\2\2\2\b\u00ab"+
		"\3\2\2\2\n\u00b3\3\2\2\2\f\u00ba\3\2\2\2\16\u00d0\3\2\2\2\20\u0112\3\2"+
		"\2\2\22\u0114\3\2\2\2\24\u0116\3\2\2\2\26\u0121\3\2\2\2\30\u0128\3\2\2"+
		"\2\32\u0133\3\2\2\2\34\u0140\3\2\2\2\36\u014e\3\2\2\2 \u0160\3\2\2\2\""+
		"\u0163\3\2\2\2$\u0177\3\2\2\2&\u018f\3\2\2\2(\u0194\3\2\2\2*\u01b5\3\2"+
		"\2\2,\u01bf\3\2\2\2.\u01cb\3\2\2\2\60\u01cf\3\2\2\2\62\u01d1\3\2\2\2\64"+
		"\u01d4\3\2\2\2\66\u01e8\3\2\2\28\u01f6\3\2\2\2:\u0221\3\2\2\2<\u0293\3"+
		"\2\2\2>\u02a1\3\2\2\2@\u02d2\3\2\2\2B\u02d4\3\2\2\2D\u02e2\3\2\2\2F\u030d"+
		"\3\2\2\2H\u0323\3\2\2\2J\u0325\3\2\2\2L\u0347\3\2\2\2N\u0357\3\2\2\2P"+
		"\u0359\3\2\2\2R\u036c\3\2\2\2T\u03ae\3\2\2\2V\u0417\3\2\2\2X\u0419\3\2"+
		"\2\2Z\u041b\3\2\2\2\\\u041e\3\2\2\2^\u042e\3\2\2\2`\u0430\3\2\2\2b\u0432"+
		"\3\2\2\2d\u0434\3\2\2\2f\u0436\3\2\2\2h\u0438\3\2\2\2j\u043a\3\2\2\2l"+
		"\u043c\3\2\2\2n\u043e\3\2\2\2p\u0440\3\2\2\2r\u0442\3\2\2\2t\u0444\3\2"+
		"\2\2v\u0449\3\2\2\2x\u044b\3\2\2\2z\u044e\3\2\2\2|\u0452\3\2\2\2~\u045c"+
		"\3\2\2\2\u0080\u0081\5\6\4\2\u0081\u0083\5\4\3\2\u0082\u0084\5\60\31\2"+
		"\u0083\u0082\3\2\2\2\u0083\u0084\3\2\2\2\u0084\3\3\2\2\2\u0085\u0088\5"+
		"\b\5\2\u0086\u0088\5\22\n\2\u0087\u0085\3\2\2\2\u0087\u0086\3\2\2\2\u0088"+
		"\u008a\3\2\2\2\u0089\u008b\7m\2\2\u008a\u0089\3\2\2\2\u008a\u008b\3\2"+
		"\2\2\u008b\5\3\2\2\2\u008c\u008d\7X\2\2\u008d\u008e\7q\2\2\u008e\7\3\2"+
		"\2\2\u008f\u0091\5 \21\2\u0090\u008f\3\2\2\2\u0090\u0091\3\2\2\2\u0091"+
		"\u0092\3\2\2\2\u0092\u00ac\5\n\6\2\u0093\u0095\5 \21\2\u0094\u0093\3\2"+
		"\2\2\u0094\u0095\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u00ac\5\f\7\2\u0097"+
		"\u00ac\5\"\22\2\u0098\u00ac\5$\23\2\u0099\u009a\7j\2\2\u009a\u009b\5\22"+
		"\n\2\u009b\u009c\7k\2\2\u009c\u00ac\3\2\2\2\u009d\u00ac\5\16\b\2\u009e"+
		"\u00ac\5\20\t\2\u009f\u00ac\5*\26\2\u00a0\u00a2\5 \21\2\u00a1\u00a0\3"+
		"\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00ac\5&\24\2\u00a4"+
		"\u00ac\5,\27\2\u00a5\u00ac\7n\2\2\u00a6\u00ac\5x=\2\u00a7\u00a9\5 \21"+
		"\2\u00a8\u00a7\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ac"+
		"\5(\25\2\u00ab\u0090\3\2\2\2\u00ab\u0094\3\2\2\2\u00ab\u0097\3\2\2\2\u00ab"+
		"\u0098\3\2\2\2\u00ab\u0099\3\2\2\2\u00ab\u009d\3\2\2\2\u00ab\u009e\3\2"+
		"\2\2\u00ab\u009f\3\2\2\2\u00ab\u00a1\3\2\2\2\u00ab\u00a4\3\2\2\2\u00ab"+
		"\u00a5\3\2\2\2\u00ab\u00a6\3\2\2\2\u00ab\u00a8\3\2\2\2\u00ac\u00ae\3\2"+
		"\2\2\u00ad\u00af\7m\2\2\u00ae\u00ad\3\2\2\2\u00ae\u00af\3\2\2\2\u00af"+
		"\t\3\2\2\2\u00b0\u00b1\5r:\2\u00b1\u00b2\7e\2\2\u00b2\u00b4\3\2\2\2\u00b3"+
		"\u00b0\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b6\5p"+
		"9\2\u00b6\13\3\2\2\2\u00b7\u00bb\5\n\6\2\u00b8\u00bb\5&\24\2\u00b9\u00bb"+
		"\5$\23\2\u00ba\u00b7\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00b9\3\2\2\2\u00bb"+
		"\u00bc\3\2\2\2\u00bc\u00bd\7\34\2\2\u00bd\u00be\5v<\2\u00be\r\3\2\2\2"+
		"\u00bf\u00c0\7h\2\2\u00c0\u00c1\5\22\n\2\u00c1\u00c2\7i\2\2\u00c2\u00c3"+
		"\7`\2\2\u00c3\u00d1\3\2\2\2\u00c4\u00c5\7h\2\2\u00c5\u00c6\5\22\n\2\u00c6"+
		"\u00c7\7i\2\2\u00c7\u00c8\7a\2\2\u00c8\u00d1\3\2\2\2\u00c9\u00ca\7h\2"+
		"\2\u00ca\u00cb\5\22\n\2\u00cb\u00cc\7i\2\2\u00cc\u00cd\7b\2\2\u00cd\u00d1"+
		"\3\2\2\2\u00ce\u00d1\7c\2\2\u00cf\u00d1\7d\2\2\u00d0\u00bf\3\2\2\2\u00d0"+
		"\u00c4\3\2\2\2\u00d0\u00c9\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d0\u00cf\3\2"+
		"\2\2\u00d1\17\3\2\2\2\u00d2\u00d3\7h\2\2\u00d3\u00d4\5\22\n\2\u00d4\u00d5"+
		"\7i\2\2\u00d5\u00e2\7`\2\2\u00d6\u00d7\7n\2\2\u00d7\u00da\7a\2\2\u00d8"+
		"\u00d9\7n\2\2\u00d9\u00db\7b\2\2\u00da\u00d8\3\2\2\2\u00da\u00db\3\2\2"+
		"\2\u00db\u00e3\3\2\2\2\u00dc\u00dd\7n\2\2\u00dd\u00e0\7b\2\2\u00de\u00df"+
		"\7n\2\2\u00df\u00e1\7a\2\2\u00e0\u00de\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1"+
		"\u00e3\3\2\2\2\u00e2\u00d6\3\2\2\2\u00e2\u00dc\3\2\2\2\u00e3\u0113\3\2"+
		"\2\2\u00e4\u00e5\7h\2\2\u00e5\u00e6\5\22\n\2\u00e6\u00e7\7i\2\2\u00e7"+
		"\u00f4\7a\2\2\u00e8\u00e9\7n\2\2\u00e9\u00ec\7`\2\2\u00ea\u00eb\7n\2\2"+
		"\u00eb\u00ed\7b\2\2\u00ec\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00f5"+
		"\3\2\2\2\u00ee\u00ef\7n\2\2\u00ef\u00f2\7b\2\2\u00f0\u00f1\7n\2\2\u00f1"+
		"\u00f3\7`\2\2\u00f2\u00f0\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00f5\3\2"+
		"\2\2\u00f4\u00e8\3\2\2\2\u00f4\u00ee\3\2\2\2\u00f5\u0113\3\2\2\2\u00f6"+
		"\u00f7\7h\2\2\u00f7\u00f8\5\22\n\2\u00f8\u00f9\7i\2\2\u00f9\u0106\7b\2"+
		"\2\u00fa\u00fb\7n\2\2\u00fb\u00fe\7`\2\2\u00fc\u00fd\7n\2\2\u00fd\u00ff"+
		"\7a\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0107\3\2\2\2\u0100"+
		"\u0101\7n\2\2\u0101\u0104\7a\2\2\u0102\u0103\7n\2\2\u0103\u0105\7`\2\2"+
		"\u0104\u0102\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u0107\3\2\2\2\u0106\u00fa"+
		"\3\2\2\2\u0106\u0100\3\2\2\2\u0107\u0113\3\2\2\2\u0108\u0109\7h\2\2\u0109"+
		"\u010a\5\22\n\2\u010a\u010b\7i\2\2\u010b\u010c\7c\2\2\u010c\u0113\3\2"+
		"\2\2\u010d\u010e\7h\2\2\u010e\u010f\5\22\n\2\u010f\u0110\7i\2\2\u0110"+
		"\u0111\7d\2\2\u0111\u0113\3\2\2\2\u0112\u00d2\3\2\2\2\u0112\u00e4\3\2"+
		"\2\2\u0112\u00f6\3\2\2\2\u0112\u0108\3\2\2\2\u0112\u010d\3\2\2\2\u0113"+
		"\21\3\2\2\2\u0114\u0115\5\24\13\2\u0115\23\3\2\2\2\u0116\u011e\5\30\r"+
		"\2\u0117\u011a\7c\2\2\u0118\u011b\5\30\r\2\u0119\u011b\5\b\5\2\u011a\u0118"+
		"\3\2\2\2\u011a\u0119\3\2\2\2\u011b\u011d\3\2\2\2\u011c\u0117\3\2\2\2\u011d"+
		"\u0120\3\2\2\2\u011e\u011c\3\2\2\2\u011e\u011f\3\2\2\2\u011f\25\3\2\2"+
		"\2\u0120\u011e\3\2\2\2\u0121\u0124\5\b\5\2\u0122\u0123\7\3\2\2\u0123\u0125"+
		"\5\b\5\2\u0124\u0122\3\2\2\2\u0125\u0126\3\2\2\2\u0126\u0124\3\2\2\2\u0126"+
		"\u0127\3\2\2\2\u0127\27\3\2\2\2\u0128\u0130\5\32\16\2\u0129\u012c\7b\2"+
		"\2\u012a\u012d\5\32\16\2\u012b\u012d\5\b\5\2\u012c\u012a\3\2\2\2\u012c"+
		"\u012b\3\2\2\2\u012d\u012f\3\2\2\2\u012e\u0129\3\2\2\2\u012f\u0132\3\2"+
		"\2\2\u0130\u012e\3\2\2\2\u0130\u0131\3\2\2\2\u0131\31\3\2\2\2\u0132\u0130"+
		"\3\2\2\2\u0133\u013b\5\34\17\2\u0134\u0137\7a\2\2\u0135\u0138\5\34\17"+
		"\2\u0136\u0138\5\b\5\2\u0137\u0135\3\2\2\2\u0137\u0136\3\2\2\2\u0138\u013a"+
		"\3\2\2\2\u0139\u0134\3\2\2\2\u013a\u013d\3\2\2\2\u013b\u0139\3\2\2\2\u013b"+
		"\u013c\3\2\2\2\u013c\33\3\2\2\2\u013d\u013b\3\2\2\2\u013e\u0141\5\b\5"+
		"\2\u013f\u0141\5\36\20\2\u0140\u013e\3\2\2\2\u0140\u013f\3\2\2\2\u0141"+
		"\u0149\3\2\2\2\u0142\u0145\7`\2\2\u0143\u0146\5\b\5\2\u0144\u0146\5\36"+
		"\20\2\u0145\u0143\3\2\2\2\u0145\u0144\3\2\2\2\u0146\u0148\3\2\2\2\u0147"+
		"\u0142\3\2\2\2\u0148\u014b\3\2\2\2\u0149\u0147\3\2\2\2\u0149\u014a\3\2"+
		"\2\2\u014a\35\3\2\2\2\u014b\u0149\3\2\2\2\u014c\u014f\5\b\5\2\u014d\u014f"+
		"\5\26\f\2\u014e\u014c\3\2\2\2\u014e\u014d\3\2\2\2\u014f\u0157\3\2\2\2"+
		"\u0150\u0153\7_\2\2\u0151\u0154\5\b\5\2\u0152\u0154\5\26\f\2\u0153\u0151"+
		"\3\2\2\2\u0153\u0152\3\2\2\2\u0154\u0156\3\2\2\2\u0155\u0150\3\2\2\2\u0156"+
		"\u0159\3\2\2\2\u0157\u0155\3\2\2\2\u0157\u0158\3\2\2\2\u0158\37\3\2\2"+
		"\2\u0159\u0157\3\2\2\2\u015a\u015b\7f\2\2\u015b\u015c\7\35\2\2\u015c\u0161"+
		"\7g\2\2\u015d\u015e\7f\2\2\u015e\u015f\7\'\2\2\u015f\u0161\7g\2\2\u0160"+
		"\u015a\3\2\2\2\u0160\u015d\3\2\2\2\u0161!\3\2\2\2\u0162\u0164\7\4\2\2"+
		"\u0163\u0162\3\2\2\2\u0163\u0164\3\2\2\2\u0164\u0165\3\2\2\2\u0165\u0166"+
		"\5^\60\2\u0166\u0169\7f\2\2\u0167\u016a\5\b\5\2\u0168\u016a\5\22\n\2\u0169"+
		"\u0167\3\2\2\2\u0169\u0168\3\2\2\2\u016a\u0172\3\2\2\2\u016b\u016e\7`"+
		"\2\2\u016c\u016f\5\b\5\2\u016d\u016f\5\22\n\2\u016e\u016c\3\2\2\2\u016e"+
		"\u016d\3\2\2\2\u016f\u0171\3\2\2\2\u0170\u016b\3\2\2\2\u0171\u0174\3\2"+
		"\2\2\u0172\u0170\3\2\2\2\u0172\u0173\3\2\2\2\u0173\u0175\3\2\2\2\u0174"+
		"\u0172\3\2\2\2\u0175\u0176\7g\2\2\u0176#\3\2\2\2\u0177\u0178\7\5\2\2\u0178"+
		"\u0179\5^\60\2\u0179\u018a\7f\2\2\u017a\u017d\5\b\5\2\u017b\u017d\5\22"+
		"\n\2\u017c\u017a\3\2\2\2\u017c\u017b\3\2\2\2\u017d\u0185\3\2\2\2\u017e"+
		"\u0181\7`\2\2\u017f\u0182\5\b\5\2\u0180\u0182\5\22\n\2\u0181\u017f\3\2"+
		"\2\2\u0181\u0180\3\2\2\2\u0182\u0184\3\2\2\2\u0183\u017e\3\2\2\2\u0184"+
		"\u0187\3\2\2\2\u0185\u0183\3\2\2\2\u0185\u0186\3\2\2\2\u0186\u0189\3\2"+
		"\2\2\u0187\u0185\3\2\2\2\u0188\u017c\3\2\2\2\u0189\u018c\3\2\2\2\u018a"+
		"\u0188\3\2\2\2\u018a\u018b\3\2\2\2\u018b\u018d\3\2\2\2\u018c\u018a\3\2"+
		"\2\2\u018d\u018e\7g\2\2\u018e%\3\2\2\2\u018f\u0190\5`\61\2\u0190\u0191"+
		"\7h\2\2\u0191\u0192\5\n\6\2\u0192\u0193\7i\2\2\u0193\'\3\2\2\2\u0194\u0195"+
		"\5d\63\2\u0195\u0196\7h\2\2\u0196\u019b\5\n\6\2\u0197\u0198\7`\2\2\u0198"+
		"\u019a\5\n\6\2\u0199\u0197\3\2\2\2\u019a\u019d\3\2\2\2\u019b\u0199\3\2"+
		"\2\2\u019b\u019c\3\2\2\2\u019c\u019e\3\2\2\2\u019d\u019b\3\2\2\2\u019e"+
		"\u019f\7i\2\2\u019f)\3\2\2\2\u01a0\u01a1\7\64\2\2\u01a1\u01a2\7f\2\2\u01a2"+
		"\u01a3\5T+\2\u01a3\u01a4\7g\2\2\u01a4\u01a5\7Q\2\2\u01a5\u01a6\7f\2\2"+
		"\u01a6\u01a7\5\22\n\2\u01a7\u01a8\7g\2\2\u01a8\u01a9\7)\2\2\u01a9\u01aa"+
		"\7f\2\2\u01aa\u01ab\5\22\n\2\u01ab\u01ac\7g\2\2\u01ac\u01b6\3\2\2\2\u01ad"+
		"\u01ae\7f\2\2\u01ae\u01af\5T+\2\u01af\u01b0\7g\2\2\u01b0\u01b1\7_\2\2"+
		"\u01b1\u01b2\5\22\n\2\u01b2\u01b3\7\6\2\2\u01b3\u01b4\5\22\n\2\u01b4\u01b6"+
		"\3\2\2\2\u01b5\u01a0\3\2\2\2\u01b5\u01ad\3\2\2\2\u01b6+\3\2\2\2\u01b7"+
		"\u01b8\b\27\1\2\u01b8\u01b9\7f\2\2\u01b9\u01ba\5,\27\2\u01ba\u01bb\t\2"+
		"\2\2\u01bb\u01bc\5,\27\2\u01bc\u01bd\7g\2\2\u01bd\u01c0\3\2\2\2\u01be"+
		"\u01c0\5.\30\2\u01bf\u01b7\3\2\2\2\u01bf\u01be\3\2\2\2\u01c0\u01c6\3\2"+
		"\2\2\u01c1\u01c2\f\4\2\2\u01c2\u01c3\t\2\2\2\u01c3\u01c5\5,\27\5\u01c4"+
		"\u01c1\3\2\2\2\u01c5\u01c8\3\2\2\2\u01c6\u01c4\3\2\2\2\u01c6\u01c7\3\2"+
		"\2\2\u01c7-\3\2\2\2\u01c8\u01c6\3\2\2\2\u01c9\u01cc\5\n\6\2\u01ca\u01cc"+
		"\7n\2\2\u01cb\u01c9\3\2\2\2\u01cb\u01ca\3\2\2\2\u01cc/\3\2\2\2\u01cd\u01d0"+
		"\5\64\33\2\u01ce\u01d0\5\62\32\2\u01cf\u01cd\3\2\2\2\u01cf\u01ce\3\2\2"+
		"\2\u01d0\61\3\2\2\2\u01d1\u01d2\7v\2\2\u01d2\u01d3\b\32\1\2\u01d3\63\3"+
		"\2\2\2\u01d4\u01dd\5\66\34\2\u01d5\u01d7\7l\2\2\u01d6\u01d5\3\2\2\2\u01d7"+
		"\u01d8\3\2\2\2\u01d8\u01d6\3\2\2\2\u01d8\u01d9\3\2\2\2\u01d9\u01da\3\2"+
		"\2\2\u01da\u01dc\5\66\34\2\u01db\u01d6\3\2\2\2\u01dc\u01df\3\2\2\2\u01dd"+
		"\u01db\3\2\2\2\u01dd\u01de\3\2\2\2\u01de\u01e3\3\2\2\2\u01df\u01dd\3\2"+
		"\2\2\u01e0\u01e2\7l\2\2\u01e1\u01e0\3\2\2\2\u01e2\u01e5\3\2\2\2\u01e3"+
		"\u01e1\3\2\2\2\u01e3\u01e4\3\2\2\2\u01e4\65\3\2\2\2\u01e5\u01e3\3\2\2"+
		"\2\u01e6\u01e9\58\35\2\u01e7\u01e9\5D#\2\u01e8\u01e6\3\2\2\2\u01e8\u01e7"+
		"\3\2\2\2\u01e9\67\3\2\2\2\u01ea\u01ec\7W\2\2\u01eb\u01ed\7L\2\2\u01ec"+
		"\u01eb\3\2\2\2\u01ec\u01ed\3\2\2\2\u01ed\u01ee\3\2\2\2\u01ee\u01f3\5P"+
		")\2\u01ef\u01f0\7`\2\2\u01f0\u01f2\5P)\2\u01f1\u01ef\3\2\2\2\u01f2\u01f5"+
		"\3\2\2\2\u01f3\u01f1\3\2\2\2\u01f3\u01f4\3\2\2\2\u01f4\u01f7\3\2\2\2\u01f5"+
		"\u01f3\3\2\2\2\u01f6\u01ea\3\2\2\2\u01f6\u01f7\3\2\2\2\u01f7\u01f8\3\2"+
		"\2\2\u01f8\u01fe\5:\36\2\u01f9\u01fa\5H%\2\u01fa\u01fb\5:\36\2\u01fb\u01fd"+
		"\3\2\2\2\u01fc\u01f9\3\2\2\2\u01fd\u0200\3\2\2\2\u01fe\u01fc\3\2\2\2\u01fe"+
		"\u01ff\3\2\2\2\u01ff\u020b\3\2\2\2\u0200\u01fe\3\2\2\2\u0201\u0202\7I"+
		"\2\2\u0202\u0203\7\37\2\2\u0203\u0208\5R*\2\u0204\u0205\7`\2\2\u0205\u0207"+
		"\5R*\2\u0206\u0204\3\2\2\2\u0207\u020a\3\2\2\2\u0208\u0206\3\2\2\2\u0208"+
		"\u0209\3\2\2\2\u0209\u020c\3\2\2\2\u020a\u0208\3\2\2\2\u020b\u0201\3\2"+
		"\2\2\u020b\u020c\3\2\2\2\u020c\u0213\3\2\2\2\u020d\u020e\7?\2\2\u020e"+
		"\u0211\5T+\2\u020f\u0210\t\3\2\2\u0210\u0212\5T+\2\u0211\u020f\3\2\2\2"+
		"\u0211\u0212\3\2\2\2\u0212\u0214\3\2\2\2\u0213\u020d\3\2\2\2\u0213\u0214"+
		"\3\2\2\2\u02149\3\2\2\2\u0215\u0217\7P\2\2\u0216\u0218\t\4\2\2\u0217\u0216"+
		"\3\2\2\2\u0217\u0218\3\2\2\2\u0218\u0219\3\2\2\2\u0219\u021e\5> \2\u021a"+
		"\u021b\7`\2\2\u021b\u021d\5> \2\u021c\u021a\3\2\2\2\u021d\u0220\3\2\2"+
		"\2\u021e\u021c\3\2\2\2\u021e\u021f\3\2\2\2\u021f\u0222\3\2\2\2\u0220\u021e"+
		"\3\2\2\2\u0221\u0215\3\2\2\2\u0221\u0222\3\2\2\2\u0222\u0223\3\2\2\2\u0223"+
		"\u022d\7\60\2\2\u0224\u0229\5@!\2\u0225\u0226\7`\2\2\u0226\u0228\5@!\2"+
		"\u0227\u0225\3\2\2\2\u0228\u022b\3\2\2\2\u0229\u0227\3\2\2\2\u0229\u022a"+
		"\3\2\2\2\u022a\u022e\3\2\2\2\u022b\u0229\3\2\2\2\u022c\u022e\5J&\2\u022d"+
		"\u0224\3\2\2\2\u022d\u022c\3\2\2\2\u022e\u0230\3\2\2\2\u022f\u0231\5<"+
		"\37\2\u0230\u022f\3\2\2\2\u0230\u0231\3\2\2\2\u0231;\3\2\2\2\u0232\u0233"+
		"\7V\2\2\u0233\u0234\5T+\2\u0234\u0243\3\2\2\2\u0235\u0236\7\62\2\2\u0236"+
		"\u0237\7\37\2\2\u0237\u023c\5T+\2\u0238\u0239\7`\2\2\u0239\u023b\5T+\2"+
		"\u023a\u0238\3\2\2\2\u023b\u023e\3\2\2\2\u023c\u023a\3\2\2\2\u023c\u023d"+
		"\3\2\2\2\u023d\u0241\3\2\2\2\u023e\u023c\3\2\2\2\u023f\u0240\7\63\2\2"+
		"\u0240\u0242\5T+\2\u0241\u023f\3\2\2\2\u0241\u0242\3\2\2\2\u0242\u0244"+
		"\3\2\2\2\u0243\u0235\3\2\2\2\u0243\u0244\3\2\2\2\u0244\u0262\3\2\2\2\u0245"+
		"\u0246\7T\2\2\u0246\u0247\7f\2\2\u0247\u024c\5T+\2\u0248\u0249\7`\2\2"+
		"\u0249\u024b\5T+\2\u024a\u0248\3\2\2\2\u024b\u024e\3\2\2\2\u024c\u024a"+
		"\3\2\2\2\u024c\u024d\3\2\2\2\u024d\u024f\3\2\2\2\u024e\u024c\3\2\2\2\u024f"+
		"\u025e\7g\2\2\u0250\u0251\7`\2\2\u0251\u0252\7f\2\2\u0252\u0257\5T+\2"+
		"\u0253\u0254\7`\2\2\u0254\u0256\5T+\2\u0255\u0253\3\2\2\2\u0256\u0259"+
		"\3\2\2\2\u0257\u0255\3\2\2\2\u0257\u0258\3\2\2\2\u0258\u025a\3\2\2\2\u0259"+
		"\u0257\3\2\2\2\u025a\u025b\7g\2\2\u025b\u025d\3\2\2\2\u025c\u0250\3\2"+
		"\2\2\u025d\u0260\3\2\2\2\u025e\u025c\3\2\2\2\u025e\u025f\3\2\2\2\u025f"+
		"\u0262\3\2\2\2\u0260\u025e\3\2\2\2\u0261\u0232\3\2\2\2\u0261\u0245\3\2"+
		"\2\2\u0262\u0294\3\2\2\2\u0263\u0264\7V\2\2\u0264\u0266\5T+\2\u0265\u0263"+
		"\3\2\2\2\u0265\u0266\3\2\2\2\u0266\u0267\3\2\2\2\u0267\u0268\7\62\2\2"+
		"\u0268\u0269\7\37\2\2\u0269\u026e\5T+\2\u026a\u026b\7`\2\2\u026b\u026d"+
		"\5T+\2\u026c\u026a\3\2\2\2\u026d\u0270\3\2\2\2\u026e\u026c\3\2\2\2\u026e"+
		"\u026f\3\2\2\2\u026f\u0273\3\2\2\2\u0270\u026e\3\2\2\2\u0271\u0272\7\63"+
		"\2\2\u0272\u0274\5T+\2\u0273\u0271\3\2\2\2\u0273\u0274\3\2\2\2\u0274\u0292"+
		"\3\2\2\2\u0275\u0276\7T\2\2\u0276\u0277\7f\2\2\u0277\u027c\5T+\2\u0278"+
		"\u0279\7`\2\2\u0279\u027b\5T+\2\u027a\u0278\3\2\2\2\u027b\u027e\3\2\2"+
		"\2\u027c\u027a\3\2\2\2\u027c\u027d\3\2\2\2\u027d\u027f\3\2\2\2\u027e\u027c"+
		"\3\2\2\2\u027f\u028e\7g\2\2\u0280\u0281\7`\2\2\u0281\u0282\7f\2\2\u0282"+
		"\u0287\5T+\2\u0283\u0284\7`\2\2\u0284\u0286\5T+\2\u0285\u0283\3\2\2\2"+
		"\u0286\u0289\3\2\2\2\u0287\u0285\3\2\2\2\u0287\u0288\3\2\2\2\u0288\u028a"+
		"\3\2\2\2\u0289\u0287\3\2\2\2\u028a\u028b\7g\2\2\u028b\u028d\3\2\2\2\u028c"+
		"\u0280\3\2\2\2\u028d\u0290\3\2\2\2\u028e\u028c\3\2\2\2\u028e\u028f\3\2"+
		"\2\2\u028f\u0292\3\2\2\2\u0290\u028e\3\2\2\2\u0291\u0265\3\2\2\2\u0291"+
		"\u0275\3\2\2\2\u0292\u0294\3\2\2\2\u0293\u0261\3\2\2\2\u0293\u0291\3\2"+
		"\2\2\u0294=\3\2\2\2\u0295\u02a2\7\7\2\2\u0296\u0297\5l\67\2\u0297\u0298"+
		"\7e\2\2\u0298\u0299\7\7\2\2\u0299\u02a2\3\2\2\2\u029a\u029f\5T+\2\u029b"+
		"\u029d\7\34\2\2\u029c\u029b\3\2\2\2\u029c\u029d\3\2\2\2\u029d\u029e\3"+
		"\2\2\2\u029e\u02a0\5n8\2\u029f\u029c\3\2\2\2\u029f\u02a0\3\2\2\2\u02a0"+
		"\u02a2\3\2\2\2\u02a1\u0295\3\2\2\2\u02a1\u0296\3\2\2\2\u02a1\u029a\3\2"+
		"\2\2\u02a2?\3\2\2\2\u02a3\u02a4\5j\66\2\u02a4\u02a5\7e\2\2\u02a5\u02a7"+
		"\3\2\2\2\u02a6\u02a3\3\2\2\2\u02a6\u02a7\3\2\2\2\u02a7\u02a8\3\2\2\2\u02a8"+
		"\u02ad\5l\67\2\u02a9\u02ab\7\34\2\2\u02aa\u02a9\3\2\2\2\u02aa\u02ab\3"+
		"\2\2\2\u02ab\u02ac\3\2\2\2\u02ac\u02ae\5r:\2\u02ad\u02aa\3\2\2\2\u02ad"+
		"\u02ae\3\2\2\2\u02ae\u02b4\3\2\2\2\u02af\u02b0\7\67\2\2\u02b0\u02b1\7"+
		"\37\2\2\u02b1\u02b5\5t;\2\u02b2\u02b3\7C\2\2\u02b3\u02b5\7\67\2\2\u02b4"+
		"\u02af\3\2\2\2\u02b4\u02b2\3\2\2\2\u02b4\u02b5\3\2\2\2\u02b5\u02d3\3\2"+
		"\2\2\u02b6\u02c0\7f\2\2\u02b7\u02bc\5@!\2\u02b8\u02b9\7`\2\2\u02b9\u02bb"+
		"\5@!\2\u02ba\u02b8\3\2\2\2\u02bb\u02be\3\2\2\2\u02bc\u02ba\3\2\2\2\u02bc"+
		"\u02bd\3\2\2\2\u02bd\u02c1\3\2\2\2\u02be\u02bc\3\2\2\2\u02bf\u02c1\5J"+
		"&\2\u02c0\u02b7\3\2\2\2\u02c0\u02bf\3\2\2\2\u02c1\u02c2\3\2\2\2\u02c2"+
		"\u02c7\7g\2\2\u02c3\u02c5\7\34\2\2\u02c4\u02c3\3\2\2\2\u02c4\u02c5\3\2"+
		"\2\2\u02c5\u02c6\3\2\2\2\u02c6\u02c8\5r:\2\u02c7\u02c4\3\2\2\2\u02c7\u02c8"+
		"\3\2\2\2\u02c8\u02d3\3\2\2\2\u02c9\u02ca\7f\2\2\u02ca\u02cb\5D#\2\u02cb"+
		"\u02d0\7g\2\2\u02cc\u02ce\7\34\2\2\u02cd\u02cc\3\2\2\2\u02cd\u02ce\3\2"+
		"\2\2\u02ce\u02cf\3\2\2\2\u02cf\u02d1\5r:\2\u02d0\u02cd\3\2\2\2\u02d0\u02d1"+
		"\3\2\2\2\u02d1\u02d3\3\2\2\2\u02d2\u02a6\3\2\2\2\u02d2\u02b6\3\2\2\2\u02d2"+
		"\u02c9\3\2\2\2\u02d3A\3\2\2\2\u02d4\u02d5\t\5\2\2\u02d5C\3\2\2\2\u02d6"+
		"\u02d8\7W\2\2\u02d7\u02d9\7L\2\2\u02d8\u02d7\3\2\2\2\u02d8\u02d9\3\2\2"+
		"\2\u02d9\u02da\3\2\2\2\u02da\u02df\5P)\2\u02db\u02dc\7`\2\2\u02dc\u02de"+
		"\5P)\2\u02dd\u02db\3\2\2\2\u02de\u02e1\3\2\2\2\u02df\u02dd\3\2\2\2\u02df"+
		"\u02e0\3\2\2\2\u02e0\u02e3\3\2\2\2\u02e1\u02df\3\2\2\2\u02e2\u02d6\3\2"+
		"\2\2\u02e2\u02e3\3\2\2\2\u02e3\u02e4\3\2\2\2\u02e4\u02ea\5F$\2\u02e5\u02e6"+
		"\5H%\2\u02e6\u02e7\5F$\2\u02e7\u02e9\3\2\2\2\u02e8\u02e5\3\2\2\2\u02e9"+
		"\u02ec\3\2\2\2\u02ea\u02e8\3\2\2\2\u02ea\u02eb\3\2\2\2\u02eb\u02f7\3\2"+
		"\2\2\u02ec\u02ea\3\2\2\2\u02ed\u02ee\7I\2\2\u02ee\u02ef\7\37\2\2\u02ef"+
		"\u02f4\5R*\2\u02f0\u02f1\7`\2\2\u02f1\u02f3\5R*\2\u02f2\u02f0\3\2\2\2"+
		"\u02f3\u02f6\3\2\2\2\u02f4\u02f2\3\2\2\2\u02f4\u02f5\3\2\2\2\u02f5\u02f8"+
		"\3\2\2\2\u02f6\u02f4\3\2\2\2\u02f7\u02ed\3\2\2\2\u02f7\u02f8\3\2\2\2\u02f8"+
		"\u02ff\3\2\2\2\u02f9\u02fa\7?\2\2\u02fa\u02fd\5T+\2\u02fb\u02fc\t\3\2"+
		"\2\u02fc\u02fe\5T+\2\u02fd\u02fb\3\2\2\2\u02fd\u02fe\3\2\2\2\u02fe\u0300"+
		"\3\2\2\2\u02ff\u02f9\3\2\2\2\u02ff\u0300\3\2\2\2\u0300E\3\2\2\2\u0301"+
		"\u0303\7P\2\2\u0302\u0304\t\4\2\2\u0303\u0302\3\2\2\2\u0303\u0304\3\2"+
		"\2\2\u0304\u0305\3\2\2\2\u0305\u030a\5> \2\u0306\u0307\7`\2\2\u0307\u0309"+
		"\5> \2\u0308\u0306\3\2\2\2\u0309\u030c\3\2\2\2\u030a\u0308\3\2\2\2\u030a"+
		"\u030b\3\2\2\2\u030b\u030e\3\2\2\2\u030c\u030a\3\2\2\2\u030d\u0301\3\2"+
		"\2\2\u030d\u030e\3\2\2\2\u030e\u030f\3\2\2\2\u030f\u0319\7\60\2\2\u0310"+
		"\u0315\5@!\2\u0311\u0312\7`\2\2\u0312\u0314\5@!\2\u0313\u0311\3\2\2\2"+
		"\u0314\u0317\3\2\2\2\u0315\u0313\3\2\2\2\u0315\u0316\3\2\2\2\u0316\u031a"+
		"\3\2\2\2\u0317\u0315\3\2\2\2\u0318\u031a\5J&\2\u0319\u0310\3\2\2\2\u0319"+
		"\u0318\3\2\2\2\u031a\u031c\3\2\2\2\u031b\u031d\5<\37\2\u031c\u031b\3\2"+
		"\2\2\u031c\u031d\3\2\2\2\u031dG\3\2\2\2\u031e\u0324\7R\2\2\u031f\u0320"+
		"\7R\2\2\u0320\u0324\7\32\2\2\u0321\u0324\79\2\2\u0322\u0324\7,\2\2\u0323"+
		"\u031e\3\2\2\2\u0323\u031f\3\2\2\2\u0323\u0321\3\2\2\2\u0323\u0322\3\2"+
		"\2\2\u0324I\3\2\2\2\u0325\u032f\5@!\2\u0326\u0329\5L\'\2\u0327\u032a\5"+
		"J&\2\u0328\u032a\5@!\2\u0329\u0327\3\2\2\2\u0329\u0328\3\2\2\2\u032a\u032b"+
		"\3\2\2\2\u032b\u032c\5N(\2\u032c\u032e\3\2\2\2\u032d\u0326\3\2\2\2\u032e"+
		"\u0331\3\2\2\2\u032f\u032d\3\2\2\2\u032f\u0330\3\2\2\2\u0330K\3\2\2\2"+
		"\u0331\u032f\3\2\2\2\u0332\u0348\7`\2\2\u0333\u0335\7A\2\2\u0334\u0333"+
		"\3\2\2\2\u0334\u0335\3\2\2\2\u0335\u0344\3\2\2\2\u0336\u0338\7=\2\2\u0337"+
		"\u0339\7J\2\2\u0338\u0337\3\2\2\2\u0338\u0339\3\2\2\2\u0339\u0345\3\2"+
		"\2\2\u033a\u033c\7N\2\2\u033b\u033d\7J\2\2\u033c\u033b\3\2\2\2\u033c\u033d"+
		"\3\2\2\2\u033d\u0345\3\2\2\2\u033e\u0340\7/\2\2\u033f\u0341\7J\2\2\u0340"+
		"\u033f\3\2\2\2\u0340\u0341\3\2\2\2\u0341\u0345\3\2\2\2\u0342\u0345\78"+
		"\2\2\u0343\u0345\7#\2\2\u0344\u0336\3\2\2\2\u0344\u033a\3\2\2\2\u0344"+
		"\u033e\3\2\2\2\u0344\u0342\3\2\2\2\u0344\u0343\3\2\2\2\u0344\u0345\3\2"+
		"\2\2\u0345\u0346\3\2\2\2\u0346\u0348\7<\2\2\u0347\u0332\3\2\2\2\u0347"+
		"\u0334\3\2\2\2\u0348M\3\2\2\2\u0349\u034a\7G\2\2\u034a\u0358\5T+\2\u034b"+
		"\u034c\7S\2\2\u034c\u034d\7f\2\2\u034d\u0352\5p9\2\u034e\u034f\7`\2\2"+
		"\u034f\u0351\5p9\2\u0350\u034e\3\2\2\2\u0351\u0354\3\2\2\2\u0352\u0350"+
		"\3\2\2\2\u0352\u0353\3\2\2\2\u0353\u0355\3\2\2\2\u0354\u0352\3\2\2\2\u0355"+
		"\u0356\7g\2\2\u0356\u0358\3\2\2\2\u0357\u0349\3\2\2\2\u0357\u034b\3\2"+
		"\2\2\u0357\u0358\3\2\2\2\u0358O\3\2\2\2\u0359\u0365\5l\67\2\u035a\u035b"+
		"\7f\2\2\u035b\u0360\5p9\2\u035c\u035d\7`\2\2\u035d\u035f\5p9\2\u035e\u035c"+
		"\3\2\2\2\u035f\u0362\3\2\2\2\u0360\u035e\3\2\2\2\u0360\u0361\3\2\2\2\u0361"+
		"\u0363\3\2\2\2\u0362\u0360\3\2\2\2\u0363\u0364\7g\2\2\u0364\u0366\3\2"+
		"\2\2\u0365\u035a\3\2\2\2\u0365\u0366\3\2\2\2\u0366\u0367\3\2\2\2\u0367"+
		"\u0368\7\34\2\2\u0368\u0369\7f\2\2\u0369\u036a\5D#\2\u036a\u036b\7g\2"+
		"\2\u036bQ\3\2\2\2\u036c\u036f\5T+\2\u036d\u036e\7\"\2\2\u036e\u0370\5"+
		"h\65\2\u036f\u036d\3\2\2\2\u036f\u0370\3\2\2\2\u0370\u0372\3\2\2\2\u0371"+
		"\u0373\t\6\2\2\u0372\u0371\3\2\2\2\u0372\u0373\3\2\2\2\u0373S\3\2\2\2"+
		"\u0374\u0375\b+\1\2\u0375\u0376\5X-\2\u0376\u0377\5T+\26\u0377\u03af\3"+
		"\2\2\2\u0378\u03af\5\b\5\2\u0379\u03af\7p\2\2\u037a\u037b\5j\66\2\u037b"+
		"\u037c\7e\2\2\u037c\u037e\3\2\2\2\u037d\u037a\3\2\2\2\u037d\u037e\3\2"+
		"\2\2\u037e\u037f\3\2\2\2\u037f\u0380\5r:\2\u0380\u0381\7e\2\2\u0381\u0383"+
		"\3\2\2\2\u0382\u037d\3\2\2\2\u0382\u0383\3\2\2\2\u0383\u0384\3\2\2\2\u0384"+
		"\u03af\5p9\2\u0385\u0386\7f\2\2\u0386\u0387\5T+\2\u0387\u0388\7g\2\2\u0388"+
		"\u03af\3\2\2\2\u0389\u038a\7!\2\2\u038a\u038b\7f\2\2\u038b\u038c\5T+\2"+
		"\u038c\u038d\7\34\2\2\u038d\u038e\5\\/\2\u038e\u038f\7g\2\2\u038f\u03af"+
		"\3\2\2\2\u0390\u0392\7C\2\2\u0391\u0390\3\2\2\2\u0391\u0392\3\2\2\2\u0392"+
		"\u0393\3\2\2\2\u0393\u0395\7-\2\2\u0394\u0391\3\2\2\2\u0394\u0395\3\2"+
		"\2\2\u0395\u0396\3\2\2\2\u0396\u0397\7f\2\2\u0397\u0398\5D#\2\u0398\u0399"+
		"\7g\2\2\u0399\u03af\3\2\2\2\u039a\u039c\7 \2\2\u039b\u039d\5T+\2\u039c"+
		"\u039b\3\2\2\2\u039c\u039d\3\2\2\2\u039d\u03a3\3\2\2\2\u039e\u039f\7U"+
		"\2\2\u039f\u03a0\5T+\2\u03a0\u03a1\7Q\2\2\u03a1\u03a2\5T+\2\u03a2\u03a4"+
		"\3\2\2\2\u03a3\u039e\3\2\2\2\u03a4\u03a5\3\2\2\2\u03a5\u03a3\3\2\2\2\u03a5"+
		"\u03a6\3\2\2\2\u03a6\u03a9\3\2\2\2\u03a7\u03a8\7)\2\2\u03a8\u03aa\5T+"+
		"\2\u03a9\u03a7\3\2\2\2\u03a9\u03aa\3\2\2\2\u03aa\u03ab\3\2\2\2\u03ab\u03ac"+
		"\7*\2\2\u03ac\u03af\3\2\2\2\u03ad\u03af\5|?\2\u03ae\u0374\3\2\2\2\u03ae"+
		"\u0378\3\2\2\2\u03ae\u0379\3\2\2\2\u03ae\u0382\3\2\2\2\u03ae\u0385\3\2"+
		"\2\2\u03ae\u0389\3\2\2\2\u03ae\u0394\3\2\2\2\u03ae\u039a\3\2\2\2\u03ae"+
		"\u03ad\3\2\2\2\u03af\u0414\3\2\2\2\u03b0\u03b1\f\25\2\2\u03b1\u03b2\7"+
		"\3\2\2\u03b2\u0413\5T+\26\u03b3\u03b4\f\24\2\2\u03b4\u03b5\t\7\2\2\u03b5"+
		"\u0413\5T+\25\u03b6\u03b7\f\23\2\2\u03b7\u03b8\t\b\2\2\u03b8\u0413\5T"+
		"+\24\u03b9\u03ba\f\22\2\2\u03ba\u03bb\t\t\2\2\u03bb\u0413\5T+\23\u03bc"+
		"\u03bd\f\21\2\2\u03bd\u03be\t\n\2\2\u03be\u0413\5T+\22\u03bf\u03cc\f\20"+
		"\2\2\u03c0\u03cd\7\23\2\2\u03c1\u03cd\7\24\2\2\u03c2\u03cd\7\25\2\2\u03c3"+
		"\u03cd\7\26\2\2\u03c4\u03cd\7:\2\2\u03c5\u03c6\7:\2\2\u03c6\u03cd\7C\2"+
		"\2\u03c7\u03cd\7\66\2\2\u03c8\u03cd\7>\2\2\u03c9\u03cd\7\61\2\2\u03ca"+
		"\u03cd\7@\2\2\u03cb\u03cd\7M\2\2\u03cc\u03c0\3\2\2\2\u03cc\u03c1\3\2\2"+
		"\2\u03cc\u03c2\3\2\2\2\u03cc\u03c3\3\2\2\2\u03cc\u03c4\3\2\2\2\u03cc\u03c5"+
		"\3\2\2\2\u03cc\u03c7\3\2\2\2\u03cc\u03c8\3\2\2\2\u03cc\u03c9\3\2\2\2\u03cc"+
		"\u03ca\3\2\2\2\u03cc\u03cb\3\2\2\2\u03cd\u03ce\3\2\2\2\u03ce\u0413\5T"+
		"+\21\u03cf\u03d0\f\17\2\2\u03d0\u03d1\7\33\2\2\u03d1\u0413\5T+\20\u03d2"+
		"\u03d3\f\16\2\2\u03d3\u03d4\7H\2\2\u03d4\u0413\5T+\17\u03d5\u03d6\f\b"+
		"\2\2\u03d6\u03d8\7:\2\2\u03d7\u03d9\7C\2\2\u03d8\u03d7\3\2\2\2\u03d8\u03d9"+
		"\3\2\2\2\u03d9\u03da\3\2\2\2\u03da\u0413\5T+\t\u03db\u03dd\f\7\2\2\u03dc"+
		"\u03de\7C\2\2\u03dd\u03dc\3\2\2\2\u03dd\u03de\3\2\2\2\u03de\u03df\3\2"+
		"\2\2\u03df\u03e0\7\36\2\2\u03e0\u03e1\5T+\2\u03e1\u03e2\7\33\2\2\u03e2"+
		"\u03e3\5T+\b\u03e3\u0413\3\2\2\2\u03e4\u03e5\f\13\2\2\u03e5\u03e6\7\""+
		"\2\2\u03e6\u0413\5h\65\2\u03e7\u03e9\f\n\2\2\u03e8\u03ea\7C\2\2\u03e9"+
		"\u03e8\3\2\2\2\u03e9\u03ea\3\2\2\2\u03ea\u03eb\3\2\2\2\u03eb\u03ec\t\13"+
		"\2\2\u03ec\u03ef\5T+\2\u03ed\u03ee\7+\2\2\u03ee\u03f0\5T+\2\u03ef\u03ed"+
		"\3\2\2\2\u03ef\u03f0\3\2\2\2\u03f0\u0413\3\2\2\2\u03f1\u03f6\f\t\2\2\u03f2"+
		"\u03f7\7;\2\2\u03f3\u03f7\7D\2\2\u03f4\u03f5\7C\2\2\u03f5\u03f7\7E\2\2"+
		"\u03f6\u03f2\3\2\2\2\u03f6\u03f3\3\2\2\2\u03f6\u03f4\3\2\2\2\u03f7\u0413"+
		"\3\2\2\2\u03f8\u03fa\f\6\2\2\u03f9\u03fb\7C\2\2\u03fa\u03f9\3\2\2\2\u03fa"+
		"\u03fb\3\2\2\2\u03fb\u03fc\3\2\2\2\u03fc\u0410\7\66\2\2\u03fd\u0407\7"+
		"f\2\2\u03fe\u0408\5D#\2\u03ff\u0404\5T+\2\u0400\u0401\7`\2\2\u0401\u0403"+
		"\5T+\2\u0402\u0400\3\2\2\2\u0403\u0406\3\2\2\2\u0404\u0402\3\2\2\2\u0404"+
		"\u0405\3\2\2\2\u0405\u0408\3\2\2\2\u0406\u0404\3\2\2\2\u0407\u03fe\3\2"+
		"\2\2\u0407\u03ff\3\2\2\2\u0407\u0408\3\2\2\2\u0408\u0409\3\2\2\2\u0409"+
		"\u0411\7g\2\2\u040a\u040b\5j\66\2\u040b\u040c\7e\2\2\u040c\u040e\3\2\2"+
		"\2\u040d\u040a\3\2\2\2\u040d\u040e\3\2\2\2\u040e\u040f\3\2\2\2\u040f\u0411"+
		"\5l\67\2\u0410\u03fd\3\2\2\2\u0410\u040d\3\2\2\2\u0411\u0413\3\2\2\2\u0412"+
		"\u03b0\3\2\2\2\u0412\u03b3\3\2\2\2\u0412\u03b6\3\2\2\2\u0412\u03b9\3\2"+
		"\2\2\u0412\u03bc\3\2\2\2\u0412\u03bf\3\2\2\2\u0412\u03cf\3\2\2\2\u0412"+
		"\u03d2\3\2\2\2\u0412\u03d5\3\2\2\2\u0412\u03db\3\2\2\2\u0412\u03e4\3\2"+
		"\2\2\u0412\u03e7\3\2\2\2\u0412\u03f1\3\2\2\2\u0412\u03f8\3\2\2\2\u0413"+
		"\u0416\3\2\2\2\u0414\u0412\3\2\2\2\u0414\u0415\3\2\2\2\u0415U\3\2\2\2"+
		"\u0416\u0414\3\2\2\2\u0417\u0418\t\f\2\2\u0418W\3\2\2\2\u0419\u041a\t"+
		"\r\2\2\u041aY\3\2\2\2\u041b\u041c\5v<\2\u041c[\3\2\2\2\u041d\u041f\5Z"+
		".\2\u041e\u041d\3\2\2\2\u041f\u0420\3\2\2\2\u0420\u041e\3\2\2\2\u0420"+
		"\u0421\3\2\2\2\u0421\u042c\3\2\2\2\u0422\u0423\7f\2\2\u0423\u0424\5z>"+
		"\2\u0424\u0425\7g\2\2\u0425\u042d\3\2\2\2\u0426\u0427\7f\2\2\u0427\u0428"+
		"\5z>\2\u0428\u0429\7`\2\2\u0429\u042a\5z>\2\u042a\u042b\7g\2\2\u042b\u042d"+
		"\3\2\2\2\u042c\u0422\3\2\2\2\u042c\u0426\3\2\2\2\u042c\u042d\3\2\2\2\u042d"+
		"]\3\2\2\2\u042e\u042f\5v<\2\u042f_\3\2\2\2\u0430\u0431\5b\62\2\u0431a"+
		"\3\2\2\2\u0432\u0433\t\16\2\2\u0433c\3\2\2\2\u0434\u0435\5f\64\2\u0435"+
		"e\3\2\2\2\u0436\u0437\7^\2\2\u0437g\3\2\2\2\u0438\u0439\5v<\2\u0439i\3"+
		"\2\2\2\u043a\u043b\5v<\2\u043bk\3\2\2\2\u043c\u043d\5v<\2\u043dm\3\2\2"+
		"\2\u043e\u043f\t\17\2\2\u043fo\3\2\2\2\u0440\u0441\5v<\2\u0441q\3\2\2"+
		"\2\u0442\u0443\5v<\2\u0443s\3\2\2\2\u0444\u0445\5v<\2\u0445u\3\2\2\2\u0446"+
		"\u044a\5B\"\2\u0447\u044a\7q\2\2\u0448\u044a\7r\2\2\u0449\u0446\3\2\2"+
		"\2\u0449\u0447\3\2\2\2\u0449\u0448\3\2\2\2\u044aw\3\2\2\2\u044b\u044c"+
		"\7r\2\2\u044cy\3\2\2\2\u044d\u044f\t\b\2\2\u044e\u044d\3\2\2\2\u044e\u044f"+
		"\3\2\2\2\u044f\u0450\3\2\2\2\u0450\u0451\7n\2\2\u0451{\3\2\2\2\u0452\u0453"+
		"\7K\2\2\u0453\u0458\7f\2\2\u0454\u0459\7\65\2\2\u0455\u0456\t\20\2\2\u0456"+
		"\u0457\7`\2\2\u0457\u0459\5~@\2\u0458\u0454\3\2\2\2\u0458\u0455\3\2\2"+
		"\2\u0459\u045a\3\2\2\2\u045a\u045b\7g\2\2\u045b}\3\2\2\2\u045c\u045d\7"+
		"r\2\2\u045d\177\3\2\2\2\u009a\u0083\u0087\u008a\u0090\u0094\u00a1\u00a8"+
		"\u00ab\u00ae\u00b3\u00ba\u00d0\u00da\u00e0\u00e2\u00ec\u00f2\u00f4\u00fe"+
		"\u0104\u0106\u0112\u011a\u011e\u0126\u012c\u0130\u0137\u013b\u0140\u0145"+
		"\u0149\u014e\u0153\u0157\u0160\u0163\u0169\u016e\u0172\u017c\u0181\u0185"+
		"\u018a\u019b\u01b5\u01bf\u01c6\u01cb\u01cf\u01d8\u01dd\u01e3\u01e8\u01ec"+
		"\u01f3\u01f6\u01fe\u0208\u020b\u0211\u0213\u0217\u021e\u0221\u0229\u022d"+
		"\u0230\u023c\u0241\u0243\u024c\u0257\u025e\u0261\u0265\u026e\u0273\u027c"+
		"\u0287\u028e\u0291\u0293\u029c\u029f\u02a1\u02a6\u02aa\u02ad\u02b4\u02bc"+
		"\u02c0\u02c4\u02c7\u02cd\u02d0\u02d2\u02d8\u02df\u02e2\u02ea\u02f4\u02f7"+
		"\u02fd\u02ff\u0303\u030a\u030d\u0315\u0319\u031c\u0323\u0329\u032f\u0334"+
		"\u0338\u033c\u0340\u0344\u0347\u0352\u0357\u0360\u0365\u036f\u0372\u037d"+
		"\u0382\u0391\u0394\u039c\u03a5\u03a9\u03ae\u03cc\u03d8\u03dd\u03e9\u03ef"+
		"\u03f6\u03fa\u0404\u0407\u040d\u0410\u0412\u0414\u0420\u042c\u0449\u044e"+
		"\u0458";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}