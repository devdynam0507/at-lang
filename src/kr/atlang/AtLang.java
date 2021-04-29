package kr.atlang;

import kr.atlang.io.ScriptReader;
import kr.atlang.lexer.AtLexer;
import kr.atlang.token.TokenTable;

public class AtLang {

    public static void main(String... args) {
        String script = new ScriptReader().getSourceCodes();

        TokenTable tokenTable = new TokenTable();
        tokenTable.initialize();

        AtLexer lexer = new AtLexer(tokenTable, script);
        lexer.lex();
    }

}
