package ast;

import visitor.Visitor;

public interface ASTNode
{
    int getLine();
    int getColumn();
    <R,T> R accept(Visitor<R,T> v, T param);
}
