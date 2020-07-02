package ast.statements;

import ast.expressions.Expression;
import visitor.Visitor;

public class Assignment implements Statement {

    private int theLine;
    private int theColumn;
    private Expression leftExpression;
    private Expression rightExpression;

    public Assignment(int theLine, int theColumn, Expression leftExpression, Expression rightExpression){
        this.theColumn = theColumn;
        this.theLine = theLine;
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public int getLine() {
        return theLine;
    }

    @Override
    public int getColumn() {
        return theColumn;
    }

    @Override
    public <R, T> R accept(Visitor<R, T> v, T param) {
        return v.visit(this, param);

    }

    public Expression getLeftExpression() {
        return leftExpression;
    }

    public Expression getRightExpression() {
        return rightExpression;
    }
}
