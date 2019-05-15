import japa.parser.ASTHelper;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.ModifierSet;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.expr.VariableDeclarationExpr;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.ReturnStmt;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;


class FactorSubsequencesVisitor extends VoidVisitorAdapter<Void> {
    ClassOrInterfaceDeclaration now_ClassOrInterfaceDeclaration;
    MethodDeclaration now_MethodDeclaration;

    @Override
    public void visit(ClassOrInterfaceDeclaration n, Void v) {
        now_ClassOrInterfaceDeclaration = n;
        super.visit(n, v);
    }
    
    @Override
    public void visit(MethodDeclaration n, Void v) {
        now_MethodDeclaration = n;
        super.visit(n, v);
        /**
         * Remove node, but don't do it right now as we are still 
         * traversing the tree!
         **/            
        delayedDelete(now_ClassOrInterfaceDeclaration, n);
    }
    @Override
    public void visit(VariableDeclarationExpr n, Void v) {
        n.getType();
        super.visit(n, v);
        /**
         * Add node to the AST corresponding to this variable declaration, 
         * but don't add right now as we are still traversing the tree!
         **/
        delayedAdd(now_ClassOrInterfaceDeclaration, now_MethodDeclaration, n);
    }

    /**********************************************************
     * delayed modifications! cannot modify AST while visiting. 
     **********************************************************/
    List<ClassOrInterfaceDeclaration> deleteArg0 = new ArrayList<ClassOrInterfaceDeclaration>();
    List<MethodDeclaration> deleteArg1 = new ArrayList<MethodDeclaration>();
    private void delayedDelete(ClassOrInterfaceDeclaration arg1, MethodDeclaration arg2) {
        deleteArg0.add(arg1);
        deleteArg1.add(arg2);
    }
    public void deleteNodes() {
        for (int i = 0; i < deleteArg0.size(); i++)
            deleteArg0.get(i).getMembers().remove(deleteArg1.get(i));
    }
    // additions
    List<ClassOrInterfaceDeclaration> addArg0 = new ArrayList<ClassOrInterfaceDeclaration>();
    List<MethodDeclaration> addArg1 = new ArrayList<MethodDeclaration>();
    List<VariableDeclarationExpr> addArg2 = new ArrayList<VariableDeclarationExpr>();
    private void delayedAdd(ClassOrInterfaceDeclaration arg0, MethodDeclaration arg1, VariableDeclarationExpr arg2) {
        addArg0.add(arg0);
        addArg1.add(arg1);
        addArg2.add(arg2);
    }
    public void addNodes() {
        for (int i = 0; i < addArg0.size(); i++) {
            VariableDeclarationExpr n = addArg2.get(i);
            // create new node
            int mods = ModifierSet.addModifier(ModifierSet.PUBLIC, ModifierSet.STATIC);
            List<VariableDeclarator> list = n.getVars();
            if (list.size() != 1) throw new RuntimeException("unexpected! generalized this");
            // name of method = <name of original method>_<name of variable>
            VariableDeclarator vd = list.get(0);
            String name = addArg1.get(i).getName() + "_" + vd.getId().getName();
            MethodDeclaration method = new MethodDeclaration(mods, n.getType(), name);
            BlockStmt block = new BlockStmt();
            method.setBody(block);
            ASTHelper.addStmt(block, new ReturnStmt(vd.getInit()));
            ASTHelper.addMember(addArg0.get(i), method);
        }                
    }

}