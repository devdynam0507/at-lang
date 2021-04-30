package kr.atlang.token;

public class TokenConst {

    final public static int NOT_KEYWORD = -1;

    final public static int EOF = 0;
    final public static int SUBSTITUTE = 1; // =
    final public static int IDENTIFIER = 2; // [a-zA-Z0-9]
    final public static int INT = 20;

    final public static int PLUS = 3;
    final public static int MINUS = 4;
    final public static int MULTIPLY = 5;
    final public static int DIVIDE = 6;

    final public static int EQUALS = 7; // @@
    final public static int NOT_EQUALS = 8; // !=
    final public static int BIGGER_THEN_LEFT = 9; // <
    final public static int BIGGER_THEN_RIGHT = 10; // >
    final public static int BIGGER_THEN_LEFT_OR_EQUALS = 11; // <=
    final public static int BIGGER_THEN_RIGHT_OR_EQUALS = 12; // >=

    final public static int IF = 13;

    final public static int LABEL = 14;

    final public static int PRINTER = 15;

    final public static int L_PAREN = 16;
    final public static int R_PAREN = 17;
    final public static int L_BRACKET = 18;
    final public static int R_BRACKET = 19;

    final public static String IDENTIFIER_REGEX = "[a-zA-Z]+";
    final public static String DIGIT_REGEX = "[0-9]+";
}
