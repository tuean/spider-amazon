package settings;


import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UrlRecords {


    private static Map<String, Boolean> urlMap;

    static {
        urlMap = new HashMap<String, Boolean>(1 << 8);
    }


    public static void addToVisit(String url) {
        if (StringUtils.isBlank(url)) {
            return;
        }

        if (!checkIfExist(url)) {
            return;
        }

        urlMap.put(url, false);
    }

    public static void visited(String url) {
        if (StringUtils.isBlank(url)) {
            return;
        }

        urlMap.put(url, true);
    }

    public static boolean checkIfExist(String url) {
        if (StringUtils.isBlank(url)) {
            return false;
        }
        for (Object o : urlMap.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            String key = (String) entry.getKey();
            if (url.trim().equals(key)) {
                return true;
            }
        }
        return false;
    }



}
