package youyanzu.seu.restaurant02.entity.chart;

import lombok.Data;

/**
 * @author CoderXshuai
 * @date 2023/3/20 14:07
 * @description: 商品销售图类
 */
@Data
public class GoodsSales {
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 销售数量
     */
    private Integer count;
    /**
     * 日期天数
     */
    private Integer day;
    private Integer number;
}
