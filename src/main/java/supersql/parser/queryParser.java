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
		T__17=18, T__18=19, T__19=20, K_ABORT=21, K_ADD=22, K_ALL=23, K_AND=24, 
		K_AS=25, K_ASC=26, K_BETWEEN=27, K_BY=28, K_CASE=29, K_CAST=30, K_COLLATE=31, 
		K_CROSS=32, K_CURRENT_DATE=33, K_CURRENT_TIME=34, K_CURRENT_TIMESTAMP=35, 
		K_DESC=36, K_DISTINCT=37, K_ELSE=38, K_END=39, K_ESCAPE=40, K_EXCEPT=41, 
		K_EXISTS=42, K_FAIL=43, K_FULL=44, K_FROM=45, K_GLOB=46, K_GROUP=47, K_HAVING=48, 
		K_IF=49, K_IGNORE=50, K_IN=51, K_INDEXED=52, K_INNER=53, K_INTERSECT=54, 
		K_IS=55, K_ISNULL=56, K_JOIN=57, K_LEFT=58, K_LIKE=59, K_LIMIT=60, K_MATCH=61, 
		K_NATURAL=62, K_NO=63, K_NOT=64, K_NOTNULL=65, K_NULL=66, K_OFFSET=67, 
		K_ON=68, K_OR=69, K_ORDER=70, K_OUTER=71, K_RAISE=72, K_RECURSIVE=73, 
		K_REGEXP=74, K_RIGHT=75, K_ROLLBACK=76, K_SELECT=77, K_THEN=78, K_UNION=79, 
		K_USING=80, K_VALUES=81, K_WHEN=82, K_WHERE=83, K_WITH=84, K_GENERATE=85, 
		K_MAX=86, K_MIN=87, K_AVG=88, K_COUNT=89, K_SUM=90, K_GGPLOT=91, C0=92, 
		C1=93, C2=94, C3=95, C4=96, CIRCLE=97, DOT=98, OPEN_PARENTHESE=99, CLOSE_PARENTHESE=100, 
		OPEN_BRACKET=101, CLOSE_BRACKET=102, OPEN_BRACE=103, CLOSE_BRACE=104, 
		SEMICOLON=105, COLON=106, DECORATOR=107, NUMERIC_LITERAL=108, BLOB_LITERAL=109, 
		BIND_PARAMETER=110, IDENTIFIER=111, STRING_LITERAL=112, MULTI_LINE_COMMENT=113, 
		SINGLE_LINE_COMMENT=114, WS=115, UNEXPECTED_CHAR=116;
	public static final int
		RULE_query = 0, RULE_root = 1, RULE_media = 2, RULE_operand = 3, RULE_attribute = 4, 
		RULE_as_pair = 5, RULE_grouper = 6, RULE_composite_iterator = 7, RULE_exp = 8, 
		RULE_t_exp = 9, RULE_concat_exp = 10, RULE_d_exp = 11, RULE_v_exp = 12, 
		RULE_h_exp = 13, RULE_n_exp = 14, RULE_cast = 15, RULE_sorting = 16, RULE_function = 17, 
		RULE_sqlfunc = 18, RULE_sqlprocess = 19, RULE_aggregate = 20, RULE_ggplot = 21, 
		RULE_if_then_else = 22, RULE_arithmetics = 23, RULE_arith = 24, RULE_from_where = 25, 
		RULE_error = 26, RULE_sql_stmt_list = 27, RULE_sql_stmt = 28, RULE_factored_select_stmt = 29, 
		RULE_select_core = 30, RULE_where_clause = 31, RULE_result_column = 32, 
		RULE_table_or_subquery = 33, RULE_keyword = 34, RULE_select_stmt = 35, 
		RULE_select_or_values = 36, RULE_compound_operator = 37, RULE_join_clause = 38, 
		RULE_join_operator = 39, RULE_join_constraint = 40, RULE_common_table_expression = 41, 
		RULE_ordering_term = 42, RULE_expr = 43, RULE_literal_value = 44, RULE_unary_operator = 45, 
		RULE_name = 46, RULE_type_name = 47, RULE_function_name = 48, RULE_ag_function_name = 49, 
		RULE_ag_keyword = 50, RULE_gg_function_name = 51, RULE_gg_keyword = 52, 
		RULE_collation_name = 53, RULE_database_name = 54, RULE_table_name = 55, 
		RULE_column_alias = 56, RULE_column_name = 57, RULE_table_alias = 58, 
		RULE_index_name = 59, RULE_any_name = 60, RULE_sl = 61, RULE_signed_number = 62, 
		RULE_raise_function = 63, RULE_error_message = 64;
	public static final String[] ruleNames = {
		"query", "root", "media", "operand", "attribute", "as_pair", "grouper", 
		"composite_iterator", "exp", "t_exp", "concat_exp", "d_exp", "v_exp", 
		"h_exp", "n_exp", "cast", "sorting", "function", "sqlfunc", "sqlprocess", 
		"aggregate", "ggplot", "if_then_else", "arithmetics", "arith", "from_where", 
		"error", "sql_stmt_list", "sql_stmt", "factored_select_stmt", "select_core", 
		"where_clause", "result_column", "table_or_subquery", "keyword", "select_stmt", 
		"select_or_values", "compound_operator", "join_clause", "join_operator", 
		"join_constraint", "common_table_expression", "ordering_term", "expr", 
		"literal_value", "unary_operator", "name", "type_name", "function_name", 
		"ag_function_name", "ag_keyword", "gg_function_name", "gg_keyword", "collation_name", 
		"database_name", "table_name", "column_alias", "column_name", "table_alias", 
		"index_name", "any_name", "sl", "signed_number", "raise_function", "error_message"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'||'", "'$'", "'&'", "'*'", "'/'", "'+'", "'-'", "'\\'", "'<<'", 
		"'>>'", "'|'", "'<'", "'<='", "'>'", "'>='", "'='", "'=='", "'!='", "'<>'", 
		"'~'", null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		"'?'", "','", "'!'", "'%'", "'#'", "'◯'", "'.'", "'('", "')'", "'['", 
		"']'", "'{'", "'}'", "';'", "':'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, "K_ABORT", "K_ADD", 
		"K_ALL", "K_AND", "K_AS", "K_ASC", "K_BETWEEN", "K_BY", "K_CASE", "K_CAST", 
		"K_COLLATE", "K_CROSS", "K_CURRENT_DATE", "K_CURRENT_TIME", "K_CURRENT_TIMESTAMP", 
		"K_DESC", "K_DISTINCT", "K_ELSE", "K_END", "K_ESCAPE", "K_EXCEPT", "K_EXISTS", 
		"K_FAIL", "K_FULL", "K_FROM", "K_GLOB", "K_GROUP", "K_HAVING", "K_IF", 
		"K_IGNORE", "K_IN", "K_INDEXED", "K_INNER", "K_INTERSECT", "K_IS", "K_ISNULL", 
		"K_JOIN", "K_LEFT", "K_LIKE", "K_LIMIT", "K_MATCH", "K_NATURAL", "K_NO", 
		"K_NOT", "K_NOTNULL", "K_NULL", "K_OFFSET", "K_ON", "K_OR", "K_ORDER", 
		"K_OUTER", "K_RAISE", "K_RECURSIVE", "K_REGEXP", "K_RIGHT", "K_ROLLBACK", 
		"K_SELECT", "K_THEN", "K_UNION", "K_USING", "K_VALUES", "K_WHEN", "K_WHERE", 
		"K_WITH", "K_GENERATE", "K_MAX", "K_MIN", "K_AVG", "K_COUNT", "K_SUM", 
		"K_GGPLOT", "C0", "C1", "C2", "C3", "C4", "CIRCLE", "DOT", "OPEN_PARENTHESE", 
		"CLOSE_PARENTHESE", "OPEN_BRACKET", "CLOSE_BRACKET", "OPEN_BRACE", "CLOSE_BRACE", 
		"SEMICOLON", "COLON", "DECORATOR", "NUMERIC_LITERAL", "BLOB_LITERAL", 
		"BIND_PARAMETER", "IDENTIFIER", "STRING_LITERAL", "MULTI_LINE_COMMENT", 
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
			setState(130);
			media();
			setState(131);
			root();
			setState(133);
			_la = _input.LA(1);
			if (_la==K_FROM || ((((_la - 77)) & ~0x3f) == 0 && ((1L << (_la - 77)) & ((1L << (K_SELECT - 77)) | (1L << (K_WITH - 77)) | (1L << (UNEXPECTED_CHAR - 77)))) != 0)) {
				{
				setState(132);
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
			setState(137);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(135);
				operand();
				}
				break;
			case 2:
				{
				setState(136);
				exp();
				}
				break;
			}
			setState(140);
			_la = _input.LA(1);
			if (_la==DECORATOR) {
				{
				setState(139);
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
			setState(142);
			match(K_GENERATE);
			setState(143);
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
		public SqlprocessContext sqlprocess() {
			return getRuleContext(SqlprocessContext.class,0);
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
			setState(174);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
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
				attribute();
				}
				break;
			case 2:
				{
				setState(150);
				_la = _input.LA(1);
				if (_la==OPEN_PARENTHESE) {
					{
					setState(149);
					sorting();
					}
				}

				setState(152);
				as_pair();
				}
				break;
			case 3:
				{
				setState(153);
				function();
				}
				break;
			case 4:
				{
				setState(154);
				sqlfunc();
				}
				break;
			case 5:
				{
				setState(155);
				match(OPEN_BRACE);
				setState(156);
				exp();
				setState(157);
				match(CLOSE_BRACE);
				}
				break;
			case 6:
				{
				setState(159);
				grouper();
				}
				break;
			case 7:
				{
				setState(160);
				composite_iterator();
				}
				break;
			case 8:
				{
				setState(161);
				if_then_else();
				}
				break;
			case 9:
				{
				setState(163);
				_la = _input.LA(1);
				if (_la==OPEN_PARENTHESE) {
					{
					setState(162);
					sorting();
					}
				}

				setState(165);
				aggregate();
				}
				break;
			case 10:
				{
				setState(166);
				arithmetics(0);
				}
				break;
			case 11:
				{
				setState(167);
				match(NUMERIC_LITERAL);
				}
				break;
			case 12:
				{
				setState(168);
				sl();
				}
				break;
			case 13:
				{
				setState(170);
				_la = _input.LA(1);
				if (_la==OPEN_PARENTHESE) {
					{
					setState(169);
					sorting();
					}
				}

				setState(172);
				ggplot();
				}
				break;
			case 14:
				{
				setState(173);
				sqlprocess();
				}
				break;
			}
			setState(177);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(176);
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
			setState(182);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(179);
				table_alias();
				setState(180);
				match(DOT);
				}
				break;
			}
			setState(184);
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
			setState(189);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(186);
				attribute();
				}
				break;
			case 2:
				{
				setState(187);
				aggregate();
				}
				break;
			case 3:
				{
				setState(188);
				sqlfunc();
				}
				break;
			}
			setState(191);
			match(K_AS);
			{
			setState(192);
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
			setState(211);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(194);
				match(OPEN_BRACKET);
				setState(195);
				exp();
				setState(196);
				match(CLOSE_BRACKET);
				setState(197);
				match(C1);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(199);
				match(OPEN_BRACKET);
				setState(200);
				exp();
				setState(201);
				match(CLOSE_BRACKET);
				setState(202);
				match(C2);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(204);
				match(OPEN_BRACKET);
				setState(205);
				exp();
				setState(206);
				match(CLOSE_BRACKET);
				setState(207);
				match(C3);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(209);
				match(C4);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(210);
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
			setState(277);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(213);
				match(OPEN_BRACKET);
				setState(214);
				exp();
				setState(215);
				match(CLOSE_BRACKET);
				setState(216);
				match(C1);
				setState(229);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(217);
					match(NUMERIC_LITERAL);
					setState(218);
					match(C2);
					setState(221);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						setState(219);
						match(NUMERIC_LITERAL);
						setState(220);
						match(C3);
						}
						break;
					}
					}
					break;
				case 2:
					{
					setState(223);
					match(NUMERIC_LITERAL);
					setState(224);
					match(C3);
					setState(227);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						setState(225);
						match(NUMERIC_LITERAL);
						setState(226);
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
				setState(231);
				match(OPEN_BRACKET);
				setState(232);
				exp();
				setState(233);
				match(CLOSE_BRACKET);
				setState(234);
				match(C2);
				setState(247);
				switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
				case 1:
					{
					setState(235);
					match(NUMERIC_LITERAL);
					setState(236);
					match(C1);
					setState(239);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						setState(237);
						match(NUMERIC_LITERAL);
						setState(238);
						match(C3);
						}
						break;
					}
					}
					break;
				case 2:
					{
					setState(241);
					match(NUMERIC_LITERAL);
					setState(242);
					match(C3);
					setState(245);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						setState(243);
						match(NUMERIC_LITERAL);
						setState(244);
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
				setState(249);
				match(OPEN_BRACKET);
				setState(250);
				exp();
				setState(251);
				match(CLOSE_BRACKET);
				setState(252);
				match(C3);
				setState(265);
				switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
				case 1:
					{
					setState(253);
					match(NUMERIC_LITERAL);
					setState(254);
					match(C1);
					setState(257);
					switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
					case 1:
						{
						setState(255);
						match(NUMERIC_LITERAL);
						setState(256);
						match(C2);
						}
						break;
					}
					}
					break;
				case 2:
					{
					setState(259);
					match(NUMERIC_LITERAL);
					setState(260);
					match(C2);
					setState(263);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
					case 1:
						{
						setState(261);
						match(NUMERIC_LITERAL);
						setState(262);
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
				setState(267);
				match(OPEN_BRACKET);
				setState(268);
				exp();
				setState(269);
				match(CLOSE_BRACKET);
				setState(270);
				match(C4);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(272);
				match(OPEN_BRACKET);
				setState(273);
				exp();
				setState(274);
				match(CLOSE_BRACKET);
				setState(275);
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
			setState(279);
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
			setState(281);
			d_exp();
			setState(289);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(282);
					match(C4);
					setState(285);
					switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
					case 1:
						{
						setState(283);
						d_exp();
						}
						break;
					case 2:
						{
						setState(284);
						operand();
						}
						break;
					}
					}
					} 
				}
				setState(291);
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
			setState(292);
			operand();
			}
			setState(295); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(293);
					match(T__0);
					{
					setState(294);
					operand();
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(297); 
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
			setState(299);
			v_exp();
			setState(307);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(300);
					match(C3);
					setState(303);
					switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
					case 1:
						{
						setState(301);
						v_exp();
						}
						break;
					case 2:
						{
						setState(302);
						operand();
						}
						break;
					}
					}
					} 
				}
				setState(309);
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
			setState(310);
			h_exp();
			setState(318);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(311);
					match(C2);
					setState(314);
					switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
					case 1:
						{
						setState(312);
						h_exp();
						}
						break;
					case 2:
						{
						setState(313);
						operand();
						}
						break;
					}
					}
					} 
				}
				setState(320);
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
			setState(323);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
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
			setState(332);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(325);
					match(C1);
					setState(328);
					switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
					case 1:
						{
						setState(326);
						operand();
						}
						break;
					case 2:
						{
						setState(327);
						n_exp();
						}
						break;
					}
					}
					} 
				}
				setState(334);
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
			setState(337);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
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
			setState(346);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(339);
					match(C0);
					setState(342);
					switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
					case 1:
						{
						setState(340);
						operand();
						}
						break;
					case 2:
						{
						setState(341);
						concat_exp();
						}
						break;
					}
					}
					} 
				}
				setState(348);
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

	public static class CastContext extends ParserRuleContext {
		public List<TerminalNode> COLON() { return getTokens(queryParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(queryParser.COLON, i);
		}
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public CastContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cast; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterCast(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitCast(this);
		}
	}

	public final CastContext cast() throws RecognitionException {
		CastContext _localctx = new CastContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_cast);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(349);
			match(COLON);
			setState(350);
			match(COLON);
			setState(351);
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
		enterRule(_localctx, 32, RULE_sorting);
		try {
			setState(359);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(353);
				match(OPEN_PARENTHESE);
				setState(354);
				match(K_ASC);
				setState(355);
				match(CLOSE_PARENTHESE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(356);
				match(OPEN_PARENTHESE);
				setState(357);
				match(K_DESC);
				setState(358);
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
		enterRule(_localctx, 34, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(362);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(361);
				match(T__1);
				}
			}

			setState(364);
			function_name();
			setState(365);
			match(OPEN_PARENTHESE);
			{
			setState(380);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << K_ABORT) | (1L << K_ALL) | (1L << K_AND) | (1L << K_AS) | (1L << K_ASC) | (1L << K_BETWEEN) | (1L << K_BY) | (1L << K_CASE) | (1L << K_CAST) | (1L << K_COLLATE) | (1L << K_CROSS) | (1L << K_CURRENT_DATE) | (1L << K_CURRENT_TIME) | (1L << K_CURRENT_TIMESTAMP) | (1L << K_DESC) | (1L << K_DISTINCT) | (1L << K_ELSE) | (1L << K_END) | (1L << K_ESCAPE) | (1L << K_EXCEPT) | (1L << K_EXISTS) | (1L << K_FAIL) | (1L << K_FULL) | (1L << K_FROM) | (1L << K_GLOB) | (1L << K_GROUP) | (1L << K_HAVING) | (1L << K_IF) | (1L << K_IGNORE) | (1L << K_IN) | (1L << K_INDEXED) | (1L << K_INNER) | (1L << K_INTERSECT) | (1L << K_IS) | (1L << K_ISNULL) | (1L << K_JOIN) | (1L << K_LEFT) | (1L << K_LIKE) | (1L << K_LIMIT) | (1L << K_MATCH) | (1L << K_NATURAL) | (1L << K_NO))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (K_NOT - 64)) | (1L << (K_NOTNULL - 64)) | (1L << (K_NULL - 64)) | (1L << (K_OFFSET - 64)) | (1L << (K_ON - 64)) | (1L << (K_OR - 64)) | (1L << (K_ORDER - 64)) | (1L << (K_OUTER - 64)) | (1L << (K_RAISE - 64)) | (1L << (K_RECURSIVE - 64)) | (1L << (K_REGEXP - 64)) | (1L << (K_RIGHT - 64)) | (1L << (K_ROLLBACK - 64)) | (1L << (K_SELECT - 64)) | (1L << (K_THEN - 64)) | (1L << (K_UNION - 64)) | (1L << (K_USING - 64)) | (1L << (K_VALUES - 64)) | (1L << (K_WHEN - 64)) | (1L << (K_WHERE - 64)) | (1L << (K_WITH - 64)) | (1L << (K_GENERATE - 64)) | (1L << (K_MAX - 64)) | (1L << (K_MIN - 64)) | (1L << (K_AVG - 64)) | (1L << (K_COUNT - 64)) | (1L << (K_SUM - 64)) | (1L << (K_GGPLOT - 64)) | (1L << (C4 - 64)) | (1L << (CIRCLE - 64)) | (1L << (OPEN_PARENTHESE - 64)) | (1L << (OPEN_BRACKET - 64)) | (1L << (OPEN_BRACE - 64)) | (1L << (NUMERIC_LITERAL - 64)) | (1L << (IDENTIFIER - 64)) | (1L << (STRING_LITERAL - 64)))) != 0)) {
				{
				setState(368);
				switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
				case 1:
					{
					setState(366);
					operand();
					}
					break;
				case 2:
					{
					setState(367);
					exp();
					}
					break;
				}
				setState(377);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(370);
					match(C1);
					setState(373);
					switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
					case 1:
						{
						setState(371);
						operand();
						}
						break;
					case 2:
						{
						setState(372);
						exp();
						}
						break;
					}
					}
					}
					setState(379);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
			setState(382);
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
		enterRule(_localctx, 36, RULE_sqlfunc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(384);
			match(T__2);
			setState(385);
			function_name();
			setState(386);
			match(OPEN_PARENTHESE);
			setState(403);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << K_ABORT) | (1L << K_ALL) | (1L << K_AND) | (1L << K_AS) | (1L << K_ASC) | (1L << K_BETWEEN) | (1L << K_BY) | (1L << K_CASE) | (1L << K_CAST) | (1L << K_COLLATE) | (1L << K_CROSS) | (1L << K_CURRENT_DATE) | (1L << K_CURRENT_TIME) | (1L << K_CURRENT_TIMESTAMP) | (1L << K_DESC) | (1L << K_DISTINCT) | (1L << K_ELSE) | (1L << K_END) | (1L << K_ESCAPE) | (1L << K_EXCEPT) | (1L << K_EXISTS) | (1L << K_FAIL) | (1L << K_FULL) | (1L << K_FROM) | (1L << K_GLOB) | (1L << K_GROUP) | (1L << K_HAVING) | (1L << K_IF) | (1L << K_IGNORE) | (1L << K_IN) | (1L << K_INDEXED) | (1L << K_INNER) | (1L << K_INTERSECT) | (1L << K_IS) | (1L << K_ISNULL) | (1L << K_JOIN) | (1L << K_LEFT) | (1L << K_LIKE) | (1L << K_LIMIT) | (1L << K_MATCH) | (1L << K_NATURAL) | (1L << K_NO))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (K_NOT - 64)) | (1L << (K_NOTNULL - 64)) | (1L << (K_NULL - 64)) | (1L << (K_OFFSET - 64)) | (1L << (K_ON - 64)) | (1L << (K_OR - 64)) | (1L << (K_ORDER - 64)) | (1L << (K_OUTER - 64)) | (1L << (K_RAISE - 64)) | (1L << (K_RECURSIVE - 64)) | (1L << (K_REGEXP - 64)) | (1L << (K_RIGHT - 64)) | (1L << (K_ROLLBACK - 64)) | (1L << (K_SELECT - 64)) | (1L << (K_THEN - 64)) | (1L << (K_UNION - 64)) | (1L << (K_USING - 64)) | (1L << (K_VALUES - 64)) | (1L << (K_WHEN - 64)) | (1L << (K_WHERE - 64)) | (1L << (K_WITH - 64)) | (1L << (K_GENERATE - 64)) | (1L << (K_MAX - 64)) | (1L << (K_MIN - 64)) | (1L << (K_AVG - 64)) | (1L << (K_COUNT - 64)) | (1L << (K_SUM - 64)) | (1L << (K_GGPLOT - 64)) | (1L << (C4 - 64)) | (1L << (CIRCLE - 64)) | (1L << (OPEN_PARENTHESE - 64)) | (1L << (OPEN_BRACKET - 64)) | (1L << (OPEN_BRACE - 64)) | (1L << (NUMERIC_LITERAL - 64)) | (1L << (IDENTIFIER - 64)) | (1L << (STRING_LITERAL - 64)))) != 0)) {
				{
				{
				setState(389);
				switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
				case 1:
					{
					setState(387);
					operand();
					}
					break;
				case 2:
					{
					setState(388);
					exp();
					}
					break;
				}
				setState(398);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(391);
					match(C1);
					setState(394);
					switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
					case 1:
						{
						setState(392);
						operand();
						}
						break;
					case 2:
						{
						setState(393);
						exp();
						}
						break;
					}
					}
					}
					setState(400);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(405);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(406);
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

	public static class SqlprocessContext extends ParserRuleContext {
		public TerminalNode OPEN_PARENTHESE() { return getToken(queryParser.OPEN_PARENTHESE, 0); }
		public TerminalNode CLOSE_PARENTHESE() { return getToken(queryParser.CLOSE_PARENTHESE, 0); }
		public SqlprocessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sqlprocess; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterSqlprocess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitSqlprocess(this);
		}
	}

	public final SqlprocessContext sqlprocess() throws RecognitionException {
		SqlprocessContext _localctx = new SqlprocessContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_sqlprocess);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(408);
			match(T__2);
			setState(409);
			match(OPEN_PARENTHESE);
			setState(413);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(410);
					matchWildcard();
					}
					} 
				}
				setState(415);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
			}
			setState(416);
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
		enterRule(_localctx, 40, RULE_aggregate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(418);
			ag_function_name();
			setState(419);
			match(OPEN_BRACKET);
			setState(420);
			attribute();
			setState(421);
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
		public List<SortingContext> sorting() {
			return getRuleContexts(SortingContext.class);
		}
		public SortingContext sorting(int i) {
			return getRuleContext(SortingContext.class,i);
		}
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
		enterRule(_localctx, 42, RULE_ggplot);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(423);
			gg_function_name();
			setState(424);
			match(OPEN_BRACKET);
			setState(426);
			_la = _input.LA(1);
			if (_la==OPEN_PARENTHESE) {
				{
				setState(425);
				sorting();
				}
			}

			setState(428);
			attribute();
			setState(436);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==C1) {
				{
				{
				setState(429);
				match(C1);
				setState(431);
				_la = _input.LA(1);
				if (_la==OPEN_PARENTHESE) {
					{
					setState(430);
					sorting();
					}
				}

				setState(433);
				attribute();
				}
				}
				setState(438);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(439);
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
		enterRule(_localctx, 44, RULE_if_then_else);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(462);
			switch (_input.LA(1)) {
			case K_IF:
				{
				{
				setState(441);
				match(K_IF);
				setState(442);
				match(OPEN_PARENTHESE);
				setState(443);
				expr(0);
				setState(444);
				match(CLOSE_PARENTHESE);
				setState(445);
				match(K_THEN);
				setState(446);
				match(OPEN_PARENTHESE);
				setState(447);
				exp();
				setState(448);
				match(CLOSE_PARENTHESE);
				setState(449);
				match(K_ELSE);
				setState(450);
				match(OPEN_PARENTHESE);
				setState(451);
				exp();
				setState(452);
				match(CLOSE_PARENTHESE);
				}
				}
				break;
			case OPEN_PARENTHESE:
				{
				{
				setState(454);
				match(OPEN_PARENTHESE);
				setState(455);
				expr(0);
				setState(456);
				match(CLOSE_PARENTHESE);
				setState(457);
				match(C0);
				setState(458);
				exp();
				setState(459);
				match(COLON);
				setState(460);
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
		int _startState = 46;
		enterRecursionRule(_localctx, 46, RULE_arithmetics, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(472);
			switch (_input.LA(1)) {
			case OPEN_PARENTHESE:
				{
				setState(465);
				match(OPEN_PARENTHESE);
				setState(466);
				arithmetics(0);
				setState(467);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6))) != 0) || _la==C3) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(468);
				arithmetics(0);
				setState(469);
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
				setState(471);
				arith();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(479);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ArithmeticsContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_arithmetics);
					setState(474);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(475);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6))) != 0) || _la==C3) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					setState(476);
					arithmetics(3);
					}
					} 
				}
				setState(481);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
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
		enterRule(_localctx, 48, RULE_arith);
		try {
			setState(484);
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
				setState(482);
				attribute();
				}
				break;
			case NUMERIC_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(483);
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
		enterRule(_localctx, 50, RULE_from_where);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(488);
			switch (_input.LA(1)) {
			case K_FROM:
			case K_SELECT:
			case K_WITH:
				{
				setState(486);
				sql_stmt_list();
				}
				break;
			case UNEXPECTED_CHAR:
				{
				setState(487);
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
		enterRule(_localctx, 52, RULE_error);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(490);
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
		enterRule(_localctx, 54, RULE_sql_stmt_list);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(493);
			sql_stmt();
			setState(502);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(495); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(494);
						match(SEMICOLON);
						}
						}
						setState(497); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==SEMICOLON );
					setState(499);
					sql_stmt();
					}
					} 
				}
				setState(504);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
			}
			setState(508);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMICOLON) {
				{
				{
				setState(505);
				match(SEMICOLON);
				}
				}
				setState(510);
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
		enterRule(_localctx, 56, RULE_sql_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(513);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
			case 1:
				{
				setState(511);
				factored_select_stmt();
				}
				break;
			case 2:
				{
				setState(512);
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
		enterRule(_localctx, 58, RULE_factored_select_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(527);
			_la = _input.LA(1);
			if (_la==K_WITH) {
				{
				setState(515);
				match(K_WITH);
				setState(517);
				switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
				case 1:
					{
					setState(516);
					match(K_RECURSIVE);
					}
					break;
				}
				setState(519);
				common_table_expression();
				setState(524);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(520);
					match(C1);
					setState(521);
					common_table_expression();
					}
					}
					setState(526);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(529);
			select_core();
			setState(535);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 41)) & ~0x3f) == 0 && ((1L << (_la - 41)) & ((1L << (K_EXCEPT - 41)) | (1L << (K_INTERSECT - 41)) | (1L << (K_UNION - 41)))) != 0)) {
				{
				{
				setState(530);
				compound_operator();
				setState(531);
				select_core();
				}
				}
				setState(537);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(548);
			_la = _input.LA(1);
			if (_la==K_ORDER) {
				{
				setState(538);
				match(K_ORDER);
				setState(539);
				match(K_BY);
				setState(540);
				ordering_term();
				setState(545);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(541);
					match(C1);
					setState(542);
					ordering_term();
					}
					}
					setState(547);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(556);
			_la = _input.LA(1);
			if (_la==K_LIMIT) {
				{
				setState(550);
				match(K_LIMIT);
				setState(551);
				expr(0);
				setState(554);
				_la = _input.LA(1);
				if (_la==K_OFFSET || _la==C1) {
					{
					setState(552);
					_la = _input.LA(1);
					if ( !(_la==K_OFFSET || _la==C1) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					setState(553);
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
		enterRule(_localctx, 60, RULE_select_core);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(570);
			_la = _input.LA(1);
			if (_la==K_SELECT) {
				{
				setState(558);
				match(K_SELECT);
				setState(560);
				switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
				case 1:
					{
					setState(559);
					_la = _input.LA(1);
					if ( !(_la==K_ALL || _la==K_DISTINCT) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					break;
				}
				setState(562);
				result_column();
				setState(567);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(563);
					match(C1);
					setState(564);
					result_column();
					}
					}
					setState(569);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			{
			setState(572);
			match(K_FROM);
			setState(582);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
			case 1:
				{
				setState(573);
				table_or_subquery();
				setState(578);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(574);
					match(C1);
					setState(575);
					table_or_subquery();
					}
					}
					setState(580);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				{
				setState(581);
				join_clause();
				}
				break;
			}
			}
			setState(585);
			_la = _input.LA(1);
			if (((((_la - 47)) & ~0x3f) == 0 && ((1L << (_la - 47)) & ((1L << (K_GROUP - 47)) | (1L << (K_VALUES - 47)) | (1L << (K_WHERE - 47)))) != 0)) {
				{
				setState(584);
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
		enterRule(_localctx, 62, RULE_where_clause);
		int _la;
		try {
			setState(684);
			switch ( getInterpreter().adaptivePredict(_input,86,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(634);
				switch (_input.LA(1)) {
				case K_WHERE:
					{
					{
					setState(587);
					match(K_WHERE);
					setState(588);
					expr(0);
					}
					setState(604);
					_la = _input.LA(1);
					if (_la==K_GROUP) {
						{
						setState(590);
						match(K_GROUP);
						setState(591);
						match(K_BY);
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
						setState(602);
						_la = _input.LA(1);
						if (_la==K_HAVING) {
							{
							setState(600);
							match(K_HAVING);
							setState(601);
							expr(0);
							}
						}

						}
					}

					}
					break;
				case K_VALUES:
					{
					setState(606);
					match(K_VALUES);
					setState(607);
					match(OPEN_PARENTHESE);
					setState(608);
					expr(0);
					setState(613);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==C1) {
						{
						{
						setState(609);
						match(C1);
						setState(610);
						expr(0);
						}
						}
						setState(615);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(616);
					match(CLOSE_PARENTHESE);
					setState(631);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==C1) {
						{
						{
						setState(617);
						match(C1);
						setState(618);
						match(OPEN_PARENTHESE);
						setState(619);
						expr(0);
						setState(624);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==C1) {
							{
							{
							setState(620);
							match(C1);
							setState(621);
							expr(0);
							}
							}
							setState(626);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(627);
						match(CLOSE_PARENTHESE);
						}
						}
						setState(633);
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
				setState(682);
				switch (_input.LA(1)) {
				case K_GROUP:
				case K_WHERE:
					{
					setState(638);
					_la = _input.LA(1);
					if (_la==K_WHERE) {
						{
						setState(636);
						match(K_WHERE);
						setState(637);
						expr(0);
						}
					}

					{
					setState(640);
					match(K_GROUP);
					setState(641);
					match(K_BY);
					setState(642);
					expr(0);
					setState(647);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==C1) {
						{
						{
						setState(643);
						match(C1);
						setState(644);
						expr(0);
						}
						}
						setState(649);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(652);
					_la = _input.LA(1);
					if (_la==K_HAVING) {
						{
						setState(650);
						match(K_HAVING);
						setState(651);
						expr(0);
						}
					}

					}
					}
					break;
				case K_VALUES:
					{
					setState(654);
					match(K_VALUES);
					setState(655);
					match(OPEN_PARENTHESE);
					setState(656);
					expr(0);
					setState(661);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==C1) {
						{
						{
						setState(657);
						match(C1);
						setState(658);
						expr(0);
						}
						}
						setState(663);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(664);
					match(CLOSE_PARENTHESE);
					setState(679);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==C1) {
						{
						{
						setState(665);
						match(C1);
						setState(666);
						match(OPEN_PARENTHESE);
						setState(667);
						expr(0);
						setState(672);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==C1) {
							{
							{
							setState(668);
							match(C1);
							setState(669);
							expr(0);
							}
							}
							setState(674);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(675);
						match(CLOSE_PARENTHESE);
						}
						}
						setState(681);
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
		enterRule(_localctx, 64, RULE_result_column);
		int _la;
		try {
			setState(698);
			switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(686);
				match(T__3);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(687);
				table_name();
				setState(688);
				match(DOT);
				setState(689);
				match(T__3);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(691);
				expr(0);
				setState(696);
				_la = _input.LA(1);
				if (_la==K_AS || _la==IDENTIFIER || _la==STRING_LITERAL) {
					{
					setState(693);
					_la = _input.LA(1);
					if (_la==K_AS) {
						{
						setState(692);
						match(K_AS);
						}
					}

					setState(695);
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
		enterRule(_localctx, 66, RULE_table_or_subquery);
		int _la;
		try {
			setState(747);
			switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(703);
				switch ( getInterpreter().adaptivePredict(_input,90,_ctx) ) {
				case 1:
					{
					setState(700);
					database_name();
					setState(701);
					match(DOT);
					}
					break;
				}
				setState(705);
				table_name();
				setState(710);
				switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
				case 1:
					{
					setState(707);
					switch ( getInterpreter().adaptivePredict(_input,91,_ctx) ) {
					case 1:
						{
						setState(706);
						match(K_AS);
						}
						break;
					}
					setState(709);
					table_alias();
					}
					break;
				}
				setState(717);
				switch (_input.LA(1)) {
				case K_INDEXED:
					{
					setState(712);
					match(K_INDEXED);
					setState(713);
					match(K_BY);
					setState(714);
					index_name();
					}
					break;
				case K_NOT:
					{
					setState(715);
					match(K_NOT);
					setState(716);
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
				setState(719);
				match(OPEN_PARENTHESE);
				setState(729);
				switch ( getInterpreter().adaptivePredict(_input,95,_ctx) ) {
				case 1:
					{
					setState(720);
					table_or_subquery();
					setState(725);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==C1) {
						{
						{
						setState(721);
						match(C1);
						setState(722);
						table_or_subquery();
						}
						}
						setState(727);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				case 2:
					{
					setState(728);
					join_clause();
					}
					break;
				}
				setState(731);
				match(CLOSE_PARENTHESE);
				setState(736);
				switch ( getInterpreter().adaptivePredict(_input,97,_ctx) ) {
				case 1:
					{
					setState(733);
					switch ( getInterpreter().adaptivePredict(_input,96,_ctx) ) {
					case 1:
						{
						setState(732);
						match(K_AS);
						}
						break;
					}
					setState(735);
					table_alias();
					}
					break;
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(738);
				match(OPEN_PARENTHESE);
				setState(739);
				select_stmt();
				setState(740);
				match(CLOSE_PARENTHESE);
				setState(745);
				switch ( getInterpreter().adaptivePredict(_input,99,_ctx) ) {
				case 1:
					{
					setState(742);
					switch ( getInterpreter().adaptivePredict(_input,98,_ctx) ) {
					case 1:
						{
						setState(741);
						match(K_AS);
						}
						break;
					}
					setState(744);
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
		enterRule(_localctx, 68, RULE_keyword);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(749);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_ABORT) | (1L << K_ALL) | (1L << K_AND) | (1L << K_AS) | (1L << K_ASC) | (1L << K_BETWEEN) | (1L << K_BY) | (1L << K_CASE) | (1L << K_CAST) | (1L << K_COLLATE) | (1L << K_CROSS) | (1L << K_CURRENT_DATE) | (1L << K_CURRENT_TIME) | (1L << K_CURRENT_TIMESTAMP) | (1L << K_DESC) | (1L << K_DISTINCT) | (1L << K_ELSE) | (1L << K_END) | (1L << K_ESCAPE) | (1L << K_EXCEPT) | (1L << K_EXISTS) | (1L << K_FAIL) | (1L << K_FULL) | (1L << K_FROM) | (1L << K_GLOB) | (1L << K_GROUP) | (1L << K_HAVING) | (1L << K_IF) | (1L << K_IGNORE) | (1L << K_IN) | (1L << K_INDEXED) | (1L << K_INNER) | (1L << K_INTERSECT) | (1L << K_IS) | (1L << K_ISNULL) | (1L << K_JOIN) | (1L << K_LEFT) | (1L << K_LIKE) | (1L << K_LIMIT) | (1L << K_MATCH) | (1L << K_NATURAL) | (1L << K_NO))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (K_NOT - 64)) | (1L << (K_NOTNULL - 64)) | (1L << (K_NULL - 64)) | (1L << (K_OFFSET - 64)) | (1L << (K_ON - 64)) | (1L << (K_OR - 64)) | (1L << (K_ORDER - 64)) | (1L << (K_OUTER - 64)) | (1L << (K_RAISE - 64)) | (1L << (K_RECURSIVE - 64)) | (1L << (K_REGEXP - 64)) | (1L << (K_RIGHT - 64)) | (1L << (K_ROLLBACK - 64)) | (1L << (K_SELECT - 64)) | (1L << (K_THEN - 64)) | (1L << (K_UNION - 64)) | (1L << (K_USING - 64)) | (1L << (K_VALUES - 64)) | (1L << (K_WHEN - 64)) | (1L << (K_WHERE - 64)) | (1L << (K_WITH - 64)) | (1L << (K_GENERATE - 64)) | (1L << (K_MAX - 64)) | (1L << (K_MIN - 64)) | (1L << (K_AVG - 64)) | (1L << (K_COUNT - 64)) | (1L << (K_SUM - 64)) | (1L << (K_GGPLOT - 64)))) != 0)) ) {
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
		enterRule(_localctx, 70, RULE_select_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(763);
			_la = _input.LA(1);
			if (_la==K_WITH) {
				{
				setState(751);
				match(K_WITH);
				setState(753);
				switch ( getInterpreter().adaptivePredict(_input,101,_ctx) ) {
				case 1:
					{
					setState(752);
					match(K_RECURSIVE);
					}
					break;
				}
				setState(755);
				common_table_expression();
				setState(760);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(756);
					match(C1);
					setState(757);
					common_table_expression();
					}
					}
					setState(762);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(765);
			select_or_values();
			setState(771);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 41)) & ~0x3f) == 0 && ((1L << (_la - 41)) & ((1L << (K_EXCEPT - 41)) | (1L << (K_INTERSECT - 41)) | (1L << (K_UNION - 41)))) != 0)) {
				{
				{
				setState(766);
				compound_operator();
				setState(767);
				select_or_values();
				}
				}
				setState(773);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(784);
			_la = _input.LA(1);
			if (_la==K_ORDER) {
				{
				setState(774);
				match(K_ORDER);
				setState(775);
				match(K_BY);
				setState(776);
				ordering_term();
				setState(781);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(777);
					match(C1);
					setState(778);
					ordering_term();
					}
					}
					setState(783);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(792);
			_la = _input.LA(1);
			if (_la==K_LIMIT) {
				{
				setState(786);
				match(K_LIMIT);
				setState(787);
				expr(0);
				setState(790);
				_la = _input.LA(1);
				if (_la==K_OFFSET || _la==C1) {
					{
					setState(788);
					_la = _input.LA(1);
					if ( !(_la==K_OFFSET || _la==C1) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					setState(789);
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
		enterRule(_localctx, 72, RULE_select_or_values);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(806);
			_la = _input.LA(1);
			if (_la==K_SELECT) {
				{
				setState(794);
				match(K_SELECT);
				setState(796);
				switch ( getInterpreter().adaptivePredict(_input,109,_ctx) ) {
				case 1:
					{
					setState(795);
					_la = _input.LA(1);
					if ( !(_la==K_ALL || _la==K_DISTINCT) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					break;
				}
				setState(798);
				result_column();
				setState(803);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(799);
					match(C1);
					setState(800);
					result_column();
					}
					}
					setState(805);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			{
			setState(808);
			match(K_FROM);
			setState(818);
			switch ( getInterpreter().adaptivePredict(_input,113,_ctx) ) {
			case 1:
				{
				setState(809);
				table_or_subquery();
				setState(814);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(810);
					match(C1);
					setState(811);
					table_or_subquery();
					}
					}
					setState(816);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				{
				setState(817);
				join_clause();
				}
				break;
			}
			}
			setState(821);
			_la = _input.LA(1);
			if (((((_la - 47)) & ~0x3f) == 0 && ((1L << (_la - 47)) & ((1L << (K_GROUP - 47)) | (1L << (K_VALUES - 47)) | (1L << (K_WHERE - 47)))) != 0)) {
				{
				setState(820);
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
		enterRule(_localctx, 74, RULE_compound_operator);
		try {
			setState(828);
			switch ( getInterpreter().adaptivePredict(_input,115,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(823);
				match(K_UNION);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(824);
				match(K_UNION);
				setState(825);
				match(K_ALL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(826);
				match(K_INTERSECT);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(827);
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
		enterRule(_localctx, 76, RULE_join_clause);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(830);
			table_or_subquery();
			setState(840);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,117,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(831);
					join_operator();
					setState(834);
					switch ( getInterpreter().adaptivePredict(_input,116,_ctx) ) {
					case 1:
						{
						setState(832);
						join_clause();
						}
						break;
					case 2:
						{
						setState(833);
						table_or_subquery();
						}
						break;
					}
					setState(836);
					join_constraint();
					}
					} 
				}
				setState(842);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,117,_ctx);
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
		enterRule(_localctx, 78, RULE_join_operator);
		int _la;
		try {
			setState(864);
			switch (_input.LA(1)) {
			case C1:
				enterOuterAlt(_localctx, 1);
				{
				setState(843);
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
				setState(845);
				_la = _input.LA(1);
				if (_la==K_NATURAL) {
					{
					setState(844);
					match(K_NATURAL);
					}
				}

				setState(861);
				switch (_input.LA(1)) {
				case K_LEFT:
					{
					setState(847);
					match(K_LEFT);
					setState(849);
					_la = _input.LA(1);
					if (_la==K_OUTER) {
						{
						setState(848);
						match(K_OUTER);
						}
					}

					}
					break;
				case K_RIGHT:
					{
					setState(851);
					match(K_RIGHT);
					setState(853);
					_la = _input.LA(1);
					if (_la==K_OUTER) {
						{
						setState(852);
						match(K_OUTER);
						}
					}

					}
					break;
				case K_FULL:
					{
					setState(855);
					match(K_FULL);
					setState(857);
					_la = _input.LA(1);
					if (_la==K_OUTER) {
						{
						setState(856);
						match(K_OUTER);
						}
					}

					}
					break;
				case K_INNER:
					{
					setState(859);
					match(K_INNER);
					}
					break;
				case K_CROSS:
					{
					setState(860);
					match(K_CROSS);
					}
					break;
				case K_JOIN:
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(863);
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
		enterRule(_localctx, 80, RULE_join_constraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(880);
			switch ( getInterpreter().adaptivePredict(_input,125,_ctx) ) {
			case 1:
				{
				setState(866);
				match(K_ON);
				setState(867);
				expr(0);
				}
				break;
			case 2:
				{
				setState(868);
				match(K_USING);
				setState(869);
				match(OPEN_PARENTHESE);
				setState(870);
				column_name();
				setState(875);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(871);
					match(C1);
					setState(872);
					column_name();
					}
					}
					setState(877);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(878);
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
		enterRule(_localctx, 82, RULE_common_table_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(882);
			table_name();
			setState(894);
			_la = _input.LA(1);
			if (_la==OPEN_PARENTHESE) {
				{
				setState(883);
				match(OPEN_PARENTHESE);
				setState(884);
				column_name();
				setState(889);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(885);
					match(C1);
					setState(886);
					column_name();
					}
					}
					setState(891);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(892);
				match(CLOSE_PARENTHESE);
				}
			}

			setState(896);
			match(K_AS);
			setState(897);
			match(OPEN_PARENTHESE);
			setState(898);
			select_stmt();
			setState(899);
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
		enterRule(_localctx, 84, RULE_ordering_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(901);
			expr(0);
			setState(904);
			_la = _input.LA(1);
			if (_la==K_COLLATE) {
				{
				setState(902);
				match(K_COLLATE);
				setState(903);
				collation_name();
				}
			}

			setState(907);
			_la = _input.LA(1);
			if (_la==K_ASC || _la==K_DESC) {
				{
				setState(906);
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
		int _startState = 86;
		enterRecursionRule(_localctx, 86, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(967);
			switch ( getInterpreter().adaptivePredict(_input,137,_ctx) ) {
			case 1:
				{
				setState(910);
				unary_operator();
				setState(911);
				expr(20);
				}
				break;
			case 2:
				{
				setState(913);
				operand();
				}
				break;
			case 3:
				{
				setState(914);
				match(BIND_PARAMETER);
				}
				break;
			case 4:
				{
				setState(923);
				switch ( getInterpreter().adaptivePredict(_input,131,_ctx) ) {
				case 1:
					{
					setState(918);
					switch ( getInterpreter().adaptivePredict(_input,130,_ctx) ) {
					case 1:
						{
						setState(915);
						database_name();
						setState(916);
						match(DOT);
						}
						break;
					}
					setState(920);
					table_alias();
					setState(921);
					match(DOT);
					}
					break;
				}
				setState(925);
				column_name();
				}
				break;
			case 5:
				{
				setState(926);
				match(OPEN_PARENTHESE);
				setState(927);
				expr(0);
				setState(928);
				match(CLOSE_PARENTHESE);
				}
				break;
			case 6:
				{
				setState(930);
				match(K_CAST);
				setState(931);
				match(OPEN_PARENTHESE);
				setState(932);
				expr(0);
				setState(933);
				match(K_AS);
				setState(934);
				type_name();
				setState(935);
				match(CLOSE_PARENTHESE);
				}
				break;
			case 7:
				{
				setState(941);
				_la = _input.LA(1);
				if (_la==K_EXISTS || _la==K_NOT) {
					{
					setState(938);
					_la = _input.LA(1);
					if (_la==K_NOT) {
						{
						setState(937);
						match(K_NOT);
						}
					}

					setState(940);
					match(K_EXISTS);
					}
				}

				setState(943);
				match(OPEN_PARENTHESE);
				setState(944);
				select_stmt();
				setState(945);
				match(CLOSE_PARENTHESE);
				}
				break;
			case 8:
				{
				setState(947);
				match(K_CASE);
				setState(949);
				switch ( getInterpreter().adaptivePredict(_input,134,_ctx) ) {
				case 1:
					{
					setState(948);
					expr(0);
					}
					break;
				}
				setState(956); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(951);
					match(K_WHEN);
					setState(952);
					expr(0);
					setState(953);
					match(K_THEN);
					setState(954);
					expr(0);
					}
					}
					setState(958); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==K_WHEN );
				setState(962);
				_la = _input.LA(1);
				if (_la==K_ELSE) {
					{
					setState(960);
					match(K_ELSE);
					setState(961);
					expr(0);
					}
				}

				setState(964);
				match(K_END);
				}
				break;
			case 9:
				{
				setState(966);
				raise_function();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(1069);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,150,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(1067);
					switch ( getInterpreter().adaptivePredict(_input,149,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(969);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(970);
						match(T__0);
						setState(971);
						expr(20);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(972);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(973);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__7))) != 0) || _la==C3) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(974);
						expr(19);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(975);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(976);
						_la = _input.LA(1);
						if ( !(_la==T__5 || _la==T__6) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(977);
						expr(18);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(978);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(979);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__8) | (1L << T__9) | (1L << T__10))) != 0)) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(980);
						expr(17);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(981);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(982);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14))) != 0)) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(983);
						expr(16);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(984);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(997);
						switch ( getInterpreter().adaptivePredict(_input,138,_ctx) ) {
						case 1:
							{
							setState(985);
							match(T__15);
							}
							break;
						case 2:
							{
							setState(986);
							match(T__16);
							}
							break;
						case 3:
							{
							setState(987);
							match(T__17);
							}
							break;
						case 4:
							{
							setState(988);
							match(T__18);
							}
							break;
						case 5:
							{
							setState(989);
							match(K_IS);
							}
							break;
						case 6:
							{
							setState(990);
							match(K_IS);
							setState(991);
							match(K_NOT);
							}
							break;
						case 7:
							{
							setState(992);
							match(K_IN);
							}
							break;
						case 8:
							{
							setState(993);
							match(K_LIKE);
							}
							break;
						case 9:
							{
							setState(994);
							match(K_GLOB);
							}
							break;
						case 10:
							{
							setState(995);
							match(K_MATCH);
							}
							break;
						case 11:
							{
							setState(996);
							match(K_REGEXP);
							}
							break;
						}
						setState(999);
						expr(15);
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(1000);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(1001);
						match(K_AND);
						setState(1002);
						expr(14);
						}
						break;
					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(1003);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(1004);
						match(K_OR);
						setState(1005);
						expr(13);
						}
						break;
					case 9:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(1006);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(1007);
						match(K_IS);
						setState(1009);
						switch ( getInterpreter().adaptivePredict(_input,139,_ctx) ) {
						case 1:
							{
							setState(1008);
							match(K_NOT);
							}
							break;
						}
						setState(1011);
						expr(7);
						}
						break;
					case 10:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(1012);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(1014);
						_la = _input.LA(1);
						if (_la==K_NOT) {
							{
							setState(1013);
							match(K_NOT);
							}
						}

						setState(1016);
						match(K_BETWEEN);
						setState(1017);
						expr(0);
						setState(1018);
						match(K_AND);
						setState(1019);
						expr(6);
						}
						break;
					case 11:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(1021);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(1022);
						match(K_COLLATE);
						setState(1023);
						collation_name();
						}
						break;
					case 12:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(1024);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(1026);
						_la = _input.LA(1);
						if (_la==K_NOT) {
							{
							setState(1025);
							match(K_NOT);
							}
						}

						setState(1028);
						_la = _input.LA(1);
						if ( !(((((_la - 46)) & ~0x3f) == 0 && ((1L << (_la - 46)) & ((1L << (K_GLOB - 46)) | (1L << (K_LIKE - 46)) | (1L << (K_MATCH - 46)) | (1L << (K_REGEXP - 46)))) != 0)) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(1029);
						expr(0);
						setState(1032);
						switch ( getInterpreter().adaptivePredict(_input,142,_ctx) ) {
						case 1:
							{
							setState(1030);
							match(K_ESCAPE);
							setState(1031);
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
						setState(1034);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(1039);
						switch (_input.LA(1)) {
						case K_ISNULL:
							{
							setState(1035);
							match(K_ISNULL);
							}
							break;
						case K_NOTNULL:
							{
							setState(1036);
							match(K_NOTNULL);
							}
							break;
						case K_NOT:
							{
							setState(1037);
							match(K_NOT);
							setState(1038);
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
						setState(1041);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(1043);
						_la = _input.LA(1);
						if (_la==K_NOT) {
							{
							setState(1042);
							match(K_NOT);
							}
						}

						setState(1045);
						match(K_IN);
						setState(1065);
						switch (_input.LA(1)) {
						case OPEN_PARENTHESE:
							{
							setState(1046);
							match(OPEN_PARENTHESE);
							setState(1056);
							switch ( getInterpreter().adaptivePredict(_input,146,_ctx) ) {
							case 1:
								{
								setState(1047);
								select_stmt();
								}
								break;
							case 2:
								{
								setState(1048);
								expr(0);
								setState(1053);
								_errHandler.sync(this);
								_la = _input.LA(1);
								while (_la==C1) {
									{
									{
									setState(1049);
									match(C1);
									setState(1050);
									expr(0);
									}
									}
									setState(1055);
									_errHandler.sync(this);
									_la = _input.LA(1);
								}
								}
								break;
							}
							setState(1058);
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
							setState(1062);
							switch ( getInterpreter().adaptivePredict(_input,147,_ctx) ) {
							case 1:
								{
								setState(1059);
								database_name();
								setState(1060);
								match(DOT);
								}
								break;
							}
							setState(1064);
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
				setState(1071);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,150,_ctx);
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
		enterRule(_localctx, 88, RULE_literal_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1072);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_CURRENT_DATE) | (1L << K_CURRENT_TIME) | (1L << K_CURRENT_TIMESTAMP))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (K_NULL - 66)) | (1L << (NUMERIC_LITERAL - 66)) | (1L << (BLOB_LITERAL - 66)) | (1L << (STRING_LITERAL - 66)))) != 0)) ) {
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
		enterRule(_localctx, 90, RULE_unary_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1074);
			_la = _input.LA(1);
			if ( !(((((_la - 6)) & ~0x3f) == 0 && ((1L << (_la - 6)) & ((1L << (T__5 - 6)) | (1L << (T__6 - 6)) | (1L << (T__19 - 6)) | (1L << (K_NOT - 6)))) != 0)) ) {
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
		enterRule(_localctx, 92, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1076);
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
		enterRule(_localctx, 94, RULE_type_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1079); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1078);
				name();
				}
				}
				setState(1081); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_ABORT) | (1L << K_ALL) | (1L << K_AND) | (1L << K_AS) | (1L << K_ASC) | (1L << K_BETWEEN) | (1L << K_BY) | (1L << K_CASE) | (1L << K_CAST) | (1L << K_COLLATE) | (1L << K_CROSS) | (1L << K_CURRENT_DATE) | (1L << K_CURRENT_TIME) | (1L << K_CURRENT_TIMESTAMP) | (1L << K_DESC) | (1L << K_DISTINCT) | (1L << K_ELSE) | (1L << K_END) | (1L << K_ESCAPE) | (1L << K_EXCEPT) | (1L << K_EXISTS) | (1L << K_FAIL) | (1L << K_FULL) | (1L << K_FROM) | (1L << K_GLOB) | (1L << K_GROUP) | (1L << K_HAVING) | (1L << K_IF) | (1L << K_IGNORE) | (1L << K_IN) | (1L << K_INDEXED) | (1L << K_INNER) | (1L << K_INTERSECT) | (1L << K_IS) | (1L << K_ISNULL) | (1L << K_JOIN) | (1L << K_LEFT) | (1L << K_LIKE) | (1L << K_LIMIT) | (1L << K_MATCH) | (1L << K_NATURAL) | (1L << K_NO))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (K_NOT - 64)) | (1L << (K_NOTNULL - 64)) | (1L << (K_NULL - 64)) | (1L << (K_OFFSET - 64)) | (1L << (K_ON - 64)) | (1L << (K_OR - 64)) | (1L << (K_ORDER - 64)) | (1L << (K_OUTER - 64)) | (1L << (K_RAISE - 64)) | (1L << (K_RECURSIVE - 64)) | (1L << (K_REGEXP - 64)) | (1L << (K_RIGHT - 64)) | (1L << (K_ROLLBACK - 64)) | (1L << (K_SELECT - 64)) | (1L << (K_THEN - 64)) | (1L << (K_UNION - 64)) | (1L << (K_USING - 64)) | (1L << (K_VALUES - 64)) | (1L << (K_WHEN - 64)) | (1L << (K_WHERE - 64)) | (1L << (K_WITH - 64)) | (1L << (K_GENERATE - 64)) | (1L << (K_MAX - 64)) | (1L << (K_MIN - 64)) | (1L << (K_AVG - 64)) | (1L << (K_COUNT - 64)) | (1L << (K_SUM - 64)) | (1L << (K_GGPLOT - 64)) | (1L << (IDENTIFIER - 64)) | (1L << (STRING_LITERAL - 64)))) != 0) );
			setState(1093);
			switch ( getInterpreter().adaptivePredict(_input,152,_ctx) ) {
			case 1:
				{
				setState(1083);
				match(OPEN_PARENTHESE);
				setState(1084);
				signed_number();
				setState(1085);
				match(CLOSE_PARENTHESE);
				}
				break;
			case 2:
				{
				setState(1087);
				match(OPEN_PARENTHESE);
				setState(1088);
				signed_number();
				setState(1089);
				match(C1);
				setState(1090);
				signed_number();
				setState(1091);
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
		enterRule(_localctx, 96, RULE_function_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1095);
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
		enterRule(_localctx, 98, RULE_ag_function_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1097);
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
		enterRule(_localctx, 100, RULE_ag_keyword);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1099);
			_la = _input.LA(1);
			if ( !(((((_la - 86)) & ~0x3f) == 0 && ((1L << (_la - 86)) & ((1L << (K_MAX - 86)) | (1L << (K_MIN - 86)) | (1L << (K_AVG - 86)) | (1L << (K_COUNT - 86)) | (1L << (K_SUM - 86)))) != 0)) ) {
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
		enterRule(_localctx, 102, RULE_gg_function_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1101);
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
		enterRule(_localctx, 104, RULE_gg_keyword);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1103);
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
		enterRule(_localctx, 106, RULE_collation_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1105);
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
		enterRule(_localctx, 108, RULE_database_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1107);
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
		enterRule(_localctx, 110, RULE_table_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1109);
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
		enterRule(_localctx, 112, RULE_column_alias);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1111);
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
		enterRule(_localctx, 114, RULE_column_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1113);
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
		enterRule(_localctx, 116, RULE_table_alias);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1115);
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
		enterRule(_localctx, 118, RULE_index_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1117);
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
		enterRule(_localctx, 120, RULE_any_name);
		try {
			setState(1122);
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
				setState(1119);
				keyword();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(1120);
				match(IDENTIFIER);
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(1121);
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
		enterRule(_localctx, 122, RULE_sl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1124);
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
		enterRule(_localctx, 124, RULE_signed_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1127);
			_la = _input.LA(1);
			if (_la==T__5 || _la==T__6) {
				{
				setState(1126);
				_la = _input.LA(1);
				if ( !(_la==T__5 || _la==T__6) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
			}

			setState(1129);
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
		enterRule(_localctx, 126, RULE_raise_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1131);
			match(K_RAISE);
			setState(1132);
			match(OPEN_PARENTHESE);
			setState(1137);
			switch (_input.LA(1)) {
			case K_IGNORE:
				{
				setState(1133);
				match(K_IGNORE);
				}
				break;
			case K_ABORT:
			case K_FAIL:
			case K_ROLLBACK:
				{
				setState(1134);
				_la = _input.LA(1);
				if ( !(((((_la - 21)) & ~0x3f) == 0 && ((1L << (_la - 21)) & ((1L << (K_ABORT - 21)) | (1L << (K_FAIL - 21)) | (1L << (K_ROLLBACK - 21)))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(1135);
				match(C1);
				setState(1136);
				error_message();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1139);
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
		enterRule(_localctx, 128, RULE_error_message);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1141);
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
		case 23:
			return arithmetics_sempred((ArithmeticsContext)_localctx, predIndex);
		case 43:
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3v\u047a\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\3\2\3\2\3\2\5\2\u0088\n\2\3\3\3\3\5\3\u008c"+
		"\n\3\3\3\5\3\u008f\n\3\3\4\3\4\3\4\3\5\5\5\u0095\n\5\3\5\3\5\5\5\u0099"+
		"\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00a6\n\5\3\5\3\5"+
		"\3\5\3\5\3\5\5\5\u00ad\n\5\3\5\3\5\5\5\u00b1\n\5\3\5\5\5\u00b4\n\5\3\6"+
		"\3\6\3\6\5\6\u00b9\n\6\3\6\3\6\3\7\3\7\3\7\5\7\u00c0\n\7\3\7\3\7\3\7\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b"+
		"\u00d6\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00e0\n\t\3\t\3\t\3\t\3"+
		"\t\5\t\u00e6\n\t\5\t\u00e8\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00f2"+
		"\n\t\3\t\3\t\3\t\3\t\5\t\u00f8\n\t\5\t\u00fa\n\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\5\t\u0104\n\t\3\t\3\t\3\t\3\t\5\t\u010a\n\t\5\t\u010c\n\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u0118\n\t\3\n\3\n\3\13\3\13"+
		"\3\13\3\13\5\13\u0120\n\13\7\13\u0122\n\13\f\13\16\13\u0125\13\13\3\f"+
		"\3\f\3\f\6\f\u012a\n\f\r\f\16\f\u012b\3\r\3\r\3\r\3\r\5\r\u0132\n\r\7"+
		"\r\u0134\n\r\f\r\16\r\u0137\13\r\3\16\3\16\3\16\3\16\5\16\u013d\n\16\7"+
		"\16\u013f\n\16\f\16\16\16\u0142\13\16\3\17\3\17\5\17\u0146\n\17\3\17\3"+
		"\17\3\17\5\17\u014b\n\17\7\17\u014d\n\17\f\17\16\17\u0150\13\17\3\20\3"+
		"\20\5\20\u0154\n\20\3\20\3\20\3\20\5\20\u0159\n\20\7\20\u015b\n\20\f\20"+
		"\16\20\u015e\13\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\5"+
		"\22\u016a\n\22\3\23\5\23\u016d\n\23\3\23\3\23\3\23\3\23\5\23\u0173\n\23"+
		"\3\23\3\23\3\23\5\23\u0178\n\23\7\23\u017a\n\23\f\23\16\23\u017d\13\23"+
		"\5\23\u017f\n\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\5\24\u0188\n\24\3"+
		"\24\3\24\3\24\5\24\u018d\n\24\7\24\u018f\n\24\f\24\16\24\u0192\13\24\7"+
		"\24\u0194\n\24\f\24\16\24\u0197\13\24\3\24\3\24\3\25\3\25\3\25\7\25\u019e"+
		"\n\25\f\25\16\25\u01a1\13\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3"+
		"\27\3\27\5\27\u01ad\n\27\3\27\3\27\3\27\5\27\u01b2\n\27\3\27\7\27\u01b5"+
		"\n\27\f\27\16\27\u01b8\13\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3"+
		"\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3"+
		"\30\5\30\u01d1\n\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u01db"+
		"\n\31\3\31\3\31\3\31\7\31\u01e0\n\31\f\31\16\31\u01e3\13\31\3\32\3\32"+
		"\5\32\u01e7\n\32\3\33\3\33\5\33\u01eb\n\33\3\34\3\34\3\34\3\35\3\35\6"+
		"\35\u01f2\n\35\r\35\16\35\u01f3\3\35\7\35\u01f7\n\35\f\35\16\35\u01fa"+
		"\13\35\3\35\7\35\u01fd\n\35\f\35\16\35\u0200\13\35\3\36\3\36\5\36\u0204"+
		"\n\36\3\37\3\37\5\37\u0208\n\37\3\37\3\37\3\37\7\37\u020d\n\37\f\37\16"+
		"\37\u0210\13\37\5\37\u0212\n\37\3\37\3\37\3\37\3\37\7\37\u0218\n\37\f"+
		"\37\16\37\u021b\13\37\3\37\3\37\3\37\3\37\3\37\7\37\u0222\n\37\f\37\16"+
		"\37\u0225\13\37\5\37\u0227\n\37\3\37\3\37\3\37\3\37\5\37\u022d\n\37\5"+
		"\37\u022f\n\37\3 \3 \5 \u0233\n \3 \3 \3 \7 \u0238\n \f \16 \u023b\13"+
		" \5 \u023d\n \3 \3 \3 \3 \7 \u0243\n \f \16 \u0246\13 \3 \5 \u0249\n "+
		"\3 \5 \u024c\n \3!\3!\3!\3!\3!\3!\3!\3!\7!\u0256\n!\f!\16!\u0259\13!\3"+
		"!\3!\5!\u025d\n!\5!\u025f\n!\3!\3!\3!\3!\3!\7!\u0266\n!\f!\16!\u0269\13"+
		"!\3!\3!\3!\3!\3!\3!\7!\u0271\n!\f!\16!\u0274\13!\3!\3!\7!\u0278\n!\f!"+
		"\16!\u027b\13!\5!\u027d\n!\3!\3!\5!\u0281\n!\3!\3!\3!\3!\3!\7!\u0288\n"+
		"!\f!\16!\u028b\13!\3!\3!\5!\u028f\n!\3!\3!\3!\3!\3!\7!\u0296\n!\f!\16"+
		"!\u0299\13!\3!\3!\3!\3!\3!\3!\7!\u02a1\n!\f!\16!\u02a4\13!\3!\3!\7!\u02a8"+
		"\n!\f!\16!\u02ab\13!\5!\u02ad\n!\5!\u02af\n!\3\"\3\"\3\"\3\"\3\"\3\"\3"+
		"\"\5\"\u02b8\n\"\3\"\5\"\u02bb\n\"\5\"\u02bd\n\"\3#\3#\3#\5#\u02c2\n#"+
		"\3#\3#\5#\u02c6\n#\3#\5#\u02c9\n#\3#\3#\3#\3#\3#\5#\u02d0\n#\3#\3#\3#"+
		"\3#\7#\u02d6\n#\f#\16#\u02d9\13#\3#\5#\u02dc\n#\3#\3#\5#\u02e0\n#\3#\5"+
		"#\u02e3\n#\3#\3#\3#\3#\5#\u02e9\n#\3#\5#\u02ec\n#\5#\u02ee\n#\3$\3$\3"+
		"%\3%\5%\u02f4\n%\3%\3%\3%\7%\u02f9\n%\f%\16%\u02fc\13%\5%\u02fe\n%\3%"+
		"\3%\3%\3%\7%\u0304\n%\f%\16%\u0307\13%\3%\3%\3%\3%\3%\7%\u030e\n%\f%\16"+
		"%\u0311\13%\5%\u0313\n%\3%\3%\3%\3%\5%\u0319\n%\5%\u031b\n%\3&\3&\5&\u031f"+
		"\n&\3&\3&\3&\7&\u0324\n&\f&\16&\u0327\13&\5&\u0329\n&\3&\3&\3&\3&\7&\u032f"+
		"\n&\f&\16&\u0332\13&\3&\5&\u0335\n&\3&\5&\u0338\n&\3\'\3\'\3\'\3\'\3\'"+
		"\5\'\u033f\n\'\3(\3(\3(\3(\5(\u0345\n(\3(\3(\7(\u0349\n(\f(\16(\u034c"+
		"\13(\3)\3)\5)\u0350\n)\3)\3)\5)\u0354\n)\3)\3)\5)\u0358\n)\3)\3)\5)\u035c"+
		"\n)\3)\3)\5)\u0360\n)\3)\5)\u0363\n)\3*\3*\3*\3*\3*\3*\3*\7*\u036c\n*"+
		"\f*\16*\u036f\13*\3*\3*\5*\u0373\n*\3+\3+\3+\3+\3+\7+\u037a\n+\f+\16+"+
		"\u037d\13+\3+\3+\5+\u0381\n+\3+\3+\3+\3+\3+\3,\3,\3,\5,\u038b\n,\3,\5"+
		",\u038e\n,\3-\3-\3-\3-\3-\3-\3-\3-\3-\5-\u0399\n-\3-\3-\3-\5-\u039e\n"+
		"-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\5-\u03ad\n-\3-\5-\u03b0\n-\3"+
		"-\3-\3-\3-\3-\3-\5-\u03b8\n-\3-\3-\3-\3-\3-\6-\u03bf\n-\r-\16-\u03c0\3"+
		"-\3-\5-\u03c5\n-\3-\3-\3-\5-\u03ca\n-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3"+
		"-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\5-\u03e8\n-\3-\3"+
		"-\3-\3-\3-\3-\3-\3-\3-\3-\5-\u03f4\n-\3-\3-\3-\5-\u03f9\n-\3-\3-\3-\3"+
		"-\3-\3-\3-\3-\3-\3-\5-\u0405\n-\3-\3-\3-\3-\5-\u040b\n-\3-\3-\3-\3-\3"+
		"-\5-\u0412\n-\3-\3-\5-\u0416\n-\3-\3-\3-\3-\3-\3-\7-\u041e\n-\f-\16-\u0421"+
		"\13-\5-\u0423\n-\3-\3-\3-\3-\5-\u0429\n-\3-\5-\u042c\n-\7-\u042e\n-\f"+
		"-\16-\u0431\13-\3.\3.\3/\3/\3\60\3\60\3\61\6\61\u043a\n\61\r\61\16\61"+
		"\u043b\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\5\61\u0448\n"+
		"\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\38\38"+
		"\39\39\3:\3:\3;\3;\3<\3<\3=\3=\3>\3>\3>\5>\u0465\n>\3?\3?\3@\5@\u046a"+
		"\n@\3@\3@\3A\3A\3A\3A\3A\3A\5A\u0474\nA\3A\3A\3B\3B\3B\3\u019f\4\60XC"+
		"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFH"+
		"JLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\2\21\4\2\6\taa\4\2EE__\4\2\31"+
		"\31\'\'\4\2\27\27\31]\4\2\34\34&&\5\2\6\7\n\naa\3\2\b\t\4\2\5\5\13\r\3"+
		"\2\16\21\6\2\60\60==??LL\6\2#%DDnorr\5\2\b\t\26\26BB\3\2X\\\3\2qr\5\2"+
		"\27\27--NN\u0511\2\u0084\3\2\2\2\4\u008b\3\2\2\2\6\u0090\3\2\2\2\b\u00b0"+
		"\3\2\2\2\n\u00b8\3\2\2\2\f\u00bf\3\2\2\2\16\u00d5\3\2\2\2\20\u0117\3\2"+
		"\2\2\22\u0119\3\2\2\2\24\u011b\3\2\2\2\26\u0126\3\2\2\2\30\u012d\3\2\2"+
		"\2\32\u0138\3\2\2\2\34\u0145\3\2\2\2\36\u0153\3\2\2\2 \u015f\3\2\2\2\""+
		"\u0169\3\2\2\2$\u016c\3\2\2\2&\u0182\3\2\2\2(\u019a\3\2\2\2*\u01a4\3\2"+
		"\2\2,\u01a9\3\2\2\2.\u01d0\3\2\2\2\60\u01da\3\2\2\2\62\u01e6\3\2\2\2\64"+
		"\u01ea\3\2\2\2\66\u01ec\3\2\2\28\u01ef\3\2\2\2:\u0203\3\2\2\2<\u0211\3"+
		"\2\2\2>\u023c\3\2\2\2@\u02ae\3\2\2\2B\u02bc\3\2\2\2D\u02ed\3\2\2\2F\u02ef"+
		"\3\2\2\2H\u02fd\3\2\2\2J\u0328\3\2\2\2L\u033e\3\2\2\2N\u0340\3\2\2\2P"+
		"\u0362\3\2\2\2R\u0372\3\2\2\2T\u0374\3\2\2\2V\u0387\3\2\2\2X\u03c9\3\2"+
		"\2\2Z\u0432\3\2\2\2\\\u0434\3\2\2\2^\u0436\3\2\2\2`\u0439\3\2\2\2b\u0449"+
		"\3\2\2\2d\u044b\3\2\2\2f\u044d\3\2\2\2h\u044f\3\2\2\2j\u0451\3\2\2\2l"+
		"\u0453\3\2\2\2n\u0455\3\2\2\2p\u0457\3\2\2\2r\u0459\3\2\2\2t\u045b\3\2"+
		"\2\2v\u045d\3\2\2\2x\u045f\3\2\2\2z\u0464\3\2\2\2|\u0466\3\2\2\2~\u0469"+
		"\3\2\2\2\u0080\u046d\3\2\2\2\u0082\u0477\3\2\2\2\u0084\u0085\5\6\4\2\u0085"+
		"\u0087\5\4\3\2\u0086\u0088\5\64\33\2\u0087\u0086\3\2\2\2\u0087\u0088\3"+
		"\2\2\2\u0088\3\3\2\2\2\u0089\u008c\5\b\5\2\u008a\u008c\5\22\n\2\u008b"+
		"\u0089\3\2\2\2\u008b\u008a\3\2\2\2\u008c\u008e\3\2\2\2\u008d\u008f\7m"+
		"\2\2\u008e\u008d\3\2\2\2\u008e\u008f\3\2\2\2\u008f\5\3\2\2\2\u0090\u0091"+
		"\7W\2\2\u0091\u0092\7q\2\2\u0092\7\3\2\2\2\u0093\u0095\5\"\22\2\u0094"+
		"\u0093\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u00b1\5\n"+
		"\6\2\u0097\u0099\5\"\22\2\u0098\u0097\3\2\2\2\u0098\u0099\3\2\2\2\u0099"+
		"\u009a\3\2\2\2\u009a\u00b1\5\f\7\2\u009b\u00b1\5$\23\2\u009c\u00b1\5&"+
		"\24\2\u009d\u009e\7i\2\2\u009e\u009f\5\22\n\2\u009f\u00a0\7j\2\2\u00a0"+
		"\u00b1\3\2\2\2\u00a1\u00b1\5\16\b\2\u00a2\u00b1\5\20\t\2\u00a3\u00b1\5"+
		".\30\2\u00a4\u00a6\5\"\22\2\u00a5\u00a4\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6"+
		"\u00a7\3\2\2\2\u00a7\u00b1\5*\26\2\u00a8\u00b1\5\60\31\2\u00a9\u00b1\7"+
		"n\2\2\u00aa\u00b1\5|?\2\u00ab\u00ad\5\"\22\2\u00ac\u00ab\3\2\2\2\u00ac"+
		"\u00ad\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00b1\5,\27\2\u00af\u00b1\5("+
		"\25\2\u00b0\u0094\3\2\2\2\u00b0\u0098\3\2\2\2\u00b0\u009b\3\2\2\2\u00b0"+
		"\u009c\3\2\2\2\u00b0\u009d\3\2\2\2\u00b0\u00a1\3\2\2\2\u00b0\u00a2\3\2"+
		"\2\2\u00b0\u00a3\3\2\2\2\u00b0\u00a5\3\2\2\2\u00b0\u00a8\3\2\2\2\u00b0"+
		"\u00a9\3\2\2\2\u00b0\u00aa\3\2\2\2\u00b0\u00ac\3\2\2\2\u00b0\u00af\3\2"+
		"\2\2\u00b1\u00b3\3\2\2\2\u00b2\u00b4\7m\2\2\u00b3\u00b2\3\2\2\2\u00b3"+
		"\u00b4\3\2\2\2\u00b4\t\3\2\2\2\u00b5\u00b6\5v<\2\u00b6\u00b7\7d\2\2\u00b7"+
		"\u00b9\3\2\2\2\u00b8\u00b5\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00ba\3\2"+
		"\2\2\u00ba\u00bb\5t;\2\u00bb\13\3\2\2\2\u00bc\u00c0\5\n\6\2\u00bd\u00c0"+
		"\5*\26\2\u00be\u00c0\5&\24\2\u00bf\u00bc\3\2\2\2\u00bf\u00bd\3\2\2\2\u00bf"+
		"\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c2\7\33\2\2\u00c2\u00c3\5"+
		"z>\2\u00c3\r\3\2\2\2\u00c4\u00c5\7g\2\2\u00c5\u00c6\5\22\n\2\u00c6\u00c7"+
		"\7h\2\2\u00c7\u00c8\7_\2\2\u00c8\u00d6\3\2\2\2\u00c9\u00ca\7g\2\2\u00ca"+
		"\u00cb\5\22\n\2\u00cb\u00cc\7h\2\2\u00cc\u00cd\7`\2\2\u00cd\u00d6\3\2"+
		"\2\2\u00ce\u00cf\7g\2\2\u00cf\u00d0\5\22\n\2\u00d0\u00d1\7h\2\2\u00d1"+
		"\u00d2\7a\2\2\u00d2\u00d6\3\2\2\2\u00d3\u00d6\7b\2\2\u00d4\u00d6\7c\2"+
		"\2\u00d5\u00c4\3\2\2\2\u00d5\u00c9\3\2\2\2\u00d5\u00ce\3\2\2\2\u00d5\u00d3"+
		"\3\2\2\2\u00d5\u00d4\3\2\2\2\u00d6\17\3\2\2\2\u00d7\u00d8\7g\2\2\u00d8"+
		"\u00d9\5\22\n\2\u00d9\u00da\7h\2\2\u00da\u00e7\7_\2\2\u00db\u00dc\7n\2"+
		"\2\u00dc\u00df\7`\2\2\u00dd\u00de\7n\2\2\u00de\u00e0\7a\2\2\u00df\u00dd"+
		"\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e8\3\2\2\2\u00e1\u00e2\7n\2\2\u00e2"+
		"\u00e5\7a\2\2\u00e3\u00e4\7n\2\2\u00e4\u00e6\7`\2\2\u00e5\u00e3\3\2\2"+
		"\2\u00e5\u00e6\3\2\2\2\u00e6\u00e8\3\2\2\2\u00e7\u00db\3\2\2\2\u00e7\u00e1"+
		"\3\2\2\2\u00e8\u0118\3\2\2\2\u00e9\u00ea\7g\2\2\u00ea\u00eb\5\22\n\2\u00eb"+
		"\u00ec\7h\2\2\u00ec\u00f9\7`\2\2\u00ed\u00ee\7n\2\2\u00ee\u00f1\7_\2\2"+
		"\u00ef\u00f0\7n\2\2\u00f0\u00f2\7a\2\2\u00f1\u00ef\3\2\2\2\u00f1\u00f2"+
		"\3\2\2\2\u00f2\u00fa\3\2\2\2\u00f3\u00f4\7n\2\2\u00f4\u00f7\7a\2\2\u00f5"+
		"\u00f6\7n\2\2\u00f6\u00f8\7_\2\2\u00f7\u00f5\3\2\2\2\u00f7\u00f8\3\2\2"+
		"\2\u00f8\u00fa\3\2\2\2\u00f9\u00ed\3\2\2\2\u00f9\u00f3\3\2\2\2\u00fa\u0118"+
		"\3\2\2\2\u00fb\u00fc\7g\2\2\u00fc\u00fd\5\22\n\2\u00fd\u00fe\7h\2\2\u00fe"+
		"\u010b\7a\2\2\u00ff\u0100\7n\2\2\u0100\u0103\7_\2\2\u0101\u0102\7n\2\2"+
		"\u0102\u0104\7`\2\2\u0103\u0101\3\2\2\2\u0103\u0104\3\2\2\2\u0104\u010c"+
		"\3\2\2\2\u0105\u0106\7n\2\2\u0106\u0109\7`\2\2\u0107\u0108\7n\2\2\u0108"+
		"\u010a\7_\2\2\u0109\u0107\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u010c\3\2"+
		"\2\2\u010b\u00ff\3\2\2\2\u010b\u0105\3\2\2\2\u010c\u0118\3\2\2\2\u010d"+
		"\u010e\7g\2\2\u010e\u010f\5\22\n\2\u010f\u0110\7h\2\2\u0110\u0111\7b\2"+
		"\2\u0111\u0118\3\2\2\2\u0112\u0113\7g\2\2\u0113\u0114\5\22\n\2\u0114\u0115"+
		"\7h\2\2\u0115\u0116\7c\2\2\u0116\u0118\3\2\2\2\u0117\u00d7\3\2\2\2\u0117"+
		"\u00e9\3\2\2\2\u0117\u00fb\3\2\2\2\u0117\u010d\3\2\2\2\u0117\u0112\3\2"+
		"\2\2\u0118\21\3\2\2\2\u0119\u011a\5\24\13\2\u011a\23\3\2\2\2\u011b\u0123"+
		"\5\30\r\2\u011c\u011f\7b\2\2\u011d\u0120\5\30\r\2\u011e\u0120\5\b\5\2"+
		"\u011f\u011d\3\2\2\2\u011f\u011e\3\2\2\2\u0120\u0122\3\2\2\2\u0121\u011c"+
		"\3\2\2\2\u0122\u0125\3\2\2\2\u0123\u0121\3\2\2\2\u0123\u0124\3\2\2\2\u0124"+
		"\25\3\2\2\2\u0125\u0123\3\2\2\2\u0126\u0129\5\b\5\2\u0127\u0128\7\3\2"+
		"\2\u0128\u012a\5\b\5\2\u0129\u0127\3\2\2\2\u012a\u012b\3\2\2\2\u012b\u0129"+
		"\3\2\2\2\u012b\u012c\3\2\2\2\u012c\27\3\2\2\2\u012d\u0135\5\32\16\2\u012e"+
		"\u0131\7a\2\2\u012f\u0132\5\32\16\2\u0130\u0132\5\b\5\2\u0131\u012f\3"+
		"\2\2\2\u0131\u0130\3\2\2\2\u0132\u0134\3\2\2\2\u0133\u012e\3\2\2\2\u0134"+
		"\u0137\3\2\2\2\u0135\u0133\3\2\2\2\u0135\u0136\3\2\2\2\u0136\31\3\2\2"+
		"\2\u0137\u0135\3\2\2\2\u0138\u0140\5\34\17\2\u0139\u013c\7`\2\2\u013a"+
		"\u013d\5\34\17\2\u013b\u013d\5\b\5\2\u013c\u013a\3\2\2\2\u013c\u013b\3"+
		"\2\2\2\u013d\u013f\3\2\2\2\u013e\u0139\3\2\2\2\u013f\u0142\3\2\2\2\u0140"+
		"\u013e\3\2\2\2\u0140\u0141\3\2\2\2\u0141\33\3\2\2\2\u0142\u0140\3\2\2"+
		"\2\u0143\u0146\5\b\5\2\u0144\u0146\5\36\20\2\u0145\u0143\3\2\2\2\u0145"+
		"\u0144\3\2\2\2\u0146\u014e\3\2\2\2\u0147\u014a\7_\2\2\u0148\u014b\5\b"+
		"\5\2\u0149\u014b\5\36\20\2\u014a\u0148\3\2\2\2\u014a\u0149\3\2\2\2\u014b"+
		"\u014d\3\2\2\2\u014c\u0147\3\2\2\2\u014d\u0150\3\2\2\2\u014e\u014c\3\2"+
		"\2\2\u014e\u014f\3\2\2\2\u014f\35\3\2\2\2\u0150\u014e\3\2\2\2\u0151\u0154"+
		"\5\b\5\2\u0152\u0154\5\26\f\2\u0153\u0151\3\2\2\2\u0153\u0152\3\2\2\2"+
		"\u0154\u015c\3\2\2\2\u0155\u0158\7^\2\2\u0156\u0159\5\b\5\2\u0157\u0159"+
		"\5\26\f\2\u0158\u0156\3\2\2\2\u0158\u0157\3\2\2\2\u0159\u015b\3\2\2\2"+
		"\u015a\u0155\3\2\2\2\u015b\u015e\3\2\2\2\u015c\u015a\3\2\2\2\u015c\u015d"+
		"\3\2\2\2\u015d\37\3\2\2\2\u015e\u015c\3\2\2\2\u015f\u0160\7l\2\2\u0160"+
		"\u0161\7l\2\2\u0161\u0162\5z>\2\u0162!\3\2\2\2\u0163\u0164\7e\2\2\u0164"+
		"\u0165\7\34\2\2\u0165\u016a\7f\2\2\u0166\u0167\7e\2\2\u0167\u0168\7&\2"+
		"\2\u0168\u016a\7f\2\2\u0169\u0163\3\2\2\2\u0169\u0166\3\2\2\2\u016a#\3"+
		"\2\2\2\u016b\u016d\7\4\2\2\u016c\u016b\3\2\2\2\u016c\u016d\3\2\2\2\u016d"+
		"\u016e\3\2\2\2\u016e\u016f\5b\62\2\u016f\u017e\7e\2\2\u0170\u0173\5\b"+
		"\5\2\u0171\u0173\5\22\n\2\u0172\u0170\3\2\2\2\u0172\u0171\3\2\2\2\u0173"+
		"\u017b\3\2\2\2\u0174\u0177\7_\2\2\u0175\u0178\5\b\5\2\u0176\u0178\5\22"+
		"\n\2\u0177\u0175\3\2\2\2\u0177\u0176\3\2\2\2\u0178\u017a\3\2\2\2\u0179"+
		"\u0174\3\2\2\2\u017a\u017d\3\2\2\2\u017b\u0179\3\2\2\2\u017b\u017c\3\2"+
		"\2\2\u017c\u017f\3\2\2\2\u017d\u017b\3\2\2\2\u017e\u0172\3\2\2\2\u017e"+
		"\u017f\3\2\2\2\u017f\u0180\3\2\2\2\u0180\u0181\7f\2\2\u0181%\3\2\2\2\u0182"+
		"\u0183\7\5\2\2\u0183\u0184\5b\62\2\u0184\u0195\7e\2\2\u0185\u0188\5\b"+
		"\5\2\u0186\u0188\5\22\n\2\u0187\u0185\3\2\2\2\u0187\u0186\3\2\2\2\u0188"+
		"\u0190\3\2\2\2\u0189\u018c\7_\2\2\u018a\u018d\5\b\5\2\u018b\u018d\5\22"+
		"\n\2\u018c\u018a\3\2\2\2\u018c\u018b\3\2\2\2\u018d\u018f\3\2\2\2\u018e"+
		"\u0189\3\2\2\2\u018f\u0192\3\2\2\2\u0190\u018e\3\2\2\2\u0190\u0191\3\2"+
		"\2\2\u0191\u0194\3\2\2\2\u0192\u0190\3\2\2\2\u0193\u0187\3\2\2\2\u0194"+
		"\u0197\3\2\2\2\u0195\u0193\3\2\2\2\u0195\u0196\3\2\2\2\u0196\u0198\3\2"+
		"\2\2\u0197\u0195\3\2\2\2\u0198\u0199\7f\2\2\u0199\'\3\2\2\2\u019a\u019b"+
		"\7\5\2\2\u019b\u019f\7e\2\2\u019c\u019e\13\2\2\2\u019d\u019c\3\2\2\2\u019e"+
		"\u01a1\3\2\2\2\u019f\u01a0\3\2\2\2\u019f\u019d\3\2\2\2\u01a0\u01a2\3\2"+
		"\2\2\u01a1\u019f\3\2\2\2\u01a2\u01a3\7f\2\2\u01a3)\3\2\2\2\u01a4\u01a5"+
		"\5d\63\2\u01a5\u01a6\7g\2\2\u01a6\u01a7\5\n\6\2\u01a7\u01a8\7h\2\2\u01a8"+
		"+\3\2\2\2\u01a9\u01aa\5h\65\2\u01aa\u01ac\7g\2\2\u01ab\u01ad\5\"\22\2"+
		"\u01ac\u01ab\3\2\2\2\u01ac\u01ad\3\2\2\2\u01ad\u01ae\3\2\2\2\u01ae\u01b6"+
		"\5\n\6\2\u01af\u01b1\7_\2\2\u01b0\u01b2\5\"\22\2\u01b1\u01b0\3\2\2\2\u01b1"+
		"\u01b2\3\2\2\2\u01b2\u01b3\3\2\2\2\u01b3\u01b5\5\n\6\2\u01b4\u01af\3\2"+
		"\2\2\u01b5\u01b8\3\2\2\2\u01b6\u01b4\3\2\2\2\u01b6\u01b7\3\2\2\2\u01b7"+
		"\u01b9\3\2\2\2\u01b8\u01b6\3\2\2\2\u01b9\u01ba\7h\2\2\u01ba-\3\2\2\2\u01bb"+
		"\u01bc\7\63\2\2\u01bc\u01bd\7e\2\2\u01bd\u01be\5X-\2\u01be\u01bf\7f\2"+
		"\2\u01bf\u01c0\7P\2\2\u01c0\u01c1\7e\2\2\u01c1\u01c2\5\22\n\2\u01c2\u01c3"+
		"\7f\2\2\u01c3\u01c4\7(\2\2\u01c4\u01c5\7e\2\2\u01c5\u01c6\5\22\n\2\u01c6"+
		"\u01c7\7f\2\2\u01c7\u01d1\3\2\2\2\u01c8\u01c9\7e\2\2\u01c9\u01ca\5X-\2"+
		"\u01ca\u01cb\7f\2\2\u01cb\u01cc\7^\2\2\u01cc\u01cd\5\22\n\2\u01cd\u01ce"+
		"\7l\2\2\u01ce\u01cf\5\22\n\2\u01cf\u01d1\3\2\2\2\u01d0\u01bb\3\2\2\2\u01d0"+
		"\u01c8\3\2\2\2\u01d1/\3\2\2\2\u01d2\u01d3\b\31\1\2\u01d3\u01d4\7e\2\2"+
		"\u01d4\u01d5\5\60\31\2\u01d5\u01d6\t\2\2\2\u01d6\u01d7\5\60\31\2\u01d7"+
		"\u01d8\7f\2\2\u01d8\u01db\3\2\2\2\u01d9\u01db\5\62\32\2\u01da\u01d2\3"+
		"\2\2\2\u01da\u01d9\3\2\2\2\u01db\u01e1\3\2\2\2\u01dc\u01dd\f\4\2\2\u01dd"+
		"\u01de\t\2\2\2\u01de\u01e0\5\60\31\5\u01df\u01dc\3\2\2\2\u01e0\u01e3\3"+
		"\2\2\2\u01e1\u01df\3\2\2\2\u01e1\u01e2\3\2\2\2\u01e2\61\3\2\2\2\u01e3"+
		"\u01e1\3\2\2\2\u01e4\u01e7\5\n\6\2\u01e5\u01e7\7n\2\2\u01e6\u01e4\3\2"+
		"\2\2\u01e6\u01e5\3\2\2\2\u01e7\63\3\2\2\2\u01e8\u01eb\58\35\2\u01e9\u01eb"+
		"\5\66\34\2\u01ea\u01e8\3\2\2\2\u01ea\u01e9\3\2\2\2\u01eb\65\3\2\2\2\u01ec"+
		"\u01ed\7v\2\2\u01ed\u01ee\b\34\1\2\u01ee\67\3\2\2\2\u01ef\u01f8\5:\36"+
		"\2\u01f0\u01f2\7k\2\2\u01f1\u01f0\3\2\2\2\u01f2\u01f3\3\2\2\2\u01f3\u01f1"+
		"\3\2\2\2\u01f3\u01f4\3\2\2\2\u01f4\u01f5\3\2\2\2\u01f5\u01f7\5:\36\2\u01f6"+
		"\u01f1\3\2\2\2\u01f7\u01fa\3\2\2\2\u01f8\u01f6\3\2\2\2\u01f8\u01f9\3\2"+
		"\2\2\u01f9\u01fe\3\2\2\2\u01fa\u01f8\3\2\2\2\u01fb\u01fd\7k\2\2\u01fc"+
		"\u01fb\3\2\2\2\u01fd\u0200\3\2\2\2\u01fe\u01fc\3\2\2\2\u01fe\u01ff\3\2"+
		"\2\2\u01ff9\3\2\2\2\u0200\u01fe\3\2\2\2\u0201\u0204\5<\37\2\u0202\u0204"+
		"\5H%\2\u0203\u0201\3\2\2\2\u0203\u0202\3\2\2\2\u0204;\3\2\2\2\u0205\u0207"+
		"\7V\2\2\u0206\u0208\7K\2\2\u0207\u0206\3\2\2\2\u0207\u0208\3\2\2\2\u0208"+
		"\u0209\3\2\2\2\u0209\u020e\5T+\2\u020a\u020b\7_\2\2\u020b\u020d\5T+\2"+
		"\u020c\u020a\3\2\2\2\u020d\u0210\3\2\2\2\u020e\u020c\3\2\2\2\u020e\u020f"+
		"\3\2\2\2\u020f\u0212\3\2\2\2\u0210\u020e\3\2\2\2\u0211\u0205\3\2\2\2\u0211"+
		"\u0212\3\2\2\2\u0212\u0213\3\2\2\2\u0213\u0219\5> \2\u0214\u0215\5L\'"+
		"\2\u0215\u0216\5> \2\u0216\u0218\3\2\2\2\u0217\u0214\3\2\2\2\u0218\u021b"+
		"\3\2\2\2\u0219\u0217\3\2\2\2\u0219\u021a\3\2\2\2\u021a\u0226\3\2\2\2\u021b"+
		"\u0219\3\2\2\2\u021c\u021d\7H\2\2\u021d\u021e\7\36\2\2\u021e\u0223\5V"+
		",\2\u021f\u0220\7_\2\2\u0220\u0222\5V,\2\u0221\u021f\3\2\2\2\u0222\u0225"+
		"\3\2\2\2\u0223\u0221\3\2\2\2\u0223\u0224\3\2\2\2\u0224\u0227\3\2\2\2\u0225"+
		"\u0223\3\2\2\2\u0226\u021c\3\2\2\2\u0226\u0227\3\2\2\2\u0227\u022e\3\2"+
		"\2\2\u0228\u0229\7>\2\2\u0229\u022c\5X-\2\u022a\u022b\t\3\2\2\u022b\u022d"+
		"\5X-\2\u022c\u022a\3\2\2\2\u022c\u022d\3\2\2\2\u022d\u022f\3\2\2\2\u022e"+
		"\u0228\3\2\2\2\u022e\u022f\3\2\2\2\u022f=\3\2\2\2\u0230\u0232\7O\2\2\u0231"+
		"\u0233\t\4\2\2\u0232\u0231\3\2\2\2\u0232\u0233\3\2\2\2\u0233\u0234\3\2"+
		"\2\2\u0234\u0239\5B\"\2\u0235\u0236\7_\2\2\u0236\u0238\5B\"\2\u0237\u0235"+
		"\3\2\2\2\u0238\u023b\3\2\2\2\u0239\u0237\3\2\2\2\u0239\u023a\3\2\2\2\u023a"+
		"\u023d\3\2\2\2\u023b\u0239\3\2\2\2\u023c\u0230\3\2\2\2\u023c\u023d\3\2"+
		"\2\2\u023d\u023e\3\2\2\2\u023e\u0248\7/\2\2\u023f\u0244\5D#\2\u0240\u0241"+
		"\7_\2\2\u0241\u0243\5D#\2\u0242\u0240\3\2\2\2\u0243\u0246\3\2\2\2\u0244"+
		"\u0242\3\2\2\2\u0244\u0245\3\2\2\2\u0245\u0249\3\2\2\2\u0246\u0244\3\2"+
		"\2\2\u0247\u0249\5N(\2\u0248\u023f\3\2\2\2\u0248\u0247\3\2\2\2\u0249\u024b"+
		"\3\2\2\2\u024a\u024c\5@!\2\u024b\u024a\3\2\2\2\u024b\u024c\3\2\2\2\u024c"+
		"?\3\2\2\2\u024d\u024e\7U\2\2\u024e\u024f\5X-\2\u024f\u025e\3\2\2\2\u0250"+
		"\u0251\7\61\2\2\u0251\u0252\7\36\2\2\u0252\u0257\5X-\2\u0253\u0254\7_"+
		"\2\2\u0254\u0256\5X-\2\u0255\u0253\3\2\2\2\u0256\u0259\3\2\2\2\u0257\u0255"+
		"\3\2\2\2\u0257\u0258\3\2\2\2\u0258\u025c\3\2\2\2\u0259\u0257\3\2\2\2\u025a"+
		"\u025b\7\62\2\2\u025b\u025d\5X-\2\u025c\u025a\3\2\2\2\u025c\u025d\3\2"+
		"\2\2\u025d\u025f\3\2\2\2\u025e\u0250\3\2\2\2\u025e\u025f\3\2\2\2\u025f"+
		"\u027d\3\2\2\2\u0260\u0261\7S\2\2\u0261\u0262\7e\2\2\u0262\u0267\5X-\2"+
		"\u0263\u0264\7_\2\2\u0264\u0266\5X-\2\u0265\u0263\3\2\2\2\u0266\u0269"+
		"\3\2\2\2\u0267\u0265\3\2\2\2\u0267\u0268\3\2\2\2\u0268\u026a\3\2\2\2\u0269"+
		"\u0267\3\2\2\2\u026a\u0279\7f\2\2\u026b\u026c\7_\2\2\u026c\u026d\7e\2"+
		"\2\u026d\u0272\5X-\2\u026e\u026f\7_\2\2\u026f\u0271\5X-\2\u0270\u026e"+
		"\3\2\2\2\u0271\u0274\3\2\2\2\u0272\u0270\3\2\2\2\u0272\u0273\3\2\2\2\u0273"+
		"\u0275\3\2\2\2\u0274\u0272\3\2\2\2\u0275\u0276\7f\2\2\u0276\u0278\3\2"+
		"\2\2\u0277\u026b\3\2\2\2\u0278\u027b\3\2\2\2\u0279\u0277\3\2\2\2\u0279"+
		"\u027a\3\2\2\2\u027a\u027d\3\2\2\2\u027b\u0279\3\2\2\2\u027c\u024d\3\2"+
		"\2\2\u027c\u0260\3\2\2\2\u027d\u02af\3\2\2\2\u027e\u027f\7U\2\2\u027f"+
		"\u0281\5X-\2\u0280\u027e\3\2\2\2\u0280\u0281\3\2\2\2\u0281\u0282\3\2\2"+
		"\2\u0282\u0283\7\61\2\2\u0283\u0284\7\36\2\2\u0284\u0289\5X-\2\u0285\u0286"+
		"\7_\2\2\u0286\u0288\5X-\2\u0287\u0285\3\2\2\2\u0288\u028b\3\2\2\2\u0289"+
		"\u0287\3\2\2\2\u0289\u028a\3\2\2\2\u028a\u028e\3\2\2\2\u028b\u0289\3\2"+
		"\2\2\u028c\u028d\7\62\2\2\u028d\u028f\5X-\2\u028e\u028c\3\2\2\2\u028e"+
		"\u028f\3\2\2\2\u028f\u02ad\3\2\2\2\u0290\u0291\7S\2\2\u0291\u0292\7e\2"+
		"\2\u0292\u0297\5X-\2\u0293\u0294\7_\2\2\u0294\u0296\5X-\2\u0295\u0293"+
		"\3\2\2\2\u0296\u0299\3\2\2\2\u0297\u0295\3\2\2\2\u0297\u0298\3\2\2\2\u0298"+
		"\u029a\3\2\2\2\u0299\u0297\3\2\2\2\u029a\u02a9\7f\2\2\u029b\u029c\7_\2"+
		"\2\u029c\u029d\7e\2\2\u029d\u02a2\5X-\2\u029e\u029f\7_\2\2\u029f\u02a1"+
		"\5X-\2\u02a0\u029e\3\2\2\2\u02a1\u02a4\3\2\2\2\u02a2\u02a0\3\2\2\2\u02a2"+
		"\u02a3\3\2\2\2\u02a3\u02a5\3\2\2\2\u02a4\u02a2\3\2\2\2\u02a5\u02a6\7f"+
		"\2\2\u02a6\u02a8\3\2\2\2\u02a7\u029b\3\2\2\2\u02a8\u02ab\3\2\2\2\u02a9"+
		"\u02a7\3\2\2\2\u02a9\u02aa\3\2\2\2\u02aa\u02ad\3\2\2\2\u02ab\u02a9\3\2"+
		"\2\2\u02ac\u0280\3\2\2\2\u02ac\u0290\3\2\2\2\u02ad\u02af\3\2\2\2\u02ae"+
		"\u027c\3\2\2\2\u02ae\u02ac\3\2\2\2\u02afA\3\2\2\2\u02b0\u02bd\7\6\2\2"+
		"\u02b1\u02b2\5p9\2\u02b2\u02b3\7d\2\2\u02b3\u02b4\7\6\2\2\u02b4\u02bd"+
		"\3\2\2\2\u02b5\u02ba\5X-\2\u02b6\u02b8\7\33\2\2\u02b7\u02b6\3\2\2\2\u02b7"+
		"\u02b8\3\2\2\2\u02b8\u02b9\3\2\2\2\u02b9\u02bb\5r:\2\u02ba\u02b7\3\2\2"+
		"\2\u02ba\u02bb\3\2\2\2\u02bb\u02bd\3\2\2\2\u02bc\u02b0\3\2\2\2\u02bc\u02b1"+
		"\3\2\2\2\u02bc\u02b5\3\2\2\2\u02bdC\3\2\2\2\u02be\u02bf\5n8\2\u02bf\u02c0"+
		"\7d\2\2\u02c0\u02c2\3\2\2\2\u02c1\u02be\3\2\2\2\u02c1\u02c2\3\2\2\2\u02c2"+
		"\u02c3\3\2\2\2\u02c3\u02c8\5p9\2\u02c4\u02c6\7\33\2\2\u02c5\u02c4\3\2"+
		"\2\2\u02c5\u02c6\3\2\2\2\u02c6\u02c7\3\2\2\2\u02c7\u02c9\5v<\2\u02c8\u02c5"+
		"\3\2\2\2\u02c8\u02c9\3\2\2\2\u02c9\u02cf\3\2\2\2\u02ca\u02cb\7\66\2\2"+
		"\u02cb\u02cc\7\36\2\2\u02cc\u02d0\5x=\2\u02cd\u02ce\7B\2\2\u02ce\u02d0"+
		"\7\66\2\2\u02cf\u02ca\3\2\2\2\u02cf\u02cd\3\2\2\2\u02cf\u02d0\3\2\2\2"+
		"\u02d0\u02ee\3\2\2\2\u02d1\u02db\7e\2\2\u02d2\u02d7\5D#\2\u02d3\u02d4"+
		"\7_\2\2\u02d4\u02d6\5D#\2\u02d5\u02d3\3\2\2\2\u02d6\u02d9\3\2\2\2\u02d7"+
		"\u02d5\3\2\2\2\u02d7\u02d8\3\2\2\2\u02d8\u02dc\3\2\2\2\u02d9\u02d7\3\2"+
		"\2\2\u02da\u02dc\5N(\2\u02db\u02d2\3\2\2\2\u02db\u02da\3\2\2\2\u02dc\u02dd"+
		"\3\2\2\2\u02dd\u02e2\7f\2\2\u02de\u02e0\7\33\2\2\u02df\u02de\3\2\2\2\u02df"+
		"\u02e0\3\2\2\2\u02e0\u02e1\3\2\2\2\u02e1\u02e3\5v<\2\u02e2\u02df\3\2\2"+
		"\2\u02e2\u02e3\3\2\2\2\u02e3\u02ee\3\2\2\2\u02e4\u02e5\7e\2\2\u02e5\u02e6"+
		"\5H%\2\u02e6\u02eb\7f\2\2\u02e7\u02e9\7\33\2\2\u02e8\u02e7\3\2\2\2\u02e8"+
		"\u02e9\3\2\2\2\u02e9\u02ea\3\2\2\2\u02ea\u02ec\5v<\2\u02eb\u02e8\3\2\2"+
		"\2\u02eb\u02ec\3\2\2\2\u02ec\u02ee\3\2\2\2\u02ed\u02c1\3\2\2\2\u02ed\u02d1"+
		"\3\2\2\2\u02ed\u02e4\3\2\2\2\u02eeE\3\2\2\2\u02ef\u02f0\t\5\2\2\u02f0"+
		"G\3\2\2\2\u02f1\u02f3\7V\2\2\u02f2\u02f4\7K\2\2\u02f3\u02f2\3\2\2\2\u02f3"+
		"\u02f4\3\2\2\2\u02f4\u02f5\3\2\2\2\u02f5\u02fa\5T+\2\u02f6\u02f7\7_\2"+
		"\2\u02f7\u02f9\5T+\2\u02f8\u02f6\3\2\2\2\u02f9\u02fc\3\2\2\2\u02fa\u02f8"+
		"\3\2\2\2\u02fa\u02fb\3\2\2\2\u02fb\u02fe\3\2\2\2\u02fc\u02fa\3\2\2\2\u02fd"+
		"\u02f1\3\2\2\2\u02fd\u02fe\3\2\2\2\u02fe\u02ff\3\2\2\2\u02ff\u0305\5J"+
		"&\2\u0300\u0301\5L\'\2\u0301\u0302\5J&\2\u0302\u0304\3\2\2\2\u0303\u0300"+
		"\3\2\2\2\u0304\u0307\3\2\2\2\u0305\u0303\3\2\2\2\u0305\u0306\3\2\2\2\u0306"+
		"\u0312\3\2\2\2\u0307\u0305\3\2\2\2\u0308\u0309\7H\2\2\u0309\u030a\7\36"+
		"\2\2\u030a\u030f\5V,\2\u030b\u030c\7_\2\2\u030c\u030e\5V,\2\u030d\u030b"+
		"\3\2\2\2\u030e\u0311\3\2\2\2\u030f\u030d\3\2\2\2\u030f\u0310\3\2\2\2\u0310"+
		"\u0313\3\2\2\2\u0311\u030f\3\2\2\2\u0312\u0308\3\2\2\2\u0312\u0313\3\2"+
		"\2\2\u0313\u031a\3\2\2\2\u0314\u0315\7>\2\2\u0315\u0318\5X-\2\u0316\u0317"+
		"\t\3\2\2\u0317\u0319\5X-\2\u0318\u0316\3\2\2\2\u0318\u0319\3\2\2\2\u0319"+
		"\u031b\3\2\2\2\u031a\u0314\3\2\2\2\u031a\u031b\3\2\2\2\u031bI\3\2\2\2"+
		"\u031c\u031e\7O\2\2\u031d\u031f\t\4\2\2\u031e\u031d\3\2\2\2\u031e\u031f"+
		"\3\2\2\2\u031f\u0320\3\2\2\2\u0320\u0325\5B\"\2\u0321\u0322\7_\2\2\u0322"+
		"\u0324\5B\"\2\u0323\u0321\3\2\2\2\u0324\u0327\3\2\2\2\u0325\u0323\3\2"+
		"\2\2\u0325\u0326\3\2\2\2\u0326\u0329\3\2\2\2\u0327\u0325\3\2\2\2\u0328"+
		"\u031c\3\2\2\2\u0328\u0329\3\2\2\2\u0329\u032a\3\2\2\2\u032a\u0334\7/"+
		"\2\2\u032b\u0330\5D#\2\u032c\u032d\7_\2\2\u032d\u032f\5D#\2\u032e\u032c"+
		"\3\2\2\2\u032f\u0332\3\2\2\2\u0330\u032e\3\2\2\2\u0330\u0331\3\2\2\2\u0331"+
		"\u0335\3\2\2\2\u0332\u0330\3\2\2\2\u0333\u0335\5N(\2\u0334\u032b\3\2\2"+
		"\2\u0334\u0333\3\2\2\2\u0335\u0337\3\2\2\2\u0336\u0338\5@!\2\u0337\u0336"+
		"\3\2\2\2\u0337\u0338\3\2\2\2\u0338K\3\2\2\2\u0339\u033f\7Q\2\2\u033a\u033b"+
		"\7Q\2\2\u033b\u033f\7\31\2\2\u033c\u033f\78\2\2\u033d\u033f\7+\2\2\u033e"+
		"\u0339\3\2\2\2\u033e\u033a\3\2\2\2\u033e\u033c\3\2\2\2\u033e\u033d\3\2"+
		"\2\2\u033fM\3\2\2\2\u0340\u034a\5D#\2\u0341\u0344\5P)\2\u0342\u0345\5"+
		"N(\2\u0343\u0345\5D#\2\u0344\u0342\3\2\2\2\u0344\u0343\3\2\2\2\u0345\u0346"+
		"\3\2\2\2\u0346\u0347\5R*\2\u0347\u0349\3\2\2\2\u0348\u0341\3\2\2\2\u0349"+
		"\u034c\3\2\2\2\u034a\u0348\3\2\2\2\u034a\u034b\3\2\2\2\u034bO\3\2\2\2"+
		"\u034c\u034a\3\2\2\2\u034d\u0363\7_\2\2\u034e\u0350\7@\2\2\u034f\u034e"+
		"\3\2\2\2\u034f\u0350\3\2\2\2\u0350\u035f\3\2\2\2\u0351\u0353\7<\2\2\u0352"+
		"\u0354\7I\2\2\u0353\u0352\3\2\2\2\u0353\u0354\3\2\2\2\u0354\u0360\3\2"+
		"\2\2\u0355\u0357\7M\2\2\u0356\u0358\7I\2\2\u0357\u0356\3\2\2\2\u0357\u0358"+
		"\3\2\2\2\u0358\u0360\3\2\2\2\u0359\u035b\7.\2\2\u035a\u035c\7I\2\2\u035b"+
		"\u035a\3\2\2\2\u035b\u035c\3\2\2\2\u035c\u0360\3\2\2\2\u035d\u0360\7\67"+
		"\2\2\u035e\u0360\7\"\2\2\u035f\u0351\3\2\2\2\u035f\u0355\3\2\2\2\u035f"+
		"\u0359\3\2\2\2\u035f\u035d\3\2\2\2\u035f\u035e\3\2\2\2\u035f\u0360\3\2"+
		"\2\2\u0360\u0361\3\2\2\2\u0361\u0363\7;\2\2\u0362\u034d\3\2\2\2\u0362"+
		"\u034f\3\2\2\2\u0363Q\3\2\2\2\u0364\u0365\7F\2\2\u0365\u0373\5X-\2\u0366"+
		"\u0367\7R\2\2\u0367\u0368\7e\2\2\u0368\u036d\5t;\2\u0369\u036a\7_\2\2"+
		"\u036a\u036c\5t;\2\u036b\u0369\3\2\2\2\u036c\u036f\3\2\2\2\u036d\u036b"+
		"\3\2\2\2\u036d\u036e\3\2\2\2\u036e\u0370\3\2\2\2\u036f\u036d\3\2\2\2\u0370"+
		"\u0371\7f\2\2\u0371\u0373\3\2\2\2\u0372\u0364\3\2\2\2\u0372\u0366\3\2"+
		"\2\2\u0372\u0373\3\2\2\2\u0373S\3\2\2\2\u0374\u0380\5p9\2\u0375\u0376"+
		"\7e\2\2\u0376\u037b\5t;\2\u0377\u0378\7_\2\2\u0378\u037a\5t;\2\u0379\u0377"+
		"\3\2\2\2\u037a\u037d\3\2\2\2\u037b\u0379\3\2\2\2\u037b\u037c\3\2\2\2\u037c"+
		"\u037e\3\2\2\2\u037d\u037b\3\2\2\2\u037e\u037f\7f\2\2\u037f\u0381\3\2"+
		"\2\2\u0380\u0375\3\2\2\2\u0380\u0381\3\2\2\2\u0381\u0382\3\2\2\2\u0382"+
		"\u0383\7\33\2\2\u0383\u0384\7e\2\2\u0384\u0385\5H%\2\u0385\u0386\7f\2"+
		"\2\u0386U\3\2\2\2\u0387\u038a\5X-\2\u0388\u0389\7!\2\2\u0389\u038b\5l"+
		"\67\2\u038a\u0388\3\2\2\2\u038a\u038b\3\2\2\2\u038b\u038d\3\2\2\2\u038c"+
		"\u038e\t\6\2\2\u038d\u038c\3\2\2\2\u038d\u038e\3\2\2\2\u038eW\3\2\2\2"+
		"\u038f\u0390\b-\1\2\u0390\u0391\5\\/\2\u0391\u0392\5X-\26\u0392\u03ca"+
		"\3\2\2\2\u0393\u03ca\5\b\5\2\u0394\u03ca\7p\2\2\u0395\u0396\5n8\2\u0396"+
		"\u0397\7d\2\2\u0397\u0399\3\2\2\2\u0398\u0395\3\2\2\2\u0398\u0399\3\2"+
		"\2\2\u0399\u039a\3\2\2\2\u039a\u039b\5v<\2\u039b\u039c\7d\2\2\u039c\u039e"+
		"\3\2\2\2\u039d\u0398\3\2\2\2\u039d\u039e\3\2\2\2\u039e\u039f\3\2\2\2\u039f"+
		"\u03ca\5t;\2\u03a0\u03a1\7e\2\2\u03a1\u03a2\5X-\2\u03a2\u03a3\7f\2\2\u03a3"+
		"\u03ca\3\2\2\2\u03a4\u03a5\7 \2\2\u03a5\u03a6\7e\2\2\u03a6\u03a7\5X-\2"+
		"\u03a7\u03a8\7\33\2\2\u03a8\u03a9\5`\61\2\u03a9\u03aa\7f\2\2\u03aa\u03ca"+
		"\3\2\2\2\u03ab\u03ad\7B\2\2\u03ac\u03ab\3\2\2\2\u03ac\u03ad\3\2\2\2\u03ad"+
		"\u03ae\3\2\2\2\u03ae\u03b0\7,\2\2\u03af\u03ac\3\2\2\2\u03af\u03b0\3\2"+
		"\2\2\u03b0\u03b1\3\2\2\2\u03b1\u03b2\7e\2\2\u03b2\u03b3\5H%\2\u03b3\u03b4"+
		"\7f\2\2\u03b4\u03ca\3\2\2\2\u03b5\u03b7\7\37\2\2\u03b6\u03b8\5X-\2\u03b7"+
		"\u03b6\3\2\2\2\u03b7\u03b8\3\2\2\2\u03b8\u03be\3\2\2\2\u03b9\u03ba\7T"+
		"\2\2\u03ba\u03bb\5X-\2\u03bb\u03bc\7P\2\2\u03bc\u03bd\5X-\2\u03bd\u03bf"+
		"\3\2\2\2\u03be\u03b9\3\2\2\2\u03bf\u03c0\3\2\2\2\u03c0\u03be\3\2\2\2\u03c0"+
		"\u03c1\3\2\2\2\u03c1\u03c4\3\2\2\2\u03c2\u03c3\7(\2\2\u03c3\u03c5\5X-"+
		"\2\u03c4\u03c2\3\2\2\2\u03c4\u03c5\3\2\2\2\u03c5\u03c6\3\2\2\2\u03c6\u03c7"+
		"\7)\2\2\u03c7\u03ca\3\2\2\2\u03c8\u03ca\5\u0080A\2\u03c9\u038f\3\2\2\2"+
		"\u03c9\u0393\3\2\2\2\u03c9\u0394\3\2\2\2\u03c9\u039d\3\2\2\2\u03c9\u03a0"+
		"\3\2\2\2\u03c9\u03a4\3\2\2\2\u03c9\u03af\3\2\2\2\u03c9\u03b5\3\2\2\2\u03c9"+
		"\u03c8\3\2\2\2\u03ca\u042f\3\2\2\2\u03cb\u03cc\f\25\2\2\u03cc\u03cd\7"+
		"\3\2\2\u03cd\u042e\5X-\26\u03ce\u03cf\f\24\2\2\u03cf\u03d0\t\7\2\2\u03d0"+
		"\u042e\5X-\25\u03d1\u03d2\f\23\2\2\u03d2\u03d3\t\b\2\2\u03d3\u042e\5X"+
		"-\24\u03d4\u03d5\f\22\2\2\u03d5\u03d6\t\t\2\2\u03d6\u042e\5X-\23\u03d7"+
		"\u03d8\f\21\2\2\u03d8\u03d9\t\n\2\2\u03d9\u042e\5X-\22\u03da\u03e7\f\20"+
		"\2\2\u03db\u03e8\7\22\2\2\u03dc\u03e8\7\23\2\2\u03dd\u03e8\7\24\2\2\u03de"+
		"\u03e8\7\25\2\2\u03df\u03e8\79\2\2\u03e0\u03e1\79\2\2\u03e1\u03e8\7B\2"+
		"\2\u03e2\u03e8\7\65\2\2\u03e3\u03e8\7=\2\2\u03e4\u03e8\7\60\2\2\u03e5"+
		"\u03e8\7?\2\2\u03e6\u03e8\7L\2\2\u03e7\u03db\3\2\2\2\u03e7\u03dc\3\2\2"+
		"\2\u03e7\u03dd\3\2\2\2\u03e7\u03de\3\2\2\2\u03e7\u03df\3\2\2\2\u03e7\u03e0"+
		"\3\2\2\2\u03e7\u03e2\3\2\2\2\u03e7\u03e3\3\2\2\2\u03e7\u03e4\3\2\2\2\u03e7"+
		"\u03e5\3\2\2\2\u03e7\u03e6\3\2\2\2\u03e8\u03e9\3\2\2\2\u03e9\u042e\5X"+
		"-\21\u03ea\u03eb\f\17\2\2\u03eb\u03ec\7\32\2\2\u03ec\u042e\5X-\20\u03ed"+
		"\u03ee\f\16\2\2\u03ee\u03ef\7G\2\2\u03ef\u042e\5X-\17\u03f0\u03f1\f\b"+
		"\2\2\u03f1\u03f3\79\2\2\u03f2\u03f4\7B\2\2\u03f3\u03f2\3\2\2\2\u03f3\u03f4"+
		"\3\2\2\2\u03f4\u03f5\3\2\2\2\u03f5\u042e\5X-\t\u03f6\u03f8\f\7\2\2\u03f7"+
		"\u03f9\7B\2\2\u03f8\u03f7\3\2\2\2\u03f8\u03f9\3\2\2\2\u03f9\u03fa\3\2"+
		"\2\2\u03fa\u03fb\7\35\2\2\u03fb\u03fc\5X-\2\u03fc\u03fd\7\32\2\2\u03fd"+
		"\u03fe\5X-\b\u03fe\u042e\3\2\2\2\u03ff\u0400\f\13\2\2\u0400\u0401\7!\2"+
		"\2\u0401\u042e\5l\67\2\u0402\u0404\f\n\2\2\u0403\u0405\7B\2\2\u0404\u0403"+
		"\3\2\2\2\u0404\u0405\3\2\2\2\u0405\u0406\3\2\2\2\u0406\u0407\t\13\2\2"+
		"\u0407\u040a\5X-\2\u0408\u0409\7*\2\2\u0409\u040b\5X-\2\u040a\u0408\3"+
		"\2\2\2\u040a\u040b\3\2\2\2\u040b\u042e\3\2\2\2\u040c\u0411\f\t\2\2\u040d"+
		"\u0412\7:\2\2\u040e\u0412\7C\2\2\u040f\u0410\7B\2\2\u0410\u0412\7D\2\2"+
		"\u0411\u040d\3\2\2\2\u0411\u040e\3\2\2\2\u0411\u040f\3\2\2\2\u0412\u042e"+
		"\3\2\2\2\u0413\u0415\f\6\2\2\u0414\u0416\7B\2\2\u0415\u0414\3\2\2\2\u0415"+
		"\u0416\3\2\2\2\u0416\u0417\3\2\2\2\u0417\u042b\7\65\2\2\u0418\u0422\7"+
		"e\2\2\u0419\u0423\5H%\2\u041a\u041f\5X-\2\u041b\u041c\7_\2\2\u041c\u041e"+
		"\5X-\2\u041d\u041b\3\2\2\2\u041e\u0421\3\2\2\2\u041f\u041d\3\2\2\2\u041f"+
		"\u0420\3\2\2\2\u0420\u0423\3\2\2\2\u0421\u041f\3\2\2\2\u0422\u0419\3\2"+
		"\2\2\u0422\u041a\3\2\2\2\u0422\u0423\3\2\2\2\u0423\u0424\3\2\2\2\u0424"+
		"\u042c\7f\2\2\u0425\u0426\5n8\2\u0426\u0427\7d\2\2\u0427\u0429\3\2\2\2"+
		"\u0428\u0425\3\2\2\2\u0428\u0429\3\2\2\2\u0429\u042a\3\2\2\2\u042a\u042c"+
		"\5p9\2\u042b\u0418\3\2\2\2\u042b\u0428\3\2\2\2\u042c\u042e\3\2\2\2\u042d"+
		"\u03cb\3\2\2\2\u042d\u03ce\3\2\2\2\u042d\u03d1\3\2\2\2\u042d\u03d4\3\2"+
		"\2\2\u042d\u03d7\3\2\2\2\u042d\u03da\3\2\2\2\u042d\u03ea\3\2\2\2\u042d"+
		"\u03ed\3\2\2\2\u042d\u03f0\3\2\2\2\u042d\u03f6\3\2\2\2\u042d\u03ff\3\2"+
		"\2\2\u042d\u0402\3\2\2\2\u042d\u040c\3\2\2\2\u042d\u0413\3\2\2\2\u042e"+
		"\u0431\3\2\2\2\u042f\u042d\3\2\2\2\u042f\u0430\3\2\2\2\u0430Y\3\2\2\2"+
		"\u0431\u042f\3\2\2\2\u0432\u0433\t\f\2\2\u0433[\3\2\2\2\u0434\u0435\t"+
		"\r\2\2\u0435]\3\2\2\2\u0436\u0437\5z>\2\u0437_\3\2\2\2\u0438\u043a\5^"+
		"\60\2\u0439\u0438\3\2\2\2\u043a\u043b\3\2\2\2\u043b\u0439\3\2\2\2\u043b"+
		"\u043c\3\2\2\2\u043c\u0447\3\2\2\2\u043d\u043e\7e\2\2\u043e\u043f\5~@"+
		"\2\u043f\u0440\7f\2\2\u0440\u0448\3\2\2\2\u0441\u0442\7e\2\2\u0442\u0443"+
		"\5~@\2\u0443\u0444\7_\2\2\u0444\u0445\5~@\2\u0445\u0446\7f\2\2\u0446\u0448"+
		"\3\2\2\2\u0447\u043d\3\2\2\2\u0447\u0441\3\2\2\2\u0447\u0448\3\2\2\2\u0448"+
		"a\3\2\2\2\u0449\u044a\5z>\2\u044ac\3\2\2\2\u044b\u044c\5f\64\2\u044ce"+
		"\3\2\2\2\u044d\u044e\t\16\2\2\u044eg\3\2\2\2\u044f\u0450\5j\66\2\u0450"+
		"i\3\2\2\2\u0451\u0452\7]\2\2\u0452k\3\2\2\2\u0453\u0454\5z>\2\u0454m\3"+
		"\2\2\2\u0455\u0456\5z>\2\u0456o\3\2\2\2\u0457\u0458\5z>\2\u0458q\3\2\2"+
		"\2\u0459\u045a\t\17\2\2\u045as\3\2\2\2\u045b\u045c\5z>\2\u045cu\3\2\2"+
		"\2\u045d\u045e\5z>\2\u045ew\3\2\2\2\u045f\u0460\5z>\2\u0460y\3\2\2\2\u0461"+
		"\u0465\5F$\2\u0462\u0465\7q\2\2\u0463\u0465\7r\2\2\u0464\u0461\3\2\2\2"+
		"\u0464\u0462\3\2\2\2\u0464\u0463\3\2\2\2\u0465{\3\2\2\2\u0466\u0467\7"+
		"r\2\2\u0467}\3\2\2\2\u0468\u046a\t\b\2\2\u0469\u0468\3\2\2\2\u0469\u046a"+
		"\3\2\2\2\u046a\u046b\3\2\2\2\u046b\u046c\7n\2\2\u046c\177\3\2\2\2\u046d"+
		"\u046e\7J\2\2\u046e\u0473\7e\2\2\u046f\u0474\7\64\2\2\u0470\u0471\t\20"+
		"\2\2\u0471\u0472\7_\2\2\u0472\u0474\5\u0082B\2\u0473\u046f\3\2\2\2\u0473"+
		"\u0470\3\2\2\2\u0474\u0475\3\2\2\2\u0475\u0476\7f\2\2\u0476\u0081\3\2"+
		"\2\2\u0477\u0478\7r\2\2\u0478\u0083\3\2\2\2\u009e\u0087\u008b\u008e\u0094"+
		"\u0098\u00a5\u00ac\u00b0\u00b3\u00b8\u00bf\u00d5\u00df\u00e5\u00e7\u00f1"+
		"\u00f7\u00f9\u0103\u0109\u010b\u0117\u011f\u0123\u012b\u0131\u0135\u013c"+
		"\u0140\u0145\u014a\u014e\u0153\u0158\u015c\u0169\u016c\u0172\u0177\u017b"+
		"\u017e\u0187\u018c\u0190\u0195\u019f\u01ac\u01b1\u01b6\u01d0\u01da\u01e1"+
		"\u01e6\u01ea\u01f3\u01f8\u01fe\u0203\u0207\u020e\u0211\u0219\u0223\u0226"+
		"\u022c\u022e\u0232\u0239\u023c\u0244\u0248\u024b\u0257\u025c\u025e\u0267"+
		"\u0272\u0279\u027c\u0280\u0289\u028e\u0297\u02a2\u02a9\u02ac\u02ae\u02b7"+
		"\u02ba\u02bc\u02c1\u02c5\u02c8\u02cf\u02d7\u02db\u02df\u02e2\u02e8\u02eb"+
		"\u02ed\u02f3\u02fa\u02fd\u0305\u030f\u0312\u0318\u031a\u031e\u0325\u0328"+
		"\u0330\u0334\u0337\u033e\u0344\u034a\u034f\u0353\u0357\u035b\u035f\u0362"+
		"\u036d\u0372\u037b\u0380\u038a\u038d\u0398\u039d\u03ac\u03af\u03b7\u03c0"+
		"\u03c4\u03c9\u03e7\u03f3\u03f8\u0404\u040a\u0411\u0415\u041f\u0422\u0428"+
		"\u042b\u042d\u042f\u043b\u0447\u0464\u0469\u0473";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}