package ast.types;

import visitor.Visitor;

import java.util.List;
import java.util.Optional;

public class StrucType extends AbstractType {


    private List<FieldDefinition> fieldDefinitionList;
    public StrucType(int theLine, int theColumn, List<FieldDefinition> fieldDefinitionList)
    {

        super(theLine, theColumn);
        this.fieldDefinitionList = fieldDefinitionList;
        fieldDefinitionList.forEach( theField -> {
            if(fieldDefinitionList.stream().filter(x -> x.getFieldName().equals(theField.getFieldName())).count()>=2)
               new ErrorType(theLine, theColumn, "The field ("+ theField.getFieldName()+") is repeated in the struct definition in line "+ getLine());
        });

    }


    public List<FieldDefinition> getFieldDefinitionList() {
        return fieldDefinitionList;
    }

    @Override
    public <R, T> R accept(Visitor<R, T> v, T param) {
        return v.visit(this, param);
    }

    @Override
    public Type dot(String theField) {
         Optional<FieldDefinition> theDefinition = getFieldDefinitionList().stream().filter(field -> field.getFieldName().equals(theField)).findAny();
         if(theDefinition.isPresent())
             return theDefinition.get().getFieldType();
        return super.dot(theField);
    }

    @Override
    public int getByteSize() {
        return fieldDefinitionList.stream().map(x -> x.getByteSize()).reduce( 0, (x, y) -> x+y);
    }

    public FieldDefinition getField(String recordName){
        return getFieldDefinitionList().stream().filter(fieldDefinition -> fieldDefinition.getFieldName().equals(recordName)).findAny().get();
    }

    @Override
    public String getTypeString() {
        return "Stuct["+ getFieldDefinitionList().stream().map(fieldDefinition -> fieldDefinition.getTypeString()).
                reduce("Fields: ",(s, s2) -> s +"," +s2)+ "]";
    }
}
