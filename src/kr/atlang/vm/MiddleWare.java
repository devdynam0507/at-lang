package kr.atlang.vm;

import kr.atlang.token.Token;
import kr.atlang.token.TokenConst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiddleWare {

    public static final String PUSH = "push";

    public static final String OPER_ADD = "_add";
    public static final String OPER_SUB = "_subtract";
    public static final String OPER_MULT = "_multiply";
    public static final String OPER_DIVIDE = "_divide";

    public static final String CMP_OPER_EQ = "_eq";
    public static final String CMP_OPER_NEQ = "_neq";
    public static final String CMP_OPER_BL = "_bl";
    public static final String CMP_OPER_BLEQ = "_bleq";
    public static final String CMP_OPER_BR = "_br";
    public static final String CMP_OPER_BREQ = "_breq";

    public static final String JMP_ZERO = "_jz";
    public static final String JMP_NOT_ZERO = "_jnz";
    public static final String JMP_BL = "_jbl";
    public static final String JMP_BLEQ = "_jbleq";
    public static final String JMP_BR = "_jbr";
    public static final String JMP_BREQ = "_jbreq";

    public static final String CONBUF = "conbuf";
    public static final String CONFLUSH = "conflush";
    public static final String ALLOC = "alloc";

    private List<String> vmMiddleWare;

    public MiddleWare() {
        this.vmMiddleWare = new ArrayList<>();
    }

    public void addMiddle(String middleWareSrc) {
        vmMiddleWare.add(middleWareSrc);
    }

    public Map<String, Integer> getLabelPositions() {
        Map<String, Integer> cache = new HashMap<>();
        int len = vmMiddleWare.size();

        for(int i = 0; i < len; i++) {
            String labelSrc = vmMiddleWare.get(i);

            if(labelSrc.contains("label")) {
                cache.put(labelSrc.split(" ")[1], (i + 1));
            }
        }

        return cache;
    }

    public List<String> getVmMiddleWare() { return vmMiddleWare; }

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

    public static String getJumpCmd(String cmd) {
        switch (cmd) {
            case CMP_OPER_EQ:
                return JMP_ZERO;
            case CMP_OPER_NEQ:
                return JMP_NOT_ZERO;
            case CMP_OPER_BL:
                return JMP_BL;
            case CMP_OPER_BLEQ:
                return JMP_BLEQ;
            case CMP_OPER_BR:
                return JMP_BR;
            case CMP_OPER_BREQ:
                return JMP_BREQ;
        }

        return "";
    }

    public static String getCmpOperatorToMiddleWare(Token token) {
        switch (token.getTokenId()) {
            case TokenConst.EQUALS:
                return CMP_OPER_EQ;
            case TokenConst.NOT_EQUALS:
                return CMP_OPER_NEQ;
            case TokenConst.BIGGER_THEN_LEFT:
                return CMP_OPER_BL;
            case TokenConst.BIGGER_THEN_LEFT_OR_EQUALS:
                return CMP_OPER_BLEQ;
            case TokenConst.BIGGER_THEN_RIGHT:
                return CMP_OPER_BR;
            case TokenConst.BIGGER_THEN_RIGHT_OR_EQUALS:
                return CMP_OPER_BREQ;
        }

        return "";
    }

    public void printMiddleware() {
        vmMiddleWare.forEach(i -> System.out.println(i));
    }

}
