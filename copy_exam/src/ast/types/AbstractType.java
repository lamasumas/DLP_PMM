package ast.types;

import ast.ASTNode;
import ast.expressions.Expression;

import java.util.List;

public abstract class AbstractType implements ASTNode, Type
{
    private int theLine;
    private int theColumn;
    public AbstractType(int theLine, int theColumn)
    {
        this.theColumn = theColumn;
        this.theLine = theLine;
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
    public boolean isLogic() {
        return false;
    }

    @Override
    public Type arithmetic(Type otherType) {
        if( otherType instanceof  ErrorType)
            return otherType;
        else
            return null;
    }

    @Override
    public Type comparison(Type otherType) {
        if( otherType instanceof  ErrorType)
            return otherType;
        else
            return null;


    }


    @Override
    public Type logic(Type otherType) {
        if( otherType instanceof  ErrorType)
            return otherType;
        else
            return null;
    }

    @Override
    public Type unaryMinus() {
        return null;
    }

    @Override
    public Type negation() {
        return null;
    }

    @Override
    public Type promotesTo(Type otherType) {
        if( otherType instanceof  ErrorType)
            return otherType;
        else
            return null;

    }

    @Override
    public boolean isBuiltInType() {
        return false;
    }

    @Override
    public Type canBeCast(Type otherType) {
        if( otherType instanceof  ErrorType)
            return otherType;
        else
            return null;

    }

    @Override
    public Type squareBrackets(Type otherType) {
        if( otherType instanceof  ErrorType)
            return otherType;
        else
            return null;

    }

    @Override
    public Type dot(String theField) {
        return null;

    }

    @Override
    public Type parenthesis(List<Expression> param, int errorline) {
        return null;

    }


    @Override
    public boolean isValidReturnType(Type otherReturn) {
        return false;
    }

    @Override
    public int getByteSize() {
        return 0;
    }

    @Override
    public String getSuffix() {
        return null;
    }

    @Override
    public String getTypeString() {
        return null;
    }
}
