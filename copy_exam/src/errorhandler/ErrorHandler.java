package errorhandler;

import ast.types.ErrorType;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ErrorHandler
{

    private static ErrorHandler theHandler;
    private List<ErrorType> errorList;

    private ErrorHandler(){
        errorList = new ArrayList<>();
    }

    public static ErrorHandler getInstance(){
        if(theHandler ==null)
        {
            theHandler = new ErrorHandler();
        }
        return theHandler;

    }

    public boolean anyError(){
        return !errorList.isEmpty();
    }

    public void addError(ErrorType newError){
        errorList.add(newError);
    }

    public void showErrors(PrintStream thePrintStream){
        errorList.forEach(theError -> thePrintStream.println(theError.getMessage()));
    }
    public void cleanError(){
        errorList.clear();
    }
}
