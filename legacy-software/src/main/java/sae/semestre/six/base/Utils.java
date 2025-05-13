package sae.semestre.six.base;

import java.io.FileWriter;
import java.io.IOException;

public class Utils {

    public static void writeToFile(String path, String content) throws IOException {
        try (FileWriter fw = new FileWriter(path, true)) {
            fw.write(content);
        } catch (IOException e) {
            throw e;
        }
    }

}
