package youyanzu.seu.restaurant02.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import youyanzu.seu.restaurant02.entity.SysUser;
import youyanzu.seu.restaurant02.mapper.SysUserMapper;
import youyanzu.seu.restaurant02.service.ISysRoleService;
import youyanzu.seu.restaurant02.service.ISysUserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author summerWang
 * @since 2023-03-14
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Autowired
    private SysUserMapper mapper;
    @Autowired
    private ISysRoleService roleService;

    @Override
    public SysUser getUserByUsername(String username) {
        SysUser user = null;
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("login_code", username);
        user = mapper.selectOne(wrapper);
        return user;
    }

    @Override
    public boolean userExist(String username) {
        SysUser user = null;
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("login_code", username);
        user = mapper.selectOne(wrapper);
        return user != null;
    }

    @Override
    public boolean addUser(SysUser user) {
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), user.getPassword(), 3);
        user.setPassword(md5Hash.toString());
        LocalDateTime now = LocalDateTime.now();
        user.setCreateTime(now);
        int i = 0;
        i = mapper.insert(user);
        return i == 1;
    }

    @Override
    public boolean idExist(String id) {
        SysUser user = null;
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("id_number", id);
        user = mapper.selectOne(wrapper);
        return user != null;
    }

    @Override
    public boolean editUser(String username, SysUser user) {
        UpdateWrapper<SysUser> wrapper = new UpdateWrapper<SysUser>();
        wrapper.eq("login_code", username);
        LocalDateTime now = LocalDateTime.now();
        user.setModifyTime(now);
        int i = 0;
        i = mapper.update(user, wrapper);
        return i == 1;
    }

    @Override
    public boolean deleteByUsername(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("login_code", username);
        int i = 0;
        i = mapper.delete(wrapper);
        return i == 1;
    }

    @Override
    public boolean resetPassword(String username, String password) {
        SysUser user = getUserByUsername(username);
        Md5Hash md5Hash = new Md5Hash(password, password, 3);
        user.setPassword(md5Hash.toString());
        LocalDateTime now = LocalDateTime.now();
        user.setModifyTime(now);
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("login_code", username);
        int i = 0;
        i = mapper.update(user, wrapper);
        return i == 1;
    }

    @Override
    public Page<SysUser> getList(int pageNum, int pageSize, SysUser user, String[] roles) {
        Page<SysUser> page = new Page<SysUser>();
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        if (user.getName() != null)
            wrapper.eq("name", user.getName());
        if (user.getPhone() != null)
            wrapper.eq("phone", user.getPhone());
        if (user.getEmail() != null)
            wrapper.eq("email", user.getEmail());
        if (user.getBirthday() != null) {
            wrapper.eq("birthday", user.getBirthday());
        }
        if (user.getAddress() != null)
            wrapper.eq("address", user.getAddress());
        List<Integer> roleIds = new ArrayList<>();
        if (roles != null)
            if (roles.length != 0) {
                for (String role : roles) {
                    Integer id = roleService.getIdByName(role);
                    roleIds.add(id);
                }
                wrapper.in("role_id", roleIds);
            }
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        Page<SysUser> userPage = mapper.selectPage(page, wrapper);

        return userPage;
    }


}
