package ast.expressions;

import visitor.Visitor;

public class Arithmetic extends  AbstractExpression {


    private  String operator;
    private Expression leftExpression;
    private Expression rightExpression;


    public Arithmetic(int theColumn, int theLine, String operator, Expression leftExpression, Expression rightExpression){
        super(theLine,theColumn);
        this.operator = operator;
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }



    @Override
    public <R, T> R accept(Visitor<R, T> v, T param) {
        return v.visit(this, param);
    }

    public String getOperator(){
        return operator;
    }

    public Expression getLeftExpression() {
        return leftExpression;
    }

    public Expression getRightExpression() {
        return rightExpression;
    }
}
