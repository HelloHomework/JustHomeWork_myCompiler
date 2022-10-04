grammar myCompiler;

options {
   language = Java;
}

@header {
    // import packages here.
    import java.util.HashMap;
    import java.util.ArrayList;
    import static java.lang.Math.round;
}

@members {
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
}

program:
 VOID MAIN '(' ')'
{
   /* Output function prologue */
   prologue();
}

'{' 
   declarations
   statements
'}'
{
if (TRACEON)
System.out.println("VOID MAIN () {declarations statements}");

   /* output function epilogue */   
   epilogue();
}
;

declarations:type t1 = Identifier(
',' t2 = Identifier
{
   if (TRACEON)
      System.out.println("declarations: type Identifier : declarations");

   if (symtab.containsKey($t2.text)) {
      // variable re-declared.
      System.out.println("Type Error: " + 
                           $t2.getLine() + 
                        ": Redeclared identifier.");
      System.exit(0);
   }
         
   /* Add ID and its info into the symbol table. */
   Info the_entry = new Info();
   the_entry.theType = $type.attr_type;
   the_entry.theVar.varIndex = varCount;
   varCount ++;
   symtab.put($t2.text, the_entry);

   // issue the instruction.
   // Ex: \%a = alloca i32, align 4
   if ($type.attr_type == Type.INT) { 
      TextCode.add("\%t" + the_entry.theVar.varIndex + " = alloca i32, align 4");
   }
}
)* ';' declarations
{
   if (TRACEON)
      System.out.println("declarations: type Identifier : declarations");

   if (symtab.containsKey($t1.text)) {
      // variable re-declared.
      System.out.println("Type Error: " + 
                           $t1.getLine() + 
                        ": Redeclared identifier.");
      System.exit(0);
   }
         
   /* Add ID and its info into the symbol table. */
   Info the_entry = new Info();
   the_entry.theType = $type.attr_type;
   the_entry.theVar.varIndex = varCount;
   varCount ++;
   symtab.put($t1.text, the_entry);

   // issue the instruction.
   // Ex: \%a = alloca i32, align 4
   if ($type.attr_type == Type.INT) { 
      TextCode.add("\%t" + the_entry.theVar.varIndex + " = alloca i32, align 4");
   }
}
| 
{
   if (TRACEON)
      System.out.println("declarations: ");
}
;

type
returns [Type attr_type]
: INT { if (TRACEON) System.out.println("type: INT"); $attr_type=Type.INT; }
| CHAR { if (TRACEON) System.out.println("type: CHAR"); $attr_type=Type.CHAR; }
| FLOAT {if (TRACEON) System.out.println("type: FLOAT"); $attr_type=Type.FLOAT; }
| DOUBLE {if (TRACEON) System.out.println("type: DOUBLE"); $attr_type=Type.FLOAT; }
| LONG {if (TRACEON) System.out.println("type: LONG"); $attr_type=Type.INT; }
| VOID { if (TRACEON) System.out.println("type: VOID"); $attr_type=Type.VOID; }
| type_prefix type
   ;

type_prefix:
'register' 
|'const' 
|'signed'
|'unsigned'
|'static'
|'extrn'
|'volatile'
|'extern'
;

statements:statement statements
|
;


statement: assign_stmt ';'
| if_stmt
| func_no_return_stmt ';'
| for_stmt
| while_stmt
| do_while_stmt
;


while_stmt
@init {
   String condStartLabel = newLabel();
   String startLabel = newLabel();
   String endLabel = newLabel();

   TextCode.add("br label \%" + condStartLabel);
   TextCode.add("");
   
   TextCode.add(condStartLabel + ":");
}
: WHILE '(' cond_expression ')' 
{
   TextCode.add("br i1 \%t" + $cond_expression.theInfo.theVar.varIndex + ", label \%" + startLabel + ", label \%" + endLabel);
   TextCode.add("");
   TextCode.add(startLabel + ":");
}
block_stmt
{
   TextCode.add("");
   TextCode.add("br label \%" + condStartLabel);
   TextCode.add(endLabel + ":");
}
;

do_while_stmt
@init {
   String startLabel = newLabel();
   String endLabel = newLabel();

   TextCode.add("br label \%" + startLabel);
   TextCode.add("");
   TextCode.add(startLabel + ":");
}
: DO block_stmt WHILE '(' cond_expression ')' ';'
{
   TextCode.add("br i1 \%t" + $cond_expression.theInfo.theVar.varIndex + ", label \%" + startLabel + ", label \%" + endLabel);
   TextCode.add("");
   TextCode.add(endLabel + ":");
}
;


