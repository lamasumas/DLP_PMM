package ast.statements;

import ast.expressions.Expression;
import visitor.Visitor;

import java.util.List;

public class IfElse implements Statement{

    private int theLine;
    private int theColumn;
    private List<Statement> ifStatements;
    private List<Statement> elseStatements;
    private Expression theExpression;

    public IfElse(int theLine, int theColumn, List<Statement> ifStatements, List<Statement> elseStatements, Expression theExpression){
        this.theColumn = theColumn;
        this.theLine = theLine;
        this.ifStatements = ifStatements;
        this.elseStatements = elseStatements;
        this.theExpression = theExpression;
    }

    @Override
    public int getLine() {
        return theLine;
    }

    @Override
    public int getColumn() {
        return theColumn;
    }

    @Override
    public <R, T> R accept(Visitor<R, T> v, T param) {
        return v.visit(this, param);
    }

    public List<Statement> getIfStatements() {
        return ifStatements;
    }

    public List<Statement> getElseStatements() {
        return elseStatements;
    }

    public Expression getTheExpression() {
        return theExpression;
    }
}
