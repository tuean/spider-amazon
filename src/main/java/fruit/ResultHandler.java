package fruit;

import parser.ProductDetail;

import java.sql.SQLException;
import java.util.List;

public interface ResultHandler {

    void init() throws ClassNotFoundException;

    void handleResult(List<ProductDetail> list) throws ClassNotFoundException, SQLException;

}
