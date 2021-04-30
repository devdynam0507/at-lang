package kr.atlang.parser.ast;

import kr.atlang.stack.OperatorPriority;
import kr.atlang.token.Token;
import kr.atlang.token.TokenConst;

import java.util.List;
import java.util.Stack;

public class ExpressionAST extends AST {

    private Stack<Token> expression;
    private List<Token> tokens;
    private int size;

    public ExpressionAST() {
        this.expression = new Stack<>();
    }

    @Override
    public void sort(List<Token> tokens) {
        this.tokens = tokens;
        this.size = tokens.size();
        Stack<Token> operatorStack = new Stack<>();

        for(int i = 0; i < size; i++) {
            Token token = tokens.get(i);
            int tokenId = token.getTokenId();

            if(tokenId == TokenConst.INT || tokenId == TokenConst.IDENTIFIER) {
                expression.push(token);
                continue;
            } else {
                System.out.println(token);
                if(tokenId == TokenConst.L_PAREN || operatorStack.empty()) {
                    operatorStack.push(token);
                    continue;
                } else if(tokenId == TokenConst.R_PAREN) {
                    while(!operatorStack.empty()) {
                        Token pop = operatorStack.pop();

                        if(pop.getTokenId() != TokenConst.L_PAREN) {
                            expression.push(pop);
                        }
                    }

                    continue;
                } else {
                    Token inStackOperator = operatorStack.peek();
                    int inStackOperatorPriority = OperatorPriority.getPriority(inStackOperator);
                    int inputOperatorPriority = OperatorPriority.getPriority(token);

                    if(inStackOperator.getTokenId() == TokenConst.L_PAREN) {
                        operatorStack.push(inStackOperator);
                        continue;
                    }

                    while(!operatorStack.empty() && inStackOperatorPriority > inputOperatorPriority) {
                        Token pop = operatorStack.pop();
                        inStackOperatorPriority = OperatorPriority.getPriority(pop);

                        if(pop.getTokenId() == TokenConst.L_PAREN) {
                            break;
                        }

                        expression.push(pop);
                    }

                    operatorStack.push(token);
                }
            }
        }

        while(!operatorStack.empty()) {
            Token pop = operatorStack.pop();
            int tokenId = pop.getTokenId();

            if(tokenId != TokenConst.R_PAREN && tokenId != TokenConst.L_PAREN) {
                expression.push(pop);
            }
        }

        System.out.println(expression);
    }

    @Override
    public boolean isValidSyntax(List<Token> tokens) {
        return false;
    }

}
