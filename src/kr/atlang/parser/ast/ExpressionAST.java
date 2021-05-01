package kr.atlang.parser.ast;

import kr.atlang.stack.OperatorPriority;
import kr.atlang.token.Token;
import kr.atlang.token.TokenConst;

import java.util.List;
import java.util.Stack;

public class ExpressionAST extends AST {

    @Override
    public void sort(List<Token> tokens) {
        int size = tokens.size();
        Stack<Token> operatorStack = new Stack<>();

        for(int i = 0; i < size; i++) {
            Token token = tokens.get(i);
            int tokenId = token.getTokenId();

            if(tokenId == TokenConst.INT || tokenId == TokenConst.IDENTIFIER) {
                addTokenToStack(token);
                continue;
            } else {
                if(tokenId == TokenConst.L_PAREN || operatorStack.empty()) {
                    operatorStack.push(token);
                    continue;
                } else if(tokenId == TokenConst.R_PAREN) {
                    while(!operatorStack.empty()) {
                        Token pop = operatorStack.pop();

                        if(pop.getTokenId() != TokenConst.L_PAREN) {
                            addTokenToStack(pop);
                        }
                    }

                    continue;
                } else {
                    Token inStackOperator = operatorStack.peek();
                    int inStackOperatorPriority = OperatorPriority.getPriority(inStackOperator);
                    int inputOperatorPriority = OperatorPriority.getPriority(token);

                    while(!operatorStack.empty() && inStackOperatorPriority > inputOperatorPriority) {
                        Token pop = operatorStack.pop();
                        inStackOperatorPriority = OperatorPriority.getPriority(pop);

                        if(pop.getTokenId() == TokenConst.L_PAREN) {
                            break;
                        }

                        addTokenToStack(pop);
                    }

                    operatorStack.push(token);
                }
            }
        }

        while(!operatorStack.empty()) {
            Token pop = operatorStack.pop();
            int tokenId = pop.getTokenId();

            if(tokenId != TokenConst.R_PAREN && tokenId != TokenConst.L_PAREN) {
                addTokenToStack(pop);
            }
        }
    }

}
