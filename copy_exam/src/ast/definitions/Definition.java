package ast.definitions;

import ast.ASTNode;
import ast.Program;
import ast.types.Type;

public interface Definition extends ASTNode {

    Type getType();
    String getName();
    void setScope(int scope);
    int getScope();

}
