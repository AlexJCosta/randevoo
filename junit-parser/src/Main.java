import japa.parser.ASTHelper;
import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.PackageDeclaration;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.ModifierSet;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.ReturnStmt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.Tool;
import javax.tools.ToolProvider;

public class Main {
	private static String path = "";
	static int count = 0;

    public static void main(String[] args) throws Exception {
    	path = args[2];
/*    	path = "/home/alex/Downloads/SF110-20130704/70_echodep/echodep.jar:" +
    			"/home/alex/Downloads/SF110-20130704/70_echodep/lib/mets.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/lib/jsr173_1.0_api.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/lib/heritrix-1.12.0.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/lib/xercesImpl-2.8.1.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/lib/xom-1.1.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/lib/sword-common-1.1.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/lib/jhove-handler.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/lib/saxon8-jdom.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/lib/handsutils.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/lib/commons-httpclient-3.0.1.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/lib/saxon8-dom.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/lib/xmlpublic.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/lib/HaSMETSProfileXmlBeans.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/lib/saxon8-dom4j.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/lib/saxon8-ant.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/lib/xbean.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/lib/log4j-1.2.14.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/lib/jhove.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/lib/jdom.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/libsaxon8-xqj.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/lib/jhove-module.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/liblrcrud-client.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/lib/saxon8-sql.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/libsaxon8.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/lib/commons-cli-1.0.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/lib/saxon8-xom.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/lib/saxon8-xpath.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/lib/commons-codec-1.3.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/libcommons-logging-1.1.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/libresolver.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/libxbean_xpath.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/libberkeleydb-1.5.1.jar:" + 
    			"/home/alex/Downloads/SF110-20130704/70_echodep/libjug-lgpl-2.0.0.jar:" + 
    			""; */

    	//String[] a = {"","/home/alex/Music/randevoo/junit-parser/resources/sample.txt",	path};args = a;
    	
        /** input validation **/
        if (args.length != 3) { // when this is called from jar (as in java -jar), the main class will be the first parameter
            System.out.println("Please, provide as parameter file name with paths of tests files to process. Format: Main <file-name>");
            System.exit(-1);
        }
        String fileWithTestFilesToProcess = args[1];
        File file = new File(fileWithTestFilesToProcess);
        if (!file.exists()) {
            System.out.println("Could not find file name provide. Please check" + fileWithTestFilesToProcess);
            System.exit(-1);
        }

        /** process each file and store resulting compilation unit **/
        List<CompilationUnit> units = new ArrayList<CompilationUnit>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        while ((s = br.readLine())!=null) {
//            s = s.trim();
//            if (s.equals("")) continue;
//            CompilationUnit compUnit = refactorEvoSuiteTest(s);
//            units.add(compUnit);
//            saveCompilationUnit(compUnit);
        	
            s = s.trim();
            if (s.equals("")) continue;
            CompilationUnit compUnit = refactorEvoSuiteTest(s);
            saveCompilationUnit(compUnit);
            units.add(compUnit);
        }
        br.close();

        /** created wrapper class containing factory methods **/
        createWrapperClass(units);

    }

