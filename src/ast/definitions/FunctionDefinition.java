package ast.definitions;

import ast.statements.Statement;
import ast.types.Type;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class FunctionDefinition extends  AbstractDefinition{

    private List<VarDefinition> definitionList;
    private List<Statement> statementList;

    public FunctionDefinition(int theLine, int theColumn, String theName, Type theType,  List<VarDefinition> definitionList, List<Statement> statementList){
        super(theLine, theColumn, theName, theType);
        this.definitionList = definitionList;
        this.statementList= statementList;


    }

    @Override
    public <R, T> R accept(Visitor<R, T> v, T param) {
        return v.visit(this, param);
    }


    public List<VarDefinition> getDefinitionList() {
        return definitionList;
    }

    public List<Statement> getStatementList() {
        return statementList;
    }
}

