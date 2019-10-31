package fruit;

import enums.ResultType;
import parser.ProductDetail;

import java.sql.SQLException;
import java.util.List;

public interface ResultHandler {

    void init() throws ClassNotFoundException;

    void handleResult(List<ProductDetail> list) throws ClassNotFoundException, SQLException;

    void finish();

    static ResultHandler getByType(ResultType resultType) {
        switch (resultType) {
            case db:
                return new DBResultHandler();
            case excel:
                return new ExcelResultHandler();
        }
        return null;
    }
}
