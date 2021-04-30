package kr.atlang.parser.ast;

import kr.atlang.lexer.SyntaxConst;
import kr.atlang.token.Token;

import java.util.List;
import java.util.Stack;

public abstract class AST {

    private SyntaxConst syntaxType;
    private Stack<Token> ast;

    protected void addTokenToStack(Token token) {
        ast.push(token);
    }

    public Stack<Token> getAST() { return ast; }

    public abstract void sort(List<Token> tokens);
    public abstract boolean isValidSyntax(List<Token> tokens);

}
