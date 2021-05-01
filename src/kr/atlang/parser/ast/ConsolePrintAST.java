package kr.atlang.parser.ast;

import kr.atlang.token.Token;
import kr.atlang.token.TokenConst;
import kr.atlang.vm.IMiddleWareConvertor;
import kr.atlang.vm.MiddleWare;

import java.util.ArrayList;
import java.util.List;

public class ConsolePrintAST extends AST implements IMiddleWareConvertor {

    @Override
    public void sort(List<Token> tokens) {
        int size = tokens.size();
        ExpressionAST ast = new ExpressionAST();
        List<Token> expr = new ArrayList<>();

        for(int i = 1; i < size; i++) {
            expr.add(tokens.get(i));
        }

        ast.sort(expr);
        addTokensToList(ast.getAST());
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

        middleWare.addMiddle("conbuf"); //수식을 스택 최상단에 넣어놓고 뺴서 콘솔출력 버퍼에 넣는다.
        getAST().clear();
    }

}
