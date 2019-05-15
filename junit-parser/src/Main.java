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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
            CompilationUnit compUnit = refactorEvoSuiteTest(s.trim());
            units.add(compUnit);
        }
        br.close();

        /** merge each compilation unit and produce single file **/
        //TO CHECK: I am assuming that merging tests from different classes will not result in collision problems

        // create new compilation unit and one class/type declaration                                                                                                                                                                                                                                                                                        
        CompilationUnit compUnit = new CompilationUnit();
        compUnit.setPackage(new PackageDeclaration(ASTHelper.createNameExpr("fromevosuite")));
        ClassOrInterfaceDeclaration type = new ClassOrInterfaceDeclaration(ModifierSet.PUBLIC, false, "ToRandoop");
        type.setMembers(new ArrayList<BodyDeclaration>());
        ASTHelper.addTypeDeclaration(compUnit, type);

        // traverse all tests looking for distinct import declarations and methods
        Set<ImportDeclaration> set = new HashSet<ImportDeclaration>();
        for (CompilationUnit unit: units) {
            set.addAll(unit.getImports());
            List<TypeDeclaration> typeDeclarations = unit.getTypes();
            if (typeDeclarations.size() != 1) {
                throw new RuntimeException("Please check. We assume there was only one type in each test class.");
            }
            type.getMembers().addAll(typeDeclarations.get(0).getMembers());            
        }
        compUnit.setImports(new ArrayList<ImportDeclaration>(set));

        System.out.println(compUnit);

    }

    private static CompilationUnit refactorEvoSuiteTest(String fileName) throws FileNotFoundException, ParseException, IOException {
        FileInputStream in = new FileInputStream(fileName);
        CompilationUnit compUnit = JavaParser.parse(in);
        in.close();

        /** replace variable names */
        ReplaceVarNamesVisitor replaceVarNamesVisitor = new ReplaceVarNamesVisitor();
        compUnit.accept(replaceVarNamesVisitor, null);

        /** factor subsequences AST **/
        FactorSubsequencesVisitor factorVisitor = new FactorSubsequencesVisitor();
        compUnit.accept(factorVisitor, null);
        // delayed modifications on AST
        factorVisitor.deleteNodes();
        factorVisitor.addNodes();
        return compUnit;
    }

}