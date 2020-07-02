package visitor.codegenerator;

import ast.Program;
import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;
import visitor.Visitor;

public class AbstractCGVisitor<R, T> implements Visitor<R, T> {
    @Override
    public R visit(FunctionDefinition a, T param) {
        throw new IllegalStateException();

    }

    @Override
    public R visit(VarDefinition a, T param) {
        throw new IllegalStateException();
    }

    @Override
    public R visit(AndOr a, T param) {
        throw new IllegalStateException();
    }

    @Override
    public R visit(Arithmetic a, T param) {
        throw new IllegalStateException();
    }

    @Override
    public R visit(ArrayAccess a, T param) {
        throw new IllegalStateException();
    }

    @Override
    public R visit(Cast a, T param) {
        throw new IllegalStateException();
    }

    @Override
    public R visit(CharLiteral a, T param) {
        throw new IllegalStateException();
    }

    @Override
    public R visit(Comparison a, T param) {
        throw new IllegalStateException();
    }

    @Override
    public R visit(FieldAccess a, T param) {
        throw new IllegalStateException();
    }

    @Override
    public R visit(FunctionInvocation a, T param) {
        throw new IllegalStateException();
    }

    @Override
    public R visit(IntLiteral a, T param) {
        throw new IllegalStateException();
    }

    @Override
    public R visit(Not a, T param) {
        throw new IllegalStateException();
    }

    @Override
    public R visit(RealLiteral a, T param) {
        throw new IllegalStateException();
    }

    @Override
    public R visit(UnaryMinus a, T param) {
        throw new IllegalStateException();
    }

    @Override
    public R visit(Variable a, T param) {
        throw new IllegalStateException();
    }

    @Override
    public R visit(Assignment a, T param) {
        throw new IllegalStateException();
    }

    @Override
    public R visit(IfElse a, T param) {
        throw new IllegalStateException();
    }

    @Override
    public R visit(Input a, T param) {
        throw new IllegalStateException();
    }

    @Override
    public R visit(Print a, T param) {
        throw new IllegalStateException();
    }

    @Override
    public R visit(Return a, T param) {
        throw new IllegalStateException();
    }

    @Override
    public R visit(While a, T param) {
        throw new IllegalStateException();
    }

    @Override
    public R visit(ArrayType a, T param) {
        throw new IllegalStateException();
    }

    @Override
    public R visit(CharType a, T param) {
        throw new IllegalStateException();
    }

    @Override
    public R visit(DoubleType a, T param) {
        throw new IllegalStateException();
    }

    @Override
    public R visit(ErrorType a, T param) {
        throw new IllegalStateException();
    }

    @Override
    public R visit(VoidType a, T param) {
        throw new IllegalStateException();
    }

    @Override
    public R visit(FieldDefinition a, T param) {
        throw new IllegalStateException();
    }

    @Override
    public R visit(FuncType a, T param) {
        throw new IllegalStateException();
    }

    @Override
    public R visit(IntType a, T param) {
        throw new IllegalStateException();
    }

    @Override
    public R visit(StrucType a, T param) {
        throw new IllegalStateException();
    }

    @Override
    public R visit(Program a, T param) {
        throw new IllegalStateException();
    }
}
