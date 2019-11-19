package util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import logger.MineLogger;

import java.util.List;

public class ExcelUtil {


    public static void writeToXlsx(String fileName, List data, Class clazz) {
        ExcelWriter excelWriter = null;
        try {
            // 这里 需要指定写用哪个class去写
            excelWriter = EasyExcel.write(fileName, clazz).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(data, writeSheet);
        } catch (Exception var) {
            MineLogger.log("write to excel error");
            MineLogger.log(var);
        } finally {
            /// 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }


    //    public static void main(String[] args) {
//        String outPath = "/tmp/result.xlsx";
//        List<ProductDetail> data = new ArrayList<>();
//        data.add(ProductDetail.builder().asin("111")
//                .grade("5")
//                .price("11")
//                .searchKey("key")
//                .build());
//        ExcelUtil.writeToXlsx(outPath, data, ProductDetail.class);
//    }

}
