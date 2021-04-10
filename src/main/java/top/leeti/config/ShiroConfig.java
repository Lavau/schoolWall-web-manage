package top.leeti.config;

import top.leeti.realm.MyRealm;
import top.leeti.util.RoleFilterUtil;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean("hashedCredentialsMatcher")
    public CredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher
                = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5"); //设置加密算法
        hashedCredentialsMatcher.setHashIterations(1024);     //设置加密时迭代（加密）次数
        return hashedCredentialsMatcher;
    }

    @Bean("myRealm")
    public Realm myRealm(@Qualifier("hashedCredentialsMatcher")CredentialsMatcher matcher){
        MyRealm realm = new MyRealm();
        realm.setCredentialsMatcher(matcher);
        return realm;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("myRealm") Realm realm){
        DefaultSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        shiroFilterFactoryBean.setLoginUrl("/admin/login/error");
        shiroFilterFactoryBean.setUnauthorizedUrl("/admin/noAccess");

        // 为 shiroFilterFactoryBean 配置 filter ,满足一个 url 对应多个 role 的需要
        Map<String, Filter> filterMap = new Hashtable<>();
        filterMap.put("roles", new RoleFilterUtil());
        shiroFilterFactoryBean.setFilters(filterMap);

        /*
         * anon：匿名用户可访问
         * authc：认证用户可访问
         * user：使用rememberMe可访问
         * perms：对应权限可访问
         * roles[角色名]：对应角色权限可访问
         */
        //设置访问各 url 的权限
        Map<String, String> filterTochain = new LinkedHashMap<>();
        filterTochain.put("/admin/login", "anon");
        filterTochain.put("/admin/login/error", "anon");
        filterTochain.put("/admin/login/out", "authc");
        filterTochain.put("/admin/index", "authc");
        filterTochain.put("/admin/error", "authc");
        filterTochain.put("/admin/noAccess", "authc");
        filterTochain.put("/admin/ecard/**", "roles[admin, generalAdmin]");
        filterTochain.put("/admin/college/**", "roles[admin, generalAdmin]");
        filterTochain.put("/admin/administrator/**", "roles[admin]");
        filterTochain.put("/admin/administrator", "roles[admin, generalAdmin]");
        filterTochain.put("/admin/lostAndFound/**", "roles[admin, generalAdmin]");
        filterTochain.put("/admin/seekHelp/**", "roles[admin, generalAdmin]");
        filterTochain.put("/admin/mixedManagement/**", "roles[admin, generalAdmin]");
        filterTochain.put("/admin/publicity/**", "roles[admin, generalAdmin]");
        filterTochain.put("/admin/publicity", "roles[admin, generalAdmin]");
        filterTochain.put("/admin/search", "roles[admin, generalAdmin]");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterTochain);

        return shiroFilterFactoryBean;
    }

}