for_stmt
@init {
   String condStartLabel = newLabel();
   String CounterStartLabel = newLabel();
   String trueLabel = newLabel();
   String endLabel = newLabel();

   TextCode.add("");
}
: 
FOR '(' assign_stmt ';'
{
   TextCode.add("br label \%" + condStartLabel);
   TextCode.add("");
   TextCode.add(condStartLabel + ":");
}
cond_expression ';'
{
   TextCode.add("br i1 \%t" + $cond_expression.theInfo.theVar.varIndex + ", label \%" + trueLabel + ", label \%" + endLabel);

   TextCode.add("");
   TextCode.add(CounterStartLabel + ":" + "    ;counter");
}
assign_stmt ')'
{
   TextCode.add("br label \%" + condStartLabel);
   TextCode.add(trueLabel + ":" + "    ;trueLabel");
}
block_stmt
{
   TextCode.add("br label \%" + CounterStartLabel);
   TextCode.add("");
   TextCode.add(endLabel + ":" + "    ;endLabel");
}
;
       
       
if_stmt
@init {
   String trueLabel = newLabel();
   String falseLabel = newLabel();
   String endLabel = newLabel();

   TextCode.add("");
}
:
IF '(' cond_expression ')'  
{
   TextCode.add("br i1 \%t" + $cond_expression.theInfo.theVar.varIndex + ", label \%" + trueLabel + ", label \%" + falseLabel);
   TextCode.add("");
   TextCode.add(trueLabel + ":");
}
block_stmt 
{
   TextCode.add("br label \%" + endLabel);
   TextCode.add("");
   TextCode.add(falseLabel + ":");
}
if_then_stmt[endLabel]
{
   TextCode.add("");
   TextCode.add("br label \%" + endLabel);
   TextCode.add(endLabel + ":");
}
;

      
if_then_stmt [String endLabel]
@init {
   String trueLabel = newLabel();
   String falseLabel = newLabel();

}
:
(ELSE IF) => ELSE IF '(' cond_expression ')'
{
   TextCode.add("br i1 \%t" + $cond_expression.theInfo.theVar.varIndex + ", label \%" + trueLabel + ", label \%" + falseLabel);
   TextCode.add("");
   TextCode.add(trueLabel + ":");
}
block_stmt 
{
   TextCode.add("br label \%" + endLabel);
   TextCode.add("");
   TextCode.add(falseLabel + ":");
}
if_then_stmt[endLabel]
| (ELSE) => ELSE block_stmt
{
   TextCode.add("br label \%" + endLabel);
}
|
;
              
block_stmt
   : 
   statement
   | 
   '{' statements '}'
;


assign_stmt: Identifier '=' arith_expression
{
   Info theRHS = $arith_expression.theInfo;
   Info theLHS = symtab.get($Identifier.text); 

   if ((theLHS.theType == Type.INT) &&
      (theRHS.theType == Type.INT)) {         
      // issue store insruction.
      // Ex: store i32 \%tx, i32* \%ty
      TextCode.add("store i32 \%t" + theRHS.theVar.varIndex + ", i32* \%t" + theLHS.theVar.varIndex);
   } else if ((theLHS.theType == Type.INT) &&
   (theRHS.theType == Type.CONST_INT)) {
      // issue store insruction.
      // Ex: store i32 value, i32* \%ty
      TextCode.add("store i32 " + theRHS.theVar.iValue + ", i32* \%t" + theLHS.theVar.varIndex);           
   }
}
;

         
func_no_return_stmt: (Identifier '(') => funcName = Identifier '(' (arg1 = argument (',' arg2 = argument)*)? ')'
{
   if(funcName.getText().contains("printf")){
      int arg2VarCount = -1;
      String arg1String,arg2String;
      int arg1Len = -1;
      int StrId = constantCount;
      if(arg1 != null){

         //sub \n to \0A
         arg1Len = $arg1.text.length();
         arg1String = $arg1.text;
         arg1String = arg1String.replace("\\n","\\0A");
         arg1Len -= (arg1String.length() - $arg1.text.length());

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
            TextCode.add("\%t" + varCount + "=call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([" + arg1Len + "x i8], [" + arg1Len + " x i8]* @.str." + StrId + ", i64 0, i64 0))");
      } else {
         if($arg2.theInfo.theType == Type.INT){
            TextCode.add("\%t" + varCount + "=call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([" + arg1Len + "x i8], [" + arg1Len + " x i8]* @.str." + StrId + ", i64 0, i64 0), i32 \%t" + $arg2.theInfo.theVar.varIndex + ")");
         }else if($arg2.theInfo.theType == Type.CONST_INT){
            TextCode.add("\%t" + varCount + "=call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([" + arg1Len + "x i8], [" + arg1Len + " x i8]* @.str." + StrId + ", i64 0, i64 0), i32 " + $arg2.theInfo.theVar.iValue + ")");
         }
      }
      varCount++;
      constantCount++;
   }
}
;


