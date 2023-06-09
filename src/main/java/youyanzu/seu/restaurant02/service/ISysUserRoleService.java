package youyanzu.seu.restaurant02.service;

import com.baomidou.mybatisplus.extension.service.IService;
import youyanzu.seu.restaurant02.entity.SysUserRole;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author summerWang
 * @since 2023-03-15
 */
public interface ISysUserRoleService extends IService<SysUserRole> {
    boolean insertUser(Long userId, Integer roleId);

    SysUserRole getByUserId(Long id);

    boolean deleteByUserId(Long id);
}
