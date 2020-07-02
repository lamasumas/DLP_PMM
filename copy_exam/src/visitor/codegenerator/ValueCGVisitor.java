package visitor.codegenerator;

import ast.expressions.*;

public class ValueCGVisitor extends AbstractCGVisitor {

    private CodeGenerator cg ;
    private ExecuteCGVisitor executeCGVisitor;
    private AddressCGVisitor addressCGVisitor;


    public void setAddressCGVisitor(AddressCGVisitor theVisistor){
        this.addressCGVisitor = theVisistor;
    }

    public void setExecuteCGVisitor(ExecuteCGVisitor theVisitor){
        this.executeCGVisitor = theVisitor;
    }

    public void setCodeGenerator(CodeGenerator cg){
        this.cg = cg;
    }

    @Override
    public Object visit(AndOr a, Object param) {
        /**
         *  value[[ AndOr: exp -> exp1 (|| | && ) exp2]] () =
         *      value[[exp1]]
         *      value[[exp2]]
         *      switch(exp.getOperator()){
         *          case "||": <or> break;
         *          case "&&": <and> break;
         *      }
         */
        a.getLeftExpression().accept(this, param);
        a.getRightExpression().accept(this, param);
        cg.logical(a.getOperator());
        return null;
    }

    @Override
    public Object visit(Arithmetic a, Object param) {
        /**
         *  value[[ Arithmetic: exp -> exp1 (+|-|*|/) exp2]] () =
         *      value[[exp1]]
         *      <exp1.type.getSuffix() 2 exp.type.getSuffix()>
         *      value[[exp2]]
         *      <exp2.type.getSuffix() 2 exp.type.getSuffix()>
         *      switch(a.getOperator())
         *      {
         *          case "+": <add exp.type.getSuffix()> break;
         *          case "-": <sub exp.type.getSuffix()> break;
         *          case "*": <mul exp.type.getSuffix()> break;
         *          case "/": <div exp.type.getSuffix()> break;
         *      }
         */
        a.getLeftExpression().accept(this, param);
        a.getRightExpression().accept(this, param);
        cg.arithmetic(a.getOperator(), a.getType().getSuffix());
        return null;
    }

    @Override
    public Object visit(ArrayAccess a, Object param) {
        /**
         *  value[[ ArrayAccess: exp -> exp1 exp2]] () =
         *      address[[exp]]
         *      <load> exp.type.getSuffix()
         */
        a.accept(addressCGVisitor, param);
        cg.load(a.getType().getSuffix());
        return null;
    }

    @Override
    public Object visit(Cast a, Object param) {
        /**
         *  value[[ Cast: exp -> (type) exp1]] () =
         *      value[[exp1]]
         *      <exp1.type.getSuffix() 2 type.getSuffix()>
         *
         */
        a.getTheExpression().accept(this, param);
        cg.convert(a.getTheExpression().getType().getSuffix(), a.getTheType().getSuffix());
        return null;
    }

    @Override
    public Object visit(CharLiteral a, Object param) {
        /**
         *  value[[CharLiteral: exp -> CHAR_CONSTANT]]  () =
         *      <pushb> exp.value
         */

        cg.push(a.getValue());
        return null;
    }

    @Override
    public Object visit(Comparison a, Object param) {
        /**
         *  value[[Comparison: exp -> exp1 (==|!=|>|>=|<|<=)  exp2]] () =
         *      value[[exp1]]()
         *      value[exp2]]()
         *      switch( exp.getOperator()){
         *          case "==": <eq exp.type.getSuffix()> break;
         *          case "!=":<ne exp.type.getSuffix()> break;
         *          case "<=":<le exp.type.getSuffix()> break;
         *          case "<":<lt exp.type.getSuffix()> break;
         *          case ">":<gt exp.type.getSuffix()> break;
         *          case ">=":<ge exp.type.getSuffix()> break;
         *
         *
         */
        a.getLeftExpression().accept(this, param);
        a.getRightExpression().accept(this, param);
        cg.comparison(a.getOperation(), a.getType().getSuffix());

        return null;
    }

    @Override
    public Object visit(FieldAccess a, Object param) {
        /**
         *  value[[FieldAccess: exp1 -> exp2 ID]] () =
         *      address[[exp1]]()
         *      <load> exp1.getType.suffix()
         */
        a.accept(addressCGVisitor, param);
        cg.load(a.getType().getSuffix());
        return null;
    }

    @Override
    public Object visit(FunctionInvocation a, Object param) {
        /**
         *  value[[FunctionInvocation: exp -> exp1 exp2*]] () =
         *      for(Expression ex: exp2*)
         *          value[[ex]]()
         *      <call> ex1.name
         */
        a.getExpressionList().forEach( expression ->  expression.accept(this, null));
        cg.call(a.getName());
        return null;
    }

    @Override
    public Object visit(IntLiteral a, Object param) {
        /**
         *  value[[IntLiteral: exp -> INT_CONSTANT]]  () =
         *      <pushi> exp.value
         */
        cg.push(a.getValue());
        return null;
    }

    @Override
    public Object visit(Not a, Object param) {
        /**
         *  value[[ Not: exp -> ! exp1 ]] () =
         *      value[[exp1]]()
         *      <not>
         */
        a.getTheExpression().accept(this, param);
        cg.not();
        return null;
    }

    @Override
    public Object visit(RealLiteral a, Object param) {
        /**
         *  value[[RealLiteral: exp -> REAL_CONSTANT]]  () =
         *      <pushf> exp.value
         */
        cg.push(a.getValue());
        return null;
    }

    @Override
    public Object visit(UnaryMinus a, Object param) {
        /**
         *  value[[UnaryMinus: exp -> - exp1]] () =
         *      value[[exp1.value]]
         *      <pushi> -1
         *      <mul a.type.getSuffix()>
         */
        a.getExpression().accept(this, param);
        cg.push(-1);
        cg.arithmetic("*", a.getType().getSuffix());
        return null;
    }

    @Override
    public Object visit(Variable a, Object param) {
        /**
         *  value[[ Variable: expression -> ID]] () =
         *      address[[expression]] ()
         *      <loadi> expression.type.suffix()
         */

        addressCGVisitor.visit(a, param);
        cg.load(a.getType().getSuffix());
        return null;
    }
}
