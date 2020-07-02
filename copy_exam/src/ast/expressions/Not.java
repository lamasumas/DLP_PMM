package ast.expressions;

import visitor.Visitor;

public class Not extends AbstractExpression{


    private Expression theExpression;

    public Not(int theColumn, int theLine, Expression theExpression){
        super(theLine,theColumn);
        this.theExpression = theExpression;
    }


    @Override
    public <R, T> R accept(Visitor<R, T> v, T param) {
        return v.visit(this, param);
    }

    public Expression getTheExpression() {
        return theExpression;
    }
}
