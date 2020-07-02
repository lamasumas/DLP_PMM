package ast.types;

import ast.ASTNode;
import ast.expressions.Expression;

import java.util.List;


public interface Type extends ASTNode
{
    boolean isLogic();
    Type logic( Type otherType);
    Type negation();
    Type arithmetic(Type otherType);
    Type comparison(Type otherType);
    Type promotesTo(Type otherType);
    boolean isBuiltInType();
    Type canBeCast(Type otherType);
    Type squareBrackets(Type otherType);
    Type dot(String theField);
    Type parenthesis(List<Expression> param, int errorline);
    Type unaryMinus();
    int getByteSize();
    String getSuffix();
    boolean isValidReturnType( Type otherReturn);
    String getTypeString();
}
