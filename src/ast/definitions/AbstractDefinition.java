package ast.definitions;

import ast.types.Type;
import visitor.Visitor;

public abstract class AbstractDefinition implements  Definition {

    private Type theType;
    private String theName;
    private int theLine;
    private int theColumn;
    private  int scope;

    public AbstractDefinition(int theLine, int theColumn, String theName, Type theType){
        this.theType = theType;
        this.theColumn = theColumn;
        this.theLine  = theLine;
        this.theName = theName;

    }
    @Override
    public Type getType() {
        return theType;
    }

    @Override
    public String getName() {
        return theName;
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
    public void setScope(int scope) {
        this.scope = scope;
    }

    @Override
    public int getScope() {
        return scope;
    }



    @Override
    public abstract  <R, T> R accept(Visitor<R, T> v, T param);
}
