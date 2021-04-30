package kr.atlang.parser;

import kr.atlang.impl.ICompiler;
import kr.atlang.lexer.AtLexer;

public class AtParser implements ICompiler {

    private AtLexer.LexerResult lexerResult;
    private int currentLine;

    public AtParser(AtLexer.LexerResult lexerResult) {
        this.lexerResult = lexerResult;
        this.currentLine = 1;
    }

    @Override
    public ICompiler compile() {
        return null;
    }

    @Override
    public Object get() {
        return null;
    }
}
