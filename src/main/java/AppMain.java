import entity.SearchContainer;
import enums.AmazonType;
import enums.BaiduType;
import enums.ResultType;
import enums.SourceEnum;
import org.apache.commons.cli.*;
import settings.Constants;
import settings.GlobalConfig;
import starter.Starter;

import java.nio.file.AccessDeniedException;
import java.util.LinkedList;
import java.util.List;


public class AppMain {

    public static void main(String[] args) throws ParseException, AccessDeniedException {

        String key = "Sealant";
        String separator = ",";
        ResultType resultType = ResultType.excel;


        List<GlobalConfig> configList = new LinkedList<>();
        GlobalConfig config = Constants.init();
//        config.setBaseHost(AmazonType.get(AmazonType.Australia));
//        config.setBaseHost(AmazonType.get(AmazonType.England));
//        config.setBaseHost(AmazonType.get(AmazonType.American));
        config.setBaseHost(BaiduType.get(BaiduType.GLOBAL));
        config.setSourceEnum(SourceEnum.Baidu);
        configList.add(config);

        SearchContainer searchContainer = new SearchContainer();
        searchContainer.setKey(key);
        searchContainer.setSeparator(separator);
        searchContainer.setResultType(resultType);
        searchContainer.setConfigList(configList);

        Starter.run(searchContainer);
    }

}
