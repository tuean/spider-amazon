import enums.ResultType;
import org.apache.commons.cli.*;
import settings.Constants;
import starter.Starter;

import java.nio.file.AccessDeniedException;


public class AppMain {

    public static void main(String[] args) throws ParseException, AccessDeniedException {

        String key = "essential oil";
        String separator = ",";
        ResultType resultType = ResultType.excel;

        Constants.init();

        Starter.run(key, separator, resultType);
    }

}
