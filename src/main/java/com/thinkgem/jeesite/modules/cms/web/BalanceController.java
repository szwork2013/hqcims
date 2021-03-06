/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.entity.Balance;
import com.thinkgem.jeesite.modules.cms.service.BalanceService;
import com.thinkgem.jeesite.modules.cms.service.ReceivableService;

/**
 * 欠款Controller
 * @author wharlookingfor
 * @version 2013-05-18
 */
@Controller
@RequestMapping(value = Global.ADMIN_PATH+"/cms/balance")
public class BalanceController extends BaseController {
	@Autowired
	private ReceivableService receivableService;
	@Autowired
	private BalanceService balanceService;
	
	@ModelAttribute
	public Balance get(@RequestParam(required=false) Long id) {
		if (id != null){
			return balanceService.get(id);
		}else{
			return new Balance();
		}
	}
	


	@RequestMapping(value = "form")
	public String form(Balance balance, Model model) {
		model.addAttribute("balance", balance);
		return "modules/cms/balanceForm";
	}


	@RequestMapping(value = "add")
	public String add(Long id,int type, RedirectAttributes redirectAttributes) {
        balanceService.addBalance(id,type);

		addMessage(redirectAttributes, "操作成功");
		return "redirect:"+Global.ADMIN_PATH+"/cms/collecting/?repage";
	}
	

}
