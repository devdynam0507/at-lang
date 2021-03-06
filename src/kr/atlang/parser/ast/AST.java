package kr.atlang.parser.ast;

import kr.atlang.lexer.SyntaxConst;
import kr.atlang.token.Token;

import java.util.ArrayList;
import java.util.List;

public abstract class AST {

    private SyntaxConst syntaxType;
    private List<Token> ast;

    public AST() {
        this.ast = new ArrayList<>();
    }

    protected void addTokenToStack(Token token) {
        ast.add(token);
    }
    protected void addTokensToList(List<Token> tokens) {
        ast.addAll(tokens);
    }

    public List<Token> getAST() { return ast; }

    public abstract void sort(List<Token> tokens);

}
