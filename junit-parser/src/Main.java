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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws Exception {
        /** input validation **/
        if (args.length != 2) { // when this is called from jar (as in java -jar), the main class will be the first parameter
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
            s = s.trim();
            if (s.equals("")) continue;
            CompilationUnit compUnit = refactorEvoSuiteTest(s);
            units.add(compUnit);
            saveCompilationUnit(compUnit);
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
            List<TypeDeclaration> typeDeclarations = unit.getTypes();
            if (typeDeclarations.size() != 1) {
                throw new RuntimeException("Please check. We assume there was only one type in each test class.");
            }
            ClassOrInterfaceDeclaration clazz = (ClassOrInterfaceDeclaration) typeDeclarations.get(0);

            // add import
            importSet.addAll(unit.getImports());
            // import this type
            String name = unit.getPackage().getName() + "." + clazz.getName();
            importSet.add(new ImportDeclaration(new NameExpr(name), false, false));

            for (BodyDeclaration bd : clazz.getMembers()) {
                MethodDeclaration md = (MethodDeclaration) bd;
                // creating new method declaration
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
        }
        compUnit.setImports(new ArrayList<ImportDeclaration>(importSet));

        saveCompilationUnit(compUnit);
    }

    private static void saveCompilationUnit(CompilationUnit compUnit) throws IOException {
        // save file
        String fname = compUnit.getTypes().get(0).getName();
        BufferedWriter bw = new BufferedWriter(new FileWriter(fname+".java"));
        bw.write(compUnit.toString());
        bw.close();
    }

    private static CompilationUnit refactorEvoSuiteTest(String fileName) throws FileNotFoundException, ParseException, IOException {
        FileInputStream in = new FileInputStream(fileName);
        CompilationUnit compUnit = JavaParser.parse(in);
        in.close();

        /** find subsequences */
        FindSubsequencesVisitor findSubsequencesVisitor = new FindSubsequencesVisitor();
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
