package kr.atlang.parser.ast.register;

public class ASTRegister {

    private static ASTRegister registerInstance;

    private ASTRegister() { }
    
    public static ASTRegister getASTRegister() {
        if(registerInstance == null) {
            registerInstance = new ASTRegister();
        }

        return registerInstance;
    }

}
