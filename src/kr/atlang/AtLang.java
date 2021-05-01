package kr.atlang;

import kr.atlang.impl.ICompiler;
import kr.atlang.io.ScriptReader;
import kr.atlang.lexer.AtLexer;
import kr.atlang.parser.AtParser;
import kr.atlang.parser.Label;
import kr.atlang.parser.ast.AST;
import kr.atlang.parser.ast.ConsolePrintAST;
import kr.atlang.parser.ast.IfAST;
import kr.atlang.parser.ast.Int64DeclareVariableAST;
import kr.atlang.parser.ast.syntax.ASTValidation;
import kr.atlang.stack.StackMachine;
import kr.atlang.token.Token;
import kr.atlang.token.TokenTable;
import kr.atlang.tokenizer.AtTokenizer;
import kr.atlang.vm.ConsoleBuf;
import kr.atlang.vm.MiddleWare;
import kr.atlang.vm.VirtualMemory;
import kr.atlang.vm.VirtualMemoryRegister;

import java.util.ArrayList;
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

        MiddleWare middleWare = new MiddleWare();
        ASTValidation validation = new ASTValidation();
        AtParser parser = new AtParser(lexerResult, validation, middleWare);
        parser.compile();

        VirtualMemory virtualMemory = new VirtualMemory(8096);
        VirtualMemoryRegister register = new VirtualMemoryRegister(virtualMemory);
        ConsoleBuf console = new ConsoleBuf();

        long start = System.currentTimeMillis();
        StackMachine main = new StackMachine(register, middleWare, tokenTable, console);
        main.begin();
        long end = System.currentTimeMillis();

        System.out.println("실행시간: " + (end - start) + "ms");
    }

}
