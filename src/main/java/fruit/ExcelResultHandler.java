package fruit;

import parser.ProductDetail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ExcelResultHandler implements ResultHandler {

    private static List<ProductDetail> data = new LinkedList<>();

    @Override
    public void init() throws ClassNotFoundException {
        data = new ArrayList<>();
    }

    private void add(ProductDetail detail) {
        if (detail.getAsin() == null) {
            data.add(detail);
        }
        boolean exist = false;
        for (ProductDetail d : data) {
            if (detail.getAsin().equals(d.getAsin())) {
                exist = true;
                break;
            }
        }

        if (!exist) {
            data.add(detail);
        }
    }

    @Override
    public void handleResult(List<ProductDetail> list) throws ClassNotFoundException, SQLException {
        if (list == null || list.size() < 1) return;
        list.forEach(this::add);
    }

    @Override
    public void finish() {
        if (data == null) {

        }
    }
}
