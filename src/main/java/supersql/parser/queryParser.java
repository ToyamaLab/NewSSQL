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
		K_MAX=86, K_MIN=87, K_AVG=88, K_COUNT=89, K_SUM=90, C0=91, C1=92, C2=93, 
		C3=94, C4=95, CIRCLE=96, DOT=97, OPEN_PARENTHESE=98, CLOSE_PARENTHESE=99, 
		OPEN_BRACKET=100, CLOSE_BRACKET=101, OPEN_BRACE=102, CLOSE_BRACE=103, 
		SEMICOLON=104, DECORATOR=105, NUMERIC_LITERAL=106, BLOB_LITERAL=107, BIND_PARAMETER=108, 
		IDENTIFIER=109, STRING_LITERAL=110, MULTI_LINE_COMMENT=111, SINGLE_LINE_COMMENT=112, 
		WS=113, UNEXPECTED_CHAR=114;
	public static final int
		RULE_query = 0, RULE_root = 1, RULE_media = 2, RULE_operand = 3, RULE_attribute = 4, 
		RULE_join_string = 5, RULE_grouper = 6, RULE_composite_iterator = 7, RULE_exp = 8, 
		RULE_t_exp = 9, RULE_d_exp = 10, RULE_v_exp = 11, RULE_h_exp = 12, RULE_n_exp = 13, 
		RULE_sorting = 14, RULE_function = 15, RULE_sqlfunc = 16, RULE_aggregate = 17, 
		RULE_if_then_else = 18, RULE_arithmetics = 19, RULE_arith = 20, RULE_from_where = 21, 
		RULE_error = 22, RULE_sql_stmt_list = 23, RULE_sql_stmt = 24, RULE_factored_select_stmt = 25, 
		RULE_select_core = 26, RULE_where_clause = 27, RULE_result_column = 28, 
		RULE_table_or_subquery = 29, RULE_keyword = 30, RULE_select_stmt = 31, 
		RULE_select_or_values = 32, RULE_compound_operator = 33, RULE_join_clause = 34, 
		RULE_join_operator = 35, RULE_join_constraint = 36, RULE_common_table_expression = 37, 
		RULE_ordering_term = 38, RULE_expr = 39, RULE_literal_value = 40, RULE_unary_operator = 41, 
		RULE_name = 42, RULE_type_name = 43, RULE_function_name = 44, RULE_ag_function_name = 45, 
		RULE_ag_keyword = 46, RULE_collation_name = 47, RULE_database_name = 48, 
		RULE_table_name = 49, RULE_column_alias = 50, RULE_column_name = 51, RULE_table_alias = 52, 
		RULE_index_name = 53, RULE_any_name = 54, RULE_sl = 55, RULE_signed_number = 56, 
		RULE_raise_function = 57, RULE_error_message = 58;
	public static final String[] ruleNames = {
		"query", "root", "media", "operand", "attribute", "join_string", "grouper", 
		"composite_iterator", "exp", "t_exp", "d_exp", "v_exp", "h_exp", "n_exp", 
		"sorting", "function", "sqlfunc", "aggregate", "if_then_else", "arithmetics", 
		"arith", "from_where", "error", "sql_stmt_list", "sql_stmt", "factored_select_stmt", 
		"select_core", "where_clause", "result_column", "table_or_subquery", "keyword", 
		"select_stmt", "select_or_values", "compound_operator", "join_clause", 
		"join_operator", "join_constraint", "common_table_expression", "ordering_term", 
		"expr", "literal_value", "unary_operator", "name", "type_name", "function_name", 
		"ag_function_name", "ag_keyword", "collation_name", "database_name", "table_name", 
		"column_alias", "column_name", "table_alias", "index_name", "any_name", 
		"sl", "signed_number", "raise_function", "error_message"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'||'", "'$'", "'&'", "':'", "'*'", "'/'", "'+'", "'-'", "'<<'", 
		"'>>'", "'|'", "'<'", "'<='", "'>'", "'>='", "'='", "'=='", "'!='", "'<>'", 
		"'~'", null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, "'?'", 
		"','", "'!'", "'%'", "'#'", null, "'.'", "'('", "')'", "'['", "']'", "'{'", 
		"'}'", "';'"
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
		"C0", "C1", "C2", "C3", "C4", "CIRCLE", "DOT", "OPEN_PARENTHESE", "CLOSE_PARENTHESE", 
		"OPEN_BRACKET", "CLOSE_BRACKET", "OPEN_BRACE", "CLOSE_BRACE", "SEMICOLON", 
		"DECORATOR", "NUMERIC_LITERAL", "BLOB_LITERAL", "BIND_PARAMETER", "IDENTIFIER", 
		"STRING_LITERAL", "MULTI_LINE_COMMENT", "SINGLE_LINE_COMMENT", "WS", "UNEXPECTED_CHAR"
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
			setState(118);
			media();
			setState(119);
			root();
			setState(121);
			_la = _input.LA(1);
			if (_la==K_FROM || ((((_la - 77)) & ~0x3f) == 0 && ((1L << (_la - 77)) & ((1L << (K_SELECT - 77)) | (1L << (K_WITH - 77)) | (1L << (UNEXPECTED_CHAR - 77)))) != 0)) {
				{
				setState(120);
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
			setState(125);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(123);
				operand();
				}
				break;
			case 2:
				{
				setState(124);
				exp();
				}
				break;
			}
			setState(128);
			_la = _input.LA(1);
			if (_la==DECORATOR) {
				{
				setState(127);
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
			setState(130);
			match(K_GENERATE);
			setState(131);
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
		public Join_stringContext join_string() {
			return getRuleContext(Join_stringContext.class,0);
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
		public TerminalNode NUMERIC_LITERAL() { return getToken(queryParser.NUMERIC_LITERAL, 0); }
		public AggregateContext aggregate() {
			return getRuleContext(AggregateContext.class,0);
		}
		public ArithmeticsContext arithmetics() {
			return getRuleContext(ArithmeticsContext.class,0);
		}
		public SlContext sl() {
			return getRuleContext(SlContext.class,0);
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
			setState(157);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(134);
				_la = _input.LA(1);
				if (_la==OPEN_PARENTHESE) {
					{
					setState(133);
					sorting();
					}
				}

				setState(136);
				attribute();
				}
				break;
			case 2:
				{
				setState(138);
				switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(137);
					sorting();
					}
					break;
				}
				setState(140);
				join_string();
				}
				break;
			case 3:
				{
				setState(141);
				function();
				}
				break;
			case 4:
				{
				setState(142);
				sqlfunc();
				}
				break;
			case 5:
				{
				setState(143);
				match(OPEN_BRACE);
				setState(144);
				exp();
				setState(145);
				match(CLOSE_BRACE);
				}
				break;
			case 6:
				{
				setState(147);
				grouper();
				}
				break;
			case 7:
				{
				setState(148);
				composite_iterator();
				}
				break;
			case 8:
				{
				setState(149);
				if_then_else();
				}
				break;
			case 9:
				{
				setState(150);
				match(NUMERIC_LITERAL);
				}
				break;
			case 10:
				{
				setState(152);
				_la = _input.LA(1);
				if (_la==OPEN_PARENTHESE) {
					{
					setState(151);
					sorting();
					}
				}

				setState(154);
				aggregate();
				}
				break;
			case 11:
				{
				setState(155);
				arithmetics(0);
				}
				break;
			case 12:
				{
				setState(156);
				sl();
				}
				break;
			}
			setState(160);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(159);
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
			setState(165);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(162);
				table_alias();
				setState(163);
				match(DOT);
				}
				break;
			}
			setState(167);
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

	public static class Join_stringContext extends ParserRuleContext {
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public List<TerminalNode> NUMERIC_LITERAL() { return getTokens(queryParser.NUMERIC_LITERAL); }
		public TerminalNode NUMERIC_LITERAL(int i) {
			return getToken(queryParser.NUMERIC_LITERAL, i);
		}
		public List<ArithmeticsContext> arithmetics() {
			return getRuleContexts(ArithmeticsContext.class);
		}
		public ArithmeticsContext arithmetics(int i) {
			return getRuleContext(ArithmeticsContext.class,i);
		}
		public List<SlContext> sl() {
			return getRuleContexts(SlContext.class);
		}
		public SlContext sl(int i) {
			return getRuleContext(SlContext.class,i);
		}
		public Join_stringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterJoin_string(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitJoin_string(this);
		}
	}

	public final Join_stringContext join_string() throws RecognitionException {
		Join_stringContext _localctx = new Join_stringContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_join_string);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(173);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(169);
				attribute();
				}
				break;
			case 2:
				{
				setState(170);
				match(NUMERIC_LITERAL);
				}
				break;
			case 3:
				{
				setState(171);
				arithmetics(0);
				}
				break;
			case 4:
				{
				setState(172);
				sl();
				}
				break;
			}
			setState(182); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(175);
					match(T__0);
					setState(180);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
					case 1:
						{
						setState(176);
						attribute();
						}
						break;
					case 2:
						{
						setState(177);
						match(NUMERIC_LITERAL);
						}
						break;
					case 3:
						{
						setState(178);
						arithmetics(0);
						}
						break;
					case 4:
						{
						setState(179);
						sl();
						}
						break;
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(184); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			match(OPEN_BRACKET);
			setState(187);
			exp();
			setState(188);
			match(CLOSE_BRACKET);
			setState(189);
			_la = _input.LA(1);
			if ( !(((((_la - 92)) & ~0x3f) == 0 && ((1L << (_la - 92)) & ((1L << (C1 - 92)) | (1L << (C2 - 92)) | (1L << (C3 - 92)) | (1L << (C4 - 92)) | (1L << (CIRCLE - 92)))) != 0)) ) {
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

	public static class Composite_iteratorContext extends ParserRuleContext {
		public Token grouper_token;
		public TerminalNode OPEN_BRACKET() { return getToken(queryParser.OPEN_BRACKET, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(queryParser.CLOSE_BRACKET, 0); }
		public List<TerminalNode> C1() { return getTokens(queryParser.C1); }
		public TerminalNode C1(int i) {
			return getToken(queryParser.C1, i);
		}
		public List<TerminalNode> C2() { return getTokens(queryParser.C2); }
		public TerminalNode C2(int i) {
			return getToken(queryParser.C2, i);
		}
		public List<TerminalNode> C3() { return getTokens(queryParser.C3); }
		public TerminalNode C3(int i) {
			return getToken(queryParser.C3, i);
		}
		public List<TerminalNode> C4() { return getTokens(queryParser.C4); }
		public TerminalNode C4(int i) {
			return getToken(queryParser.C4, i);
		}
		public List<TerminalNode> CIRCLE() { return getTokens(queryParser.CIRCLE); }
		public TerminalNode CIRCLE(int i) {
			return getToken(queryParser.CIRCLE, i);
		}
		public List<TerminalNode> NUMERIC_LITERAL() { return getTokens(queryParser.NUMERIC_LITERAL); }
		public TerminalNode NUMERIC_LITERAL(int i) {
			return getToken(queryParser.NUMERIC_LITERAL, i);
		}
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
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			match(OPEN_BRACKET);
			setState(192);
			exp();
			setState(193);
			match(CLOSE_BRACKET);
			List<String> groupersList = new ArrayList<>();
			setState(198); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(195);
					((Composite_iteratorContext)_localctx).grouper_token = _input.LT(1);
					_la = _input.LA(1);
					if ( !(((((_la - 92)) & ~0x3f) == 0 && ((1L << (_la - 92)) & ((1L << (C1 - 92)) | (1L << (C2 - 92)) | (1L << (C3 - 92)) | (1L << (C4 - 92)) | (1L << (CIRCLE - 92)))) != 0)) ) {
						((Composite_iteratorContext)_localctx).grouper_token = (Token)_errHandler.recoverInline(this);
					} else {
						consume();
					}
					setState(196);
					match(NUMERIC_LITERAL);
					groupersList.add((((Composite_iteratorContext)_localctx).grouper_token!=null?((Composite_iteratorContext)_localctx).grouper_token.getText():null));
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(200); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(202);
			((Composite_iteratorContext)_localctx).grouper_token = _input.LT(1);
			_la = _input.LA(1);
			if ( !(((((_la - 92)) & ~0x3f) == 0 && ((1L << (_la - 92)) & ((1L << (C1 - 92)) | (1L << (C2 - 92)) | (1L << (C3 - 92)) | (1L << (C4 - 92)) | (1L << (CIRCLE - 92)))) != 0)) ) {
				((Composite_iteratorContext)_localctx).grouper_token = (Token)_errHandler.recoverInline(this);
			} else {
				consume();
			}
			groupersList.add((((Composite_iteratorContext)_localctx).grouper_token!=null?((Composite_iteratorContext)_localctx).grouper_token.getText():null));
			Set<String> groupersSet = new HashSet<>(groupersList); if(groupersList.size() != groupersSet.size()){throw new RuntimeException("Two identical connectors used in composite connector at line " + (((Composite_iteratorContext)_localctx).grouper_token!=null?((Composite_iteratorContext)_localctx).grouper_token.getLine():0));}
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
			setState(206);
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
			setState(208);
			d_exp();
			setState(216);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(209);
					match(C4);
					setState(212);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						setState(210);
						d_exp();
						}
						break;
					case 2:
						{
						setState(211);
						operand();
						}
						break;
					}
					}
					} 
				}
				setState(218);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
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
		enterRule(_localctx, 20, RULE_d_exp);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			v_exp();
			setState(227);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(220);
					match(C3);
					setState(223);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						setState(221);
						v_exp();
						}
						break;
					case 2:
						{
						setState(222);
						operand();
						}
						break;
					}
					}
					} 
				}
				setState(229);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
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
		enterRule(_localctx, 22, RULE_v_exp);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			h_exp();
			setState(238);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(231);
					match(C2);
					setState(234);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
					case 1:
						{
						setState(232);
						h_exp();
						}
						break;
					case 2:
						{
						setState(233);
						operand();
						}
						break;
					}
					}
					} 
				}
				setState(240);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
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
		enterRule(_localctx, 24, RULE_h_exp);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(243);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(241);
				operand();
				}
				break;
			case 2:
				{
				setState(242);
				n_exp();
				}
				break;
			}
			setState(252);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(245);
					match(C1);
					setState(248);
					switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
					case 1:
						{
						setState(246);
						operand();
						}
						break;
					case 2:
						{
						setState(247);
						n_exp();
						}
						break;
					}
					}
					} 
				}
				setState(254);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
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
		public TerminalNode C0() { return getToken(queryParser.C0, 0); }
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
		enterRule(_localctx, 26, RULE_n_exp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			operand();
			setState(256);
			match(C0);
			setState(257);
			operand();
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
		enterRule(_localctx, 28, RULE_sorting);
		try {
			setState(265);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(259);
				match(OPEN_PARENTHESE);
				setState(260);
				match(K_ASC);
				setState(261);
				match(CLOSE_PARENTHESE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(262);
				match(OPEN_PARENTHESE);
				setState(263);
				match(K_DESC);
				setState(264);
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
		enterRule(_localctx, 30, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(268);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(267);
				match(T__1);
				}
			}

			setState(270);
			function_name();
			setState(271);
			match(OPEN_PARENTHESE);
			{
			setState(274);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(272);
				operand();
				}
				break;
			case 2:
				{
				setState(273);
				exp();
				}
				break;
			}
			setState(283);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==C1) {
				{
				{
				{
				setState(276);
				match(C1);
				}
				setState(279);
				switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
				case 1:
					{
					setState(277);
					operand();
					}
					break;
				case 2:
					{
					setState(278);
					exp();
					}
					break;
				}
				}
				}
				setState(285);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			setState(286);
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
		enterRule(_localctx, 32, RULE_sqlfunc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(288);
			match(T__2);
			setState(289);
			function_name();
			setState(290);
			match(OPEN_PARENTHESE);
			setState(307);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << K_ABORT) | (1L << K_ALL) | (1L << K_AND) | (1L << K_AS) | (1L << K_ASC) | (1L << K_BETWEEN) | (1L << K_BY) | (1L << K_CASE) | (1L << K_CAST) | (1L << K_COLLATE) | (1L << K_CROSS) | (1L << K_CURRENT_DATE) | (1L << K_CURRENT_TIME) | (1L << K_CURRENT_TIMESTAMP) | (1L << K_DESC) | (1L << K_DISTINCT) | (1L << K_ELSE) | (1L << K_END) | (1L << K_ESCAPE) | (1L << K_EXCEPT) | (1L << K_EXISTS) | (1L << K_FAIL) | (1L << K_FULL) | (1L << K_FROM) | (1L << K_GLOB) | (1L << K_GROUP) | (1L << K_HAVING) | (1L << K_IF) | (1L << K_IGNORE) | (1L << K_IN) | (1L << K_INDEXED) | (1L << K_INNER) | (1L << K_INTERSECT) | (1L << K_IS) | (1L << K_ISNULL) | (1L << K_JOIN) | (1L << K_LEFT) | (1L << K_LIKE) | (1L << K_LIMIT) | (1L << K_MATCH) | (1L << K_NATURAL) | (1L << K_NO))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (K_NOT - 64)) | (1L << (K_NOTNULL - 64)) | (1L << (K_NULL - 64)) | (1L << (K_OFFSET - 64)) | (1L << (K_ON - 64)) | (1L << (K_OR - 64)) | (1L << (K_ORDER - 64)) | (1L << (K_OUTER - 64)) | (1L << (K_RAISE - 64)) | (1L << (K_RECURSIVE - 64)) | (1L << (K_REGEXP - 64)) | (1L << (K_RIGHT - 64)) | (1L << (K_ROLLBACK - 64)) | (1L << (K_SELECT - 64)) | (1L << (K_THEN - 64)) | (1L << (K_UNION - 64)) | (1L << (K_USING - 64)) | (1L << (K_VALUES - 64)) | (1L << (K_WHEN - 64)) | (1L << (K_WHERE - 64)) | (1L << (K_WITH - 64)) | (1L << (K_GENERATE - 64)) | (1L << (K_MAX - 64)) | (1L << (K_MIN - 64)) | (1L << (K_AVG - 64)) | (1L << (K_COUNT - 64)) | (1L << (K_SUM - 64)) | (1L << (OPEN_PARENTHESE - 64)) | (1L << (OPEN_BRACKET - 64)) | (1L << (OPEN_BRACE - 64)) | (1L << (NUMERIC_LITERAL - 64)) | (1L << (IDENTIFIER - 64)) | (1L << (STRING_LITERAL - 64)))) != 0)) {
				{
				{
				setState(293);
				switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
				case 1:
					{
					setState(291);
					operand();
					}
					break;
				case 2:
					{
					setState(292);
					exp();
					}
					break;
				}
				setState(302);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(295);
					match(C1);
					setState(298);
					switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
					case 1:
						{
						setState(296);
						operand();
						}
						break;
					case 2:
						{
						setState(297);
						exp();
						}
						break;
					}
					}
					}
					setState(304);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(309);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(310);
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
		enterRule(_localctx, 34, RULE_aggregate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			ag_function_name();
			setState(313);
			match(OPEN_BRACKET);
			setState(314);
			attribute();
			setState(315);
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
		enterRule(_localctx, 36, RULE_if_then_else);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(338);
			switch (_input.LA(1)) {
			case K_IF:
				{
				{
				setState(317);
				match(K_IF);
				setState(318);
				match(OPEN_PARENTHESE);
				setState(319);
				expr(0);
				setState(320);
				match(CLOSE_PARENTHESE);
				setState(321);
				match(K_THEN);
				setState(322);
				match(OPEN_PARENTHESE);
				setState(323);
				exp();
				setState(324);
				match(CLOSE_PARENTHESE);
				setState(325);
				match(K_ELSE);
				setState(326);
				match(OPEN_PARENTHESE);
				setState(327);
				exp();
				setState(328);
				match(CLOSE_PARENTHESE);
				}
				}
				break;
			case OPEN_PARENTHESE:
				{
				{
				setState(330);
				match(OPEN_PARENTHESE);
				setState(331);
				expr(0);
				setState(332);
				match(CLOSE_PARENTHESE);
				setState(333);
				match(C0);
				setState(334);
				exp();
				setState(335);
				match(T__3);
				setState(336);
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
		int _startState = 38;
		enterRecursionRule(_localctx, 38, RULE_arithmetics, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(348);
			switch (_input.LA(1)) {
			case OPEN_PARENTHESE:
				{
				setState(341);
				match(OPEN_PARENTHESE);
				setState(342);
				arithmetics(0);
				setState(343);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7))) != 0) || _la==C3) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(344);
				arithmetics(0);
				setState(345);
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
			case NUMERIC_LITERAL:
			case IDENTIFIER:
				{
				setState(347);
				arith();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(355);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ArithmeticsContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_arithmetics);
					setState(350);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(351);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7))) != 0) || _la==C3) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					setState(352);
					arithmetics(3);
					}
					} 
				}
				setState(357);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
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
		enterRule(_localctx, 40, RULE_arith);
		try {
			setState(360);
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
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(358);
				attribute();
				}
				break;
			case NUMERIC_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(359);
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
		enterRule(_localctx, 42, RULE_from_where);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(364);
			switch (_input.LA(1)) {
			case K_FROM:
			case K_SELECT:
			case K_WITH:
				{
				setState(362);
				sql_stmt_list();
				}
				break;
			case UNEXPECTED_CHAR:
				{
				setState(363);
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
		enterRule(_localctx, 44, RULE_error);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(366);
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
		enterRule(_localctx, 46, RULE_sql_stmt_list);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(369);
			sql_stmt();
			setState(378);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(371); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(370);
						match(SEMICOLON);
						}
						}
						setState(373); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==SEMICOLON );
					setState(375);
					sql_stmt();
					}
					} 
				}
				setState(380);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			}
			setState(384);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMICOLON) {
				{
				{
				setState(381);
				match(SEMICOLON);
				}
				}
				setState(386);
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
		enterRule(_localctx, 48, RULE_sql_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(389);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				{
				setState(387);
				factored_select_stmt();
				}
				break;
			case 2:
				{
				setState(388);
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
		enterRule(_localctx, 50, RULE_factored_select_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(403);
			_la = _input.LA(1);
			if (_la==K_WITH) {
				{
				setState(391);
				match(K_WITH);
				setState(393);
				switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
				case 1:
					{
					setState(392);
					match(K_RECURSIVE);
					}
					break;
				}
				setState(395);
				common_table_expression();
				setState(400);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(396);
					match(C1);
					setState(397);
					common_table_expression();
					}
					}
					setState(402);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(405);
			select_core();
			setState(411);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 41)) & ~0x3f) == 0 && ((1L << (_la - 41)) & ((1L << (K_EXCEPT - 41)) | (1L << (K_INTERSECT - 41)) | (1L << (K_UNION - 41)))) != 0)) {
				{
				{
				setState(406);
				compound_operator();
				setState(407);
				select_core();
				}
				}
				setState(413);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(424);
			_la = _input.LA(1);
			if (_la==K_ORDER) {
				{
				setState(414);
				match(K_ORDER);
				setState(415);
				match(K_BY);
				setState(416);
				ordering_term();
				setState(421);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(417);
					match(C1);
					setState(418);
					ordering_term();
					}
					}
					setState(423);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(432);
			_la = _input.LA(1);
			if (_la==K_LIMIT) {
				{
				setState(426);
				match(K_LIMIT);
				setState(427);
				expr(0);
				setState(430);
				_la = _input.LA(1);
				if (_la==K_OFFSET || _la==C1) {
					{
					setState(428);
					_la = _input.LA(1);
					if ( !(_la==K_OFFSET || _la==C1) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					setState(429);
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
		enterRule(_localctx, 52, RULE_select_core);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(446);
			_la = _input.LA(1);
			if (_la==K_SELECT) {
				{
				setState(434);
				match(K_SELECT);
				setState(436);
				switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
				case 1:
					{
					setState(435);
					_la = _input.LA(1);
					if ( !(_la==K_ALL || _la==K_DISTINCT) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					break;
				}
				setState(438);
				result_column();
				setState(443);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(439);
					match(C1);
					setState(440);
					result_column();
					}
					}
					setState(445);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			{
			setState(448);
			match(K_FROM);
			setState(458);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
			case 1:
				{
				setState(449);
				table_or_subquery();
				setState(454);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(450);
					match(C1);
					setState(451);
					table_or_subquery();
					}
					}
					setState(456);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				{
				setState(457);
				join_clause();
				}
				break;
			}
			}
			setState(461);
			_la = _input.LA(1);
			if (((((_la - 47)) & ~0x3f) == 0 && ((1L << (_la - 47)) & ((1L << (K_GROUP - 47)) | (1L << (K_VALUES - 47)) | (1L << (K_WHERE - 47)))) != 0)) {
				{
				setState(460);
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
		enterRule(_localctx, 54, RULE_where_clause);
		int _la;
		try {
			setState(560);
			switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(510);
				switch (_input.LA(1)) {
				case K_WHERE:
					{
					{
					setState(463);
					match(K_WHERE);
					setState(464);
					expr(0);
					}
					setState(480);
					_la = _input.LA(1);
					if (_la==K_GROUP) {
						{
						setState(466);
						match(K_GROUP);
						setState(467);
						match(K_BY);
						setState(468);
						expr(0);
						setState(473);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==C1) {
							{
							{
							setState(469);
							match(C1);
							setState(470);
							expr(0);
							}
							}
							setState(475);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(478);
						_la = _input.LA(1);
						if (_la==K_HAVING) {
							{
							setState(476);
							match(K_HAVING);
							setState(477);
							expr(0);
							}
						}

						}
					}

					}
					break;
				case K_VALUES:
					{
					setState(482);
					match(K_VALUES);
					setState(483);
					match(OPEN_PARENTHESE);
					setState(484);
					expr(0);
					setState(489);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==C1) {
						{
						{
						setState(485);
						match(C1);
						setState(486);
						expr(0);
						}
						}
						setState(491);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(492);
					match(CLOSE_PARENTHESE);
					setState(507);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==C1) {
						{
						{
						setState(493);
						match(C1);
						setState(494);
						match(OPEN_PARENTHESE);
						setState(495);
						expr(0);
						setState(500);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==C1) {
							{
							{
							setState(496);
							match(C1);
							setState(497);
							expr(0);
							}
							}
							setState(502);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(503);
						match(CLOSE_PARENTHESE);
						}
						}
						setState(509);
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
				setState(558);
				switch (_input.LA(1)) {
				case K_GROUP:
				case K_WHERE:
					{
					setState(514);
					_la = _input.LA(1);
					if (_la==K_WHERE) {
						{
						setState(512);
						match(K_WHERE);
						setState(513);
						expr(0);
						}
					}

					{
					setState(516);
					match(K_GROUP);
					setState(517);
					match(K_BY);
					setState(518);
					expr(0);
					setState(523);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==C1) {
						{
						{
						setState(519);
						match(C1);
						setState(520);
						expr(0);
						}
						}
						setState(525);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(528);
					_la = _input.LA(1);
					if (_la==K_HAVING) {
						{
						setState(526);
						match(K_HAVING);
						setState(527);
						expr(0);
						}
					}

					}
					}
					break;
				case K_VALUES:
					{
					setState(530);
					match(K_VALUES);
					setState(531);
					match(OPEN_PARENTHESE);
					setState(532);
					expr(0);
					setState(537);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==C1) {
						{
						{
						setState(533);
						match(C1);
						setState(534);
						expr(0);
						}
						}
						setState(539);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(540);
					match(CLOSE_PARENTHESE);
					setState(555);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==C1) {
						{
						{
						setState(541);
						match(C1);
						setState(542);
						match(OPEN_PARENTHESE);
						setState(543);
						expr(0);
						setState(548);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==C1) {
							{
							{
							setState(544);
							match(C1);
							setState(545);
							expr(0);
							}
							}
							setState(550);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(551);
						match(CLOSE_PARENTHESE);
						}
						}
						setState(557);
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
		enterRule(_localctx, 56, RULE_result_column);
		int _la;
		try {
			setState(574);
			switch ( getInterpreter().adaptivePredict(_input,71,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(562);
				match(T__4);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(563);
				table_name();
				setState(564);
				match(DOT);
				setState(565);
				match(T__4);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(567);
				expr(0);
				setState(572);
				_la = _input.LA(1);
				if (_la==K_AS || _la==IDENTIFIER || _la==STRING_LITERAL) {
					{
					setState(569);
					_la = _input.LA(1);
					if (_la==K_AS) {
						{
						setState(568);
						match(K_AS);
						}
					}

					setState(571);
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
		enterRule(_localctx, 58, RULE_table_or_subquery);
		int _la;
		try {
			setState(623);
			switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(579);
				switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
				case 1:
					{
					setState(576);
					database_name();
					setState(577);
					match(DOT);
					}
					break;
				}
				setState(581);
				table_name();
				setState(586);
				switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
				case 1:
					{
					setState(583);
					switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
					case 1:
						{
						setState(582);
						match(K_AS);
						}
						break;
					}
					setState(585);
					table_alias();
					}
					break;
				}
				setState(593);
				switch (_input.LA(1)) {
				case K_INDEXED:
					{
					setState(588);
					match(K_INDEXED);
					setState(589);
					match(K_BY);
					setState(590);
					index_name();
					}
					break;
				case K_NOT:
					{
					setState(591);
					match(K_NOT);
					setState(592);
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
				setState(595);
				match(OPEN_PARENTHESE);
				setState(605);
				switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
				case 1:
					{
					setState(596);
					table_or_subquery();
					setState(601);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==C1) {
						{
						{
						setState(597);
						match(C1);
						setState(598);
						table_or_subquery();
						}
						}
						setState(603);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				case 2:
					{
					setState(604);
					join_clause();
					}
					break;
				}
				setState(607);
				match(CLOSE_PARENTHESE);
				setState(612);
				switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
				case 1:
					{
					setState(609);
					switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
					case 1:
						{
						setState(608);
						match(K_AS);
						}
						break;
					}
					setState(611);
					table_alias();
					}
					break;
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(614);
				match(OPEN_PARENTHESE);
				setState(615);
				select_stmt();
				setState(616);
				match(CLOSE_PARENTHESE);
				setState(621);
				switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
				case 1:
					{
					setState(618);
					switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
					case 1:
						{
						setState(617);
						match(K_AS);
						}
						break;
					}
					setState(620);
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
		enterRule(_localctx, 60, RULE_keyword);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(625);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_ABORT) | (1L << K_ALL) | (1L << K_AND) | (1L << K_AS) | (1L << K_ASC) | (1L << K_BETWEEN) | (1L << K_BY) | (1L << K_CASE) | (1L << K_CAST) | (1L << K_COLLATE) | (1L << K_CROSS) | (1L << K_CURRENT_DATE) | (1L << K_CURRENT_TIME) | (1L << K_CURRENT_TIMESTAMP) | (1L << K_DESC) | (1L << K_DISTINCT) | (1L << K_ELSE) | (1L << K_END) | (1L << K_ESCAPE) | (1L << K_EXCEPT) | (1L << K_EXISTS) | (1L << K_FAIL) | (1L << K_FULL) | (1L << K_FROM) | (1L << K_GLOB) | (1L << K_GROUP) | (1L << K_HAVING) | (1L << K_IF) | (1L << K_IGNORE) | (1L << K_IN) | (1L << K_INDEXED) | (1L << K_INNER) | (1L << K_INTERSECT) | (1L << K_IS) | (1L << K_ISNULL) | (1L << K_JOIN) | (1L << K_LEFT) | (1L << K_LIKE) | (1L << K_LIMIT) | (1L << K_MATCH) | (1L << K_NATURAL) | (1L << K_NO))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (K_NOT - 64)) | (1L << (K_NOTNULL - 64)) | (1L << (K_NULL - 64)) | (1L << (K_OFFSET - 64)) | (1L << (K_ON - 64)) | (1L << (K_OR - 64)) | (1L << (K_ORDER - 64)) | (1L << (K_OUTER - 64)) | (1L << (K_RAISE - 64)) | (1L << (K_RECURSIVE - 64)) | (1L << (K_REGEXP - 64)) | (1L << (K_RIGHT - 64)) | (1L << (K_ROLLBACK - 64)) | (1L << (K_SELECT - 64)) | (1L << (K_THEN - 64)) | (1L << (K_UNION - 64)) | (1L << (K_USING - 64)) | (1L << (K_VALUES - 64)) | (1L << (K_WHEN - 64)) | (1L << (K_WHERE - 64)) | (1L << (K_WITH - 64)) | (1L << (K_GENERATE - 64)) | (1L << (K_MAX - 64)) | (1L << (K_MIN - 64)) | (1L << (K_AVG - 64)) | (1L << (K_COUNT - 64)) | (1L << (K_SUM - 64)))) != 0)) ) {
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
		enterRule(_localctx, 62, RULE_select_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(639);
			_la = _input.LA(1);
			if (_la==K_WITH) {
				{
				setState(627);
				match(K_WITH);
				setState(629);
				switch ( getInterpreter().adaptivePredict(_input,83,_ctx) ) {
				case 1:
					{
					setState(628);
					match(K_RECURSIVE);
					}
					break;
				}
				setState(631);
				common_table_expression();
				setState(636);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(632);
					match(C1);
					setState(633);
					common_table_expression();
					}
					}
					setState(638);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(641);
			select_or_values();
			setState(647);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 41)) & ~0x3f) == 0 && ((1L << (_la - 41)) & ((1L << (K_EXCEPT - 41)) | (1L << (K_INTERSECT - 41)) | (1L << (K_UNION - 41)))) != 0)) {
				{
				{
				setState(642);
				compound_operator();
				setState(643);
				select_or_values();
				}
				}
				setState(649);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(660);
			_la = _input.LA(1);
			if (_la==K_ORDER) {
				{
				setState(650);
				match(K_ORDER);
				setState(651);
				match(K_BY);
				setState(652);
				ordering_term();
				setState(657);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(653);
					match(C1);
					setState(654);
					ordering_term();
					}
					}
					setState(659);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(668);
			_la = _input.LA(1);
			if (_la==K_LIMIT) {
				{
				setState(662);
				match(K_LIMIT);
				setState(663);
				expr(0);
				setState(666);
				_la = _input.LA(1);
				if (_la==K_OFFSET || _la==C1) {
					{
					setState(664);
					_la = _input.LA(1);
					if ( !(_la==K_OFFSET || _la==C1) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					setState(665);
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
		enterRule(_localctx, 64, RULE_select_or_values);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(682);
			_la = _input.LA(1);
			if (_la==K_SELECT) {
				{
				setState(670);
				match(K_SELECT);
				setState(672);
				switch ( getInterpreter().adaptivePredict(_input,91,_ctx) ) {
				case 1:
					{
					setState(671);
					_la = _input.LA(1);
					if ( !(_la==K_ALL || _la==K_DISTINCT) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					break;
				}
				setState(674);
				result_column();
				setState(679);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(675);
					match(C1);
					setState(676);
					result_column();
					}
					}
					setState(681);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			{
			setState(684);
			match(K_FROM);
			setState(694);
			switch ( getInterpreter().adaptivePredict(_input,95,_ctx) ) {
			case 1:
				{
				setState(685);
				table_or_subquery();
				setState(690);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(686);
					match(C1);
					setState(687);
					table_or_subquery();
					}
					}
					setState(692);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				{
				setState(693);
				join_clause();
				}
				break;
			}
			}
			setState(697);
			_la = _input.LA(1);
			if (((((_la - 47)) & ~0x3f) == 0 && ((1L << (_la - 47)) & ((1L << (K_GROUP - 47)) | (1L << (K_VALUES - 47)) | (1L << (K_WHERE - 47)))) != 0)) {
				{
				setState(696);
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
		enterRule(_localctx, 66, RULE_compound_operator);
		try {
			setState(704);
			switch ( getInterpreter().adaptivePredict(_input,97,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(699);
				match(K_UNION);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(700);
				match(K_UNION);
				setState(701);
				match(K_ALL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(702);
				match(K_INTERSECT);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(703);
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
		enterRule(_localctx, 68, RULE_join_clause);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(706);
			table_or_subquery();
			setState(716);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,99,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(707);
					join_operator();
					setState(710);
					switch ( getInterpreter().adaptivePredict(_input,98,_ctx) ) {
					case 1:
						{
						setState(708);
						join_clause();
						}
						break;
					case 2:
						{
						setState(709);
						table_or_subquery();
						}
						break;
					}
					setState(712);
					join_constraint();
					}
					} 
				}
				setState(718);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,99,_ctx);
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
		enterRule(_localctx, 70, RULE_join_operator);
		int _la;
		try {
			setState(740);
			switch (_input.LA(1)) {
			case C1:
				enterOuterAlt(_localctx, 1);
				{
				setState(719);
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
				setState(721);
				_la = _input.LA(1);
				if (_la==K_NATURAL) {
					{
					setState(720);
					match(K_NATURAL);
					}
				}

				setState(737);
				switch (_input.LA(1)) {
				case K_LEFT:
					{
					setState(723);
					match(K_LEFT);
					setState(725);
					_la = _input.LA(1);
					if (_la==K_OUTER) {
						{
						setState(724);
						match(K_OUTER);
						}
					}

					}
					break;
				case K_RIGHT:
					{
					setState(727);
					match(K_RIGHT);
					setState(729);
					_la = _input.LA(1);
					if (_la==K_OUTER) {
						{
						setState(728);
						match(K_OUTER);
						}
					}

					}
					break;
				case K_FULL:
					{
					setState(731);
					match(K_FULL);
					setState(733);
					_la = _input.LA(1);
					if (_la==K_OUTER) {
						{
						setState(732);
						match(K_OUTER);
						}
					}

					}
					break;
				case K_INNER:
					{
					setState(735);
					match(K_INNER);
					}
					break;
				case K_CROSS:
					{
					setState(736);
					match(K_CROSS);
					}
					break;
				case K_JOIN:
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(739);
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
		enterRule(_localctx, 72, RULE_join_constraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(756);
			switch ( getInterpreter().adaptivePredict(_input,107,_ctx) ) {
			case 1:
				{
				setState(742);
				match(K_ON);
				setState(743);
				expr(0);
				}
				break;
			case 2:
				{
				setState(744);
				match(K_USING);
				setState(745);
				match(OPEN_PARENTHESE);
				setState(746);
				column_name();
				setState(751);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(747);
					match(C1);
					setState(748);
					column_name();
					}
					}
					setState(753);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(754);
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
		enterRule(_localctx, 74, RULE_common_table_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(758);
			table_name();
			setState(770);
			_la = _input.LA(1);
			if (_la==OPEN_PARENTHESE) {
				{
				setState(759);
				match(OPEN_PARENTHESE);
				setState(760);
				column_name();
				setState(765);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C1) {
					{
					{
					setState(761);
					match(C1);
					setState(762);
					column_name();
					}
					}
					setState(767);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(768);
				match(CLOSE_PARENTHESE);
				}
			}

			setState(772);
			match(K_AS);
			setState(773);
			match(OPEN_PARENTHESE);
			setState(774);
			select_stmt();
			setState(775);
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
		enterRule(_localctx, 76, RULE_ordering_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(777);
			expr(0);
			setState(780);
			_la = _input.LA(1);
			if (_la==K_COLLATE) {
				{
				setState(778);
				match(K_COLLATE);
				setState(779);
				collation_name();
				}
			}

			setState(783);
			_la = _input.LA(1);
			if (_la==K_ASC || _la==K_DESC) {
				{
				setState(782);
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
		int _startState = 78;
		enterRecursionRule(_localctx, 78, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(843);
			switch ( getInterpreter().adaptivePredict(_input,119,_ctx) ) {
			case 1:
				{
				setState(786);
				unary_operator();
				setState(787);
				expr(20);
				}
				break;
			case 2:
				{
				setState(789);
				operand();
				}
				break;
			case 3:
				{
				setState(790);
				match(BIND_PARAMETER);
				}
				break;
			case 4:
				{
				setState(799);
				switch ( getInterpreter().adaptivePredict(_input,113,_ctx) ) {
				case 1:
					{
					setState(794);
					switch ( getInterpreter().adaptivePredict(_input,112,_ctx) ) {
					case 1:
						{
						setState(791);
						database_name();
						setState(792);
						match(DOT);
						}
						break;
					}
					setState(796);
					table_alias();
					setState(797);
					match(DOT);
					}
					break;
				}
				setState(801);
				column_name();
				}
				break;
			case 5:
				{
				setState(802);
				match(OPEN_PARENTHESE);
				setState(803);
				expr(0);
				setState(804);
				match(CLOSE_PARENTHESE);
				}
				break;
			case 6:
				{
				setState(806);
				match(K_CAST);
				setState(807);
				match(OPEN_PARENTHESE);
				setState(808);
				expr(0);
				setState(809);
				match(K_AS);
				setState(810);
				type_name();
				setState(811);
				match(CLOSE_PARENTHESE);
				}
				break;
			case 7:
				{
				setState(817);
				_la = _input.LA(1);
				if (_la==K_EXISTS || _la==K_NOT) {
					{
					setState(814);
					_la = _input.LA(1);
					if (_la==K_NOT) {
						{
						setState(813);
						match(K_NOT);
						}
					}

					setState(816);
					match(K_EXISTS);
					}
				}

				setState(819);
				match(OPEN_PARENTHESE);
				setState(820);
				select_stmt();
				setState(821);
				match(CLOSE_PARENTHESE);
				}
				break;
			case 8:
				{
				setState(823);
				match(K_CASE);
				setState(825);
				switch ( getInterpreter().adaptivePredict(_input,116,_ctx) ) {
				case 1:
					{
					setState(824);
					expr(0);
					}
					break;
				}
				setState(832); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(827);
					match(K_WHEN);
					setState(828);
					expr(0);
					setState(829);
					match(K_THEN);
					setState(830);
					expr(0);
					}
					}
					setState(834); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==K_WHEN );
				setState(838);
				_la = _input.LA(1);
				if (_la==K_ELSE) {
					{
					setState(836);
					match(K_ELSE);
					setState(837);
					expr(0);
					}
				}

				setState(840);
				match(K_END);
				}
				break;
			case 9:
				{
				setState(842);
				raise_function();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(945);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,132,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(943);
					switch ( getInterpreter().adaptivePredict(_input,131,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(845);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(846);
						match(T__0);
						setState(847);
						expr(20);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(848);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(849);
						_la = _input.LA(1);
						if ( !(_la==T__4 || _la==T__5 || _la==C3) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(850);
						expr(19);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(851);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(852);
						_la = _input.LA(1);
						if ( !(_la==T__6 || _la==T__7) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(853);
						expr(18);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(854);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(855);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__8) | (1L << T__9) | (1L << T__10))) != 0)) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(856);
						expr(17);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(857);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(858);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14))) != 0)) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(859);
						expr(16);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(860);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(873);
						switch ( getInterpreter().adaptivePredict(_input,120,_ctx) ) {
						case 1:
							{
							setState(861);
							match(T__15);
							}
							break;
						case 2:
							{
							setState(862);
							match(T__16);
							}
							break;
						case 3:
							{
							setState(863);
							match(T__17);
							}
							break;
						case 4:
							{
							setState(864);
							match(T__18);
							}
							break;
						case 5:
							{
							setState(865);
							match(K_IS);
							}
							break;
						case 6:
							{
							setState(866);
							match(K_IS);
							setState(867);
							match(K_NOT);
							}
							break;
						case 7:
							{
							setState(868);
							match(K_IN);
							}
							break;
						case 8:
							{
							setState(869);
							match(K_LIKE);
							}
							break;
						case 9:
							{
							setState(870);
							match(K_GLOB);
							}
							break;
						case 10:
							{
							setState(871);
							match(K_MATCH);
							}
							break;
						case 11:
							{
							setState(872);
							match(K_REGEXP);
							}
							break;
						}
						setState(875);
						expr(15);
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(876);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(877);
						match(K_AND);
						setState(878);
						expr(14);
						}
						break;
					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(879);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(880);
						match(K_OR);
						setState(881);
						expr(13);
						}
						break;
					case 9:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(882);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(883);
						match(K_IS);
						setState(885);
						switch ( getInterpreter().adaptivePredict(_input,121,_ctx) ) {
						case 1:
							{
							setState(884);
							match(K_NOT);
							}
							break;
						}
						setState(887);
						expr(7);
						}
						break;
					case 10:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(888);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(890);
						_la = _input.LA(1);
						if (_la==K_NOT) {
							{
							setState(889);
							match(K_NOT);
							}
						}

						setState(892);
						match(K_BETWEEN);
						setState(893);
						expr(0);
						setState(894);
						match(K_AND);
						setState(895);
						expr(6);
						}
						break;
					case 11:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(897);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(898);
						match(K_COLLATE);
						setState(899);
						collation_name();
						}
						break;
					case 12:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(900);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(902);
						_la = _input.LA(1);
						if (_la==K_NOT) {
							{
							setState(901);
							match(K_NOT);
							}
						}

						setState(904);
						_la = _input.LA(1);
						if ( !(((((_la - 46)) & ~0x3f) == 0 && ((1L << (_la - 46)) & ((1L << (K_GLOB - 46)) | (1L << (K_LIKE - 46)) | (1L << (K_MATCH - 46)) | (1L << (K_REGEXP - 46)))) != 0)) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(905);
						expr(0);
						setState(908);
						switch ( getInterpreter().adaptivePredict(_input,124,_ctx) ) {
						case 1:
							{
							setState(906);
							match(K_ESCAPE);
							setState(907);
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
						setState(910);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(915);
						switch (_input.LA(1)) {
						case K_ISNULL:
							{
							setState(911);
							match(K_ISNULL);
							}
							break;
						case K_NOTNULL:
							{
							setState(912);
							match(K_NOTNULL);
							}
							break;
						case K_NOT:
							{
							setState(913);
							match(K_NOT);
							setState(914);
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
						setState(917);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(919);
						_la = _input.LA(1);
						if (_la==K_NOT) {
							{
							setState(918);
							match(K_NOT);
							}
						}

						setState(921);
						match(K_IN);
						setState(941);
						switch (_input.LA(1)) {
						case OPEN_PARENTHESE:
							{
							setState(922);
							match(OPEN_PARENTHESE);
							setState(932);
							switch ( getInterpreter().adaptivePredict(_input,128,_ctx) ) {
							case 1:
								{
								setState(923);
								select_stmt();
								}
								break;
							case 2:
								{
								setState(924);
								expr(0);
								setState(929);
								_errHandler.sync(this);
								_la = _input.LA(1);
								while (_la==C1) {
									{
									{
									setState(925);
									match(C1);
									setState(926);
									expr(0);
									}
									}
									setState(931);
									_errHandler.sync(this);
									_la = _input.LA(1);
								}
								}
								break;
							}
							setState(934);
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
						case IDENTIFIER:
							{
							setState(938);
							switch ( getInterpreter().adaptivePredict(_input,129,_ctx) ) {
							case 1:
								{
								setState(935);
								database_name();
								setState(936);
								match(DOT);
								}
								break;
							}
							setState(940);
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
				setState(947);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,132,_ctx);
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
		enterRule(_localctx, 80, RULE_literal_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(948);
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
		enterRule(_localctx, 82, RULE_unary_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(950);
			_la = _input.LA(1);
			if ( !(((((_la - 7)) & ~0x3f) == 0 && ((1L << (_la - 7)) & ((1L << (T__6 - 7)) | (1L << (T__7 - 7)) | (1L << (T__19 - 7)) | (1L << (K_NOT - 7)))) != 0)) ) {
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
		enterRule(_localctx, 84, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(952);
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
		enterRule(_localctx, 86, RULE_type_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(955); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(954);
				name();
				}
				}
				setState(957); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_ABORT) | (1L << K_ALL) | (1L << K_AND) | (1L << K_AS) | (1L << K_ASC) | (1L << K_BETWEEN) | (1L << K_BY) | (1L << K_CASE) | (1L << K_CAST) | (1L << K_COLLATE) | (1L << K_CROSS) | (1L << K_CURRENT_DATE) | (1L << K_CURRENT_TIME) | (1L << K_CURRENT_TIMESTAMP) | (1L << K_DESC) | (1L << K_DISTINCT) | (1L << K_ELSE) | (1L << K_END) | (1L << K_ESCAPE) | (1L << K_EXCEPT) | (1L << K_EXISTS) | (1L << K_FAIL) | (1L << K_FULL) | (1L << K_FROM) | (1L << K_GLOB) | (1L << K_GROUP) | (1L << K_HAVING) | (1L << K_IF) | (1L << K_IGNORE) | (1L << K_IN) | (1L << K_INDEXED) | (1L << K_INNER) | (1L << K_INTERSECT) | (1L << K_IS) | (1L << K_ISNULL) | (1L << K_JOIN) | (1L << K_LEFT) | (1L << K_LIKE) | (1L << K_LIMIT) | (1L << K_MATCH) | (1L << K_NATURAL) | (1L << K_NO))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (K_NOT - 64)) | (1L << (K_NOTNULL - 64)) | (1L << (K_NULL - 64)) | (1L << (K_OFFSET - 64)) | (1L << (K_ON - 64)) | (1L << (K_OR - 64)) | (1L << (K_ORDER - 64)) | (1L << (K_OUTER - 64)) | (1L << (K_RAISE - 64)) | (1L << (K_RECURSIVE - 64)) | (1L << (K_REGEXP - 64)) | (1L << (K_RIGHT - 64)) | (1L << (K_ROLLBACK - 64)) | (1L << (K_SELECT - 64)) | (1L << (K_THEN - 64)) | (1L << (K_UNION - 64)) | (1L << (K_USING - 64)) | (1L << (K_VALUES - 64)) | (1L << (K_WHEN - 64)) | (1L << (K_WHERE - 64)) | (1L << (K_WITH - 64)) | (1L << (K_GENERATE - 64)) | (1L << (K_MAX - 64)) | (1L << (K_MIN - 64)) | (1L << (K_AVG - 64)) | (1L << (K_COUNT - 64)) | (1L << (K_SUM - 64)) | (1L << (IDENTIFIER - 64)))) != 0) );
			setState(969);
			switch ( getInterpreter().adaptivePredict(_input,134,_ctx) ) {
			case 1:
				{
				setState(959);
				match(OPEN_PARENTHESE);
				setState(960);
				signed_number();
				setState(961);
				match(CLOSE_PARENTHESE);
				}
				break;
			case 2:
				{
				setState(963);
				match(OPEN_PARENTHESE);
				setState(964);
				signed_number();
				setState(965);
				match(C1);
				setState(966);
				signed_number();
				setState(967);
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
		enterRule(_localctx, 88, RULE_function_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(971);
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
		enterRule(_localctx, 90, RULE_ag_function_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(973);
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
		enterRule(_localctx, 92, RULE_ag_keyword);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(975);
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
		enterRule(_localctx, 94, RULE_collation_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(977);
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
		enterRule(_localctx, 96, RULE_database_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(979);
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
		enterRule(_localctx, 98, RULE_table_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(981);
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
		enterRule(_localctx, 100, RULE_column_alias);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(983);
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
		enterRule(_localctx, 102, RULE_column_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(985);
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
		enterRule(_localctx, 104, RULE_table_alias);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(987);
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
		enterRule(_localctx, 106, RULE_index_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(989);
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
		enterRule(_localctx, 108, RULE_any_name);
		try {
			setState(993);
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
				enterOuterAlt(_localctx, 1);
				{
				setState(991);
				keyword();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(992);
				match(IDENTIFIER);
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
		enterRule(_localctx, 110, RULE_sl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(995);
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
		enterRule(_localctx, 112, RULE_signed_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(998);
			_la = _input.LA(1);
			if (_la==T__6 || _la==T__7) {
				{
				setState(997);
				_la = _input.LA(1);
				if ( !(_la==T__6 || _la==T__7) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
			}

			setState(1000);
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
		enterRule(_localctx, 114, RULE_raise_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1002);
			match(K_RAISE);
			setState(1003);
			match(OPEN_PARENTHESE);
			setState(1008);
			switch (_input.LA(1)) {
			case K_IGNORE:
				{
				setState(1004);
				match(K_IGNORE);
				}
				break;
			case K_ABORT:
			case K_FAIL:
			case K_ROLLBACK:
				{
				setState(1005);
				_la = _input.LA(1);
				if ( !(((((_la - 21)) & ~0x3f) == 0 && ((1L << (_la - 21)) & ((1L << (K_ABORT - 21)) | (1L << (K_FAIL - 21)) | (1L << (K_ROLLBACK - 21)))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(1006);
				match(C1);
				setState(1007);
				error_message();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1010);
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
		enterRule(_localctx, 116, RULE_error_message);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1012);
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
		case 19:
			return arithmetics_sempred((ArithmeticsContext)_localctx, predIndex);
		case 39:
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3t\u03f9\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\3\2\3"+
		"\2\3\2\5\2|\n\2\3\3\3\3\5\3\u0080\n\3\3\3\5\3\u0083\n\3\3\4\3\4\3\4\3"+
		"\5\5\5\u0089\n\5\3\5\3\5\5\5\u008d\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\5\5\u009b\n\5\3\5\3\5\3\5\5\5\u00a0\n\5\3\5\5\5\u00a3"+
		"\n\5\3\6\3\6\3\6\5\6\u00a8\n\6\3\6\3\6\3\7\3\7\3\7\3\7\5\7\u00b0\n\7\3"+
		"\7\3\7\3\7\3\7\3\7\5\7\u00b7\n\7\6\7\u00b9\n\7\r\7\16\7\u00ba\3\b\3\b"+
		"\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\6\t\u00c9\n\t\r\t\16\t\u00ca"+
		"\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\5\13\u00d7\n\13\7\13\u00d9"+
		"\n\13\f\13\16\13\u00dc\13\13\3\f\3\f\3\f\3\f\5\f\u00e2\n\f\7\f\u00e4\n"+
		"\f\f\f\16\f\u00e7\13\f\3\r\3\r\3\r\3\r\5\r\u00ed\n\r\7\r\u00ef\n\r\f\r"+
		"\16\r\u00f2\13\r\3\16\3\16\5\16\u00f6\n\16\3\16\3\16\3\16\5\16\u00fb\n"+
		"\16\7\16\u00fd\n\16\f\16\16\16\u0100\13\16\3\17\3\17\3\17\3\17\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\5\20\u010c\n\20\3\21\5\21\u010f\n\21\3\21\3\21"+
		"\3\21\3\21\5\21\u0115\n\21\3\21\3\21\3\21\5\21\u011a\n\21\7\21\u011c\n"+
		"\21\f\21\16\21\u011f\13\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\5\22\u0128"+
		"\n\22\3\22\3\22\3\22\5\22\u012d\n\22\7\22\u012f\n\22\f\22\16\22\u0132"+
		"\13\22\7\22\u0134\n\22\f\22\16\22\u0137\13\22\3\22\3\22\3\23\3\23\3\23"+
		"\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u0155\n\24\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u015f\n\25\3\25\3\25\3\25\7\25\u0164"+
		"\n\25\f\25\16\25\u0167\13\25\3\26\3\26\5\26\u016b\n\26\3\27\3\27\5\27"+
		"\u016f\n\27\3\30\3\30\3\30\3\31\3\31\6\31\u0176\n\31\r\31\16\31\u0177"+
		"\3\31\7\31\u017b\n\31\f\31\16\31\u017e\13\31\3\31\7\31\u0181\n\31\f\31"+
		"\16\31\u0184\13\31\3\32\3\32\5\32\u0188\n\32\3\33\3\33\5\33\u018c\n\33"+
		"\3\33\3\33\3\33\7\33\u0191\n\33\f\33\16\33\u0194\13\33\5\33\u0196\n\33"+
		"\3\33\3\33\3\33\3\33\7\33\u019c\n\33\f\33\16\33\u019f\13\33\3\33\3\33"+
		"\3\33\3\33\3\33\7\33\u01a6\n\33\f\33\16\33\u01a9\13\33\5\33\u01ab\n\33"+
		"\3\33\3\33\3\33\3\33\5\33\u01b1\n\33\5\33\u01b3\n\33\3\34\3\34\5\34\u01b7"+
		"\n\34\3\34\3\34\3\34\7\34\u01bc\n\34\f\34\16\34\u01bf\13\34\5\34\u01c1"+
		"\n\34\3\34\3\34\3\34\3\34\7\34\u01c7\n\34\f\34\16\34\u01ca\13\34\3\34"+
		"\5\34\u01cd\n\34\3\34\5\34\u01d0\n\34\3\35\3\35\3\35\3\35\3\35\3\35\3"+
		"\35\3\35\7\35\u01da\n\35\f\35\16\35\u01dd\13\35\3\35\3\35\5\35\u01e1\n"+
		"\35\5\35\u01e3\n\35\3\35\3\35\3\35\3\35\3\35\7\35\u01ea\n\35\f\35\16\35"+
		"\u01ed\13\35\3\35\3\35\3\35\3\35\3\35\3\35\7\35\u01f5\n\35\f\35\16\35"+
		"\u01f8\13\35\3\35\3\35\7\35\u01fc\n\35\f\35\16\35\u01ff\13\35\5\35\u0201"+
		"\n\35\3\35\3\35\5\35\u0205\n\35\3\35\3\35\3\35\3\35\3\35\7\35\u020c\n"+
		"\35\f\35\16\35\u020f\13\35\3\35\3\35\5\35\u0213\n\35\3\35\3\35\3\35\3"+
		"\35\3\35\7\35\u021a\n\35\f\35\16\35\u021d\13\35\3\35\3\35\3\35\3\35\3"+
		"\35\3\35\7\35\u0225\n\35\f\35\16\35\u0228\13\35\3\35\3\35\7\35\u022c\n"+
		"\35\f\35\16\35\u022f\13\35\5\35\u0231\n\35\5\35\u0233\n\35\3\36\3\36\3"+
		"\36\3\36\3\36\3\36\3\36\5\36\u023c\n\36\3\36\5\36\u023f\n\36\5\36\u0241"+
		"\n\36\3\37\3\37\3\37\5\37\u0246\n\37\3\37\3\37\5\37\u024a\n\37\3\37\5"+
		"\37\u024d\n\37\3\37\3\37\3\37\3\37\3\37\5\37\u0254\n\37\3\37\3\37\3\37"+
		"\3\37\7\37\u025a\n\37\f\37\16\37\u025d\13\37\3\37\5\37\u0260\n\37\3\37"+
		"\3\37\5\37\u0264\n\37\3\37\5\37\u0267\n\37\3\37\3\37\3\37\3\37\5\37\u026d"+
		"\n\37\3\37\5\37\u0270\n\37\5\37\u0272\n\37\3 \3 \3!\3!\5!\u0278\n!\3!"+
		"\3!\3!\7!\u027d\n!\f!\16!\u0280\13!\5!\u0282\n!\3!\3!\3!\3!\7!\u0288\n"+
		"!\f!\16!\u028b\13!\3!\3!\3!\3!\3!\7!\u0292\n!\f!\16!\u0295\13!\5!\u0297"+
		"\n!\3!\3!\3!\3!\5!\u029d\n!\5!\u029f\n!\3\"\3\"\5\"\u02a3\n\"\3\"\3\""+
		"\3\"\7\"\u02a8\n\"\f\"\16\"\u02ab\13\"\5\"\u02ad\n\"\3\"\3\"\3\"\3\"\7"+
		"\"\u02b3\n\"\f\"\16\"\u02b6\13\"\3\"\5\"\u02b9\n\"\3\"\5\"\u02bc\n\"\3"+
		"#\3#\3#\3#\3#\5#\u02c3\n#\3$\3$\3$\3$\5$\u02c9\n$\3$\3$\7$\u02cd\n$\f"+
		"$\16$\u02d0\13$\3%\3%\5%\u02d4\n%\3%\3%\5%\u02d8\n%\3%\3%\5%\u02dc\n%"+
		"\3%\3%\5%\u02e0\n%\3%\3%\5%\u02e4\n%\3%\5%\u02e7\n%\3&\3&\3&\3&\3&\3&"+
		"\3&\7&\u02f0\n&\f&\16&\u02f3\13&\3&\3&\5&\u02f7\n&\3\'\3\'\3\'\3\'\3\'"+
		"\7\'\u02fe\n\'\f\'\16\'\u0301\13\'\3\'\3\'\5\'\u0305\n\'\3\'\3\'\3\'\3"+
		"\'\3\'\3(\3(\3(\5(\u030f\n(\3(\5(\u0312\n(\3)\3)\3)\3)\3)\3)\3)\3)\3)"+
		"\5)\u031d\n)\3)\3)\3)\5)\u0322\n)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)"+
		"\3)\5)\u0331\n)\3)\5)\u0334\n)\3)\3)\3)\3)\3)\3)\5)\u033c\n)\3)\3)\3)"+
		"\3)\3)\6)\u0343\n)\r)\16)\u0344\3)\3)\5)\u0349\n)\3)\3)\3)\5)\u034e\n"+
		")\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3"+
		")\3)\3)\3)\3)\3)\5)\u036c\n)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\5)\u0378\n"+
		")\3)\3)\3)\5)\u037d\n)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\5)\u0389\n)\3)\3"+
		")\3)\3)\5)\u038f\n)\3)\3)\3)\3)\3)\5)\u0396\n)\3)\3)\5)\u039a\n)\3)\3"+
		")\3)\3)\3)\3)\7)\u03a2\n)\f)\16)\u03a5\13)\5)\u03a7\n)\3)\3)\3)\3)\5)"+
		"\u03ad\n)\3)\5)\u03b0\n)\7)\u03b2\n)\f)\16)\u03b5\13)\3*\3*\3+\3+\3,\3"+
		",\3-\6-\u03be\n-\r-\16-\u03bf\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\5-\u03cc\n"+
		"-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3"+
		"\65\3\66\3\66\3\67\3\67\38\38\58\u03e4\n8\39\39\3:\5:\u03e9\n:\3:\3:\3"+
		";\3;\3;\3;\3;\3;\5;\u03f3\n;\3;\3;\3<\3<\3<\2\4(P=\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnp"+
		"rtv\2\22\3\2^b\4\2\7\n``\4\2EE^^\4\2\31\31\'\'\4\2\27\27\31\\\4\2\34\34"+
		"&&\4\2\7\b``\3\2\t\n\4\2\5\5\13\r\3\2\16\21\6\2\60\60==??LL\6\2#%DDlm"+
		"pp\5\2\t\n\26\26BB\3\2X\\\3\2op\5\2\27\27--NN\u047e\2x\3\2\2\2\4\177\3"+
		"\2\2\2\6\u0084\3\2\2\2\b\u009f\3\2\2\2\n\u00a7\3\2\2\2\f\u00af\3\2\2\2"+
		"\16\u00bc\3\2\2\2\20\u00c1\3\2\2\2\22\u00d0\3\2\2\2\24\u00d2\3\2\2\2\26"+
		"\u00dd\3\2\2\2\30\u00e8\3\2\2\2\32\u00f5\3\2\2\2\34\u0101\3\2\2\2\36\u010b"+
		"\3\2\2\2 \u010e\3\2\2\2\"\u0122\3\2\2\2$\u013a\3\2\2\2&\u0154\3\2\2\2"+
		"(\u015e\3\2\2\2*\u016a\3\2\2\2,\u016e\3\2\2\2.\u0170\3\2\2\2\60\u0173"+
		"\3\2\2\2\62\u0187\3\2\2\2\64\u0195\3\2\2\2\66\u01c0\3\2\2\28\u0232\3\2"+
		"\2\2:\u0240\3\2\2\2<\u0271\3\2\2\2>\u0273\3\2\2\2@\u0281\3\2\2\2B\u02ac"+
		"\3\2\2\2D\u02c2\3\2\2\2F\u02c4\3\2\2\2H\u02e6\3\2\2\2J\u02f6\3\2\2\2L"+
		"\u02f8\3\2\2\2N\u030b\3\2\2\2P\u034d\3\2\2\2R\u03b6\3\2\2\2T\u03b8\3\2"+
		"\2\2V\u03ba\3\2\2\2X\u03bd\3\2\2\2Z\u03cd\3\2\2\2\\\u03cf\3\2\2\2^\u03d1"+
		"\3\2\2\2`\u03d3\3\2\2\2b\u03d5\3\2\2\2d\u03d7\3\2\2\2f\u03d9\3\2\2\2h"+
		"\u03db\3\2\2\2j\u03dd\3\2\2\2l\u03df\3\2\2\2n\u03e3\3\2\2\2p\u03e5\3\2"+
		"\2\2r\u03e8\3\2\2\2t\u03ec\3\2\2\2v\u03f6\3\2\2\2xy\5\6\4\2y{\5\4\3\2"+
		"z|\5,\27\2{z\3\2\2\2{|\3\2\2\2|\3\3\2\2\2}\u0080\5\b\5\2~\u0080\5\22\n"+
		"\2\177}\3\2\2\2\177~\3\2\2\2\u0080\u0082\3\2\2\2\u0081\u0083\7k\2\2\u0082"+
		"\u0081\3\2\2\2\u0082\u0083\3\2\2\2\u0083\5\3\2\2\2\u0084\u0085\7W\2\2"+
		"\u0085\u0086\7o\2\2\u0086\7\3\2\2\2\u0087\u0089\5\36\20\2\u0088\u0087"+
		"\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u00a0\5\n\6\2\u008b"+
		"\u008d\5\36\20\2\u008c\u008b\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e\3"+
		"\2\2\2\u008e\u00a0\5\f\7\2\u008f\u00a0\5 \21\2\u0090\u00a0\5\"\22\2\u0091"+
		"\u0092\7h\2\2\u0092\u0093\5\22\n\2\u0093\u0094\7i\2\2\u0094\u00a0\3\2"+
		"\2\2\u0095\u00a0\5\16\b\2\u0096\u00a0\5\20\t\2\u0097\u00a0\5&\24\2\u0098"+
		"\u00a0\7l\2\2\u0099\u009b\5\36\20\2\u009a\u0099\3\2\2\2\u009a\u009b\3"+
		"\2\2\2\u009b\u009c\3\2\2\2\u009c\u00a0\5$\23\2\u009d\u00a0\5(\25\2\u009e"+
		"\u00a0\5p9\2\u009f\u0088\3\2\2\2\u009f\u008c\3\2\2\2\u009f\u008f\3\2\2"+
		"\2\u009f\u0090\3\2\2\2\u009f\u0091\3\2\2\2\u009f\u0095\3\2\2\2\u009f\u0096"+
		"\3\2\2\2\u009f\u0097\3\2\2\2\u009f\u0098\3\2\2\2\u009f\u009a\3\2\2\2\u009f"+
		"\u009d\3\2\2\2\u009f\u009e\3\2\2\2\u00a0\u00a2\3\2\2\2\u00a1\u00a3\7k"+
		"\2\2\u00a2\u00a1\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\t\3\2\2\2\u00a4\u00a5"+
		"\5j\66\2\u00a5\u00a6\7c\2\2\u00a6\u00a8\3\2\2\2\u00a7\u00a4\3\2\2\2\u00a7"+
		"\u00a8\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\5h\65\2\u00aa\13\3\2\2"+
		"\2\u00ab\u00b0\5\n\6\2\u00ac\u00b0\7l\2\2\u00ad\u00b0\5(\25\2\u00ae\u00b0"+
		"\5p9\2\u00af\u00ab\3\2\2\2\u00af\u00ac\3\2\2\2\u00af\u00ad\3\2\2\2\u00af"+
		"\u00ae\3\2\2\2\u00b0\u00b8\3\2\2\2\u00b1\u00b6\7\3\2\2\u00b2\u00b7\5\n"+
		"\6\2\u00b3\u00b7\7l\2\2\u00b4\u00b7\5(\25\2\u00b5\u00b7\5p9\2\u00b6\u00b2"+
		"\3\2\2\2\u00b6\u00b3\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b5\3\2\2\2\u00b7"+
		"\u00b9\3\2\2\2\u00b8\u00b1\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00b8\3\2"+
		"\2\2\u00ba\u00bb\3\2\2\2\u00bb\r\3\2\2\2\u00bc\u00bd\7f\2\2\u00bd\u00be"+
		"\5\22\n\2\u00be\u00bf\7g\2\2\u00bf\u00c0\t\2\2\2\u00c0\17\3\2\2\2\u00c1"+
		"\u00c2\7f\2\2\u00c2\u00c3\5\22\n\2\u00c3\u00c4\7g\2\2\u00c4\u00c8\b\t"+
		"\1\2\u00c5\u00c6\t\2\2\2\u00c6\u00c7\7l\2\2\u00c7\u00c9\b\t\1\2\u00c8"+
		"\u00c5\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00c8\3\2\2\2\u00ca\u00cb\3\2"+
		"\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00cd\t\2\2\2\u00cd\u00ce\b\t\1\2\u00ce"+
		"\u00cf\b\t\1\2\u00cf\21\3\2\2\2\u00d0\u00d1\5\24\13\2\u00d1\23\3\2\2\2"+
		"\u00d2\u00da\5\26\f\2\u00d3\u00d6\7a\2\2\u00d4\u00d7\5\26\f\2\u00d5\u00d7"+
		"\5\b\5\2\u00d6\u00d4\3\2\2\2\u00d6\u00d5\3\2\2\2\u00d7\u00d9\3\2\2\2\u00d8"+
		"\u00d3\3\2\2\2\u00d9\u00dc\3\2\2\2\u00da\u00d8\3\2\2\2\u00da\u00db\3\2"+
		"\2\2\u00db\25\3\2\2\2\u00dc\u00da\3\2\2\2\u00dd\u00e5\5\30\r\2\u00de\u00e1"+
		"\7`\2\2\u00df\u00e2\5\30\r\2\u00e0\u00e2\5\b\5\2\u00e1\u00df\3\2\2\2\u00e1"+
		"\u00e0\3\2\2\2\u00e2\u00e4\3\2\2\2\u00e3\u00de\3\2\2\2\u00e4\u00e7\3\2"+
		"\2\2\u00e5\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\27\3\2\2\2\u00e7\u00e5"+
		"\3\2\2\2\u00e8\u00f0\5\32\16\2\u00e9\u00ec\7_\2\2\u00ea\u00ed\5\32\16"+
		"\2\u00eb\u00ed\5\b\5\2\u00ec\u00ea\3\2\2\2\u00ec\u00eb\3\2\2\2\u00ed\u00ef"+
		"\3\2\2\2\u00ee\u00e9\3\2\2\2\u00ef\u00f2\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0"+
		"\u00f1\3\2\2\2\u00f1\31\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f3\u00f6\5\b\5"+
		"\2\u00f4\u00f6\5\34\17\2\u00f5\u00f3\3\2\2\2\u00f5\u00f4\3\2\2\2\u00f6"+
		"\u00fe\3\2\2\2\u00f7\u00fa\7^\2\2\u00f8\u00fb\5\b\5\2\u00f9\u00fb\5\34"+
		"\17\2\u00fa\u00f8\3\2\2\2\u00fa\u00f9\3\2\2\2\u00fb\u00fd\3\2\2\2\u00fc"+
		"\u00f7\3\2\2\2\u00fd\u0100\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2"+
		"\2\2\u00ff\33\3\2\2\2\u0100\u00fe\3\2\2\2\u0101\u0102\5\b\5\2\u0102\u0103"+
		"\7]\2\2\u0103\u0104\5\b\5\2\u0104\35\3\2\2\2\u0105\u0106\7d\2\2\u0106"+
		"\u0107\7\34\2\2\u0107\u010c\7e\2\2\u0108\u0109\7d\2\2\u0109\u010a\7&\2"+
		"\2\u010a\u010c\7e\2\2\u010b\u0105\3\2\2\2\u010b\u0108\3\2\2\2\u010c\37"+
		"\3\2\2\2\u010d\u010f\7\4\2\2\u010e\u010d\3\2\2\2\u010e\u010f\3\2\2\2\u010f"+
		"\u0110\3\2\2\2\u0110\u0111\5Z.\2\u0111\u0114\7d\2\2\u0112\u0115\5\b\5"+
		"\2\u0113\u0115\5\22\n\2\u0114\u0112\3\2\2\2\u0114\u0113\3\2\2\2\u0115"+
		"\u011d\3\2\2\2\u0116\u0119\7^\2\2\u0117\u011a\5\b\5\2\u0118\u011a\5\22"+
		"\n\2\u0119\u0117\3\2\2\2\u0119\u0118\3\2\2\2\u011a\u011c\3\2\2\2\u011b"+
		"\u0116\3\2\2\2\u011c\u011f\3\2\2\2\u011d\u011b\3\2\2\2\u011d\u011e\3\2"+
		"\2\2\u011e\u0120\3\2\2\2\u011f\u011d\3\2\2\2\u0120\u0121\7e\2\2\u0121"+
		"!\3\2\2\2\u0122\u0123\7\5\2\2\u0123\u0124\5Z.\2\u0124\u0135\7d\2\2\u0125"+
		"\u0128\5\b\5\2\u0126\u0128\5\22\n\2\u0127\u0125\3\2\2\2\u0127\u0126\3"+
		"\2\2\2\u0128\u0130\3\2\2\2\u0129\u012c\7^\2\2\u012a\u012d\5\b\5\2\u012b"+
		"\u012d\5\22\n\2\u012c\u012a\3\2\2\2\u012c\u012b\3\2\2\2\u012d\u012f\3"+
		"\2\2\2\u012e\u0129\3\2\2\2\u012f\u0132\3\2\2\2\u0130\u012e\3\2\2\2\u0130"+
		"\u0131\3\2\2\2\u0131\u0134\3\2\2\2\u0132\u0130\3\2\2\2\u0133\u0127\3\2"+
		"\2\2\u0134\u0137\3\2\2\2\u0135\u0133\3\2\2\2\u0135\u0136\3\2\2\2\u0136"+
		"\u0138\3\2\2\2\u0137\u0135\3\2\2\2\u0138\u0139\7e\2\2\u0139#\3\2\2\2\u013a"+
		"\u013b\5\\/\2\u013b\u013c\7f\2\2\u013c\u013d\5\n\6\2\u013d\u013e\7g\2"+
		"\2\u013e%\3\2\2\2\u013f\u0140\7\63\2\2\u0140\u0141\7d\2\2\u0141\u0142"+
		"\5P)\2\u0142\u0143\7e\2\2\u0143\u0144\7P\2\2\u0144\u0145\7d\2\2\u0145"+
		"\u0146\5\22\n\2\u0146\u0147\7e\2\2\u0147\u0148\7(\2\2\u0148\u0149\7d\2"+
		"\2\u0149\u014a\5\22\n\2\u014a\u014b\7e\2\2\u014b\u0155\3\2\2\2\u014c\u014d"+
		"\7d\2\2\u014d\u014e\5P)\2\u014e\u014f\7e\2\2\u014f\u0150\7]\2\2\u0150"+
		"\u0151\5\22\n\2\u0151\u0152\7\6\2\2\u0152\u0153\5\22\n\2\u0153\u0155\3"+
		"\2\2\2\u0154\u013f\3\2\2\2\u0154\u014c\3\2\2\2\u0155\'\3\2\2\2\u0156\u0157"+
		"\b\25\1\2\u0157\u0158\7d\2\2\u0158\u0159\5(\25\2\u0159\u015a\t\3\2\2\u015a"+
		"\u015b\5(\25\2\u015b\u015c\7e\2\2\u015c\u015f\3\2\2\2\u015d\u015f\5*\26"+
		"\2\u015e\u0156\3\2\2\2\u015e\u015d\3\2\2\2\u015f\u0165\3\2\2\2\u0160\u0161"+
		"\f\4\2\2\u0161\u0162\t\3\2\2\u0162\u0164\5(\25\5\u0163\u0160\3\2\2\2\u0164"+
		"\u0167\3\2\2\2\u0165\u0163\3\2\2\2\u0165\u0166\3\2\2\2\u0166)\3\2\2\2"+
		"\u0167\u0165\3\2\2\2\u0168\u016b\5\n\6\2\u0169\u016b\7l\2\2\u016a\u0168"+
		"\3\2\2\2\u016a\u0169\3\2\2\2\u016b+\3\2\2\2\u016c\u016f\5\60\31\2\u016d"+
		"\u016f\5.\30\2\u016e\u016c\3\2\2\2\u016e\u016d\3\2\2\2\u016f-\3\2\2\2"+
		"\u0170\u0171\7t\2\2\u0171\u0172\b\30\1\2\u0172/\3\2\2\2\u0173\u017c\5"+
		"\62\32\2\u0174\u0176\7j\2\2\u0175\u0174\3\2\2\2\u0176\u0177\3\2\2\2\u0177"+
		"\u0175\3\2\2\2\u0177\u0178\3\2\2\2\u0178\u0179\3\2\2\2\u0179\u017b\5\62"+
		"\32\2\u017a\u0175\3\2\2\2\u017b\u017e\3\2\2\2\u017c\u017a\3\2\2\2\u017c"+
		"\u017d\3\2\2\2\u017d\u0182\3\2\2\2\u017e\u017c\3\2\2\2\u017f\u0181\7j"+
		"\2\2\u0180\u017f\3\2\2\2\u0181\u0184\3\2\2\2\u0182\u0180\3\2\2\2\u0182"+
		"\u0183\3\2\2\2\u0183\61\3\2\2\2\u0184\u0182\3\2\2\2\u0185\u0188\5\64\33"+
		"\2\u0186\u0188\5@!\2\u0187\u0185\3\2\2\2\u0187\u0186\3\2\2\2\u0188\63"+
		"\3\2\2\2\u0189\u018b\7V\2\2\u018a\u018c\7K\2\2\u018b\u018a\3\2\2\2\u018b"+
		"\u018c\3\2\2\2\u018c\u018d\3\2\2\2\u018d\u0192\5L\'\2\u018e\u018f\7^\2"+
		"\2\u018f\u0191\5L\'\2\u0190\u018e\3\2\2\2\u0191\u0194\3\2\2\2\u0192\u0190"+
		"\3\2\2\2\u0192\u0193\3\2\2\2\u0193\u0196\3\2\2\2\u0194\u0192\3\2\2\2\u0195"+
		"\u0189\3\2\2\2\u0195\u0196\3\2\2\2\u0196\u0197\3\2\2\2\u0197\u019d\5\66"+
		"\34\2\u0198\u0199\5D#\2\u0199\u019a\5\66\34\2\u019a\u019c\3\2\2\2\u019b"+
		"\u0198\3\2\2\2\u019c\u019f\3\2\2\2\u019d\u019b\3\2\2\2\u019d\u019e\3\2"+
		"\2\2\u019e\u01aa\3\2\2\2\u019f\u019d\3\2\2\2\u01a0\u01a1\7H\2\2\u01a1"+
		"\u01a2\7\36\2\2\u01a2\u01a7\5N(\2\u01a3\u01a4\7^\2\2\u01a4\u01a6\5N(\2"+
		"\u01a5\u01a3\3\2\2\2\u01a6\u01a9\3\2\2\2\u01a7\u01a5\3\2\2\2\u01a7\u01a8"+
		"\3\2\2\2\u01a8\u01ab\3\2\2\2\u01a9\u01a7\3\2\2\2\u01aa\u01a0\3\2\2\2\u01aa"+
		"\u01ab\3\2\2\2\u01ab\u01b2\3\2\2\2\u01ac\u01ad\7>\2\2\u01ad\u01b0\5P)"+
		"\2\u01ae\u01af\t\4\2\2\u01af\u01b1\5P)\2\u01b0\u01ae\3\2\2\2\u01b0\u01b1"+
		"\3\2\2\2\u01b1\u01b3\3\2\2\2\u01b2\u01ac\3\2\2\2\u01b2\u01b3\3\2\2\2\u01b3"+
		"\65\3\2\2\2\u01b4\u01b6\7O\2\2\u01b5\u01b7\t\5\2\2\u01b6\u01b5\3\2\2\2"+
		"\u01b6\u01b7\3\2\2\2\u01b7\u01b8\3\2\2\2\u01b8\u01bd\5:\36\2\u01b9\u01ba"+
		"\7^\2\2\u01ba\u01bc\5:\36\2\u01bb\u01b9\3\2\2\2\u01bc\u01bf\3\2\2\2\u01bd"+
		"\u01bb\3\2\2\2\u01bd\u01be\3\2\2\2\u01be\u01c1\3\2\2\2\u01bf\u01bd\3\2"+
		"\2\2\u01c0\u01b4\3\2\2\2\u01c0\u01c1\3\2\2\2\u01c1\u01c2\3\2\2\2\u01c2"+
		"\u01cc\7/\2\2\u01c3\u01c8\5<\37\2\u01c4\u01c5\7^\2\2\u01c5\u01c7\5<\37"+
		"\2\u01c6\u01c4\3\2\2\2\u01c7\u01ca\3\2\2\2\u01c8\u01c6\3\2\2\2\u01c8\u01c9"+
		"\3\2\2\2\u01c9\u01cd\3\2\2\2\u01ca\u01c8\3\2\2\2\u01cb\u01cd\5F$\2\u01cc"+
		"\u01c3\3\2\2\2\u01cc\u01cb\3\2\2\2\u01cd\u01cf\3\2\2\2\u01ce\u01d0\58"+
		"\35\2\u01cf\u01ce\3\2\2\2\u01cf\u01d0\3\2\2\2\u01d0\67\3\2\2\2\u01d1\u01d2"+
		"\7U\2\2\u01d2\u01d3\5P)\2\u01d3\u01e2\3\2\2\2\u01d4\u01d5\7\61\2\2\u01d5"+
		"\u01d6\7\36\2\2\u01d6\u01db\5P)\2\u01d7\u01d8\7^\2\2\u01d8\u01da\5P)\2"+
		"\u01d9\u01d7\3\2\2\2\u01da\u01dd\3\2\2\2\u01db\u01d9\3\2\2\2\u01db\u01dc"+
		"\3\2\2\2\u01dc\u01e0\3\2\2\2\u01dd\u01db\3\2\2\2\u01de\u01df\7\62\2\2"+
		"\u01df\u01e1\5P)\2\u01e0\u01de\3\2\2\2\u01e0\u01e1\3\2\2\2\u01e1\u01e3"+
		"\3\2\2\2\u01e2\u01d4\3\2\2\2\u01e2\u01e3\3\2\2\2\u01e3\u0201\3\2\2\2\u01e4"+
		"\u01e5\7S\2\2\u01e5\u01e6\7d\2\2\u01e6\u01eb\5P)\2\u01e7\u01e8\7^\2\2"+
		"\u01e8\u01ea\5P)\2\u01e9\u01e7\3\2\2\2\u01ea\u01ed\3\2\2\2\u01eb\u01e9"+
		"\3\2\2\2\u01eb\u01ec\3\2\2\2\u01ec\u01ee\3\2\2\2\u01ed\u01eb\3\2\2\2\u01ee"+
		"\u01fd\7e\2\2\u01ef\u01f0\7^\2\2\u01f0\u01f1\7d\2\2\u01f1\u01f6\5P)\2"+
		"\u01f2\u01f3\7^\2\2\u01f3\u01f5\5P)\2\u01f4\u01f2\3\2\2\2\u01f5\u01f8"+
		"\3\2\2\2\u01f6\u01f4\3\2\2\2\u01f6\u01f7\3\2\2\2\u01f7\u01f9\3\2\2\2\u01f8"+
		"\u01f6\3\2\2\2\u01f9\u01fa\7e\2\2\u01fa\u01fc\3\2\2\2\u01fb\u01ef\3\2"+
		"\2\2\u01fc\u01ff\3\2\2\2\u01fd\u01fb\3\2\2\2\u01fd\u01fe\3\2\2\2\u01fe"+
		"\u0201\3\2\2\2\u01ff\u01fd\3\2\2\2\u0200\u01d1\3\2\2\2\u0200\u01e4\3\2"+
		"\2\2\u0201\u0233\3\2\2\2\u0202\u0203\7U\2\2\u0203\u0205\5P)\2\u0204\u0202"+
		"\3\2\2\2\u0204\u0205\3\2\2\2\u0205\u0206\3\2\2\2\u0206\u0207\7\61\2\2"+
		"\u0207\u0208\7\36\2\2\u0208\u020d\5P)\2\u0209\u020a\7^\2\2\u020a\u020c"+
		"\5P)\2\u020b\u0209\3\2\2\2\u020c\u020f\3\2\2\2\u020d\u020b\3\2\2\2\u020d"+
		"\u020e\3\2\2\2\u020e\u0212\3\2\2\2\u020f\u020d\3\2\2\2\u0210\u0211\7\62"+
		"\2\2\u0211\u0213\5P)\2\u0212\u0210\3\2\2\2\u0212\u0213\3\2\2\2\u0213\u0231"+
		"\3\2\2\2\u0214\u0215\7S\2\2\u0215\u0216\7d\2\2\u0216\u021b\5P)\2\u0217"+
		"\u0218\7^\2\2\u0218\u021a\5P)\2\u0219\u0217\3\2\2\2\u021a\u021d\3\2\2"+
		"\2\u021b\u0219\3\2\2\2\u021b\u021c\3\2\2\2\u021c\u021e\3\2\2\2\u021d\u021b"+
		"\3\2\2\2\u021e\u022d\7e\2\2\u021f\u0220\7^\2\2\u0220\u0221\7d\2\2\u0221"+
		"\u0226\5P)\2\u0222\u0223\7^\2\2\u0223\u0225\5P)\2\u0224\u0222\3\2\2\2"+
		"\u0225\u0228\3\2\2\2\u0226\u0224\3\2\2\2\u0226\u0227\3\2\2\2\u0227\u0229"+
		"\3\2\2\2\u0228\u0226\3\2\2\2\u0229\u022a\7e\2\2\u022a\u022c\3\2\2\2\u022b"+
		"\u021f\3\2\2\2\u022c\u022f\3\2\2\2\u022d\u022b\3\2\2\2\u022d\u022e\3\2"+
		"\2\2\u022e\u0231\3\2\2\2\u022f\u022d\3\2\2\2\u0230\u0204\3\2\2\2\u0230"+
		"\u0214\3\2\2\2\u0231\u0233\3\2\2\2\u0232\u0200\3\2\2\2\u0232\u0230\3\2"+
		"\2\2\u02339\3\2\2\2\u0234\u0241\7\7\2\2\u0235\u0236\5d\63\2\u0236\u0237"+
		"\7c\2\2\u0237\u0238\7\7\2\2\u0238\u0241\3\2\2\2\u0239\u023e\5P)\2\u023a"+
		"\u023c\7\33\2\2\u023b\u023a\3\2\2\2\u023b\u023c\3\2\2\2\u023c\u023d\3"+
		"\2\2\2\u023d\u023f\5f\64\2\u023e\u023b\3\2\2\2\u023e\u023f\3\2\2\2\u023f"+
		"\u0241\3\2\2\2\u0240\u0234\3\2\2\2\u0240\u0235\3\2\2\2\u0240\u0239\3\2"+
		"\2\2\u0241;\3\2\2\2\u0242\u0243\5b\62\2\u0243\u0244\7c\2\2\u0244\u0246"+
		"\3\2\2\2\u0245\u0242\3\2\2\2\u0245\u0246\3\2\2\2\u0246\u0247\3\2\2\2\u0247"+
		"\u024c\5d\63\2\u0248\u024a\7\33\2\2\u0249\u0248\3\2\2\2\u0249\u024a\3"+
		"\2\2\2\u024a\u024b\3\2\2\2\u024b\u024d\5j\66\2\u024c\u0249\3\2\2\2\u024c"+
		"\u024d\3\2\2\2\u024d\u0253\3\2\2\2\u024e\u024f\7\66\2\2\u024f\u0250\7"+
		"\36\2\2\u0250\u0254\5l\67\2\u0251\u0252\7B\2\2\u0252\u0254\7\66\2\2\u0253"+
		"\u024e\3\2\2\2\u0253\u0251\3\2\2\2\u0253\u0254\3\2\2\2\u0254\u0272\3\2"+
		"\2\2\u0255\u025f\7d\2\2\u0256\u025b\5<\37\2\u0257\u0258\7^\2\2\u0258\u025a"+
		"\5<\37\2\u0259\u0257\3\2\2\2\u025a\u025d\3\2\2\2\u025b\u0259\3\2\2\2\u025b"+
		"\u025c\3\2\2\2\u025c\u0260\3\2\2\2\u025d\u025b\3\2\2\2\u025e\u0260\5F"+
		"$\2\u025f\u0256\3\2\2\2\u025f\u025e\3\2\2\2\u0260\u0261\3\2\2\2\u0261"+
		"\u0266\7e\2\2\u0262\u0264\7\33\2\2\u0263\u0262\3\2\2\2\u0263\u0264\3\2"+
		"\2\2\u0264\u0265\3\2\2\2\u0265\u0267\5j\66\2\u0266\u0263\3\2\2\2\u0266"+
		"\u0267\3\2\2\2\u0267\u0272\3\2\2\2\u0268\u0269\7d\2\2\u0269\u026a\5@!"+
		"\2\u026a\u026f\7e\2\2\u026b\u026d\7\33\2\2\u026c\u026b\3\2\2\2\u026c\u026d"+
		"\3\2\2\2\u026d\u026e\3\2\2\2\u026e\u0270\5j\66\2\u026f\u026c\3\2\2\2\u026f"+
		"\u0270\3\2\2\2\u0270\u0272\3\2\2\2\u0271\u0245\3\2\2\2\u0271\u0255\3\2"+
		"\2\2\u0271\u0268\3\2\2\2\u0272=\3\2\2\2\u0273\u0274\t\6\2\2\u0274?\3\2"+
		"\2\2\u0275\u0277\7V\2\2\u0276\u0278\7K\2\2\u0277\u0276\3\2\2\2\u0277\u0278"+
		"\3\2\2\2\u0278\u0279\3\2\2\2\u0279\u027e\5L\'\2\u027a\u027b\7^\2\2\u027b"+
		"\u027d\5L\'\2\u027c\u027a\3\2\2\2\u027d\u0280\3\2\2\2\u027e\u027c\3\2"+
		"\2\2\u027e\u027f\3\2\2\2\u027f\u0282\3\2\2\2\u0280\u027e\3\2\2\2\u0281"+
		"\u0275\3\2\2\2\u0281\u0282\3\2\2\2\u0282\u0283\3\2\2\2\u0283\u0289\5B"+
		"\"\2\u0284\u0285\5D#\2\u0285\u0286\5B\"\2\u0286\u0288\3\2\2\2\u0287\u0284"+
		"\3\2\2\2\u0288\u028b\3\2\2\2\u0289\u0287\3\2\2\2\u0289\u028a\3\2\2\2\u028a"+
		"\u0296\3\2\2\2\u028b\u0289\3\2\2\2\u028c\u028d\7H\2\2\u028d\u028e\7\36"+
		"\2\2\u028e\u0293\5N(\2\u028f\u0290\7^\2\2\u0290\u0292\5N(\2\u0291\u028f"+
		"\3\2\2\2\u0292\u0295\3\2\2\2\u0293\u0291\3\2\2\2\u0293\u0294\3\2\2\2\u0294"+
		"\u0297\3\2\2\2\u0295\u0293\3\2\2\2\u0296\u028c\3\2\2\2\u0296\u0297\3\2"+
		"\2\2\u0297\u029e\3\2\2\2\u0298\u0299\7>\2\2\u0299\u029c\5P)\2\u029a\u029b"+
		"\t\4\2\2\u029b\u029d\5P)\2\u029c\u029a\3\2\2\2\u029c\u029d\3\2\2\2\u029d"+
		"\u029f\3\2\2\2\u029e\u0298\3\2\2\2\u029e\u029f\3\2\2\2\u029fA\3\2\2\2"+
		"\u02a0\u02a2\7O\2\2\u02a1\u02a3\t\5\2\2\u02a2\u02a1\3\2\2\2\u02a2\u02a3"+
		"\3\2\2\2\u02a3\u02a4\3\2\2\2\u02a4\u02a9\5:\36\2\u02a5\u02a6\7^\2\2\u02a6"+
		"\u02a8\5:\36\2\u02a7\u02a5\3\2\2\2\u02a8\u02ab\3\2\2\2\u02a9\u02a7\3\2"+
		"\2\2\u02a9\u02aa\3\2\2\2\u02aa\u02ad\3\2\2\2\u02ab\u02a9\3\2\2\2\u02ac"+
		"\u02a0\3\2\2\2\u02ac\u02ad\3\2\2\2\u02ad\u02ae\3\2\2\2\u02ae\u02b8\7/"+
		"\2\2\u02af\u02b4\5<\37\2\u02b0\u02b1\7^\2\2\u02b1\u02b3\5<\37\2\u02b2"+
		"\u02b0\3\2\2\2\u02b3\u02b6\3\2\2\2\u02b4\u02b2\3\2\2\2\u02b4\u02b5\3\2"+
		"\2\2\u02b5\u02b9\3\2\2\2\u02b6\u02b4\3\2\2\2\u02b7\u02b9\5F$\2\u02b8\u02af"+
		"\3\2\2\2\u02b8\u02b7\3\2\2\2\u02b9\u02bb\3\2\2\2\u02ba\u02bc\58\35\2\u02bb"+
		"\u02ba\3\2\2\2\u02bb\u02bc\3\2\2\2\u02bcC\3\2\2\2\u02bd\u02c3\7Q\2\2\u02be"+
		"\u02bf\7Q\2\2\u02bf\u02c3\7\31\2\2\u02c0\u02c3\78\2\2\u02c1\u02c3\7+\2"+
		"\2\u02c2\u02bd\3\2\2\2\u02c2\u02be\3\2\2\2\u02c2\u02c0\3\2\2\2\u02c2\u02c1"+
		"\3\2\2\2\u02c3E\3\2\2\2\u02c4\u02ce\5<\37\2\u02c5\u02c8\5H%\2\u02c6\u02c9"+
		"\5F$\2\u02c7\u02c9\5<\37\2\u02c8\u02c6\3\2\2\2\u02c8\u02c7\3\2\2\2\u02c9"+
		"\u02ca\3\2\2\2\u02ca\u02cb\5J&\2\u02cb\u02cd\3\2\2\2\u02cc\u02c5\3\2\2"+
		"\2\u02cd\u02d0\3\2\2\2\u02ce\u02cc\3\2\2\2\u02ce\u02cf\3\2\2\2\u02cfG"+
		"\3\2\2\2\u02d0\u02ce\3\2\2\2\u02d1\u02e7\7^\2\2\u02d2\u02d4\7@\2\2\u02d3"+
		"\u02d2\3\2\2\2\u02d3\u02d4\3\2\2\2\u02d4\u02e3\3\2\2\2\u02d5\u02d7\7<"+
		"\2\2\u02d6\u02d8\7I\2\2\u02d7\u02d6\3\2\2\2\u02d7\u02d8\3\2\2\2\u02d8"+
		"\u02e4\3\2\2\2\u02d9\u02db\7M\2\2\u02da\u02dc\7I\2\2\u02db\u02da\3\2\2"+
		"\2\u02db\u02dc\3\2\2\2\u02dc\u02e4\3\2\2\2\u02dd\u02df\7.\2\2\u02de\u02e0"+
		"\7I\2\2\u02df\u02de\3\2\2\2\u02df\u02e0\3\2\2\2\u02e0\u02e4\3\2\2\2\u02e1"+
		"\u02e4\7\67\2\2\u02e2\u02e4\7\"\2\2\u02e3\u02d5\3\2\2\2\u02e3\u02d9\3"+
		"\2\2\2\u02e3\u02dd\3\2\2\2\u02e3\u02e1\3\2\2\2\u02e3\u02e2\3\2\2\2\u02e3"+
		"\u02e4\3\2\2\2\u02e4\u02e5\3\2\2\2\u02e5\u02e7\7;\2\2\u02e6\u02d1\3\2"+
		"\2\2\u02e6\u02d3\3\2\2\2\u02e7I\3\2\2\2\u02e8\u02e9\7F\2\2\u02e9\u02f7"+
		"\5P)\2\u02ea\u02eb\7R\2\2\u02eb\u02ec\7d\2\2\u02ec\u02f1\5h\65\2\u02ed"+
		"\u02ee\7^\2\2\u02ee\u02f0\5h\65\2\u02ef\u02ed\3\2\2\2\u02f0\u02f3\3\2"+
		"\2\2\u02f1\u02ef\3\2\2\2\u02f1\u02f2\3\2\2\2\u02f2\u02f4\3\2\2\2\u02f3"+
		"\u02f1\3\2\2\2\u02f4\u02f5\7e\2\2\u02f5\u02f7\3\2\2\2\u02f6\u02e8\3\2"+
		"\2\2\u02f6\u02ea\3\2\2\2\u02f6\u02f7\3\2\2\2\u02f7K\3\2\2\2\u02f8\u0304"+
		"\5d\63\2\u02f9\u02fa\7d\2\2\u02fa\u02ff\5h\65\2\u02fb\u02fc\7^\2\2\u02fc"+
		"\u02fe\5h\65\2\u02fd\u02fb\3\2\2\2\u02fe\u0301\3\2\2\2\u02ff\u02fd\3\2"+
		"\2\2\u02ff\u0300\3\2\2\2\u0300\u0302\3\2\2\2\u0301\u02ff\3\2\2\2\u0302"+
		"\u0303\7e\2\2\u0303\u0305\3\2\2\2\u0304\u02f9\3\2\2\2\u0304\u0305\3\2"+
		"\2\2\u0305\u0306\3\2\2\2\u0306\u0307\7\33\2\2\u0307\u0308\7d\2\2\u0308"+
		"\u0309\5@!\2\u0309\u030a\7e\2\2\u030aM\3\2\2\2\u030b\u030e\5P)\2\u030c"+
		"\u030d\7!\2\2\u030d\u030f\5`\61\2\u030e\u030c\3\2\2\2\u030e\u030f\3\2"+
		"\2\2\u030f\u0311\3\2\2\2\u0310\u0312\t\7\2\2\u0311\u0310\3\2\2\2\u0311"+
		"\u0312\3\2\2\2\u0312O\3\2\2\2\u0313\u0314\b)\1\2\u0314\u0315\5T+\2\u0315"+
		"\u0316\5P)\26\u0316\u034e\3\2\2\2\u0317\u034e\5\b\5\2\u0318\u034e\7n\2"+
		"\2\u0319\u031a\5b\62\2\u031a\u031b\7c\2\2\u031b\u031d\3\2\2\2\u031c\u0319"+
		"\3\2\2\2\u031c\u031d\3\2\2\2\u031d\u031e\3\2\2\2\u031e\u031f\5j\66\2\u031f"+
		"\u0320\7c\2\2\u0320\u0322\3\2\2\2\u0321\u031c\3\2\2\2\u0321\u0322\3\2"+
		"\2\2\u0322\u0323\3\2\2\2\u0323\u034e\5h\65\2\u0324\u0325\7d\2\2\u0325"+
		"\u0326\5P)\2\u0326\u0327\7e\2\2\u0327\u034e\3\2\2\2\u0328\u0329\7 \2\2"+
		"\u0329\u032a\7d\2\2\u032a\u032b\5P)\2\u032b\u032c\7\33\2\2\u032c\u032d"+
		"\5X-\2\u032d\u032e\7e\2\2\u032e\u034e\3\2\2\2\u032f\u0331\7B\2\2\u0330"+
		"\u032f\3\2\2\2\u0330\u0331\3\2\2\2\u0331\u0332\3\2\2\2\u0332\u0334\7,"+
		"\2\2\u0333\u0330\3\2\2\2\u0333\u0334\3\2\2\2\u0334\u0335\3\2\2\2\u0335"+
		"\u0336\7d\2\2\u0336\u0337\5@!\2\u0337\u0338\7e\2\2\u0338\u034e\3\2\2\2"+
		"\u0339\u033b\7\37\2\2\u033a\u033c\5P)\2\u033b\u033a\3\2\2\2\u033b\u033c"+
		"\3\2\2\2\u033c\u0342\3\2\2\2\u033d\u033e\7T\2\2\u033e\u033f\5P)\2\u033f"+
		"\u0340\7P\2\2\u0340\u0341\5P)\2\u0341\u0343\3\2\2\2\u0342\u033d\3\2\2"+
		"\2\u0343\u0344\3\2\2\2\u0344\u0342\3\2\2\2\u0344\u0345\3\2\2\2\u0345\u0348"+
		"\3\2\2\2\u0346\u0347\7(\2\2\u0347\u0349\5P)\2\u0348\u0346\3\2\2\2\u0348"+
		"\u0349\3\2\2\2\u0349\u034a\3\2\2\2\u034a\u034b\7)\2\2\u034b\u034e\3\2"+
		"\2\2\u034c\u034e\5t;\2\u034d\u0313\3\2\2\2\u034d\u0317\3\2\2\2\u034d\u0318"+
		"\3\2\2\2\u034d\u0321\3\2\2\2\u034d\u0324\3\2\2\2\u034d\u0328\3\2\2\2\u034d"+
		"\u0333\3\2\2\2\u034d\u0339\3\2\2\2\u034d\u034c\3\2\2\2\u034e\u03b3\3\2"+
		"\2\2\u034f\u0350\f\25\2\2\u0350\u0351\7\3\2\2\u0351\u03b2\5P)\26\u0352"+
		"\u0353\f\24\2\2\u0353\u0354\t\b\2\2\u0354\u03b2\5P)\25\u0355\u0356\f\23"+
		"\2\2\u0356\u0357\t\t\2\2\u0357\u03b2\5P)\24\u0358\u0359\f\22\2\2\u0359"+
		"\u035a\t\n\2\2\u035a\u03b2\5P)\23\u035b\u035c\f\21\2\2\u035c\u035d\t\13"+
		"\2\2\u035d\u03b2\5P)\22\u035e\u036b\f\20\2\2\u035f\u036c\7\22\2\2\u0360"+
		"\u036c\7\23\2\2\u0361\u036c\7\24\2\2\u0362\u036c\7\25\2\2\u0363\u036c"+
		"\79\2\2\u0364\u0365\79\2\2\u0365\u036c\7B\2\2\u0366\u036c\7\65\2\2\u0367"+
		"\u036c\7=\2\2\u0368\u036c\7\60\2\2\u0369\u036c\7?\2\2\u036a\u036c\7L\2"+
		"\2\u036b\u035f\3\2\2\2\u036b\u0360\3\2\2\2\u036b\u0361\3\2\2\2\u036b\u0362"+
		"\3\2\2\2\u036b\u0363\3\2\2\2\u036b\u0364\3\2\2\2\u036b\u0366\3\2\2\2\u036b"+
		"\u0367\3\2\2\2\u036b\u0368\3\2\2\2\u036b\u0369\3\2\2\2\u036b\u036a\3\2"+
		"\2\2\u036c\u036d\3\2\2\2\u036d\u03b2\5P)\21\u036e\u036f\f\17\2\2\u036f"+
		"\u0370\7\32\2\2\u0370\u03b2\5P)\20\u0371\u0372\f\16\2\2\u0372\u0373\7"+
		"G\2\2\u0373\u03b2\5P)\17\u0374\u0375\f\b\2\2\u0375\u0377\79\2\2\u0376"+
		"\u0378\7B\2\2\u0377\u0376\3\2\2\2\u0377\u0378\3\2\2\2\u0378\u0379\3\2"+
		"\2\2\u0379\u03b2\5P)\t\u037a\u037c\f\7\2\2\u037b\u037d\7B\2\2\u037c\u037b"+
		"\3\2\2\2\u037c\u037d\3\2\2\2\u037d\u037e\3\2\2\2\u037e\u037f\7\35\2\2"+
		"\u037f\u0380\5P)\2\u0380\u0381\7\32\2\2\u0381\u0382\5P)\b\u0382\u03b2"+
		"\3\2\2\2\u0383\u0384\f\13\2\2\u0384\u0385\7!\2\2\u0385\u03b2\5`\61\2\u0386"+
		"\u0388\f\n\2\2\u0387\u0389\7B\2\2\u0388\u0387\3\2\2\2\u0388\u0389\3\2"+
		"\2\2\u0389\u038a\3\2\2\2\u038a\u038b\t\f\2\2\u038b\u038e\5P)\2\u038c\u038d"+
		"\7*\2\2\u038d\u038f\5P)\2\u038e\u038c\3\2\2\2\u038e\u038f\3\2\2\2\u038f"+
		"\u03b2\3\2\2\2\u0390\u0395\f\t\2\2\u0391\u0396\7:\2\2\u0392\u0396\7C\2"+
		"\2\u0393\u0394\7B\2\2\u0394\u0396\7D\2\2\u0395\u0391\3\2\2\2\u0395\u0392"+
		"\3\2\2\2\u0395\u0393\3\2\2\2\u0396\u03b2\3\2\2\2\u0397\u0399\f\6\2\2\u0398"+
		"\u039a\7B\2\2\u0399\u0398\3\2\2\2\u0399\u039a\3\2\2\2\u039a\u039b\3\2"+
		"\2\2\u039b\u03af\7\65\2\2\u039c\u03a6\7d\2\2\u039d\u03a7\5@!\2\u039e\u03a3"+
		"\5P)\2\u039f\u03a0\7^\2\2\u03a0\u03a2\5P)\2\u03a1\u039f\3\2\2\2\u03a2"+
		"\u03a5\3\2\2\2\u03a3\u03a1\3\2\2\2\u03a3\u03a4\3\2\2\2\u03a4\u03a7\3\2"+
		"\2\2\u03a5\u03a3\3\2\2\2\u03a6\u039d\3\2\2\2\u03a6\u039e\3\2\2\2\u03a6"+
		"\u03a7\3\2\2\2\u03a7\u03a8\3\2\2\2\u03a8\u03b0\7e\2\2\u03a9\u03aa\5b\62"+
		"\2\u03aa\u03ab\7c\2\2\u03ab\u03ad\3\2\2\2\u03ac\u03a9\3\2\2\2\u03ac\u03ad"+
		"\3\2\2\2\u03ad\u03ae\3\2\2\2\u03ae\u03b0\5d\63\2\u03af\u039c\3\2\2\2\u03af"+
		"\u03ac\3\2\2\2\u03b0\u03b2\3\2\2\2\u03b1\u034f\3\2\2\2\u03b1\u0352\3\2"+
		"\2\2\u03b1\u0355\3\2\2\2\u03b1\u0358\3\2\2\2\u03b1\u035b\3\2\2\2\u03b1"+
		"\u035e\3\2\2\2\u03b1\u036e\3\2\2\2\u03b1\u0371\3\2\2\2\u03b1\u0374\3\2"+
		"\2\2\u03b1\u037a\3\2\2\2\u03b1\u0383\3\2\2\2\u03b1\u0386\3\2\2\2\u03b1"+
		"\u0390\3\2\2\2\u03b1\u0397\3\2\2\2\u03b2\u03b5\3\2\2\2\u03b3\u03b1\3\2"+
		"\2\2\u03b3\u03b4\3\2\2\2\u03b4Q\3\2\2\2\u03b5\u03b3\3\2\2\2\u03b6\u03b7"+
		"\t\r\2\2\u03b7S\3\2\2\2\u03b8\u03b9\t\16\2\2\u03b9U\3\2\2\2\u03ba\u03bb"+
		"\5n8\2\u03bbW\3\2\2\2\u03bc\u03be\5V,\2\u03bd\u03bc\3\2\2\2\u03be\u03bf"+
		"\3\2\2\2\u03bf\u03bd\3\2\2\2\u03bf\u03c0\3\2\2\2\u03c0\u03cb\3\2\2\2\u03c1"+
		"\u03c2\7d\2\2\u03c2\u03c3\5r:\2\u03c3\u03c4\7e\2\2\u03c4\u03cc\3\2\2\2"+
		"\u03c5\u03c6\7d\2\2\u03c6\u03c7\5r:\2\u03c7\u03c8\7^\2\2\u03c8\u03c9\5"+
		"r:\2\u03c9\u03ca\7e\2\2\u03ca\u03cc\3\2\2\2\u03cb\u03c1\3\2\2\2\u03cb"+
		"\u03c5\3\2\2\2\u03cb\u03cc\3\2\2\2\u03ccY\3\2\2\2\u03cd\u03ce\5n8\2\u03ce"+
		"[\3\2\2\2\u03cf\u03d0\5^\60\2\u03d0]\3\2\2\2\u03d1\u03d2\t\17\2\2\u03d2"+
		"_\3\2\2\2\u03d3\u03d4\5n8\2\u03d4a\3\2\2\2\u03d5\u03d6\5n8\2\u03d6c\3"+
		"\2\2\2\u03d7\u03d8\5n8\2\u03d8e\3\2\2\2\u03d9\u03da\t\20\2\2\u03dag\3"+
		"\2\2\2\u03db\u03dc\5n8\2\u03dci\3\2\2\2\u03dd\u03de\5n8\2\u03dek\3\2\2"+
		"\2\u03df\u03e0\5n8\2\u03e0m\3\2\2\2\u03e1\u03e4\5> \2\u03e2\u03e4\7o\2"+
		"\2\u03e3\u03e1\3\2\2\2\u03e3\u03e2\3\2\2\2\u03e4o\3\2\2\2\u03e5\u03e6"+
		"\7p\2\2\u03e6q\3\2\2\2\u03e7\u03e9\t\t\2\2\u03e8\u03e7\3\2\2\2\u03e8\u03e9"+
		"\3\2\2\2\u03e9\u03ea\3\2\2\2\u03ea\u03eb\7l\2\2\u03ebs\3\2\2\2\u03ec\u03ed"+
		"\7J\2\2\u03ed\u03f2\7d\2\2\u03ee\u03f3\7\64\2\2\u03ef\u03f0\t\21\2\2\u03f0"+
		"\u03f1\7^\2\2\u03f1\u03f3\5v<\2\u03f2\u03ee\3\2\2\2\u03f2\u03ef\3\2\2"+
		"\2\u03f3\u03f4\3\2\2\2\u03f4\u03f5\7e\2\2\u03f5u\3\2\2\2\u03f6\u03f7\7"+
		"p\2\2\u03f7w\3\2\2\2\u008c{\177\u0082\u0088\u008c\u009a\u009f\u00a2\u00a7"+
		"\u00af\u00b6\u00ba\u00ca\u00d6\u00da\u00e1\u00e5\u00ec\u00f0\u00f5\u00fa"+
		"\u00fe\u010b\u010e\u0114\u0119\u011d\u0127\u012c\u0130\u0135\u0154\u015e"+
		"\u0165\u016a\u016e\u0177\u017c\u0182\u0187\u018b\u0192\u0195\u019d\u01a7"+
		"\u01aa\u01b0\u01b2\u01b6\u01bd\u01c0\u01c8\u01cc\u01cf\u01db\u01e0\u01e2"+
		"\u01eb\u01f6\u01fd\u0200\u0204\u020d\u0212\u021b\u0226\u022d\u0230\u0232"+
		"\u023b\u023e\u0240\u0245\u0249\u024c\u0253\u025b\u025f\u0263\u0266\u026c"+
		"\u026f\u0271\u0277\u027e\u0281\u0289\u0293\u0296\u029c\u029e\u02a2\u02a9"+
		"\u02ac\u02b4\u02b8\u02bb\u02c2\u02c8\u02ce\u02d3\u02d7\u02db\u02df\u02e3"+
		"\u02e6\u02f1\u02f6\u02ff\u0304\u030e\u0311\u031c\u0321\u0330\u0333\u033b"+
		"\u0344\u0348\u034d\u036b\u0377\u037c\u0388\u038e\u0395\u0399\u03a3\u03a6"+
		"\u03ac\u03af\u03b1\u03b3\u03bf\u03cb\u03e3\u03e8\u03f2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}