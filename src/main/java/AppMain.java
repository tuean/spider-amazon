import entity.SearchContainer;
import enums.AmazonType;
import enums.ResultType;
import org.apache.commons.cli.*;
import settings.Constants;
import settings.GlobalConfig;
import starter.Starter;

import java.nio.file.AccessDeniedException;
import java.util.LinkedList;
import java.util.List;


public class AppMain {

    public static void main(String[] args) throws ParseException, AccessDeniedException {

        String key = "Reed Diffuser Sets";
        String separator = ",";
        ResultType resultType = ResultType.excel;


        List<GlobalConfig> configList = new LinkedList<>();
        GlobalConfig config = Constants.init();
        config.setBaseHost(AmazonType.get(AmazonType.American));
        configList.add(config);

        SearchContainer searchContainer = new SearchContainer();
        searchContainer.setKey(key);
        searchContainer.setSeparator(separator);
        searchContainer.setResultType(resultType);
        searchContainer.setConfigList(configList);

        Starter.run(searchContainer);
    }

}
