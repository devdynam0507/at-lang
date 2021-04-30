package kr.atlang.parser;

import kr.atlang.impl.ICompiler;
import kr.atlang.token.Token;

import java.util.List;

public class AtParser implements ICompiler {

    private List<Token> tokens;

    public AtParser(List<Token> tokens) {
        this.tokens = tokens;
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
