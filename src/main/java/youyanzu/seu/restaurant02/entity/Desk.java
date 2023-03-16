package youyanzu.seu.restaurant02.entity;

import lombok.Data;

/**
 * @author CoderXshuai
 * @date 2023/3/16 20:48
 */

@Data
public class Desk extends BaseBean {
    /**
     * 餐桌ID
     */
    private int deskId;
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
