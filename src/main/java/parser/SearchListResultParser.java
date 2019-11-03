package parser;

import com.alibaba.fastjson.JSON;
import exception.BusinessException;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import settings.Constants;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class SearchListResultParser {


    private static Pattern pp_goods = Pattern.compile("http://www(.+?)/dp/(.+)");

    public static List<ProductDetail> parse(String content, String searchKey) throws BusinessException {
        if (StringUtils.isBlank(content)) {
            throw new BusinessException("web page result is empty");
        }

        List<ProductDetail> resultList = new ArrayList<ProductDetail>(30);

        Document doc = Jsoup.parse(content);

        Elements nodes = doc.select("div.s-search-results");

        if (nodes.isEmpty()) {
            return new ArrayList<>(0);
//            throw new BusinessException("can't find result dom");
        }

        for (Element node : nodes.get(0).children()) {
            String asin = node.attr("data-asin").trim();
            String productName = node.select("span.a-text-normal").text().trim();
            String grade = node.select("span.a-icon-alt").text().trim();
            String commentSize = node.select("a.a-link-normal").select("span.a-size-base").text().replace("更多购买选择", "").trim();
            String price = node.select("span.a-offscreen").text().trim();
            String link = node.select("a.a-link-normal").attr("href").trim();
            if (StringUtils.isBlank(price)) {
                price = node.select("div.a-color-secondary").select("div > span.a-color-base").text().trim();
//                System.out.println(price);
            }

            ProductDetail detail = ProductDetail.builder()
                    .asin(asin)
                    .productName(productName)
                    .productDetailUrl(Constants.globalConfig.getBaseHost() + link)
                    .grade(grade)
                    .price(price)
                    .searchKey(searchKey)
                    .build();

            if (StringUtils.isNotBlank(commentSize)) {
                try {
                    detail.setCommentNum(Integer.parseInt(commentSize.replaceAll(",", "")));
                } catch (Exception var){
                    var.printStackTrace();
                }
            }

            resultList.add(detail);
        }

        return resultList;
    }


    public static void main(String[] args) throws IOException, BusinessException {
        ClassLoader classLoader = SearchListResultParser.class.getClassLoader();
        File file = new File(classLoader.getResource("example.txt").getFile());

        String content = new String(Files.readAllBytes(file.toPath()));

        List list = SearchListResultParser.parse(content, "text");
        System.out.println(JSON.toJSONString(list));
    }



}
