package ast.expressions;

import visitor.Visitor;

public class ArrayAccess extends AbstractExpression {


    private Expression firstExpression;
    private Expression secondExpression;

    public ArrayAccess(int theColumn, int theLine,  Expression firstExpression, Expression secondExpression)
    {
        super(theLine,theColumn);
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }


    @Override
    public <R, T> R accept(Visitor<R, T> v, T param) {
        return v.visit(this, param);
    }

    public Expression getFirstExpression() {
        return firstExpression;
    }

    public Expression getSecondExpression() {
        return secondExpression;
    }
}
