import java.util.HashSet;
import java.util.Set;

import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.body.VariableDeclaratorId;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.visitor.VoidVisitorAdapter;

class ReplaceVarNamesVisitor extends VoidVisitorAdapter<Void> {

    ClassOrInterfaceDeclaration now_ClassOrInterfaceDeclaration;
    MethodDeclaration now_MethodDeclaration;
    boolean within = false;

    @Override
    public void visit(ClassOrInterfaceDeclaration n, Void v) {
        now_ClassOrInterfaceDeclaration = n;
        super.visit(n, v);
    }
    
    // local variables should use our naming convention
    Set<String> localVariableNames = new HashSet<String>();
    @Override
    public void visit(MethodDeclaration n, Void v) {
        now_MethodDeclaration = n;
        super.visit(n, v);
        localVariableNames.clear();
    }
    
    @Override
    public void visit(VariableDeclarator n, Void v) {
        within = true;
        super.visit(n, v);
        within = false;
    }

    @Override
    public void visit(VariableDeclaratorId n, Void v) {
        localVariableNames.add(n.getName()); // add original name of variable
        super.visit(n, v);
    }


    @Override
    public void visit(NameExpr n, Void v) {
        super.visit(n, v);
        /**
         * We were able to modify the AST in place as the change was made on 
         * the leaves and went unnoticed. Do it with care.
         * 
         * We check if we are within a method body and this name has appeared 
         * before in a local variable declaration. This is necessary to avoid
         * tranforming other names (e.g., names of classes in calls to static 
         * methods).
         */
        if (within && localVariableNames.contains(n.getName())) {
            n.setName(now_MethodDeclaration.getName() + "_" + n + "()");
        }
    }

}
