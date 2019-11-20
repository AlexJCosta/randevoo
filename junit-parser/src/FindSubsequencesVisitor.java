import java.util.HashMap;
import java.util.List;
import java.util.Map;

import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.expr.ArrayAccessExpr;
import japa.parser.ast.expr.AssignExpr;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.FieldAccessExpr;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.VariableDeclarationExpr;
import japa.parser.ast.stmt.AssertStmt;
import japa.parser.ast.stmt.ExpressionStmt;
import japa.parser.ast.stmt.ReturnStmt;
import japa.parser.ast.stmt.Statement;
import japa.parser.ast.stmt.TryStmt;
import japa.parser.ast.visitor.VoidVisitorAdapter;

class FindSubsequencesVisitor extends VoidVisitorAdapter<Void> {
	
    public StringBuffer seqs = new StringBuffer();

    ClassOrInterfaceDeclaration now_ClassOrInterfaceDeclaration;
    MethodDeclaration now_MethodDeclaration;
    boolean within = false;
    
    //added boy allex
	public List<ImportDeclaration> imports;
    
    @Override
    public void visit(ClassOrInterfaceDeclaration n, Void v) {
        now_ClassOrInterfaceDeclaration = n;         
        super.visit(n, v);
    }
    
    static String TEMPLATE = "public static %s %s_%s() throws Throwable {\n %s %s \n}\n";
    @Override
    public void visit(MethodDeclaration n, Void v) {
        now_MethodDeclaration = n;
        super.visit(n, v);
        if (n.getBody()==null || n.getBody().getStmts()==null) return;
        int counter = 0; // reset                      
        StringBuffer prefix = new StringBuffer();
        Map<String, String> varTypes = new HashMap<String,String>();
        
       // System.out.println(n.getBody().getStmts());
        
        for (Statement stmt : n.getBody().getStmts()) {
            if (stmt instanceof ExpressionStmt) {
                Expression exp = ((ExpressionStmt) stmt).getExpression();
                
         //       System.out.println(exp);
                
                if (exp instanceof MethodCallExpr || exp instanceof AssignExpr) { // a.m();
                    String name;
                    if (exp instanceof MethodCallExpr) {
                        MethodCallExpr mce = (MethodCallExpr) exp;
                        if (mce.getScope() == null) {
                            if (mce.getName().startsWith("assert")) continue;
                            else throw new RuntimeException("Please, check this...");
                        }
                        name = mce.getScope().toString();
                    } else {
                        AssignExpr ase = (AssignExpr) exp;
                        Expression e = ase.getTarget();
                        if (e instanceof FieldAccessExpr) {
                        	FieldAccessExpr fae = (FieldAccessExpr) e;
                        	name = fae.getScope().toString();
                        }else if (e instanceof ArrayAccessExpr) {
                        	ArrayAccessExpr fae = (ArrayAccessExpr) e;
                        	name = fae.getName().toString();
                        }else {
                        	throw new RuntimeException("Please, check this...");
                        }
                    }
                    String type = varTypes.get(name);
                    if (type == null) {
                        /***** Unusual case. Probably a chained method call. Trying to rescue...HACK!!!! ******/
                        for (String var: varTypes.keySet()) {
                            if (name.contains(var)) {
                                name = var;
                                type = varTypes.get(name);
                                break;
                            }
                        }
                        if (type == null) {
                        	//Static void call
                        	//TODO: check if omitting the exception does not cause other kinds of problems
                        	type = "void";
                            //System.out.println("Type void: " + name);
                            //System.out.println(exp);
                            //System.out.println(prefix);
                            //throw new RuntimeException("Please, check this...");
                        }
                    }
                    String closing = exp.toString() + "; ";
                    if (!type.equals("void")) {
                    	closing = "\n return " + name + ";";
                    }
                    System.out.println(n.getType().toString());
                    
                    //added by alex
                    String canonicalImport = "void";
                    for (ImportDeclaration i : imports) {
                    	System.out.println("now-> " + type);
                    	System.out.println(i.toString());
                    	System.out.println(i.getName().getName());
                    	if (type.equals(i.getName().getName())) {
                    		canonicalImport = i.getName().toString();
                    	}
                    }
                    
                    String s = "";
                    if (canonicalImport.equals("void")) {
                    	s = String.format(TEMPLATE, type, n.getName(), counter, prefix.toString(), closing);
                    }else {
                    	s = String.format(TEMPLATE, canonicalImport, n.getName(), counter, prefix.toString(), closing);
                    }
                    
                    seqs.append(s);
                    prefix.append(exp.toString()+ ";\n");
                    counter++;
                } else if (exp instanceof VariableDeclarationExpr) { // T v = e;
                    VariableDeclarationExpr vde = (VariableDeclarationExpr) exp;
                    if (vde.getVars().size() != 1) throw new RuntimeException("Please, check this...");
                    VariableDeclarator vd = (VariableDeclarator) vde.getVars().get(0);
                    varTypes.put(vd.getId().toString(), vde.getType().toString());
                    
                    // sequences
                    //added by alex
                    String canonicalImport = vde.getType().toString();
                    boolean key = false;
                    for (ImportDeclaration i : imports) {
                    	System.out.println("now-> " + vde.getType().toString());
                    	System.out.println(i.toString());
                    	System.out.println(i.getName().getName());
                    	if (vde.getType().toString().equals(i.getName().getName())) {
                    		canonicalImport = i.getName().toString();
                    		key = true;
                    	}
                    }
                    
                    System.out.println(n);
                    // added by alex
                    //if (key) {
	                    String s = String.format(TEMPLATE, canonicalImport, n.getName(), counter, prefix.toString(), "return " + vd.getInit() + ";");
	                    //String s = String.format(TEMPLATE, vde.getType().toString(), n.getName(), counter, prefix.toString(), "return " + vd.getInit() + ";");
	                    System.out.println(s);
	                    seqs.append(s);
	                    prefix.append(exp.toString() + ";\n");
	                    counter++;
                    //}
                } else {
                    //System.out.println(exp.getClass());
                    throw new RuntimeException("Please, check this...");                    
                }
            } else if (stmt instanceof AssertStmt) {
                //TODO: doing nothing; check if it would help
            } else if (stmt instanceof TryStmt) {
                //TODO: doing nothing; check if it would help
            } else if (stmt instanceof ReturnStmt) {
                //TODO: doing nothing; check if it would help
            } else {
                System.out.println(stmt);
                System.out.println(stmt.getClass());
                throw new RuntimeException("Please, check this...");                
            }
        }
    }
    
}
