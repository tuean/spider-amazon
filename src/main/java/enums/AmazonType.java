package enums;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum AmazonType {

    American,

    Australia,

    England

    ;


    public static Map<AmazonType, String> map = null;

    static {
        map = new HashMap<>();
        map.put(American, "https://www.amazon.com/");
        map.put(Australia, "https://www.amazon.com.au/");
        map.put(England, "https://www.amazon.co.uk/");
        map = Collections.unmodifiableMap(map);
    }


    public static String get(AmazonType type) {
        return map.get(type);
    }

    public static AmazonType get(String baseHost) {
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            if (entry.getValue().equals(baseHost)) {
                return (AmazonType) entry.getKey();
            }
        }
        return null;
    }

}
