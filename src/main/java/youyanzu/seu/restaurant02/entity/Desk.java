package youyanzu.seu.restaurant02.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author CoderXshuai
 * @date 2023/3/16 20:48
 */

@Data
@TableName("r_desk")
public class Desk extends BaseBean {
    /**
     * 餐桌ID
     */
    @TableId(value = "desk_id", type = IdType.AUTO)
    private Integer deskId;
    /**
     * 餐桌编号
     */
    private int deskCode;
    /**
     * 座位人数
     */
    private int peopleCount;
    /**
     * 餐桌状态
     */
    private int idleStatus;
}
