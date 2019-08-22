package settings;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Constants {

//    public static String baseHost = "https://www.amazon.com";
    public static String baseHost = "https://www.amazon.com.au";

    private static String searchUrl = baseHost + "/s?k=%s&lo=grid&__mk_zh_CN=亚马逊网站";

    private static String pageEnd = "&page=";


    public static String getRequestUrl(String key, int page) {
        key = URLEncoder.encode(key, StandardCharsets.UTF_8);
        String format = String.format(searchUrl, key);
        if (page > 1) {
            format += pageEnd + page;
        }

        return format;
    }

    public static String getRequestUrl(String key) {
        return getRequestUrl(key, 1);
    }


}
