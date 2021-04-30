package kr.atlang.tokenizer;

import kr.atlang.impl.ICompiler;
import kr.atlang.lexer.AtLexerUtil;
import kr.atlang.token.Token;
import kr.atlang.token.TokenConst;
import kr.atlang.token.TokenTable;

import java.util.ArrayList;
import java.util.List;

/**
 * 스크립트 소스를 토큰화 시켜주는 클래스입니다.
 *
 * @author 남대영
 * */
public class AtTokenizer implements ICompiler<List<Token>> {

    private TokenTable tokenTable;
    private String script;
    private List<Token> tokens;

    public AtTokenizer(TokenTable tokenTable, String script) {
        this.tokenTable = tokenTable;
        this.script = script;
        this.tokens = new ArrayList<>();
    }

    public ICompiler<List<Token>> compile() {
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

        tokens.add(new Token("", TokenConst.EOF, line));
        sb.setLength(0);
        return this;
    }

    public List<Token> get() { return tokens; }

}
