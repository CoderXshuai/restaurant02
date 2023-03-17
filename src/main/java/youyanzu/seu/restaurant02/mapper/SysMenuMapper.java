package youyanzu.seu.restaurant02.mapper;

import org.apache.ibatis.annotations.Mapper;
import youyanzu.seu.restaurant02.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author summerWang
 * @since 2023-03-17
 */

public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<String> getPermissionsByRoleId(Integer id);
}
