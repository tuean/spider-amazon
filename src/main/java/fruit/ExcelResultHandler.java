package fruit;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.EasyExcelTempFile;
import logger.MineLogger;
import parser.ProductDetail;
import settings.Constants;

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
        if (data == null || data.isEmpty()) {
            MineLogger.log("source data is empty");
            return;
        }


        String outPath = Constants.globalConfig.getOutPath();
        ExcelWriter excelWriter = null;
        try {
            excelWriter = new ExcelWriter(new FileOutputStream(outPath), ExcelTypeEnum.XLSX);
            Sheet sheet = new Sheet(1);
            excelWriter.write(data, sheet);
        } catch (Exception e) {
            MineLogger.log("write to excel error");
            MineLogger.log(e);
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }
}
