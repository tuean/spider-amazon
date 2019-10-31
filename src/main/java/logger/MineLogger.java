package logger;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class MineLogger {

    private static String getInstant() {
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS", Locale.CHINA);

        return format.format(ldt) + " ";
    }

    public static void log(Object log) {
        System.out.println(getInstant() + JSONObject.toJSONString(log));
    }


    public static void log(String prefix, Object... logs) {
        log(prefix + JSONObject.toJSONString(logs));
    }

    public static void log(Throwable throwable) {
        log(ExceptionUtils.getFullStackTrace(throwable));
    }

}
