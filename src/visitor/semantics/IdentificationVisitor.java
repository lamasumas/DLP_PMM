package visitor.semantics;

import ast.definitions.Definition;
import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.FunctionInvocation;
import ast.expressions.Variable;
import ast.types.ErrorType;
import semantic.symboltable.SymbolTable;
import visitor.AbstractVisitor;

import java.util.ArrayList;
import java.util.List;

public class IdentificationVisitor extends AbstractVisitor<Void, Void> {

    private SymbolTable st;
    public IdentificationVisitor(){
        super();
        st= new SymbolTable();
    }


    @Override
    public Void visit(FunctionDefinition a, Void param) {
        if(!st.insert(a))
            new ErrorType(a.getLine(), a.getColumn(), "Duplicate function definition in line " + a.getLine());

        st.set();
        super.visit(a, param);
        st.reset();
        return null;


    }

    @Override
    public Void visit(VarDefinition a, Void param) {
        super.visit(a, param);
        if(!st.insert(a))
            new ErrorType(a.getLine(), a.getColumn(), "Duplicate variable definition in line " + a.getLine());
        return null;
    }

    @Override
    public Void visit(FunctionInvocation a, Void param) {
        super.visit(a, param);
        Definition savedFunctionDefinition = st.find(a.getName());
        if(savedFunctionDefinition != null && savedFunctionDefinition instanceof FunctionDefinition)
            a.setFunctionDefinition((FunctionDefinition)savedFunctionDefinition);
        else if(savedFunctionDefinition == null)
            a.setFunctionDefinition(new FunctionDefinition(a.getLine(), a.getColumn(), a.getName(), new ErrorType(a.getLine(), a.getColumn(), "Function definition no defined for the funcion call in " + a.getLine()),
                    new ArrayList<>(), new ArrayList<>()));


        return null;

    }

    @Override
    public Void visit(Variable a, Void param) {
        super.visit(a, param);
        Definition savedVarDefinition = st.find(a.getName());
        if(savedVarDefinition != null && savedVarDefinition instanceof VarDefinition)
            a.setVarDefinition((VarDefinition) (savedVarDefinition));
        else if(savedVarDefinition == null)
            a.setVarDefinition(new VarDefinition(a.getLine(), a.getColumn(), new ErrorType(a.getLine(), a.getColumn(), "Variable " + a.getName() + " no defined for the varibale  in " + a.getLine()), a.getName()));


        return null;
    }
}
