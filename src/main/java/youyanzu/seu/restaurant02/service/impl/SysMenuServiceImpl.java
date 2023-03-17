package youyanzu.seu.restaurant02.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import youyanzu.seu.restaurant02.entity.SysMenu;
import youyanzu.seu.restaurant02.mapper.SysMenuMapper;
import youyanzu.seu.restaurant02.mapper.SysRoleMenuMapper;
import youyanzu.seu.restaurant02.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author summerWang
 * @since 2023-03-17
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
    @Autowired
    private SysMenuMapper mapper;
    @Override
    public List<String> getPermissionsByRoleId(int id) {
        List<String> result = mapper.getPermissionsByRoleId((Integer) id);
        return result;
    }
}
