/** 
 * @Title: ToolsController.java 
 * @Package com.thinkgem.jeesite.modules.cms.web 
 * @Description: (用一句话描述该文件做什么) 
 * @author lookingfor
 * @date 2013-5-2 下午4:18:33 
 * @version V1.0  
 */ 
package com.thinkgem.jeesite.modules.cms.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.PinyinUtils;
import com.thinkgem.jeesite.common.web.BaseController;

/** 
 * @ClassName: ToolsController 
 * @Description: 一些常用方法的封装
 * @author lookingfor
 * @date 2013-5-2 下午4:18:33 
 *  
 */
@Controller
@RequestMapping(value = Global.ADMIN_PATH+"/cms/tools")
public class ToolsController extends BaseController{

	@ResponseBody
	@RequestMapping(value = "getCode")
	public String getCode(@RequestParam(required=false)String name) {
		String code= PinyinUtils.getPinYinHeadChar(name);
		return code;
	}
	
}
