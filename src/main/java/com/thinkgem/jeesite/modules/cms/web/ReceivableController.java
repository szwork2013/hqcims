/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.entity.Receivable;
import com.thinkgem.jeesite.modules.cms.service.ReceivableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 应收Controller
 * @author wharlookingfor
 * @version 2013-05-12
 */
@Controller
@RequestMapping(value = Global.ADMIN_PATH+"/cms/receivable")
public class ReceivableController extends BaseController {

	@Autowired
	private ReceivableService receivableService;
	
	@ModelAttribute
	public Receivable get(@RequestParam(required=false) Long id) {
		if (id != null){
			return receivableService.get(id);
		}else{
			return new Receivable();
		}
	}

    /**
     * 欠款的应收明细查询
     * @param receivable
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="blist")
    public String blist(Receivable receivable,HttpServletRequest request,HttpServletResponse response,Model model){
        Page<Receivable> page = receivableService.find(new Page<Receivable>(request, response), receivable);
        model.addAttribute("page", page);
        return "modules/cms/breceivableList";
    }

	@RequestMapping(value = {"list", ""})
	public String list(Receivable receivable, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Receivable> page = receivableService.find(new Page<Receivable>(request, response), receivable); 
        model.addAttribute("page", page);
		return "modules/cms/receivableList";
	}


	@RequestMapping(value = "form")
	public String form(Receivable receivable, Model model) {
		model.addAttribute("receivable", receivable);
		return "modules/cms/receivableForm";
	}



	

	

}
