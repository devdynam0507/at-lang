package kr.atlang.parser.ast;

import kr.atlang.token.Token;
import kr.atlang.token.TokenConst;
import kr.atlang.vm.IMiddleWareConvertor;
import kr.atlang.vm.MiddleWare;

import java.util.ArrayList;
import java.util.List;

public class Int64DeclareVariableAST extends AST implements IMiddleWareConvertor {

    private String identifier;

    @Override
    public void sort(List<Token> tokens) {
        int tokenSize = tokens.size();
        this.identifier = tokens.get(0).toString();
        List<Token> expressionTokens = new ArrayList<>();
        ExpressionAST expressionAST = new ExpressionAST();

        for(int i = 2; i < tokenSize; i++) {
            expressionTokens.add(tokens.get(i));
        }

        expressionAST.sort(expressionTokens);
        addTokensToList(expressionAST.getAST());
    }

    @Override
    public void toMiddleware(MiddleWare middleWare) {
        List<Token> ast = getAST();

        for(Token t : ast) {
            int id = t.getTokenId();

            if(id == TokenConst.INT || id == TokenConst.IDENTIFIER) {
                middleWare.addMiddle("push " + t.getToken());
            } else {
                middleWare.addMiddle(MiddleWare.getOperatorToMiddleWare(t));
            }
        }

        middleWare.addMiddle("alloc " + identifier);
        getAST().clear();
    }

}
