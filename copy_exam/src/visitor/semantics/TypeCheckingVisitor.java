package visitor.semantics;

import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;
import visitor.AbstractVisitor;

public class TypeCheckingVisitor extends AbstractVisitor<Void, Type>
{
    @Override
    public Void visit(AndOr a, Type param) {
        super.visit(a, param);
        a.setLvalue(false);
        a.setType(a.getLeftExpression().getType().logic(a.getRightExpression().getType()));
        if (a.getType() == null){
            a.setType(new ErrorType(a.getLine(), a.getColumn(), "The element of line " +  a.getLine() + " cannot be negated"));
        }

        return null;
    }

    @Override
    public Void visit(Arithmetic a, Type param) {
        super.visit(a, param);
        a.setLvalue(false);
        a.setType(a.getLeftExpression().getType().arithmetic(a.getRightExpression().getType()));
        if(a.getType() == null){
            a.setType( new ErrorType(a.getLine(), a.getColumn(), "Arithmetic types used in line " + a.getLine() + " are wrong"));
        }
        return null;
    }

    @Override
    public Void visit(ArrayAccess a, Type param) {
        super.visit(a, param);
        a.setLvalue(true);
        a.setType(a.getFirstExpression().getType().squareBrackets(a.getSecondExpression().getType()));
        if(a.getType() == null){
            a.setType(new ErrorType(a.getLine(), a.getColumn(), "It cannot be indexed the element in line " + a.getLine()));
        }
        return null;
    }

    @Override
    public Void visit(Cast a, Type param) {
        super.visit(a, param);
        a.setLvalue(false);
        a.setType( a.getTheExpression().getType().canBeCast(a.getTheType()));
        if (a.getType() == null){
            a.setType(new ErrorType(a.getLine(), a.getColumn(), "The cast of the line " + a.getLine() + " cannot be done"));
        }
        return  null;
    }

    @Override
    public Void visit(Comparison a, Type param) {
        super.visit(a, param);
        a.setLvalue(false);
        a.setType(a.getLeftExpression().getType().comparison(a.getRightExpression().getType()));
        if(a.getType() == null){
            a.setType(new ErrorType(a.getLine(), a.getColumn(), "Each side of the comparison in line "+ a.getLine()+ " must be the same type (integer or double)"));
        }
        return null;

    }

    @Override
    public Void visit(FieldAccess a, Type param) {
        super.visit(a, param);
        a.setLvalue(true);
        a.setType(a.getFirstExpression().getType().dot(a.getSecondExpression()));
        if (a.getType() == null){
            a.setType( new ErrorType( a.getLine(), a.getColumn(), "The field '"+ a.getSecondExpression() + "' couldn't be found in the struc "));
        }
        return null;

    }

    @Override
    public Void visit(FunctionInvocation a, Type param) {
        super.visit(a, param);
        a.setLvalue(false);
        a.setType(a.getFunctionDefinition().getType().parenthesis(a.getExpressionList(), a.getLine()));
        if( a.getType() == null){
            a.setType(new ErrorType(a.getLine(), a.getColumn(), "The function invocation in line " + a.getLine()+ " has type mismatch in the parameters"));
        }
        return null;

    }


    @Override
    public Void visit(Not a, Type param) {
         super.visit(a, param);
         a.setLvalue(false);
         a.setType(a.getTheExpression().getType().negation());
         if (a.getType() == null){
             a.setType(new ErrorType(a.getLine(), a.getColumn(), "The elements of the negation in line " + a.getLine() + " cannot be negated"));
         }
         return null;
    }


    @Override
    public Void visit(UnaryMinus a, Type param) {
        super.visit(a, param);
        a.setLvalue(false);
        a.setType(a.getExpression().getType().unaryMinus());
        if(a.getType() == null)
        {
            a.setType(new ErrorType(a.getLine(), a.getColumn(), "Trying to apply a unaryminus in line "+ a.getLine() +" but it cannot be done"));
        }
        return null;
    }

    @Override
    public Void visit(Variable a, Type param) {
        super.visit(a, param);
        a.setLvalue(true);
        if( a.getVarDefinition() != null) {
            a.setType(a.getVarDefinition().getType());

        }
        return null;
    }

    @Override
    public Void visit(Assignment a, Type param) {
        super.visit(a, param);
        if(!a.getLeftExpression().isLvalue())
            new ErrorType(a.getLine(),a.getColumn(),"The lvalue is badly setted in line "+ a.getLine());
        a.getLeftExpression().setType(a.getLeftExpression().getType().promotesTo(a.getRightExpression().getType()));
        if( a.getLeftExpression().getType() == null){
            a.getLeftExpression().setType(new ErrorType(a.getLine(), a.getColumn(), "The promotion in the  assignment of line " +a.getLine() + " cannot be done"));
        }
        return null;

    }

    @Override
    public Void visit(Input a, Type param) {
        super.visit(a, param);
        a.getExpressionList().forEach( expression -> {
            if(!expression.isLvalue())
                new ErrorType(expression.getLine(),expression.getColumn(), "The lvalue is wrongly used in line " + expression.getLine());
        });
        return null;
    }


    @Override
    public Void visit(RealLiteral a, Type param) {
        super.visit(a, param);
        a.setLvalue(false);
        a.setType( new DoubleType( a.getLine(), a.getColumn()));
        return null;
    }

    @Override
    public Void visit(CharLiteral a, Type param) {
        super.visit(a, param);
        a.setLvalue(false);
        a.setType( new CharType(a.getLine(), a.getColumn()));
        return null;
    }

    @Override
    public Void visit(IntLiteral a, Type param) {
        super.visit(a, param);
        a.setLvalue(false);
        a.setType(new IntType(a.getLine(), a.getColumn()));
        return null;
    }


    @Override
    public Void visit(IfElse a, Type param) {
        super.visit(a, param);
        if (!a.getTheExpression().getType().isLogic())
            a.getTheExpression().setType(new ErrorType(a.getTheExpression().getLine(), a.getTheExpression().getColumn(),
                    "The type of the condition in line " + a.getTheExpression().getLine()+ " is not logical"));
        return null;
    }

    @Override
    public Void visit(While a, Type param) {
        super.visit(a, param);
        if (!a.getTheExpression().getType().isLogic())
            a.getTheExpression().setType(new ErrorType(a.getTheExpression().getLine(), a.getTheExpression().getColumn(),
                    "The type of the condition in line " + a.getTheExpression().getLine()+ " is not logical"));
        return null;
    }

    @Override
    public Void visit(FunctionDefinition a, Type param) {
        super.visit(a, a.getType());
        if(!a.getType().isBuiltInType())
            new ErrorType( a.getLine(), a.getColumn(), "The parameters type of the " + a.getName() +" function  defined in line " + a.getLine() + " are not built-in types");
        return null;
    }

    @Override
    public Void visit(Return a, Type param) {
        super.visit(a, param);
        if (!param.isValidReturnType(a.getTheExpression().getType()))
            new ErrorType(a.getLine(), a.getColumn(),"The return element in line "+ a.getLine()+ " mismatch the return type of the function");
        return null;
    }

    @Override
    public Void visit(VarDefinition a, Type param) {
        super.visit(a, param);
        if(a.isHasValue()) {
            a.getValue().accept(this, param);
            if (!a.getValue().getType().getTypeString().equals(a.getType().getTypeString()))
                new ErrorType(a.getLine(), a.getColumn(), "Type error in line  " + a.getLine());
        }
        return null;
    }
}