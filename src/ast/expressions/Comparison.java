package ast.expressions;

import visitor.Visitor;

public class Comparison extends AbstractExpression {


    private String operation;
    private  Expression leftExpression;
    private  Expression rightExpression;


    public Comparison(int theColumn, int theLine, String operator, Expression leftExpression, Expression rightExpression){
        super(theLine,theColumn);
        this.operation = operator;
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }


    @Override
    public <R, T> R accept(Visitor<R, T> v, T param) {
        return v.visit(this, param);
    }

    public String getOperation(){
        return operation;
    }

    public Expression getLeftExpression() {
        return leftExpression;
    }

    public Expression getRightExpression() {
        return rightExpression;
    }
}
