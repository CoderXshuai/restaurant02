package youyanzu.seu.restaurant02.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDate;
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
@TableName("r_member")
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 会员id
     */
    @TableId(value = "member_id", type = IdType.AUTO)
    private Long memberId;

    /**
     * 会员号（用手机号做会员号）
     */
    private String memberCode;

    /**
     * 顾客姓名
     */
    private String mName;

    /**
     * 性别
     */
    private String mGender;

    /**
     * 生日
     */
    private LocalDate mBirthday;

    /**
     * 电话
     */
    private String mPhone;

    /**
     * 会员类型id外键
     */
    private Integer mcId;

    /**
     * 该会员总消费金额
     */
    private Double totalMoney;

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
