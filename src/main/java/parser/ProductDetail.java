package parser;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetail {

    private String asin;

    private String productName;

    private String price;

    private Integer commentNum;

    private String grade;

    private String picUrl;

    private Integer salesNum;

    private String productDetailUrl;

    private String searchKey;


}
