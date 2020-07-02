package ast.expressions;

import ast.definitions.VarDefinition;
import visitor.Visitor;

public class Variable extends AbstractExpression {
    private String name;
    private VarDefinition varDefinition;

    public Variable( String name, int theLine, int theColumn) {
        super(theLine,theColumn);
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VarDefinition getVarDefinition() {
        return varDefinition;
    }

    public void setVarDefinition(VarDefinition varDefinition) {
        this.varDefinition = varDefinition;
    }

    @Override
    public <R, T> R accept(Visitor<R, T> v, T param) {
        return v.visit(this, param);
    }

    public String getName(){
        return name;
    }
}
