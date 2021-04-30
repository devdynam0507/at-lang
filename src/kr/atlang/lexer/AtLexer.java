package kr.atlang.lexer;

import kr.atlang.impl.ICompiler;
import kr.atlang.token.Token;
import kr.atlang.token.TokenTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AtLexer implements ICompiler<AtLexer.LexerResult> {

    private TokenTable tokenTable;
    private List<Token> tokens;
    private LexerResult lexerResult;

    public AtLexer(TokenTable tokenTable, List<Token> tokens) {
        this.tokenTable = tokenTable;
        this.tokens = tokens;
        this.lexerResult = new LexerResult();
    }

    public ICompiler<LexerResult> compile() {

        for(Token token : tokens) {
            lexerResult.addTokenByLine(token, token.getLine());
        }

        return this;
    }

    public LexerResult get() { return lexerResult; }

    public static class LexerResult {

        private Map<Integer, List<Token>> mergedTokens;

        public LexerResult() {
            this.mergedTokens = new HashMap<>();
        }

        public void addTokenByLine(Token token, int line) {
            List<Token> tokenList = getTokens(line);
            tokenList.add(token);
        }

        public List<Token> get(int line) {
            return mergedTokens.get(line);
        }

        private void allocList(int line) {
            if(!mergedTokens.containsKey(line)) {
                mergedTokens.put(line, new ArrayList<>());
            }
        }

        private List<Token> getTokens(int line) {
            allocList(line);

            return get(line);
        }

        @Override
        public String toString() {
            return "LexerResult{" +
                    "mergedTokens=" + mergedTokens +
                    '}';
        }
    }

}
