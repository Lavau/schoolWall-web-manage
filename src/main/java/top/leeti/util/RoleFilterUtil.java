package top.leeti.util;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class RoleFilterUtil extends RolesAuthorizationFilter {

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response,
                                   Object mappedValue){
        Subject subject = getSubject(request, response);
        String[] rolesArray = (String[])mappedValue;

        // 无指定角色时，不检查，直接返回
        if(rolesArray == null || rolesArray.length == 0){
            return true;
        }

        for(String roleName : rolesArray){
            if(subject.hasRole(roleName))
                return true;
        }

        return false;
    }
}
