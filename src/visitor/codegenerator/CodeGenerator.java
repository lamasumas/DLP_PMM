package visitor.codegenerator;

import ast.expressions.Expression;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class CodeGenerator {
    private PrintWriter printer;
    private StringBuilder codeBuffer;
    private final  String INTEGER_SUFFIX = "i";
    private final  String FLOAT_SUFFIX = "f";
    private final  String CHAR_SUFFIX = "b";
    private int labels = -1;

    public CodeGenerator(String filename) {
        try {
            printer = new PrintWriter(filename);
        } catch (FileNotFoundException e) {
            System.out.println("There was an error in the code generation file creation");
        }


    }

    public void push( int number){
        printer.println("\tpushi " + number);
    }

    public void push(double number){
        printer.println("\tpushf "+ number);
    }

    public void push(char letter){
        printer.println("\tpushb "+ (int)letter);
    }

    public void pushBP()
    {
        printer.println("\tpusha bp");
    }

    public void load(String suffix)
    {
        printer.println("\tload" + suffix);
    }

    public void not(){
        printer.println("\tnot");
    }

    public void convert(String firstSuffix, String secondSuffix){
        if(firstSuffix != secondSuffix) {
            if (firstSuffix == INTEGER_SUFFIX || secondSuffix == INTEGER_SUFFIX)
                printer.println("\t"+firstSuffix + "2" + secondSuffix);
            else {
                printer.println("\t" +firstSuffix + "2" + INTEGER_SUFFIX);
                convert(INTEGER_SUFFIX, secondSuffix);
            }
        }
    }

    public void logical(String operator){
        switch (operator){
            case "||": printer.println("\tor"); break;
            case "&&": printer.println("\tand");break;
        }
    }


    public void arithmetic(String operator, String suffix) {
        switch (operator){
            case "+": printer.println("\tadd" + suffix); break;
            case "-": printer.println("\tsub" + suffix); break;
            case "*": printer.println("\tmul" + suffix); break;
            case "/": printer.println("\tdiv" + suffix); break;
            case "%": printer.println("\tmod" + suffix); break;
        }
    }

    public void comparison(String operation, String suffix) {
        switch (operation){
            case "==":  printer.println("\teq"+ suffix); break;
            case "!=":  printer.println("\tne"+ suffix); break;
            case "<=":  printer.println("\tle"+ suffix); break;
            case "<":   printer.println("\tlt"+ suffix); break;
            case ">=":  printer.println("\tge"+ suffix); break;
            case ">":   printer.println("\tgt"+ suffix); break;
        }
    }

    public void call(String id) {
        printer.println("\tcall " + id );
    }

    public void halt() {
        printer.println("\thalt");
    }

    public void out(String suffix) {
        printer.println("\tout" + suffix);
    }

    public void in(String suffix) {
        printer.println("\tin" + suffix);
    }

    public void store(String suffix) {
        printer.println("\tstore" + suffix);
    }

    public void writeComment(String message) {
        printer.println("' *\t "+ message);

    }

    public void pusha(int address) {
        printer.println("\tpusha " + address);
    }

    public void id(String name) {
        printer.println(name + ":");
    }

    public void enter(int totalByteSize) {
        printer.println("\tenter " + totalByteSize);
    }

    public void closeFile(){
        printer.close();
    }

    public void addSource(String fileName) {
        printer.println("#source \"" + fileName + "\"");
    }
    public void addLine(int line) {
        printer.println("#line " + line);
    }

    public void addReturn(int returnSize, int localVariablesSize, int parameterSize )
    {
        printer.println("\tret "+ returnSize +", " + localVariablesSize +", " + parameterSize);
    }
    public int getLabel(){
        labels++;
        return labels;
    }

    public void jumpZ(int label){
        printer.println("\tjz label" + label);
    }
    public void jump(int label){
        printer.println("\tjmp label"+ label);
    }
    public void addLabel(int label){
        printer.println("label" +label + " :" );

    }

    public void pop(String suffix) {
        printer.println("\tpop" + suffix);
    }

    public void addEmptyLine() {
        printer.println();
    }
}
