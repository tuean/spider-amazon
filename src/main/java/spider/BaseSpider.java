package spider;

import exception.BusinessException;
import fruit.ResultHandler;
import settings.GlobalConfig;

import java.sql.SQLException;

public interface BaseSpider {

    void start(String key, ResultHandler resultHandler) throws BusinessException, SQLException, ClassNotFoundException, InterruptedException;

}
