package kr.atlang.parser.ast;

import kr.atlang.token.Token;
import kr.atlang.token.TokenConst;

import java.util.ArrayList;
import java.util.List;

public class Int64DeclareVariableAST extends AST {

    @Override
    public void sort(List<Token> tokens) {
        int tokenSize = tokens.size();
        List<Token> expressionTokens = new ArrayList<>();
        ExpressionAST expressionAST = new ExpressionAST();

        for(int i = 2; i < tokenSize; i++) {
            expressionTokens.add(tokens.get(i));
        }

        expressionAST.sort(expressionTokens);
    }

    @Override
    public boolean isValidSyntax(List<Token> tokens) {
        return false;
    }

}
