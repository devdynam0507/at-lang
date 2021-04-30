package kr.atlang.parser.ast;

import kr.atlang.token.Token;
import kr.atlang.token.TokenConst;
import kr.atlang.vm.MiddleWare;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
        System.out.println(expressionAST.getAST());
        toMiddleWare(tokens.get(0), expressionAST);
    }

    public void toMiddleWare(Token identifier, ExpressionAST expressionAST) {
        List<Token> ast = expressionAST.getAST();
        List<String> testMiddleWare = new ArrayList<>();

        for(Token t : ast) {
            int id = t.getTokenId();

            if(id == TokenConst.INT || id == TokenConst.IDENTIFIER) {
                testMiddleWare.add("push " + t.getToken());
            } else {
                testMiddleWare.add(MiddleWare.getOperatorToMiddleWare(t));
            }
        }

        testMiddleWare.add("alloc " + identifier.toString());
        testMiddleWare.forEach(i -> System.out.println(i));
    }

    @Override
    public boolean isValidSyntax(List<Token> tokens) {
        return false;
    }

}
