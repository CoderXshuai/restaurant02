package youyanzu.seu.restaurant02.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import youyanzu.seu.restaurant02.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author summerWang
 * @since 2023-03-14
 */
public interface ISysUserService extends IService<SysUser> {
    SysUser getUserByUsername(String username);
    boolean userExist(String username);
    boolean addUser(SysUser user);
    boolean idExist(String id);
    boolean editUser(String username, SysUser user);
    boolean deleteByUsername(String username);
    boolean resetPassword(String username, String password);

    Page<SysUser> getList(int pageNum, int pageSize, SysUser user, String[] roles);
}
