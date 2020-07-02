package ast.expressions;

import visitor.Visitor;

public class FieldAccess extends AbstractExpression{


    private String rightExpression;
    private Expression leftExpdression;

    public FieldAccess( int theColumn, int theLine, Expression leftExpression, String rightExpression)
    {
        super(theLine,theColumn);
        this.rightExpression = rightExpression;
        this.leftExpdression = leftExpression;
    }

    @Override
    public <R, T> R accept(Visitor<R, T> v, T param) {
        return v.visit(this, param);
    }

    public Expression getFirstExpression(){
        return leftExpdression;
    }

    public String getSecondExpression() {
        return rightExpression;
    }


}
