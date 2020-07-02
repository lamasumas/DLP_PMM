package ast.definitions;

import ast.definitions.Definition;
import ast.expressions.Expression;
import ast.types.ErrorType;
import ast.types.Type;
import errorhandler.ErrorHandler;
import visitor.Visitor;

import java.util.List;

public class VarDefinition extends AbstractDefinition {

    private int offset;
    private boolean hasValue = false;
    private Expression value;
    public VarDefinition(int theLine, int theColumn, Type theType, String theName){
        super(theLine, theColumn, theName, theType);
        hasValue = false;
    }
    public VarDefinition(int theLine, int theColumn, Type theType, String theName, Expression value){
        super(theLine, theColumn, theName, theType);
        this.value = value;
        hasValue = true;
    }

    @Override
    public <R, T> R accept(Visitor<R, T> v, T param) {
        return v.visit(this, param);
    }

    public Expression getValue() {
        return value;
    }

    public boolean isHasValue() {
        return hasValue;
    }

    public static void checkDuplicateNames(List<String> varNames, int xline, int xColumn)
    {
        varNames.forEach(theName -> {
                    if(varNames.stream().filter(x -> x.equals(theName)).count() >=2)
                        new ErrorType(xline,xColumn,"Duplicate var names (" + theName +") in line " + xline );
        });
    }
    public int getOffset(){
        return  offset;
    }

    public void setOffset(int offset){
        this.offset = offset;
    }

}