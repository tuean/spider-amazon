package settings;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;

public class Constants {

//    public static String baseHost_default = "https://www.amazon.com";


    public static GlobalConfig globalConfig = new GlobalConfig();

    public static void init() throws AccessDeniedException {
        globalConfig.setBaseHost(GlobalConfig.baseHost_default);
        globalConfig.setOutPath(GlobalConfig.outPath_default);
    }







}
