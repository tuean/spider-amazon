package enums;

import java.util.Collections;
import java.util.HashMap;
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
        map = Collections.unmodifiableMap(new HashMap<>());
    }


}
