package ast.statements;

import ast.expressions.Expression;
import visitor.Visitor;

import java.util.List;

public class Print implements  Statement{


    private int theLine;
    private int theColumn;
    private List<Expression> expressionList;

    public Print(int theLine, int theColumn, List<Expression> expressionList){
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
