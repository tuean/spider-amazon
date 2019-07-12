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
            spider.start("pearl necklace", new DBResultHandler());
        } catch (Exception var) {
//            System.out.println(var.getMessage());
            var.printStackTrace();
        }
        System.out.println("spider has started");
    }

}
