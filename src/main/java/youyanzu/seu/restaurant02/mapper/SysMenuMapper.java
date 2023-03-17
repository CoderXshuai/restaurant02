package youyanzu.seu.restaurant02.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import youyanzu.seu.restaurant02.entity.SysMenu;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author summerWang
 * @since 2023-03-17
 */

public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<String> getPermissionsByRoleId(Integer id);
}
