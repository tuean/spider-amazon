package fruit;

import logger.MineLogger;
import parser.ProductDetail;
import settings.Constants;
import util.ExcelUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ExcelResultHandler implements ResultHandler {

    private static List<ProductDetail> data = new LinkedList<>();

    @Override
    public void init() throws ClassNotFoundException {
        init(false);
    }

    @Override
    public void init(boolean needClear) {
        if (data == null) {
            data = new LinkedList<>();
        } else if (needClear){
            data.clear();
        } else {
            // nothing to do
            // keep the data
        }
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
        if (data == null || data.isEmpty()) {
            MineLogger.log("source data is empty");
            return;
        }

        String outPath = Constants.globalConfig.getOutPath();
        File out = new File(outPath);
        if (out.isDirectory()) {
            outPath = outPath + File.separator + Constants.globalConfig.getExcelFileName();
        }

        ExcelUtil.writeToXlsx(outPath, data, ProductDetail.class);
    }





}
