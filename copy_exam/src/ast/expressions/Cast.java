package ast.expressions;

import ast.types.Type;
import visitor.Visitor;

public class Cast extends AbstractExpression {

    private int theLine;
    private int theColumn;
    private Expression theExpression;
    private Type theType;

    public Cast(int theColumn, int theLine, Expression theExpression, Type theType){
        super(theLine,theColumn);
        this.theExpression = theExpression;
        this.theType = theType;
    }


    @Override
    public <R, T> R accept(Visitor<R, T> v, T param) {
        return v.visit(this, param);
    }

    public Expression getTheExpression() {
        return theExpression;
    }

    public Type getTheType() {
        return theType;
    }
}
