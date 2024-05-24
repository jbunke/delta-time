// Generated from C:/Users/Jordan Bunke/Documents/Java/2022/delta-time/script/antlr/ScriptParser.g4 by ANTLR 4.13.1
package com.jordanbunke.delta_time.scripting;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ScriptParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, LINE_COMMENT=2, MULTILINE_COMMENT=3, LPAREN=4, RPAREN=5, LBRACKET=6, 
		RBRACKET=7, LCURLY=8, RCURLY=9, SEMICOLON=10, COLON=11, COMMA=12, PERIOD=13, 
		PIPE=14, QUESTION=15, ASSIGN=16, INCREMENT=17, DECREMENT=18, ADD_ASSIGN=19, 
		SUB_ASSIGN=20, MUL_ASSIGN=21, DIV_ASSIGN=22, MOD_ASSIGN=23, AND_ASSIGN=24, 
		OR_ASSIGN=25, ARROW=26, DEF=27, EXTENSION=28, EQUAL=29, NOT_EQUAL=30, 
		GT=31, LT=32, GEQ=33, LEQ=34, RAISE=35, PLUS=36, MINUS=37, TIMES=38, DIVIDE=39, 
		MOD=40, AND=41, OR=42, NOT=43, SIZE=44, IN=45, FINAL=46, BOOL=47, FLOAT=48, 
		INT=49, CHAR=50, COLOR=51, IMAGE=52, STRING=53, RETURN=54, DO=55, WHILE=56, 
		FOR=57, IF=58, ELSE=59, TRUE=60, FALSE=61, NEW=62, FLOAT_LIT=63, DEC_LIT=64, 
		HEX_LIT=65, COL_LIT=66, CHAR_QUOTE=67, STR_QUOTE=68, STRING_LIT=69, CHAR_LIT=70, 
		ESC_CHAR=71, IDENTIFIER=72, SUB_IDENT=73;
	public static final int
		RULE_head_rule = 0, RULE_helper = 1, RULE_func_body = 2, RULE_signature = 3, 
		RULE_param_list = 4, RULE_declaration = 5, RULE_type = 6, RULE_func_type = 7, 
		RULE_param_types = 8, RULE_body = 9, RULE_stat = 10, RULE_return_stat = 11, 
		RULE_loop_stat = 12, RULE_iteration_def = 13, RULE_while_def = 14, RULE_for_def = 15, 
		RULE_if_stat = 16, RULE_if_def = 17, RULE_expr = 18, RULE_k_v_pairs = 19, 
		RULE_k_v_pair = 20, RULE_args = 21, RULE_assignment = 22, RULE_var_init = 23, 
		RULE_var_def = 24, RULE_assignable = 25, RULE_ident = 26, RULE_subident = 27, 
		RULE_namespace = 28, RULE_literal = 29, RULE_int_lit = 30, RULE_bool_lit = 31;
	private static String[] makeRuleNames() {
		return new String[] {
			"head_rule", "helper", "func_body", "signature", "param_list", "declaration", 
			"type", "func_type", "param_types", "body", "stat", "return_stat", "loop_stat", 
			"iteration_def", "while_def", "for_def", "if_stat", "if_def", "expr", 
			"k_v_pairs", "k_v_pair", "args", "assignment", "var_init", "var_def", 
			"assignable", "ident", "subident", "namespace", "literal", "int_lit", 
			"bool_lit"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "'('", "')'", "'['", "']'", "'{'", "'}'", "';'", 
			"':'", "','", "'.'", "'|'", "'?'", "'='", "'++'", "'--'", "'+='", "'-='", 
			"'*='", "'/='", "'%='", "'&='", "'|='", "'->'", "'::'", "'$'", "'=='", 
			"'!='", "'>'", "'<'", "'>='", "'<='", "'^'", "'+'", "'-'", "'*'", "'/'", 
			"'%'", "'&&'", "'||'", "'!'", "'#|'", "'in'", null, "'bool'", "'float'", 
			"'int'", "'char'", "'color'", "'image'", "'string'", "'return'", "'do'", 
			"'while'", "'for'", "'if'", "'else'", "'true'", "'false'", "'new'", null, 
			null, null, null, "'''", "'\"'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WS", "LINE_COMMENT", "MULTILINE_COMMENT", "LPAREN", "RPAREN", 
			"LBRACKET", "RBRACKET", "LCURLY", "RCURLY", "SEMICOLON", "COLON", "COMMA", 
			"PERIOD", "PIPE", "QUESTION", "ASSIGN", "INCREMENT", "DECREMENT", "ADD_ASSIGN", 
			"SUB_ASSIGN", "MUL_ASSIGN", "DIV_ASSIGN", "MOD_ASSIGN", "AND_ASSIGN", 
			"OR_ASSIGN", "ARROW", "DEF", "EXTENSION", "EQUAL", "NOT_EQUAL", "GT", 
			"LT", "GEQ", "LEQ", "RAISE", "PLUS", "MINUS", "TIMES", "DIVIDE", "MOD", 
			"AND", "OR", "NOT", "SIZE", "IN", "FINAL", "BOOL", "FLOAT", "INT", "CHAR", 
			"COLOR", "IMAGE", "STRING", "RETURN", "DO", "WHILE", "FOR", "IF", "ELSE", 
			"TRUE", "FALSE", "NEW", "FLOAT_LIT", "DEC_LIT", "HEX_LIT", "COL_LIT", 
			"CHAR_QUOTE", "STR_QUOTE", "STRING_LIT", "CHAR_LIT", "ESC_CHAR", "IDENTIFIER", 
			"SUB_IDENT"
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

	@Override
	public String getGrammarFileName() { return "ScriptParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ScriptParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Head_ruleContext extends ParserRuleContext {
		public SignatureContext signature() {
			return getRuleContext(SignatureContext.class,0);
		}
		public Func_bodyContext func_body() {
			return getRuleContext(Func_bodyContext.class,0);
		}
		public List<HelperContext> helper() {
			return getRuleContexts(HelperContext.class);
		}
		public HelperContext helper(int i) {
			return getRuleContext(HelperContext.class,i);
		}
		public Head_ruleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_head_rule; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitHead_rule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Head_ruleContext head_rule() throws RecognitionException {
		Head_ruleContext _localctx = new Head_ruleContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_head_rule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			signature();
			setState(65);
			func_body();
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IDENTIFIER) {
				{
				{
				setState(66);
				helper();
				}
				}
				setState(71);
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

	@SuppressWarnings("CheckReturnValue")
	public static class HelperContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public SignatureContext signature() {
			return getRuleContext(SignatureContext.class,0);
		}
		public Func_bodyContext func_body() {
			return getRuleContext(Func_bodyContext.class,0);
		}
		public HelperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_helper; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitHelper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HelperContext helper() throws RecognitionException {
		HelperContext _localctx = new HelperContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_helper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			ident();
			setState(73);
			signature();
			setState(74);
			func_body();
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

	@SuppressWarnings("CheckReturnValue")
	public static class Func_bodyContext extends ParserRuleContext {
		public Func_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_body; }
	 
		public Func_bodyContext() { }
		public void copyFrom(Func_bodyContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FunctionalFuncBodyContext extends Func_bodyContext {
		public TerminalNode ARROW() { return getToken(ScriptParser.ARROW, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public FunctionalFuncBodyContext(Func_bodyContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitFunctionalFuncBody(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StandardFuncBodyContext extends Func_bodyContext {
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public StandardFuncBodyContext(Func_bodyContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitStandardFuncBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_bodyContext func_body() throws RecognitionException {
		Func_bodyContext _localctx = new Func_bodyContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_func_body);
		try {
			setState(79);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
			case LBRACKET:
			case LCURLY:
			case DEF:
			case EXTENSION:
			case LT:
			case MINUS:
			case NOT:
			case SIZE:
			case FINAL:
			case BOOL:
			case FLOAT:
			case INT:
			case CHAR:
			case COLOR:
			case IMAGE:
			case STRING:
			case RETURN:
			case DO:
			case WHILE:
			case FOR:
			case IF:
			case TRUE:
			case FALSE:
			case NEW:
			case FLOAT_LIT:
			case DEC_LIT:
			case HEX_LIT:
			case COL_LIT:
			case STRING_LIT:
			case CHAR_LIT:
			case IDENTIFIER:
				_localctx = new StandardFuncBodyContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(76);
				body();
				}
				break;
			case ARROW:
				_localctx = new FunctionalFuncBodyContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(77);
				match(ARROW);
				setState(78);
				expr(0);
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

	@SuppressWarnings("CheckReturnValue")
	public static class SignatureContext extends ParserRuleContext {
		public SignatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_signature; }
	 
		public SignatureContext() { }
		public void copyFrom(SignatureContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VoidReturnSignatureContext extends SignatureContext {
		public TerminalNode LPAREN() { return getToken(ScriptParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ScriptParser.RPAREN, 0); }
		public Param_listContext param_list() {
			return getRuleContext(Param_listContext.class,0);
		}
		public VoidReturnSignatureContext(SignatureContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitVoidReturnSignature(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TypeReturnSignatureContext extends SignatureContext {
		public TerminalNode LPAREN() { return getToken(ScriptParser.LPAREN, 0); }
		public TerminalNode ARROW() { return getToken(ScriptParser.ARROW, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ScriptParser.RPAREN, 0); }
		public Param_listContext param_list() {
			return getRuleContext(Param_listContext.class,0);
		}
		public TypeReturnSignatureContext(SignatureContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitTypeReturnSignature(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SignatureContext signature() throws RecognitionException {
		SignatureContext _localctx = new SignatureContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_signature);
		int _la;
		try {
			setState(94);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				_localctx = new VoidReturnSignatureContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(81);
				match(LPAREN);
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 17944029765304592L) != 0) || _la==IDENTIFIER) {
					{
					setState(82);
					param_list();
					}
				}

				setState(85);
				match(RPAREN);
				}
				break;
			case 2:
				_localctx = new TypeReturnSignatureContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(86);
				match(LPAREN);
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 17944029765304592L) != 0) || _la==IDENTIFIER) {
					{
					setState(87);
					param_list();
					}
				}

				setState(90);
				match(ARROW);
				setState(91);
				type(0);
				setState(92);
				match(RPAREN);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Param_listContext extends ParserRuleContext {
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ScriptParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ScriptParser.COMMA, i);
		}
		public Param_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitParam_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Param_listContext param_list() throws RecognitionException {
		Param_listContext _localctx = new Param_listContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_param_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			declaration();
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(97);
				match(COMMA);
				setState(98);
				declaration();
				}
				}
				setState(103);
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

	@SuppressWarnings("CheckReturnValue")
	public static class DeclarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode FINAL() { return getToken(ScriptParser.FINAL, 0); }
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FINAL) {
				{
				setState(104);
				match(FINAL);
				}
			}

			setState(107);
			type(0);
			setState(108);
			ident();
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

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	 
		public TypeContext() { }
		public void copyFrom(TypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BoolTypeContext extends TypeContext {
		public TerminalNode BOOL() { return getToken(ScriptParser.BOOL, 0); }
		public BoolTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitBoolType(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringTypeContext extends TypeContext {
		public TerminalNode STRING() { return getToken(ScriptParser.STRING, 0); }
		public StringTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitStringType(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FunctionTypeContext extends TypeContext {
		public TerminalNode LPAREN() { return getToken(ScriptParser.LPAREN, 0); }
		public Func_typeContext func_type() {
			return getRuleContext(Func_typeContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ScriptParser.RPAREN, 0); }
		public FunctionTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitFunctionType(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MapTypeContext extends TypeContext {
		public TypeContext key;
		public TypeContext val;
		public TerminalNode LCURLY() { return getToken(ScriptParser.LCURLY, 0); }
		public TerminalNode COLON() { return getToken(ScriptParser.COLON, 0); }
		public TerminalNode RCURLY() { return getToken(ScriptParser.RCURLY, 0); }
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public MapTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitMapType(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IntTypeContext extends TypeContext {
		public TerminalNode INT() { return getToken(ScriptParser.INT, 0); }
		public IntTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitIntType(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FloatTypeContext extends TypeContext {
		public TerminalNode FLOAT() { return getToken(ScriptParser.FLOAT, 0); }
		public FloatTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitFloatType(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ImageTypeContext extends TypeContext {
		public TerminalNode IMAGE() { return getToken(ScriptParser.IMAGE, 0); }
		public ImageTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitImageType(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArrayTypeContext extends TypeContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode LBRACKET() { return getToken(ScriptParser.LBRACKET, 0); }
		public TerminalNode RBRACKET() { return getToken(ScriptParser.RBRACKET, 0); }
		public ArrayTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitArrayType(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SetTypeContext extends TypeContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode LCURLY() { return getToken(ScriptParser.LCURLY, 0); }
		public TerminalNode RCURLY() { return getToken(ScriptParser.RCURLY, 0); }
		public SetTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitSetType(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExtensionTypeContext extends TypeContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public ExtensionTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitExtensionType(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ListTypeContext extends TypeContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode LT() { return getToken(ScriptParser.LT, 0); }
		public TerminalNode GT() { return getToken(ScriptParser.GT, 0); }
		public ListTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitListType(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ColorTypeContext extends TypeContext {
		public TerminalNode COLOR() { return getToken(ScriptParser.COLOR, 0); }
		public ColorTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitColorType(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CharTypeContext extends TypeContext {
		public TerminalNode CHAR() { return getToken(ScriptParser.CHAR, 0); }
		public CharTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitCharType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		return type(0);
	}

	private TypeContext type(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeContext _localctx = new TypeContext(_ctx, _parentState);
		TypeContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_type, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL:
				{
				_localctx = new BoolTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(111);
				match(BOOL);
				}
				break;
			case INT:
				{
				_localctx = new IntTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(112);
				match(INT);
				}
				break;
			case FLOAT:
				{
				_localctx = new FloatTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(113);
				match(FLOAT);
				}
				break;
			case CHAR:
				{
				_localctx = new CharTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(114);
				match(CHAR);
				}
				break;
			case STRING:
				{
				_localctx = new StringTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(115);
				match(STRING);
				}
				break;
			case IMAGE:
				{
				_localctx = new ImageTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(116);
				match(IMAGE);
				}
				break;
			case COLOR:
				{
				_localctx = new ColorTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(117);
				match(COLOR);
				}
				break;
			case LCURLY:
				{
				_localctx = new MapTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(118);
				match(LCURLY);
				setState(119);
				((MapTypeContext)_localctx).key = type(0);
				setState(120);
				match(COLON);
				setState(121);
				((MapTypeContext)_localctx).val = type(0);
				setState(122);
				match(RCURLY);
				}
				break;
			case LPAREN:
				{
				_localctx = new FunctionTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(124);
				match(LPAREN);
				setState(125);
				func_type();
				setState(126);
				match(RPAREN);
				}
				break;
			case IDENTIFIER:
				{
				_localctx = new ExtensionTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(128);
				ident();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(142);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(140);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new ArrayTypeContext(new TypeContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_type);
						setState(131);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(132);
						match(LBRACKET);
						setState(133);
						match(RBRACKET);
						}
						break;
					case 2:
						{
						_localctx = new ListTypeContext(new TypeContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_type);
						setState(134);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(135);
						match(LT);
						setState(136);
						match(GT);
						}
						break;
					case 3:
						{
						_localctx = new SetTypeContext(new TypeContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_type);
						setState(137);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(138);
						match(LCURLY);
						setState(139);
						match(RCURLY);
						}
						break;
					}
					} 
				}
				setState(144);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Func_typeContext extends ParserRuleContext {
		public TypeContext ret;
		public Param_typesContext param_types() {
			return getRuleContext(Param_typesContext.class,0);
		}
		public TerminalNode ARROW() { return getToken(ScriptParser.ARROW, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Func_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitFunc_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_typeContext func_type() throws RecognitionException {
		Func_typeContext _localctx = new Func_typeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_func_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			param_types();
			setState(146);
			match(ARROW);
			setState(147);
			((Func_typeContext)_localctx).ret = type(0);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Param_typesContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ScriptParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ScriptParser.COMMA, i);
		}
		public Param_typesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param_types; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitParam_types(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Param_typesContext param_types() throws RecognitionException {
		Param_typesContext _localctx = new Param_typesContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_param_types);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 17873661021126928L) != 0) || _la==IDENTIFIER) {
				{
				setState(149);
				type(0);
				setState(154);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(150);
					match(COMMA);
					setState(151);
					type(0);
					}
					}
					setState(156);
					_errHandler.sync(this);
					_la = _input.LA(1);
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

	@SuppressWarnings("CheckReturnValue")
	public static class BodyContext extends ParserRuleContext {
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
	 
		public BodyContext() { }
		public void copyFrom(BodyContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SingleStatBodyContext extends BodyContext {
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public SingleStatBodyContext(BodyContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitSingleStatBody(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ComplexBodyContext extends BodyContext {
		public TerminalNode LCURLY() { return getToken(ScriptParser.LCURLY, 0); }
		public TerminalNode RCURLY() { return getToken(ScriptParser.RCURLY, 0); }
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public ComplexBodyContext(BodyContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitComplexBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_body);
		int _la;
		try {
			setState(168);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				_localctx = new SingleStatBodyContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(159);
				stat();
				}
				break;
			case 2:
				_localctx = new ComplexBodyContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(160);
				match(LCURLY);
				setState(164);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -576504590631960240L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 359L) != 0)) {
					{
					{
					setState(161);
					stat();
					}
					}
					setState(166);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(167);
				match(RCURLY);
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

	@SuppressWarnings("CheckReturnValue")
	public static class StatContext extends ParserRuleContext {
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
	 
		public StatContext() { }
		public void copyFrom(StatContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IfStatementContext extends StatContext {
		public If_statContext if_stat() {
			return getRuleContext(If_statContext.class,0);
		}
		public IfStatementContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentStatementContext extends StatContext {
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(ScriptParser.SEMICOLON, 0); }
		public AssignmentStatementContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitAssignmentStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExtFuncCallStatementContext extends StatContext {
		public NamespaceContext namespace() {
			return getRuleContext(NamespaceContext.class,0);
		}
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(ScriptParser.SEMICOLON, 0); }
		public ExtFuncCallStatementContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitExtFuncCallStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ReturnStatementContext extends StatContext {
		public Return_statContext return_stat() {
			return getRuleContext(Return_statContext.class,0);
		}
		public ReturnStatementContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ScopedFuncCallStatementContext extends StatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public SubidentContext subident() {
			return getRuleContext(SubidentContext.class,0);
		}
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(ScriptParser.SEMICOLON, 0); }
		public ScopedFuncCallStatementContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitScopedFuncCallStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FunctionCallStatementContext extends StatContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(ScriptParser.SEMICOLON, 0); }
		public FunctionCallStatementContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitFunctionCallStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LoopStatementContext extends StatContext {
		public Loop_statContext loop_stat() {
			return getRuleContext(Loop_statContext.class,0);
		}
		public LoopStatementContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitLoopStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VarDefStatementContext extends StatContext {
		public Var_defContext var_def() {
			return getRuleContext(Var_defContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(ScriptParser.SEMICOLON, 0); }
		public VarDefStatementContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitVarDefStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_stat);
		try {
			setState(192);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				_localctx = new LoopStatementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(170);
				loop_stat();
				}
				break;
			case 2:
				_localctx = new IfStatementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(171);
				if_stat();
				}
				break;
			case 3:
				_localctx = new VarDefStatementContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(172);
				var_def();
				setState(173);
				match(SEMICOLON);
				}
				break;
			case 4:
				_localctx = new AssignmentStatementContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(175);
				assignment();
				setState(176);
				match(SEMICOLON);
				}
				break;
			case 5:
				_localctx = new ReturnStatementContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(178);
				return_stat();
				}
				break;
			case 6:
				_localctx = new ScopedFuncCallStatementContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(179);
				expr(0);
				setState(180);
				subident();
				setState(181);
				args();
				setState(182);
				match(SEMICOLON);
				}
				break;
			case 7:
				_localctx = new FunctionCallStatementContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(184);
				ident();
				setState(185);
				args();
				setState(186);
				match(SEMICOLON);
				}
				break;
			case 8:
				_localctx = new ExtFuncCallStatementContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(188);
				namespace();
				setState(189);
				args();
				setState(190);
				match(SEMICOLON);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Return_statContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(ScriptParser.RETURN, 0); }
		public TerminalNode SEMICOLON() { return getToken(ScriptParser.SEMICOLON, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Return_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_stat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitReturn_stat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Return_statContext return_stat() throws RecognitionException {
		Return_statContext _localctx = new Return_statContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_return_stat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			match(RETURN);
			setState(196);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -1152894974191206064L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 359L) != 0)) {
				{
				setState(195);
				expr(0);
				}
			}

			setState(198);
			match(SEMICOLON);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Loop_statContext extends ParserRuleContext {
		public Loop_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loop_stat; }
	 
		public Loop_statContext() { }
		public void copyFrom(Loop_statContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ForLoopContext extends Loop_statContext {
		public For_defContext for_def() {
			return getRuleContext(For_defContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public ForLoopContext(Loop_statContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitForLoop(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IteratorLoopContext extends Loop_statContext {
		public Iteration_defContext iteration_def() {
			return getRuleContext(Iteration_defContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public IteratorLoopContext(Loop_statContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitIteratorLoop(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DoWhileLoopContext extends Loop_statContext {
		public TerminalNode DO() { return getToken(ScriptParser.DO, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public While_defContext while_def() {
			return getRuleContext(While_defContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(ScriptParser.SEMICOLON, 0); }
		public DoWhileLoopContext(Loop_statContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitDoWhileLoop(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WhileLoopContext extends Loop_statContext {
		public While_defContext while_def() {
			return getRuleContext(While_defContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public WhileLoopContext(Loop_statContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitWhileLoop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Loop_statContext loop_stat() throws RecognitionException {
		Loop_statContext _localctx = new Loop_statContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_loop_stat);
		try {
			setState(214);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				_localctx = new WhileLoopContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(200);
				while_def();
				setState(201);
				body();
				}
				break;
			case 2:
				_localctx = new IteratorLoopContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(203);
				iteration_def();
				setState(204);
				body();
				}
				break;
			case 3:
				_localctx = new ForLoopContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(206);
				for_def();
				setState(207);
				body();
				}
				break;
			case 4:
				_localctx = new DoWhileLoopContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(209);
				match(DO);
				setState(210);
				body();
				setState(211);
				while_def();
				setState(212);
				match(SEMICOLON);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Iteration_defContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(ScriptParser.FOR, 0); }
		public TerminalNode LPAREN() { return getToken(ScriptParser.LPAREN, 0); }
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public TerminalNode IN() { return getToken(ScriptParser.IN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ScriptParser.RPAREN, 0); }
		public Iteration_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iteration_def; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitIteration_def(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Iteration_defContext iteration_def() throws RecognitionException {
		Iteration_defContext _localctx = new Iteration_defContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_iteration_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			match(FOR);
			setState(217);
			match(LPAREN);
			setState(218);
			declaration();
			setState(219);
			match(IN);
			setState(220);
			expr(0);
			setState(221);
			match(RPAREN);
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

	@SuppressWarnings("CheckReturnValue")
	public static class While_defContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(ScriptParser.WHILE, 0); }
		public TerminalNode LPAREN() { return getToken(ScriptParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ScriptParser.RPAREN, 0); }
		public While_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_def; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitWhile_def(this);
			else return visitor.visitChildren(this);
		}
	}

	public final While_defContext while_def() throws RecognitionException {
		While_defContext _localctx = new While_defContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_while_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			match(WHILE);
			setState(224);
			match(LPAREN);
			setState(225);
			expr(0);
			setState(226);
			match(RPAREN);
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

	@SuppressWarnings("CheckReturnValue")
	public static class For_defContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(ScriptParser.FOR, 0); }
		public TerminalNode LPAREN() { return getToken(ScriptParser.LPAREN, 0); }
		public Var_initContext var_init() {
			return getRuleContext(Var_initContext.class,0);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(ScriptParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(ScriptParser.SEMICOLON, i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ScriptParser.RPAREN, 0); }
		public For_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_def; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitFor_def(this);
			else return visitor.visitChildren(this);
		}
	}

	public final For_defContext for_def() throws RecognitionException {
		For_defContext _localctx = new For_defContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_for_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			match(FOR);
			setState(229);
			match(LPAREN);
			setState(230);
			var_init();
			setState(231);
			match(SEMICOLON);
			setState(232);
			expr(0);
			setState(233);
			match(SEMICOLON);
			setState(234);
			assignment();
			setState(235);
			match(RPAREN);
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

	@SuppressWarnings("CheckReturnValue")
	public static class If_statContext extends ParserRuleContext {
		public BodyContext elseBody;
		public List<If_defContext> if_def() {
			return getRuleContexts(If_defContext.class);
		}
		public If_defContext if_def(int i) {
			return getRuleContext(If_defContext.class,i);
		}
		public List<TerminalNode> ELSE() { return getTokens(ScriptParser.ELSE); }
		public TerminalNode ELSE(int i) {
			return getToken(ScriptParser.ELSE, i);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public If_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_stat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitIf_stat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_statContext if_stat() throws RecognitionException {
		If_statContext _localctx = new If_statContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_if_stat);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			if_def();
			setState(242);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(238);
					match(ELSE);
					setState(239);
					if_def();
					}
					} 
				}
				setState(244);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			setState(247);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(245);
				match(ELSE);
				setState(246);
				((If_statContext)_localctx).elseBody = body();
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

	@SuppressWarnings("CheckReturnValue")
	public static class If_defContext extends ParserRuleContext {
		public ExprContext cond;
		public TerminalNode IF() { return getToken(ScriptParser.IF, 0); }
		public TerminalNode LPAREN() { return getToken(ScriptParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ScriptParser.RPAREN, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public If_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_def; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitIf_def(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_defContext if_def() throws RecognitionException {
		If_defContext _localctx = new If_defContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_if_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			match(IF);
			setState(250);
			match(LPAREN);
			setState(251);
			((If_defContext)_localctx).cond = expr(0);
			setState(252);
			match(RPAREN);
			setState(253);
			body();
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NewSetExpressionContext extends ExprContext {
		public TerminalNode NEW() { return getToken(ScriptParser.NEW, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode LCURLY() { return getToken(ScriptParser.LCURLY, 0); }
		public TerminalNode RCURLY() { return getToken(ScriptParser.RCURLY, 0); }
		public NewSetExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitNewSetExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LogicBinExpressionContext extends ExprContext {
		public ExprContext a;
		public Token op;
		public ExprContext b;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OR() { return getToken(ScriptParser.OR, 0); }
		public TerminalNode AND() { return getToken(ScriptParser.AND, 0); }
		public LogicBinExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitLogicBinExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TernaryExpressionContext extends ExprContext {
		public ExprContext cond;
		public ExprContext if_;
		public ExprContext else_;
		public TerminalNode QUESTION() { return getToken(ScriptParser.QUESTION, 0); }
		public TerminalNode COLON() { return getToken(ScriptParser.COLON, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TernaryExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitTernaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExplicitArrayExpressionContext extends ExprContext {
		public TerminalNode LBRACKET() { return getToken(ScriptParser.LBRACKET, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode RBRACKET() { return getToken(ScriptParser.RBRACKET, 0); }
		public List<TerminalNode> COMMA() { return getTokens(ScriptParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ScriptParser.COMMA, i);
		}
		public ExplicitArrayExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitExplicitArrayExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MultBinExpressionContext extends ExprContext {
		public ExprContext a;
		public Token op;
		public ExprContext b;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode TIMES() { return getToken(ScriptParser.TIMES, 0); }
		public TerminalNode DIVIDE() { return getToken(ScriptParser.DIVIDE, 0); }
		public TerminalNode MOD() { return getToken(ScriptParser.MOD, 0); }
		public MultBinExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitMultBinExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LiteralExpressionContext extends ExprContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public LiteralExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitLiteralExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnaryExpressionContext extends ExprContext {
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(ScriptParser.MINUS, 0); }
		public TerminalNode NOT() { return getToken(ScriptParser.NOT, 0); }
		public TerminalNode SIZE() { return getToken(ScriptParser.SIZE, 0); }
		public UnaryExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitUnaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NewListExpressionContext extends ExprContext {
		public TerminalNode NEW() { return getToken(ScriptParser.NEW, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode LT() { return getToken(ScriptParser.LT, 0); }
		public TerminalNode GT() { return getToken(ScriptParser.GT, 0); }
		public NewListExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitNewListExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PowerBinExpressionContext extends ExprContext {
		public ExprContext a;
		public ExprContext b;
		public TerminalNode RAISE() { return getToken(ScriptParser.RAISE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public PowerBinExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitPowerBinExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FunctionCallExpressionContext extends ExprContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public FunctionCallExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitFunctionCallExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExplicitListExpressionContext extends ExprContext {
		public TerminalNode LT() { return getToken(ScriptParser.LT, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode GT() { return getToken(ScriptParser.GT, 0); }
		public List<TerminalNode> COMMA() { return getTokens(ScriptParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ScriptParser.COMMA, i);
		}
		public ExplicitListExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitExplicitListExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ComparisonBinExpressionContext extends ExprContext {
		public ExprContext a;
		public Token op;
		public ExprContext b;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode EQUAL() { return getToken(ScriptParser.EQUAL, 0); }
		public TerminalNode NOT_EQUAL() { return getToken(ScriptParser.NOT_EQUAL, 0); }
		public TerminalNode GT() { return getToken(ScriptParser.GT, 0); }
		public TerminalNode LT() { return getToken(ScriptParser.LT, 0); }
		public TerminalNode GEQ() { return getToken(ScriptParser.GEQ, 0); }
		public TerminalNode LEQ() { return getToken(ScriptParser.LEQ, 0); }
		public ComparisonBinExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitComparisonBinExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ScopedFuncCallExpressionContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public SubidentContext subident() {
			return getRuleContext(SubidentContext.class,0);
		}
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public ScopedFuncCallExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitScopedFuncCallExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NestedExpressionContext extends ExprContext {
		public TerminalNode LPAREN() { return getToken(ScriptParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ScriptParser.RPAREN, 0); }
		public NestedExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitNestedExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExplicitMapExpressionContext extends ExprContext {
		public TerminalNode LCURLY() { return getToken(ScriptParser.LCURLY, 0); }
		public K_v_pairsContext k_v_pairs() {
			return getRuleContext(K_v_pairsContext.class,0);
		}
		public TerminalNode RCURLY() { return getToken(ScriptParser.RCURLY, 0); }
		public ExplicitMapExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitExplicitMapExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class HOFuncExpressionContext extends ExprContext {
		public TerminalNode DEF() { return getToken(ScriptParser.DEF, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public HOFuncExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitHOFuncExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArithmeticBinExpressionContext extends ExprContext {
		public ExprContext a;
		public Token op;
		public ExprContext b;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(ScriptParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(ScriptParser.MINUS, 0); }
		public ArithmeticBinExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitArithmeticBinExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignableExpressionContext extends ExprContext {
		public AssignableContext assignable() {
			return getRuleContext(AssignableContext.class,0);
		}
		public AssignableExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitAssignableExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExplicitSetExpressionContext extends ExprContext {
		public TerminalNode LCURLY() { return getToken(ScriptParser.LCURLY, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode RCURLY() { return getToken(ScriptParser.RCURLY, 0); }
		public List<TerminalNode> COMMA() { return getTokens(ScriptParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ScriptParser.COMMA, i);
		}
		public ExplicitSetExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitExplicitSetExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PropertyExpressionContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public SubidentContext subident() {
			return getRuleContext(SubidentContext.class,0);
		}
		public PropertyExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitPropertyExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CastExpressionContext extends ExprContext {
		public TerminalNode LPAREN() { return getToken(ScriptParser.LPAREN, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ScriptParser.RPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public CastExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitCastExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NewMapExpressionContext extends ExprContext {
		public TypeContext kt;
		public TypeContext vt;
		public TerminalNode NEW() { return getToken(ScriptParser.NEW, 0); }
		public TerminalNode LCURLY() { return getToken(ScriptParser.LCURLY, 0); }
		public TerminalNode COLON() { return getToken(ScriptParser.COLON, 0); }
		public TerminalNode RCURLY() { return getToken(ScriptParser.RCURLY, 0); }
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public NewMapExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitNewMapExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NewArrayExpressionContext extends ExprContext {
		public TerminalNode NEW() { return getToken(ScriptParser.NEW, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode LBRACKET() { return getToken(ScriptParser.LBRACKET, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(ScriptParser.RBRACKET, 0); }
		public NewArrayExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitNewArrayExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExtFuncCallExpressionContext extends ExprContext {
		public NamespaceContext namespace() {
			return getRuleContext(NamespaceContext.class,0);
		}
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public ExtFuncCallExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitExtFuncCallExpression(this);
			else return visitor.visitChildren(this);
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
		int _startState = 36;
		enterRecursionRule(_localctx, 36, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(337);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				_localctx = new NestedExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(256);
				match(LPAREN);
				setState(257);
				expr(0);
				setState(258);
				match(RPAREN);
				}
				break;
			case 2:
				{
				_localctx = new FunctionCallExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(260);
				ident();
				setState(261);
				args();
				}
				break;
			case 3:
				{
				_localctx = new ExtFuncCallExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(263);
				namespace();
				setState(264);
				args();
				}
				break;
			case 4:
				{
				_localctx = new HOFuncExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(266);
				match(DEF);
				setState(267);
				ident();
				}
				break;
			case 5:
				{
				_localctx = new UnaryExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(268);
				((UnaryExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 26525718020096L) != 0)) ) {
					((UnaryExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(269);
				expr(18);
				}
				break;
			case 6:
				{
				_localctx = new CastExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(270);
				match(LPAREN);
				setState(271);
				type(0);
				setState(272);
				match(RPAREN);
				setState(273);
				expr(17);
				}
				break;
			case 7:
				{
				_localctx = new ExplicitMapExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(275);
				match(LCURLY);
				setState(276);
				k_v_pairs();
				setState(277);
				match(RCURLY);
				}
				break;
			case 8:
				{
				_localctx = new ExplicitArrayExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(279);
				match(LBRACKET);
				setState(280);
				expr(0);
				setState(285);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(281);
					match(COMMA);
					setState(282);
					expr(0);
					}
					}
					setState(287);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(288);
				match(RBRACKET);
				}
				break;
			case 9:
				{
				_localctx = new ExplicitListExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(290);
				match(LT);
				setState(291);
				expr(0);
				setState(296);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(292);
					match(COMMA);
					setState(293);
					expr(0);
					}
					}
					setState(298);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(299);
				match(GT);
				}
				break;
			case 10:
				{
				_localctx = new ExplicitSetExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(301);
				match(LCURLY);
				setState(302);
				expr(0);
				setState(307);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(303);
					match(COMMA);
					setState(304);
					expr(0);
					}
					}
					setState(309);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(310);
				match(RCURLY);
				}
				break;
			case 11:
				{
				_localctx = new NewArrayExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(312);
				match(NEW);
				setState(313);
				type(0);
				setState(314);
				match(LBRACKET);
				setState(315);
				expr(0);
				setState(316);
				match(RBRACKET);
				}
				break;
			case 12:
				{
				_localctx = new NewListExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(318);
				match(NEW);
				setState(319);
				type(0);
				setState(320);
				match(LT);
				setState(321);
				match(GT);
				}
				break;
			case 13:
				{
				_localctx = new NewSetExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(323);
				match(NEW);
				setState(324);
				type(0);
				setState(325);
				match(LCURLY);
				setState(326);
				match(RCURLY);
				}
				break;
			case 14:
				{
				_localctx = new NewMapExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(328);
				match(NEW);
				setState(329);
				match(LCURLY);
				setState(330);
				((NewMapExpressionContext)_localctx).kt = type(0);
				setState(331);
				match(COLON);
				setState(332);
				((NewMapExpressionContext)_localctx).vt = type(0);
				setState(333);
				match(RCURLY);
				}
				break;
			case 15:
				{
				_localctx = new AssignableExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(335);
				assignable();
				}
				break;
			case 16:
				{
				_localctx = new LiteralExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(336);
				literal();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(368);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(366);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
					case 1:
						{
						_localctx = new ArithmeticBinExpressionContext(new ExprContext(_parentctx, _parentState));
						((ArithmeticBinExpressionContext)_localctx).a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(339);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(340);
						((ArithmeticBinExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
							((ArithmeticBinExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(341);
						((ArithmeticBinExpressionContext)_localctx).b = expr(17);
						}
						break;
					case 2:
						{
						_localctx = new MultBinExpressionContext(new ExprContext(_parentctx, _parentState));
						((MultBinExpressionContext)_localctx).a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(342);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(343);
						((MultBinExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1924145348608L) != 0)) ) {
							((MultBinExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(344);
						((MultBinExpressionContext)_localctx).b = expr(16);
						}
						break;
					case 3:
						{
						_localctx = new PowerBinExpressionContext(new ExprContext(_parentctx, _parentState));
						((PowerBinExpressionContext)_localctx).a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(345);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(346);
						match(RAISE);
						setState(347);
						((PowerBinExpressionContext)_localctx).b = expr(15);
						}
						break;
					case 4:
						{
						_localctx = new ComparisonBinExpressionContext(new ExprContext(_parentctx, _parentState));
						((ComparisonBinExpressionContext)_localctx).a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(348);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(349);
						((ComparisonBinExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 33822867456L) != 0)) ) {
							((ComparisonBinExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(350);
						((ComparisonBinExpressionContext)_localctx).b = expr(14);
						}
						break;
					case 5:
						{
						_localctx = new LogicBinExpressionContext(new ExprContext(_parentctx, _parentState));
						((LogicBinExpressionContext)_localctx).a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(351);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(352);
						((LogicBinExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==AND || _la==OR) ) {
							((LogicBinExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(353);
						((LogicBinExpressionContext)_localctx).b = expr(13);
						}
						break;
					case 6:
						{
						_localctx = new TernaryExpressionContext(new ExprContext(_parentctx, _parentState));
						((TernaryExpressionContext)_localctx).cond = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(354);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(355);
						match(QUESTION);
						setState(356);
						((TernaryExpressionContext)_localctx).if_ = expr(0);
						setState(357);
						match(COLON);
						setState(358);
						((TernaryExpressionContext)_localctx).else_ = expr(12);
						}
						break;
					case 7:
						{
						_localctx = new ScopedFuncCallExpressionContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(360);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(361);
						subident();
						setState(362);
						args();
						}
						break;
					case 8:
						{
						_localctx = new PropertyExpressionContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(364);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(365);
						subident();
						}
						break;
					}
					} 
				}
				setState(370);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
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

	@SuppressWarnings("CheckReturnValue")
	public static class K_v_pairsContext extends ParserRuleContext {
		public List<K_v_pairContext> k_v_pair() {
			return getRuleContexts(K_v_pairContext.class);
		}
		public K_v_pairContext k_v_pair(int i) {
			return getRuleContext(K_v_pairContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ScriptParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ScriptParser.COMMA, i);
		}
		public K_v_pairsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_k_v_pairs; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitK_v_pairs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final K_v_pairsContext k_v_pairs() throws RecognitionException {
		K_v_pairsContext _localctx = new K_v_pairsContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_k_v_pairs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(371);
			k_v_pair();
			setState(376);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(372);
				match(COMMA);
				setState(373);
				k_v_pair();
				}
				}
				setState(378);
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

	@SuppressWarnings("CheckReturnValue")
	public static class K_v_pairContext extends ParserRuleContext {
		public ExprContext key;
		public ExprContext val;
		public TerminalNode COLON() { return getToken(ScriptParser.COLON, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public K_v_pairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_k_v_pair; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitK_v_pair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final K_v_pairContext k_v_pair() throws RecognitionException {
		K_v_pairContext _localctx = new K_v_pairContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_k_v_pair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(379);
			((K_v_pairContext)_localctx).key = expr(0);
			setState(380);
			match(COLON);
			setState(381);
			((K_v_pairContext)_localctx).val = expr(0);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ArgsContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(ScriptParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ScriptParser.RPAREN, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ScriptParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ScriptParser.COMMA, i);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(383);
			match(LPAREN);
			setState(392);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -1152894974191206064L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 359L) != 0)) {
				{
				setState(384);
				expr(0);
				setState(389);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(385);
					match(COMMA);
					setState(386);
					expr(0);
					}
					}
					setState(391);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(394);
			match(RPAREN);
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

	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentContext extends ParserRuleContext {
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
	 
		public AssignmentContext() { }
		public void copyFrom(AssignmentContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StandardAssignmentContext extends AssignmentContext {
		public AssignableContext assignable() {
			return getRuleContext(AssignableContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(ScriptParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StandardAssignmentContext(AssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitStandardAssignment(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AddAssignmentContext extends AssignmentContext {
		public AssignableContext assignable() {
			return getRuleContext(AssignableContext.class,0);
		}
		public TerminalNode ADD_ASSIGN() { return getToken(ScriptParser.ADD_ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AddAssignmentContext(AssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitAddAssignment(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DivAssignmnetContext extends AssignmentContext {
		public AssignableContext assignable() {
			return getRuleContext(AssignableContext.class,0);
		}
		public TerminalNode DIV_ASSIGN() { return getToken(ScriptParser.DIV_ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public DivAssignmnetContext(AssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitDivAssignmnet(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IncrementAssignmentContext extends AssignmentContext {
		public AssignableContext assignable() {
			return getRuleContext(AssignableContext.class,0);
		}
		public TerminalNode INCREMENT() { return getToken(ScriptParser.INCREMENT, 0); }
		public IncrementAssignmentContext(AssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitIncrementAssignment(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OrAssignmentContext extends AssignmentContext {
		public AssignableContext assignable() {
			return getRuleContext(AssignableContext.class,0);
		}
		public TerminalNode OR_ASSIGN() { return getToken(ScriptParser.OR_ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public OrAssignmentContext(AssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitOrAssignment(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SubAssignmentContext extends AssignmentContext {
		public AssignableContext assignable() {
			return getRuleContext(AssignableContext.class,0);
		}
		public TerminalNode SUB_ASSIGN() { return getToken(ScriptParser.SUB_ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public SubAssignmentContext(AssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitSubAssignment(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AndAssignmentContext extends AssignmentContext {
		public AssignableContext assignable() {
			return getRuleContext(AssignableContext.class,0);
		}
		public TerminalNode AND_ASSIGN() { return getToken(ScriptParser.AND_ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AndAssignmentContext(AssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitAndAssignment(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DecrementAssignmentContext extends AssignmentContext {
		public AssignableContext assignable() {
			return getRuleContext(AssignableContext.class,0);
		}
		public TerminalNode DECREMENT() { return getToken(ScriptParser.DECREMENT, 0); }
		public DecrementAssignmentContext(AssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitDecrementAssignment(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MultAssignmentContext extends AssignmentContext {
		public AssignableContext assignable() {
			return getRuleContext(AssignableContext.class,0);
		}
		public TerminalNode MUL_ASSIGN() { return getToken(ScriptParser.MUL_ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public MultAssignmentContext(AssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitMultAssignment(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ModAssignmentContext extends AssignmentContext {
		public AssignableContext assignable() {
			return getRuleContext(AssignableContext.class,0);
		}
		public TerminalNode MOD_ASSIGN() { return getToken(ScriptParser.MOD_ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ModAssignmentContext(AssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitModAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_assignment);
		try {
			setState(434);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				_localctx = new StandardAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(396);
				assignable();
				setState(397);
				match(ASSIGN);
				setState(398);
				expr(0);
				}
				break;
			case 2:
				_localctx = new IncrementAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(400);
				assignable();
				setState(401);
				match(INCREMENT);
				}
				break;
			case 3:
				_localctx = new DecrementAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(403);
				assignable();
				setState(404);
				match(DECREMENT);
				}
				break;
			case 4:
				_localctx = new AddAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(406);
				assignable();
				setState(407);
				match(ADD_ASSIGN);
				setState(408);
				expr(0);
				}
				break;
			case 5:
				_localctx = new SubAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(410);
				assignable();
				setState(411);
				match(SUB_ASSIGN);
				setState(412);
				expr(0);
				}
				break;
			case 6:
				_localctx = new MultAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(414);
				assignable();
				setState(415);
				match(MUL_ASSIGN);
				setState(416);
				expr(0);
				}
				break;
			case 7:
				_localctx = new DivAssignmnetContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(418);
				assignable();
				setState(419);
				match(DIV_ASSIGN);
				setState(420);
				expr(0);
				}
				break;
			case 8:
				_localctx = new ModAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(422);
				assignable();
				setState(423);
				match(MOD_ASSIGN);
				setState(424);
				expr(0);
				}
				break;
			case 9:
				_localctx = new AndAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(426);
				assignable();
				setState(427);
				match(AND_ASSIGN);
				setState(428);
				expr(0);
				}
				break;
			case 10:
				_localctx = new OrAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(430);
				assignable();
				setState(431);
				match(OR_ASSIGN);
				setState(432);
				expr(0);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Var_initContext extends ParserRuleContext {
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(ScriptParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Var_initContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_init; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitVar_init(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_initContext var_init() throws RecognitionException {
		Var_initContext _localctx = new Var_initContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_var_init);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(436);
			declaration();
			setState(437);
			match(ASSIGN);
			setState(438);
			expr(0);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Var_defContext extends ParserRuleContext {
		public Var_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_def; }
	 
		public Var_defContext() { }
		public void copyFrom(Var_defContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExplicitVarDefContext extends Var_defContext {
		public Var_initContext var_init() {
			return getRuleContext(Var_initContext.class,0);
		}
		public ExplicitVarDefContext(Var_defContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitExplicitVarDef(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ImplicitVarDefContext extends Var_defContext {
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public ImplicitVarDefContext(Var_defContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitImplicitVarDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_defContext var_def() throws RecognitionException {
		Var_defContext _localctx = new Var_defContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_var_def);
		try {
			setState(442);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				_localctx = new ImplicitVarDefContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(440);
				declaration();
				}
				break;
			case 2:
				_localctx = new ExplicitVarDefContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(441);
				var_init();
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

	@SuppressWarnings("CheckReturnValue")
	public static class AssignableContext extends ParserRuleContext {
		public AssignableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignable; }
	 
		public AssignableContext() { }
		public void copyFrom(AssignableContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArrayAssignableContext extends AssignableContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode LBRACKET() { return getToken(ScriptParser.LBRACKET, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(ScriptParser.RBRACKET, 0); }
		public ArrayAssignableContext(AssignableContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitArrayAssignable(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SimpleAssignableContext extends AssignableContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public SimpleAssignableContext(AssignableContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitSimpleAssignable(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ListAssignableContext extends AssignableContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode LT() { return getToken(ScriptParser.LT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode GT() { return getToken(ScriptParser.GT, 0); }
		public ListAssignableContext(AssignableContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitListAssignable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignableContext assignable() throws RecognitionException {
		AssignableContext _localctx = new AssignableContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_assignable);
		try {
			setState(455);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				_localctx = new SimpleAssignableContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(444);
				ident();
				}
				break;
			case 2:
				_localctx = new ListAssignableContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(445);
				ident();
				setState(446);
				match(LT);
				setState(447);
				expr(0);
				setState(448);
				match(GT);
				}
				break;
			case 3:
				_localctx = new ArrayAssignableContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(450);
				ident();
				setState(451);
				match(LBRACKET);
				setState(452);
				expr(0);
				setState(453);
				match(RBRACKET);
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

	@SuppressWarnings("CheckReturnValue")
	public static class IdentContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(ScriptParser.IDENTIFIER, 0); }
		public IdentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ident; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitIdent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentContext ident() throws RecognitionException {
		IdentContext _localctx = new IdentContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_ident);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(457);
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

	@SuppressWarnings("CheckReturnValue")
	public static class SubidentContext extends ParserRuleContext {
		public TerminalNode SUB_IDENT() { return getToken(ScriptParser.SUB_IDENT, 0); }
		public SubidentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subident; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitSubident(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubidentContext subident() throws RecognitionException {
		SubidentContext _localctx = new SubidentContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_subident);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(459);
			match(SUB_IDENT);
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

	@SuppressWarnings("CheckReturnValue")
	public static class NamespaceContext extends ParserRuleContext {
		public TerminalNode EXTENSION() { return getToken(ScriptParser.EXTENSION, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public SubidentContext subident() {
			return getRuleContext(SubidentContext.class,0);
		}
		public NamespaceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespace; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitNamespace(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NamespaceContext namespace() throws RecognitionException {
		NamespaceContext _localctx = new NamespaceContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_namespace);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(461);
			match(EXTENSION);
			setState(462);
			ident();
			setState(463);
			subident();
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

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralContext extends ParserRuleContext {
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	 
		public LiteralContext() { }
		public void copyFrom(LiteralContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ColorLiteralContext extends LiteralContext {
		public TerminalNode COL_LIT() { return getToken(ScriptParser.COL_LIT, 0); }
		public ColorLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitColorLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringLiteralContext extends LiteralContext {
		public TerminalNode STRING_LIT() { return getToken(ScriptParser.STRING_LIT, 0); }
		public StringLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitStringLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CharLiteralContext extends LiteralContext {
		public TerminalNode CHAR_LIT() { return getToken(ScriptParser.CHAR_LIT, 0); }
		public CharLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitCharLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BoolLiteralContext extends LiteralContext {
		public Bool_litContext bool_lit() {
			return getRuleContext(Bool_litContext.class,0);
		}
		public BoolLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitBoolLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FloatLiteralContext extends LiteralContext {
		public TerminalNode FLOAT_LIT() { return getToken(ScriptParser.FLOAT_LIT, 0); }
		public FloatLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitFloatLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IntLiteralContext extends LiteralContext {
		public Int_litContext int_lit() {
			return getRuleContext(Int_litContext.class,0);
		}
		public IntLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitIntLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_literal);
		try {
			setState(471);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING_LIT:
				_localctx = new StringLiteralContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(465);
				match(STRING_LIT);
				}
				break;
			case CHAR_LIT:
				_localctx = new CharLiteralContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(466);
				match(CHAR_LIT);
				}
				break;
			case COL_LIT:
				_localctx = new ColorLiteralContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(467);
				match(COL_LIT);
				}
				break;
			case DEC_LIT:
			case HEX_LIT:
				_localctx = new IntLiteralContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(468);
				int_lit();
				}
				break;
			case FLOAT_LIT:
				_localctx = new FloatLiteralContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(469);
				match(FLOAT_LIT);
				}
				break;
			case TRUE:
			case FALSE:
				_localctx = new BoolLiteralContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(470);
				bool_lit();
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

	@SuppressWarnings("CheckReturnValue")
	public static class Int_litContext extends ParserRuleContext {
		public Int_litContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_int_lit; }
	 
		public Int_litContext() { }
		public void copyFrom(Int_litContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class HexadecimalContext extends Int_litContext {
		public TerminalNode HEX_LIT() { return getToken(ScriptParser.HEX_LIT, 0); }
		public HexadecimalContext(Int_litContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitHexadecimal(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DecimalContext extends Int_litContext {
		public TerminalNode DEC_LIT() { return getToken(ScriptParser.DEC_LIT, 0); }
		public DecimalContext(Int_litContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitDecimal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Int_litContext int_lit() throws RecognitionException {
		Int_litContext _localctx = new Int_litContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_int_lit);
		try {
			setState(475);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case HEX_LIT:
				_localctx = new HexadecimalContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(473);
				match(HEX_LIT);
				}
				break;
			case DEC_LIT:
				_localctx = new DecimalContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(474);
				match(DEC_LIT);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Bool_litContext extends ParserRuleContext {
		public Bool_litContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool_lit; }
	 
		public Bool_litContext() { }
		public void copyFrom(Bool_litContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TrueContext extends Bool_litContext {
		public TerminalNode TRUE() { return getToken(ScriptParser.TRUE, 0); }
		public TrueContext(Bool_litContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitTrue(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FalseContext extends Bool_litContext {
		public TerminalNode FALSE() { return getToken(ScriptParser.FALSE, 0); }
		public FalseContext(Bool_litContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitFalse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Bool_litContext bool_lit() throws RecognitionException {
		Bool_litContext _localctx = new Bool_litContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_bool_lit);
		try {
			setState(479);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TRUE:
				_localctx = new TrueContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(477);
				match(TRUE);
				}
				break;
			case FALSE:
				_localctx = new FalseContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(478);
				match(FALSE);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 6:
			return type_sempred((TypeContext)_localctx, predIndex);
		case 18:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean type_sempred(TypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		case 2:
			return precpred(_ctx, 4);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 16);
		case 4:
			return precpred(_ctx, 15);
		case 5:
			return precpred(_ctx, 14);
		case 6:
			return precpred(_ctx, 13);
		case 7:
			return precpred(_ctx, 12);
		case 8:
			return precpred(_ctx, 11);
		case 9:
			return precpred(_ctx, 20);
		case 10:
			return precpred(_ctx, 19);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001I\u01e2\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0001\u0000\u0001\u0000\u0001\u0000\u0005\u0000"+
		"D\b\u0000\n\u0000\f\u0000G\t\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002P\b\u0002"+
		"\u0001\u0003\u0001\u0003\u0003\u0003T\b\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0003\u0003Y\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0003\u0003_\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0005\u0004d\b\u0004\n\u0004\f\u0004g\t\u0004\u0001\u0005\u0003\u0005"+
		"j\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006"+
		"\u0082\b\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006\u008d\b\u0006"+
		"\n\u0006\f\u0006\u0090\t\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\b\u0001\b\u0001\b\u0005\b\u0099\b\b\n\b\f\b\u009c\t\b\u0003"+
		"\b\u009e\b\b\u0001\t\u0001\t\u0001\t\u0005\t\u00a3\b\t\n\t\f\t\u00a6\t"+
		"\t\u0001\t\u0003\t\u00a9\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00c1"+
		"\b\n\u0001\u000b\u0001\u000b\u0003\u000b\u00c5\b\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u00d7\b\f\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u00f1\b\u0010\n\u0010"+
		"\f\u0010\u00f4\t\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u00f8\b\u0010"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0005\u0012\u011c\b\u0012"+
		"\n\u0012\f\u0012\u011f\t\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0005\u0012\u0127\b\u0012\n\u0012\f\u0012"+
		"\u012a\t\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0005\u0012\u0132\b\u0012\n\u0012\f\u0012\u0135\t\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u0152\b\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0005\u0012\u016f\b\u0012\n\u0012\f\u0012\u0172\t\u0012"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0005\u0013\u0177\b\u0013\n\u0013"+
		"\f\u0013\u017a\t\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u0184\b\u0015"+
		"\n\u0015\f\u0015\u0187\t\u0015\u0003\u0015\u0189\b\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u01b3\b\u0016\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0003\u0018\u01bb"+
		"\b\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003"+
		"\u0019\u01c8\b\u0019\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001"+
		"\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0003\u001d\u01d8\b\u001d\u0001"+
		"\u001e\u0001\u001e\u0003\u001e\u01dc\b\u001e\u0001\u001f\u0001\u001f\u0003"+
		"\u001f\u01e0\b\u001f\u0001\u001f\u0000\u0002\f$ \u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,."+
		"02468:<>\u0000\u0005\u0002\u0000%%+,\u0001\u0000$%\u0001\u0000&(\u0001"+
		"\u0000\u001d\"\u0001\u0000)*\u0215\u0000@\u0001\u0000\u0000\u0000\u0002"+
		"H\u0001\u0000\u0000\u0000\u0004O\u0001\u0000\u0000\u0000\u0006^\u0001"+
		"\u0000\u0000\u0000\b`\u0001\u0000\u0000\u0000\ni\u0001\u0000\u0000\u0000"+
		"\f\u0081\u0001\u0000\u0000\u0000\u000e\u0091\u0001\u0000\u0000\u0000\u0010"+
		"\u009d\u0001\u0000\u0000\u0000\u0012\u00a8\u0001\u0000\u0000\u0000\u0014"+
		"\u00c0\u0001\u0000\u0000\u0000\u0016\u00c2\u0001\u0000\u0000\u0000\u0018"+
		"\u00d6\u0001\u0000\u0000\u0000\u001a\u00d8\u0001\u0000\u0000\u0000\u001c"+
		"\u00df\u0001\u0000\u0000\u0000\u001e\u00e4\u0001\u0000\u0000\u0000 \u00ed"+
		"\u0001\u0000\u0000\u0000\"\u00f9\u0001\u0000\u0000\u0000$\u0151\u0001"+
		"\u0000\u0000\u0000&\u0173\u0001\u0000\u0000\u0000(\u017b\u0001\u0000\u0000"+
		"\u0000*\u017f\u0001\u0000\u0000\u0000,\u01b2\u0001\u0000\u0000\u0000."+
		"\u01b4\u0001\u0000\u0000\u00000\u01ba\u0001\u0000\u0000\u00002\u01c7\u0001"+
		"\u0000\u0000\u00004\u01c9\u0001\u0000\u0000\u00006\u01cb\u0001\u0000\u0000"+
		"\u00008\u01cd\u0001\u0000\u0000\u0000:\u01d7\u0001\u0000\u0000\u0000<"+
		"\u01db\u0001\u0000\u0000\u0000>\u01df\u0001\u0000\u0000\u0000@A\u0003"+
		"\u0006\u0003\u0000AE\u0003\u0004\u0002\u0000BD\u0003\u0002\u0001\u0000"+
		"CB\u0001\u0000\u0000\u0000DG\u0001\u0000\u0000\u0000EC\u0001\u0000\u0000"+
		"\u0000EF\u0001\u0000\u0000\u0000F\u0001\u0001\u0000\u0000\u0000GE\u0001"+
		"\u0000\u0000\u0000HI\u00034\u001a\u0000IJ\u0003\u0006\u0003\u0000JK\u0003"+
		"\u0004\u0002\u0000K\u0003\u0001\u0000\u0000\u0000LP\u0003\u0012\t\u0000"+
		"MN\u0005\u001a\u0000\u0000NP\u0003$\u0012\u0000OL\u0001\u0000\u0000\u0000"+
		"OM\u0001\u0000\u0000\u0000P\u0005\u0001\u0000\u0000\u0000QS\u0005\u0004"+
		"\u0000\u0000RT\u0003\b\u0004\u0000SR\u0001\u0000\u0000\u0000ST\u0001\u0000"+
		"\u0000\u0000TU\u0001\u0000\u0000\u0000U_\u0005\u0005\u0000\u0000VX\u0005"+
		"\u0004\u0000\u0000WY\u0003\b\u0004\u0000XW\u0001\u0000\u0000\u0000XY\u0001"+
		"\u0000\u0000\u0000YZ\u0001\u0000\u0000\u0000Z[\u0005\u001a\u0000\u0000"+
		"[\\\u0003\f\u0006\u0000\\]\u0005\u0005\u0000\u0000]_\u0001\u0000\u0000"+
		"\u0000^Q\u0001\u0000\u0000\u0000^V\u0001\u0000\u0000\u0000_\u0007\u0001"+
		"\u0000\u0000\u0000`e\u0003\n\u0005\u0000ab\u0005\f\u0000\u0000bd\u0003"+
		"\n\u0005\u0000ca\u0001\u0000\u0000\u0000dg\u0001\u0000\u0000\u0000ec\u0001"+
		"\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000f\t\u0001\u0000\u0000\u0000"+
		"ge\u0001\u0000\u0000\u0000hj\u0005.\u0000\u0000ih\u0001\u0000\u0000\u0000"+
		"ij\u0001\u0000\u0000\u0000jk\u0001\u0000\u0000\u0000kl\u0003\f\u0006\u0000"+
		"lm\u00034\u001a\u0000m\u000b\u0001\u0000\u0000\u0000no\u0006\u0006\uffff"+
		"\uffff\u0000o\u0082\u0005/\u0000\u0000p\u0082\u00051\u0000\u0000q\u0082"+
		"\u00050\u0000\u0000r\u0082\u00052\u0000\u0000s\u0082\u00055\u0000\u0000"+
		"t\u0082\u00054\u0000\u0000u\u0082\u00053\u0000\u0000vw\u0005\b\u0000\u0000"+
		"wx\u0003\f\u0006\u0000xy\u0005\u000b\u0000\u0000yz\u0003\f\u0006\u0000"+
		"z{\u0005\t\u0000\u0000{\u0082\u0001\u0000\u0000\u0000|}\u0005\u0004\u0000"+
		"\u0000}~\u0003\u000e\u0007\u0000~\u007f\u0005\u0005\u0000\u0000\u007f"+
		"\u0082\u0001\u0000\u0000\u0000\u0080\u0082\u00034\u001a\u0000\u0081n\u0001"+
		"\u0000\u0000\u0000\u0081p\u0001\u0000\u0000\u0000\u0081q\u0001\u0000\u0000"+
		"\u0000\u0081r\u0001\u0000\u0000\u0000\u0081s\u0001\u0000\u0000\u0000\u0081"+
		"t\u0001\u0000\u0000\u0000\u0081u\u0001\u0000\u0000\u0000\u0081v\u0001"+
		"\u0000\u0000\u0000\u0081|\u0001\u0000\u0000\u0000\u0081\u0080\u0001\u0000"+
		"\u0000\u0000\u0082\u008e\u0001\u0000\u0000\u0000\u0083\u0084\n\u0006\u0000"+
		"\u0000\u0084\u0085\u0005\u0006\u0000\u0000\u0085\u008d\u0005\u0007\u0000"+
		"\u0000\u0086\u0087\n\u0005\u0000\u0000\u0087\u0088\u0005 \u0000\u0000"+
		"\u0088\u008d\u0005\u001f\u0000\u0000\u0089\u008a\n\u0004\u0000\u0000\u008a"+
		"\u008b\u0005\b\u0000\u0000\u008b\u008d\u0005\t\u0000\u0000\u008c\u0083"+
		"\u0001\u0000\u0000\u0000\u008c\u0086\u0001\u0000\u0000\u0000\u008c\u0089"+
		"\u0001\u0000\u0000\u0000\u008d\u0090\u0001\u0000\u0000\u0000\u008e\u008c"+
		"\u0001\u0000\u0000\u0000\u008e\u008f\u0001\u0000\u0000\u0000\u008f\r\u0001"+
		"\u0000\u0000\u0000\u0090\u008e\u0001\u0000\u0000\u0000\u0091\u0092\u0003"+
		"\u0010\b\u0000\u0092\u0093\u0005\u001a\u0000\u0000\u0093\u0094\u0003\f"+
		"\u0006\u0000\u0094\u000f\u0001\u0000\u0000\u0000\u0095\u009a\u0003\f\u0006"+
		"\u0000\u0096\u0097\u0005\f\u0000\u0000\u0097\u0099\u0003\f\u0006\u0000"+
		"\u0098\u0096\u0001\u0000\u0000\u0000\u0099\u009c\u0001\u0000\u0000\u0000"+
		"\u009a\u0098\u0001\u0000\u0000\u0000\u009a\u009b\u0001\u0000\u0000\u0000"+
		"\u009b\u009e\u0001\u0000\u0000\u0000\u009c\u009a\u0001\u0000\u0000\u0000"+
		"\u009d\u0095\u0001\u0000\u0000\u0000\u009d\u009e\u0001\u0000\u0000\u0000"+
		"\u009e\u0011\u0001\u0000\u0000\u0000\u009f\u00a9\u0003\u0014\n\u0000\u00a0"+
		"\u00a4\u0005\b\u0000\u0000\u00a1\u00a3\u0003\u0014\n\u0000\u00a2\u00a1"+
		"\u0001\u0000\u0000\u0000\u00a3\u00a6\u0001\u0000\u0000\u0000\u00a4\u00a2"+
		"\u0001\u0000\u0000\u0000\u00a4\u00a5\u0001\u0000\u0000\u0000\u00a5\u00a7"+
		"\u0001\u0000\u0000\u0000\u00a6\u00a4\u0001\u0000\u0000\u0000\u00a7\u00a9"+
		"\u0005\t\u0000\u0000\u00a8\u009f\u0001\u0000\u0000\u0000\u00a8\u00a0\u0001"+
		"\u0000\u0000\u0000\u00a9\u0013\u0001\u0000\u0000\u0000\u00aa\u00c1\u0003"+
		"\u0018\f\u0000\u00ab\u00c1\u0003 \u0010\u0000\u00ac\u00ad\u00030\u0018"+
		"\u0000\u00ad\u00ae\u0005\n\u0000\u0000\u00ae\u00c1\u0001\u0000\u0000\u0000"+
		"\u00af\u00b0\u0003,\u0016\u0000\u00b0\u00b1\u0005\n\u0000\u0000\u00b1"+
		"\u00c1\u0001\u0000\u0000\u0000\u00b2\u00c1\u0003\u0016\u000b\u0000\u00b3"+
		"\u00b4\u0003$\u0012\u0000\u00b4\u00b5\u00036\u001b\u0000\u00b5\u00b6\u0003"+
		"*\u0015\u0000\u00b6\u00b7\u0005\n\u0000\u0000\u00b7\u00c1\u0001\u0000"+
		"\u0000\u0000\u00b8\u00b9\u00034\u001a\u0000\u00b9\u00ba\u0003*\u0015\u0000"+
		"\u00ba\u00bb\u0005\n\u0000\u0000\u00bb\u00c1\u0001\u0000\u0000\u0000\u00bc"+
		"\u00bd\u00038\u001c\u0000\u00bd\u00be\u0003*\u0015\u0000\u00be\u00bf\u0005"+
		"\n\u0000\u0000\u00bf\u00c1\u0001\u0000\u0000\u0000\u00c0\u00aa\u0001\u0000"+
		"\u0000\u0000\u00c0\u00ab\u0001\u0000\u0000\u0000\u00c0\u00ac\u0001\u0000"+
		"\u0000\u0000\u00c0\u00af\u0001\u0000\u0000\u0000\u00c0\u00b2\u0001\u0000"+
		"\u0000\u0000\u00c0\u00b3\u0001\u0000\u0000\u0000\u00c0\u00b8\u0001\u0000"+
		"\u0000\u0000\u00c0\u00bc\u0001\u0000\u0000\u0000\u00c1\u0015\u0001\u0000"+
		"\u0000\u0000\u00c2\u00c4\u00056\u0000\u0000\u00c3\u00c5\u0003$\u0012\u0000"+
		"\u00c4\u00c3\u0001\u0000\u0000\u0000\u00c4\u00c5\u0001\u0000\u0000\u0000"+
		"\u00c5\u00c6\u0001\u0000\u0000\u0000\u00c6\u00c7\u0005\n\u0000\u0000\u00c7"+
		"\u0017\u0001\u0000\u0000\u0000\u00c8\u00c9\u0003\u001c\u000e\u0000\u00c9"+
		"\u00ca\u0003\u0012\t\u0000\u00ca\u00d7\u0001\u0000\u0000\u0000\u00cb\u00cc"+
		"\u0003\u001a\r\u0000\u00cc\u00cd\u0003\u0012\t\u0000\u00cd\u00d7\u0001"+
		"\u0000\u0000\u0000\u00ce\u00cf\u0003\u001e\u000f\u0000\u00cf\u00d0\u0003"+
		"\u0012\t\u0000\u00d0\u00d7\u0001\u0000\u0000\u0000\u00d1\u00d2\u00057"+
		"\u0000\u0000\u00d2\u00d3\u0003\u0012\t\u0000\u00d3\u00d4\u0003\u001c\u000e"+
		"\u0000\u00d4\u00d5\u0005\n\u0000\u0000\u00d5\u00d7\u0001\u0000\u0000\u0000"+
		"\u00d6\u00c8\u0001\u0000\u0000\u0000\u00d6\u00cb\u0001\u0000\u0000\u0000"+
		"\u00d6\u00ce\u0001\u0000\u0000\u0000\u00d6\u00d1\u0001\u0000\u0000\u0000"+
		"\u00d7\u0019\u0001\u0000\u0000\u0000\u00d8\u00d9\u00059\u0000\u0000\u00d9"+
		"\u00da\u0005\u0004\u0000\u0000\u00da\u00db\u0003\n\u0005\u0000\u00db\u00dc"+
		"\u0005-\u0000\u0000\u00dc\u00dd\u0003$\u0012\u0000\u00dd\u00de\u0005\u0005"+
		"\u0000\u0000\u00de\u001b\u0001\u0000\u0000\u0000\u00df\u00e0\u00058\u0000"+
		"\u0000\u00e0\u00e1\u0005\u0004\u0000\u0000\u00e1\u00e2\u0003$\u0012\u0000"+
		"\u00e2\u00e3\u0005\u0005\u0000\u0000\u00e3\u001d\u0001\u0000\u0000\u0000"+
		"\u00e4\u00e5\u00059\u0000\u0000\u00e5\u00e6\u0005\u0004\u0000\u0000\u00e6"+
		"\u00e7\u0003.\u0017\u0000\u00e7\u00e8\u0005\n\u0000\u0000\u00e8\u00e9"+
		"\u0003$\u0012\u0000\u00e9\u00ea\u0005\n\u0000\u0000\u00ea\u00eb\u0003"+
		",\u0016\u0000\u00eb\u00ec\u0005\u0005\u0000\u0000\u00ec\u001f\u0001\u0000"+
		"\u0000\u0000\u00ed\u00f2\u0003\"\u0011\u0000\u00ee\u00ef\u0005;\u0000"+
		"\u0000\u00ef\u00f1\u0003\"\u0011\u0000\u00f0\u00ee\u0001\u0000\u0000\u0000"+
		"\u00f1\u00f4\u0001\u0000\u0000\u0000\u00f2\u00f0\u0001\u0000\u0000\u0000"+
		"\u00f2\u00f3\u0001\u0000\u0000\u0000\u00f3\u00f7\u0001\u0000\u0000\u0000"+
		"\u00f4\u00f2\u0001\u0000\u0000\u0000\u00f5\u00f6\u0005;\u0000\u0000\u00f6"+
		"\u00f8\u0003\u0012\t\u0000\u00f7\u00f5\u0001\u0000\u0000\u0000\u00f7\u00f8"+
		"\u0001\u0000\u0000\u0000\u00f8!\u0001\u0000\u0000\u0000\u00f9\u00fa\u0005"+
		":\u0000\u0000\u00fa\u00fb\u0005\u0004\u0000\u0000\u00fb\u00fc\u0003$\u0012"+
		"\u0000\u00fc\u00fd\u0005\u0005\u0000\u0000\u00fd\u00fe\u0003\u0012\t\u0000"+
		"\u00fe#\u0001\u0000\u0000\u0000\u00ff\u0100\u0006\u0012\uffff\uffff\u0000"+
		"\u0100\u0101\u0005\u0004\u0000\u0000\u0101\u0102\u0003$\u0012\u0000\u0102"+
		"\u0103\u0005\u0005\u0000\u0000\u0103\u0152\u0001\u0000\u0000\u0000\u0104"+
		"\u0105\u00034\u001a\u0000\u0105\u0106\u0003*\u0015\u0000\u0106\u0152\u0001"+
		"\u0000\u0000\u0000\u0107\u0108\u00038\u001c\u0000\u0108\u0109\u0003*\u0015"+
		"\u0000\u0109\u0152\u0001\u0000\u0000\u0000\u010a\u010b\u0005\u001b\u0000"+
		"\u0000\u010b\u0152\u00034\u001a\u0000\u010c\u010d\u0007\u0000\u0000\u0000"+
		"\u010d\u0152\u0003$\u0012\u0012\u010e\u010f\u0005\u0004\u0000\u0000\u010f"+
		"\u0110\u0003\f\u0006\u0000\u0110\u0111\u0005\u0005\u0000\u0000\u0111\u0112"+
		"\u0003$\u0012\u0011\u0112\u0152\u0001\u0000\u0000\u0000\u0113\u0114\u0005"+
		"\b\u0000\u0000\u0114\u0115\u0003&\u0013\u0000\u0115\u0116\u0005\t\u0000"+
		"\u0000\u0116\u0152\u0001\u0000\u0000\u0000\u0117\u0118\u0005\u0006\u0000"+
		"\u0000\u0118\u011d\u0003$\u0012\u0000\u0119\u011a\u0005\f\u0000\u0000"+
		"\u011a\u011c\u0003$\u0012\u0000\u011b\u0119\u0001\u0000\u0000\u0000\u011c"+
		"\u011f\u0001\u0000\u0000\u0000\u011d\u011b\u0001\u0000\u0000\u0000\u011d"+
		"\u011e\u0001\u0000\u0000\u0000\u011e\u0120\u0001\u0000\u0000\u0000\u011f"+
		"\u011d\u0001\u0000\u0000\u0000\u0120\u0121\u0005\u0007\u0000\u0000\u0121"+
		"\u0152\u0001\u0000\u0000\u0000\u0122\u0123\u0005 \u0000\u0000\u0123\u0128"+
		"\u0003$\u0012\u0000\u0124\u0125\u0005\f\u0000\u0000\u0125\u0127\u0003"+
		"$\u0012\u0000\u0126\u0124\u0001\u0000\u0000\u0000\u0127\u012a\u0001\u0000"+
		"\u0000\u0000\u0128\u0126\u0001\u0000\u0000\u0000\u0128\u0129\u0001\u0000"+
		"\u0000\u0000\u0129\u012b\u0001\u0000\u0000\u0000\u012a\u0128\u0001\u0000"+
		"\u0000\u0000\u012b\u012c\u0005\u001f\u0000\u0000\u012c\u0152\u0001\u0000"+
		"\u0000\u0000\u012d\u012e\u0005\b\u0000\u0000\u012e\u0133\u0003$\u0012"+
		"\u0000\u012f\u0130\u0005\f\u0000\u0000\u0130\u0132\u0003$\u0012\u0000"+
		"\u0131\u012f\u0001\u0000\u0000\u0000\u0132\u0135\u0001\u0000\u0000\u0000"+
		"\u0133\u0131\u0001\u0000\u0000\u0000\u0133\u0134\u0001\u0000\u0000\u0000"+
		"\u0134\u0136\u0001\u0000\u0000\u0000\u0135\u0133\u0001\u0000\u0000\u0000"+
		"\u0136\u0137\u0005\t\u0000\u0000\u0137\u0152\u0001\u0000\u0000\u0000\u0138"+
		"\u0139\u0005>\u0000\u0000\u0139\u013a\u0003\f\u0006\u0000\u013a\u013b"+
		"\u0005\u0006\u0000\u0000\u013b\u013c\u0003$\u0012\u0000\u013c\u013d\u0005"+
		"\u0007\u0000\u0000\u013d\u0152\u0001\u0000\u0000\u0000\u013e\u013f\u0005"+
		">\u0000\u0000\u013f\u0140\u0003\f\u0006\u0000\u0140\u0141\u0005 \u0000"+
		"\u0000\u0141\u0142\u0005\u001f\u0000\u0000\u0142\u0152\u0001\u0000\u0000"+
		"\u0000\u0143\u0144\u0005>\u0000\u0000\u0144\u0145\u0003\f\u0006\u0000"+
		"\u0145\u0146\u0005\b\u0000\u0000\u0146\u0147\u0005\t\u0000\u0000\u0147"+
		"\u0152\u0001\u0000\u0000\u0000\u0148\u0149\u0005>\u0000\u0000\u0149\u014a"+
		"\u0005\b\u0000\u0000\u014a\u014b\u0003\f\u0006\u0000\u014b\u014c\u0005"+
		"\u000b\u0000\u0000\u014c\u014d\u0003\f\u0006\u0000\u014d\u014e\u0005\t"+
		"\u0000\u0000\u014e\u0152\u0001\u0000\u0000\u0000\u014f\u0152\u00032\u0019"+
		"\u0000\u0150\u0152\u0003:\u001d\u0000\u0151\u00ff\u0001\u0000\u0000\u0000"+
		"\u0151\u0104\u0001\u0000\u0000\u0000\u0151\u0107\u0001\u0000\u0000\u0000"+
		"\u0151\u010a\u0001\u0000\u0000\u0000\u0151\u010c\u0001\u0000\u0000\u0000"+
		"\u0151\u010e\u0001\u0000\u0000\u0000\u0151\u0113\u0001\u0000\u0000\u0000"+
		"\u0151\u0117\u0001\u0000\u0000\u0000\u0151\u0122\u0001\u0000\u0000\u0000"+
		"\u0151\u012d\u0001\u0000\u0000\u0000\u0151\u0138\u0001\u0000\u0000\u0000"+
		"\u0151\u013e\u0001\u0000\u0000\u0000\u0151\u0143\u0001\u0000\u0000\u0000"+
		"\u0151\u0148\u0001\u0000\u0000\u0000\u0151\u014f\u0001\u0000\u0000\u0000"+
		"\u0151\u0150\u0001\u0000\u0000\u0000\u0152\u0170\u0001\u0000\u0000\u0000"+
		"\u0153\u0154\n\u0010\u0000\u0000\u0154\u0155\u0007\u0001\u0000\u0000\u0155"+
		"\u016f\u0003$\u0012\u0011\u0156\u0157\n\u000f\u0000\u0000\u0157\u0158"+
		"\u0007\u0002\u0000\u0000\u0158\u016f\u0003$\u0012\u0010\u0159\u015a\n"+
		"\u000e\u0000\u0000\u015a\u015b\u0005#\u0000\u0000\u015b\u016f\u0003$\u0012"+
		"\u000f\u015c\u015d\n\r\u0000\u0000\u015d\u015e\u0007\u0003\u0000\u0000"+
		"\u015e\u016f\u0003$\u0012\u000e\u015f\u0160\n\f\u0000\u0000\u0160\u0161"+
		"\u0007\u0004\u0000\u0000\u0161\u016f\u0003$\u0012\r\u0162\u0163\n\u000b"+
		"\u0000\u0000\u0163\u0164\u0005\u000f\u0000\u0000\u0164\u0165\u0003$\u0012"+
		"\u0000\u0165\u0166\u0005\u000b\u0000\u0000\u0166\u0167\u0003$\u0012\f"+
		"\u0167\u016f\u0001\u0000\u0000\u0000\u0168\u0169\n\u0014\u0000\u0000\u0169"+
		"\u016a\u00036\u001b\u0000\u016a\u016b\u0003*\u0015\u0000\u016b\u016f\u0001"+
		"\u0000\u0000\u0000\u016c\u016d\n\u0013\u0000\u0000\u016d\u016f\u00036"+
		"\u001b\u0000\u016e\u0153\u0001\u0000\u0000\u0000\u016e\u0156\u0001\u0000"+
		"\u0000\u0000\u016e\u0159\u0001\u0000\u0000\u0000\u016e\u015c\u0001\u0000"+
		"\u0000\u0000\u016e\u015f\u0001\u0000\u0000\u0000\u016e\u0162\u0001\u0000"+
		"\u0000\u0000\u016e\u0168\u0001\u0000\u0000\u0000\u016e\u016c\u0001\u0000"+
		"\u0000\u0000\u016f\u0172\u0001\u0000\u0000\u0000\u0170\u016e\u0001\u0000"+
		"\u0000\u0000\u0170\u0171\u0001\u0000\u0000\u0000\u0171%\u0001\u0000\u0000"+
		"\u0000\u0172\u0170\u0001\u0000\u0000\u0000\u0173\u0178\u0003(\u0014\u0000"+
		"\u0174\u0175\u0005\f\u0000\u0000\u0175\u0177\u0003(\u0014\u0000\u0176"+
		"\u0174\u0001\u0000\u0000\u0000\u0177\u017a\u0001\u0000\u0000\u0000\u0178"+
		"\u0176\u0001\u0000\u0000\u0000\u0178\u0179\u0001\u0000\u0000\u0000\u0179"+
		"\'\u0001\u0000\u0000\u0000\u017a\u0178\u0001\u0000\u0000\u0000\u017b\u017c"+
		"\u0003$\u0012\u0000\u017c\u017d\u0005\u000b\u0000\u0000\u017d\u017e\u0003"+
		"$\u0012\u0000\u017e)\u0001\u0000\u0000\u0000\u017f\u0188\u0005\u0004\u0000"+
		"\u0000\u0180\u0185\u0003$\u0012\u0000\u0181\u0182\u0005\f\u0000\u0000"+
		"\u0182\u0184\u0003$\u0012\u0000\u0183\u0181\u0001\u0000\u0000\u0000\u0184"+
		"\u0187\u0001\u0000\u0000\u0000\u0185\u0183\u0001\u0000\u0000\u0000\u0185"+
		"\u0186\u0001\u0000\u0000\u0000\u0186\u0189\u0001\u0000\u0000\u0000\u0187"+
		"\u0185\u0001\u0000\u0000\u0000\u0188\u0180\u0001\u0000\u0000\u0000\u0188"+
		"\u0189\u0001\u0000\u0000\u0000\u0189\u018a\u0001\u0000\u0000\u0000\u018a"+
		"\u018b\u0005\u0005\u0000\u0000\u018b+\u0001\u0000\u0000\u0000\u018c\u018d"+
		"\u00032\u0019\u0000\u018d\u018e\u0005\u0010\u0000\u0000\u018e\u018f\u0003"+
		"$\u0012\u0000\u018f\u01b3\u0001\u0000\u0000\u0000\u0190\u0191\u00032\u0019"+
		"\u0000\u0191\u0192\u0005\u0011\u0000\u0000\u0192\u01b3\u0001\u0000\u0000"+
		"\u0000\u0193\u0194\u00032\u0019\u0000\u0194\u0195\u0005\u0012\u0000\u0000"+
		"\u0195\u01b3\u0001\u0000\u0000\u0000\u0196\u0197\u00032\u0019\u0000\u0197"+
		"\u0198\u0005\u0013\u0000\u0000\u0198\u0199\u0003$\u0012\u0000\u0199\u01b3"+
		"\u0001\u0000\u0000\u0000\u019a\u019b\u00032\u0019\u0000\u019b\u019c\u0005"+
		"\u0014\u0000\u0000\u019c\u019d\u0003$\u0012\u0000\u019d\u01b3\u0001\u0000"+
		"\u0000\u0000\u019e\u019f\u00032\u0019\u0000\u019f\u01a0\u0005\u0015\u0000"+
		"\u0000\u01a0\u01a1\u0003$\u0012\u0000\u01a1\u01b3\u0001\u0000\u0000\u0000"+
		"\u01a2\u01a3\u00032\u0019\u0000\u01a3\u01a4\u0005\u0016\u0000\u0000\u01a4"+
		"\u01a5\u0003$\u0012\u0000\u01a5\u01b3\u0001\u0000\u0000\u0000\u01a6\u01a7"+
		"\u00032\u0019\u0000\u01a7\u01a8\u0005\u0017\u0000\u0000\u01a8\u01a9\u0003"+
		"$\u0012\u0000\u01a9\u01b3\u0001\u0000\u0000\u0000\u01aa\u01ab\u00032\u0019"+
		"\u0000\u01ab\u01ac\u0005\u0018\u0000\u0000\u01ac\u01ad\u0003$\u0012\u0000"+
		"\u01ad\u01b3\u0001\u0000\u0000\u0000\u01ae\u01af\u00032\u0019\u0000\u01af"+
		"\u01b0\u0005\u0019\u0000\u0000\u01b0\u01b1\u0003$\u0012\u0000\u01b1\u01b3"+
		"\u0001\u0000\u0000\u0000\u01b2\u018c\u0001\u0000\u0000\u0000\u01b2\u0190"+
		"\u0001\u0000\u0000\u0000\u01b2\u0193\u0001\u0000\u0000\u0000\u01b2\u0196"+
		"\u0001\u0000\u0000\u0000\u01b2\u019a\u0001\u0000\u0000\u0000\u01b2\u019e"+
		"\u0001\u0000\u0000\u0000\u01b2\u01a2\u0001\u0000\u0000\u0000\u01b2\u01a6"+
		"\u0001\u0000\u0000\u0000\u01b2\u01aa\u0001\u0000\u0000\u0000\u01b2\u01ae"+
		"\u0001\u0000\u0000\u0000\u01b3-\u0001\u0000\u0000\u0000\u01b4\u01b5\u0003"+
		"\n\u0005\u0000\u01b5\u01b6\u0005\u0010\u0000\u0000\u01b6\u01b7\u0003$"+
		"\u0012\u0000\u01b7/\u0001\u0000\u0000\u0000\u01b8\u01bb\u0003\n\u0005"+
		"\u0000\u01b9\u01bb\u0003.\u0017\u0000\u01ba\u01b8\u0001\u0000\u0000\u0000"+
		"\u01ba\u01b9\u0001\u0000\u0000\u0000\u01bb1\u0001\u0000\u0000\u0000\u01bc"+
		"\u01c8\u00034\u001a\u0000\u01bd\u01be\u00034\u001a\u0000\u01be\u01bf\u0005"+
		" \u0000\u0000\u01bf\u01c0\u0003$\u0012\u0000\u01c0\u01c1\u0005\u001f\u0000"+
		"\u0000\u01c1\u01c8\u0001\u0000\u0000\u0000\u01c2\u01c3\u00034\u001a\u0000"+
		"\u01c3\u01c4\u0005\u0006\u0000\u0000\u01c4\u01c5\u0003$\u0012\u0000\u01c5"+
		"\u01c6\u0005\u0007\u0000\u0000\u01c6\u01c8\u0001\u0000\u0000\u0000\u01c7"+
		"\u01bc\u0001\u0000\u0000\u0000\u01c7\u01bd\u0001\u0000\u0000\u0000\u01c7"+
		"\u01c2\u0001\u0000\u0000\u0000\u01c83\u0001\u0000\u0000\u0000\u01c9\u01ca"+
		"\u0005H\u0000\u0000\u01ca5\u0001\u0000\u0000\u0000\u01cb\u01cc\u0005I"+
		"\u0000\u0000\u01cc7\u0001\u0000\u0000\u0000\u01cd\u01ce\u0005\u001c\u0000"+
		"\u0000\u01ce\u01cf\u00034\u001a\u0000\u01cf\u01d0\u00036\u001b\u0000\u01d0"+
		"9\u0001\u0000\u0000\u0000\u01d1\u01d8\u0005E\u0000\u0000\u01d2\u01d8\u0005"+
		"F\u0000\u0000\u01d3\u01d8\u0005B\u0000\u0000\u01d4\u01d8\u0003<\u001e"+
		"\u0000\u01d5\u01d8\u0005?\u0000\u0000\u01d6\u01d8\u0003>\u001f\u0000\u01d7"+
		"\u01d1\u0001\u0000\u0000\u0000\u01d7\u01d2\u0001\u0000\u0000\u0000\u01d7"+
		"\u01d3\u0001\u0000\u0000\u0000\u01d7\u01d4\u0001\u0000\u0000\u0000\u01d7"+
		"\u01d5\u0001\u0000\u0000\u0000\u01d7\u01d6\u0001\u0000\u0000\u0000\u01d8"+
		";\u0001\u0000\u0000\u0000\u01d9\u01dc\u0005A\u0000\u0000\u01da\u01dc\u0005"+
		"@\u0000\u0000\u01db\u01d9\u0001\u0000\u0000\u0000\u01db\u01da\u0001\u0000"+
		"\u0000\u0000\u01dc=\u0001\u0000\u0000\u0000\u01dd\u01e0\u0005<\u0000\u0000"+
		"\u01de\u01e0\u0005=\u0000\u0000\u01df\u01dd\u0001\u0000\u0000\u0000\u01df"+
		"\u01de\u0001\u0000\u0000\u0000\u01e0?\u0001\u0000\u0000\u0000\"EOSX^e"+
		"i\u0081\u008c\u008e\u009a\u009d\u00a4\u00a8\u00c0\u00c4\u00d6\u00f2\u00f7"+
		"\u011d\u0128\u0133\u0151\u016e\u0170\u0178\u0185\u0188\u01b2\u01ba\u01c7"+
		"\u01d7\u01db\u01df";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}