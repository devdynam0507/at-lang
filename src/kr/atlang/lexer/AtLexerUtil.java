package kr.atlang.lexer;

public class AtLexerUtil {

    public static boolean isCarriage(char c) {
        return (int) c == 10;
    }

    public static boolean isBlank(char c) {
        return c == ' ';
    }

}
