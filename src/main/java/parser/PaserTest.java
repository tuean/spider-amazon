package parser;

import com.alibaba.fastjson.JSON;
import exception.BusinessException;
import parser.defaults.SearchListResultParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class PaserTest {


    public static void main(String[] args) throws IOException, BusinessException {
        ClassLoader classLoader = SearchListResultParser.class.getClassLoader();
//        File file = new File(classLoader.getResource("example.txt").getFile());

        File file = new File("/Users/tuean/Desktop/tmp/Sealant/www.amazon.co.uk/1.html");

        String content = new String(Files.readAllBytes(file.toPath()));

        List list = SearchListResultParser.parse(content, "Sealant");
        System.out.println(JSON.toJSONString(list));
    }

}
