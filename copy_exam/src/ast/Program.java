package ast;


import ast.definitions.Definition;
import visitor.Visitor;

import java.util.List;

public class Program implements ASTNode
{
    private int line, column;

    private List<Definition> definitionList;
    public Program(List<Definition> definitionList, int line, int column){
        this.definitionList = definitionList;
        this.line = line;
        this.column = column;

    }

    public List<Definition> getDefinitionList() {
        return definitionList;
    }

    public void setDefinitionList(List<Definition> definitionList) {
        this.definitionList = definitionList;
    }
    @Override
    public int getLine() {
        return line;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public <R, T> R accept(Visitor<R, T> v, T param) {
        return v.visit(this, param);
    }


}
