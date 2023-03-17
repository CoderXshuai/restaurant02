package youyanzu.seu.restaurant02.service;

import com.baomidou.mybatisplus.extension.service.IService;
import youyanzu.seu.restaurant02.entity.SysRole;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author summerWang
 * @since 2023-03-15
 */
public interface ISysRoleService extends IService<SysRole> {
    Integer getIdByName(String mcName);

    String getNameById(Integer id);
}
