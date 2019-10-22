package com.wang.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShiroController {
	
	@RequestMapping("/login")
	public String shiroLogin(HttpServletRequest request,HttpServletResponse response,@RequestParam String username,@RequestParam String pwd) {
		Subject currentUser= SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(username, pwd);
		try {
			//没有登录的用户进行登录
			if (!currentUser.isAuthenticated()) {
				currentUser.login(token);
			}
		} catch (UnknownAccountException uae) {
			return "用户不存在";
		} catch (IncorrectCredentialsException ice) {
			return "密码不正确";
		} catch (LockedAccountException lae) {
			return "用户被禁用";
		}catch (Exception ex) {
			return "未知错误，无法完成登录";
		}
		return "进入首页";
	}
	
	
	@RequestMapping("loginpage")
	public String loginpage() {
		return "登录页面-用户-密码";
	}
	
	
	@RequestMapping("/logout")
	public String shiroLogout(HttpServletRequest request,HttpServletResponse response) {
		Subject currentUser= SecurityUtils.getSubject();
		currentUser.logout();
		return "注销成功";
	}
	

	@RequestMapping("/home")
	public String home() {
		return "首页内容";
	}
	
	@RequestMapping("/unauthorized")
	public String unauthorized() {
		return "没有权限";
	}	
}
