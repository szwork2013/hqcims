/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.web;

import java.util.HashMap;

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
import com.thinkgem.jeesite.modules.cms.entity.Balance;
import com.thinkgem.jeesite.modules.cms.entity.Balance1;
import com.thinkgem.jeesite.modules.cms.entity.Receivable;
import com.thinkgem.jeesite.modules.cms.service.Balance1Service;
import com.thinkgem.jeesite.modules.cms.service.BalanceService;
import com.thinkgem.jeesite.modules.cms.service.ReceivableService;

/**
 * 欠款Controller
 * @author wharlookingfor
 * @version 2013-05-18
 */
@Controller
@RequestMapping(value = Global.ADMIN_PATH+"/cms/balance1")
public class Balance1Controller extends BaseController {
	@Autowired
	private Balance1Service balanceService;
	
	@ModelAttribute
	public Balance1 get(@RequestParam(required=false) Long id) {

			return new Balance1();
		
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Balance1 balance1, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Balance1> page = balanceService.find(new Page<Balance1>(request, response), balance1); 
        model.addAttribute("page", page);
		return "modules/cms/balanceList";
	}

	

}
