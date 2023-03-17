package youyanzu.seu.restaurant02.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author summerWang
 * @since 2023-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单id
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    /**
     * 菜单名
     */
    private String menuName;

    /**
     * 父节点id
     */
    private Long parentId;

    /**
     * 祖先节点id集
     */
    private String parentIds;

    /**
     * 是否显示（0不显示，1显示）
     */
    private Integer isShow;

    /**
     * 权限
     */
    private String permission;

    /**
     * 菜单链接
     */
    private String menuHref;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 菜单权重
     */
    private Integer weight;

    /**
     * 菜单描述
     */
    private String description;

    /**
     * 删除标志(0未删除，1删除)
     */
    private Integer del;


}
