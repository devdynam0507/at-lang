package kr.atlang.vm;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ConsoleBuf {

    private BufferedWriter bw;

    public ConsoleBuf() {
        this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    public void addBuf(String buf) {
        try {
            bw.write(buf);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addBuf(long l) {
        addBuf(String.valueOf(l));
    }

    public void out() {
        try {
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
