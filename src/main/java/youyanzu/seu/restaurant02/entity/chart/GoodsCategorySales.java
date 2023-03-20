package youyanzu.seu.restaurant02.entity.chart;

import lombok.Data;

/**
 * @author CoderXshuai
 * @date 2023/3/20 14:12
 */
@Data
public class GoodsCategorySales {
    /**
     * 类别名称
     */
    private String categoryName;
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
