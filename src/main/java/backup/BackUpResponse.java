package backup;

import settings.Constants;
import util.StringUtil;

import java.io.File;
import java.io.FileWriter;

import static util.FileUtil.defaultFilePath;

public class BackUpResponse {

    public static void store(String searchKey, Integer page, String content) {
        String baseFolder = defaultFilePath();

        String keyFolder = baseFolder + File.separator + searchKey;

        keyFolder = keyFolder + File.separator + StringUtil.formatReqUrl(Constants.globalConfig.getBaseHost()) + File.separator;

        File fileFolder = new File(keyFolder);
        if (!fileFolder.exists()) {
            fileFolder.mkdirs();
        }

        String finalFilePath = keyFolder + File.separator + page + Constants.HTML;
        try {
            FileWriter fw = new FileWriter(finalFilePath);
            fw.write(content);
            fw.close();
        } catch (Exception var) {
            var.printStackTrace();
        }

    }

}
