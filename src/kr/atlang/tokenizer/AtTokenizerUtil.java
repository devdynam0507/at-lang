package kr.atlang.tokenizer;

public class AtTokenizerUtil {

    public static boolean isCarriage(char c) {
        return (int) c == 10;
    }

    public static boolean isBlank(char c) {
        return c == ' ';
    }

}
