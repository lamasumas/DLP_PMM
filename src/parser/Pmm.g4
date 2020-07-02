grammar Pmm;	

@header{
import ast.*;
import ast.definitions.*;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;
import java.util.*;
}

program returns [Program ast]: {List<Definition> definitionList = new ArrayList<Definition>();}
         (definition{definitionList.addAll($definition.ast);})* main {definitionList.add($main.ast);} EOF {$ast = new Program(definitionList, $EOF.getLine(), $EOF.getCharPositionInLine());}
       ;


expression returns [Expression ast]:
            '(' expression ')' { $ast = $expression.ast;}
           | ex=expression '.' ID { $ast = new FieldAccess( $ex.start.getCharPositionInLine(), $ex.start.getLine(), $ex.ast, $ID.text);}
           | '('type')' expression {$ast = new Cast($type.start.getCharPositionInLine(), $type.start.getLine(), $expression.ast, $type.ast);}
           | ex1=expression '[' ex2=expression ']' { $ast = new ArrayAccess($ex1.start.getCharPositionInLine(), $ex1.start.getLine(), $ex1.ast, $ex2.ast );}
           | '!' expression {  $ast = new Not($expression.start.getCharPositionInLine(), $expression.start.getLine(), $expression.ast );}
           | function_invocation_as_expression { $ast = $function_invocation_as_expression.ast;}
           | ex1=expression op=('*'|'/'|'%') ex2=expression {$ast = new Arithmetic( $ex1.start.getCharPositionInLine(), $ex1.start.getLine(),$op.text, $ex1.ast, $ex2.ast) ;}
           | ex1=expression op=('+'|'-') ex2=expression {$ast = new Arithmetic($ex1.start.getCharPositionInLine(), $ex1.start.getLine(), $op.text, $ex1.ast, $ex2.ast);}
           | ex1=expression op=('>'|'>='|'<'|'<='|'!='|'==') ex2=expression {$ast = new Comparison($ex1.start.getCharPositionInLine(), $ex1.start.getLine(),$op.text, $ex1.ast, $ex2.ast);}
           | ex1=expression op=('&&' | '||') ex2=expression {$ast = new AndOr($ex1.start.getCharPositionInLine(), $ex1.start.getLine(), $op.text, $ex1.ast, $ex2.ast);}
           | '-'expression { $ast = new UnaryMinus($expression.start.getCharPositionInLine(), $expression.start.getLine(), $expression.ast );}
           | ID {$ast = new Variable($ID.text, $ID.getLine(), $ID.getCharPositionInLine() + 1);}
           | INT_CONSTANT {$ast = new IntLiteral( $INT_CONSTANT.getLine(), $INT_CONSTANT.getCharPositionInLine() +1, LexerHelper.lexemeToInt($INT_CONSTANT.text));}
           | REAL_CONSTANT {$ast = new RealLiteral( $REAL_CONSTANT.getLine(), $REAL_CONSTANT.getCharPositionInLine() +1,  LexerHelper.lexemeToReal($REAL_CONSTANT.text));}
           | CHAR_CONSTANT {$ast = new CharLiteral( $CHAR_CONSTANT.getLine(), $CHAR_CONSTANT.getCharPositionInLine() +1, LexerHelper.lexemeToChar($CHAR_CONSTANT.text));}
           ;


main returns [Definition ast]:
         'def' fname='main' {List<Statement> statementList = new ArrayList<Statement>(); List<VarDefinition> definitionList = new ArrayList<VarDefinition>();}
                       '(' ')' ':' '{' (var_definitions{definitionList.addAll($var_definitions.ast);})* (statement{statementList.add($statement.ast);})* '}'
                        {$ast = (new FunctionDefinition($fname.getLine(), $fname.getCharPositionInLine(),$fname.text, new FuncType($fname.getLine(), $fname.getCharPositionInLine(),
                        new VoidType($fname.getLine(), $fname.getCharPositionInLine()), new ArrayList() )
                        , definitionList, statementList));}
                ;

statement returns [Statement ast]:
            t='print' {List<Expression> exp = new ArrayList<Expression>();} expression { exp.add( $expression.ast);} (',' expression{ exp.add( $expression.ast);})* ';'
                { $ast = new Print($t.getLine(), $t.getCharPositionInLine(), exp); }
        |   'while' expression ':' block { $ast = new While( $expression.start.getLine(), $expression.start.getCharPositionInLine(), $expression.ast, $block.ast); }
        |   'if' {List<Statement> elseList = new ArrayList<Statement>(); }
                expression ':' block { List<Statement> ifList = $block.ast; }('else' block{elseList = $block.ast;})?
                { $ast = new IfElse( $expression.start.getLine(), $expression.start.getCharPositionInLine(), ifList, elseList, $expression.ast);}
        |   'input' {List<Expression> aux = new ArrayList<Expression>();} expression { aux.add($expression.ast);}  (',' expression{ aux.add($expression.ast);})* ';'
                {$ast = new Input($expression.start.getLine(), $expression.start.getCharPositionInLine(), aux);}
        |   function_invocation_as_statement { $ast = $function_invocation_as_statement.ast;} ';'
        |   ex1=expression '=' ex2=expression';' { $ast = new Assignment($ex1.start.getLine(), $ex1.start.getCharPositionInLine(), $ex1.ast, $ex2.ast);}
        |   'return' expression ';' {$ast = new Return($expression.start.getLine(), $expression.start.getCharPositionInLine(), $expression.ast);}
        ;

