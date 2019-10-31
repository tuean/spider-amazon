package starter;

import enums.ResultType;
import fruit.DBResultHandler;
import fruit.ExcelResultHandler;
import fruit.ResultHandler;
import logger.MineLogger;
import org.apache.commons.lang.StringUtils;
import spider.BaseSpider;
import spider.SerialSpider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Starter {


    public static void run(String key, String separator, ResultType resultType) {
        MineLogger.log("spider has started");
//        String seperator = "，";
//        String key = "Educational toys，intelligence toys，Toy dinosaur， Jigsaw puzzles，construction toys";

        List<String> keys;
        if (StringUtils.isEmpty(separator)) {
            keys = new ArrayList<>(1);
            keys.add(key);
        } else {
            keys = Arrays.stream(key.split(separator))
                    .map(String::trim)
                    .filter(StringUtils::isNotBlank)
                    .collect(Collectors.toList());
            MineLogger.log("keys:{}", keys);
        }
        MineLogger.log("key size: " + keys.size());


        BaseSpider spider = new SerialSpider();
        ResultHandler resultHandler = ResultHandler.getByType(resultType);

        for (String k : keys) {
            try {
                spider.start(k, resultHandler);
            } catch (Exception var) {
                MineLogger.log(var);
            }
        }

        MineLogger.log("spider has finished");
    }

}
