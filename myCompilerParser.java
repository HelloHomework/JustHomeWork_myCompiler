// $ANTLR 3.5.3 ./myCompiler.g 2022-06-13 19:54:52

    // import packages here.
    import java.util.HashMap;
    import java.util.ArrayList;
    import static java.lang.Math.round;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@SuppressWarnings("all")
public class myCompilerParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "CHAR", "COMMENT", "DO", "DOUBLE", 
		"ELSE", "EscapeSequence", "FLOAT", "FOR", "Floating_point_constant", "IF", 
		"INT", "Identifier", "Integer_constant", "LONG", "MAIN", "SHORT", "STRING_LITERAL", 
		"VOID", "WHILE", "WS", "'!='", "'%'", "'&&'", "'&'", "'('", "')'", "'*'", 
		"'+'", "','", "'-'", "'/'", "';'", "'<'", "'<='", "'='", "'=='", "'>'", 
		"'>='", "'const'", "'extern'", "'extrn'", "'register'", "'signed'", "'static'", 
		"'unsigned'", "'volatile'", "'{'", "'||'", "'}'"
	};
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
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public myCompilerParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public myCompilerParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return myCompilerParser.tokenNames; }
	@Override public String getGrammarFileName() { return "./myCompiler.g"; }


	    boolean TRACEON = false;

	    // Type information.
	    public enum Type{
	       ERR, BOOL, INT, FLOAT, CHAR, CONST_INT, CONST_FLOAT, VOID;
	    }

	    // This structure is used to record the information of a variable or a constant.
	    class tVar {
	      int   varIndex; // temporary variable's index. Ex: t1, t2, ..., etc.
	      int   iValue;   // value of constant integer. Ex: 123.
	      float fValue;   // value of constant floating point. Ex: 2.314.
	   };

	    class Info {
	       Type theType;  // type information.
	       tVar theVar;
	      
	      Info() {
	         theType = Type.ERR;
	         theVar = new tVar();
	      }
	    };

	   
	   // ============================================
	   // Create a symbol table.
	   // ArrayList is easy to extend to add more info. into symbol table.
	   //
	   // The structure of symbol table:
	   // <variable ID, [Type, [varIndex or iValue, or fValue]]>
	   //    - type: the variable type   (please check "enum Type")
	   //    - varIndex: the variable's index, ex: t1, t2, ...
	   //    - iValue: value of integer constant.
	   //    - fValue: value of floating-point constant.
	    // ============================================

	    HashMap<String, Info> symtab = new HashMap<String, Info>();

	    // constantCount is used to represent constant variable.
	    // The first index is 0.
	    int constantCount = 0;

	    // labelCount is used to represent temporary label.
	    // The first index is 0.
	    int labelCount = 0;
	   
	    // varCount is used to represent temporary variables.
	    // The first index is 0.
	    int varCount = 0;

	    // Record all assembly instructions.
	    List<String> TextCode = new ArrayList<String>();

	    // Record all constant stirng.
	    List<String> ConstantCode = new ArrayList<String>();

	    /*
	     * Output prologue.
	     */
	    void prologue()
	    {
	      TextCode.add("; === prologue ====");
	      TextCode.add("declare dso_local i32 @printf(i8*, ...)\n");
	      TextCode.add("define dso_local i32 @main()");
	      TextCode.add("{");
	    }
	    
	   
	    /*
	     * Output epilogue.
	     */
	    void epilogue()
	    {
	       /* handle epilogue */
	       TextCode.add("\n; === epilogue ===");
	       TextCode.add("ret i32 0");
	       TextCode.add("}");
	    }
	    
	    
	    /* Generate a new label */
	    String newLabel()
	    {
	       labelCount ++;
	       return (new String("L")) + Integer.toString(labelCount);
	    } 
	    
	    
	    public List<String> getTextCode()
	    {
	       return TextCode;
	    }

	    public List<String> getConstantCode()
	    {
	       return ConstantCode;
	    }



	// $ANTLR start "program"
	// ./myCompiler.g:115:1: program : VOID MAIN '(' ')' '{' declarations statements '}' ;
	public final void program() throws RecognitionException {
		try {
			// ./myCompiler.g:115:8: ( VOID MAIN '(' ')' '{' declarations statements '}' )
			// ./myCompiler.g:116:2: VOID MAIN '(' ')' '{' declarations statements '}'
			{
			match(input,VOID,FOLLOW_VOID_in_program37); if (state.failed) return;
			match(input,MAIN,FOLLOW_MAIN_in_program39); if (state.failed) return;
			match(input,28,FOLLOW_28_in_program41); if (state.failed) return;
			match(input,29,FOLLOW_29_in_program43); if (state.failed) return;
			if ( state.backtracking==0 ) {
			   /* Output function prologue */
			   prologue();
			}
			match(input,50,FOLLOW_50_in_program48); if (state.failed) return;
			pushFollow(FOLLOW_declarations_in_program54);
			declarations();
			state._fsp--;
			if (state.failed) return;
			pushFollow(FOLLOW_statements_in_program59);
			statements();
			state._fsp--;
			if (state.failed) return;
			match(input,52,FOLLOW_52_in_program61); if (state.failed) return;
			if ( state.backtracking==0 ) {
			if (TRACEON)
			System.out.println("VOID MAIN () {declarations statements}");

			   /* output function epilogue */   
			   epilogue();
			}
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "program"



	// $ANTLR start "declarations"
	// ./myCompiler.g:135:1: declarations : ( type t1= Identifier ( ',' t2= Identifier )* ';' declarations |);
	public final void declarations() throws RecognitionException {
		Token t1=null;
		Token t2=null;
		Type type1 =null;

		try {
			// ./myCompiler.g:135:13: ( type t1= Identifier ( ',' t2= Identifier )* ';' declarations |)
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==CHAR||LA2_0==DOUBLE||LA2_0==FLOAT||LA2_0==INT||LA2_0==LONG||LA2_0==VOID||(LA2_0 >= 42 && LA2_0 <= 49)) ) {
				alt2=1;
			}
			else if ( (LA2_0==DO||LA2_0==FOR||LA2_0==IF||LA2_0==Identifier||LA2_0==WHILE||LA2_0==52) ) {
				alt2=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return;}
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}

			switch (alt2) {
				case 1 :
					// ./myCompiler.g:135:14: type t1= Identifier ( ',' t2= Identifier )* ';' declarations
					{
					pushFollow(FOLLOW_type_in_declarations70);
					type1=type();
					state._fsp--;
					if (state.failed) return;
					t1=(Token)match(input,Identifier,FOLLOW_Identifier_in_declarations76); if (state.failed) return;
					// ./myCompiler.g:135:34: ( ',' t2= Identifier )*
					loop1:
					while (true) {
						int alt1=2;
						int LA1_0 = input.LA(1);
						if ( (LA1_0==32) ) {
							alt1=1;
						}

						switch (alt1) {
						case 1 :
							// ./myCompiler.g:136:1: ',' t2= Identifier
							{
							match(input,32,FOLLOW_32_in_declarations79); if (state.failed) return;
							t2=(Token)match(input,Identifier,FOLLOW_Identifier_in_declarations85); if (state.failed) return;
							if ( state.backtracking==0 ) {
							   if (TRACEON)
							      System.out.println("declarations: type Identifier : declarations");

							   if (symtab.containsKey((t2!=null?t2.getText():null))) {
							      // variable re-declared.
							      System.out.println("Type Error: " + 
							                           t2.getLine() + 
							                        ": Redeclared identifier.");
							      System.exit(0);
							   }
							         
							   /* Add ID and its info into the symbol table. */
							   Info the_entry = new Info();
							   the_entry.theType = type1;
							   the_entry.theVar.varIndex = varCount;
							   varCount ++;
							   symtab.put((t2!=null?t2.getText():null), the_entry);

							   // issue the instruction.
							   // Ex: %a = alloca i32, align 4
							   if (type1 == Type.INT) { 
							      TextCode.add("%t" + the_entry.theVar.varIndex + " = alloca i32, align 4");
							   }
							}
							}
							break;

						default :
							break loop1;
						}
					}

					match(input,35,FOLLOW_35_in_declarations92); if (state.failed) return;
					pushFollow(FOLLOW_declarations_in_declarations94);
					declarations();
					state._fsp--;
					if (state.failed) return;
					if ( state.backtracking==0 ) {
					   if (TRACEON)
					      System.out.println("declarations: type Identifier : declarations");

					   if (symtab.containsKey((t1!=null?t1.getText():null))) {
					      // variable re-declared.
					      System.out.println("Type Error: " + 
					                           t1.getLine() + 
					                        ": Redeclared identifier.");
					      System.exit(0);
					   }
					         
					   /* Add ID and its info into the symbol table. */
					   Info the_entry = new Info();
					   the_entry.theType = type1;
					   the_entry.theVar.varIndex = varCount;
					   varCount ++;
					   symtab.put((t1!=null?t1.getText():null), the_entry);

					   // issue the instruction.
					   // Ex: %a = alloca i32, align 4
					   if (type1 == Type.INT) { 
					      TextCode.add("%t" + the_entry.theVar.varIndex + " = alloca i32, align 4");
					   }
					}
					}
					break;
				case 2 :
					// ./myCompiler.g:189:1: 
					{
					if ( state.backtracking==0 ) {
					   if (TRACEON)
					      System.out.println("declarations: ");
					}
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "declarations"



	// $ANTLR start "type"
	// ./myCompiler.g:195:1: type returns [Type attr_type] : ( INT | CHAR | FLOAT | DOUBLE | LONG | VOID | type_prefix type );
	public final Type type() throws RecognitionException {
		Type attr_type = null;


		try {
			// ./myCompiler.g:197:3: ( INT | CHAR | FLOAT | DOUBLE | LONG | VOID | type_prefix type )
			int alt3=7;
			switch ( input.LA(1) ) {
			case INT:
				{
				alt3=1;
				}
				break;
			case CHAR:
				{
				alt3=2;
				}
				break;
			case FLOAT:
				{
				alt3=3;
				}
				break;
			case DOUBLE:
				{
				alt3=4;
				}
				break;
			case LONG:
				{
				alt3=5;
				}
				break;
			case VOID:
				{
				alt3=6;
				}
				break;
			case 42:
			case 43:
			case 44:
			case 45:
			case 46:
			case 47:
			case 48:
			case 49:
				{
				alt3=7;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return attr_type;}
				NoViableAltException nvae =
					new NoViableAltException("", 3, 0, input);
				throw nvae;
			}
			switch (alt3) {
				case 1 :
					// ./myCompiler.g:197:3: INT
					{
					match(input,INT,FOLLOW_INT_in_type114); if (state.failed) return attr_type;
					if ( state.backtracking==0 ) { if (TRACEON) System.out.println("type: INT"); attr_type =Type.INT; }
					}
					break;
				case 2 :
					// ./myCompiler.g:198:3: CHAR
					{
					match(input,CHAR,FOLLOW_CHAR_in_type120); if (state.failed) return attr_type;
					if ( state.backtracking==0 ) { if (TRACEON) System.out.println("type: CHAR"); attr_type =Type.CHAR; }
					}
					break;
				case 3 :
					// ./myCompiler.g:199:3: FLOAT
					{
					match(input,FLOAT,FOLLOW_FLOAT_in_type126); if (state.failed) return attr_type;
					if ( state.backtracking==0 ) {if (TRACEON) System.out.println("type: FLOAT"); attr_type =Type.FLOAT; }
					}
					break;
				case 4 :
					// ./myCompiler.g:200:3: DOUBLE
					{
					match(input,DOUBLE,FOLLOW_DOUBLE_in_type132); if (state.failed) return attr_type;
					if ( state.backtracking==0 ) {if (TRACEON) System.out.println("type: DOUBLE"); attr_type =Type.FLOAT; }
					}
					break;
				case 5 :
					// ./myCompiler.g:201:3: LONG
					{
					match(input,LONG,FOLLOW_LONG_in_type138); if (state.failed) return attr_type;
					if ( state.backtracking==0 ) {if (TRACEON) System.out.println("type: LONG"); attr_type =Type.INT; }
					}
					break;
				case 6 :
					// ./myCompiler.g:202:3: VOID
					{
					match(input,VOID,FOLLOW_VOID_in_type144); if (state.failed) return attr_type;
					if ( state.backtracking==0 ) { if (TRACEON) System.out.println("type: VOID"); attr_type =Type.VOID; }
					}
					break;
				case 7 :
					// ./myCompiler.g:203:3: type_prefix type
					{
					pushFollow(FOLLOW_type_prefix_in_type150);
					type_prefix();
					state._fsp--;
					if (state.failed) return attr_type;
					pushFollow(FOLLOW_type_in_type152);
					type();
					state._fsp--;
					if (state.failed) return attr_type;
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return attr_type;
	}
	// $ANTLR end "type"



	// $ANTLR start "type_prefix"
	// ./myCompiler.g:206:1: type_prefix : ( 'register' | 'const' | 'signed' | 'unsigned' | 'static' | 'extrn' | 'volatile' | 'extern' );
	public final void type_prefix() throws RecognitionException {
		try {
			// ./myCompiler.g:206:12: ( 'register' | 'const' | 'signed' | 'unsigned' | 'static' | 'extrn' | 'volatile' | 'extern' )
			// ./myCompiler.g:
			{
			if ( (input.LA(1) >= 42 && input.LA(1) <= 49) ) {
				input.consume();
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "type_prefix"



	// $ANTLR start "statements"
	// ./myCompiler.g:217:1: statements : ( statement statements |);
	public final void statements() throws RecognitionException {
		try {
			// ./myCompiler.g:217:11: ( statement statements |)
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0==DO||LA4_0==FOR||LA4_0==IF||LA4_0==Identifier||LA4_0==WHILE) ) {
				alt4=1;
			}
			else if ( (LA4_0==52) ) {
				alt4=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return;}
				NoViableAltException nvae =
					new NoViableAltException("", 4, 0, input);
				throw nvae;
			}

			switch (alt4) {
				case 1 :
					// ./myCompiler.g:217:12: statement statements
					{
					pushFollow(FOLLOW_statement_in_statements193);
					statement();
					state._fsp--;
					if (state.failed) return;
					pushFollow(FOLLOW_statements_in_statements195);
					statements();
					state._fsp--;
					if (state.failed) return;
					}
					break;
				case 2 :
					// ./myCompiler.g:219:1: 
					{
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "statements"



	// $ANTLR start "statement"
	// ./myCompiler.g:222:1: statement : ( assign_stmt ';' | if_stmt | func_no_return_stmt ';' | for_stmt | while_stmt | do_while_stmt );
	public final void statement() throws RecognitionException {
		try {
			// ./myCompiler.g:222:10: ( assign_stmt ';' | if_stmt | func_no_return_stmt ';' | for_stmt | while_stmt | do_while_stmt )
			int alt5=6;
			switch ( input.LA(1) ) {
			case Identifier:
				{
				int LA5_1 = input.LA(2);
				if ( (LA5_1==38) ) {
					alt5=1;
				}
				else if ( (LA5_1==28) ) {
					alt5=3;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 5, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case IF:
				{
				alt5=2;
				}
				break;
			case FOR:
				{
				alt5=4;
				}
				break;
			case WHILE:
				{
				alt5=5;
				}
				break;
			case DO:
				{
				alt5=6;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return;}
				NoViableAltException nvae =
					new NoViableAltException("", 5, 0, input);
				throw nvae;
			}
			switch (alt5) {
				case 1 :
					// ./myCompiler.g:222:12: assign_stmt ';'
					{
					pushFollow(FOLLOW_assign_stmt_in_statement206);
					assign_stmt();
					state._fsp--;
					if (state.failed) return;
					match(input,35,FOLLOW_35_in_statement208); if (state.failed) return;
					}
					break;
				case 2 :
					// ./myCompiler.g:223:3: if_stmt
					{
					pushFollow(FOLLOW_if_stmt_in_statement212);
					if_stmt();
					state._fsp--;
					if (state.failed) return;
					}
					break;
				case 3 :
					// ./myCompiler.g:224:3: func_no_return_stmt ';'
					{
					pushFollow(FOLLOW_func_no_return_stmt_in_statement216);
					func_no_return_stmt();
					state._fsp--;
					if (state.failed) return;
					match(input,35,FOLLOW_35_in_statement218); if (state.failed) return;
					}
					break;
				case 4 :
					// ./myCompiler.g:225:3: for_stmt
					{
					pushFollow(FOLLOW_for_stmt_in_statement222);
					for_stmt();
					state._fsp--;
					if (state.failed) return;
					}
					break;
				case 5 :
					// ./myCompiler.g:226:3: while_stmt
					{
					pushFollow(FOLLOW_while_stmt_in_statement226);
					while_stmt();
					state._fsp--;
					if (state.failed) return;
					}
					break;
				case 6 :
					// ./myCompiler.g:227:3: do_while_stmt
					{
					pushFollow(FOLLOW_do_while_stmt_in_statement230);
					do_while_stmt();
					state._fsp--;
					if (state.failed) return;
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "statement"



	// $ANTLR start "while_stmt"
	// ./myCompiler.g:231:1: while_stmt : WHILE '(' cond_expression ')' block_stmt ;
	public final void while_stmt() throws RecognitionException {
		Info cond_expression2 =null;


		   String condStartLabel = newLabel();
		   String startLabel = newLabel();
		   String endLabel = newLabel();

		   TextCode.add("br label %" + condStartLabel);
		   TextCode.add("");
		   
		   TextCode.add(condStartLabel + ":");

		try {
			// ./myCompiler.g:242:3: ( WHILE '(' cond_expression ')' block_stmt )
			// ./myCompiler.g:242:3: WHILE '(' cond_expression ')' block_stmt
			{
			match(input,WHILE,FOLLOW_WHILE_in_while_stmt245); if (state.failed) return;
			match(input,28,FOLLOW_28_in_while_stmt247); if (state.failed) return;
			pushFollow(FOLLOW_cond_expression_in_while_stmt249);
			cond_expression2=cond_expression();
			state._fsp--;
			if (state.failed) return;
			match(input,29,FOLLOW_29_in_while_stmt251); if (state.failed) return;
			if ( state.backtracking==0 ) {
			   TextCode.add("br i1 %t" + cond_expression2.theVar.varIndex + ", label %" + startLabel + ", label %" + endLabel);
			   TextCode.add("");
			   TextCode.add(startLabel + ":");
			}
			pushFollow(FOLLOW_block_stmt_in_while_stmt256);
			block_stmt();
			state._fsp--;
			if (state.failed) return;
			if ( state.backtracking==0 ) {
			   TextCode.add("");
			   TextCode.add("br label %" + condStartLabel);
			   TextCode.add(endLabel + ":");
			}
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "while_stmt"



	// $ANTLR start "do_while_stmt"
	// ./myCompiler.g:256:1: do_while_stmt : DO block_stmt WHILE '(' cond_expression ')' ';' ;
	public final void do_while_stmt() throws RecognitionException {
		Info cond_expression3 =null;


		   String startLabel = newLabel();
		   String endLabel = newLabel();

		   TextCode.add("br label %" + startLabel);
		   TextCode.add("");
		   TextCode.add(startLabel + ":");

		try {
			// ./myCompiler.g:265:3: ( DO block_stmt WHILE '(' cond_expression ')' ';' )
			// ./myCompiler.g:265:3: DO block_stmt WHILE '(' cond_expression ')' ';'
			{
			match(input,DO,FOLLOW_DO_in_do_while_stmt272); if (state.failed) return;
			pushFollow(FOLLOW_block_stmt_in_do_while_stmt274);
			block_stmt();
			state._fsp--;
			if (state.failed) return;
			match(input,WHILE,FOLLOW_WHILE_in_do_while_stmt276); if (state.failed) return;
			match(input,28,FOLLOW_28_in_do_while_stmt278); if (state.failed) return;
			pushFollow(FOLLOW_cond_expression_in_do_while_stmt280);
			cond_expression3=cond_expression();
			state._fsp--;
			if (state.failed) return;
			match(input,29,FOLLOW_29_in_do_while_stmt282); if (state.failed) return;
			match(input,35,FOLLOW_35_in_do_while_stmt284); if (state.failed) return;
			if ( state.backtracking==0 ) {
			   TextCode.add("br i1 %t" + cond_expression3.theVar.varIndex + ", label %" + startLabel + ", label %" + endLabel);
			   TextCode.add("");
			   TextCode.add(endLabel + ":");
			}
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "do_while_stmt"



	// $ANTLR start "for_stmt"
	// ./myCompiler.g:274:1: for_stmt : FOR '(' assign_stmt ';' cond_expression ';' assign_stmt ')' block_stmt ;
	public final void for_stmt() throws RecognitionException {
		Info cond_expression4 =null;


		   String condStartLabel = newLabel();
		   String CounterStartLabel = newLabel();
		   String trueLabel = newLabel();
		   String endLabel = newLabel();

		   TextCode.add("");

		try {
			// ./myCompiler.g:283:1: ( FOR '(' assign_stmt ';' cond_expression ';' assign_stmt ')' block_stmt )
			// ./myCompiler.g:284:1: FOR '(' assign_stmt ';' cond_expression ';' assign_stmt ')' block_stmt
			{
			match(input,FOR,FOLLOW_FOR_in_for_stmt302); if (state.failed) return;
			match(input,28,FOLLOW_28_in_for_stmt304); if (state.failed) return;
			pushFollow(FOLLOW_assign_stmt_in_for_stmt306);
			assign_stmt();
			state._fsp--;
			if (state.failed) return;
			match(input,35,FOLLOW_35_in_for_stmt308); if (state.failed) return;
			if ( state.backtracking==0 ) {
			   TextCode.add("br label %" + condStartLabel);
			   TextCode.add("");
			   TextCode.add(condStartLabel + ":");
			}
			pushFollow(FOLLOW_cond_expression_in_for_stmt312);
			cond_expression4=cond_expression();
			state._fsp--;
			if (state.failed) return;
			match(input,35,FOLLOW_35_in_for_stmt314); if (state.failed) return;
			if ( state.backtracking==0 ) {
			   TextCode.add("br i1 %t" + cond_expression4.theVar.varIndex + ", label %" + trueLabel + ", label %" + endLabel);

			   TextCode.add("");
			   TextCode.add(CounterStartLabel + ":" + "    ;counter");
			}
			pushFollow(FOLLOW_assign_stmt_in_for_stmt318);
			assign_stmt();
			state._fsp--;
			if (state.failed) return;
			match(input,29,FOLLOW_29_in_for_stmt320); if (state.failed) return;
			if ( state.backtracking==0 ) {
			   TextCode.add("br label %" + condStartLabel);
			   TextCode.add(trueLabel + ":" + "    ;trueLabel");
			}
			pushFollow(FOLLOW_block_stmt_in_for_stmt324);
			block_stmt();
			state._fsp--;
			if (state.failed) return;
			if ( state.backtracking==0 ) {
			   TextCode.add("br label %" + CounterStartLabel);
			   TextCode.add("");
			   TextCode.add(endLabel + ":" + "    ;endLabel");
			}
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "for_stmt"



	// $ANTLR start "if_stmt"
	// ./myCompiler.g:311:1: if_stmt : IF '(' cond_expression ')' block_stmt if_then_stmt[endLabel] ;
	public final void if_stmt() throws RecognitionException {
		Info cond_expression5 =null;


		   String trueLabel = newLabel();
		   String falseLabel = newLabel();
		   String endLabel = newLabel();

		   TextCode.add("");

		try {
			// ./myCompiler.g:319:1: ( IF '(' cond_expression ')' block_stmt if_then_stmt[endLabel] )
			// ./myCompiler.g:320:1: IF '(' cond_expression ')' block_stmt if_then_stmt[endLabel]
			{
			match(input,IF,FOLLOW_IF_in_if_stmt355); if (state.failed) return;
			match(input,28,FOLLOW_28_in_if_stmt357); if (state.failed) return;
			pushFollow(FOLLOW_cond_expression_in_if_stmt359);
			cond_expression5=cond_expression();
			state._fsp--;
			if (state.failed) return;
			match(input,29,FOLLOW_29_in_if_stmt361); if (state.failed) return;
			if ( state.backtracking==0 ) {
			   TextCode.add("br i1 %t" + cond_expression5.theVar.varIndex + ", label %" + trueLabel + ", label %" + falseLabel);
			   TextCode.add("");
			   TextCode.add(trueLabel + ":");
			}
			pushFollow(FOLLOW_block_stmt_in_if_stmt367);
			block_stmt();
			state._fsp--;
			if (state.failed) return;
			if ( state.backtracking==0 ) {
			   TextCode.add("br label %" + endLabel);
			   TextCode.add("");
			   TextCode.add(falseLabel + ":");
			}
			pushFollow(FOLLOW_if_then_stmt_in_if_stmt372);
			if_then_stmt(endLabel);
			state._fsp--;
			if (state.failed) return;
			if ( state.backtracking==0 ) {
			   TextCode.add("");
			   TextCode.add("br label %" + endLabel);
			   TextCode.add(endLabel + ":");
			}
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "if_stmt"



	// $ANTLR start "if_then_stmt"
	// ./myCompiler.g:341:1: if_then_stmt[String endLabel] : ( ( ELSE IF )=> ELSE IF '(' cond_expression ')' block_stmt if_then_stmt[endLabel] | ( ELSE )=> ELSE block_stmt |);
	public final void if_then_stmt(String endLabel) throws RecognitionException {
		Info cond_expression6 =null;


		   String trueLabel = newLabel();
		   String falseLabel = newLabel();


		try {
			// ./myCompiler.g:347:0: ( ( ELSE IF )=> ELSE IF '(' cond_expression ')' block_stmt if_then_stmt[endLabel] | ( ELSE )=> ELSE block_stmt |)
			int alt6=3;
			int LA6_0 = input.LA(1);
			if ( (LA6_0==ELSE) ) {
				int LA6_1 = input.LA(2);
				if ( (synpred1_myCompiler()) ) {
					alt6=1;
				}
				else if ( (synpred2_myCompiler()) ) {
					alt6=2;
				}
				else if ( (true) ) {
					alt6=3;
				}

			}
			else if ( (LA6_0==DO||LA6_0==FOR||LA6_0==IF||LA6_0==Identifier||LA6_0==WHILE||LA6_0==52) ) {
				alt6=3;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return;}
				NoViableAltException nvae =
					new NoViableAltException("", 6, 0, input);
				throw nvae;
			}

			switch (alt6) {
				case 1 :
					// ./myCompiler.g:348:0: ( ELSE IF )=> ELSE IF '(' cond_expression ')' block_stmt if_then_stmt[endLabel]
					{
					match(input,ELSE,FOLLOW_ELSE_in_if_then_stmt406); if (state.failed) return;
					match(input,IF,FOLLOW_IF_in_if_then_stmt408); if (state.failed) return;
					match(input,28,FOLLOW_28_in_if_then_stmt410); if (state.failed) return;
					pushFollow(FOLLOW_cond_expression_in_if_then_stmt412);
					cond_expression6=cond_expression();
					state._fsp--;
					if (state.failed) return;
					match(input,29,FOLLOW_29_in_if_then_stmt414); if (state.failed) return;
					if ( state.backtracking==0 ) {
					   TextCode.add("br i1 %t" + cond_expression6.theVar.varIndex + ", label %" + trueLabel + ", label %" + falseLabel);
					   TextCode.add("");
					   TextCode.add(trueLabel + ":");
					}
					pushFollow(FOLLOW_block_stmt_in_if_then_stmt418);
					block_stmt();
					state._fsp--;
					if (state.failed) return;
					if ( state.backtracking==0 ) {
					   TextCode.add("br label %" + endLabel);
					   TextCode.add("");
					   TextCode.add(falseLabel + ":");
					}
					pushFollow(FOLLOW_if_then_stmt_in_if_then_stmt423);
					if_then_stmt(endLabel);
					state._fsp--;
					if (state.failed) return;
					}
					break;
				case 2 :
					// ./myCompiler.g:361:3: ( ELSE )=> ELSE block_stmt
					{
					match(input,ELSE,FOLLOW_ELSE_in_if_then_stmt434); if (state.failed) return;
					pushFollow(FOLLOW_block_stmt_in_if_then_stmt436);
					block_stmt();
					state._fsp--;
					if (state.failed) return;
					if ( state.backtracking==0 ) {
					   TextCode.add("br label %" + endLabel);
					}
					}
					break;
				case 3 :
					// ./myCompiler.g:366:1: 
					{
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "if_then_stmt"



	// $ANTLR start "block_stmt"
	// ./myCompiler.g:368:1: block_stmt : ( statement | '{' statements '}' );
	public final void block_stmt() throws RecognitionException {
		try {
			// ./myCompiler.g:369:4: ( statement | '{' statements '}' )
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==DO||LA7_0==FOR||LA7_0==IF||LA7_0==Identifier||LA7_0==WHILE) ) {
				alt7=1;
			}
			else if ( (LA7_0==50) ) {
				alt7=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return;}
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}

			switch (alt7) {
				case 1 :
					// ./myCompiler.g:370:4: statement
					{
					pushFollow(FOLLOW_statement_in_block_stmt470);
					statement();
					state._fsp--;
					if (state.failed) return;
					}
					break;
				case 2 :
					// ./myCompiler.g:372:4: '{' statements '}'
					{
					match(input,50,FOLLOW_50_in_block_stmt481); if (state.failed) return;
					pushFollow(FOLLOW_statements_in_block_stmt483);
					statements();
					state._fsp--;
					if (state.failed) return;
					match(input,52,FOLLOW_52_in_block_stmt485); if (state.failed) return;
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "block_stmt"



	// $ANTLR start "assign_stmt"
	// ./myCompiler.g:376:1: assign_stmt : Identifier '=' arith_expression ;
	public final void assign_stmt() throws RecognitionException {
		Token Identifier8=null;
		Info arith_expression7 =null;

		try {
			// ./myCompiler.g:376:12: ( Identifier '=' arith_expression )
			// ./myCompiler.g:376:14: Identifier '=' arith_expression
			{
			Identifier8=(Token)match(input,Identifier,FOLLOW_Identifier_in_assign_stmt494); if (state.failed) return;
			match(input,38,FOLLOW_38_in_assign_stmt496); if (state.failed) return;
			pushFollow(FOLLOW_arith_expression_in_assign_stmt498);
			arith_expression7=arith_expression();
			state._fsp--;
			if (state.failed) return;
			if ( state.backtracking==0 ) {
			   Info theRHS = arith_expression7;
			   Info theLHS = symtab.get((Identifier8!=null?Identifier8.getText():null)); 

			   if ((theLHS.theType == Type.INT) &&
			      (theRHS.theType == Type.INT)) {         
			      // issue store insruction.
			      // Ex: store i32 %tx, i32* %ty
			      TextCode.add("store i32 %t" + theRHS.theVar.varIndex + ", i32* %t" + theLHS.theVar.varIndex);
			   } else if ((theLHS.theType == Type.INT) &&
			   (theRHS.theType == Type.CONST_INT)) {
			      // issue store insruction.
			      // Ex: store i32 value, i32* %ty
			      TextCode.add("store i32 " + theRHS.theVar.iValue + ", i32* %t" + theLHS.theVar.varIndex);           
			   }
			}
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "assign_stmt"



	// $ANTLR start "func_no_return_stmt"
	// ./myCompiler.g:396:1: func_no_return_stmt : ( Identifier '(' )=>funcName= Identifier '(' (arg1= argument ( ',' arg2= argument )* )? ')' ;
	public final void func_no_return_stmt() throws RecognitionException {
		Token funcName=null;
		ParserRuleReturnScope arg1 =null;
		ParserRuleReturnScope arg2 =null;

		try {
			// ./myCompiler.g:396:20: ( ( Identifier '(' )=>funcName= Identifier '(' (arg1= argument ( ',' arg2= argument )* )? ')' )
			// ./myCompiler.g:396:22: ( Identifier '(' )=>funcName= Identifier '(' (arg1= argument ( ',' arg2= argument )* )? ')'
			{
			funcName=(Token)match(input,Identifier,FOLLOW_Identifier_in_func_no_return_stmt530); if (state.failed) return;
			match(input,28,FOLLOW_28_in_func_no_return_stmt532); if (state.failed) return;
			// ./myCompiler.g:396:68: (arg1= argument ( ',' arg2= argument )* )?
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==Floating_point_constant||(LA9_0 >= Identifier && LA9_0 <= Integer_constant)||LA9_0==STRING_LITERAL||(LA9_0 >= 27 && LA9_0 <= 28)||LA9_0==33) ) {
				alt9=1;
			}
			switch (alt9) {
				case 1 :
					// ./myCompiler.g:396:69: arg1= argument ( ',' arg2= argument )*
					{
					pushFollow(FOLLOW_argument_in_func_no_return_stmt539);
					arg1=argument();
					state._fsp--;
					if (state.failed) return;
					// ./myCompiler.g:396:85: ( ',' arg2= argument )*
					loop8:
					while (true) {
						int alt8=2;
						int LA8_0 = input.LA(1);
						if ( (LA8_0==32) ) {
							alt8=1;
						}

						switch (alt8) {
						case 1 :
							// ./myCompiler.g:396:86: ',' arg2= argument
							{
							match(input,32,FOLLOW_32_in_func_no_return_stmt542); if (state.failed) return;
							pushFollow(FOLLOW_argument_in_func_no_return_stmt548);
							arg2=argument();
							state._fsp--;
							if (state.failed) return;
							}
							break;

						default :
							break loop8;
						}
					}

					}
					break;

			}

			match(input,29,FOLLOW_29_in_func_no_return_stmt554); if (state.failed) return;
			if ( state.backtracking==0 ) {
			   if(funcName.getText().contains("printf")){
			      int arg2VarCount = -1;
			      String arg1String,arg2String;
			      int arg1Len = -1;
			      int StrId = constantCount;
			      if(arg1 != null){

			         //sub \n to \0A
			         arg1Len = (arg1!=null?input.toString(arg1.start,arg1.stop):null).length();
			         arg1String = (arg1!=null?input.toString(arg1.start,arg1.stop):null);
			         arg1String = arg1String.replace("\\n","\\0A");
			         arg1Len -= (arg1String.length() - (arg1!=null?input.toString(arg1.start,arg1.stop):null).length());

			         // remove length of double qoute
			         arg1Len -= 2;

			         // add \\00 add
			         String arg1StringBegin = arg1String.substring(0,arg1String.length()-1);
			         String arg1StringEnd = arg1String.substring(arg1String.length()-1);
			         arg1String =  arg1StringBegin + "\\00" + arg1StringEnd;
			         arg1Len += 1;

			         ConstantCode.add("@.str." + constantCount + " = private unnamed_addr constant [" + arg1Len + " x i8]c" + arg1String + ", align 1");
			      }

			      if ( arg2 == null ) {
			            TextCode.add("%t" + varCount + "=call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([" + arg1Len + "x i8], [" + arg1Len + " x i8]* @.str." + StrId + ", i64 0, i64 0))");
			      } else {
			         if((arg2!=null?((myCompilerParser.argument_return)arg2).theInfo:null).theType == Type.INT){
			            TextCode.add("%t" + varCount + "=call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([" + arg1Len + "x i8], [" + arg1Len + " x i8]* @.str." + StrId + ", i64 0, i64 0), i32 %t" + (arg2!=null?((myCompilerParser.argument_return)arg2).theInfo:null).theVar.varIndex + ")");
			         }else if((arg2!=null?((myCompilerParser.argument_return)arg2).theInfo:null).theType == Type.CONST_INT){
			            TextCode.add("%t" + varCount + "=call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([" + arg1Len + "x i8], [" + arg1Len + " x i8]* @.str." + StrId + ", i64 0, i64 0), i32 " + (arg2!=null?((myCompilerParser.argument_return)arg2).theInfo:null).theVar.iValue + ")");
			         }
			      }
			      varCount++;
			      constantCount++;
			   }
			}
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "func_no_return_stmt"


	public static class argument_return extends ParserRuleReturnScope {
		public Info theInfo;
	};


	// $ANTLR start "argument"
	// ./myCompiler.g:439:1: argument returns [Info theInfo] : ( arith_expression |s= STRING_LITERAL );
	public final myCompilerParser.argument_return argument() throws RecognitionException {
		myCompilerParser.argument_return retval = new myCompilerParser.argument_return();
		retval.start = input.LT(1);

		Token s=null;
		Info arith_expression9 =null;

		try {
			// ./myCompiler.g:441:1: ( arith_expression |s= STRING_LITERAL )
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0==Floating_point_constant||(LA10_0 >= Identifier && LA10_0 <= Integer_constant)||(LA10_0 >= 27 && LA10_0 <= 28)||LA10_0==33) ) {
				alt10=1;
			}
			else if ( (LA10_0==STRING_LITERAL) ) {
				alt10=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}

			switch (alt10) {
				case 1 :
					// ./myCompiler.g:442:1: arith_expression
					{
					pushFollow(FOLLOW_arith_expression_in_argument572);
					arith_expression9=arith_expression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {retval.theInfo = arith_expression9;}
					}
					break;
				case 2 :
					// ./myCompiler.g:444:3: s= STRING_LITERAL
					{
					s=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_argument582); if (state.failed) return retval;
					}
					break;

			}
			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "argument"



	// $ANTLR start "cond_expression"
	// ./myCompiler.g:447:1: cond_expression returns [Info theInfo] : a= arith_expression b= compare_expression_back ;
	public final Info cond_expression() throws RecognitionException {
		Info theInfo = null;


		Info a =null;
		ParserRuleReturnScope b =null;

		theInfo = new Info();
		try {
			// ./myCompiler.g:450:2: (a= arith_expression b= compare_expression_back )
			// ./myCompiler.g:451:2: a= arith_expression b= compare_expression_back
			{
			pushFollow(FOLLOW_arith_expression_in_cond_expression614);
			a=arith_expression();
			state._fsp--;
			if (state.failed) return theInfo;
			pushFollow(FOLLOW_compare_expression_back_in_cond_expression618);
			b=compare_expression_back();
			state._fsp--;
			if (state.failed) return theInfo;
			if ( state.backtracking==0 ) {
			   String opString = "eq";

			   if((b!=null?((myCompilerParser.compare_expression_back_return)b).op:0) == 1) opString = "eq";
			   else if((b!=null?((myCompilerParser.compare_expression_back_return)b).op:0) == 2) opString = "sge";
			   else if((b!=null?((myCompilerParser.compare_expression_back_return)b).op:0) == 3) opString = "sle";
			   else if((b!=null?((myCompilerParser.compare_expression_back_return)b).op:0) == 4) opString = "ne";
			   else if((b!=null?((myCompilerParser.compare_expression_back_return)b).op:0) == 5) opString = "eq";
			   else if((b!=null?((myCompilerParser.compare_expression_back_return)b).op:0) == 6) opString = "eq";
			   else if((b!=null?((myCompilerParser.compare_expression_back_return)b).op:0) == 7) opString = "sgt";
			   else if((b!=null?((myCompilerParser.compare_expression_back_return)b).op:0) == 8) opString = "slt";
			   else System.err.println("Unrecognized op");

			   TextCode.add(";[DEBUG] A type: " + a.theType + "; B type: " + (b!=null?((myCompilerParser.compare_expression_back_return)b).theInfo:null).theType);

			   // code generation.                 
			   if ((a.theType == Type.INT) &&
			      ((b!=null?((myCompilerParser.compare_expression_back_return)b).theInfo:null).theType == Type.INT)) {
			      TextCode.add("%t" + varCount + " = icmp " + opString + " i32 %t" + a.theVar.varIndex + ", %t" + (b!=null?((myCompilerParser.compare_expression_back_return)b).theInfo:null).theVar.varIndex);
			   } else if ((a.theType == Type.INT) &&
			   ((b!=null?((myCompilerParser.compare_expression_back_return)b).theInfo:null).theType == Type.CONST_INT)) {
			      TextCode.add("%t" + varCount + " = icmp " + opString + " i32 %t" + a.theVar.varIndex + ", " + (b!=null?((myCompilerParser.compare_expression_back_return)b).theInfo:null).theVar.iValue);
			   }else if ((a.theType == Type.CONST_INT) &&
			   ((b!=null?((myCompilerParser.compare_expression_back_return)b).theInfo:null).theType == Type.INT)) {
			      TextCode.add("%t" + varCount + " = icmp " + opString + " i32 " + a.theVar.iValue + ", %t" + (b!=null?((myCompilerParser.compare_expression_back_return)b).theInfo:null).theVar.varIndex);
			   } else if ((a.theType == Type.CONST_INT) &&
			   ((b!=null?((myCompilerParser.compare_expression_back_return)b).theInfo:null).theType == Type.CONST_INT)) {
			      TextCode.add("%t" + varCount + " = icmp " + opString + " i32 " + a.theVar.iValue + ", " + (b!=null?((myCompilerParser.compare_expression_back_return)b).theInfo:null).theVar.iValue);
			   }

			    // Update arith_expression's theInfo.
			   theInfo.theType = Type.BOOL;
			   theInfo.theVar.varIndex = varCount;
			   varCount++;
			}
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return theInfo;
	}
	// $ANTLR end "cond_expression"


	public static class compare_expression_back_return extends ParserRuleReturnScope {
		public int op;
		public Info theInfo;
	};


	// $ANTLR start "compare_expression_back"
	// ./myCompiler.g:489:1: compare_expression_back returns [int op, Info theInfo] : ( '==' e= arith_expression | '>=' e= arith_expression | '<=' e= arith_expression | '!=' e= arith_expression | '&&' e= arith_expression | '||' e= arith_expression | '>' e= arith_expression | '<' e= arith_expression );
	public final myCompilerParser.compare_expression_back_return compare_expression_back() throws RecognitionException {
		myCompilerParser.compare_expression_back_return retval = new myCompilerParser.compare_expression_back_return();
		retval.start = input.LT(1);

		Info e =null;

		try {
			// ./myCompiler.g:490:1: ( '==' e= arith_expression | '>=' e= arith_expression | '<=' e= arith_expression | '!=' e= arith_expression | '&&' e= arith_expression | '||' e= arith_expression | '>' e= arith_expression | '<' e= arith_expression )
			int alt11=8;
			switch ( input.LA(1) ) {
			case 39:
				{
				alt11=1;
				}
				break;
			case 41:
				{
				alt11=2;
				}
				break;
			case 37:
				{
				alt11=3;
				}
				break;
			case 24:
				{
				alt11=4;
				}
				break;
			case 26:
				{
				alt11=5;
				}
				break;
			case 51:
				{
				alt11=6;
				}
				break;
			case 40:
				{
				alt11=7;
				}
				break;
			case 36:
				{
				alt11=8;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}
			switch (alt11) {
				case 1 :
					// ./myCompiler.g:491:1: '==' e= arith_expression
					{
					match(input,39,FOLLOW_39_in_compare_expression_back635); if (state.failed) return retval;
					pushFollow(FOLLOW_arith_expression_in_compare_expression_back641);
					e=arith_expression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {retval.theInfo = e; retval.op = 1;}
					}
					break;
				case 2 :
					// ./myCompiler.g:492:3: '>=' e= arith_expression
					{
					match(input,41,FOLLOW_41_in_compare_expression_back647); if (state.failed) return retval;
					pushFollow(FOLLOW_arith_expression_in_compare_expression_back653);
					e=arith_expression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {retval.theInfo = e; retval.op = 2;}
					}
					break;
				case 3 :
					// ./myCompiler.g:493:3: '<=' e= arith_expression
					{
					match(input,37,FOLLOW_37_in_compare_expression_back660); if (state.failed) return retval;
					pushFollow(FOLLOW_arith_expression_in_compare_expression_back666);
					e=arith_expression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {retval.theInfo = e; retval.op = 3;}
					}
					break;
				case 4 :
					// ./myCompiler.g:494:3: '!=' e= arith_expression
					{
					match(input,24,FOLLOW_24_in_compare_expression_back673); if (state.failed) return retval;
					pushFollow(FOLLOW_arith_expression_in_compare_expression_back679);
					e=arith_expression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {retval.theInfo = e; retval.op = 4;}
					}
					break;
				case 5 :
					// ./myCompiler.g:495:3: '&&' e= arith_expression
					{
					match(input,26,FOLLOW_26_in_compare_expression_back685); if (state.failed) return retval;
					pushFollow(FOLLOW_arith_expression_in_compare_expression_back691);
					e=arith_expression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {retval.theInfo = e; retval.op = 5;}
					}
					break;
				case 6 :
					// ./myCompiler.g:496:3: '||' e= arith_expression
					{
					match(input,51,FOLLOW_51_in_compare_expression_back697); if (state.failed) return retval;
					pushFollow(FOLLOW_arith_expression_in_compare_expression_back703);
					e=arith_expression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {retval.theInfo = e; retval.op = 6;}
					}
					break;
				case 7 :
					// ./myCompiler.g:497:3: '>' e= arith_expression
					{
					match(input,40,FOLLOW_40_in_compare_expression_back709); if (state.failed) return retval;
					pushFollow(FOLLOW_arith_expression_in_compare_expression_back715);
					e=arith_expression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {retval.theInfo = e; retval.op = 7;}
					}
					break;
				case 8 :
					// ./myCompiler.g:498:3: '<' e= arith_expression
					{
					match(input,36,FOLLOW_36_in_compare_expression_back721); if (state.failed) return retval;
					pushFollow(FOLLOW_arith_expression_in_compare_expression_back727);
					e=arith_expression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {retval.theInfo = e; retval.op = 8;}
					}
					break;

			}
			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "compare_expression_back"



	// $ANTLR start "arith_expression"
	// ./myCompiler.g:501:1: arith_expression returns [Info theInfo] : a= multExpr ( '+' b= multExpr | '-' b= multExpr )* ;
	public final Info arith_expression() throws RecognitionException {
		Info theInfo = null;


		Info a =null;
		Info b =null;

		theInfo = new Info();
		try {
			// ./myCompiler.g:504:3: (a= multExpr ( '+' b= multExpr | '-' b= multExpr )* )
			// ./myCompiler.g:504:3: a= multExpr ( '+' b= multExpr | '-' b= multExpr )*
			{
			pushFollow(FOLLOW_multExpr_in_arith_expression761);
			a=multExpr();
			state._fsp--;
			if (state.failed) return theInfo;
			if ( state.backtracking==0 ) { theInfo =a; }
			// ./myCompiler.g:505:3: ( '+' b= multExpr | '-' b= multExpr )*
			loop12:
			while (true) {
				int alt12=3;
				int LA12_0 = input.LA(1);
				if ( (LA12_0==31) ) {
					alt12=1;
				}
				else if ( (LA12_0==33) ) {
					alt12=2;
				}

				switch (alt12) {
				case 1 :
					// ./myCompiler.g:505:3: '+' b= multExpr
					{
					match(input,31,FOLLOW_31_in_arith_expression767); if (state.failed) return theInfo;
					pushFollow(FOLLOW_multExpr_in_arith_expression771);
					b=multExpr();
					state._fsp--;
					if (state.failed) return theInfo;
					if ( state.backtracking==0 ) {
					   // We need to do type checking first.
					   // ...

					   // code generation.                  
					   if ((a.theType == Type.INT) &&
					      (b.theType == Type.INT)) {
					      TextCode.add("%t" + varCount + " = add nsw i32 %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
					   } else if ((a.theType == Type.INT) &&
					   (b.theType == Type.CONST_INT)) {
					      TextCode.add("%t" + varCount + " = add nsw i32 %t" + theInfo.theVar.varIndex + ", " + b.theVar.iValue);
					   }else if ((a.theType == Type.CONST_INT) &&
					   (b.theType == Type.INT)) {
					      TextCode.add("%t" + varCount + " = add nsw i32 " + theInfo.theVar.iValue + ", %t"+   b.theVar.varIndex);
					   } else if ((a.theType == Type.CONST_INT) &&
					   (b.theType == Type.CONST_INT)) {
					      TextCode.add("%t" + varCount + " = add nsw i32 " + theInfo.theVar.iValue + ", " + b.theVar.iValue);
					   }

					   // Update arith_expression's theInfo.
					   theInfo.theType = Type.INT;
					   theInfo.theVar.varIndex = varCount;
					   varCount ++;
					}
					}
					break;
				case 2 :
					// ./myCompiler.g:530:3: '-' b= multExpr
					{
					match(input,33,FOLLOW_33_in_arith_expression777); if (state.failed) return theInfo;
					pushFollow(FOLLOW_multExpr_in_arith_expression781);
					b=multExpr();
					state._fsp--;
					if (state.failed) return theInfo;
					if ( state.backtracking==0 ) {
					   // We need to do type checking first.
					   // ...

					   // code generation.                  
					   if ((a.theType == Type.INT) &&
					      (b.theType == Type.INT)) {
					      TextCode.add("%t" + varCount + " = sub nsw i32 %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
					   } else if ((a.theType == Type.INT) &&
					   (b.theType == Type.CONST_INT)) {
					      TextCode.add("%t" + varCount + " = sub nsw i32 %t" + theInfo.theVar.varIndex + ", " + b.theVar.iValue);
					   }else if ((a.theType == Type.CONST_INT) &&
					   (b.theType == Type.INT)) {
					      TextCode.add("%t" + varCount + " = sub nsw i32 " + theInfo.theVar.iValue + ", %t"+   b.theVar.varIndex);
					   } else if ((a.theType == Type.CONST_INT) &&
					   (b.theType == Type.CONST_INT)) {
					      TextCode.add("%t" + varCount + " = sub nsw i32 " + theInfo.theVar.iValue + ", " + b.theVar.iValue);
					   }
					   
					   // Update arith_expression's theInfo.
					   theInfo.theType = Type.INT;
					   theInfo.theVar.varIndex = varCount;
					   varCount ++;
					}
					}
					break;

				default :
					break loop12;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return theInfo;
	}
	// $ANTLR end "arith_expression"



	// $ANTLR start "multExpr"
	// ./myCompiler.g:558:1: multExpr returns [Info theInfo] : a= signExpr ( '*' b= signExpr | '/' b= signExpr | '%' b= signExpr )* ;
	public final Info multExpr() throws RecognitionException {
		Info theInfo = null;


		Info a =null;
		Info b =null;

		theInfo = new Info();
		try {
			// ./myCompiler.g:561:3: (a= signExpr ( '*' b= signExpr | '/' b= signExpr | '%' b= signExpr )* )
			// ./myCompiler.g:561:3: a= signExpr ( '*' b= signExpr | '/' b= signExpr | '%' b= signExpr )*
			{
			pushFollow(FOLLOW_signExpr_in_multExpr806);
			a=signExpr();
			state._fsp--;
			if (state.failed) return theInfo;
			if ( state.backtracking==0 ) { theInfo =a; }
			// ./myCompiler.g:562:3: ( '*' b= signExpr | '/' b= signExpr | '%' b= signExpr )*
			loop13:
			while (true) {
				int alt13=4;
				switch ( input.LA(1) ) {
				case 30:
					{
					alt13=1;
					}
					break;
				case 34:
					{
					alt13=2;
					}
					break;
				case 25:
					{
					alt13=3;
					}
					break;
				}
				switch (alt13) {
				case 1 :
					// ./myCompiler.g:562:3: '*' b= signExpr
					{
					match(input,30,FOLLOW_30_in_multExpr812); if (state.failed) return theInfo;
					pushFollow(FOLLOW_signExpr_in_multExpr816);
					b=signExpr();
					state._fsp--;
					if (state.failed) return theInfo;
					if ( state.backtracking==0 ) {
					   // We need to do type checking first.
					   // ...

					   // code generation.                 
					   if ((a.theType == Type.INT) &&
					      (b.theType == Type.INT)) {
					      TextCode.add("%t" + varCount + " = mul nsw i32 %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);

					   } else if ((a.theType == Type.INT) &&
					   (b.theType == Type.CONST_INT)) {
					      TextCode.add("%t" + varCount + " = mul nsw i32 %t" + theInfo.theVar.varIndex + ", " + b.theVar.iValue);

					   }else if ((a.theType == Type.CONST_INT) &&
					   (b.theType == Type.INT)) {
					      TextCode.add("%t" + varCount + " = mul nsw i32 " + theInfo.theVar.iValue + ", %t"+   b.theVar.varIndex);

					   } else if ((a.theType == Type.CONST_INT) &&
					   (b.theType == Type.CONST_INT)) {
					      TextCode.add("%t" + varCount + " = mul nsw i32 " + theInfo.theVar.iValue + ", " + b.theVar.iValue);
					   }
					      // Update arith_expression's theInfo.
					   theInfo.theType = Type.INT;
					   theInfo.theVar.varIndex = varCount;
					   varCount ++;
					}
					}
					break;
				case 2 :
					// ./myCompiler.g:589:3: '/' b= signExpr
					{
					match(input,34,FOLLOW_34_in_multExpr822); if (state.failed) return theInfo;
					pushFollow(FOLLOW_signExpr_in_multExpr826);
					b=signExpr();
					state._fsp--;
					if (state.failed) return theInfo;
					if ( state.backtracking==0 ) {
					   // We need to do type checking first.
					   // ...

					   // code generation.                 
					   if ((a.theType == Type.INT) &&
					      (b.theType == Type.INT)) {
					      TextCode.add("%t" + varCount + " = div nsw i32 %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
					   } else if ((a.theType == Type.INT) &&
					   (b.theType == Type.CONST_INT)) {
					      TextCode.add("%t" + varCount + " = div nsw i32 %t" + theInfo.theVar.varIndex + ", " + b.theVar.iValue);
					   }else if ((a.theType == Type.CONST_INT) &&
					   (b.theType == Type.INT)) {
					      TextCode.add("%t" + varCount + " = div nsw i32 " + theInfo.theVar.iValue + ", %t"+   b.theVar.varIndex);
					   } else if ((a.theType == Type.CONST_INT) &&
					   (b.theType == Type.CONST_INT)) {
					      TextCode.add("%t" + varCount + " = div nsw i32 " + theInfo.theVar.iValue + ", " + b.theVar.iValue);
					   }
					   
					   // Update arith_expression's theInfo.
					   theInfo.theType = Type.INT;
					   theInfo.theVar.varIndex = varCount;
					   varCount ++;
					}
					}
					break;
				case 3 :
					// ./myCompiler.g:614:3: '%' b= signExpr
					{
					match(input,25,FOLLOW_25_in_multExpr832); if (state.failed) return theInfo;
					pushFollow(FOLLOW_signExpr_in_multExpr836);
					b=signExpr();
					state._fsp--;
					if (state.failed) return theInfo;
					if ( state.backtracking==0 ) {
					   // We need to do type checking first.
					   // ...

					   // code generation.                 
					   if ((a.theType == Type.INT) &&
					      (b.theType == Type.INT)) {
					      TextCode.add("%t" + varCount + " = srem i32 %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
					   } else if ((a.theType == Type.INT) &&
					   (b.theType == Type.CONST_INT)) {
					      TextCode.add("%t" + varCount + " = srem i32 %t" + theInfo.theVar.varIndex + ", " + b.theVar.iValue);
					   }else if ((a.theType == Type.CONST_INT) &&
					   (b.theType == Type.INT)) {
					      TextCode.add("%t" + varCount + " = srem i32 " + theInfo.theVar.iValue + ", %t"+   b.theVar.varIndex);
					   } else if ((a.theType == Type.CONST_INT) &&
					   (b.theType == Type.CONST_INT)) {
					      TextCode.add("%t" + varCount + " = srem 32 " + theInfo.theVar.iValue + ", " + b.theVar.iValue);
					   }

					   // Update arith_expression's theInfo.
					   theInfo.theType = Type.INT;
					   theInfo.theVar.varIndex = varCount;
					   varCount ++;
					}
					}
					break;

				default :
					break loop13;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return theInfo;
	}
	// $ANTLR end "multExpr"



	// $ANTLR start "signExpr"
	// ./myCompiler.g:642:1: signExpr returns [Info theInfo] : (a= primaryExpr | '-' a= primaryExpr );
	public final Info signExpr() throws RecognitionException {
		Info theInfo = null;


		Info a =null;

		theInfo = new Info();
		try {
			// ./myCompiler.g:645:3: (a= primaryExpr | '-' a= primaryExpr )
			int alt14=2;
			int LA14_0 = input.LA(1);
			if ( (LA14_0==Floating_point_constant||(LA14_0 >= Identifier && LA14_0 <= Integer_constant)||(LA14_0 >= 27 && LA14_0 <= 28)) ) {
				alt14=1;
			}
			else if ( (LA14_0==33) ) {
				alt14=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return theInfo;}
				NoViableAltException nvae =
					new NoViableAltException("", 14, 0, input);
				throw nvae;
			}

			switch (alt14) {
				case 1 :
					// ./myCompiler.g:645:3: a= primaryExpr
					{
					pushFollow(FOLLOW_primaryExpr_in_signExpr862);
					a=primaryExpr();
					state._fsp--;
					if (state.failed) return theInfo;
					if ( state.backtracking==0 ) { theInfo =a; }
					}
					break;
				case 2 :
					// ./myCompiler.g:647:3: '-' a= primaryExpr
					{
					match(input,33,FOLLOW_33_in_signExpr870); if (state.failed) return theInfo;
					pushFollow(FOLLOW_primaryExpr_in_signExpr874);
					a=primaryExpr();
					state._fsp--;
					if (state.failed) return theInfo;
					if ( state.backtracking==0 ) {
					   // We need to do type checking first.
					   // ...

					   // code generation.                 
					   if (a.theType == Type.INT) {
					      TextCode.add("%t" + varCount + " = mul nsw i32 %t" + theInfo.theVar.varIndex + ", %t" + -1);

					      // Update arith_expression's theInfo.
					      theInfo.theType = Type.INT;
					      theInfo.theVar.varIndex = varCount;
					      varCount ++;
					   } else if (a.theType == Type.CONST_INT) {
					      // Update arith_expression's theInfo.
					      theInfo.theType = Type.CONST_INT;
					      theInfo.theVar.iValue = -1 * a.theVar.iValue;
					   }
					}
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return theInfo;
	}
	// $ANTLR end "signExpr"



	// $ANTLR start "primaryExpr"
	// ./myCompiler.g:668:1: primaryExpr returns [Info theInfo] : ( Integer_constant | Floating_point_constant | Identifier | '&' Identifier | '(' a= arith_expression ')' );
	public final Info primaryExpr() throws RecognitionException {
		Info theInfo = null;


		Token Integer_constant10=null;
		Token Floating_point_constant11=null;
		Token Identifier12=null;
		Info a =null;

		theInfo = new Info();
		try {
			// ./myCompiler.g:671:3: ( Integer_constant | Floating_point_constant | Identifier | '&' Identifier | '(' a= arith_expression ')' )
			int alt15=5;
			switch ( input.LA(1) ) {
			case Integer_constant:
				{
				alt15=1;
				}
				break;
			case Floating_point_constant:
				{
				alt15=2;
				}
				break;
			case Identifier:
				{
				alt15=3;
				}
				break;
			case 27:
				{
				alt15=4;
				}
				break;
			case 28:
				{
				alt15=5;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return theInfo;}
				NoViableAltException nvae =
					new NoViableAltException("", 15, 0, input);
				throw nvae;
			}
			switch (alt15) {
				case 1 :
					// ./myCompiler.g:671:3: Integer_constant
					{
					Integer_constant10=(Token)match(input,Integer_constant,FOLLOW_Integer_constant_in_primaryExpr902); if (state.failed) return theInfo;
					if ( state.backtracking==0 ) {
					   theInfo.theType = Type.CONST_INT;
					   theInfo.theVar.iValue = Integer.parseInt((Integer_constant10!=null?Integer_constant10.getText():null));
					}
					}
					break;
				case 2 :
					// ./myCompiler.g:676:3: Floating_point_constant
					{
					Floating_point_constant11=(Token)match(input,Floating_point_constant,FOLLOW_Floating_point_constant_in_primaryExpr908); if (state.failed) return theInfo;
					if ( state.backtracking==0 ) {
					   theInfo.theType = Type.CONST_FLOAT;
					   theInfo.theVar.fValue = Float.parseFloat((Floating_point_constant11!=null?Floating_point_constant11.getText():null));
					   theInfo.theVar.iValue = Math.round(theInfo.theVar.fValue);
					}
					}
					break;
				case 3 :
					// ./myCompiler.g:682:3: Identifier
					{
					Identifier12=(Token)match(input,Identifier,FOLLOW_Identifier_in_primaryExpr914); if (state.failed) return theInfo;
					if ( state.backtracking==0 ) {
					   // get type information from symtab.
					   Type the_type = symtab.get((Identifier12!=null?Identifier12.getText():null)).theType;
					   theInfo.theType = the_type;

					   // get variable index from symtab.
					   int vIndex = symtab.get((Identifier12!=null?Identifier12.getText():null)).theVar.varIndex;

					   switch (the_type) {
					      case INT: 
					         // get a new temporary variable and
					         // load the variable into the temporary variable.
					            
					         // Ex: %tx = load i32, i32* %ty.
					         TextCode.add("%t" + varCount + " = load i32, i32* %t" + vIndex);
					      
					         // Now, Identifier's value is at the temporary variable %t[varCount].
					         // Therefore, update it.
					         theInfo.theVar.varIndex = varCount;
					         varCount++;
					         break;
					      case FLOAT:
					         break;
					      case CHAR:
					         break;
					   }
					}
					}
					break;
				case 4 :
					// ./myCompiler.g:710:3: '&' Identifier
					{
					match(input,27,FOLLOW_27_in_primaryExpr920); if (state.failed) return theInfo;
					match(input,Identifier,FOLLOW_Identifier_in_primaryExpr922); if (state.failed) return theInfo;
					}
					break;
				case 5 :
					// ./myCompiler.g:711:3: '(' a= arith_expression ')'
					{
					match(input,28,FOLLOW_28_in_primaryExpr926); if (state.failed) return theInfo;
					pushFollow(FOLLOW_arith_expression_in_primaryExpr930);
					a=arith_expression();
					state._fsp--;
					if (state.failed) return theInfo;
					match(input,29,FOLLOW_29_in_primaryExpr932); if (state.failed) return theInfo;
					if ( state.backtracking==0 ) { theInfo =a;}
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return theInfo;
	}
	// $ANTLR end "primaryExpr"

	// $ANTLR start synpred1_myCompiler
	public final void synpred1_myCompiler_fragment() throws RecognitionException {
		// ./myCompiler.g:348:2: ( ELSE IF )
		// ./myCompiler.g:348:2: ELSE IF
		{
		match(input,ELSE,FOLLOW_ELSE_in_synpred1_myCompiler399); if (state.failed) return;
		match(input,IF,FOLLOW_IF_in_synpred1_myCompiler401); if (state.failed) return;
		}

	}
	// $ANTLR end synpred1_myCompiler

	// $ANTLR start synpred2_myCompiler
	public final void synpred2_myCompiler_fragment() throws RecognitionException {
		// ./myCompiler.g:361:3: ( ELSE )
		// ./myCompiler.g:361:4: ELSE
		{
		match(input,ELSE,FOLLOW_ELSE_in_synpred2_myCompiler429); if (state.failed) return;
		}

	}
	// $ANTLR end synpred2_myCompiler

	// Delegated rules

	public final boolean synpred1_myCompiler() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred1_myCompiler_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred2_myCompiler() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred2_myCompiler_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}



	public static final BitSet FOLLOW_VOID_in_program37 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_MAIN_in_program39 = new BitSet(new long[]{0x0000000010000000L});
	public static final BitSet FOLLOW_28_in_program41 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_29_in_program43 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_50_in_program48 = new BitSet(new long[]{0x0013FC000062ECD0L});
	public static final BitSet FOLLOW_declarations_in_program54 = new BitSet(new long[]{0x001000000040A840L});
	public static final BitSet FOLLOW_statements_in_program59 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_52_in_program61 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_type_in_declarations70 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_Identifier_in_declarations76 = new BitSet(new long[]{0x0000000900000000L});
	public static final BitSet FOLLOW_32_in_declarations79 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_Identifier_in_declarations85 = new BitSet(new long[]{0x0000000900000000L});
	public static final BitSet FOLLOW_35_in_declarations92 = new BitSet(new long[]{0x0003FC0000224490L});
	public static final BitSet FOLLOW_declarations_in_declarations94 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INT_in_type114 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CHAR_in_type120 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FLOAT_in_type126 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOUBLE_in_type132 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LONG_in_type138 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VOID_in_type144 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_type_prefix_in_type150 = new BitSet(new long[]{0x0003FC0000224490L});
	public static final BitSet FOLLOW_type_in_type152 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_statement_in_statements193 = new BitSet(new long[]{0x000000000040A840L});
	public static final BitSet FOLLOW_statements_in_statements195 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_assign_stmt_in_statement206 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_35_in_statement208 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_if_stmt_in_statement212 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_func_no_return_stmt_in_statement216 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_35_in_statement218 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_for_stmt_in_statement222 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_while_stmt_in_statement226 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_do_while_stmt_in_statement230 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WHILE_in_while_stmt245 = new BitSet(new long[]{0x0000000010000000L});
	public static final BitSet FOLLOW_28_in_while_stmt247 = new BitSet(new long[]{0x0000000218019000L});
	public static final BitSet FOLLOW_cond_expression_in_while_stmt249 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_29_in_while_stmt251 = new BitSet(new long[]{0x000400000040A840L});
	public static final BitSet FOLLOW_block_stmt_in_while_stmt256 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DO_in_do_while_stmt272 = new BitSet(new long[]{0x000400000040A840L});
	public static final BitSet FOLLOW_block_stmt_in_do_while_stmt274 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_WHILE_in_do_while_stmt276 = new BitSet(new long[]{0x0000000010000000L});
	public static final BitSet FOLLOW_28_in_do_while_stmt278 = new BitSet(new long[]{0x0000000218019000L});
	public static final BitSet FOLLOW_cond_expression_in_do_while_stmt280 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_29_in_do_while_stmt282 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_35_in_do_while_stmt284 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FOR_in_for_stmt302 = new BitSet(new long[]{0x0000000010000000L});
	public static final BitSet FOLLOW_28_in_for_stmt304 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_assign_stmt_in_for_stmt306 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_35_in_for_stmt308 = new BitSet(new long[]{0x0000000218019000L});
	public static final BitSet FOLLOW_cond_expression_in_for_stmt312 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_35_in_for_stmt314 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_assign_stmt_in_for_stmt318 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_29_in_for_stmt320 = new BitSet(new long[]{0x000400000040A840L});
	public static final BitSet FOLLOW_block_stmt_in_for_stmt324 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IF_in_if_stmt355 = new BitSet(new long[]{0x0000000010000000L});
	public static final BitSet FOLLOW_28_in_if_stmt357 = new BitSet(new long[]{0x0000000218019000L});
	public static final BitSet FOLLOW_cond_expression_in_if_stmt359 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_29_in_if_stmt361 = new BitSet(new long[]{0x000400000040A840L});
	public static final BitSet FOLLOW_block_stmt_in_if_stmt367 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_if_then_stmt_in_if_stmt372 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ELSE_in_if_then_stmt406 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_IF_in_if_then_stmt408 = new BitSet(new long[]{0x0000000010000000L});
	public static final BitSet FOLLOW_28_in_if_then_stmt410 = new BitSet(new long[]{0x0000000218019000L});
	public static final BitSet FOLLOW_cond_expression_in_if_then_stmt412 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_29_in_if_then_stmt414 = new BitSet(new long[]{0x000400000040A840L});
	public static final BitSet FOLLOW_block_stmt_in_if_then_stmt418 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_if_then_stmt_in_if_then_stmt423 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ELSE_in_if_then_stmt434 = new BitSet(new long[]{0x000400000040A840L});
	public static final BitSet FOLLOW_block_stmt_in_if_then_stmt436 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_statement_in_block_stmt470 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_50_in_block_stmt481 = new BitSet(new long[]{0x001000000040A840L});
	public static final BitSet FOLLOW_statements_in_block_stmt483 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_52_in_block_stmt485 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Identifier_in_assign_stmt494 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_38_in_assign_stmt496 = new BitSet(new long[]{0x0000000218019000L});
	public static final BitSet FOLLOW_arith_expression_in_assign_stmt498 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Identifier_in_func_no_return_stmt530 = new BitSet(new long[]{0x0000000010000000L});
	public static final BitSet FOLLOW_28_in_func_no_return_stmt532 = new BitSet(new long[]{0x0000000238119000L});
	public static final BitSet FOLLOW_argument_in_func_no_return_stmt539 = new BitSet(new long[]{0x0000000120000000L});
	public static final BitSet FOLLOW_32_in_func_no_return_stmt542 = new BitSet(new long[]{0x0000000218119000L});
	public static final BitSet FOLLOW_argument_in_func_no_return_stmt548 = new BitSet(new long[]{0x0000000120000000L});
	public static final BitSet FOLLOW_29_in_func_no_return_stmt554 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_arith_expression_in_argument572 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_argument582 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_arith_expression_in_cond_expression614 = new BitSet(new long[]{0x000803B005000000L});
	public static final BitSet FOLLOW_compare_expression_back_in_cond_expression618 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_39_in_compare_expression_back635 = new BitSet(new long[]{0x0000000218019000L});
	public static final BitSet FOLLOW_arith_expression_in_compare_expression_back641 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_41_in_compare_expression_back647 = new BitSet(new long[]{0x0000000218019000L});
	public static final BitSet FOLLOW_arith_expression_in_compare_expression_back653 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_37_in_compare_expression_back660 = new BitSet(new long[]{0x0000000218019000L});
	public static final BitSet FOLLOW_arith_expression_in_compare_expression_back666 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_24_in_compare_expression_back673 = new BitSet(new long[]{0x0000000218019000L});
	public static final BitSet FOLLOW_arith_expression_in_compare_expression_back679 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_26_in_compare_expression_back685 = new BitSet(new long[]{0x0000000218019000L});
	public static final BitSet FOLLOW_arith_expression_in_compare_expression_back691 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_51_in_compare_expression_back697 = new BitSet(new long[]{0x0000000218019000L});
	public static final BitSet FOLLOW_arith_expression_in_compare_expression_back703 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_40_in_compare_expression_back709 = new BitSet(new long[]{0x0000000218019000L});
	public static final BitSet FOLLOW_arith_expression_in_compare_expression_back715 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_36_in_compare_expression_back721 = new BitSet(new long[]{0x0000000218019000L});
	public static final BitSet FOLLOW_arith_expression_in_compare_expression_back727 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_multExpr_in_arith_expression761 = new BitSet(new long[]{0x0000000280000002L});
	public static final BitSet FOLLOW_31_in_arith_expression767 = new BitSet(new long[]{0x0000000218019000L});
	public static final BitSet FOLLOW_multExpr_in_arith_expression771 = new BitSet(new long[]{0x0000000280000002L});
	public static final BitSet FOLLOW_33_in_arith_expression777 = new BitSet(new long[]{0x0000000218019000L});
	public static final BitSet FOLLOW_multExpr_in_arith_expression781 = new BitSet(new long[]{0x0000000280000002L});
	public static final BitSet FOLLOW_signExpr_in_multExpr806 = new BitSet(new long[]{0x0000000442000002L});
	public static final BitSet FOLLOW_30_in_multExpr812 = new BitSet(new long[]{0x0000000218019000L});
	public static final BitSet FOLLOW_signExpr_in_multExpr816 = new BitSet(new long[]{0x0000000442000002L});
	public static final BitSet FOLLOW_34_in_multExpr822 = new BitSet(new long[]{0x0000000218019000L});
	public static final BitSet FOLLOW_signExpr_in_multExpr826 = new BitSet(new long[]{0x0000000442000002L});
	public static final BitSet FOLLOW_25_in_multExpr832 = new BitSet(new long[]{0x0000000218019000L});
	public static final BitSet FOLLOW_signExpr_in_multExpr836 = new BitSet(new long[]{0x0000000442000002L});
	public static final BitSet FOLLOW_primaryExpr_in_signExpr862 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_33_in_signExpr870 = new BitSet(new long[]{0x0000000018019000L});
	public static final BitSet FOLLOW_primaryExpr_in_signExpr874 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Integer_constant_in_primaryExpr902 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Floating_point_constant_in_primaryExpr908 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Identifier_in_primaryExpr914 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_27_in_primaryExpr920 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_Identifier_in_primaryExpr922 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_28_in_primaryExpr926 = new BitSet(new long[]{0x0000000218019000L});
	public static final BitSet FOLLOW_arith_expression_in_primaryExpr930 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_29_in_primaryExpr932 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ELSE_in_synpred1_myCompiler399 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_IF_in_synpred1_myCompiler401 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ELSE_in_synpred2_myCompiler429 = new BitSet(new long[]{0x0000000000000002L});
}
