package kr.atlang.parser.ast;

import kr.atlang.parser.Label;
import kr.atlang.token.Token;
import kr.atlang.vm.IMiddleWareConvertor;
import kr.atlang.vm.MiddleWare;

import java.util.List;

public class ScopeFinishAST extends AST implements IMiddleWareConvertor {

    private Label label;

    public ScopeFinishAST(Label label) {
        this.label = label;
    }

    @Override
    public void sort(List<Token> tokens) {
    }

    @Override
    public void toMiddleware(MiddleWare middleWare) {
        middleWare.addMiddle(label.dequeueSrc());
    }

}
