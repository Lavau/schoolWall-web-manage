package top.leeti.realm;

import top.leeti.service.AdministratorService;
import top.leeti.entity.Administrator;
import top.leeti.util.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class MyRealm extends AuthorizingRealm{

    @Autowired
    private AdministratorService administratorService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Administrator administrator = (Administrator)SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();
        roles.add(administrator.getRoleName());
        info.setRoles(roles);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        Administrator administrator = administratorService.findAdministratorByUsername(username);
        String encryptedPassword = PasswordUtil.encrypt(username, String.valueOf(token.getPassword()));
        if (administrator == null) {
            throw new UnknownAccountException();
        } else if(!administrator.getEnPassword().equals(encryptedPassword)){
            throw new IncorrectCredentialsException();
        } else {
            ByteSource credentialsSalt = ByteSource.Util.bytes(administrator.getUsername());
            return new SimpleAuthenticationInfo(administrator, administrator.getEnPassword(),
                    credentialsSalt, getName());
        }
    }
}
