package kr.atlang.parser.ast.syntax;

import kr.atlang.lexer.SyntaxConst;
import kr.atlang.token.Token;
import kr.atlang.token.TokenConst;
import kr.atlang.token.TokenTable;

import java.util.List;

public class ASTValidation {

    public SyntaxConst validation(List<Token> tokens) {
        SyntaxConst syntax = SyntaxConst.BLANK;
        Token head = tokens.get(0);
        int id = head.getTokenId();

        switch (id) {
            case TokenConst.IDENTIFIER:
                syntax = SyntaxConst.DECLARE_VARIABLE;

                if(TokenTable.isOperator(tokens.get(1).getTokenId())) {
                    syntax = SyntaxConst.COMPARE;
                }
                break;
            case TokenConst.PRINTER:
                syntax = SyntaxConst.PRINT;
                break;
            case TokenConst.R_BRACKET:
                syntax = SyntaxConst.R_BRACKET;
                break;
        }

        return syntax;
    }

}
