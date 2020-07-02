package ast.expressions;

import ast.definitions.FunctionDefinition;
import ast.statements.Statement;
import visitor.Visitor;

import java.util.List;

public class FunctionInvocation extends AbstractExpression implements  Expression, Statement  {
    private List<Expression> expressionList;
    private FunctionDefinition functionDefinition;
    private String name;

    public FunctionInvocation(int theLine, int theColumn, String name, List<Expression> expressionList) {
        super(theLine, theColumn);
        this.name = name;
        this.expressionList = expressionList;
    }

    public FunctionDefinition getFunctionDefinition() {
        return functionDefinition;
    }

    public void setFunctionDefinition(FunctionDefinition functionDefinition) {
        this.functionDefinition = functionDefinition;
    }

    @Override
    public <R, T> R accept(Visitor<R, T> v, T param) {
        return v.visit(this, param);
    }

    public String getName() {
        return name;
    }

    public List<Expression> getExpressionList() {
        return expressionList;
    }
}
