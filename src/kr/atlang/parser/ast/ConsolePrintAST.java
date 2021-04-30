package kr.atlang.parser.ast;

import kr.atlang.token.Token;

import java.util.ArrayList;
import java.util.List;

public class ConsolePrintAST extends AST {

    @Override
    public void sort(List<Token> tokens) {
        addTokenToStack(tokens.get(1));
        toMiddleWare();
    }

    public void toMiddleWare() {
        List<String> middleWare = new ArrayList<>();

        middleWare.add("conbuf " + getAST().get(0));
        System.out.println(middleWare);
    }

    @Override
    public boolean isValidSyntax(List<Token> tokens) {
        return false;
    }
}
