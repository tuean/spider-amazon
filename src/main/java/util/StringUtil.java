package util;

public class StringUtil {


    public static String formatReqUrl(String baseHost) {
        return baseHost.replace("https://", "").replace("http://", "");
    }

}
