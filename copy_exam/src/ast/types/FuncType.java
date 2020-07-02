package ast.types;

import ast.definitions.VarDefinition;
import ast.expressions.Expression;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class FuncType extends AbstractType {

    private Type returnType;
    private List<VarDefinition> params;
    public FuncType(int theLine, int theColumn,  Type returnType, List<VarDefinition> params)
    {
        super(theLine, theColumn);
        this.returnType = returnType;
        this.params = params;
    }


    public Type getReturnType() {
        return returnType;
    }

    public List<VarDefinition> getParams() {
        return params;
    }

    @Override
    public <R, T> R accept(Visitor<R, T> v, T param) {
        return v.visit(this, param);
    }

    @Override
    public boolean isBuiltInType() {
        Object[] temp =  getParams().stream().filter(x -> x.getType().isBuiltInType() == false).toArray();
        return temp.length == 0;
    }

    @Override
    public Type parenthesis(List<Expression> param, int errorline) {
        /**
         * Here we need to check the possible parameters size, and if it is in the correct bounds allow this.
         */
        if( param .size() == params .size())
        {
            for(int i = 0; i< param.size(); i++)
            {
                if( !param.get(i).getType().getClass().equals( params.get(i).getType().getClass()) )
                    return super.parenthesis(param, errorline);
            }
            return returnType;
        }
        return super.parenthesis(param, errorline);
    }

    @Override
    public boolean isValidReturnType(Type otherReturn) {
        return returnType.isValidReturnType(otherReturn);
    }

    @Override
    public int getByteSize() {
        return returnType.getByteSize();
    }
}
