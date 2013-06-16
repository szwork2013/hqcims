/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.cms.entity.Collecting;
import com.thinkgem.jeesite.modules.cms.entity.Count;
import com.thinkgem.jeesite.modules.cms.entity.OrderList;
import com.thinkgem.jeesite.modules.cms.service.CountService;

/**
 * 统计Controller
 * @author wharlookingfor
 * @version 2013-06-16
 */
@Controller
@RequestMapping(value = Global.ADMIN_PATH+"/cms/count")
public class CountController extends BaseController {

	@Autowired
	private CountService countService;
	
	@ModelAttribute
	public Count get() {
			return new Count();
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Count count, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Collecting> page = countService.find(new Page<Collecting>(request, response), count); 
        model.addAttribute("page", page);
        model.addAttribute("queryDate", count.getQueryDate());
		return "modules/cms/countList";
	}

	@RequestMapping(value = "print")
	public String print(String queryDate, Model model) {

		List<Collecting> list=countService.find(queryDate);
		model.addAttribute("list", list);
		return "modules/cms/countForm";
	}



	

}
