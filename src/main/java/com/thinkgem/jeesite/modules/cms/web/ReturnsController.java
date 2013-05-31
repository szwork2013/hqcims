/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.web;

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
import com.thinkgem.jeesite.modules.cms.entity.Returns;
import com.thinkgem.jeesite.modules.cms.service.ReturnsService;

/**
 * 退货车Controller
 * @author wharlookingfor
 * @version 2013-05-31
 */
@Controller
@RequestMapping(value = Global.ADMIN_PATH+"/cms/returns")
public class ReturnsController extends BaseController {

	@Autowired
	private ReturnsService returnsService;
	
	@ModelAttribute
	public Returns get(@RequestParam(required=false) Long id) {
		if (id != null){
			return returnsService.get(id);
		}else{
			return new Returns();
		}
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Returns returns, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Returns> page = returnsService.find(new Page<Returns>(request, response), returns); 
        model.addAttribute("page", page);
		return "modules/cms/returnsList";
	}


	@RequestMapping(value = "form")
	public String form(Returns returns, Model model) {
		model.addAttribute("returns", returns);
		return "modules/cms/returnsForm";
	}


	@RequestMapping(value = "save")
	public String save(Returns returns, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, returns)){
			return form(returns, model);
		}
		returnsService.save(returns);
		addMessage(redirectAttributes, "保存退货车成功");
		return "redirect:"+Global.ADMIN_PATH+"/modules/cms/returns/?repage";
	}
	

	@RequestMapping(value = "delete")
	public String delete(Long id, RedirectAttributes redirectAttributes) {
		returnsService.delete(id);
		addMessage(redirectAttributes, "删除退货车成功");
		return "redirect:"+Global.ADMIN_PATH+"/modules/cms/returns/?repage";
	}

}
