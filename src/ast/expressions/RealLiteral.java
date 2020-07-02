package ast.expressions;

import visitor.Visitor;

public class RealLiteral extends AbstractExpression {

    private double value;


    public RealLiteral(int theLine, int theColumn, double value) {
        super(theLine,theColumn);
        this.value = value;
    }


    @Override
    public <R, T> R accept(Visitor<R, T> v, T param) {
        return v.visit(this, param);
    }

    public  double getValue(){
        return value;
    }
}
