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
        tokenTable.put("(", TokenConst.L_PAREN);
        tokenTable.put(")", TokenConst.R_PAREN);
        tokenTable.put("{", TokenConst.L_BRACKET);
        tokenTable.put("}", TokenConst.R_BRACKET);
    }

    public boolean isToken(String token) {
        return tokenTable.containsKey(token);
    }

    public static boolean isOperator(int tokenId) {
        return tokenId >= 7 && tokenId <= 12;
    }

    public int getTokenId(String token) {
        Integer id = tokenTable.get(token);

        if(id == null) {
            id = TokenConst.NOT_KEYWORD;
        }

        return id;
    }

    public int getTokenIdWithRegex(String token) {
        boolean isIdentifier = token.matches(TokenConst.IDENTIFIER_REGEX);
        boolean isDigit = token.matches(TokenConst.DIGIT_REGEX);
        int tokenId = TokenConst.NOT_KEYWORD;

        if(isIdentifier) {
            tokenId = TokenConst.IDENTIFIER;
        } else if(isDigit) {
            tokenId = TokenConst.INT;
        }

        return tokenId;
    }

}
