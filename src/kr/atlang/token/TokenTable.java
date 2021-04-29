package kr.atlang.token;

import java.util.HashMap;
import java.util.Map;

public class TokenTable {

    private Map<String, Integer> tokenTable;

    public TokenTable() {
        this.tokenTable = new HashMap<>();
    }

    public void initialize() {
        tokenTable.put("=", TokenConst.SUBSTITUTE);
        tokenTable.put("+", TokenConst.PLUS);
        tokenTable.put("-", TokenConst.MINUS);
        tokenTable.put("*", TokenConst.MULTIPLY);
        tokenTable.put("/", TokenConst.DIVIDE);
        tokenTable.put("if", TokenConst.IF);
        tokenTable.put("<", TokenConst.BIGGER_THEN_LEFT);
        tokenTable.put(">", TokenConst.BIGGER_THEN_RIGHT);
        tokenTable.put("<=", TokenConst.BIGGER_THEN_LEFT_OR_EQUALS);
        tokenTable.put(">=", TokenConst.BIGGER_THEN_RIGHT_OR_EQUALS);
        tokenTable.put("@@", TokenConst.EQUALS);
        tokenTable.put("!=", TokenConst.NOT_EQUALS);
        tokenTable.put("print", TokenConst.PRINTER);
    }

    public boolean isToken(String token) {
        return tokenTable.containsKey(token);
    }

    public int getTokenId(String token) {
        Integer id = tokenTable.get(token);

        if(id == null) {
            id = -1;
        }

        return id;
    }

}
