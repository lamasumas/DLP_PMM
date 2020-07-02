package ast.types;

import visitor.Visitor;

public class VoidType extends AbstractType{


    public VoidType(int theLine, int theColumn)
    {
        super(theLine, theColumn);

    }


    @Override
    public <R, T> R accept(Visitor<R, T> v, T param) {
        return v.visit(this, param);
    }


    @Override
    public boolean isValidReturnType(Type otherReturn) {
        return (otherReturn instanceof VoidType)? true: super.isValidReturnType(otherReturn);
    }


}
