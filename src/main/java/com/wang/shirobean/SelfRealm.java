package com.wang.shirobean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
/**
 * 自定义的realm ，这里的认证走数据库查询， 不再使用shiro.ini中定义的用户密码
 * 这时采用的 配置文件是shiro-realm.ini
 * @author wanghe
 *
 */
public class SelfRealm extends AuthorizingRealm{
	
	private final String realmName="selfRealm";
	
	
	public  SelfRealm() {
		initCredenticalsMatcher();
	}
	/**
	 * 初始化凭证匹配器（其实就是加密算法，这里采用MD5）
	 */
	void initCredenticalsMatcher() {
		HashedCredentialsMatcher credentialsMatcher=new HashedCredentialsMatcher();
		credentialsMatcher.setHashAlgorithmName("md5");
		credentialsMatcher.setHashIterations(1);
		this.setCredentialsMatcher(credentialsMatcher);
	}
	
	/**
	 * 授权---当验证权限时会调用该方法(权限的验证可以从permission和role去考虑)
	 * 所以给当前用户查询权限时也包括role和permission
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		System.out.println("进入角色授权");
		String username = (String) getAvailablePrincipal(principalCollection);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		List<String> roles = getRolesByUser(username);
		info.setRoles(new HashSet<String>(roles));
		List<String>permissions=getPermissionByRoles(roles);
		info.setStringPermissions(new HashSet<String>(permissions));
		return  info;
	}
	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		String username=(String)authenticationToken.getPrincipal();
		String result=queryByDBMd5(username);
		if("no_user".equals(result)) {
			return null;
		}
		/**
		 * 返回认证信息，由父类AuthorizingRealm继续认证
		 * （result其实返回的就是数据库里的密码），在subject.login(token)时，会验证密码
		 */
		SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(username, result, realmName);
		return authenticationInfo;
	}
	/**
	 * 模拟数据库
	 * @param username
	 * @return
	 */
	String queryByDB(String username) {
		List<String>users= Arrays.asList("wanghe","onesister");
		if(!users.contains(username)) {
			return "no_user";
		}
		if("wanghe".equals(username)) {
			return "654321";
		}
		return "123456";
	}
	/**
	 * 加密后的数据
	 * @param username
	 * @return
	 */
	String queryByDBMd5(String username) {
		List<String>users= Arrays.asList("wanghe","onesister");
		if(!users.contains(username)) {
			return "no_user";
		}
		if("wanghe".equals(username)) {
			return "96e79218965eb72c92a549dd5a330112";
		}
		return "96e79218965eb72c92a549dd5a330112";
	}
	/**
	 * 根据用户获取角色
	 * @param username
	 * @return
	 */
	List<String>getRolesByUser(String username){
		if("wanghe".equals(username)) {
			return Arrays.asList("admin","creator","editor");
		}
		if("onesister".equals(username)) {
			return Arrays.asList("editor","viewer","deleter");
		}
		return new ArrayList<String>();
	}
	/**
	 * 根据角色获取权限（实际中可以用数据库实现）
	 * @param roles
	 * @return
	 */
	List<String>getPermissionByRoles(List<String>roles){
		List<String>permissions=new ArrayList<String>();
		for(String role:roles) {
			if("admin".equals(role)) {
				permissions.add("all");
			}
			if("creator".equals(role)) {
				permissions.add("create");
			}
			if("editor".equals(role)) {
				permissions.add("edit");
			}
			if("viewer".equals(role)) {
				permissions.add("view");
			}
			if("deleter".equals(role)) {
				permissions.add("delete");
			}
		}
		return permissions;
	}
}
