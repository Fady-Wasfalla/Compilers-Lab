// Generated from /home/fady/Documents/Semester 10/Compilers_Labs/Compilers-Lab/lab_5/src/task5.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class task5Lexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		X=1, Y=2, ZERO=3, ONE=4;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"X", "Y", "ZERO", "ONE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'0'", "'1'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "X", "Y", "ZERO", "ONE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	public task5Lexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "task5.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 0:
			X_action((RuleContext)_localctx, actionIndex);
			break;
		case 1:
			Y_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void X_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			System.out.print("00");
			break;
		}
	}
	private void Y_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			System.out.print("11");
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\6\u0092\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\3\2\3\2\3\2\3\2\3\2\7\2\21\n\2\f\2\16\2\24"+
		"\13\2\3\2\3\2\5\2\30\n\2\3\2\3\2\7\2\34\n\2\f\2\16\2\37\13\2\3\2\3\2\3"+
		"\2\7\2$\n\2\f\2\16\2\'\13\2\3\2\3\2\5\2+\n\2\3\2\3\2\7\2/\n\2\f\2\16\2"+
		"\62\13\2\3\2\3\2\3\2\3\2\7\28\n\2\f\2\16\2;\13\2\3\2\3\2\5\2?\n\2\3\2"+
		"\3\2\7\2C\n\2\f\2\16\2F\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\7\3O\n\3\f\3"+
		"\16\3R\13\3\3\3\3\3\5\3V\n\3\3\3\3\3\7\3Z\n\3\f\3\16\3]\13\3\3\3\3\3\3"+
		"\3\7\3b\n\3\f\3\16\3e\13\3\3\3\3\3\5\3i\n\3\3\3\3\3\7\3m\n\3\f\3\16\3"+
		"p\13\3\3\3\3\3\3\3\3\3\7\3v\n\3\f\3\16\3y\13\3\3\3\3\3\5\3}\n\3\3\3\3"+
		"\3\7\3\u0081\n\3\f\3\16\3\u0084\13\3\3\3\3\3\7\3\u0088\n\3\f\3\16\3\u008b"+
		"\13\3\3\3\3\3\3\4\3\4\3\5\3\5\2\2\6\3\3\5\4\7\5\t\6\3\2\2\2\u00a6\2\3"+
		"\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\3\60\3\2\2\2\5n\3\2\2\2\7"+
		"\u008e\3\2\2\2\t\u0090\3\2\2\2\13/\5\7\4\2\f\35\5\t\5\2\r\30\5\7\4\2\16"+
		"\22\5\t\5\2\17\21\5\t\5\2\20\17\3\2\2\2\21\24\3\2\2\2\22\20\3\2\2\2\22"+
		"\23\3\2\2\2\23\25\3\2\2\2\24\22\3\2\2\2\25\26\5\7\4\2\26\30\3\2\2\2\27"+
		"\r\3\2\2\2\27\16\3\2\2\2\30\31\3\2\2\2\31\32\5\7\4\2\32\34\3\2\2\2\33"+
		"\27\3\2\2\2\34\37\3\2\2\2\35\33\3\2\2\2\35\36\3\2\2\2\36*\3\2\2\2\37\35"+
		"\3\2\2\2 +\5\7\4\2!%\5\t\5\2\"$\5\t\5\2#\"\3\2\2\2$\'\3\2\2\2%#\3\2\2"+
		"\2%&\3\2\2\2&(\3\2\2\2\'%\3\2\2\2()\5\7\4\2)+\3\2\2\2* \3\2\2\2*!\3\2"+
		"\2\2+,\3\2\2\2,-\5\t\5\2-/\3\2\2\2.\13\3\2\2\2.\f\3\2\2\2/\62\3\2\2\2"+
		"\60.\3\2\2\2\60\61\3\2\2\2\61\63\3\2\2\2\62\60\3\2\2\2\63D\5\t\5\2\64"+
		"?\5\7\4\2\659\5\t\5\2\668\5\t\5\2\67\66\3\2\2\28;\3\2\2\29\67\3\2\2\2"+
		"9:\3\2\2\2:<\3\2\2\2;9\3\2\2\2<=\5\7\4\2=?\3\2\2\2>\64\3\2\2\2>\65\3\2"+
		"\2\2?@\3\2\2\2@A\5\7\4\2AC\3\2\2\2B>\3\2\2\2CF\3\2\2\2DB\3\2\2\2DE\3\2"+
		"\2\2EG\3\2\2\2FD\3\2\2\2GH\b\2\2\2H\4\3\2\2\2Im\5\7\4\2J[\5\t\5\2KV\5"+
		"\7\4\2LP\5\t\5\2MO\5\t\5\2NM\3\2\2\2OR\3\2\2\2PN\3\2\2\2PQ\3\2\2\2QS\3"+
		"\2\2\2RP\3\2\2\2ST\5\7\4\2TV\3\2\2\2UK\3\2\2\2UL\3\2\2\2VW\3\2\2\2WX\5"+
		"\7\4\2XZ\3\2\2\2YU\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\h\3\2\2\2]"+
		"[\3\2\2\2^i\5\7\4\2_c\5\t\5\2`b\5\t\5\2a`\3\2\2\2be\3\2\2\2ca\3\2\2\2"+
		"cd\3\2\2\2df\3\2\2\2ec\3\2\2\2fg\5\7\4\2gi\3\2\2\2h^\3\2\2\2h_\3\2\2\2"+
		"ij\3\2\2\2jk\5\t\5\2km\3\2\2\2lI\3\2\2\2lJ\3\2\2\2mp\3\2\2\2nl\3\2\2\2"+
		"no\3\2\2\2oq\3\2\2\2pn\3\2\2\2q\u0082\5\t\5\2r}\5\7\4\2sw\5\t\5\2tv\5"+
		"\t\5\2ut\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3\2\2\2xz\3\2\2\2yw\3\2\2\2z{\5"+
		"\7\4\2{}\3\2\2\2|r\3\2\2\2|s\3\2\2\2}~\3\2\2\2~\177\5\7\4\2\177\u0081"+
		"\3\2\2\2\u0080|\3\2\2\2\u0081\u0084\3\2\2\2\u0082\u0080\3\2\2\2\u0082"+
		"\u0083\3\2\2\2\u0083\u0085\3\2\2\2\u0084\u0082\3\2\2\2\u0085\u0089\5\t"+
		"\5\2\u0086\u0088\5\t\5\2\u0087\u0086\3\2\2\2\u0088\u008b\3\2\2\2\u0089"+
		"\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008c\3\2\2\2\u008b\u0089\3\2"+
		"\2\2\u008c\u008d\b\3\3\2\u008d\6\3\2\2\2\u008e\u008f\7\62\2\2\u008f\b"+
		"\3\2\2\2\u0090\u0091\7\63\2\2\u0091\n\3\2\2\2\30\2\22\27\35%*.\609>DP"+
		"U[chlnw|\u0082\u0089\4\3\2\2\3\3\3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}