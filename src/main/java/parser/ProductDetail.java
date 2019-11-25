package parser;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetail extends BaseRowModel {

    @ExcelProperty("asin")
    private String asin;

    @ExcelProperty("产品名称")
    private String productName;

    @ExcelProperty("价格")
    private String price;

    @ExcelProperty("评论数")
    private Integer commentNum;

    @ExcelProperty("评级")
    private String grade;

    @ExcelProperty("图片地址")
    private String picUrl;

//    @ExcelProperty("销售数量")
    @ExcelIgnore
    private Integer salesNum;

    @ExcelProperty("产品详情页")
    private String productDetailUrl;

    @ExcelProperty("搜索词")
    private String searchKey;


}
