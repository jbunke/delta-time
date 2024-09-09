// Generated from C:/Users/Jordan Bunke/Documents/Java/2022/delta-time/script/deltascript/grammars/ScriptParser.g4 by ANTLR 4.13.1
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
		INT=49, CHAR=50, COLOR=51, IMAGE=52, STRING=53, RETURN=54, DO=55, NEW=56, 
		WHILE=57, FOR=58, IF=59, ELSE=60, TRUE=61, FALSE=62, WHEN=63, IS=64, MATCHES=65, 
		PASSES=66, OTHERWISE=67, FLOAT_LIT=68, DEC_LIT=69, HEX_LIT=70, COL_LIT=71, 
		CHAR_QUOTE=72, STR_QUOTE=73, STRING_LIT=74, CHAR_LIT=75, ESC_CHAR=76, 
		IDENTIFIER=77, SUB_IDENT=78;
	public static final int
		RULE_head_rule = 0, RULE_helper = 1, RULE_func_body = 2, RULE_signature = 3, 
		RULE_param_list = 4, RULE_declaration = 5, RULE_type = 6, RULE_func_type = 7, 
		RULE_param_types = 8, RULE_body = 9, RULE_stat = 10, RULE_return_stat = 11, 
		RULE_loop_stat = 12, RULE_iteration_def = 13, RULE_iterator_declaration = 14, 
		RULE_while_def = 15, RULE_for_def = 16, RULE_if_stat = 17, RULE_if_def = 18, 
		RULE_when_stat = 19, RULE_when_body = 20, RULE_case = 21, RULE_otherwise = 22, 
		RULE_expr = 23, RULE_lambda_params = 24, RULE_lambda_body = 25, RULE_k_v_pairs = 26, 
		RULE_k_v_pair = 27, RULE_args = 28, RULE_elements = 29, RULE_assignment = 30, 
		RULE_var_init = 31, RULE_var_def = 32, RULE_assignable = 33, RULE_ident = 34, 
		RULE_subident = 35, RULE_namespace = 36, RULE_literal = 37, RULE_int_lit = 38, 
		RULE_bool_lit = 39;
	private static String[] makeRuleNames() {
		return new String[] {
			"head_rule", "helper", "func_body", "signature", "param_list", "declaration", 
			"type", "func_type", "param_types", "body", "stat", "return_stat", "loop_stat", 
			"iteration_def", "iterator_declaration", "while_def", "for_def", "if_stat", 
			"if_def", "when_stat", "when_body", "case", "otherwise", "expr", "lambda_params", 
			"lambda_body", "k_v_pairs", "k_v_pair", "args", "elements", "assignment", 
			"var_init", "var_def", "assignable", "ident", "subident", "namespace", 
			"literal", "int_lit", "bool_lit"
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
			"'new'", "'while'", "'for'", "'if'", "'else'", "'true'", "'false'", "'when'", 
			"'is'", "'matches'", "'passes'", "'otherwise'", null, null, null, null, 
			"'''", "'\"'"
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
			"COLOR", "IMAGE", "STRING", "RETURN", "DO", "NEW", "WHILE", "FOR", "IF", 
			"ELSE", "TRUE", "FALSE", "WHEN", "IS", "MATCHES", "PASSES", "OTHERWISE", 
			"FLOAT_LIT", "DEC_LIT", "HEX_LIT", "COL_LIT", "CHAR_QUOTE", "STR_QUOTE", 
			"STRING_LIT", "CHAR_LIT", "ESC_CHAR", "IDENTIFIER", "SUB_IDENT"
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
			setState(80);
			signature();
			setState(81);
			func_body();
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IDENTIFIER) {
				{
				{
				setState(82);
				helper();
				}
				}
				setState(87);
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
			setState(88);
			ident();
			setState(89);
			signature();
			setState(90);
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
			setState(95);
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
			case NEW:
			case WHILE:
			case FOR:
			case IF:
			case TRUE:
			case FALSE:
			case WHEN:
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
				setState(92);
				body();
				}
				break;
			case ARROW:
				_localctx = new FunctionalFuncBodyContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(93);
				match(ARROW);
				setState(94);
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
			setState(110);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				_localctx = new VoidReturnSignatureContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(97);
				match(LPAREN);
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 17944029765304592L) != 0) || _la==IDENTIFIER) {
					{
					setState(98);
					param_list();
					}
				}

				setState(101);
				match(RPAREN);
				}
				break;
			case 2:
				_localctx = new TypeReturnSignatureContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(102);
				match(LPAREN);
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 17944029765304592L) != 0) || _la==IDENTIFIER) {
					{
					setState(103);
					param_list();
					}
				}

				setState(106);
				match(ARROW);
				setState(107);
				type(0);
				setState(108);
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
			setState(112);
			declaration();
			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(113);
				match(COMMA);
				setState(114);
				declaration();
				}
				}
				setState(119);
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
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FINAL) {
				{
				setState(120);
				match(FINAL);
				}
			}

			setState(123);
			type(0);
			setState(124);
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
			setState(145);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL:
				{
				_localctx = new BoolTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(127);
				match(BOOL);
				}
				break;
			case INT:
				{
				_localctx = new IntTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(128);
				match(INT);
				}
				break;
			case FLOAT:
				{
				_localctx = new FloatTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(129);
				match(FLOAT);
				}
				break;
			case CHAR:
				{
				_localctx = new CharTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(130);
				match(CHAR);
				}
				break;
			case STRING:
				{
				_localctx = new StringTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(131);
				match(STRING);
				}
				break;
			case IMAGE:
				{
				_localctx = new ImageTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(132);
				match(IMAGE);
				}
				break;
			case COLOR:
				{
				_localctx = new ColorTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(133);
				match(COLOR);
				}
				break;
			case LCURLY:
				{
				_localctx = new MapTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(134);
				match(LCURLY);
				setState(135);
				((MapTypeContext)_localctx).key = type(0);
				setState(136);
				match(COLON);
				setState(137);
				((MapTypeContext)_localctx).val = type(0);
				setState(138);
				match(RCURLY);
				}
				break;
			case LPAREN:
				{
				_localctx = new FunctionTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(140);
				match(LPAREN);
				setState(141);
				func_type();
				setState(142);
				match(RPAREN);
				}
				break;
			case IDENTIFIER:
				{
				_localctx = new ExtensionTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(144);
				ident();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(158);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(156);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new ArrayTypeContext(new TypeContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_type);
						setState(147);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(148);
						match(LBRACKET);
						setState(149);
						match(RBRACKET);
						}
						break;
					case 2:
						{
						_localctx = new ListTypeContext(new TypeContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_type);
						setState(150);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(151);
						match(LT);
						setState(152);
						match(GT);
						}
						break;
					case 3:
						{
						_localctx = new SetTypeContext(new TypeContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_type);
						setState(153);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(154);
						match(LCURLY);
						setState(155);
						match(RCURLY);
						}
						break;
					}
					} 
				}
				setState(160);
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
			setState(161);
			param_types();
			setState(162);
			match(ARROW);
			setState(163);
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
			setState(173);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 17873661021126928L) != 0) || _la==IDENTIFIER) {
				{
				setState(165);
				type(0);
				setState(170);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(166);
					match(COMMA);
					setState(167);
					type(0);
					}
					}
					setState(172);
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
			setState(184);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				_localctx = new SingleStatBodyContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(175);
				stat();
				}
				break;
			case 2:
				_localctx = new ComplexBodyContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(176);
				match(LCURLY);
				setState(180);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -1152965342935383728L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 719L) != 0)) {
					{
					{
					setState(177);
					stat();
					}
					}
					setState(182);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(183);
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
	public static class WhenStatementContext extends StatContext {
		public When_statContext when_stat() {
			return getRuleContext(When_statContext.class,0);
		}
		public WhenStatementContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitWhenStatement(this);
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
			setState(209);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				_localctx = new LoopStatementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(186);
				loop_stat();
				}
				break;
			case 2:
				_localctx = new IfStatementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(187);
				if_stat();
				}
				break;
			case 3:
				_localctx = new WhenStatementContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(188);
				when_stat();
				}
				break;
			case 4:
				_localctx = new VarDefStatementContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(189);
				var_def();
				setState(190);
				match(SEMICOLON);
				}
				break;
			case 5:
				_localctx = new AssignmentStatementContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(192);
				assignment();
				setState(193);
				match(SEMICOLON);
				}
				break;
			case 6:
				_localctx = new ReturnStatementContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(195);
				return_stat();
				}
				break;
			case 7:
				_localctx = new ScopedFuncCallStatementContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(196);
				expr(0);
				setState(197);
				subident();
				setState(198);
				args();
				setState(199);
				match(SEMICOLON);
				}
				break;
			case 8:
				_localctx = new FunctionCallStatementContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(201);
				ident();
				setState(202);
				args();
				setState(203);
				match(SEMICOLON);
				}
				break;
			case 9:
				_localctx = new ExtFuncCallStatementContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(205);
				namespace();
				setState(206);
				args();
				setState(207);
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
			setState(211);
			match(RETURN);
			setState(213);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6989613152094650704L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 719L) != 0)) {
				{
				setState(212);
				expr(0);
				}
			}

			setState(215);
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
			setState(231);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				_localctx = new WhileLoopContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(217);
				while_def();
				setState(218);
				body();
				}
				break;
			case 2:
				_localctx = new IteratorLoopContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(220);
				iteration_def();
				setState(221);
				body();
				}
				break;
			case 3:
				_localctx = new ForLoopContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(223);
				for_def();
				setState(224);
				body();
				}
				break;
			case 4:
				_localctx = new DoWhileLoopContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(226);
				match(DO);
				setState(227);
				body();
				setState(228);
				while_def();
				setState(229);
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
		public Iterator_declarationContext iterator_declaration() {
			return getRuleContext(Iterator_declarationContext.class,0);
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
			setState(233);
			match(FOR);
			setState(234);
			match(LPAREN);
			setState(235);
			iterator_declaration();
			setState(236);
			match(IN);
			setState(237);
			expr(0);
			setState(238);
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
	public static class Iterator_declarationContext extends ParserRuleContext {
		public Iterator_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iterator_declaration; }
	 
		public Iterator_declarationContext() { }
		public void copyFrom(Iterator_declarationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ImplicitDeclarationContext extends Iterator_declarationContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public ImplicitDeclarationContext(Iterator_declarationContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitImplicitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExplicitDeclarationContext extends Iterator_declarationContext {
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public ExplicitDeclarationContext(Iterator_declarationContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitExplicitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Iterator_declarationContext iterator_declaration() throws RecognitionException {
		Iterator_declarationContext _localctx = new Iterator_declarationContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_iterator_declaration);
		try {
			setState(242);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				_localctx = new ExplicitDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(240);
				declaration();
				}
				break;
			case 2:
				_localctx = new ImplicitDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(241);
				ident();
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
		enterRule(_localctx, 30, RULE_while_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			match(WHILE);
			setState(245);
			match(LPAREN);
			setState(246);
			expr(0);
			setState(247);
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
		enterRule(_localctx, 32, RULE_for_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			match(FOR);
			setState(250);
			match(LPAREN);
			setState(251);
			var_init();
			setState(252);
			match(SEMICOLON);
			setState(253);
			expr(0);
			setState(254);
			match(SEMICOLON);
			setState(255);
			assignment();
			setState(256);
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
		enterRule(_localctx, 34, RULE_if_stat);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			if_def();
			setState(263);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(259);
					match(ELSE);
					setState(260);
					if_def();
					}
					} 
				}
				setState(265);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			setState(268);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(266);
				match(ELSE);
				setState(267);
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
		enterRule(_localctx, 36, RULE_if_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270);
			match(IF);
			setState(271);
			match(LPAREN);
			setState(272);
			((If_defContext)_localctx).cond = expr(0);
			setState(273);
			match(RPAREN);
			setState(274);
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
	public static class When_statContext extends ParserRuleContext {
		public ExprContext control;
		public TerminalNode WHEN() { return getToken(ScriptParser.WHEN, 0); }
		public TerminalNode LPAREN() { return getToken(ScriptParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ScriptParser.RPAREN, 0); }
		public When_bodyContext when_body() {
			return getRuleContext(When_bodyContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public When_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_when_stat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitWhen_stat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final When_statContext when_stat() throws RecognitionException {
		When_statContext _localctx = new When_statContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_when_stat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			match(WHEN);
			setState(277);
			match(LPAREN);
			setState(278);
			((When_statContext)_localctx).control = expr(0);
			setState(279);
			match(RPAREN);
			setState(280);
			when_body();
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
	public static class When_bodyContext extends ParserRuleContext {
		public TerminalNode LCURLY() { return getToken(ScriptParser.LCURLY, 0); }
		public TerminalNode RCURLY() { return getToken(ScriptParser.RCURLY, 0); }
		public List<CaseContext> case_() {
			return getRuleContexts(CaseContext.class);
		}
		public CaseContext case_(int i) {
			return getRuleContext(CaseContext.class,i);
		}
		public OtherwiseContext otherwise() {
			return getRuleContext(OtherwiseContext.class,0);
		}
		public When_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_when_body; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitWhen_body(this);
			else return visitor.visitChildren(this);
		}
	}

	public final When_bodyContext when_body() throws RecognitionException {
		When_bodyContext _localctx = new When_bodyContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_when_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			match(LCURLY);
			setState(284); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(283);
				case_();
				}
				}
				setState(286); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 7L) != 0) );
			setState(289);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OTHERWISE) {
				{
				setState(288);
				otherwise();
				}
			}

			setState(291);
			match(RCURLY);
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
	public static class CaseContext extends ParserRuleContext {
		public CaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_case; }
	 
		public CaseContext() { }
		public void copyFrom(CaseContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MatchesCaseContext extends CaseContext {
		public TerminalNode MATCHES() { return getToken(ScriptParser.MATCHES, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ARROW() { return getToken(ScriptParser.ARROW, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public MatchesCaseContext(CaseContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitMatchesCase(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IsCaseContext extends CaseContext {
		public TerminalNode IS() { return getToken(ScriptParser.IS, 0); }
		public ElementsContext elements() {
			return getRuleContext(ElementsContext.class,0);
		}
		public TerminalNode ARROW() { return getToken(ScriptParser.ARROW, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public IsCaseContext(CaseContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitIsCase(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PassesCaseContext extends CaseContext {
		public TerminalNode PASSES() { return getToken(ScriptParser.PASSES, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ARROW() { return getToken(ScriptParser.ARROW, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public PassesCaseContext(CaseContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitPassesCase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseContext case_() throws RecognitionException {
		CaseContext _localctx = new CaseContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_case);
		try {
			setState(308);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IS:
				_localctx = new IsCaseContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(293);
				match(IS);
				setState(294);
				elements();
				setState(295);
				match(ARROW);
				setState(296);
				body();
				}
				break;
			case MATCHES:
				_localctx = new MatchesCaseContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(298);
				match(MATCHES);
				setState(299);
				expr(0);
				setState(300);
				match(ARROW);
				setState(301);
				body();
				}
				break;
			case PASSES:
				_localctx = new PassesCaseContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(303);
				match(PASSES);
				setState(304);
				expr(0);
				setState(305);
				match(ARROW);
				setState(306);
				body();
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
	public static class OtherwiseContext extends ParserRuleContext {
		public TerminalNode OTHERWISE() { return getToken(ScriptParser.OTHERWISE, 0); }
		public TerminalNode ARROW() { return getToken(ScriptParser.ARROW, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public OtherwiseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_otherwise; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitOtherwise(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OtherwiseContext otherwise() throws RecognitionException {
		OtherwiseContext _localctx = new OtherwiseContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_otherwise);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(310);
			match(OTHERWISE);
			setState(311);
			match(ARROW);
			setState(312);
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
		public TerminalNode RBRACKET() { return getToken(ScriptParser.RBRACKET, 0); }
		public ElementsContext elements() {
			return getRuleContext(ElementsContext.class,0);
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
	public static class ExtPropertyExpressionContext extends ExprContext {
		public NamespaceContext namespace() {
			return getRuleContext(NamespaceContext.class,0);
		}
		public ExtPropertyExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitExtPropertyExpression(this);
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
		public TerminalNode GT() { return getToken(ScriptParser.GT, 0); }
		public ElementsContext elements() {
			return getRuleContext(ElementsContext.class,0);
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
		public TerminalNode RCURLY() { return getToken(ScriptParser.RCURLY, 0); }
		public ElementsContext elements() {
			return getRuleContext(ElementsContext.class,0);
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
	public static class LambdaFunctionExpressionContext extends ExprContext {
		public Lambda_paramsContext lambda_params() {
			return getRuleContext(Lambda_paramsContext.class,0);
		}
		public Lambda_bodyContext lambda_body() {
			return getRuleContext(Lambda_bodyContext.class,0);
		}
		public LambdaFunctionExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitLambdaFunctionExpression(this);
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
		int _startState = 46;
		enterRecursionRule(_localctx, 46, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(372);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				{
				_localctx = new NestedExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(315);
				match(LPAREN);
				setState(316);
				expr(0);
				setState(317);
				match(RPAREN);
				}
				break;
			case 2:
				{
				_localctx = new LambdaFunctionExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(319);
				lambda_params();
				setState(320);
				lambda_body();
				}
				break;
			case 3:
				{
				_localctx = new FunctionCallExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(322);
				ident();
				setState(323);
				args();
				}
				break;
			case 4:
				{
				_localctx = new ExtFuncCallExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(325);
				namespace();
				setState(326);
				args();
				}
				break;
			case 5:
				{
				_localctx = new ExtPropertyExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(328);
				namespace();
				}
				break;
			case 6:
				{
				_localctx = new HOFuncExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(329);
				match(DEF);
				setState(330);
				ident();
				}
				break;
			case 7:
				{
				_localctx = new UnaryExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(331);
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
				setState(332);
				expr(16);
				}
				break;
			case 8:
				{
				_localctx = new CastExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(333);
				match(LPAREN);
				setState(334);
				type(0);
				setState(335);
				match(RPAREN);
				setState(336);
				expr(15);
				}
				break;
			case 9:
				{
				_localctx = new ExplicitMapExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(338);
				match(LCURLY);
				setState(339);
				k_v_pairs();
				setState(340);
				match(RCURLY);
				}
				break;
			case 10:
				{
				_localctx = new ExplicitArrayExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(342);
				match(LBRACKET);
				setState(344);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6989613152094650704L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 719L) != 0)) {
					{
					setState(343);
					elements();
					}
				}

				setState(346);
				match(RBRACKET);
				}
				break;
			case 11:
				{
				_localctx = new ExplicitListExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(347);
				match(LT);
				setState(349);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6989613152094650704L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 719L) != 0)) {
					{
					setState(348);
					elements();
					}
				}

				setState(351);
				match(GT);
				}
				break;
			case 12:
				{
				_localctx = new ExplicitSetExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(352);
				match(LCURLY);
				setState(354);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6989613152094650704L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 719L) != 0)) {
					{
					setState(353);
					elements();
					}
				}

				setState(356);
				match(RCURLY);
				}
				break;
			case 13:
				{
				_localctx = new NewArrayExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(357);
				match(NEW);
				setState(358);
				type(0);
				setState(359);
				match(LBRACKET);
				setState(360);
				expr(0);
				setState(361);
				match(RBRACKET);
				}
				break;
			case 14:
				{
				_localctx = new NewMapExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(363);
				match(NEW);
				setState(364);
				match(LCURLY);
				setState(365);
				((NewMapExpressionContext)_localctx).kt = type(0);
				setState(366);
				match(COLON);
				setState(367);
				((NewMapExpressionContext)_localctx).vt = type(0);
				setState(368);
				match(RCURLY);
				}
				break;
			case 15:
				{
				_localctx = new AssignableExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(370);
				assignable();
				}
				break;
			case 16:
				{
				_localctx = new LiteralExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(371);
				literal();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(403);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(401);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
					case 1:
						{
						_localctx = new ArithmeticBinExpressionContext(new ExprContext(_parentctx, _parentState));
						((ArithmeticBinExpressionContext)_localctx).a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(374);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(375);
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
						setState(376);
						((ArithmeticBinExpressionContext)_localctx).b = expr(15);
						}
						break;
					case 2:
						{
						_localctx = new MultBinExpressionContext(new ExprContext(_parentctx, _parentState));
						((MultBinExpressionContext)_localctx).a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(377);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(378);
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
						setState(379);
						((MultBinExpressionContext)_localctx).b = expr(14);
						}
						break;
					case 3:
						{
						_localctx = new PowerBinExpressionContext(new ExprContext(_parentctx, _parentState));
						((PowerBinExpressionContext)_localctx).a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(380);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(381);
						match(RAISE);
						setState(382);
						((PowerBinExpressionContext)_localctx).b = expr(13);
						}
						break;
					case 4:
						{
						_localctx = new ComparisonBinExpressionContext(new ExprContext(_parentctx, _parentState));
						((ComparisonBinExpressionContext)_localctx).a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(383);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(384);
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
						setState(385);
						((ComparisonBinExpressionContext)_localctx).b = expr(12);
						}
						break;
					case 5:
						{
						_localctx = new LogicBinExpressionContext(new ExprContext(_parentctx, _parentState));
						((LogicBinExpressionContext)_localctx).a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(386);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(387);
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
						setState(388);
						((LogicBinExpressionContext)_localctx).b = expr(11);
						}
						break;
					case 6:
						{
						_localctx = new TernaryExpressionContext(new ExprContext(_parentctx, _parentState));
						((TernaryExpressionContext)_localctx).cond = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(389);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(390);
						match(QUESTION);
						setState(391);
						((TernaryExpressionContext)_localctx).if_ = expr(0);
						setState(392);
						match(COLON);
						setState(393);
						((TernaryExpressionContext)_localctx).else_ = expr(10);
						}
						break;
					case 7:
						{
						_localctx = new ScopedFuncCallExpressionContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(395);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(396);
						subident();
						setState(397);
						args();
						}
						break;
					case 8:
						{
						_localctx = new PropertyExpressionContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(399);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(400);
						subident();
						}
						break;
					}
					} 
				}
				setState(405);
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
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Lambda_paramsContext extends ParserRuleContext {
		public Lambda_paramsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambda_params; }
	 
		public Lambda_paramsContext() { }
		public void copyFrom(Lambda_paramsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MultLambdaParamsContext extends Lambda_paramsContext {
		public TerminalNode LPAREN() { return getToken(ScriptParser.LPAREN, 0); }
		public List<IdentContext> ident() {
			return getRuleContexts(IdentContext.class);
		}
		public IdentContext ident(int i) {
			return getRuleContext(IdentContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(ScriptParser.RPAREN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(ScriptParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ScriptParser.COMMA, i);
		}
		public MultLambdaParamsContext(Lambda_paramsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitMultLambdaParams(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OneLambdaParamContext extends Lambda_paramsContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public OneLambdaParamContext(Lambda_paramsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitOneLambdaParam(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NoLambdaParamsContext extends Lambda_paramsContext {
		public TerminalNode LPAREN() { return getToken(ScriptParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ScriptParser.RPAREN, 0); }
		public NoLambdaParamsContext(Lambda_paramsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitNoLambdaParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Lambda_paramsContext lambda_params() throws RecognitionException {
		Lambda_paramsContext _localctx = new Lambda_paramsContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_lambda_params);
		int _la;
		try {
			setState(419);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				_localctx = new NoLambdaParamsContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(406);
				match(LPAREN);
				setState(407);
				match(RPAREN);
				}
				break;
			case 2:
				_localctx = new OneLambdaParamContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(408);
				ident();
				}
				break;
			case 3:
				_localctx = new MultLambdaParamsContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(409);
				match(LPAREN);
				setState(410);
				ident();
				setState(413); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(411);
					match(COMMA);
					setState(412);
					ident();
					}
					}
					setState(415); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==COMMA );
				setState(417);
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
	public static class Lambda_bodyContext extends ParserRuleContext {
		public Lambda_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambda_body; }
	 
		public Lambda_bodyContext() { }
		public void copyFrom(Lambda_bodyContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StandardLambdaBodyContext extends Lambda_bodyContext {
		public TerminalNode ARROW() { return getToken(ScriptParser.ARROW, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public StandardLambdaBodyContext(Lambda_bodyContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitStandardLambdaBody(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprLambdaBodyContext extends Lambda_bodyContext {
		public TerminalNode ARROW() { return getToken(ScriptParser.ARROW, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExprLambdaBodyContext(Lambda_bodyContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitExprLambdaBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Lambda_bodyContext lambda_body() throws RecognitionException {
		Lambda_bodyContext _localctx = new Lambda_bodyContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_lambda_body);
		try {
			setState(425);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				_localctx = new StandardLambdaBodyContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(421);
				match(ARROW);
				setState(422);
				body();
				}
				break;
			case 2:
				_localctx = new ExprLambdaBodyContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(423);
				match(ARROW);
				setState(424);
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
		enterRule(_localctx, 52, RULE_k_v_pairs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(427);
			k_v_pair();
			setState(432);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(428);
				match(COMMA);
				setState(429);
				k_v_pair();
				}
				}
				setState(434);
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
		enterRule(_localctx, 54, RULE_k_v_pair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(435);
			((K_v_pairContext)_localctx).key = expr(0);
			setState(436);
			match(COLON);
			setState(437);
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
		public ElementsContext elements() {
			return getRuleContext(ElementsContext.class,0);
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
		enterRule(_localctx, 56, RULE_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(439);
			match(LPAREN);
			setState(441);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6989613152094650704L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 719L) != 0)) {
				{
				setState(440);
				elements();
				}
			}

			setState(443);
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
	public static class ElementsContext extends ParserRuleContext {
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
		public ElementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elements; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptParserVisitor ) return ((ScriptParserVisitor<? extends T>)visitor).visitElements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementsContext elements() throws RecognitionException {
		ElementsContext _localctx = new ElementsContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_elements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(445);
			expr(0);
			setState(450);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(446);
				match(COMMA);
				setState(447);
				expr(0);
				}
				}
				setState(452);
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
		enterRule(_localctx, 60, RULE_assignment);
		try {
			setState(491);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				_localctx = new StandardAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(453);
				assignable();
				setState(454);
				match(ASSIGN);
				setState(455);
				expr(0);
				}
				break;
			case 2:
				_localctx = new IncrementAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(457);
				assignable();
				setState(458);
				match(INCREMENT);
				}
				break;
			case 3:
				_localctx = new DecrementAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(460);
				assignable();
				setState(461);
				match(DECREMENT);
				}
				break;
			case 4:
				_localctx = new AddAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(463);
				assignable();
				setState(464);
				match(ADD_ASSIGN);
				setState(465);
				expr(0);
				}
				break;
			case 5:
				_localctx = new SubAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(467);
				assignable();
				setState(468);
				match(SUB_ASSIGN);
				setState(469);
				expr(0);
				}
				break;
			case 6:
				_localctx = new MultAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(471);
				assignable();
				setState(472);
				match(MUL_ASSIGN);
				setState(473);
				expr(0);
				}
				break;
			case 7:
				_localctx = new DivAssignmnetContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(475);
				assignable();
				setState(476);
				match(DIV_ASSIGN);
				setState(477);
				expr(0);
				}
				break;
			case 8:
				_localctx = new ModAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(479);
				assignable();
				setState(480);
				match(MOD_ASSIGN);
				setState(481);
				expr(0);
				}
				break;
			case 9:
				_localctx = new AndAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(483);
				assignable();
				setState(484);
				match(AND_ASSIGN);
				setState(485);
				expr(0);
				}
				break;
			case 10:
				_localctx = new OrAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(487);
				assignable();
				setState(488);
				match(OR_ASSIGN);
				setState(489);
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
		enterRule(_localctx, 62, RULE_var_init);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(493);
			declaration();
			setState(494);
			match(ASSIGN);
			setState(495);
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
		enterRule(_localctx, 64, RULE_var_def);
		try {
			setState(499);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				_localctx = new ImplicitVarDefContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(497);
				declaration();
				}
				break;
			case 2:
				_localctx = new ExplicitVarDefContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(498);
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
		enterRule(_localctx, 66, RULE_assignable);
		try {
			setState(512);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				_localctx = new SimpleAssignableContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(501);
				ident();
				}
				break;
			case 2:
				_localctx = new ListAssignableContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(502);
				ident();
				setState(503);
				match(LT);
				setState(504);
				expr(0);
				setState(505);
				match(GT);
				}
				break;
			case 3:
				_localctx = new ArrayAssignableContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(507);
				ident();
				setState(508);
				match(LBRACKET);
				setState(509);
				expr(0);
				setState(510);
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
		enterRule(_localctx, 68, RULE_ident);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(514);
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
		enterRule(_localctx, 70, RULE_subident);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(516);
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
		enterRule(_localctx, 72, RULE_namespace);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(518);
			match(EXTENSION);
			setState(519);
			ident();
			setState(520);
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
		enterRule(_localctx, 74, RULE_literal);
		try {
			setState(528);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING_LIT:
				_localctx = new StringLiteralContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(522);
				match(STRING_LIT);
				}
				break;
			case CHAR_LIT:
				_localctx = new CharLiteralContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(523);
				match(CHAR_LIT);
				}
				break;
			case COL_LIT:
				_localctx = new ColorLiteralContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(524);
				match(COL_LIT);
				}
				break;
			case DEC_LIT:
			case HEX_LIT:
				_localctx = new IntLiteralContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(525);
				int_lit();
				}
				break;
			case FLOAT_LIT:
				_localctx = new FloatLiteralContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(526);
				match(FLOAT_LIT);
				}
				break;
			case TRUE:
			case FALSE:
				_localctx = new BoolLiteralContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(527);
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
		enterRule(_localctx, 76, RULE_int_lit);
		try {
			setState(532);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case HEX_LIT:
				_localctx = new HexadecimalContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(530);
				match(HEX_LIT);
				}
				break;
			case DEC_LIT:
				_localctx = new DecimalContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(531);
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
		enterRule(_localctx, 78, RULE_bool_lit);
		try {
			setState(536);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TRUE:
				_localctx = new TrueContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(534);
				match(TRUE);
				}
				break;
			case FALSE:
				_localctx = new FalseContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(535);
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
		case 23:
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
			return precpred(_ctx, 14);
		case 4:
			return precpred(_ctx, 13);
		case 5:
			return precpred(_ctx, 12);
		case 6:
			return precpred(_ctx, 11);
		case 7:
			return precpred(_ctx, 10);
		case 8:
			return precpred(_ctx, 9);
		case 9:
			return precpred(_ctx, 18);
		case 10:
			return precpred(_ctx, 17);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001N\u021b\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0005\u0000T\b\u0000\n\u0000\f\u0000W\t"+
		"\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0003\u0002`\b\u0002\u0001\u0003\u0001\u0003\u0003"+
		"\u0003d\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003i\b\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003o\b\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004t\b\u0004\n\u0004\f\u0004"+
		"w\t\u0004\u0001\u0005\u0003\u0005z\b\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0003\u0006\u0092\b\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0005\u0006\u009d\b\u0006\n\u0006\f\u0006\u00a0\t\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0005\b\u00a9"+
		"\b\b\n\b\f\b\u00ac\t\b\u0003\b\u00ae\b\b\u0001\t\u0001\t\u0001\t\u0005"+
		"\t\u00b3\b\t\n\t\f\t\u00b6\t\t\u0001\t\u0003\t\u00b9\b\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0003\n\u00d2\b\n\u0001\u000b\u0001\u000b\u0003"+
		"\u000b\u00d6\b\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0003\f\u00e8\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0003\u000e\u00f3\b\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011\u0106\b\u0011"+
		"\n\u0011\f\u0011\u0109\t\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u010d"+
		"\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0014\u0001\u0014\u0004\u0014\u011d\b\u0014\u000b\u0014\f"+
		"\u0014\u011e\u0001\u0014\u0003\u0014\u0122\b\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u0135\b\u0015\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u0159\b\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0003\u0017\u015e\b\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0003\u0017\u0163\b\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0003\u0017\u0175\b\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0005\u0017\u0192\b\u0017\n\u0017\f\u0017\u0195\t\u0017\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0004"+
		"\u0018\u019e\b\u0018\u000b\u0018\f\u0018\u019f\u0001\u0018\u0001\u0018"+
		"\u0003\u0018\u01a4\b\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0003\u0019\u01aa\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0005\u001a"+
		"\u01af\b\u001a\n\u001a\f\u001a\u01b2\t\u001a\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0003\u001c\u01ba\b\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0005\u001d\u01c1"+
		"\b\u001d\n\u001d\f\u001d\u01c4\t\u001d\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0003\u001e"+
		"\u01ec\b\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001 "+
		"\u0001 \u0003 \u01f4\b \u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001"+
		"!\u0001!\u0001!\u0001!\u0001!\u0003!\u0201\b!\u0001\"\u0001\"\u0001#\u0001"+
		"#\u0001$\u0001$\u0001$\u0001$\u0001%\u0001%\u0001%\u0001%\u0001%\u0001"+
		"%\u0003%\u0211\b%\u0001&\u0001&\u0003&\u0215\b&\u0001\'\u0001\'\u0003"+
		"\'\u0219\b\'\u0001\'\u0000\u0002\f.(\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDF"+
		"HJLN\u0000\u0005\u0002\u0000%%+,\u0001\u0000$%\u0001\u0000&(\u0001\u0000"+
		"\u001d\"\u0001\u0000)*\u0250\u0000P\u0001\u0000\u0000\u0000\u0002X\u0001"+
		"\u0000\u0000\u0000\u0004_\u0001\u0000\u0000\u0000\u0006n\u0001\u0000\u0000"+
		"\u0000\bp\u0001\u0000\u0000\u0000\ny\u0001\u0000\u0000\u0000\f\u0091\u0001"+
		"\u0000\u0000\u0000\u000e\u00a1\u0001\u0000\u0000\u0000\u0010\u00ad\u0001"+
		"\u0000\u0000\u0000\u0012\u00b8\u0001\u0000\u0000\u0000\u0014\u00d1\u0001"+
		"\u0000\u0000\u0000\u0016\u00d3\u0001\u0000\u0000\u0000\u0018\u00e7\u0001"+
		"\u0000\u0000\u0000\u001a\u00e9\u0001\u0000\u0000\u0000\u001c\u00f2\u0001"+
		"\u0000\u0000\u0000\u001e\u00f4\u0001\u0000\u0000\u0000 \u00f9\u0001\u0000"+
		"\u0000\u0000\"\u0102\u0001\u0000\u0000\u0000$\u010e\u0001\u0000\u0000"+
		"\u0000&\u0114\u0001\u0000\u0000\u0000(\u011a\u0001\u0000\u0000\u0000*"+
		"\u0134\u0001\u0000\u0000\u0000,\u0136\u0001\u0000\u0000\u0000.\u0174\u0001"+
		"\u0000\u0000\u00000\u01a3\u0001\u0000\u0000\u00002\u01a9\u0001\u0000\u0000"+
		"\u00004\u01ab\u0001\u0000\u0000\u00006\u01b3\u0001\u0000\u0000\u00008"+
		"\u01b7\u0001\u0000\u0000\u0000:\u01bd\u0001\u0000\u0000\u0000<\u01eb\u0001"+
		"\u0000\u0000\u0000>\u01ed\u0001\u0000\u0000\u0000@\u01f3\u0001\u0000\u0000"+
		"\u0000B\u0200\u0001\u0000\u0000\u0000D\u0202\u0001\u0000\u0000\u0000F"+
		"\u0204\u0001\u0000\u0000\u0000H\u0206\u0001\u0000\u0000\u0000J\u0210\u0001"+
		"\u0000\u0000\u0000L\u0214\u0001\u0000\u0000\u0000N\u0218\u0001\u0000\u0000"+
		"\u0000PQ\u0003\u0006\u0003\u0000QU\u0003\u0004\u0002\u0000RT\u0003\u0002"+
		"\u0001\u0000SR\u0001\u0000\u0000\u0000TW\u0001\u0000\u0000\u0000US\u0001"+
		"\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000V\u0001\u0001\u0000\u0000"+
		"\u0000WU\u0001\u0000\u0000\u0000XY\u0003D\"\u0000YZ\u0003\u0006\u0003"+
		"\u0000Z[\u0003\u0004\u0002\u0000[\u0003\u0001\u0000\u0000\u0000\\`\u0003"+
		"\u0012\t\u0000]^\u0005\u001a\u0000\u0000^`\u0003.\u0017\u0000_\\\u0001"+
		"\u0000\u0000\u0000_]\u0001\u0000\u0000\u0000`\u0005\u0001\u0000\u0000"+
		"\u0000ac\u0005\u0004\u0000\u0000bd\u0003\b\u0004\u0000cb\u0001\u0000\u0000"+
		"\u0000cd\u0001\u0000\u0000\u0000de\u0001\u0000\u0000\u0000eo\u0005\u0005"+
		"\u0000\u0000fh\u0005\u0004\u0000\u0000gi\u0003\b\u0004\u0000hg\u0001\u0000"+
		"\u0000\u0000hi\u0001\u0000\u0000\u0000ij\u0001\u0000\u0000\u0000jk\u0005"+
		"\u001a\u0000\u0000kl\u0003\f\u0006\u0000lm\u0005\u0005\u0000\u0000mo\u0001"+
		"\u0000\u0000\u0000na\u0001\u0000\u0000\u0000nf\u0001\u0000\u0000\u0000"+
		"o\u0007\u0001\u0000\u0000\u0000pu\u0003\n\u0005\u0000qr\u0005\f\u0000"+
		"\u0000rt\u0003\n\u0005\u0000sq\u0001\u0000\u0000\u0000tw\u0001\u0000\u0000"+
		"\u0000us\u0001\u0000\u0000\u0000uv\u0001\u0000\u0000\u0000v\t\u0001\u0000"+
		"\u0000\u0000wu\u0001\u0000\u0000\u0000xz\u0005.\u0000\u0000yx\u0001\u0000"+
		"\u0000\u0000yz\u0001\u0000\u0000\u0000z{\u0001\u0000\u0000\u0000{|\u0003"+
		"\f\u0006\u0000|}\u0003D\"\u0000}\u000b\u0001\u0000\u0000\u0000~\u007f"+
		"\u0006\u0006\uffff\uffff\u0000\u007f\u0092\u0005/\u0000\u0000\u0080\u0092"+
		"\u00051\u0000\u0000\u0081\u0092\u00050\u0000\u0000\u0082\u0092\u00052"+
		"\u0000\u0000\u0083\u0092\u00055\u0000\u0000\u0084\u0092\u00054\u0000\u0000"+
		"\u0085\u0092\u00053\u0000\u0000\u0086\u0087\u0005\b\u0000\u0000\u0087"+
		"\u0088\u0003\f\u0006\u0000\u0088\u0089\u0005\u000b\u0000\u0000\u0089\u008a"+
		"\u0003\f\u0006\u0000\u008a\u008b\u0005\t\u0000\u0000\u008b\u0092\u0001"+
		"\u0000\u0000\u0000\u008c\u008d\u0005\u0004\u0000\u0000\u008d\u008e\u0003"+
		"\u000e\u0007\u0000\u008e\u008f\u0005\u0005\u0000\u0000\u008f\u0092\u0001"+
		"\u0000\u0000\u0000\u0090\u0092\u0003D\"\u0000\u0091~\u0001\u0000\u0000"+
		"\u0000\u0091\u0080\u0001\u0000\u0000\u0000\u0091\u0081\u0001\u0000\u0000"+
		"\u0000\u0091\u0082\u0001\u0000\u0000\u0000\u0091\u0083\u0001\u0000\u0000"+
		"\u0000\u0091\u0084\u0001\u0000\u0000\u0000\u0091\u0085\u0001\u0000\u0000"+
		"\u0000\u0091\u0086\u0001\u0000\u0000\u0000\u0091\u008c\u0001\u0000\u0000"+
		"\u0000\u0091\u0090\u0001\u0000\u0000\u0000\u0092\u009e\u0001\u0000\u0000"+
		"\u0000\u0093\u0094\n\u0006\u0000\u0000\u0094\u0095\u0005\u0006\u0000\u0000"+
		"\u0095\u009d\u0005\u0007\u0000\u0000\u0096\u0097\n\u0005\u0000\u0000\u0097"+
		"\u0098\u0005 \u0000\u0000\u0098\u009d\u0005\u001f\u0000\u0000\u0099\u009a"+
		"\n\u0004\u0000\u0000\u009a\u009b\u0005\b\u0000\u0000\u009b\u009d\u0005"+
		"\t\u0000\u0000\u009c\u0093\u0001\u0000\u0000\u0000\u009c\u0096\u0001\u0000"+
		"\u0000\u0000\u009c\u0099\u0001\u0000\u0000\u0000\u009d\u00a0\u0001\u0000"+
		"\u0000\u0000\u009e\u009c\u0001\u0000\u0000\u0000\u009e\u009f\u0001\u0000"+
		"\u0000\u0000\u009f\r\u0001\u0000\u0000\u0000\u00a0\u009e\u0001\u0000\u0000"+
		"\u0000\u00a1\u00a2\u0003\u0010\b\u0000\u00a2\u00a3\u0005\u001a\u0000\u0000"+
		"\u00a3\u00a4\u0003\f\u0006\u0000\u00a4\u000f\u0001\u0000\u0000\u0000\u00a5"+
		"\u00aa\u0003\f\u0006\u0000\u00a6\u00a7\u0005\f\u0000\u0000\u00a7\u00a9"+
		"\u0003\f\u0006\u0000\u00a8\u00a6\u0001\u0000\u0000\u0000\u00a9\u00ac\u0001"+
		"\u0000\u0000\u0000\u00aa\u00a8\u0001\u0000\u0000\u0000\u00aa\u00ab\u0001"+
		"\u0000\u0000\u0000\u00ab\u00ae\u0001\u0000\u0000\u0000\u00ac\u00aa\u0001"+
		"\u0000\u0000\u0000\u00ad\u00a5\u0001\u0000\u0000\u0000\u00ad\u00ae\u0001"+
		"\u0000\u0000\u0000\u00ae\u0011\u0001\u0000\u0000\u0000\u00af\u00b9\u0003"+
		"\u0014\n\u0000\u00b0\u00b4\u0005\b\u0000\u0000\u00b1\u00b3\u0003\u0014"+
		"\n\u0000\u00b2\u00b1\u0001\u0000\u0000\u0000\u00b3\u00b6\u0001\u0000\u0000"+
		"\u0000\u00b4\u00b2\u0001\u0000\u0000\u0000\u00b4\u00b5\u0001\u0000\u0000"+
		"\u0000\u00b5\u00b7\u0001\u0000\u0000\u0000\u00b6\u00b4\u0001\u0000\u0000"+
		"\u0000\u00b7\u00b9\u0005\t\u0000\u0000\u00b8\u00af\u0001\u0000\u0000\u0000"+
		"\u00b8\u00b0\u0001\u0000\u0000\u0000\u00b9\u0013\u0001\u0000\u0000\u0000"+
		"\u00ba\u00d2\u0003\u0018\f\u0000\u00bb\u00d2\u0003\"\u0011\u0000\u00bc"+
		"\u00d2\u0003&\u0013\u0000\u00bd\u00be\u0003@ \u0000\u00be\u00bf\u0005"+
		"\n\u0000\u0000\u00bf\u00d2\u0001\u0000\u0000\u0000\u00c0\u00c1\u0003<"+
		"\u001e\u0000\u00c1\u00c2\u0005\n\u0000\u0000\u00c2\u00d2\u0001\u0000\u0000"+
		"\u0000\u00c3\u00d2\u0003\u0016\u000b\u0000\u00c4\u00c5\u0003.\u0017\u0000"+
		"\u00c5\u00c6\u0003F#\u0000\u00c6\u00c7\u00038\u001c\u0000\u00c7\u00c8"+
		"\u0005\n\u0000\u0000\u00c8\u00d2\u0001\u0000\u0000\u0000\u00c9\u00ca\u0003"+
		"D\"\u0000\u00ca\u00cb\u00038\u001c\u0000\u00cb\u00cc\u0005\n\u0000\u0000"+
		"\u00cc\u00d2\u0001\u0000\u0000\u0000\u00cd\u00ce\u0003H$\u0000\u00ce\u00cf"+
		"\u00038\u001c\u0000\u00cf\u00d0\u0005\n\u0000\u0000\u00d0\u00d2\u0001"+
		"\u0000\u0000\u0000\u00d1\u00ba\u0001\u0000\u0000\u0000\u00d1\u00bb\u0001"+
		"\u0000\u0000\u0000\u00d1\u00bc\u0001\u0000\u0000\u0000\u00d1\u00bd\u0001"+
		"\u0000\u0000\u0000\u00d1\u00c0\u0001\u0000\u0000\u0000\u00d1\u00c3\u0001"+
		"\u0000\u0000\u0000\u00d1\u00c4\u0001\u0000\u0000\u0000\u00d1\u00c9\u0001"+
		"\u0000\u0000\u0000\u00d1\u00cd\u0001\u0000\u0000\u0000\u00d2\u0015\u0001"+
		"\u0000\u0000\u0000\u00d3\u00d5\u00056\u0000\u0000\u00d4\u00d6\u0003.\u0017"+
		"\u0000\u00d5\u00d4\u0001\u0000\u0000\u0000\u00d5\u00d6\u0001\u0000\u0000"+
		"\u0000\u00d6\u00d7\u0001\u0000\u0000\u0000\u00d7\u00d8\u0005\n\u0000\u0000"+
		"\u00d8\u0017\u0001\u0000\u0000\u0000\u00d9\u00da\u0003\u001e\u000f\u0000"+
		"\u00da\u00db\u0003\u0012\t\u0000\u00db\u00e8\u0001\u0000\u0000\u0000\u00dc"+
		"\u00dd\u0003\u001a\r\u0000\u00dd\u00de\u0003\u0012\t\u0000\u00de\u00e8"+
		"\u0001\u0000\u0000\u0000\u00df\u00e0\u0003 \u0010\u0000\u00e0\u00e1\u0003"+
		"\u0012\t\u0000\u00e1\u00e8\u0001\u0000\u0000\u0000\u00e2\u00e3\u00057"+
		"\u0000\u0000\u00e3\u00e4\u0003\u0012\t\u0000\u00e4\u00e5\u0003\u001e\u000f"+
		"\u0000\u00e5\u00e6\u0005\n\u0000\u0000\u00e6\u00e8\u0001\u0000\u0000\u0000"+
		"\u00e7\u00d9\u0001\u0000\u0000\u0000\u00e7\u00dc\u0001\u0000\u0000\u0000"+
		"\u00e7\u00df\u0001\u0000\u0000\u0000\u00e7\u00e2\u0001\u0000\u0000\u0000"+
		"\u00e8\u0019\u0001\u0000\u0000\u0000\u00e9\u00ea\u0005:\u0000\u0000\u00ea"+
		"\u00eb\u0005\u0004\u0000\u0000\u00eb\u00ec\u0003\u001c\u000e\u0000\u00ec"+
		"\u00ed\u0005-\u0000\u0000\u00ed\u00ee\u0003.\u0017\u0000\u00ee\u00ef\u0005"+
		"\u0005\u0000\u0000\u00ef\u001b\u0001\u0000\u0000\u0000\u00f0\u00f3\u0003"+
		"\n\u0005\u0000\u00f1\u00f3\u0003D\"\u0000\u00f2\u00f0\u0001\u0000\u0000"+
		"\u0000\u00f2\u00f1\u0001\u0000\u0000\u0000\u00f3\u001d\u0001\u0000\u0000"+
		"\u0000\u00f4\u00f5\u00059\u0000\u0000\u00f5\u00f6\u0005\u0004\u0000\u0000"+
		"\u00f6\u00f7\u0003.\u0017\u0000\u00f7\u00f8\u0005\u0005\u0000\u0000\u00f8"+
		"\u001f\u0001\u0000\u0000\u0000\u00f9\u00fa\u0005:\u0000\u0000\u00fa\u00fb"+
		"\u0005\u0004\u0000\u0000\u00fb\u00fc\u0003>\u001f\u0000\u00fc\u00fd\u0005"+
		"\n\u0000\u0000\u00fd\u00fe\u0003.\u0017\u0000\u00fe\u00ff\u0005\n\u0000"+
		"\u0000\u00ff\u0100\u0003<\u001e\u0000\u0100\u0101\u0005\u0005\u0000\u0000"+
		"\u0101!\u0001\u0000\u0000\u0000\u0102\u0107\u0003$\u0012\u0000\u0103\u0104"+
		"\u0005<\u0000\u0000\u0104\u0106\u0003$\u0012\u0000\u0105\u0103\u0001\u0000"+
		"\u0000\u0000\u0106\u0109\u0001\u0000\u0000\u0000\u0107\u0105\u0001\u0000"+
		"\u0000\u0000\u0107\u0108\u0001\u0000\u0000\u0000\u0108\u010c\u0001\u0000"+
		"\u0000\u0000\u0109\u0107\u0001\u0000\u0000\u0000\u010a\u010b\u0005<\u0000"+
		"\u0000\u010b\u010d\u0003\u0012\t\u0000\u010c\u010a\u0001\u0000\u0000\u0000"+
		"\u010c\u010d\u0001\u0000\u0000\u0000\u010d#\u0001\u0000\u0000\u0000\u010e"+
		"\u010f\u0005;\u0000\u0000\u010f\u0110\u0005\u0004\u0000\u0000\u0110\u0111"+
		"\u0003.\u0017\u0000\u0111\u0112\u0005\u0005\u0000\u0000\u0112\u0113\u0003"+
		"\u0012\t\u0000\u0113%\u0001\u0000\u0000\u0000\u0114\u0115\u0005?\u0000"+
		"\u0000\u0115\u0116\u0005\u0004\u0000\u0000\u0116\u0117\u0003.\u0017\u0000"+
		"\u0117\u0118\u0005\u0005\u0000\u0000\u0118\u0119\u0003(\u0014\u0000\u0119"+
		"\'\u0001\u0000\u0000\u0000\u011a\u011c\u0005\b\u0000\u0000\u011b\u011d"+
		"\u0003*\u0015\u0000\u011c\u011b\u0001\u0000\u0000\u0000\u011d\u011e\u0001"+
		"\u0000\u0000\u0000\u011e\u011c\u0001\u0000\u0000\u0000\u011e\u011f\u0001"+
		"\u0000\u0000\u0000\u011f\u0121\u0001\u0000\u0000\u0000\u0120\u0122\u0003"+
		",\u0016\u0000\u0121\u0120\u0001\u0000\u0000\u0000\u0121\u0122\u0001\u0000"+
		"\u0000\u0000\u0122\u0123\u0001\u0000\u0000\u0000\u0123\u0124\u0005\t\u0000"+
		"\u0000\u0124)\u0001\u0000\u0000\u0000\u0125\u0126\u0005@\u0000\u0000\u0126"+
		"\u0127\u0003:\u001d\u0000\u0127\u0128\u0005\u001a\u0000\u0000\u0128\u0129"+
		"\u0003\u0012\t\u0000\u0129\u0135\u0001\u0000\u0000\u0000\u012a\u012b\u0005"+
		"A\u0000\u0000\u012b\u012c\u0003.\u0017\u0000\u012c\u012d\u0005\u001a\u0000"+
		"\u0000\u012d\u012e\u0003\u0012\t\u0000\u012e\u0135\u0001\u0000\u0000\u0000"+
		"\u012f\u0130\u0005B\u0000\u0000\u0130\u0131\u0003.\u0017\u0000\u0131\u0132"+
		"\u0005\u001a\u0000\u0000\u0132\u0133\u0003\u0012\t\u0000\u0133\u0135\u0001"+
		"\u0000\u0000\u0000\u0134\u0125\u0001\u0000\u0000\u0000\u0134\u012a\u0001"+
		"\u0000\u0000\u0000\u0134\u012f\u0001\u0000\u0000\u0000\u0135+\u0001\u0000"+
		"\u0000\u0000\u0136\u0137\u0005C\u0000\u0000\u0137\u0138\u0005\u001a\u0000"+
		"\u0000\u0138\u0139\u0003\u0012\t\u0000\u0139-\u0001\u0000\u0000\u0000"+
		"\u013a\u013b\u0006\u0017\uffff\uffff\u0000\u013b\u013c\u0005\u0004\u0000"+
		"\u0000\u013c\u013d\u0003.\u0017\u0000\u013d\u013e\u0005\u0005\u0000\u0000"+
		"\u013e\u0175\u0001\u0000\u0000\u0000\u013f\u0140\u00030\u0018\u0000\u0140"+
		"\u0141\u00032\u0019\u0000\u0141\u0175\u0001\u0000\u0000\u0000\u0142\u0143"+
		"\u0003D\"\u0000\u0143\u0144\u00038\u001c\u0000\u0144\u0175\u0001\u0000"+
		"\u0000\u0000\u0145\u0146\u0003H$\u0000\u0146\u0147\u00038\u001c\u0000"+
		"\u0147\u0175\u0001\u0000\u0000\u0000\u0148\u0175\u0003H$\u0000\u0149\u014a"+
		"\u0005\u001b\u0000\u0000\u014a\u0175\u0003D\"\u0000\u014b\u014c\u0007"+
		"\u0000\u0000\u0000\u014c\u0175\u0003.\u0017\u0010\u014d\u014e\u0005\u0004"+
		"\u0000\u0000\u014e\u014f\u0003\f\u0006\u0000\u014f\u0150\u0005\u0005\u0000"+
		"\u0000\u0150\u0151\u0003.\u0017\u000f\u0151\u0175\u0001\u0000\u0000\u0000"+
		"\u0152\u0153\u0005\b\u0000\u0000\u0153\u0154\u00034\u001a\u0000\u0154"+
		"\u0155\u0005\t\u0000\u0000\u0155\u0175\u0001\u0000\u0000\u0000\u0156\u0158"+
		"\u0005\u0006\u0000\u0000\u0157\u0159\u0003:\u001d\u0000\u0158\u0157\u0001"+
		"\u0000\u0000\u0000\u0158\u0159\u0001\u0000\u0000\u0000\u0159\u015a\u0001"+
		"\u0000\u0000\u0000\u015a\u0175\u0005\u0007\u0000\u0000\u015b\u015d\u0005"+
		" \u0000\u0000\u015c\u015e\u0003:\u001d\u0000\u015d\u015c\u0001\u0000\u0000"+
		"\u0000\u015d\u015e\u0001\u0000\u0000\u0000\u015e\u015f\u0001\u0000\u0000"+
		"\u0000\u015f\u0175\u0005\u001f\u0000\u0000\u0160\u0162\u0005\b\u0000\u0000"+
		"\u0161\u0163\u0003:\u001d\u0000\u0162\u0161\u0001\u0000\u0000\u0000\u0162"+
		"\u0163\u0001\u0000\u0000\u0000\u0163\u0164\u0001\u0000\u0000\u0000\u0164"+
		"\u0175\u0005\t\u0000\u0000\u0165\u0166\u00058\u0000\u0000\u0166\u0167"+
		"\u0003\f\u0006\u0000\u0167\u0168\u0005\u0006\u0000\u0000\u0168\u0169\u0003"+
		".\u0017\u0000\u0169\u016a\u0005\u0007\u0000\u0000\u016a\u0175\u0001\u0000"+
		"\u0000\u0000\u016b\u016c\u00058\u0000\u0000\u016c\u016d\u0005\b\u0000"+
		"\u0000\u016d\u016e\u0003\f\u0006\u0000\u016e\u016f\u0005\u000b\u0000\u0000"+
		"\u016f\u0170\u0003\f\u0006\u0000\u0170\u0171\u0005\t\u0000\u0000\u0171"+
		"\u0175\u0001\u0000\u0000\u0000\u0172\u0175\u0003B!\u0000\u0173\u0175\u0003"+
		"J%\u0000\u0174\u013a\u0001\u0000\u0000\u0000\u0174\u013f\u0001\u0000\u0000"+
		"\u0000\u0174\u0142\u0001\u0000\u0000\u0000\u0174\u0145\u0001\u0000\u0000"+
		"\u0000\u0174\u0148\u0001\u0000\u0000\u0000\u0174\u0149\u0001\u0000\u0000"+
		"\u0000\u0174\u014b\u0001\u0000\u0000\u0000\u0174\u014d\u0001\u0000\u0000"+
		"\u0000\u0174\u0152\u0001\u0000\u0000\u0000\u0174\u0156\u0001\u0000\u0000"+
		"\u0000\u0174\u015b\u0001\u0000\u0000\u0000\u0174\u0160\u0001\u0000\u0000"+
		"\u0000\u0174\u0165\u0001\u0000\u0000\u0000\u0174\u016b\u0001\u0000\u0000"+
		"\u0000\u0174\u0172\u0001\u0000\u0000\u0000\u0174\u0173\u0001\u0000\u0000"+
		"\u0000\u0175\u0193\u0001\u0000\u0000\u0000\u0176\u0177\n\u000e\u0000\u0000"+
		"\u0177\u0178\u0007\u0001\u0000\u0000\u0178\u0192\u0003.\u0017\u000f\u0179"+
		"\u017a\n\r\u0000\u0000\u017a\u017b\u0007\u0002\u0000\u0000\u017b\u0192"+
		"\u0003.\u0017\u000e\u017c\u017d\n\f\u0000\u0000\u017d\u017e\u0005#\u0000"+
		"\u0000\u017e\u0192\u0003.\u0017\r\u017f\u0180\n\u000b\u0000\u0000\u0180"+
		"\u0181\u0007\u0003\u0000\u0000\u0181\u0192\u0003.\u0017\f\u0182\u0183"+
		"\n\n\u0000\u0000\u0183\u0184\u0007\u0004\u0000\u0000\u0184\u0192\u0003"+
		".\u0017\u000b\u0185\u0186\n\t\u0000\u0000\u0186\u0187\u0005\u000f\u0000"+
		"\u0000\u0187\u0188\u0003.\u0017\u0000\u0188\u0189\u0005\u000b\u0000\u0000"+
		"\u0189\u018a\u0003.\u0017\n\u018a\u0192\u0001\u0000\u0000\u0000\u018b"+
		"\u018c\n\u0012\u0000\u0000\u018c\u018d\u0003F#\u0000\u018d\u018e\u0003"+
		"8\u001c\u0000\u018e\u0192\u0001\u0000\u0000\u0000\u018f\u0190\n\u0011"+
		"\u0000\u0000\u0190\u0192\u0003F#\u0000\u0191\u0176\u0001\u0000\u0000\u0000"+
		"\u0191\u0179\u0001\u0000\u0000\u0000\u0191\u017c\u0001\u0000\u0000\u0000"+
		"\u0191\u017f\u0001\u0000\u0000\u0000\u0191\u0182\u0001\u0000\u0000\u0000"+
		"\u0191\u0185\u0001\u0000\u0000\u0000\u0191\u018b\u0001\u0000\u0000\u0000"+
		"\u0191\u018f\u0001\u0000\u0000\u0000\u0192\u0195\u0001\u0000\u0000\u0000"+
		"\u0193\u0191\u0001\u0000\u0000\u0000\u0193\u0194\u0001\u0000\u0000\u0000"+
		"\u0194/\u0001\u0000\u0000\u0000\u0195\u0193\u0001\u0000\u0000\u0000\u0196"+
		"\u0197\u0005\u0004\u0000\u0000\u0197\u01a4\u0005\u0005\u0000\u0000\u0198"+
		"\u01a4\u0003D\"\u0000\u0199\u019a\u0005\u0004\u0000\u0000\u019a\u019d"+
		"\u0003D\"\u0000\u019b\u019c\u0005\f\u0000\u0000\u019c\u019e\u0003D\"\u0000"+
		"\u019d\u019b\u0001\u0000\u0000\u0000\u019e\u019f\u0001\u0000\u0000\u0000"+
		"\u019f\u019d\u0001\u0000\u0000\u0000\u019f\u01a0\u0001\u0000\u0000\u0000"+
		"\u01a0\u01a1\u0001\u0000\u0000\u0000\u01a1\u01a2\u0005\u0005\u0000\u0000"+
		"\u01a2\u01a4\u0001\u0000\u0000\u0000\u01a3\u0196\u0001\u0000\u0000\u0000"+
		"\u01a3\u0198\u0001\u0000\u0000\u0000\u01a3\u0199\u0001\u0000\u0000\u0000"+
		"\u01a41\u0001\u0000\u0000\u0000\u01a5\u01a6\u0005\u001a\u0000\u0000\u01a6"+
		"\u01aa\u0003\u0012\t\u0000\u01a7\u01a8\u0005\u001a\u0000\u0000\u01a8\u01aa"+
		"\u0003.\u0017\u0000\u01a9\u01a5\u0001\u0000\u0000\u0000\u01a9\u01a7\u0001"+
		"\u0000\u0000\u0000\u01aa3\u0001\u0000\u0000\u0000\u01ab\u01b0\u00036\u001b"+
		"\u0000\u01ac\u01ad\u0005\f\u0000\u0000\u01ad\u01af\u00036\u001b\u0000"+
		"\u01ae\u01ac\u0001\u0000\u0000\u0000\u01af\u01b2\u0001\u0000\u0000\u0000"+
		"\u01b0\u01ae\u0001\u0000\u0000\u0000\u01b0\u01b1\u0001\u0000\u0000\u0000"+
		"\u01b15\u0001\u0000\u0000\u0000\u01b2\u01b0\u0001\u0000\u0000\u0000\u01b3"+
		"\u01b4\u0003.\u0017\u0000\u01b4\u01b5\u0005\u000b\u0000\u0000\u01b5\u01b6"+
		"\u0003.\u0017\u0000\u01b67\u0001\u0000\u0000\u0000\u01b7\u01b9\u0005\u0004"+
		"\u0000\u0000\u01b8\u01ba\u0003:\u001d\u0000\u01b9\u01b8\u0001\u0000\u0000"+
		"\u0000\u01b9\u01ba\u0001\u0000\u0000\u0000\u01ba\u01bb\u0001\u0000\u0000"+
		"\u0000\u01bb\u01bc\u0005\u0005\u0000\u0000\u01bc9\u0001\u0000\u0000\u0000"+
		"\u01bd\u01c2\u0003.\u0017\u0000\u01be\u01bf\u0005\f\u0000\u0000\u01bf"+
		"\u01c1\u0003.\u0017\u0000\u01c0\u01be\u0001\u0000\u0000\u0000\u01c1\u01c4"+
		"\u0001\u0000\u0000\u0000\u01c2\u01c0\u0001\u0000\u0000\u0000\u01c2\u01c3"+
		"\u0001\u0000\u0000\u0000\u01c3;\u0001\u0000\u0000\u0000\u01c4\u01c2\u0001"+
		"\u0000\u0000\u0000\u01c5\u01c6\u0003B!\u0000\u01c6\u01c7\u0005\u0010\u0000"+
		"\u0000\u01c7\u01c8\u0003.\u0017\u0000\u01c8\u01ec\u0001\u0000\u0000\u0000"+
		"\u01c9\u01ca\u0003B!\u0000\u01ca\u01cb\u0005\u0011\u0000\u0000\u01cb\u01ec"+
		"\u0001\u0000\u0000\u0000\u01cc\u01cd\u0003B!\u0000\u01cd\u01ce\u0005\u0012"+
		"\u0000\u0000\u01ce\u01ec\u0001\u0000\u0000\u0000\u01cf\u01d0\u0003B!\u0000"+
		"\u01d0\u01d1\u0005\u0013\u0000\u0000\u01d1\u01d2\u0003.\u0017\u0000\u01d2"+
		"\u01ec\u0001\u0000\u0000\u0000\u01d3\u01d4\u0003B!\u0000\u01d4\u01d5\u0005"+
		"\u0014\u0000\u0000\u01d5\u01d6\u0003.\u0017\u0000\u01d6\u01ec\u0001\u0000"+
		"\u0000\u0000\u01d7\u01d8\u0003B!\u0000\u01d8\u01d9\u0005\u0015\u0000\u0000"+
		"\u01d9\u01da\u0003.\u0017\u0000\u01da\u01ec\u0001\u0000\u0000\u0000\u01db"+
		"\u01dc\u0003B!\u0000\u01dc\u01dd\u0005\u0016\u0000\u0000\u01dd\u01de\u0003"+
		".\u0017\u0000\u01de\u01ec\u0001\u0000\u0000\u0000\u01df\u01e0\u0003B!"+
		"\u0000\u01e0\u01e1\u0005\u0017\u0000\u0000\u01e1\u01e2\u0003.\u0017\u0000"+
		"\u01e2\u01ec\u0001\u0000\u0000\u0000\u01e3\u01e4\u0003B!\u0000\u01e4\u01e5"+
		"\u0005\u0018\u0000\u0000\u01e5\u01e6\u0003.\u0017\u0000\u01e6\u01ec\u0001"+
		"\u0000\u0000\u0000\u01e7\u01e8\u0003B!\u0000\u01e8\u01e9\u0005\u0019\u0000"+
		"\u0000\u01e9\u01ea\u0003.\u0017\u0000\u01ea\u01ec\u0001\u0000\u0000\u0000"+
		"\u01eb\u01c5\u0001\u0000\u0000\u0000\u01eb\u01c9\u0001\u0000\u0000\u0000"+
		"\u01eb\u01cc\u0001\u0000\u0000\u0000\u01eb\u01cf\u0001\u0000\u0000\u0000"+
		"\u01eb\u01d3\u0001\u0000\u0000\u0000\u01eb\u01d7\u0001\u0000\u0000\u0000"+
		"\u01eb\u01db\u0001\u0000\u0000\u0000\u01eb\u01df\u0001\u0000\u0000\u0000"+
		"\u01eb\u01e3\u0001\u0000\u0000\u0000\u01eb\u01e7\u0001\u0000\u0000\u0000"+
		"\u01ec=\u0001\u0000\u0000\u0000\u01ed\u01ee\u0003\n\u0005\u0000\u01ee"+
		"\u01ef\u0005\u0010\u0000\u0000\u01ef\u01f0\u0003.\u0017\u0000\u01f0?\u0001"+
		"\u0000\u0000\u0000\u01f1\u01f4\u0003\n\u0005\u0000\u01f2\u01f4\u0003>"+
		"\u001f\u0000\u01f3\u01f1\u0001\u0000\u0000\u0000\u01f3\u01f2\u0001\u0000"+
		"\u0000\u0000\u01f4A\u0001\u0000\u0000\u0000\u01f5\u0201\u0003D\"\u0000"+
		"\u01f6\u01f7\u0003D\"\u0000\u01f7\u01f8\u0005 \u0000\u0000\u01f8\u01f9"+
		"\u0003.\u0017\u0000\u01f9\u01fa\u0005\u001f\u0000\u0000\u01fa\u0201\u0001"+
		"\u0000\u0000\u0000\u01fb\u01fc\u0003D\"\u0000\u01fc\u01fd\u0005\u0006"+
		"\u0000\u0000\u01fd\u01fe\u0003.\u0017\u0000\u01fe\u01ff\u0005\u0007\u0000"+
		"\u0000\u01ff\u0201\u0001\u0000\u0000\u0000\u0200\u01f5\u0001\u0000\u0000"+
		"\u0000\u0200\u01f6\u0001\u0000\u0000\u0000\u0200\u01fb\u0001\u0000\u0000"+
		"\u0000\u0201C\u0001\u0000\u0000\u0000\u0202\u0203\u0005M\u0000\u0000\u0203"+
		"E\u0001\u0000\u0000\u0000\u0204\u0205\u0005N\u0000\u0000\u0205G\u0001"+
		"\u0000\u0000\u0000\u0206\u0207\u0005\u001c\u0000\u0000\u0207\u0208\u0003"+
		"D\"\u0000\u0208\u0209\u0003F#\u0000\u0209I\u0001\u0000\u0000\u0000\u020a"+
		"\u0211\u0005J\u0000\u0000\u020b\u0211\u0005K\u0000\u0000\u020c\u0211\u0005"+
		"G\u0000\u0000\u020d\u0211\u0003L&\u0000\u020e\u0211\u0005D\u0000\u0000"+
		"\u020f\u0211\u0003N\'\u0000\u0210\u020a\u0001\u0000\u0000\u0000\u0210"+
		"\u020b\u0001\u0000\u0000\u0000\u0210\u020c\u0001\u0000\u0000\u0000\u0210"+
		"\u020d\u0001\u0000\u0000\u0000\u0210\u020e\u0001\u0000\u0000\u0000\u0210"+
		"\u020f\u0001\u0000\u0000\u0000\u0211K\u0001\u0000\u0000\u0000\u0212\u0215"+
		"\u0005F\u0000\u0000\u0213\u0215\u0005E\u0000\u0000\u0214\u0212\u0001\u0000"+
		"\u0000\u0000\u0214\u0213\u0001\u0000\u0000\u0000\u0215M\u0001\u0000\u0000"+
		"\u0000\u0216\u0219\u0005=\u0000\u0000\u0217\u0219\u0005>\u0000\u0000\u0218"+
		"\u0216\u0001\u0000\u0000\u0000\u0218\u0217\u0001\u0000\u0000\u0000\u0219"+
		"O\u0001\u0000\u0000\u0000)U_chnuy\u0091\u009c\u009e\u00aa\u00ad\u00b4"+
		"\u00b8\u00d1\u00d5\u00e7\u00f2\u0107\u010c\u011e\u0121\u0134\u0158\u015d"+
		"\u0162\u0174\u0191\u0193\u019f\u01a3\u01a9\u01b0\u01b9\u01c2\u01eb\u01f3"+
		"\u0200\u0210\u0214\u0218";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}