/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thinkgem.jeesite.modules.sys.web;


import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.Menu;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

/**
 * 菜单Controller
 * @author ThinkGem
 * @version 2013-01-15
 */
@Controller
@RequestMapping(value =Global.ADMIN_PATH+"/sys/menu")
public class MenuController extends BaseController {

	@Autowired
	private SystemService systemService;
	
	@ModelAttribute("menu")
	public Menu get(@RequestParam(required=false) Long id) {
		if (id != null){
			return systemService.getMenu(id);
		}else{
			return new Menu();
		}
	}


	


	@RequiresUser
	@RequestMapping(value = "tree")
	public String tree() {
		return "modules/sys/menuTree";
	}
	
//	@RequiresUser
//	@ResponseBody
//	@RequestMapping(value = "treeData")
//	public String treeData(@RequestParam(required=false) Long extId, @RequestParam(required=false) String checkedIds) {
//		response.setContentType("text/html; charset=UTF-8");
//		StringBuilder sb = new StringBuilder("var data={};");
//		List<Menu> list = systemService.findAllMenu();
//		for (int i=0; i<list.size(); i++){
//			Menu e = list.get(i);
//			if (extId == null || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
//				sb.append("data['"+(e.getParent()!=null?e.getParent().getId():"-1")+"_"+e.getId()+"']='text: "+e.getName());
//				if (StringUtils.isNotBlank(checkedIds)){
//					sb.append("; checked: ").append(checkedIds.indexOf(","+e.getId()+",")==-1?"false":"true");
//				}
//				sb.append("';\n");
//			}
//		}
//		return sb.toString();
//	}
}
