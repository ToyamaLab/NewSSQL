// Generated from prefix.g4 by ANTLR 4.5

package supersql.parser;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class prefixLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, DEF=4, K_FOREACH=5, K_FOREACH1=6, K_PARAMETER=7, 
		K_IMPORT=8, K_DEFINE=9, OPEN_PARENTHESE=10, CLOSE_PARENTHESE=11, OPEN_BRACKET=12, 
		CLOSE_BRACKET=13, OPEN_BRACE=14, CLOSE_BRACE=15, IDENTIFIER=16, STRING_LITERAL=17, 
		MULTI_LINE_COMMENT=18, SINGLE_LINE_COMMENT=19, WS=20, UNEXPECTED_CHAR=21;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "DEF", "K_FOREACH", "K_FOREACH1", "K_PARAMETER", 
		"K_IMPORT", "K_DEFINE", "OPEN_PARENTHESE", "CLOSE_PARENTHESE", "OPEN_BRACKET", 
		"CLOSE_BRACKET", "OPEN_BRACE", "CLOSE_BRACE", "IDENTIFIER", "STRING_LITERAL", 
		"MULTI_LINE_COMMENT", "SINGLE_LINE_COMMENT", "WS", "UNEXPECTED_CHAR", 
		"DIGIT", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", 
		"N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
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


	public prefixLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "prefix.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\27\u011c\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\3\2\3\2\3\3\3\3\3\4\3\4"+
		"\3\5\3\5\6\5l\n\5\r\5\16\5m\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\7\3\7\6\7|\n\7\r\7\16\7}\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f"+
		"\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\7\21\u00a5\n\21\f\21\16\21"+
		"\u00a8\13\21\3\21\3\21\7\21\u00ac\n\21\f\21\16\21\u00af\13\21\3\22\3\22"+
		"\7\22\u00b3\n\22\f\22\16\22\u00b6\13\22\3\22\3\22\3\22\7\22\u00bb\n\22"+
		"\f\22\16\22\u00be\13\22\3\22\5\22\u00c1\n\22\3\23\3\23\3\23\3\23\7\23"+
		"\u00c7\n\23\f\23\16\23\u00ca\13\23\3\23\3\23\3\23\5\23\u00cf\n\23\3\23"+
		"\3\23\3\24\3\24\3\24\3\24\7\24\u00d7\n\24\f\24\16\24\u00da\13\24\3\24"+
		"\3\24\3\25\6\25\u00df\n\25\r\25\16\25\u00e0\3\25\3\25\3\26\3\26\3\27\3"+
		"\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3"+
		"\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3"+
		"(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\4m\u00c8"+
		"\2\62\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\2/\2\61\2\63\2\65\2\67\29\2;\2=\2"+
		"?\2A\2C\2E\2G\2I\2K\2M\2O\2Q\2S\2U\2W\2Y\2[\2]\2_\2a\2\3\2#\3\2\62;\6"+
		"\2\62;C\\aac|\5\2C\\aac|\3\2$$\3\2))\4\2\f\f\17\17\5\2\13\f\17\17\"\""+
		"\4\2CCcc\4\2DDdd\4\2EEee\4\2FFff\4\2GGgg\4\2HHhh\4\2IIii\4\2JJjj\4\2K"+
		"Kkk\4\2LLll\4\2MMmm\4\2NNnn\4\2OOoo\4\2PPpp\4\2QQqq\4\2RRrr\4\2SSss\4"+
		"\2TTtt\4\2UUuu\4\2VVvv\4\2WWww\4\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4\2\\\\"+
		"||\u010b\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\3c\3\2\2\2"+
		"\5e\3\2\2\2\7g\3\2\2\2\ti\3\2\2\2\13q\3\2\2\2\ry\3\2\2\2\17\177\3\2\2"+
		"\2\21\u0089\3\2\2\2\23\u0090\3\2\2\2\25\u0097\3\2\2\2\27\u0099\3\2\2\2"+
		"\31\u009b\3\2\2\2\33\u009d\3\2\2\2\35\u009f\3\2\2\2\37\u00a1\3\2\2\2!"+
		"\u00a6\3\2\2\2#\u00c0\3\2\2\2%\u00c2\3\2\2\2\'\u00d2\3\2\2\2)\u00de\3"+
		"\2\2\2+\u00e4\3\2\2\2-\u00e6\3\2\2\2/\u00e8\3\2\2\2\61\u00ea\3\2\2\2\63"+
		"\u00ec\3\2\2\2\65\u00ee\3\2\2\2\67\u00f0\3\2\2\29\u00f2\3\2\2\2;\u00f4"+
		"\3\2\2\2=\u00f6\3\2\2\2?\u00f8\3\2\2\2A\u00fa\3\2\2\2C\u00fc\3\2\2\2E"+
		"\u00fe\3\2\2\2G\u0100\3\2\2\2I\u0102\3\2\2\2K\u0104\3\2\2\2M\u0106\3\2"+
		"\2\2O\u0108\3\2\2\2Q\u010a\3\2\2\2S\u010c\3\2\2\2U\u010e\3\2\2\2W\u0110"+
		"\3\2\2\2Y\u0112\3\2\2\2[\u0114\3\2\2\2]\u0116\3\2\2\2_\u0118\3\2\2\2a"+
		"\u011a\3\2\2\2cd\7.\2\2d\4\3\2\2\2ef\7\60\2\2f\6\3\2\2\2gh\7%\2\2h\b\3"+
		"\2\2\2ik\7}\2\2jl\13\2\2\2kj\3\2\2\2lm\3\2\2\2mn\3\2\2\2mk\3\2\2\2no\3"+
		"\2\2\2op\7\177\2\2p\n\3\2\2\2qr\59\35\2rs\5K&\2st\5Q)\2tu\5\67\34\2uv"+
		"\5/\30\2vw\5\63\32\2wx\5=\37\2x\f\3\2\2\2y{\5\13\6\2z|\t\2\2\2{z\3\2\2"+
		"\2|}\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\16\3\2\2\2\177\u0080\5M\'\2\u0080\u0081"+
		"\5/\30\2\u0081\u0082\5Q)\2\u0082\u0083\5/\30\2\u0083\u0084\5G$\2\u0084"+
		"\u0085\5\67\34\2\u0085\u0086\5U+\2\u0086\u0087\5\67\34\2\u0087\u0088\5"+
		"Q)\2\u0088\20\3\2\2\2\u0089\u008a\5? \2\u008a\u008b\5G$\2\u008b\u008c"+
		"\5M\'\2\u008c\u008d\5K&\2\u008d\u008e\5Q)\2\u008e\u008f\5U+\2\u008f\22"+
		"\3\2\2\2\u0090\u0091\5\65\33\2\u0091\u0092\5\67\34\2\u0092\u0093\59\35"+
		"\2\u0093\u0094\5? \2\u0094\u0095\5I%\2\u0095\u0096\5\67\34\2\u0096\24"+
		"\3\2\2\2\u0097\u0098\7*\2\2\u0098\26\3\2\2\2\u0099\u009a\7+\2\2\u009a"+
		"\30\3\2\2\2\u009b\u009c\7]\2\2\u009c\32\3\2\2\2\u009d\u009e\7_\2\2\u009e"+
		"\34\3\2\2\2\u009f\u00a0\7}\2\2\u00a0\36\3\2\2\2\u00a1\u00a2\7\177\2\2"+
		"\u00a2 \3\2\2\2\u00a3\u00a5\t\3\2\2\u00a4\u00a3\3\2\2\2\u00a5\u00a8\3"+
		"\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a9\3\2\2\2\u00a8"+
		"\u00a6\3\2\2\2\u00a9\u00ad\t\4\2\2\u00aa\u00ac\t\3\2\2\u00ab\u00aa\3\2"+
		"\2\2\u00ac\u00af\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae"+
		"\"\3\2\2\2\u00af\u00ad\3\2\2\2\u00b0\u00b4\7$\2\2\u00b1\u00b3\n\5\2\2"+
		"\u00b2\u00b1\3\2\2\2\u00b3\u00b6\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5"+
		"\3\2\2\2\u00b5\u00b7\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b7\u00c1\7$\2\2\u00b8"+
		"\u00bc\7)\2\2\u00b9\u00bb\n\6\2\2\u00ba\u00b9\3\2\2\2\u00bb\u00be\3\2"+
		"\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00bf\3\2\2\2\u00be"+
		"\u00bc\3\2\2\2\u00bf\u00c1\7)\2\2\u00c0\u00b0\3\2\2\2\u00c0\u00b8\3\2"+
		"\2\2\u00c1$\3\2\2\2\u00c2\u00c3\7\61\2\2\u00c3\u00c4\7,\2\2\u00c4\u00c8"+
		"\3\2\2\2\u00c5\u00c7\13\2\2\2\u00c6\u00c5\3\2\2\2\u00c7\u00ca\3\2\2\2"+
		"\u00c8\u00c9\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c9\u00ce\3\2\2\2\u00ca\u00c8"+
		"\3\2\2\2\u00cb\u00cc\7,\2\2\u00cc\u00cf\7\61\2\2\u00cd\u00cf\7\2\2\3\u00ce"+
		"\u00cb\3\2\2\2\u00ce\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\b\23"+
		"\2\2\u00d1&\3\2\2\2\u00d2\u00d3\7/\2\2\u00d3\u00d4\7/\2\2\u00d4\u00d8"+
		"\3\2\2\2\u00d5\u00d7\n\7\2\2\u00d6\u00d5\3\2\2\2\u00d7\u00da\3\2\2\2\u00d8"+
		"\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00db\3\2\2\2\u00da\u00d8\3\2"+
		"\2\2\u00db\u00dc\b\24\2\2\u00dc(\3\2\2\2\u00dd\u00df\t\b\2\2\u00de\u00dd"+
		"\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1"+
		"\u00e2\3\2\2\2\u00e2\u00e3\b\25\3\2\u00e3*\3\2\2\2\u00e4\u00e5\13\2\2"+
		"\2\u00e5,\3\2\2\2\u00e6\u00e7\t\2\2\2\u00e7.\3\2\2\2\u00e8\u00e9\t\t\2"+
		"\2\u00e9\60\3\2\2\2\u00ea\u00eb\t\n\2\2\u00eb\62\3\2\2\2\u00ec\u00ed\t"+
		"\13\2\2\u00ed\64\3\2\2\2\u00ee\u00ef\t\f\2\2\u00ef\66\3\2\2\2\u00f0\u00f1"+
		"\t\r\2\2\u00f18\3\2\2\2\u00f2\u00f3\t\16\2\2\u00f3:\3\2\2\2\u00f4\u00f5"+
		"\t\17\2\2\u00f5<\3\2\2\2\u00f6\u00f7\t\20\2\2\u00f7>\3\2\2\2\u00f8\u00f9"+
		"\t\21\2\2\u00f9@\3\2\2\2\u00fa\u00fb\t\22\2\2\u00fbB\3\2\2\2\u00fc\u00fd"+
		"\t\23\2\2\u00fdD\3\2\2\2\u00fe\u00ff\t\24\2\2\u00ffF\3\2\2\2\u0100\u0101"+
		"\t\25\2\2\u0101H\3\2\2\2\u0102\u0103\t\26\2\2\u0103J\3\2\2\2\u0104\u0105"+
		"\t\27\2\2\u0105L\3\2\2\2\u0106\u0107\t\30\2\2\u0107N\3\2\2\2\u0108\u0109"+
		"\t\31\2\2\u0109P\3\2\2\2\u010a\u010b\t\32\2\2\u010bR\3\2\2\2\u010c\u010d"+
		"\t\33\2\2\u010dT\3\2\2\2\u010e\u010f\t\34\2\2\u010fV\3\2\2\2\u0110\u0111"+
		"\t\35\2\2\u0111X\3\2\2\2\u0112\u0113\t\36\2\2\u0113Z\3\2\2\2\u0114\u0115"+
		"\t\37\2\2\u0115\\\3\2\2\2\u0116\u0117\t \2\2\u0117^\3\2\2\2\u0118\u0119"+
		"\t!\2\2\u0119`\3\2\2\2\u011a\u011b\t\"\2\2\u011bb\3\2\2\2\16\2m}\u00a6"+
		"\u00ad\u00b4\u00bc\u00c0\u00c8\u00ce\u00d8\u00e0\4\2\3\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}