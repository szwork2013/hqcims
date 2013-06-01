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
import com.thinkgem.jeesite.modules.cms.entity.Change;
import com.thinkgem.jeesite.modules.cms.service.ChangeService;

/**
 * 换货Controller
 * @author wharlookingfor
 * @version 2013-05-29
 */
@Controller
@RequestMapping(value = Global.ADMIN_PATH+"/cms/change")
public class ChangeController extends BaseController {

	@Autowired
	private ChangeService changeService;
	
	@ModelAttribute
	public Change get(@RequestParam(required=false) Long id) {
		if (id != null){
			return changeService.get(id);
		}else{
			return new Change();
		}
	}

    /**
     * 换货的查询
     * @param change
     * @param request
     * @param response
     * @param model
     * @return
     */
	@RequestMapping(value = {"list", ""})
	public String list(Change change, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Change> page = changeService.find(new Page<Change>(request, response), change); 
        model.addAttribute("page", page);
		return "modules/cms/changeList";
	}


	@RequestMapping(value = "form")
	public String form(Change change, Model model) {
		model.addAttribute("change", change);
		return "modules/cms/changeForm";
	}


	@RequestMapping(value = "save")
	public String save(Change change, Model model, RedirectAttributes redirectAttributes) {
        User currentUser = UserUtils.getUser();
        change.setUser(currentUser);
		if (!beanValidator(model, change)){
			return form(change, model);
		}
		changeService.save(change);
        addMessage(redirectAttributes, "操作成功");
		return "redirect:"+Global.ADMIN_PATH+"/cms/change/?repage";
	}
	

//	@RequestMapping(value = "delete")
//	public String delete(Long id, RedirectAttributes redirectAttributes) {
//		changeService.delete(id);
//		addMessage(redirectAttributes, "删除换货成功");
//		return "redirect:"+Global.ADMIN_PATH+"/modules/cms/change/?repage";
//	}

}
