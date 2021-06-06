package enums;

public enum BaiduType {

    GLOBAL;

    public static String get(BaiduType type) {
        return "https://www.baidu.com/s?";
    }

    public static String getRequestUrl() {
        return "/s?wd=&s&pn=3&rn=20";
    }

}
