package ast.types;

import visitor.Visitor;

public class CharType extends AbstractType {

    public CharType(int theLine, int theColumn)
    {
        super(theLine, theColumn);
    }

    @Override
    public <R, T> R accept(Visitor<R, T> v, T param) {
        return v.visit(this, param);
    }

    @Override
    public Type promotesTo(Type otherType) {
        if( otherType instanceof  CharType)
            return this;
        return super.promotesTo(otherType);
    }

    @Override
    public boolean isBuiltInType() {
        return true;
    }

    @Override
    public Type canBeCast(Type otherType) {
        if( otherType instanceof  DoubleType ||  otherType instanceof IntType)
            return otherType;
        return super.canBeCast(otherType);
    }

    @Override
    public int getByteSize() {
        return 1;
    }

    @Override
    public boolean isValidReturnType(Type otherReturn) {
        return (otherReturn instanceof CharType)? true: super.isValidReturnType(otherReturn);
    }

    @Override
    public String getSuffix() {
        return "b";
    }

    @Override
    public String getTypeString() {
        return "CharType";
    }
}
