/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thinkgem.jeesite.modules.sys.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.modules.sys.dao.MenuDao;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.Menu;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.security.SystemRealm.Principal;

/**
 * 用户工具类
 * @author ThinkGem
 * @version 2013-01-15
 */
@Service
public class UserUtils implements ApplicationContextAware {
	
	private static UserDao userDao;
	private static MenuDao menuDao;
	
	public static User getUser(){
		User user = (User)getCache("user");
		if (user == null){
			Principal principal = (Principal)SecurityUtils.getSubject().getPrincipal();
			if (principal!=null){
				user = userDao.findByLoginName(principal.getLoginName());
				putCache("user", user);
			}
		}
		if (user == null){
			user = new User();
			SecurityUtils.getSubject().logout();
		}
		return user;
	}
	
	public static User getUser(boolean isRefresh){
		if (isRefresh){
			removeCache("user");
		}
		return getUser();
	}

	public static List<Menu> getMenuList(){


			List<Menu> menuList = menuDao.findAllList();
			System.out.println(menuList.size());

		for(int i=0;i<menuList.size();i++){
			Menu me=menuList.get(i);
			System.out.println(me.toString());
		}
		return menuList;
	}
	

	

	

	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext){
		userDao = (UserDao)applicationContext.getBean("userDao");
		menuDao = (MenuDao)applicationContext.getBean("menuDao");
	}
	
	// ============== User Cache ==============
	
	public static Object getCache(String key) {
		Object obj = getCacheMap().get(key);
		return obj==null?null:obj;
	}

	public static void putCache(String key, Object value) {
		getCacheMap().put(key, value);
	}

	public static void removeCache(String key) {
		getCacheMap().remove(key);
	}
	
	private static Map<String, Object> getCacheMap(){
		Principal principal = (Principal)SecurityUtils.getSubject().getPrincipal();
		return principal!=null?principal.getCacheMap():new HashMap<String, Object>();
	}
}
