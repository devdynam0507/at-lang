package kr.atlang.parser.ast;

import kr.atlang.token.Token;

import java.util.List;

public class RBracketAST extends AST {

    private int label;

    public RBracketAST(int label) {
        this.label = label;
    }

    @Override
    public void sort(List<Token> tokens) {

    }

    @Override
    public boolean isValidSyntax(List<Token> tokens) {
        return false;
    }
}
