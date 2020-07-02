package ast.statements;

import ast.expressions.Expression;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class While implements Statement {

    private int theLine;
    private int theColumn;
    private Expression theExpression;
    private List<Statement> theStatemntList;

    public While(int theLine, int theColumn, Expression theExpression, List<Statement> theStatemntList){
        this.theColumn = theColumn;
        this.theLine = theLine;
        this.theExpression = theExpression;
        this.theStatemntList = theStatemntList;

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

    public Expression getTheExpression() {
        return theExpression;
    }

    public List<Statement> getTheStatemList() {
        return theStatemntList;
    }
}
