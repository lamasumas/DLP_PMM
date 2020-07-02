package ast.types;

import visitor.Visitor;

public class IntType extends AbstractType {


    public IntType(int theLine, int theColumn)
    {
        super(theLine, theColumn);
    }


    @Override
    public <R, T> R accept(Visitor<R, T> v, T param) {
        return v.visit(this, param);
    }

    @Override
    public boolean isLogic() {
        return true;
    }

    @Override
    public Type arithmetic(Type otherType) {
        if( otherType instanceof IntType )
            return this;
        return super.arithmetic(otherType);
    }

    @Override
    public Type unaryMinus() {
        return this;
    }

    @Override
    public Type comparison(Type otherType) {
        if(otherType instanceof IntType)
            return  this;
        else
            return super.comparison(otherType);
    }

    @Override
    public Type logic(Type otherType) {
        if( otherType instanceof  IntType)
            return this;
        return super.logic(otherType);
    }



    @Override
    public Type promotesTo(Type otherType) {
        if( otherType instanceof  IntType)
            return this;
        return super.promotesTo(otherType);
    }

    @Override
    public boolean isBuiltInType() {
        return true;
    }
    @Override
    public Type canBeCast(Type otherType) {
        if( otherType instanceof  DoubleType ||  otherType instanceof CharType)
            return otherType;
        return super.canBeCast(otherType);
    }


    @Override
    public boolean isValidReturnType(Type otherReturn) {
        return (otherReturn instanceof IntType)? true: super.isValidReturnType(otherReturn);
    }


    @Override
    public Type negation() {
        return this;
    }

    @Override
    public int getByteSize() {
        return 2;
    }

    @Override
    public String getSuffix() {
        return "i";
    }

    @Override
    public String getTypeString() {
        return "IntType";
    }
}
