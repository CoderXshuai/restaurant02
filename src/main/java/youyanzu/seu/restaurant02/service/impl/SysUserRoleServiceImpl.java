package youyanzu.seu.restaurant02.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import youyanzu.seu.restaurant02.entity.SysUserRole;
import youyanzu.seu.restaurant02.mapper.SysUserRoleMapper;
import youyanzu.seu.restaurant02.service.ISysUserRoleService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author summerWang
 * @since 2023-03-15
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {
    @Autowired
    private SysUserRoleMapper mapper;

    @Override
    public boolean insertUser(Long userId, Integer roleId) {
        SysUserRole role = new SysUserRole();
        role.setUserId(userId);
        role.setRoleId(roleId);
        int i = 0;
        i = mapper.insert(role);
        return i == 1;
    }

    @Override
    public SysUserRole getByUserId(Long id) {
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", id);
        return mapper.selectOne(wrapper);
    }

    @Override
    public boolean deleteByUserId(Long id) {
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", id);
        int i = 0;
        i = mapper.delete(wrapper);
        return i == 1;
    }
}