    private static void createWrapperClass(List<CompilationUnit> units) throws IOException {
        /** merge each compilation unit and produce a single file **/
        // TO CHECK: I am assuming that merging tests from different classes will not
        // result in collision problems

        // create new compilation unit and one class/type declaration
        CompilationUnit compUnit = new CompilationUnit();
        compUnit.setPackage(new PackageDeclaration(ASTHelper.createNameExpr("fromevosuite")));
        ClassOrInterfaceDeclaration type = new ClassOrInterfaceDeclaration(ModifierSet.PUBLIC, false, "ToRandoop");
        type.setMembers(new ArrayList<BodyDeclaration>());
        ASTHelper.addTypeDeclaration(compUnit, type);

        /**
         * traverse all tests looking for distinct import declarations and methods.
         * 
         * We can't create a single test with all EvoSuite tests because some tests
         * access protected methods and constructors, which are only accessible from
         * classes defined in specific packages. Consequently, we need to respect the
         * same package structure.
         **/
        int counter = 0;
        Set<ImportDeclaration> importSet = new HashSet<ImportDeclaration>();
        for (CompilationUnit unit : units) {
        	
//        	System.out.println(unit.getImports().get(2).toString());
//        	System.out.println(unit.getTypes().get(0));
        	
        	//compilation temp
        	CompilationUnit compUnittemp = new CompilationUnit();
            ClassOrInterfaceDeclaration typeTemp = new ClassOrInterfaceDeclaration(ModifierSet.PUBLIC, false, "ToRandoopTemp");
            typeTemp.setMembers(new ArrayList<BodyDeclaration>());
            ASTHelper.addTypeDeclaration(compUnittemp, typeTemp);
            compUnittemp.setImports(unit.getImports());
            
            String fname = compUnittemp.getTypes().get(0).getName();
        	BufferedWriter bw = new BufferedWriter(new FileWriter(fname+".java"));
        	bw.write(compUnittemp.toString());
            bw.close();
            
        	
            //System.out.println("Comliling: " + compUnittemp.getTypes().get(counter).getName()+".java");
        	JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

            int result = compiler.run(null, null, null, "-cp", 
            		path
            		+ ":/home/alex/Music/randevoo/libs/junit/junit-4.13-beta-2.jar:"
            		+ "/home/alex/Music/randevoo/libs/evosuite/evosuite-1.0.6.jar", compUnittemp.getTypes().get(0).getName()+".java");
            System.out.println(result);
            
            int resultUnit = compiler.run(null, null, null, "-cp", 
            		path
            		+ ":/home/alex/Music/randevoo/libs/junit/junit-4.13-beta-2.jar:"
            		+ "/home/alex/Music/randevoo/libs/evosuite/evosuite-1.0.6.jar", unit.getTypes().get(0).getName()+".java");
            
        	
            if (result == 0 && resultUnit == 0 && unit.getPackage() != null) {
	            List<TypeDeclaration> typeDeclarations = unit.getTypes();
	            if (typeDeclarations.size() != 1) {
	                throw new RuntimeException("Please check. We assume there was only one type in each test class.");
	            }
	            ClassOrInterfaceDeclaration clazz = (ClassOrInterfaceDeclaration) typeDeclarations.get(0);
	            
	            for ( ImportDeclaration i :unit.getImports()) {
	            	boolean key = true;
	            	System.out.println(i.getName().getName());
	            	for (ImportDeclaration imp : importSet) {
	            		if(imp.getName().getName().equals(i.getName().getName())) {
	            			key = false;
	            		}
	            	}
	            	if (key) {
	            		importSet.add(i);
	            		key = true;
	            	}
	            }
	            
	            // add import
	            //importSet.addAll(unit.getImports());
	            // import this type
	            if (unit.getPackage() != null) {
	            	String name = unit.getPackage().getName() + "." + clazz.getName();
	            	importSet.add(new ImportDeclaration(new NameExpr(name), false, false));
	            }
	            
	
	            for (BodyDeclaration bd : clazz.getMembers()) {
	                MethodDeclaration md = (MethodDeclaration) bd;
	                // creating new method declaration
	                //System.out.println(md.getType().toString());
	                
	                MethodDeclaration newMD = new MethodDeclaration(ModifierSet.PUBLIC, md.getType(), "test" + (++counter));
	                newMD.setThrows(new ArrayList<NameExpr>());
	                newMD.getThrows().add(new NameExpr("Throwable"));
	                BlockStmt body = new BlockStmt();
	                newMD.setBody(body);
	                MethodCallExpr call = new MethodCallExpr(new NameExpr(clazz.getName()), md.getName());
	                if (md.getType().toString().equals("void")) {
	                	ASTHelper.addStmt(body, call);
	                }else {
	                	ASTHelper.addStmt(body, new ReturnStmt(call));
	                }
	                // attaching the new method to the newly created compilation unit
	                type.getMembers().add(newMD);
	            }
	            compUnit.setImports(new ArrayList<ImportDeclaration>(importSet));
	            
	        }else {
	        	(new File(unit.getTypes().get(0).getName()+".java")).delete();
	            (new File(unit.getTypes().get(0).getName()+".class")).delete();
	        }
            
            saveCompilationUnit(compUnit);
            
            (new File("ToRandoopTemp.java")).delete();
            (new File("ToRandoopTemp.class")).delete();
            
        }
    }

    private static void saveCompilationUnit(CompilationUnit compUnit) throws IOException {
    	// save file
    	String fname = compUnit.getTypes().get(0).getName();
    	BufferedWriter bw = null;
    	
    	// save file
    	if (!fname.equals("ToRandoop")) {
	    	
	    	if (!(new File(fname+".java")).exists()) {
	    		bw = new BufferedWriter(new FileWriter(fname+".java"));
	    	}else {
	    		compUnit.getTypes().get(0).setName(fname+"_"+count);
	    		bw = new BufferedWriter(new FileWriter(fname+"_"+count+".java"));
	    		count++;
	    	}
    	}else {
        	bw = new BufferedWriter(new FileWriter(fname+".java"));
    	}
    	
    	bw.write(compUnit.toString());
    	bw.close();
    	
//    	// save file
//    	String fname = compUnit.getTypes().get(0).getName();
//    	BufferedWriter bw = new BufferedWriter(new FileWriter(fname+".java"));
//    	bw.write(compUnit.toString());
//      bw.close();
        
    }

    private static CompilationUnit refactorEvoSuiteTest(String fileName) throws FileNotFoundException, ParseException, IOException {
        FileInputStream in = new FileInputStream(fileName);
        CompilationUnit compUnit = JavaParser.parse(in);
        in.close();

        /** find subsequences */
        FindSubsequencesVisitor findSubsequencesVisitor = new FindSubsequencesVisitor();
        // added by alex
        findSubsequencesVisitor.imports = compUnit.getImports();
        
        compUnit.accept(findSubsequencesVisitor, null);
        // visitor produces a string with factory methods
        StringBuffer sb = findSubsequencesVisitor.seqs;
        String temp = String.format("class Foo { %s }", sb.toString());
        Reader reader = new StringReader(temp);
        CompilationUnit fooCompUnit = JavaParser.parse(reader, false);
        reader.close();
        // hack -> replace original list methods with new ones  
        ClassOrInterfaceDeclaration clazz = (ClassOrInterfaceDeclaration) compUnit.getTypes().get(0);
        clazz.getMembers().clear();
        clazz.getMembers().addAll(((ClassOrInterfaceDeclaration) fooCompUnit.getTypes().get(0)).getMembers());
        
        /** replace variable names */
//        ReplaceVarNamesVisitor replaceVarNamesVisitor = new ReplaceVarNamesVisitor();
//        compUnit.accept(replaceVarNamesVisitor, null);

        /** factor subsequences AST **/
//        FactorSubsequencesVisitor factorVisitor = new FactorSubsequencesVisitor();
//        compUnit.accept(factorVisitor, null);
//        // delayed modifications on AST
//        factorVisitor.deleteNodes();
//        factorVisitor.addNodes();
        return compUnit;
    }

}
