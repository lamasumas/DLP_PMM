package ast.types;

import visitor.Visitor;

public class DoubleType extends AbstractType {


    public DoubleType(int theLine, int theColumn)
    {
        super(theLine, theColumn);
    }

    @Override
    public <R, T> R accept(Visitor<R, T> v, T param) {
        return v.visit(this, param);
    }

    @Override
    public Type arithmetic(Type otherType) {
        if( otherType instanceof DoubleType )
            return this;
        return super.arithmetic(otherType);
    }

    @Override
    public Type comparison(Type otherType) {
        if(otherType instanceof DoubleType)
            return new IntType(this.getLine(), this.getColumn());
        else
            return super.comparison(otherType);
    }

    @Override
    public Type promotesTo(Type otherType) {
        if( otherType instanceof  DoubleType)
            return this;
        return super.promotesTo(otherType);
    }

    @Override
    public boolean isBuiltInType() {
        return true;
    }

    @Override
    public Type canBeCast(Type otherType) {
        if( otherType instanceof  CharType ||  otherType instanceof IntType)
            return otherType;
        return super.canBeCast(otherType);
    }


    @Override
    public boolean isValidReturnType(Type otherReturn) {
        return (otherReturn instanceof DoubleType)? true: super.isValidReturnType(otherReturn);
    }

    @Override
    public Type unaryMinus() {
        return this;
    }


    @Override
    public int getByteSize() {
        return 4;
    }

    @Override
    public String getSuffix() {
        return "f";
    }

    @Override
    public String getTypeString() {
        return "DoubleType";
    }
}
