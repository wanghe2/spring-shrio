package com.wang.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wang.service.AdminService;

@RestController
@RequestMapping("shiro")
public class AuthorizaController {

	@Autowired
	AdminService adminService;
	
	@RequestMapping("deletedata")
	String pubdata() {
		return "删除资源";
	}
	@RequiresPermissions("create")
	@RequestMapping("authcreate")
	String authcreate() {
		return "加入权限验证的创建";
	}
	
	@RequestMapping("authall")
	String authall() {
		adminService.getAuth();
		return "管理员页面，拥有全部权限";
	} 
}
