package ast.expressions;

import ast.ASTNode;
import ast.types.Type;

public interface Expression extends ASTNode {

    void setLvalue(boolean lvalue);
    boolean isLvalue();
    Type getType();
    void setType(Type theType);
}
