package ast.types;

import visitor.Visitor;

public class ArrayType extends AbstractType {

    private  int theLine;
    private  int theColumn;
    private int theSize;
    private Type theType;

    public ArrayType(int theLine, int theColumn, int theSize, Type theType)
    {
        super(theLine, theColumn);
        this.theSize = theSize;
        this.theType = theType;
    }

    @Override
    public <R, T> R accept(Visitor<R, T> v, T param) {
        return v.visit(this, param);
    }

    @Override
    public Type squareBrackets(Type otherType) {
        if( otherType instanceof  IntType)
            return  theType;
        return super.squareBrackets(otherType);
    }

    @Override
    public int getByteSize() {
        return theSize * theType.getByteSize();
    }

    @Override
    public String getTypeString() {
        return "ArrayType [of: " + theType.getTypeString() + ", size: " + theSize +"]" ;
    }
}
