package ast.types;

import ast.expressions.Expression;
import errorhandler.ErrorHandler;
import visitor.Visitor;

import java.util.List;

public class ErrorType extends AbstractType
{
    private String message;
    private ErrorHandler eh = ErrorHandler.getInstance();
    public ErrorType(int theLine, int theColumn, String message) {
        super(theLine, theColumn);
        this.message = message;
        eh.addError(this);

    }

    public String getMessage() {
        return message;
    }

    @Override
    public <R, T> R accept(Visitor<R, T> v, T param) {
        return v.visit(this, param);
    }

    @Override
    public Type arithmetic(Type otherType) {
        return this;
    }

    @Override
    public boolean isValidReturnType(Type otherReturn) {
        return (otherReturn instanceof ErrorType)? true: super.isValidReturnType(otherReturn);
    }


    @Override
    public Type comparison(Type otherType) {
        return this;
    }

    @Override
    public Type logic(Type otherType) {
        return this;
    }

    @Override
    public Type promotesTo(Type otherType) {
        return this;
    }

    @Override
    public Type canBeCast(Type otherType) {
        return this;
    }

    @Override
    public Type squareBrackets(Type otherType) {
        return this;
    }

    @Override
    public Type dot(String theField) {
        return this;
    }

    @Override
    public Type parenthesis(List<Expression> param, int errorline) {
        return this;
    }

    @Override
    public Type unaryMinus() {
        return this;
    }


}
