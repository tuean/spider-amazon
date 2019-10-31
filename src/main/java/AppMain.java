import enums.ResultType;
import exception.BusinessException;
import fruit.DBResultHandler;
import fruit.ResultHandler;
import logger.MineLogger;
import org.apache.commons.cli.*;
import org.apache.commons.lang.StringUtils;
import spider.BaseSpider;
import spider.SerialSpider;
import starter.Starter;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static settings.Constants.setBaseHost;

public class AppMain {

    public static void main(String[] args) throws ParseException {


        Options options = new Options();
        options.addOption("k", "key", true, "search key or keys");
        options.addOption("s", "separator", true, "separator of keys, ignore this when only one key");
        options.addOption("r", "remote", true, "base remote url, default is https://www.amazon.com");
        options.addOption("h", "help", false, "Print help");
        options.addOption("t", "type", true, "excel or db, default excel");
        options.addOption("o", "output path", true, "excel output path");
        options.addOption("p", "database path", true, "the path of database e.g. jdbc:mysql://xxxxxxxx");
        options.addOption("u", "user", true, "mysql user, valid when t is db");
        options.addOption("p", "password", true, "mysql password, valid when t is db");


        CommandLineParser parser = new DefaultParser();

        CommandLine cmdLine = parser.parse(options, args);

        if (cmdLine.hasOption("h") || cmdLine.hasOption("help")) {
            HelpFormatter hf = new HelpFormatter();
            hf.setWidth(200);
            hf.printHelp("help", options, true);

            System.exit(-1);
        }

        if (cmdLine.hasOption("r")) {
            String remote = cmdLine.getOptionValue("r");
            setBaseHost(remote);
        }

        if (!cmdLine.hasOption("k")) {
            throw new IllegalArgumentException("lack of param");
        }

        String key = cmdLine.getOptionValue("k");

        String separator = cmdLine.hasOption("s") ? cmdLine.getOptionValue("s") : "";

        String type = cmdLine.hasOption("t") ? cmdLine.getOptionValue("t") : "";

        ResultType resultType = ResultType.getByString(type);
        if (resultType == null) {
            throw new IllegalArgumentException("unknown type: " + type);
        }

        Starter.run(key, separator, resultType);
    }

}
