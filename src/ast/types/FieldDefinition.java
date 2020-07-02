package ast.types;

import visitor.Visitor;

public class FieldDefinition extends AbstractType
{
    private String fieldName;
    private Type fieldType;
    private int offset;

    public FieldDefinition(int theLine, int theColumn, String fieldName, Type fieldType )
    {
        super(theLine, theColumn);
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Type getFieldType() {
        return fieldType;
    }

    @Override
    public <R, T> R accept(Visitor<R, T> v, T param) {
        return v.visit(this, param);
    }

    @Override
    public int getByteSize() {
        return fieldType.getByteSize();
    }


    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public String getTypeString() {
        return "["+fieldName + ": " +  fieldType.getTypeString() + " offset: "+ offset + "]";
    }
}
