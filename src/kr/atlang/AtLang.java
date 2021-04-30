package kr.atlang;

import kr.atlang.io.ScriptReader;
import kr.atlang.lexer.AtLexer;
import kr.atlang.token.Token;
import kr.atlang.token.TokenConst;
import kr.atlang.token.TokenTable;

import java.util.List;

public class AtLang {

    public static void main(String... args) {
        String script = new ScriptReader().getSourceCodes();

        TokenTable tokenTable = new TokenTable();
        tokenTable.initialize();

        AtLexer lexer = new AtLexer(tokenTable, script);
        List<Token> tokens = lexer.compile().getTokens();
    }

}
