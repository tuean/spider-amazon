package backup;

import java.io.File;
import java.io.FileWriter;

import static util.FileUtil.defaultFilePath;

public class BackUpResponse {

    public static void store(String searchKey, Integer page, String content) {
        String baseFolder = defaultFilePath();

        String keyFolder = baseFolder + File.separator + searchKey;

        File fileFolder = new File(keyFolder);
        if (!fileFolder.exists()) {
            fileFolder.mkdir();
        }

        String finalFilePath = keyFolder + File.separator + page + ".html";
        try {
            FileWriter fw = new FileWriter(finalFilePath);
            fw.write(content);
            fw.close();
        } catch (Exception var) {
            var.printStackTrace();
        }

    }

}
