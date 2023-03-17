package youyanzu.seu.restaurant02.service;

import youyanzu.seu.restaurant02.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author summerWang
 * @since 2023-03-17
 */
public interface ISysMenuService extends IService<SysMenu> {
    List<String> getPermissionsByRoleId(int id);
}
