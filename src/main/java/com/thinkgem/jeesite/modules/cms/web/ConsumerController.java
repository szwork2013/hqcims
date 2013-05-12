/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.thinkgem.jeesite.modules.cms.entity.Consumer;
import com.thinkgem.jeesite.modules.cms.service.ConsumerService;

/**
 * 客户维护Controller
 * @author wharlookingfor
 * @version 2013-04-30
 */
@Controller
@RequestMapping(value = Global.ADMIN_PATH+"/cms/consumer")
public class ConsumerController extends BaseController {

	@Autowired
	private ConsumerService consumerService;
	
	@ModelAttribute
	public Consumer get(@RequestParam(required=false) Long id) {
		if (id != null){
			return consumerService.get(id);
		}else{
			return new Consumer();
		}
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Consumer consumer, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Consumer> page = consumerService.find(new Page<Consumer>(request, response), consumer); 
        model.addAttribute("page", page);
		return "modules/cms/consumerList";
	}

	@RequestMapping(value = "selectList")
	public String selectList(Consumer consumer, HttpServletRequest request, HttpServletResponse response, Model model) {
        list(consumer, request, response, model);
		return "modules/cms/consumerSelectList";
	}
	

	@RequestMapping(value = "form")
	public String form(Consumer consumer, Model model) {
		model.addAttribute("consumer", consumer);
		return "modules/cms/consumerForm";
	}

	@RequestMapping(value = "save")
	public String save(Consumer consumer, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, consumer)){
			return form(consumer, model);
		}
		consumerService.save(consumer);
		addMessage(redirectAttributes, "保存客户'" + consumer.getName() + "'成功");
		return "redirect:"+Global.ADMIN_PATH+"/cms/consumer/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(Long id, RedirectAttributes redirectAttributes) {
		consumerService.delete(id);
		addMessage(redirectAttributes, "删除客户成功");
		return "redirect:"+Global.ADMIN_PATH+"/cms/consumer/?repage";
	}

}
