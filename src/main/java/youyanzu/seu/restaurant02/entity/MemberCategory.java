package youyanzu.seu.restaurant02.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author summerWang
 * @since 2023-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("r_merber_category")
public class MemberCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 会员类型id
     */
    @TableId(value = "mc_id", type = IdType.AUTO)
    private Integer mcId;

    /**
     * 类型名称
     */
    private String mcName;

    /**
     * 享有折扣
     */
    private BigDecimal discount;

    /**
     * 会员需达到的消费金额
     */
    private BigDecimal amount;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(value = "modify_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifyTime;

    /**
     * 删除标志
     */
    private Integer del;


}
