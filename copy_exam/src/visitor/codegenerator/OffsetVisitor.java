package visitor.codegenerator;

import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.FieldAccess;
import ast.types.FieldDefinition;
import ast.types.FuncType;
import ast.types.StrucType;
import visitor.AbstractVisitor;

import java.util.Collections;

public class OffsetVisitor extends AbstractVisitor<Void, Void>
{
    private int globalOffset = 0;
    private int localVariablesOffset = 0;
    private int parameterOffset = 0;
    @Override
    public Void visit(VarDefinition a, Void param) {
        if (a.getScope() == 0)
        {
            // Formula for the global variables
            a.setOffset(globalOffset);
            globalOffset += a.getType().getByteSize();

        }
        else
        {
            localVariablesOffset -= a.getType().getByteSize();
            a.setOffset(localVariablesOffset);
        }
        return super.visit(a, param);
    }

    @Override
    public Void visit(FuncType a, Void param) {
        parameterOffset = 4;

        for( int i = a.getParams().size() -1 ; i>= 0; i--){
            VarDefinition paramVar = a.getParams().get(i);
            //Formula for param
            paramVar.setOffset(parameterOffset);
            parameterOffset += paramVar.getType().getByteSize();
        };
        return null;
    }

    @Override
    public Void visit(FunctionDefinition a, Void param) {
        localVariablesOffset = 0;
        return super.visit(a, param);
    }

    @Override
    public Void visit(StrucType a, Void param) {
        int offset = 0;
        for(FieldDefinition fieldDefinition: a.getFieldDefinitionList()){
            fieldDefinition.setOffset(offset);
            offset += fieldDefinition.getByteSize();
        }
        return null;
    }
}
