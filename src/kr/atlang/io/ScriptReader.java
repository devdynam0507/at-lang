package kr.atlang.io;

import java.io.*;

public class ScriptReader {

    private static final String TEST_RESOURCE_PATH = System.getProperty("user.dir") + "/resources/script.at";

    private File resource;

    public ScriptReader() {
        this.resource = new File(TEST_RESOURCE_PATH);
    }

    public String getSourceCodes() {
        return read().toString();
    }

    private StringBuilder read() {
        FileReader reader;
        BufferedReader bufferedReader;
        StringBuilder sb = new StringBuilder();

        try {
            reader = new FileReader(resource);
            bufferedReader = new BufferedReader(reader);
            String line = "";

            while((line = bufferedReader.readLine()) != null) {
                if(!line.isEmpty()) {
                    sb.append(line).append("\n");
                }
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return sb;
    }

}
