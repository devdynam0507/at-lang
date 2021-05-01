package kr.atlang.parser.ast;

import kr.atlang.token.Token;

import java.util.List;

public class CmpAST extends AST {

    @Override
    public void sort(List<Token> tokens) {
        addTokenToStack(tokens.get(0));
        addTokenToStack(tokens.get(1));
        addTokenToStack(tokens.get(2));
    }

}
