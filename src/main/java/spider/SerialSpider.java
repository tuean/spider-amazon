package spider;

import backup.BackUpResponse;
import exception.BusinessException;
import fruit.ResultHandler;
import http.HttpHelper;
import logger.MineLogger;
import org.apache.commons.lang.StringUtils;
import parser.NextPageParser;
import parser.ProductDetail;
import parser.SearchListResultParser;
import settings.Constants;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SerialSpider implements BaseSpider {

    private static String lastPageUrl = null;

    private static String nextPageUrl = null;

    private static final Integer advertisementSize = 6;
    private static final Integer adTryTimes = 6;

    @Override
    public void start(String key, ResultHandler resultHandler) throws BusinessException, SQLException, ClassNotFoundException, InterruptedException {
        resultHandler.init();

        int page = 1;

        int adFlag = 0;

        for (;;) {
            String startUrl = Constants.globalConfig.getRequestUrl(key, page);
            startUrl = nextPageUrl == null ? startUrl : nextPageUrl;

            MineLogger.log("instant page: " + page);
            MineLogger.log("startUrl: " + startUrl);

            Map<String, String> headers = new HashMap<>();
            headers.put("Host", Constants.globalConfig.getBaseHost());
            headers.put("Referer", lastPageUrl == null ? Constants.globalConfig.getBaseHost() : lastPageUrl);
            headers.put("Upgrade-Insecure-Requests", "1");
            headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            headers.put("Accept-Encoding", "gzip, deflate, br");
            headers.put("Accept-Language", "zh-CN,zh;q=0.9");
            headers.put("Connection", "keep-alive");


            String content = null;
            MineLogger.log("request start");
            try {
                content = HttpHelper.get(startUrl, headers);
            } catch (IOException e) {
                MineLogger.log("request catch error while visit this page:" + page);
                MineLogger.log("urk: " + startUrl);
                MineLogger.log(e);
                break;
            } finally {
                MineLogger.log("request finished");
            }

            if (StringUtils.isBlank(content)) {
                MineLogger.log("get empty page at page: " + page);
                break;
            }

            // backup the response
            BackUpResponse.store(key, page, content);

            List<ProductDetail> list = SearchListResultParser.parse(content, key);
            if (list.size() < 1) {
                MineLogger.log("result list's size is 0");
                break;
            }

            if (list.size() < advertisementSize) {
                adFlag++;
            }

            if (adFlag > adTryTimes) {
                MineLogger.log("result list is all of advertisement, break");
                break;
            }

            resultHandler.handleResult(list);

            MineLogger.log("page " + page + " finished, size: " + list.size());

            lastPageUrl = startUrl;
            nextPageUrl = NextPageParser.parse(content);

            Thread.sleep(1000L);

            page++;
        }


        resultHandler.finish();
    }
}
