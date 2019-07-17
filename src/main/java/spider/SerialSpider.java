package spider;

import backup.BackUpResponse;
import exception.BusinessException;
import fruit.ResultHandler;
import http.HttpHelper;
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
import java.util.Random;

import static settings.Constants.baseHost;

public class SerialSpider implements BaseSpider {

    private static String lastPageUrl = null;

    private static String nextPageUrl = null;

    public void start(String key, ResultHandler resultHandler) throws BusinessException, SQLException, ClassNotFoundException, InterruptedException {
        int page = 1;

        for (;;) {
            String startUrl = Constants.getRequestUrl(key, page);
            startUrl = nextPageUrl == null ? startUrl : nextPageUrl;

            Map<String, String> headers = new HashMap<>();
            headers.put("Host", baseHost);
            headers.put("Referer", lastPageUrl == null ? baseHost : lastPageUrl);
            headers.put("Upgrade-Insecure-Requests", "1");
            headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            headers.put("Accept-Encoding", "gzip, deflate, br");
            headers.put("Accept-Language", "zh-CN,zh;q=0.9");
            headers.put("Connection", "keep-alive");



            String content = null;
            try {
                content = HttpHelper.get(startUrl, headers);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("catch error while visit this page: " + page);
                break;
            }

            if (StringUtils.isBlank(content)) {
                System.out.println("get empty page at page: " + page);
                break;
            }

            // backup the response
            BackUpResponse.store(key, page, content);

            List<ProductDetail> list = SearchListResultParser.parse(content, key);
            if (list.size() < 1) {
                System.out.println("result list's size is 0");
                break;
            }

            resultHandler.handleResult(list);

            System.out.println("page " + page + " finished, size: " + list.size());

            lastPageUrl = startUrl;
            nextPageUrl = NextPageParser.parse(content);

            Thread.sleep(1000L);

            page++;
        }



    }
}
