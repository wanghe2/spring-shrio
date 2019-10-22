package com.wang.service.impl;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Service;

import com.wang.service.AdminService;
@Service("adminService")
public class AdminServiceImpl implements AdminService{

	@RequiresPermissions("all")
	public void getAuth() {
		System.err.println("拥有所有权限");
	}

	
}
