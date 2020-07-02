package visitor;

import ast.Program;
import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;

public interface Visitor<R,T> {
    R visit(FunctionDefinition a, T param);
    R visit(VarDefinition a, T param);
    R visit(AndOr a, T param);
    R visit(Arithmetic a, T param);
    R visit(ArrayAccess a, T param);
    R visit(Cast a, T param);
    R visit(CharLiteral a, T param);
    R visit(Comparison a, T param);
    R visit(FieldAccess a, T param);
    R visit(FunctionInvocation a, T param);
    R visit(IntLiteral a, T param);
    R visit(Not a, T param);
    R visit(RealLiteral a, T param);
    R visit(UnaryMinus a, T param);
    R visit(Variable a, T param);
    R visit(Assignment a, T param);
    R visit(IfElse a, T param);
    R visit(Input a, T param);
    R visit(Print a, T param);
    R visit(Return a, T param);
    R visit(While a, T param);
    R visit(ArrayType a, T param);
    R visit(CharType a, T param);
    R visit(DoubleType a, T param);
    R visit(ErrorType a, T param);
    R visit(VoidType a, T param);
    R visit(FieldDefinition a, T param);
    R visit(FuncType a, T param);
    R visit(IntType a, T param);
    R visit(StrucType a, T param);
    R visit(Program a, T param);






}
