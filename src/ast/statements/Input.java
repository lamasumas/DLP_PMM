package ast.statements;

import ast.expressions.Expression;
import visitor.Visitor;

import java.util.List;

public class Input implements Statement{

    private int theLine;
    private int theColumn;
    private List<Expression> expressionList;

    public Input( int theLine,int theColumn, List<Expression> expressionList){
        this.theColumn = theColumn;
        this.theLine = theLine;
        this.expressionList = expressionList;
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

    public List<Expression> getExpressionList() {
        return expressionList;
    }
}
