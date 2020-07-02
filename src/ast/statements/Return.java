package ast.statements;

import ast.expressions.Expression;
import visitor.Visitor;

public class Return implements Statement{

    private int theLine;
    private int theColumn;
    private Expression theExpression;


    public Return( int theLine, int theColumn, Expression theExpression){
        this.theColumn = theColumn;
        this.theLine = theLine;
        this.theExpression = theExpression;
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

}
