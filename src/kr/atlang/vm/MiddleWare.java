package kr.atlang.vm;

import kr.atlang.token.Token;
import kr.atlang.token.TokenConst;

import java.util.ArrayList;
import java.util.List;

public class MiddleWare {

    public static final String OPER_ADD = "_add";
    public static final String OPER_SUB = "_subtract";
    public static final String OPER_MULT = "_multiply";
    public static final String OPER_DIVIDE = "_divide";

    private List<String> vmMiddleWare;

    public MiddleWare() {
        this.vmMiddleWare = new ArrayList<>();
    }

    public void addMiddle(String middleWareSrc) {
        vmMiddleWare.add(middleWareSrc);
    }

    public static String getOperatorToMiddleWare(Token token) {
        switch (token.getTokenId()) {
            case TokenConst.PLUS:
                return OPER_ADD;
            case TokenConst.MINUS:
                return OPER_SUB;
            case TokenConst.MULTIPLY:
                return OPER_MULT;
            case TokenConst.DIVIDE:
                return OPER_DIVIDE;
        }

        return "";
    }

}
