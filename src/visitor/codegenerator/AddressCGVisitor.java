package visitor.codegenerator;

import ast.expressions.ArrayAccess;
import ast.expressions.FieldAccess;
import ast.expressions.Variable;
import ast.types.FieldDefinition;
import ast.types.StrucType;

public class AddressCGVisitor  extends  AbstractCGVisitor {




   private CodeGenerator cg;
   private ValueCGVisitor valueCGVisitor;

    public void setCodeGenerator( CodeGenerator cg){
        this.cg = cg;
    }


    public void setValueCGVisitor(ValueCGVisitor valueCGVisitor){
        this.valueCGVisitor = valueCGVisitor;
    }

    @Override
    public Object visit(ArrayAccess a, Object param) {
        /**
         * address[[ indexing: exp -> exp2 exp3]] () =
         *  addresss[[exp2]] ()
         *  value[[exp3]]
         *  <pushi> exp1.type.getByteSize()
         *  <muli>
         *  <addi>
         */
        a.getFirstExpression().accept(this, param);
        a.getSecondExpression().accept(valueCGVisitor, param);
        cg.push(a.getType().getByteSize());
        cg.arithmetic("*","i");
        cg.arithmetic("+", "i");

        return null;
    }

    @Override
    public Object visit(FieldAccess a, Object param) {
        /**
         *  address[[FieldAccess: exp1 -> exp2 ID] () =
         *      address[[exp2]]()
         *      <pushi> exp2.getT
         */
        a.getFirstExpression().accept(this, param);
        cg.push(((StrucType)a.getFirstExpression().getType()).getField(a.getSecondExpression()).getOffset());
        cg.arithmetic("+", "i");
        return null;
    }

    @Override
    public Object visit(Variable a, Object param) {
        /**
         * address[[ Variable: expression -> ID]] () =
         *  if(expression.definition.scope == 0)
         *      <pusha> expression.definition.offset
         *  else
         *      <push bp>
         *      <pushi> expression.definition.offset
         *      <addi>
         */
        if (a.getVarDefinition().getScope() == 0)
            cg.pusha(a.getVarDefinition().getOffset());
        else {
            cg.pushBP();
            cg.push(a.getVarDefinition().getOffset());
            cg.arithmetic("+", "i");
        }
        return null;
    }
}
