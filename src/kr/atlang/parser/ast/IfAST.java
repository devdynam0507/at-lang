package kr.atlang.parser.ast;

import kr.atlang.lexer.AtLexer;
import kr.atlang.parser.Label;
import kr.atlang.token.Token;
import kr.atlang.token.TokenConst;
import kr.atlang.vm.MiddleWare;

import java.util.ArrayList;
import java.util.List;

public class IfAST extends AST {

    private Label label;

    public IfAST(Label label) {
        this.label = label;
    }

    @Override
    public void sort(List<Token> tokens) {
        label.newPoint();
        CmpAST cmpAST = new CmpAST();
        cmpAST.sort(tokens);

        toMiddleWare(cmpAST);
    }

    public void toMiddleWare(CmpAST cmpAST) {
        List<Token> ast = cmpAST.getAST();
        List<String> testMiddleWare = new ArrayList<>();

        Token oper1 = ast.get(0);
        Token cmp = ast.get(1);
        Token oper2 = ast.get(2);

        testMiddleWare.add("push " + oper1.getToken());
        testMiddleWare.add("push " + oper2.getToken());
        testMiddleWare.add(MiddleWare.getCmpOperatorToMiddleWare(cmp));
        testMiddleWare.add(MiddleWare.getJumpCmd(MiddleWare.getCmpOperatorToMiddleWare(cmp)) + " " + label.dequeue());

        System.out.println(testMiddleWare);
    }

    @Override
    public boolean isValidSyntax(List<Token> tokens) {
        return false;
    }
}
