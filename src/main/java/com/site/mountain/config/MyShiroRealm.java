package com.site.mountain.config;

import com.site.mountain.entity.SysPermission;
import com.site.mountain.entity.SysRole;
import com.site.mountain.entity.UserInfo;
import com.site.mountain.service.UserInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {
    //用于用户查询
//    @Autowired
//    private ILoginService loginService;

    @Autowired
    private UserInfoService userInfoService;

    //角色权限和对应权限添加
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String name = (String) principalCollection.getPrimaryPrincipal();
        UserInfo user = new UserInfo();
        user.setName(name);
        //查询用户名称
//        User user = loginService.findByName(name);
        List<UserInfo> list = userInfoService.selectAllUserAndRoles(user);
        UserInfo userInfo = null;
        if(list.size() != 0){
            userInfo = list.get(0);
        }
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (SysRole role : userInfo.getRoleList()) {
//        for (Role role : user.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRole());
//            simpleAuthorizationInfo.addRole(role.getRoleName());
            for (SysPermission permission : userInfo.getPermissions()) {
//            for (Permission permission : role.getPermissions()) {
                //添加权限
                simpleAuthorizationInfo.addStringPermission(permission.getPermission());
            }
        }
        return simpleAuthorizationInfo;
    }

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
//        User user = loginService.findByName(name);
        UserInfo user = new UserInfo();
        user.setUsername(name);
        List<UserInfo> list = userInfoService.selectAllUserAndRoles(user);
        UserInfo userInfo = null;
        if(list.size() != 0){
            userInfo = list.get(0);
        }
        if (userInfo == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, userInfo.getPassword().toString(), getName());
            return simpleAuthenticationInfo;
        }
    }
}
