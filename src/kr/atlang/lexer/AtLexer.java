package kr.atlang.lexer;

import kr.atlang.token.Token;
import kr.atlang.token.TokenConst;
import kr.atlang.token.TokenTable;

import java.util.ArrayList;
import java.util.List;

public class AtLexer {

    private TokenTable tokenTable;
    private String script;

    public AtLexer(TokenTable tokenTable, String script) {
        this.tokenTable = tokenTable;
        this.script = script;
    }

    public void lex() {
        char[] array = script.toCharArray();
        StringBuilder sb = new StringBuilder();
        List<Token> tokens = new ArrayList<>();
        int line = 1;

        for(char c : array) {

            if(AtLexerUtil.isBlank(c) || AtLexerUtil.isCarriage(c)) {
                String token = sb.toString();
                int tokenId = TokenConst.IDENTIFIER;

                if(!tokenTable.isToken(token)) {
                    tokenId = tokenTable.getTokenId(token);
                }

                tokens.add(new Token(token, tokenId, line));
                sb.setLength(0);
                continue;
            }

            sb.append(c);
        }

        tokens.forEach(token -> System.out.println(token.toString()));
    }

}