argument 
returns [Info theInfo]
: 
arith_expression
{$theInfo = $arith_expression.theInfo;}
| s = STRING_LITERAL   
;
         
cond_expression
returns [Info theInfo]
@init {theInfo = new Info();}
:
a=arith_expression b=compare_expression_back
{
   String opString = "eq";

   if($b.op == 1) opString = "eq";
   else if($b.op == 2) opString = "sge";
   else if($b.op == 3) opString = "sle";
   else if($b.op == 4) opString = "ne";
   else if($b.op == 5) opString = "eq";
   else if($b.op == 6) opString = "eq";
   else if($b.op == 7) opString = "sgt";
   else if($b.op == 8) opString = "slt";
   else System.err.println("Unrecognized op");

   TextCode.add(";[DEBUG] A type: " + $a.theInfo.theType + "; B type: " + $b.theInfo.theType);

   // code generation.                 
   if (($a.theInfo.theType == Type.INT) &&
      ($b.theInfo.theType == Type.INT)) {
      TextCode.add("\%t" + varCount + " = icmp " + opString + " i32 \%t" + $a.theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
   } else if (($a.theInfo.theType == Type.INT) &&
   ($b.theInfo.theType == Type.CONST_INT)) {
      TextCode.add("\%t" + varCount + " = icmp " + opString + " i32 \%t" + $a.theInfo.theVar.varIndex + ", " + $b.theInfo.theVar.iValue);
   }else if (($a.theInfo.theType == Type.CONST_INT) &&
   ($b.theInfo.theType == Type.INT)) {
      TextCode.add("\%t" + varCount + " = icmp " + opString + " i32 " + $a.theInfo.theVar.iValue + ", \%t" + $b.theInfo.theVar.varIndex);
   } else if (($a.theInfo.theType == Type.CONST_INT) &&
   ($b.theInfo.theType == Type.CONST_INT)) {
      TextCode.add("\%t" + varCount + " = icmp " + opString + " i32 " + $a.theInfo.theVar.iValue + ", " + $b.theInfo.theVar.iValue);
   }

    // Update arith_expression's theInfo.
   theInfo.theType = Type.BOOL;
   theInfo.theVar.varIndex = varCount;
   varCount++;
}
;

compare_expression_back returns [int op, Info theInfo]
:  
'==' e = arith_expression {$theInfo = $e.theInfo; $op = 1;}
| '>=' e = arith_expression  {$theInfo = $e.theInfo; $op = 2;}
| '<=' e = arith_expression  {$theInfo = $e.theInfo; $op = 3;}
| '!=' e = arith_expression {$theInfo = $e.theInfo; $op = 4;}
| '&&' e = arith_expression {$theInfo = $e.theInfo; $op = 5;}
| '||' e = arith_expression {$theInfo = $e.theInfo; $op = 6;}
| '>' e = arith_expression {$theInfo = $e.theInfo; $op = 7;}
| '<' e = arith_expression {$theInfo = $e.theInfo; $op = 8;}
;
            
arith_expression
returns [Info theInfo]
@init {theInfo = new Info();}
: a=multExpr { $theInfo=$a.theInfo; }
( '+' b=multExpr
{
   // We need to do type checking first.
   // ...

   // code generation.                  
   if (($a.theInfo.theType == Type.INT) &&
      ($b.theInfo.theType == Type.INT)) {
      TextCode.add("\%t" + varCount + " = add nsw i32 \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
   } else if (($a.theInfo.theType == Type.INT) &&
   ($b.theInfo.theType == Type.CONST_INT)) {
      TextCode.add("\%t" + varCount + " = add nsw i32 \%t" + $theInfo.theVar.varIndex + ", " + $b.theInfo.theVar.iValue);
   }else if (($a.theInfo.theType == Type.CONST_INT) &&
   ($b.theInfo.theType == Type.INT)) {
      TextCode.add("\%t" + varCount + " = add nsw i32 " + $theInfo.theVar.iValue + ", \%t"+   $b.theInfo.theVar.varIndex);
   } else if (($a.theInfo.theType == Type.CONST_INT) &&
   ($b.theInfo.theType == Type.CONST_INT)) {
      TextCode.add("\%t" + varCount + " = add nsw i32 " + $theInfo.theVar.iValue + ", " + $b.theInfo.theVar.iValue);
   }

   // Update arith_expression's theInfo.
   $theInfo.theType = Type.INT;
   $theInfo.theVar.varIndex = varCount;
   varCount ++;
}
| '-' b=multExpr
{
   // We need to do type checking first.
   // ...

   // code generation.                  
   if (($a.theInfo.theType == Type.INT) &&
      ($b.theInfo.theType == Type.INT)) {
      TextCode.add("\%t" + varCount + " = sub nsw i32 \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
   } else if (($a.theInfo.theType == Type.INT) &&
   ($b.theInfo.theType == Type.CONST_INT)) {
      TextCode.add("\%t" + varCount + " = sub nsw i32 \%t" + $theInfo.theVar.varIndex + ", " + $b.theInfo.theVar.iValue);
   }else if (($a.theInfo.theType == Type.CONST_INT) &&
   ($b.theInfo.theType == Type.INT)) {
      TextCode.add("\%t" + varCount + " = sub nsw i32 " + $theInfo.theVar.iValue + ", \%t"+   $b.theInfo.theVar.varIndex);
   } else if (($a.theInfo.theType == Type.CONST_INT) &&
   ($b.theInfo.theType == Type.CONST_INT)) {
      TextCode.add("\%t" + varCount + " = sub nsw i32 " + $theInfo.theVar.iValue + ", " + $b.theInfo.theVar.iValue);
   }
   
   // Update arith_expression's theInfo.
   $theInfo.theType = Type.INT;
   $theInfo.theVar.varIndex = varCount;
   varCount ++;
}
)*
;

multExpr
returns [Info theInfo]
@init {theInfo = new Info();}
: a=signExpr { $theInfo=$a.theInfo; }
( '*' b=signExpr
{
   // We need to do type checking first.
   // ...

   // code generation.                 
   if (($a.theInfo.theType == Type.INT) &&
      ($b.theInfo.theType == Type.INT)) {
      TextCode.add("\%t" + varCount + " = mul nsw i32 \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);

   } else if (($a.theInfo.theType == Type.INT) &&
   ($b.theInfo.theType == Type.CONST_INT)) {
      TextCode.add("\%t" + varCount + " = mul nsw i32 \%t" + $theInfo.theVar.varIndex + ", " + $b.theInfo.theVar.iValue);

   }else if (($a.theInfo.theType == Type.CONST_INT) &&
   ($b.theInfo.theType == Type.INT)) {
      TextCode.add("\%t" + varCount + " = mul nsw i32 " + $theInfo.theVar.iValue + ", \%t"+   $b.theInfo.theVar.varIndex);

   } else if (($a.theInfo.theType == Type.CONST_INT) &&
   ($b.theInfo.theType == Type.CONST_INT)) {
      TextCode.add("\%t" + varCount + " = mul nsw i32 " + $theInfo.theVar.iValue + ", " + $b.theInfo.theVar.iValue);
   }
      // Update arith_expression's theInfo.
   $theInfo.theType = Type.INT;
   $theInfo.theVar.varIndex = varCount;
   varCount ++;
}
| '/' b=signExpr
{
   // We need to do type checking first.
   // ...

   // code generation.                 
   if (($a.theInfo.theType == Type.INT) &&
      ($b.theInfo.theType == Type.INT)) {
      TextCode.add("\%t" + varCount + " = div nsw i32 \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
   } else if (($a.theInfo.theType == Type.INT) &&
   ($b.theInfo.theType == Type.CONST_INT)) {
      TextCode.add("\%t" + varCount + " = div nsw i32 \%t" + $theInfo.theVar.varIndex + ", " + $b.theInfo.theVar.iValue);
   }else if (($a.theInfo.theType == Type.CONST_INT) &&
   ($b.theInfo.theType == Type.INT)) {
      TextCode.add("\%t" + varCount + " = div nsw i32 " + $theInfo.theVar.iValue + ", \%t"+   $b.theInfo.theVar.varIndex);
   } else if (($a.theInfo.theType == Type.CONST_INT) &&
   ($b.theInfo.theType == Type.CONST_INT)) {
      TextCode.add("\%t" + varCount + " = div nsw i32 " + $theInfo.theVar.iValue + ", " + $b.theInfo.theVar.iValue);
   }
   
   // Update arith_expression's theInfo.
   $theInfo.theType = Type.INT;
   $theInfo.theVar.varIndex = varCount;
   varCount ++;
}
| '%' b=signExpr 
{
   // We need to do type checking first.
   // ...

   // code generation.                 
   if (($a.theInfo.theType == Type.INT) &&
      ($b.theInfo.theType == Type.INT)) {
      TextCode.add("\%t" + varCount + " = srem i32 \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
   } else if (($a.theInfo.theType == Type.INT) &&
   ($b.theInfo.theType == Type.CONST_INT)) {
      TextCode.add("\%t" + varCount + " = srem i32 \%t" + $theInfo.theVar.varIndex + ", " + $b.theInfo.theVar.iValue);
   }else if (($a.theInfo.theType == Type.CONST_INT) &&
   ($b.theInfo.theType == Type.INT)) {
      TextCode.add("\%t" + varCount + " = srem i32 " + $theInfo.theVar.iValue + ", \%t"+   $b.theInfo.theVar.varIndex);
   } else if (($a.theInfo.theType == Type.CONST_INT) &&
   ($b.theInfo.theType == Type.CONST_INT)) {
      TextCode.add("\%t" + varCount + " = srem 32 " + $theInfo.theVar.iValue + ", " + $b.theInfo.theVar.iValue);
   }

   // Update arith_expression's theInfo.
   $theInfo.theType = Type.INT;
   $theInfo.theVar.varIndex = varCount;
   varCount ++;
}
)*
;

signExpr
returns [Info theInfo]
@init {theInfo = new Info();}
: a=primaryExpr 
{ $theInfo=$a.theInfo; } 
| '-' a=primaryExpr
{
   // We need to do type checking first.
   // ...

   // code generation.                 
   if ($a.theInfo.theType == Type.INT) {
      TextCode.add("\%t" + varCount + " = mul nsw i32 \%t" + $theInfo.theVar.varIndex + ", \%t" + -1);

      // Update arith_expression's theInfo.
      $theInfo.theType = Type.INT;
      $theInfo.theVar.varIndex = varCount;
      varCount ++;
   } else if ($a.theInfo.theType == Type.CONST_INT) {
      // Update arith_expression's theInfo.
      $theInfo.theType = Type.CONST_INT;
      $theInfo.theVar.iValue = -1 * $a.theInfo.theVar.iValue;
   }
}
;
        
primaryExpr
returns [Info theInfo]
@init {theInfo = new Info();}
: Integer_constant
{
   $theInfo.theType = Type.CONST_INT;
   $theInfo.theVar.iValue = Integer.parseInt($Integer_constant.text);
}
| Floating_point_constant
{
   $theInfo.theType = Type.CONST_FLOAT;
   $theInfo.theVar.fValue = Float.parseFloat($Floating_point_constant.text);
   $theInfo.theVar.iValue = Math.round($theInfo.theVar.fValue);
}
| Identifier
{
   // get type information from symtab.
   Type the_type = symtab.get($Identifier.text).theType;
   $theInfo.theType = the_type;

   // get variable index from symtab.
   int vIndex = symtab.get($Identifier.text).theVar.varIndex;

   switch (the_type) {
      case INT: 
         // get a new temporary variable and
         // load the variable into the temporary variable.
            
         // Ex: \%tx = load i32, i32* \%ty.
         TextCode.add("\%t" + varCount + " = load i32, i32* \%t" + vIndex);
      
         // Now, Identifier's value is at the temporary variable \%t[varCount].
         // Therefore, update it.
         $theInfo.theVar.varIndex = varCount;
         varCount++;
         break;
      case FLOAT:
         break;
      case CHAR:
         break;
   }
}
| '&' Identifier
| '(' a=arith_expression ')'
{ $theInfo=$a.theInfo;} 
;

         
/* description of the tokens */
FLOAT:'float';
INT:'int';
CHAR: 'char';
SHORT:'short';
LONG:'long';
DOUBLE:'double';
MAIN: 'main';
VOID: 'void';
IF: 'if';
ELSE: 'else';
WHILE: 'while';
DO: 'do';
FOR: 'for';

Identifier:('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
Integer_constant:'0'..'9'+;
Floating_point_constant:'0'..'9'+ '.' '0'..'9'+;

STRING_LITERAL
:  '"' ( EscapeSequence | ~('\\'|'"') )* '"'
;

WS:( ' ' | '\t' | '\r' | '\n' ) {$channel=HIDDEN;};
COMMENT:'/*' .* '*/' {$channel=HIDDEN;};


fragment
EscapeSequence
:   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
;
