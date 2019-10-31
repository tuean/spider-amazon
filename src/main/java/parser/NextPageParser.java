package parser;

import com.alibaba.fastjson.JSON;
import exception.BusinessException;
import logger.MineLogger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import settings.Constants;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class NextPageParser {

    public static String parse(String content) {
        Document doc = Jsoup.parse(content);
        Elements elements = doc.select("li.a-last > a");

        if (!elements.isEmpty()) {
            return Constants.getBaseHost() + elements.get(0).attr("href");
        }

        MineLogger.log("elements is empty");
        return null;
    }


    public static void main(String[] args) throws IOException, BusinessException {
        ClassLoader classLoader = SearchListResultParser.class.getClassLoader();
        File file = new File(classLoader.getResource("example.txt").getFile());

        String content = new String(Files.readAllBytes(file.toPath()));

        String result = NextPageParser.parse(content);
        System.out.println(result);
    }

}
