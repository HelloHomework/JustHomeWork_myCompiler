// $ANTLR 3.5.3 ./myCompiler.g 2022-06-13 19:54:52

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class myCompilerLexer extends Lexer {
	public static final int EOF=-1;
	public static final int T__24=24;
	public static final int T__25=25;
	public static final int T__26=26;
	public static final int T__27=27;
	public static final int T__28=28;
	public static final int T__29=29;
	public static final int T__30=30;
	public static final int T__31=31;
	public static final int T__32=32;
	public static final int T__33=33;
	public static final int T__34=34;
	public static final int T__35=35;
	public static final int T__36=36;
	public static final int T__37=37;
	public static final int T__38=38;
	public static final int T__39=39;
	public static final int T__40=40;
	public static final int T__41=41;
	public static final int T__42=42;
	public static final int T__43=43;
	public static final int T__44=44;
	public static final int T__45=45;
	public static final int T__46=46;
	public static final int T__47=47;
	public static final int T__48=48;
	public static final int T__49=49;
	public static final int T__50=50;
	public static final int T__51=51;
	public static final int T__52=52;
	public static final int CHAR=4;
	public static final int COMMENT=5;
	public static final int DO=6;
	public static final int DOUBLE=7;
	public static final int ELSE=8;
	public static final int EscapeSequence=9;
	public static final int FLOAT=10;
	public static final int FOR=11;
	public static final int Floating_point_constant=12;
	public static final int IF=13;
	public static final int INT=14;
	public static final int Identifier=15;
	public static final int Integer_constant=16;
	public static final int LONG=17;
	public static final int MAIN=18;
	public static final int SHORT=19;
	public static final int STRING_LITERAL=20;
	public static final int VOID=21;
	public static final int WHILE=22;
	public static final int WS=23;

	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public myCompilerLexer() {} 
	public myCompilerLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public myCompilerLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "./myCompiler.g"; }

	// $ANTLR start "T__24"
	public final void mT__24() throws RecognitionException {
		try {
			int _type = T__24;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:7:7: ( '!=' )
			// ./myCompiler.g:7:9: '!='
			{
			match("!="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__24"

	// $ANTLR start "T__25"
	public final void mT__25() throws RecognitionException {
		try {
			int _type = T__25;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:8:7: ( '%' )
			// ./myCompiler.g:8:9: '%'
			{
			match('%'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__25"

	// $ANTLR start "T__26"
	public final void mT__26() throws RecognitionException {
		try {
			int _type = T__26;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:9:7: ( '&&' )
			// ./myCompiler.g:9:9: '&&'
			{
			match("&&"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__26"

	// $ANTLR start "T__27"
	public final void mT__27() throws RecognitionException {
		try {
			int _type = T__27;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:10:7: ( '&' )
			// ./myCompiler.g:10:9: '&'
			{
			match('&'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__27"

	// $ANTLR start "T__28"
	public final void mT__28() throws RecognitionException {
		try {
			int _type = T__28;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:11:7: ( '(' )
			// ./myCompiler.g:11:9: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__28"

	// $ANTLR start "T__29"
	public final void mT__29() throws RecognitionException {
		try {
			int _type = T__29;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:12:7: ( ')' )
			// ./myCompiler.g:12:9: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__29"

	// $ANTLR start "T__30"
	public final void mT__30() throws RecognitionException {
		try {
			int _type = T__30;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:13:7: ( '*' )
			// ./myCompiler.g:13:9: '*'
			{
			match('*'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__30"

	// $ANTLR start "T__31"
	public final void mT__31() throws RecognitionException {
		try {
			int _type = T__31;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:14:7: ( '+' )
			// ./myCompiler.g:14:9: '+'
			{
			match('+'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__31"

	// $ANTLR start "T__32"
	public final void mT__32() throws RecognitionException {
		try {
			int _type = T__32;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:15:7: ( ',' )
			// ./myCompiler.g:15:9: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__32"

	// $ANTLR start "T__33"
	public final void mT__33() throws RecognitionException {
		try {
			int _type = T__33;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:16:7: ( '-' )
			// ./myCompiler.g:16:9: '-'
			{
			match('-'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__33"

	// $ANTLR start "T__34"
	public final void mT__34() throws RecognitionException {
		try {
			int _type = T__34;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:17:7: ( '/' )
			// ./myCompiler.g:17:9: '/'
			{
			match('/'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__34"

	// $ANTLR start "T__35"
	public final void mT__35() throws RecognitionException {
		try {
			int _type = T__35;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:18:7: ( ';' )
			// ./myCompiler.g:18:9: ';'
			{
			match(';'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__35"

	// $ANTLR start "T__36"
	public final void mT__36() throws RecognitionException {
		try {
			int _type = T__36;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:19:7: ( '<' )
			// ./myCompiler.g:19:9: '<'
			{
			match('<'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__36"

	// $ANTLR start "T__37"
	public final void mT__37() throws RecognitionException {
		try {
			int _type = T__37;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:20:7: ( '<=' )
			// ./myCompiler.g:20:9: '<='
			{
			match("<="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__37"

	// $ANTLR start "T__38"
	public final void mT__38() throws RecognitionException {
		try {
			int _type = T__38;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:21:7: ( '=' )
			// ./myCompiler.g:21:9: '='
			{
			match('='); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__38"

	// $ANTLR start "T__39"
	public final void mT__39() throws RecognitionException {
		try {
			int _type = T__39;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:22:7: ( '==' )
			// ./myCompiler.g:22:9: '=='
			{
			match("=="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__39"

	// $ANTLR start "T__40"
	public final void mT__40() throws RecognitionException {
		try {
			int _type = T__40;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:23:7: ( '>' )
			// ./myCompiler.g:23:9: '>'
			{
			match('>'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__40"

	// $ANTLR start "T__41"
	public final void mT__41() throws RecognitionException {
		try {
			int _type = T__41;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:24:7: ( '>=' )
			// ./myCompiler.g:24:9: '>='
			{
			match(">="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__41"

	// $ANTLR start "T__42"
	public final void mT__42() throws RecognitionException {
		try {
			int _type = T__42;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:25:7: ( 'const' )
			// ./myCompiler.g:25:9: 'const'
			{
			match("const"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__42"

	// $ANTLR start "T__43"
	public final void mT__43() throws RecognitionException {
		try {
			int _type = T__43;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:26:7: ( 'extern' )
			// ./myCompiler.g:26:9: 'extern'
			{
			match("extern"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__43"

	// $ANTLR start "T__44"
	public final void mT__44() throws RecognitionException {
		try {
			int _type = T__44;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:27:7: ( 'extrn' )
			// ./myCompiler.g:27:9: 'extrn'
			{
			match("extrn"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__44"

	// $ANTLR start "T__45"
	public final void mT__45() throws RecognitionException {
		try {
			int _type = T__45;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:28:7: ( 'register' )
			// ./myCompiler.g:28:9: 'register'
			{
			match("register"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__45"

	// $ANTLR start "T__46"
	public final void mT__46() throws RecognitionException {
		try {
			int _type = T__46;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:29:7: ( 'signed' )
			// ./myCompiler.g:29:9: 'signed'
			{
			match("signed"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__46"

	// $ANTLR start "T__47"
	public final void mT__47() throws RecognitionException {
		try {
			int _type = T__47;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:30:7: ( 'static' )
			// ./myCompiler.g:30:9: 'static'
			{
			match("static"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__47"

	// $ANTLR start "T__48"
	public final void mT__48() throws RecognitionException {
		try {
			int _type = T__48;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:31:7: ( 'unsigned' )
			// ./myCompiler.g:31:9: 'unsigned'
			{
			match("unsigned"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__48"

	// $ANTLR start "T__49"
	public final void mT__49() throws RecognitionException {
		try {
			int _type = T__49;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:32:7: ( 'volatile' )
			// ./myCompiler.g:32:9: 'volatile'
			{
			match("volatile"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__49"

	// $ANTLR start "T__50"
	public final void mT__50() throws RecognitionException {
		try {
			int _type = T__50;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:33:7: ( '{' )
			// ./myCompiler.g:33:9: '{'
			{
			match('{'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__50"

	// $ANTLR start "T__51"
	public final void mT__51() throws RecognitionException {
		try {
			int _type = T__51;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:34:7: ( '||' )
			// ./myCompiler.g:34:9: '||'
			{
			match("||"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__51"

	// $ANTLR start "T__52"
	public final void mT__52() throws RecognitionException {
		try {
			int _type = T__52;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:35:7: ( '}' )
			// ./myCompiler.g:35:9: '}'
			{
			match('}'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__52"

	// $ANTLR start "FLOAT"
	public final void mFLOAT() throws RecognitionException {
		try {
			int _type = FLOAT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:717:6: ( 'float' )
			// ./myCompiler.g:717:7: 'float'
			{
			match("float"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FLOAT"

	// $ANTLR start "INT"
	public final void mINT() throws RecognitionException {
		try {
			int _type = INT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:718:4: ( 'int' )
			// ./myCompiler.g:718:5: 'int'
			{
			match("int"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INT"

	// $ANTLR start "CHAR"
	public final void mCHAR() throws RecognitionException {
		try {
			int _type = CHAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:719:5: ( 'char' )
			// ./myCompiler.g:719:7: 'char'
			{
			match("char"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CHAR"

	// $ANTLR start "SHORT"
	public final void mSHORT() throws RecognitionException {
		try {
			int _type = SHORT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:720:6: ( 'short' )
			// ./myCompiler.g:720:7: 'short'
			{
			match("short"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SHORT"

	// $ANTLR start "LONG"
	public final void mLONG() throws RecognitionException {
		try {
			int _type = LONG;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:721:5: ( 'long' )
			// ./myCompiler.g:721:6: 'long'
			{
			match("long"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LONG"

	// $ANTLR start "DOUBLE"
	public final void mDOUBLE() throws RecognitionException {
		try {
			int _type = DOUBLE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:722:7: ( 'double' )
			// ./myCompiler.g:722:8: 'double'
			{
			match("double"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOUBLE"

	// $ANTLR start "MAIN"
	public final void mMAIN() throws RecognitionException {
		try {
			int _type = MAIN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:723:5: ( 'main' )
			// ./myCompiler.g:723:7: 'main'
			{
			match("main"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MAIN"

	// $ANTLR start "VOID"
	public final void mVOID() throws RecognitionException {
		try {
			int _type = VOID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:724:5: ( 'void' )
			// ./myCompiler.g:724:7: 'void'
			{
			match("void"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "VOID"

	// $ANTLR start "IF"
	public final void mIF() throws RecognitionException {
		try {
			int _type = IF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:725:3: ( 'if' )
			// ./myCompiler.g:725:5: 'if'
			{
			match("if"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IF"

	// $ANTLR start "ELSE"
	public final void mELSE() throws RecognitionException {
		try {
			int _type = ELSE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:726:5: ( 'else' )
			// ./myCompiler.g:726:7: 'else'
			{
			match("else"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ELSE"

	// $ANTLR start "WHILE"
	public final void mWHILE() throws RecognitionException {
		try {
			int _type = WHILE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:727:6: ( 'while' )
			// ./myCompiler.g:727:8: 'while'
			{
			match("while"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WHILE"

	// $ANTLR start "DO"
	public final void mDO() throws RecognitionException {
		try {
			int _type = DO;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:728:3: ( 'do' )
			// ./myCompiler.g:728:5: 'do'
			{
			match("do"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DO"

	// $ANTLR start "FOR"
	public final void mFOR() throws RecognitionException {
		try {
			int _type = FOR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:729:4: ( 'for' )
			// ./myCompiler.g:729:6: 'for'
			{
			match("for"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FOR"

	// $ANTLR start "Identifier"
	public final void mIdentifier() throws RecognitionException {
		try {
			int _type = Identifier;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:731:11: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
			// ./myCompiler.g:731:12: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// ./myCompiler.g:731:36: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// ./myCompiler.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop1;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Identifier"

	// $ANTLR start "Integer_constant"
	public final void mInteger_constant() throws RecognitionException {
		try {
			int _type = Integer_constant;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:732:17: ( ( '0' .. '9' )+ )
			// ./myCompiler.g:732:18: ( '0' .. '9' )+
			{
			// ./myCompiler.g:732:18: ( '0' .. '9' )+
			int cnt2=0;
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( ((LA2_0 >= '0' && LA2_0 <= '9')) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// ./myCompiler.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt2 >= 1 ) break loop2;
					EarlyExitException eee = new EarlyExitException(2, input);
					throw eee;
				}
				cnt2++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Integer_constant"

	// $ANTLR start "Floating_point_constant"
	public final void mFloating_point_constant() throws RecognitionException {
		try {
			int _type = Floating_point_constant;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:733:24: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )+ )
			// ./myCompiler.g:733:25: ( '0' .. '9' )+ '.' ( '0' .. '9' )+
			{
			// ./myCompiler.g:733:25: ( '0' .. '9' )+
			int cnt3=0;
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( ((LA3_0 >= '0' && LA3_0 <= '9')) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// ./myCompiler.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt3 >= 1 ) break loop3;
					EarlyExitException eee = new EarlyExitException(3, input);
					throw eee;
				}
				cnt3++;
			}

			match('.'); 
			// ./myCompiler.g:733:39: ( '0' .. '9' )+
			int cnt4=0;
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( ((LA4_0 >= '0' && LA4_0 <= '9')) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// ./myCompiler.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt4 >= 1 ) break loop4;
					EarlyExitException eee = new EarlyExitException(4, input);
					throw eee;
				}
				cnt4++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Floating_point_constant"

	// $ANTLR start "STRING_LITERAL"
	public final void mSTRING_LITERAL() throws RecognitionException {
		try {
			int _type = STRING_LITERAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:736:4: ( '\"' ( EscapeSequence |~ ( '\\\\' | '\"' ) )* '\"' )
			// ./myCompiler.g:736:4: '\"' ( EscapeSequence |~ ( '\\\\' | '\"' ) )* '\"'
			{
			match('\"'); 
			// ./myCompiler.g:736:8: ( EscapeSequence |~ ( '\\\\' | '\"' ) )*
			loop5:
			while (true) {
				int alt5=3;
				int LA5_0 = input.LA(1);
				if ( (LA5_0=='\\') ) {
					alt5=1;
				}
				else if ( ((LA5_0 >= '\u0000' && LA5_0 <= '!')||(LA5_0 >= '#' && LA5_0 <= '[')||(LA5_0 >= ']' && LA5_0 <= '\uFFFF')) ) {
					alt5=2;
				}

				switch (alt5) {
				case 1 :
					// ./myCompiler.g:736:10: EscapeSequence
					{
					mEscapeSequence(); 

					}
					break;
				case 2 :
					// ./myCompiler.g:736:27: ~ ( '\\\\' | '\"' )
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop5;
				}
			}

			match('\"'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STRING_LITERAL"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:739:3: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
			// ./myCompiler.g:739:4: ( ' ' | '\\t' | '\\r' | '\\n' )
			{
			if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			_channel=HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	// $ANTLR start "COMMENT"
	public final void mCOMMENT() throws RecognitionException {
		try {
			int _type = COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ./myCompiler.g:740:8: ( '/*' ( . )* '*/' )
			// ./myCompiler.g:740:9: '/*' ( . )* '*/'
			{
			match("/*"); 

			// ./myCompiler.g:740:14: ( . )*
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( (LA6_0=='*') ) {
					int LA6_1 = input.LA(2);
					if ( (LA6_1=='/') ) {
						alt6=2;
					}
					else if ( ((LA6_1 >= '\u0000' && LA6_1 <= '.')||(LA6_1 >= '0' && LA6_1 <= '\uFFFF')) ) {
						alt6=1;
					}

				}
				else if ( ((LA6_0 >= '\u0000' && LA6_0 <= ')')||(LA6_0 >= '+' && LA6_0 <= '\uFFFF')) ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// ./myCompiler.g:740:14: .
					{
					matchAny(); 
					}
					break;

				default :
					break loop6;
				}
			}

			match("*/"); 

			_channel=HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMENT"

	// $ANTLR start "EscapeSequence"
	public final void mEscapeSequence() throws RecognitionException {
		try {
			// ./myCompiler.g:746:5: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) )
			// ./myCompiler.g:746:5: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
			{
			match('\\'); 
			if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EscapeSequence"

	@Override
	public void mTokens() throws RecognitionException {
		// ./myCompiler.g:1:8: ( T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | FLOAT | INT | CHAR | SHORT | LONG | DOUBLE | MAIN | VOID | IF | ELSE | WHILE | DO | FOR | Identifier | Integer_constant | Floating_point_constant | STRING_LITERAL | WS | COMMENT )
		int alt7=48;
		alt7 = dfa7.predict(input);
		switch (alt7) {
			case 1 :
				// ./myCompiler.g:1:10: T__24
				{
				mT__24(); 

				}
				break;
			case 2 :
				// ./myCompiler.g:1:16: T__25
				{
				mT__25(); 

				}
				break;
			case 3 :
				// ./myCompiler.g:1:22: T__26
				{
				mT__26(); 

				}
				break;
			case 4 :
				// ./myCompiler.g:1:28: T__27
				{
				mT__27(); 

				}
				break;
			case 5 :
				// ./myCompiler.g:1:34: T__28
				{
				mT__28(); 

				}
				break;
			case 6 :
				// ./myCompiler.g:1:40: T__29
				{
				mT__29(); 

				}
				break;
			case 7 :
				// ./myCompiler.g:1:46: T__30
				{
				mT__30(); 

				}
				break;
			case 8 :
				// ./myCompiler.g:1:52: T__31
				{
				mT__31(); 

				}
				break;
			case 9 :
				// ./myCompiler.g:1:58: T__32
				{
				mT__32(); 

				}
				break;
			case 10 :
				// ./myCompiler.g:1:64: T__33
				{
				mT__33(); 

				}
				break;
			case 11 :
				// ./myCompiler.g:1:70: T__34
				{
				mT__34(); 

				}
				break;
			case 12 :
				// ./myCompiler.g:1:76: T__35
				{
				mT__35(); 

				}
				break;
			case 13 :
				// ./myCompiler.g:1:82: T__36
				{
				mT__36(); 

				}
				break;
			case 14 :
				// ./myCompiler.g:1:88: T__37
				{
				mT__37(); 

				}
				break;
			case 15 :
				// ./myCompiler.g:1:94: T__38
				{
				mT__38(); 

				}
				break;
			case 16 :
				// ./myCompiler.g:1:100: T__39
				{
				mT__39(); 

				}
				break;
			case 17 :
				// ./myCompiler.g:1:106: T__40
				{
				mT__40(); 

				}
				break;
			case 18 :
				// ./myCompiler.g:1:112: T__41
				{
				mT__41(); 

				}
				break;
			case 19 :
				// ./myCompiler.g:1:118: T__42
				{
				mT__42(); 

				}
				break;
			case 20 :
				// ./myCompiler.g:1:124: T__43
				{
				mT__43(); 

				}
				break;
			case 21 :
				// ./myCompiler.g:1:130: T__44
				{
				mT__44(); 

				}
				break;
			case 22 :
				// ./myCompiler.g:1:136: T__45
				{
				mT__45(); 

				}
				break;
			case 23 :
				// ./myCompiler.g:1:142: T__46
				{
				mT__46(); 

				}
				break;
			case 24 :
				// ./myCompiler.g:1:148: T__47
				{
				mT__47(); 

				}
				break;
			case 25 :
				// ./myCompiler.g:1:154: T__48
				{
				mT__48(); 

				}
				break;
			case 26 :
				// ./myCompiler.g:1:160: T__49
				{
				mT__49(); 

				}
				break;
			case 27 :
				// ./myCompiler.g:1:166: T__50
				{
				mT__50(); 

				}
				break;
			case 28 :
				// ./myCompiler.g:1:172: T__51
				{
				mT__51(); 

				}
				break;
			case 29 :
				// ./myCompiler.g:1:178: T__52
				{
				mT__52(); 

				}
				break;
			case 30 :
				// ./myCompiler.g:1:184: FLOAT
				{
				mFLOAT(); 

				}
				break;
			case 31 :
				// ./myCompiler.g:1:190: INT
				{
				mINT(); 

				}
				break;
			case 32 :
				// ./myCompiler.g:1:194: CHAR
				{
				mCHAR(); 

				}
				break;
			case 33 :
				// ./myCompiler.g:1:199: SHORT
				{
				mSHORT(); 

				}
				break;
			case 34 :
				// ./myCompiler.g:1:205: LONG
				{
				mLONG(); 

				}
				break;
			case 35 :
				// ./myCompiler.g:1:210: DOUBLE
				{
				mDOUBLE(); 

				}
				break;
			case 36 :
				// ./myCompiler.g:1:217: MAIN
				{
				mMAIN(); 

				}
				break;
			case 37 :
				// ./myCompiler.g:1:222: VOID
				{
				mVOID(); 

				}
				break;
			case 38 :
				// ./myCompiler.g:1:227: IF
				{
				mIF(); 

				}
				break;
			case 39 :
				// ./myCompiler.g:1:230: ELSE
				{
				mELSE(); 

				}
				break;
			case 40 :
				// ./myCompiler.g:1:235: WHILE
				{
				mWHILE(); 

				}
				break;
			case 41 :
				// ./myCompiler.g:1:241: DO
				{
				mDO(); 

				}
				break;
			case 42 :
				// ./myCompiler.g:1:244: FOR
				{
				mFOR(); 

				}
				break;
			case 43 :
				// ./myCompiler.g:1:248: Identifier
				{
				mIdentifier(); 

				}
				break;
			case 44 :
				// ./myCompiler.g:1:259: Integer_constant
				{
				mInteger_constant(); 

				}
				break;
			case 45 :
				// ./myCompiler.g:1:276: Floating_point_constant
				{
				mFloating_point_constant(); 

				}
				break;
			case 46 :
				// ./myCompiler.g:1:300: STRING_LITERAL
				{
				mSTRING_LITERAL(); 

				}
				break;
			case 47 :
				// ./myCompiler.g:1:315: WS
				{
				mWS(); 

				}
				break;
			case 48 :
				// ./myCompiler.g:1:318: COMMENT
				{
				mCOMMENT(); 

				}
				break;

		}
	}


	protected DFA7 dfa7 = new DFA7(this);
	static final String DFA7_eotS =
		"\3\uffff\1\43\6\uffff\1\45\1\uffff\1\47\1\51\1\53\6\36\3\uffff\6\36\1"+
		"\uffff\1\76\14\uffff\15\36\1\116\1\36\1\121\2\36\2\uffff\14\36\1\141\1"+
		"\142\1\uffff\2\36\1\uffff\3\36\1\150\2\36\1\153\6\36\1\162\1\36\2\uffff"+
		"\1\164\1\36\1\166\1\36\1\170\1\uffff\1\36\1\172\1\uffff\3\36\1\176\2\36"+
		"\1\uffff\1\u0081\1\uffff\1\36\1\uffff\1\u0083\1\uffff\1\u0084\1\uffff"+
		"\1\36\1\u0086\1\u0087\1\uffff\2\36\1\uffff\1\u008a\2\uffff\1\36\2\uffff"+
		"\2\36\1\uffff\1\u008e\1\u008f\1\u0090\3\uffff";
	static final String DFA7_eofS =
		"\u0091\uffff";
	static final String DFA7_minS =
		"\1\11\2\uffff\1\46\6\uffff\1\52\1\uffff\3\75\1\150\1\154\1\145\1\150\1"+
		"\156\1\157\3\uffff\1\154\1\146\2\157\1\141\1\150\1\uffff\1\56\14\uffff"+
		"\1\156\1\141\1\164\1\163\2\147\1\141\1\157\1\163\1\151\1\157\1\162\1\164"+
		"\1\60\1\156\1\60\2\151\2\uffff\1\163\1\162\2\145\1\151\1\156\1\164\1\162"+
		"\1\151\1\141\1\144\1\141\2\60\1\uffff\1\147\1\142\1\uffff\1\156\1\154"+
		"\1\164\1\60\1\162\1\156\1\60\1\163\1\145\1\151\1\164\1\147\1\164\1\60"+
		"\1\164\2\uffff\1\60\1\154\1\60\1\145\1\60\1\uffff\1\156\1\60\1\uffff\1"+
		"\164\1\144\1\143\1\60\1\156\1\151\1\uffff\1\60\1\uffff\1\145\1\uffff\1"+
		"\60\1\uffff\1\60\1\uffff\1\145\2\60\1\uffff\1\145\1\154\1\uffff\1\60\2"+
		"\uffff\1\162\2\uffff\1\144\1\145\1\uffff\3\60\3\uffff";
	static final String DFA7_maxS =
		"\1\175\2\uffff\1\46\6\uffff\1\52\1\uffff\3\75\1\157\1\170\1\145\1\164"+
		"\1\156\1\157\3\uffff\1\157\1\156\2\157\1\141\1\150\1\uffff\1\71\14\uffff"+
		"\1\156\1\141\1\164\1\163\2\147\1\141\1\157\1\163\1\154\1\157\1\162\1\164"+
		"\1\172\1\156\1\172\2\151\2\uffff\1\163\2\162\1\145\1\151\1\156\1\164\1"+
		"\162\1\151\1\141\1\144\1\141\2\172\1\uffff\1\147\1\142\1\uffff\1\156\1"+
		"\154\1\164\1\172\1\162\1\156\1\172\1\163\1\145\1\151\1\164\1\147\1\164"+
		"\1\172\1\164\2\uffff\1\172\1\154\1\172\1\145\1\172\1\uffff\1\156\1\172"+
		"\1\uffff\1\164\1\144\1\143\1\172\1\156\1\151\1\uffff\1\172\1\uffff\1\145"+
		"\1\uffff\1\172\1\uffff\1\172\1\uffff\1\145\2\172\1\uffff\1\145\1\154\1"+
		"\uffff\1\172\2\uffff\1\162\2\uffff\1\144\1\145\1\uffff\3\172\3\uffff";
	static final String DFA7_acceptS =
		"\1\uffff\1\1\1\2\1\uffff\1\5\1\6\1\7\1\10\1\11\1\12\1\uffff\1\14\11\uffff"+
		"\1\33\1\34\1\35\6\uffff\1\53\1\uffff\1\56\1\57\1\3\1\4\1\60\1\13\1\16"+
		"\1\15\1\20\1\17\1\22\1\21\22\uffff\1\54\1\55\16\uffff\1\46\2\uffff\1\51"+
		"\17\uffff\1\52\1\37\5\uffff\1\40\2\uffff\1\47\6\uffff\1\45\1\uffff\1\42"+
		"\1\uffff\1\44\1\uffff\1\23\1\uffff\1\25\3\uffff\1\41\2\uffff\1\36\1\uffff"+
		"\1\50\1\24\1\uffff\1\27\1\30\2\uffff\1\43\3\uffff\1\26\1\31\1\32";
	static final String DFA7_specialS =
		"\u0091\uffff}>";
	static final String[] DFA7_transitionS = {
			"\2\41\2\uffff\1\41\22\uffff\1\41\1\1\1\40\2\uffff\1\2\1\3\1\uffff\1\4"+
			"\1\5\1\6\1\7\1\10\1\11\1\uffff\1\12\12\37\1\uffff\1\13\1\14\1\15\1\16"+
			"\2\uffff\32\36\4\uffff\1\36\1\uffff\2\36\1\17\1\33\1\20\1\30\2\36\1\31"+
			"\2\36\1\32\1\34\4\36\1\21\1\22\1\36\1\23\1\24\1\35\3\36\1\25\1\26\1\27",
			"",
			"",
			"\1\42",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\44",
			"",
			"\1\46",
			"\1\50",
			"\1\52",
			"\1\55\6\uffff\1\54",
			"\1\57\13\uffff\1\56",
			"\1\60",
			"\1\63\1\61\12\uffff\1\62",
			"\1\64",
			"\1\65",
			"",
			"",
			"",
			"\1\66\2\uffff\1\67",
			"\1\71\7\uffff\1\70",
			"\1\72",
			"\1\73",
			"\1\74",
			"\1\75",
			"",
			"\1\77\1\uffff\12\37",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\100",
			"\1\101",
			"\1\102",
			"\1\103",
			"\1\104",
			"\1\105",
			"\1\106",
			"\1\107",
			"\1\110",
			"\1\112\2\uffff\1\111",
			"\1\113",
			"\1\114",
			"\1\115",
			"\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\117",
			"\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\24\36\1\120\5\36",
			"\1\122",
			"\1\123",
			"",
			"",
			"\1\124",
			"\1\125",
			"\1\126\14\uffff\1\127",
			"\1\130",
			"\1\131",
			"\1\132",
			"\1\133",
			"\1\134",
			"\1\135",
			"\1\136",
			"\1\137",
			"\1\140",
			"\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"",
			"\1\143",
			"\1\144",
			"",
			"\1\145",
			"\1\146",
			"\1\147",
			"\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\151",
			"\1\152",
			"\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\154",
			"\1\155",
			"\1\156",
			"\1\157",
			"\1\160",
			"\1\161",
			"\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\163",
			"",
			"",
			"\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\165",
			"\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\167",
			"\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"",
			"\1\171",
			"\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"",
			"\1\173",
			"\1\174",
			"\1\175",
			"\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\177",
			"\1\u0080",
			"",
			"\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"",
			"\1\u0082",
			"",
			"\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"",
			"\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"",
			"\1\u0085",
			"\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"",
			"\1\u0088",
			"\1\u0089",
			"",
			"\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"",
			"",
			"\1\u008b",
			"",
			"",
			"\1\u008c",
			"\1\u008d",
			"",
			"\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"",
			"",
			""
	};

	static final short[] DFA7_eot = DFA.unpackEncodedString(DFA7_eotS);
	static final short[] DFA7_eof = DFA.unpackEncodedString(DFA7_eofS);
	static final char[] DFA7_min = DFA.unpackEncodedStringToUnsignedChars(DFA7_minS);
	static final char[] DFA7_max = DFA.unpackEncodedStringToUnsignedChars(DFA7_maxS);
	static final short[] DFA7_accept = DFA.unpackEncodedString(DFA7_acceptS);
	static final short[] DFA7_special = DFA.unpackEncodedString(DFA7_specialS);
	static final short[][] DFA7_transition;

	static {
		int numStates = DFA7_transitionS.length;
		DFA7_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA7_transition[i] = DFA.unpackEncodedString(DFA7_transitionS[i]);
		}
	}

	protected class DFA7 extends DFA {

		public DFA7(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 7;
			this.eot = DFA7_eot;
			this.eof = DFA7_eof;
			this.min = DFA7_min;
			this.max = DFA7_max;
			this.accept = DFA7_accept;
			this.special = DFA7_special;
			this.transition = DFA7_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | FLOAT | INT | CHAR | SHORT | LONG | DOUBLE | MAIN | VOID | IF | ELSE | WHILE | DO | FOR | Identifier | Integer_constant | Floating_point_constant | STRING_LITERAL | WS | COMMENT );";
		}
	}

}
