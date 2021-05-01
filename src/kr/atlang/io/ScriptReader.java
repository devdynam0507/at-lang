package kr.atlang.io;

import java.io.*;

public class ScriptReader {

    private static final String PATH_ = System.getProperty("user.dir") + "/{file_name}";
    private static final String TEST_PATH_ = System.getProperty("user.dir") + "/resources/script.at";

    private File resource;

    public ScriptReader(String fileName, boolean isTest) {
        if(isTest) {
            this.resource = new File(TEST_PATH_);
            System.out.println(resource);
        } else {
            this.resource = new File(PATH_.replace("{file_name}", fileName));

        }
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
