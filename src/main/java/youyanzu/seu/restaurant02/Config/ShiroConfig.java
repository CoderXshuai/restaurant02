package youyanzu.seu.restaurant02.Config;


import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import youyanzu.seu.restaurant02.Realm.authRealm;

import java.util.LinkedHashMap;
import java.util.Map;
@Configuration
public class ShiroConfig {

    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager manager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        Map<String, String> map = new LinkedHashMap<>();
        /*
        /anon 无需认证
        authc 必须认证
        user 记住我
        perms 拥有权限
        role 拥有角色
         */
//        map.put("/**","authc");
        map.put("/user/login", "anon");
//        map.put("/**","authc");
        bean.setFilterChainDefinitionMap(map);
        bean.setLoginUrl("/user/login");
        return bean;
    }
    @Bean("getDefaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") authRealm userRealm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //使用MD5，3次加密，以明文密码为盐
        matcher.setHashAlgorithmName("MD5");
        matcher.setHashIterations(3);
        userRealm.setCredentialsMatcher(matcher);
        manager.setRealm(userRealm);
        return manager;
    }


    @Bean(name = "userRealm")
    public authRealm userRealm(){
        return new authRealm();
    }
}
