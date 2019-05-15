import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.VariableDeclarator;
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
    
    @Override
    public void visit(MethodDeclaration n, Void v) {
        now_MethodDeclaration = n;
        super.visit(n, v);
    }

    @Override
    public void visit(VariableDeclarator n, Void v) {
        within = true;
        super.visit(n, v);
        within = false;
    }

    @Override
    public void visit(NameExpr n, Void v) {
        super.visit(n, v);
        /**
         * We were able to modify the AST in place as the change was made on 
         * the leaves and went unnoticed. Do it with care.
         */
        if (within) {
            n.setName(now_MethodDeclaration.getName()+"_"+n+"()");
        }
    }

}