function_invocation_as_expression returns [Expression ast]:
                        ID '(' ')' { $ast = new FunctionInvocation($ID.getLine(), $ID.getCharPositionInLine(), $ID.text, new ArrayList<Expression>() );}
                   | ID{List<Expression> params = new ArrayList<Expression>();} '(' expression { params.add($expression.ast);}(',' expression { params.add($expression.ast);})*')'
                   {$ast = new FunctionInvocation($ID.getLine(), $ID.getCharPositionInLine(),$ID.text, params);}
                   ;


function_invocation_as_statement returns [Statement ast]:
                                       ID '(' ')' { $ast = new FunctionInvocation($ID.getLine(), $ID.getCharPositionInLine(), $ID.text, new ArrayList<Expression>() );}
                                      | ID{List<Expression> params = new ArrayList<Expression>();} '(' expression { params.add($expression.ast);}(',' expression { params.add($expression.ast);})*')'
                                      {$ast = new FunctionInvocation($ID.getLine(), $ID.getCharPositionInLine(),$ID.text, params);}
                   ;
block returns [List<Statement> ast]:
        statement { $ast = new ArrayList<Statement>(); $ast.add($statement.ast);}
    |  {$ast = new ArrayList<Statement>();}'{'(statement{$ast.add($statement.ast);})*'}'
    ;

type returns [Type ast]:
        t='int' {$ast = new IntType($t.getLine(), $t.getCharPositionInLine());}
    |   t='double' {$ast = new DoubleType($t.getLine(), $t.getCharPositionInLine());}
    |   t='char' {$ast = new CharType($t.getLine(), $t.getCharPositionInLine());}
    |   t='struct'  '{'{List<FieldDefinition> fields = new ArrayList<FieldDefinition>(); List<String> ids = new ArrayList<String>();}
    (ID {ids.add($ID.text);} (',' ID {ids.add($ID.text);})*':'type ';'
        { ids.forEach( theID -> fields.add(new FieldDefinition($t.getLine(), $t.getCharPositionInLine(), theID, $type.ast)));}
        { ids.clear();})*'}'
        {$ast = new StrucType($t.getLine(),$t.getCharPositionInLine(), fields );}
    | '['INT_CONSTANT']'type { $ast = new ArrayType($INT_CONSTANT.getLine(),$INT_CONSTANT.getCharPositionInLine(), LexerHelper.lexemeToInt($INT_CONSTANT.text), $type.ast);}
    ;

definition  returns [ List<Definition> ast = new ArrayList<Definition>()]:
        var_definitions {$ast.addAll($var_definitions.ast);}
        |  def='def' fname=ID {List<Statement> statementList = new ArrayList<Statement>(); List<VarDefinition> definitionList = new ArrayList<VarDefinition>();}

                  {List<VarDefinition> params = new ArrayList<VarDefinition>();} aux='(' (ID ':' type
                                    { params.add( new VarDefinition($ID.getLine(), $ID.getCharPositionInLine(), $type.ast, $ID.text));}
                                    (','ID ':' type { params.add( new VarDefinition($ID.getLine(), $ID.getCharPositionInLine(), $type.ast, $ID.text));})*)?
                                     t=')'  { Type returnType = new VoidType($t.getLine(), $t.getCharPositionInLine()); }
                                     ':' ( type2=type{returnType = $type2.ast;})?
                                     {FuncType funtionType = new FuncType($aux.getLine(), $aux.getCharPositionInLine(), returnType, params );}

                '{' (var_definitions{definitionList.addAll($var_definitions.ast);})* (statement{statementList.add($statement.ast);})* '}'
                {$ast.add(new FunctionDefinition($def.getLine(), $def.getCharPositionInLine(),$fname.text, funtionType, definitionList, statementList));}
        ;

var_definitions returns[ List<VarDefinition> ast = new ArrayList<VarDefinition>()]:
           {List<String> varNames = new ArrayList<String>();} ID {varNames.add($ID.text);}(',' ID{varNames.add($ID.text);})* ':'  type ';'
           {VarDefinition.checkDuplicateNames(varNames,$ID.getLine(), $ID.getCharPositionInLine() );}
           {varNames.forEach( theName -> $ast.add(new VarDefinition( $ID.getLine(), $ID.getCharPositionInLine(),$type.ast,theName))); }
                   ;

fragment DIGIT: [0-9]
            ;

fragment ASCII: DIGIT
        | [1-9]DIGIT
        | '1'[0-1]DIGIT
        | '1''2'[0-7]
        ;

fragment REAL: INT_CONSTANT+'.'DIGIT*
            | INT_CONSTANT*'.'DIGIT+
            ;


USELESS_SYMBOLS: ('\n'|'\t'|'\r'|' ') -> skip
                ;


ID: ([a-zA-Z]+|'_') ([a-zA-Z]+|'_'| [0-9])*
    ;

CHAR_CONSTANT: '\''  '\\''n'  '\''
            | '\''  '\\''t'  '\''
            | '\''  '\\' ASCII '\''
            | '\''.'\''
            ;

REAL_CONSTANT: REAL
            |(REAL|INT_CONSTANT) ('e'|'E') (('-'|'+')?[1-9]DIGIT* | [1-9]DIGIT*)
            ;
INT_CONSTANT: '0'|[1-9]DIGIT*
            ;

MULTI_LINE_COMMENT:'"''"''"' .*?('"''"''"') -> skip
                ;

ONE_LINE_COMMENT: '#'.*?'\r'?('\n'|EOF)-> skip
            ;
