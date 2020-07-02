package visitor;

import ast.Program;
import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;

public class AbstractVisitor <R,T> implements Visitor<R,T>{


    @Override
    public R visit(FunctionDefinition a, T param) {
        a.getType().accept(this, param);
        a.getDefinitionList().forEach( def -> def.accept(this, param));
        a.getStatementList().forEach( stat -> stat.accept(this, param));
        return null;
    }

    @Override
    public R visit(VarDefinition a, T param) {
        a.getType().accept(this, param);
        return null;
    }

    @Override
    public R visit(AndOr a, T param) {
        a.getLeftExpression().accept(this, param);
        a.getRightExpression().accept(this, param);
        return null;
    }

    @Override
    public R visit(Arithmetic a, T param) {
        a.getLeftExpression().accept(this, param);
        a.getRightExpression().accept(this, param);
        return null;
    }

    @Override
    public R visit(ArrayAccess a, T param) {
        a.getFirstExpression().accept(this, param);
        a.getSecondExpression().accept(this, param);
        return null;
    }

    @Override
    public R visit(Cast a, T param) {
        a.getTheExpression().accept(this, param);
        a.getTheType().accept(this, param);
        return null;
    }

    @Override
    public R visit(CharLiteral a, T param) {

        return null;
    }

    @Override
    public R visit(Comparison a, T param) {
        a.getLeftExpression().accept(this, param);
        a.getRightExpression().accept(this, param);
        return null;
    }

    @Override
    public R visit(FieldAccess a, T param) {
        a.getFirstExpression().accept(this, param);
        return null;
    }

    @Override
    public R visit(FunctionInvocation a, T param) {
        a.getExpressionList().forEach(exp -> exp.accept(this, param));
        return null;
    }

    @Override
    public R visit(IntLiteral a, T param) {

        return null;
    }

    @Override
    public R visit(Not a, T param) {
        a.getTheExpression().accept(this, param);
        return null;
    }

    @Override
    public R visit(RealLiteral a, T param) {
        return null;
    }

    @Override
    public R visit(UnaryMinus a, T param) {
        a.getExpression().accept(this, param);
        return null;
    }

    @Override
    public R visit(Variable a, T param) {

        return null;
    }

    @Override
    public R visit(Assignment a, T param) {
        a.getRightExpression().accept(this, param);
        a.getLeftExpression().accept(this, param);
        return null;
    }

    @Override
    public R visit(IfElse a, T param) {

        a.getElseStatements().forEach(elseStatement -> elseStatement.accept(this, param));
        a.getIfStatements().forEach(ifStatement -> ifStatement.accept(this, param));
        a.getTheExpression().accept(this, param);
        return null;
    }

    @Override
    public R visit(Input a, T param) {
        a.getExpressionList().forEach(expression -> expression.accept(this, param));
        return null;
    }

    @Override
    public R visit(Print a, T param) {
        a.getExpressionList().forEach(expression -> expression.accept(this, param));
        return null;
    }

    @Override
    public R visit(Return a, T param) {
        a.getTheExpression().accept(this, param);
        return null;
    }

    @Override
    public R visit(While a, T param) {
        a.getTheExpression().accept(this, param);
        a.getTheStatemList().forEach( statement -> statement.accept(this, param));
        return null;
    }

    @Override
    public R visit(ArrayType a, T param) {
        return null;
    }

    @Override
    public R visit(CharType a, T param) {
        return null;
    }

    @Override
    public R visit(DoubleType a, T param) {
        return null;
    }

    @Override
    public R visit(ErrorType a, T param) {
        return null;
    }

    @Override
    public R visit(VoidType a, T param) {
        return null;
    }

    @Override
    public R visit(FieldDefinition a, T param) {
        a.getFieldType().accept(this, param);
        return null;
    }

    @Override
    public R visit(FuncType a, T param) {
        a.getReturnType().accept(this, param);
        a.getParams().forEach(varDefinition -> varDefinition.accept(this, param));
        return null;
    }

    @Override
    public R visit(IntType a, T param) {
        return null;
    }

    @Override
    public R visit(StrucType a, T param) {
        a.getFieldDefinitionList().forEach(fieldDefinition -> fieldDefinition.accept(this, param));
        return null;
    }

    @Override
    public R visit(Program a, T param) {
        a.getDefinitionList().forEach(definition -> definition.accept(this, param)
        );
        return null;
    }
}
