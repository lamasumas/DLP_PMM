import parser.*;

import org.antlr.v4.runtime.*;

import ast.Program;
import errorhandler.ErrorHandler;
import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorTree;
import visitor.codegenerator.*;
import visitor.semantics.IdentificationVisitor;
import visitor.semantics.TypeCheckingVisitor;

public class Main {
	
	public static void main(String... args) throws Exception {
		   if (args.length<1) {
		        System.err.println("Please, pass me the input file.");
		        return;
		    }
		   		 			
		 // create a lexer that feeds off of input CharStream
		CharStream input = CharStreams.fromFileName(args[0]);
		PmmLexer lexer = new PmmLexer(input);

		// create a parser that feeds off the tokens buffer
		CommonTokenStream tokens = new CommonTokenStream(lexer); 
		PmmParser parser = new PmmParser(tokens);	
		Program ast = parser.program().ast;

		//Semantic visitors
		ast.accept(new IdentificationVisitor(), null);

		if(ErrorHandler.getInstance().anyError()){
			// * Show errors
			System.err.println("---- ERRORS DURING IDENTIFICATION PHASE ---- \n");
			ErrorHandler.getInstance().showErrors(System.err);
			ErrorHandler.getInstance().cleanError();
		}
		ast.accept(new TypeCheckingVisitor(), null);


		if(ErrorHandler.getInstance().anyError()){
			// * Show errors
			System.err.println("---- ERRORS DURING TYPECHECKING PHASE ---- \n");
			ErrorHandler.getInstance().showErrors(System.err);
			ErrorHandler.getInstance().cleanError();

		}else {
			//Code generation visitors
			ast.accept(new OffsetVisitor(), null);
			ExecuteCGVisitor executeCGVisitor = new ExecuteCGVisitor();
			AddressCGVisitor addressCGVisitor = new AddressCGVisitor();
			ValueCGVisitor valueCGVisitor = new ValueCGVisitor();
			CodeGenerator cg = new CodeGenerator("GeneratedCode.txt");
			//Set all the references
			executeCGVisitor.setAddressCGVisitor(addressCGVisitor);
			executeCGVisitor.setCodeGenerator(cg);
			executeCGVisitor.setValueCGVisitor(valueCGVisitor);

			addressCGVisitor.setCodeGenerator(cg);
			addressCGVisitor.setValueCGVisitor(valueCGVisitor);

			valueCGVisitor.setAddressCGVisitor(addressCGVisitor);
			valueCGVisitor.setCodeGenerator(cg);
			valueCGVisitor.setExecuteCGVisitor(executeCGVisitor);

			cg.addSource(input.getSourceName());
			ast.accept(executeCGVisitor, null);

			cg.closeFile();
			// * Check errors
		}

		if(ErrorHandler.getInstance().anyError()){
			// * Show errors
			System.err.println("----- ERRORS DURING CODE GENERATION PHASE ----- \n");
			ErrorHandler.getInstance().showErrors(System.err);
		}
		else{			
			// * The AST is shown
			IntrospectorModel model=new IntrospectorModel("Program", ast);
			//new IntrospectorTree("Introspector", model);
		}		
	}
}
