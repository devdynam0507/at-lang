package kr.atlang.token;

public class Token {

    private String token;
    private int tokenId;
    private int line;

    public Token(String token, int tokenId, int line) {
        this.token = token;
        this.tokenId = tokenId;
        this.line = line;
    }

    public String getToken() { return token; }
    public int getTokenId() { return tokenId; }
    public int getLine() { return line; }

    @Override
    public String toString() {
        return token;
    }

}
