import exception.BusinessException;
import fruit.DBResultHandler;
import spider.BaseSpider;
import spider.SerialSpider;

import java.sql.SQLException;

public class AppMain {

    public static void main(String[] args) {
//        System.out.println(args);
        BaseSpider spider = new SerialSpider();
        try {
//            spider.start(args[0], new DBResultHandler());
//            spider.start("珍珠项链", new DBResultHandler());
            spider.start("Essential oil aromatherapy", new DBResultHandler());
//            spider.start("Aromatherapy oil", new DBResultHandler());
//            spider.start("Essential Oil", new DBResultHandler());
//            spider.start("Aromatherapy candle", new DBResultHandler());


        } catch (Exception var) {
//            System.out.println(var.getMessage());
            var.printStackTrace();
        }
        System.out.println("spider has started");
    }

}
