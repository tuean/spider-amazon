package starter;

import com.alibaba.excel.util.CollectionUtils;
import entity.SearchContainer;
import enums.ResultType;
import fruit.DBResultHandler;
import fruit.ExcelResultHandler;
import fruit.ResultHandler;
import logger.MineLogger;
import org.apache.commons.lang.StringUtils;
import settings.Constants;
import settings.GlobalConfig;
import spider.BaseSpider;
import spider.SerialSpider;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Starter {


    public static void run(SearchContainer container) throws AccessDeniedException {
        MineLogger.log("spider has started");
        String key = container.getKey();
        String separator = container.getSeparator();

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
        ResultHandler resultHandler = ResultHandler.getByType(container.getResultType());

        for (String k : keys) {
            if (CollectionUtils.isEmpty(container.getConfigList())) {
                List<GlobalConfig> list = new ArrayList<>();
                list.add(Constants.init());
                container.setConfigList(list);
            }
            for (GlobalConfig config : container.getConfigList()) {
                try {
                    resultHandler.init(container.isInitFlag());
                    spider.start(k, resultHandler, config);
                } catch (Exception var) {
                    MineLogger.log(var);
                }
            }
        }

        resultHandler.finish();

        MineLogger.log("spider has finished");
    }

}
