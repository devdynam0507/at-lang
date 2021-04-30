package kr.atlang;

import kr.atlang.impl.ICompiler;
import kr.atlang.io.ScriptReader;
import kr.atlang.lexer.AtLexer;
import kr.atlang.parser.ast.AST;
import kr.atlang.parser.ast.Int64DeclareVariableAST;
import kr.atlang.token.Token;
import kr.atlang.token.TokenTable;
import kr.atlang.tokenizer.AtTokenizer;

import java.util.List;

public class AtLang {

    public static void main(String... args) {
        String script = new ScriptReader().getSourceCodes();

        TokenTable tokenTable = new TokenTable();
        tokenTable.initialize();

        ICompiler<List<Token>> tokenizer = new AtTokenizer(tokenTable, script);
        List<Token> tokens = tokenizer.compile().get();

        AtLexer lexer = new AtLexer(tokenTable, tokens);
        AtLexer.LexerResult lexerResult = lexer.compile().get();

        AST ast = new Int64DeclareVariableAST();
        ast.sort(lexerResult.get(2));
    }

}
