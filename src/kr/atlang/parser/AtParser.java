package kr.atlang.parser;

import kr.atlang.impl.ICompiler;
import kr.atlang.lexer.AtLexer;
import kr.atlang.lexer.SyntaxConst;
import kr.atlang.parser.ast.AST;
import kr.atlang.parser.ast.register.ASTRegister;
import kr.atlang.parser.ast.syntax.ASTValidation;
import kr.atlang.token.Token;
import kr.atlang.vm.IMiddleWareConvertor;
import kr.atlang.vm.MiddleWare;

import java.util.List;

/**
 * ast를 생성하고 중간 코드를 생성합니다.
 *
 * @author 남대영
 * */
public class AtParser implements ICompiler {

    private AtLexer.LexerResult lexerResult;
    private ASTValidation validation;
    private MiddleWare middleWare;

    public AtParser(AtLexer.LexerResult lexerResult, ASTValidation validation, MiddleWare middleWare) {
        this.lexerResult = lexerResult;
        this.validation = validation;
        this.middleWare = middleWare;
    }

    @Override
    public ICompiler compile() {
        int line = 1;
        int size = lexerResult.getLine();
        ASTRegister register = ASTRegister.getASTRegister();

        for(; line < size; line++) {
            List<Token> tokens = lexerResult.get(line);
            SyntaxConst syntax = validation.validation(tokens);
            AST ast = register.getAST(syntax);

            if(ast != null && ast instanceof IMiddleWareConvertor) {
                ast.sort(tokens);
                ((IMiddleWareConvertor) ast).toMiddleware(middleWare);
            }
        }

        return null;
    }

    @Override
    public Object get() {
        return null;
    }

}
