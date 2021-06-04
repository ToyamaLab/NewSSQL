// Generated from prefix.g4 by ANTLR 4.5

package supersql.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class prefixParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, DEF=4, K_FOREACH=5, K_FOREACH1=6, K_PARAMETER=7, 
		K_IMPORT=8, K_DEFINE=9, OPEN_PARENTHESE=10, CLOSE_PARENTHESE=11, OPEN_BRACKET=12, 
		CLOSE_BRACKET=13, OPEN_BRACE=14, CLOSE_BRACE=15, IDENTIFIER=16, STRING_LITERAL=17, 
		MULTI_LINE_COMMENT=18, SINGLE_LINE_COMMENT=19, WS=20, UNEXPECTED_CHAR=21;
	public static final int
		RULE_prefix = 0, RULE_fix = 1, RULE_operand = 2, RULE_exdef = 3, RULE_foreach = 4, 
		RULE_function = 5, RULE_expr = 6, RULE_function_name = 7, RULE_database_name = 8, 
		RULE_table_name = 9, RULE_column_alias = 10, RULE_column_name = 11, RULE_table_alias = 12, 
		RULE_index_name = 13, RULE_any_name = 14;
	public static final String[] ruleNames = {
		"prefix", "fix", "operand", "exdef", "foreach", "function", "expr", "function_name", 
		"database_name", "table_name", "column_alias", "column_name", "table_alias", 
		"index_name", "any_name"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "','", "'.'", "'#'", null, null, null, null, null, null, "'('", 
		"')'", "'['", "']'", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, "DEF", "K_FOREACH", "K_FOREACH1", "K_PARAMETER", 
		"K_IMPORT", "K_DEFINE", "OPEN_PARENTHESE", "CLOSE_PARENTHESE", "OPEN_BRACKET", 
		"CLOSE_BRACKET", "OPEN_BRACE", "CLOSE_BRACE", "IDENTIFIER", "STRING_LITERAL", 
		"MULTI_LINE_COMMENT", "SINGLE_LINE_COMMENT", "WS", "UNEXPECTED_CHAR"
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
	public String getGrammarFileName() { return "prefix.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public prefixParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class PrefixContext extends ParserRuleContext {
		public List<FixContext> fix() {
			return getRuleContexts(FixContext.class);
		}
		public FixContext fix(int i) {
			return getRuleContext(FixContext.class,i);
		}
		public PrefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterPrefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitPrefix(this);
		}
	}

	public final PrefixContext prefix() throws RecognitionException {
		PrefixContext _localctx = new PrefixContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prefix);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			fix();
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(31);
				match(T__0);
				setState(32);
				fix();
				}
				}
				setState(37);
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

	public static class FixContext extends ParserRuleContext {
		public ForeachContext foreach() {
			return getRuleContext(ForeachContext.class,0);
		}
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public FixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterFix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitFix(this);
		}
	}

	public final FixContext fix() throws RecognitionException {
		FixContext _localctx = new FixContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_fix);
		try {
			setState(41);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(38);
				foreach();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(39);
				function();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(40);
				expr();
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

	public static class OperandContext extends ParserRuleContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Table_aliasContext table_alias() {
			return getRuleContext(Table_aliasContext.class,0);
		}
		public OperandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterOperand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitOperand(this);
		}
	}

	public final OperandContext operand() throws RecognitionException {
		OperandContext _localctx = new OperandContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_operand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(43);
				table_alias();
				setState(44);
				match(T__1);
				}
				break;
			}
			setState(48);
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

	public static class ExdefContext extends ParserRuleContext {
		public TerminalNode K_IMPORT() { return getToken(prefixParser.K_IMPORT, 0); }
		public TerminalNode IDENTIFIER() { return getToken(prefixParser.IDENTIFIER, 0); }
		public TerminalNode K_DEFINE() { return getToken(prefixParser.K_DEFINE, 0); }
		public Function_nameContext function_name() {
			return getRuleContext(Function_nameContext.class,0);
		}
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public TerminalNode OPEN_BRACE() { return getToken(prefixParser.OPEN_BRACE, 0); }
		public TerminalNode DEF() { return getToken(prefixParser.DEF, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(prefixParser.CLOSE_BRACE, 0); }
		public ExdefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exdef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterExdef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitExdef(this);
		}
	}

	public final ExdefContext exdef() throws RecognitionException {
		ExdefContext _localctx = new ExdefContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_exdef);
		try {
			setState(61);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(50);
				match(T__2);
				setState(51);
				match(K_IMPORT);
				setState(52);
				match(IDENTIFIER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(53);
				match(T__2);
				setState(54);
				match(K_DEFINE);
				setState(55);
				function_name();
				setState(56);
				any_name();
				setState(57);
				match(OPEN_BRACE);
				setState(58);
				match(DEF);
				setState(59);
				match(CLOSE_BRACE);
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

	public static class ForeachContext extends ParserRuleContext {
		public TerminalNode K_FOREACH() { return getToken(prefixParser.K_FOREACH, 0); }
		public TerminalNode K_FOREACH1() { return getToken(prefixParser.K_FOREACH1, 0); }
		public List<OperandContext> operand() {
			return getRuleContexts(OperandContext.class);
		}
		public OperandContext operand(int i) {
			return getRuleContext(OperandContext.class,i);
		}
		public TerminalNode OPEN_PARENTHESE() { return getToken(prefixParser.OPEN_PARENTHESE, 0); }
		public TerminalNode CLOSE_PARENTHESE() { return getToken(prefixParser.CLOSE_PARENTHESE, 0); }
		public TerminalNode K_PARAMETER() { return getToken(prefixParser.K_PARAMETER, 0); }
		public ForeachContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_foreach; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterForeach(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitForeach(this);
		}
	}

	public final ForeachContext foreach() throws RecognitionException {
		ForeachContext _localctx = new ForeachContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_foreach);
		int _la;
		try {
			int _alt;
			setState(107);
			switch (_input.LA(1)) {
			case K_FOREACH:
			case K_FOREACH1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(63);
				_la = _input.LA(1);
				if ( !(_la==K_FOREACH || _la==K_FOREACH1) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(83);
				switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					{
					setState(64);
					operand();
					setState(69);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(65);
							match(T__0);
							setState(66);
							operand();
							}
							} 
						}
						setState(71);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
					}
					}
					break;
				case 2:
					{
					setState(72);
					match(OPEN_PARENTHESE);
					setState(73);
					operand();
					setState(78);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__0) {
						{
						{
						setState(74);
						match(T__0);
						setState(75);
						operand();
						}
						}
						setState(80);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(81);
					match(CLOSE_PARENTHESE);
					}
					break;
				}
				}
				}
				break;
			case K_PARAMETER:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(85);
				match(K_PARAMETER);
				setState(105);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(86);
					operand();
					setState(91);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(87);
							match(T__0);
							setState(88);
							operand();
							}
							} 
						}
						setState(93);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
					}
					}
					break;
				case 2:
					{
					setState(94);
					match(OPEN_PARENTHESE);
					setState(95);
					operand();
					setState(100);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__0) {
						{
						{
						setState(96);
						match(T__0);
						setState(97);
						operand();
						}
						}
						setState(102);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(103);
					match(CLOSE_PARENTHESE);
					}
					break;
				}
				}
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

	public static class FunctionContext extends ParserRuleContext {
		public Function_nameContext function_name() {
			return getRuleContext(Function_nameContext.class,0);
		}
		public TerminalNode OPEN_PARENTHESE() { return getToken(prefixParser.OPEN_PARENTHESE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode CLOSE_PARENTHESE() { return getToken(prefixParser.CLOSE_PARENTHESE, 0); }
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitFunction(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			function_name();
			setState(110);
			match(OPEN_PARENTHESE);
			setState(111);
			expr();
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(112);
				match(T__0);
				setState(113);
				expr();
				}
				}
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(119);
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

	public static class ExprContext extends ParserRuleContext {
		public List<TerminalNode> STRING_LITERAL() { return getTokens(prefixParser.STRING_LITERAL); }
		public TerminalNode STRING_LITERAL(int i) {
			return getToken(prefixParser.STRING_LITERAL, i);
		}
		public List<Column_nameContext> column_name() {
			return getRuleContexts(Column_nameContext.class);
		}
		public Column_nameContext column_name(int i) {
			return getRuleContext(Column_nameContext.class,i);
		}
		public List<Table_aliasContext> table_alias() {
			return getRuleContexts(Table_aliasContext.class);
		}
		public Table_aliasContext table_alias(int i) {
			return getRuleContext(Table_aliasContext.class,i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_expr);
		try {
			int _alt;
			setState(142);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(121);
				match(STRING_LITERAL);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(125);
				switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(122);
					table_alias();
					setState(123);
					match(T__1);
					}
					break;
				}
				setState(127);
				column_name();
				}
				setState(139);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						setState(137);
						switch (_input.LA(1)) {
						case T__0:
							{
							setState(129);
							match(T__0);
							setState(130);
							match(STRING_LITERAL);
							}
							break;
						case OPEN_PARENTHESE:
						case IDENTIFIER:
						case STRING_LITERAL:
							{
							{
							setState(134);
							switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
							case 1:
								{
								setState(131);
								table_alias();
								setState(132);
								match(T__1);
								}
								break;
							}
							setState(136);
							column_name();
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						} 
					}
					setState(141);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
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
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterFunction_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitFunction_name(this);
		}
	}

	public final Function_nameContext function_name() throws RecognitionException {
		Function_nameContext _localctx = new Function_nameContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_function_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
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
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterDatabase_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitDatabase_name(this);
		}
	}

	public final Database_nameContext database_name() throws RecognitionException {
		Database_nameContext _localctx = new Database_nameContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_database_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
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
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterTable_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitTable_name(this);
		}
	}

	public final Table_nameContext table_name() throws RecognitionException {
		Table_nameContext _localctx = new Table_nameContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_table_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
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
		public TerminalNode IDENTIFIER() { return getToken(prefixParser.IDENTIFIER, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(prefixParser.STRING_LITERAL, 0); }
		public Column_aliasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_alias; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterColumn_alias(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitColumn_alias(this);
		}
	}

	public final Column_aliasContext column_alias() throws RecognitionException {
		Column_aliasContext _localctx = new Column_aliasContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_column_alias);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
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
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterColumn_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitColumn_name(this);
		}
	}

	public final Column_nameContext column_name() throws RecognitionException {
		Column_nameContext _localctx = new Column_nameContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_column_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
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
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterTable_alias(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitTable_alias(this);
		}
	}

	public final Table_aliasContext table_alias() throws RecognitionException {
		Table_aliasContext _localctx = new Table_aliasContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_table_alias);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
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
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterIndex_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitIndex_name(this);
		}
	}

	public final Index_nameContext index_name() throws RecognitionException {
		Index_nameContext _localctx = new Index_nameContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_index_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
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
		public TerminalNode IDENTIFIER() { return getToken(prefixParser.IDENTIFIER, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(prefixParser.STRING_LITERAL, 0); }
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Any_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_any_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterAny_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitAny_name(this);
		}
	}

	public final Any_nameContext any_name() throws RecognitionException {
		Any_nameContext _localctx = new Any_nameContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_any_name);
		try {
			setState(164);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(158);
				match(IDENTIFIER);
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(159);
				match(STRING_LITERAL);
				}
				break;
			case OPEN_PARENTHESE:
				enterOuterAlt(_localctx, 3);
				{
				setState(160);
				match(OPEN_PARENTHESE);
				setState(161);
				any_name();
				setState(162);
				match(CLOSE_PARENTHESE);
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\27\u00a9\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\7\2$\n"+
		"\2\f\2\16\2\'\13\2\3\3\3\3\3\3\5\3,\n\3\3\4\3\4\3\4\5\4\61\n\4\3\4\3\4"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5@\n\5\3\6\3\6\3\6\3\6"+
		"\7\6F\n\6\f\6\16\6I\13\6\3\6\3\6\3\6\3\6\7\6O\n\6\f\6\16\6R\13\6\3\6\3"+
		"\6\5\6V\n\6\3\6\3\6\3\6\3\6\7\6\\\n\6\f\6\16\6_\13\6\3\6\3\6\3\6\3\6\7"+
		"\6e\n\6\f\6\16\6h\13\6\3\6\3\6\5\6l\n\6\5\6n\n\6\3\7\3\7\3\7\3\7\3\7\7"+
		"\7u\n\7\f\7\16\7x\13\7\3\7\3\7\3\b\3\b\3\b\3\b\5\b\u0080\n\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\5\b\u0089\n\b\3\b\7\b\u008c\n\b\f\b\16\b\u008f\13\b"+
		"\5\b\u0091\n\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17"+
		"\3\17\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00a7\n\20\3\20\2\2\21\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\32\34\36\2\4\3\2\7\b\3\2\22\23\u00ad\2 \3\2\2"+
		"\2\4+\3\2\2\2\6\60\3\2\2\2\b?\3\2\2\2\nm\3\2\2\2\fo\3\2\2\2\16\u0090\3"+
		"\2\2\2\20\u0092\3\2\2\2\22\u0094\3\2\2\2\24\u0096\3\2\2\2\26\u0098\3\2"+
		"\2\2\30\u009a\3\2\2\2\32\u009c\3\2\2\2\34\u009e\3\2\2\2\36\u00a6\3\2\2"+
		"\2 %\5\4\3\2!\"\7\3\2\2\"$\5\4\3\2#!\3\2\2\2$\'\3\2\2\2%#\3\2\2\2%&\3"+
		"\2\2\2&\3\3\2\2\2\'%\3\2\2\2(,\5\n\6\2),\5\f\7\2*,\5\16\b\2+(\3\2\2\2"+
		"+)\3\2\2\2+*\3\2\2\2,\5\3\2\2\2-.\5\32\16\2./\7\4\2\2/\61\3\2\2\2\60-"+
		"\3\2\2\2\60\61\3\2\2\2\61\62\3\2\2\2\62\63\5\30\r\2\63\7\3\2\2\2\64\65"+
		"\7\5\2\2\65\66\7\n\2\2\66@\7\22\2\2\678\7\5\2\289\7\13\2\29:\5\20\t\2"+
		":;\5\36\20\2;<\7\20\2\2<=\7\6\2\2=>\7\21\2\2>@\3\2\2\2?\64\3\2\2\2?\67"+
		"\3\2\2\2@\t\3\2\2\2AU\t\2\2\2BG\5\6\4\2CD\7\3\2\2DF\5\6\4\2EC\3\2\2\2"+
		"FI\3\2\2\2GE\3\2\2\2GH\3\2\2\2HV\3\2\2\2IG\3\2\2\2JK\7\f\2\2KP\5\6\4\2"+
		"LM\7\3\2\2MO\5\6\4\2NL\3\2\2\2OR\3\2\2\2PN\3\2\2\2PQ\3\2\2\2QS\3\2\2\2"+
		"RP\3\2\2\2ST\7\r\2\2TV\3\2\2\2UB\3\2\2\2UJ\3\2\2\2Vn\3\2\2\2Wk\7\t\2\2"+
		"X]\5\6\4\2YZ\7\3\2\2Z\\\5\6\4\2[Y\3\2\2\2\\_\3\2\2\2][\3\2\2\2]^\3\2\2"+
		"\2^l\3\2\2\2_]\3\2\2\2`a\7\f\2\2af\5\6\4\2bc\7\3\2\2ce\5\6\4\2db\3\2\2"+
		"\2eh\3\2\2\2fd\3\2\2\2fg\3\2\2\2gi\3\2\2\2hf\3\2\2\2ij\7\r\2\2jl\3\2\2"+
		"\2kX\3\2\2\2k`\3\2\2\2ln\3\2\2\2mA\3\2\2\2mW\3\2\2\2n\13\3\2\2\2op\5\20"+
		"\t\2pq\7\f\2\2qv\5\16\b\2rs\7\3\2\2su\5\16\b\2tr\3\2\2\2ux\3\2\2\2vt\3"+
		"\2\2\2vw\3\2\2\2wy\3\2\2\2xv\3\2\2\2yz\7\r\2\2z\r\3\2\2\2{\u0091\7\23"+
		"\2\2|}\5\32\16\2}~\7\4\2\2~\u0080\3\2\2\2\177|\3\2\2\2\177\u0080\3\2\2"+
		"\2\u0080\u0081\3\2\2\2\u0081\u0082\5\30\r\2\u0082\u008d\3\2\2\2\u0083"+
		"\u0084\7\3\2\2\u0084\u008c\7\23\2\2\u0085\u0086\5\32\16\2\u0086\u0087"+
		"\7\4\2\2\u0087\u0089\3\2\2\2\u0088\u0085\3\2\2\2\u0088\u0089\3\2\2\2\u0089"+
		"\u008a\3\2\2\2\u008a\u008c\5\30\r\2\u008b\u0083\3\2\2\2\u008b\u0088\3"+
		"\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e"+
		"\u0091\3\2\2\2\u008f\u008d\3\2\2\2\u0090{\3\2\2\2\u0090\177\3\2\2\2\u0091"+
		"\17\3\2\2\2\u0092\u0093\5\36\20\2\u0093\21\3\2\2\2\u0094\u0095\5\36\20"+
		"\2\u0095\23\3\2\2\2\u0096\u0097\5\36\20\2\u0097\25\3\2\2\2\u0098\u0099"+
		"\t\3\2\2\u0099\27\3\2\2\2\u009a\u009b\5\36\20\2\u009b\31\3\2\2\2\u009c"+
		"\u009d\5\36\20\2\u009d\33\3\2\2\2\u009e\u009f\5\36\20\2\u009f\35\3\2\2"+
		"\2\u00a0\u00a7\7\22\2\2\u00a1\u00a7\7\23\2\2\u00a2\u00a3\7\f\2\2\u00a3"+
		"\u00a4\5\36\20\2\u00a4\u00a5\7\r\2\2\u00a5\u00a7\3\2\2\2\u00a6\u00a0\3"+
		"\2\2\2\u00a6\u00a1\3\2\2\2\u00a6\u00a2\3\2\2\2\u00a7\37\3\2\2\2\24%+\60"+
		"?GPU]fkmv\177\u0088\u008b\u008d\u0090\u00a6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}