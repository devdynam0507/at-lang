package kr.atlang.lexer;

import kr.atlang.token.Token;
import kr.atlang.token.TokenTable;

import java.util.ArrayList;
import java.util.List;

public class AtLexer {

    private TokenTable tokenTable;
    private String script;
    private List<Token> tokens;

    public AtLexer(TokenTable tokenTable, String script) {
        this.tokenTable = tokenTable;
        this.script = script;
        this.tokens = new ArrayList<>();
    }

    public AtLexer compile() {
        tokens.clear();

        char[] array = script.toCharArray();
        StringBuilder sb = new StringBuilder();
        int line = 1;

        for(char c : array) {
            boolean isCarriage = AtLexerUtil.isCarriage(c);

            if(AtLexerUtil.isBlank(c) || isCarriage) {
                String token = sb.toString().trim();

                if(!token.isEmpty()) {
                    int tokenId;

                    if (tokenTable.isToken(token)) {
                        tokenId = tokenTable.getTokenId(token);
                    } else {
                        tokenId = tokenTable.getTokenIdWithRegex(token);
                    }

                    tokens.add(new Token(token, tokenId, line));
                    sb.setLength(0);
                    line += (isCarriage ? 1 : 0);
                    continue;
                }
            }

            sb.append(c);
        }

        return this;
    }

    public List<Token> getTokens() { return tokens; }

}
