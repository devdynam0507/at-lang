package kr.atlang.parser.ast;

import kr.atlang.lexer.AtLexer;
import kr.atlang.parser.Label;
import kr.atlang.parser.ast.register.ASTRegister;
import kr.atlang.token.Token;
import kr.atlang.vm.IMiddleWareConvertor;
import kr.atlang.vm.MiddleWare;

import java.util.ArrayList;
import java.util.List;

public class IfAST extends AST implements IMiddleWareConvertor {

    private Label label;

    public IfAST(Label label) {
        this.label = label;
    }

    @Override
    public void sort(List<Token> tokens) {
        label.newPoint();
        AST cmpAST = ASTRegister.getASTRegister().getAST("cmp");
        cmpAST.sort(tokens);

        addTokensToList(cmpAST.getAST());
        cmpAST.getAST().clear();
    }

    @Override
    public void toMiddleware(MiddleWare middleWare) {
        List<Token> ast = getAST();

        Token oper1 = ast.get(0);
        Token cmp = ast.get(1);
        Token oper2 = ast.get(2);
        String cmd = MiddleWare.getCmpOperatorToMiddleWare(cmp);
        final int labelNum = label.dequeue();

        middleWare.addMiddle("push " + oper1.getToken());
        middleWare.addMiddle("push " + oper2.getToken());
        middleWare.addMiddle(cmd);
        middleWare.addMiddle(MiddleWare.getJumpCmd(cmd) + " " + labelNum);
        label.newPoint("label " + labelNum);

        getAST().clear();
    }

}
