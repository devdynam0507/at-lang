package kr.atlang.stack;

import kr.atlang.token.Token;
import kr.atlang.token.TokenConst;

public class OperatorPriority {

    public static final int PLUS = 1;
    public static final int MULTIPLY = 2;
    public static final int PAREN = 0;

    public static int getPriority(Token token) {
        switch (token.getTokenId()) {
            case TokenConst.PLUS:
            case TokenConst.MINUS:
                return PLUS;
            case TokenConst.MULTIPLY:
            case TokenConst.DIVIDE:
                return MULTIPLY;
            case TokenConst.L_PAREN:
                return PAREN;
            default:
                return -1;
        }
    }

}
