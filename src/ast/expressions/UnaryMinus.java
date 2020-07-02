package ast.expressions;

import visitor.Visitor;

public class UnaryMinus extends AbstractExpression{

    private Expression expression;

    public UnaryMinus( int theColumn, int theLine, Expression expression) {
        super(theLine,theColumn);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }



    @Override
    public <R, T> R accept(Visitor<R, T> v, T param) {
        return v.visit(this, param);
    }
}
