import entity.SearchContainer;
import enums.ResultType;
import org.apache.commons.cli.*;
import settings.Constants;
import starter.Starter;

import java.nio.file.AccessDeniedException;


public class JarMain {

    public static void main(String[] args) throws ParseException, AccessDeniedException {
        Options options = new Options();
        options.addOption("k", "key", true, "search key or keys");
        options.addOption("s", "separator", true, "separator of keys, ignore this when only one key");
        options.addOption("r", "remote", true, "base remote url, default is https://www.amazon.com");
        options.addOption("h", "help", false, "Print help");
        options.addOption("t", "type", true, "excel or db, default excel");
        options.addOption("o", "output path", true, "excel output path");
        options.addOption("d", "database path", true, "the path of database e.g. jdbc:mysql://xxxxxxxx");
        options.addOption("u", "user", true, "mysql user, valid when t is db");
        options.addOption("p", "password", true, "mysql password, valid when t is db");
        options.addOption("hs", "html save flag", false, "add this to store response html");


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
            Constants.globalConfig.setBaseHost(remote);
        }

        if (!cmdLine.hasOption("k")) {
            throw new IllegalArgumentException("lack of param");
        }

        if (cmdLine.hasOption("hs")) {
            Constants.globalConfig.setHtmlSave(true);
        } else {
            Constants.globalConfig.setHtmlSave(false);
        }

        String key = cmdLine.getOptionValue("k");
        Constants.globalConfig.setKey(key);

        String separator = cmdLine.hasOption("s") ? cmdLine.getOptionValue("s") : "";
        Constants.globalConfig.setSeparator(separator);

        String type = cmdLine.hasOption("t") ? cmdLine.getOptionValue("t") : "";


        ResultType resultType = ResultType.getByString(type);
        if (resultType == null) {
            throw new IllegalArgumentException("unknown type: " + type);
        }
        Constants.globalConfig.setResultType(resultType);

        if (ResultType.db.equals(resultType)) {
            String dbPath = cmdLine.getOptionValue("d");
            String user = cmdLine.getOptionValue("u");
            String password = cmdLine.getOptionValue("p");
            Constants.globalConfig.setDataBasePath(dbPath);
            Constants.globalConfig.setUser(user);
            Constants.globalConfig.setPassword(password);
        }

        SearchContainer searchContainer = new SearchContainer();
        searchContainer.setKey(key);
        searchContainer.setSeparator(separator);
        searchContainer.setResultType(resultType);

        Starter.run(searchContainer);
    }
}
