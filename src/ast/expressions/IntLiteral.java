package ast.expressions;

import ast.types.IntType;
import ast.types.Type;
import visitor.Visitor;

public class IntLiteral extends AbstractExpression {


    private int value;

    public IntLiteral(int theLine, int theColumn, int value) {
        super(theLine,theColumn);
        this.value = value;
    }

    @Override
    public <R, T> R accept(Visitor<R, T> v, T param) {
        return v.visit(this, param);
    }

    public int getValue(){
        return value;
    }


}
