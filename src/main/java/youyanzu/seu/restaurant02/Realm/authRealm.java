package youyanzu.seu.restaurant02.Realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import youyanzu.seu.restaurant02.entity.SysUser;
import youyanzu.seu.restaurant02.service.ISysMenuService;
import youyanzu.seu.restaurant02.service.ISysRoleService;
import youyanzu.seu.restaurant02.service.ISysUserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class authRealm extends AuthorizingRealm {
    @Autowired
    private ISysRoleService roleService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysMenuService menuService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //权限认证
        //获取用户的输入的账号.
        String loginCode = ((SysUser) SecurityUtils.getSubject().getPrincipal()).getLoginCode();
        //根据账号查询用户
        SysUser user = userService.getUserByUsername(loginCode);
        //授权信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //set roles
        Set<String> roleNames = new HashSet<>();
        roleNames.add(roleService.getNameById(user.getRoleId()));
        info.setRoles(roleNames);
        //set permissions
        List<String> permissions = new ArrayList<>();
        permissions = menuService.getPermissionsByRoleId(user.getRoleId());
        info.addStringPermissions(permissions);
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //登录认证
        String username = authenticationToken.getPrincipal().toString();
        //获取用户
        SysUser user = userService.getUserByUsername(username);
        //获取明文密码
        String password = new String((char[]) authenticationToken.getCredentials());

        return new SimpleAuthenticationInfo(
                user.getLoginCode(),
                user.getPassword(),
                //salt，以明文密码为盐
                ByteSource.Util.bytes(password),
                user.getLoginCode());
    }
}
