package ast.expressions;

import ast.types.ErrorType;
import ast.types.Type;
import visitor.Visitor;

public abstract class AbstractExpression implements Expression {
    private boolean lvalue;
    private int theLine;
    private int theColumn;
    private Type theType;

    public AbstractExpression(int theLine, int theColumn){
        this.theColumn = theColumn;
        this.theLine = theLine;
    }

    @Override
    public void setLvalue(boolean lvalue) {
        this.lvalue = lvalue;
    }

    @Override
    public boolean isLvalue() {
        return lvalue;
    }

    @Override
    public int getLine() {
        return theLine;
    }

    @Override
    public int getColumn() {
        return theColumn;
    }

    @Override
    public Type getType(){
        return theType;
    }

    @Override
    public void setType(Type theType){
        this.theType =  theType;
    }

    @Override
    public abstract  <R, T> R accept(Visitor<R, T> v, T param) ;


}
