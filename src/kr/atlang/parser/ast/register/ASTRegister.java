package kr.atlang.parser.ast.register;

import kr.atlang.lexer.SyntaxConst;
import kr.atlang.parser.Label;
import kr.atlang.parser.ast.*;

import java.util.HashMap;
import java.util.Map;

public class ASTRegister {

    private static ASTRegister registerInstance;

    final private static String CMP = "cmp";
    final private static String CONSOLE = "console";
    final private static String EXPRESSION = "expression";
    final private static String IF = "if";
    final private static String DECL_VAR = "declInt64";
    final private static String SCOPE_FIN = "rBracket";

    private Map<String, AST> astRegister;

    private ASTRegister() {
        astRegister = new HashMap<>();
        Label label = new Label();

        astRegister.put(CMP, new CmpAST());
        astRegister.put(CONSOLE, new ConsolePrintAST());
        astRegister.put(EXPRESSION, new ExpressionAST());
        astRegister.put(IF, new IfAST(label));
        astRegister.put(DECL_VAR, new Int64DeclareVariableAST());
        astRegister.put(SCOPE_FIN, new ScopeFinishAST(label));
    }
    
    public static ASTRegister getASTRegister() {
        if(registerInstance == null) {
            registerInstance = new ASTRegister();
        }

        return registerInstance;
    }

    public AST getAST(String name) { return astRegister.get(name); }

    public AST getAST(SyntaxConst syntax) {
        switch (syntax) {
            case DECLARE_VARIABLE:
                return getAST(DECL_VAR);
            case PRINT:
                return getAST(CONSOLE);
            case COMPARE:
                return getAST(IF);
            case R_BRACKET:
                return getAST(SCOPE_FIN);

        }

        return null;
    }

}
