package ast.expressions;

import visitor.Visitor;

public class CharLiteral extends AbstractExpression{

    private char value;

    public CharLiteral(int theLine, int theColumn, char value){
        super(theLine,theColumn);
        this.value = value;
    }


    @Override
    public <R, T> R accept(Visitor<R, T> v, T param) {
        return v.visit(this, param);
    }

    public char getValue(){
        return value;
    }
}
