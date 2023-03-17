package youyanzu.seu.restaurant02.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import youyanzu.seu.restaurant02.entity.SysRole;
import youyanzu.seu.restaurant02.mapper.SysRoleMapper;
import youyanzu.seu.restaurant02.service.ISysRoleService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author summerWang
 * @since 2023-03-15
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
    @Autowired
    private SysRoleMapper mapper;

    @Override
    public Integer getIdByName(String mcName) {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.eq("role_name", mcName);
        SysRole role = null;
        role = mapper.selectOne(wrapper);
        if (role != null)
            return role.getRoleId();
        return null;
    }

    @Override
    public String getNameById(Integer id) {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<SysRole>();
        wrapper.eq("role_id", id);
        SysRole sysRole = mapper.selectOne(wrapper);
        if (sysRole != null)
            return sysRole.getRoleName();
        else
            return null;
    }
}
