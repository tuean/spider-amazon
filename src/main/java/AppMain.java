import enums.ResultType;
import org.apache.commons.cli.*;
import starter.Starter;


public class AppMain {

    public static void main(String[] args) throws ParseException {

        String key = "earring,Bohemia";
        String separator = ",";
        ResultType resultType = ResultType.db;

        Starter.run(key, separator, resultType);
    }

}
