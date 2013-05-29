/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.entity.Balance;
import com.thinkgem.jeesite.modules.cms.entity.Collecting;
import com.thinkgem.jeesite.modules.cms.entity.Consumer;
import com.thinkgem.jeesite.modules.cms.entity.Receivable;
import com.thinkgem.jeesite.modules.cms.service.BalanceService;
import com.thinkgem.jeesite.modules.cms.service.CollectingService;
import com.thinkgem.jeesite.modules.cms.service.ReceivableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 实收Controller
 * @author wharlookingfor
 * @version 2013-05-18
 */
@Controller
@RequestMapping(value = Global.ADMIN_PATH+"/cms/collecting")
public class CollectingController extends BaseController {

	@Autowired
	private CollectingService collectingService;
	@Autowired
	private ReceivableService receivableService;
	@Autowired
	private BalanceService balanceService;
	
	@ModelAttribute
	public Collecting get(@RequestParam(required=false) Long id) {
		if (id != null){
			return collectingService.get(id);
		}else{
			return new Collecting();
		}
	}
    @RequestMapping(value="blist")
    public String blist (Collecting collecting,HttpServletRequest request,HttpServletResponse response,Model model){
        Page<Collecting> page = collectingService.find(new Page<Collecting>(request, response), collecting);
        model.addAttribute("page", page);
        return "modules/cms/bcollectingList";
    }

	@RequestMapping(value = {"list", ""})
	public String list(Collecting collecting, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Collecting> page = collectingService.find(new Page<Collecting>(request, response), collecting); 
        model.addAttribute("page", page);
		return "modules/cms/collectingList";
	}


	@RequestMapping(value = "form")
	public String form(Collecting collecting, Model model) {
		model.addAttribute("collecting", collecting);
		return "modules/cms/balanceForm";
	}

    @RequestMapping(value = "save1")
    public String save1(Long consumer_id,Float amount,RedirectAttributes redirectAttributes) {
        Collecting c=new Collecting();
        c.setAmount(amount);
        c.setAmount1(0);
        Consumer consumer=new Consumer();
        consumer.setId(consumer_id);
        c.setConsumer(consumer);
        c.setFlag(1);
        collectingService.save(c);
        Balance b=new Balance();
        b.setConsumer(consumer);
        b.setAmount(0-amount);
        balanceService.save(b);

        addMessage(redirectAttributes, "保存成功");
        return "redirect:"+Global.ADMIN_PATH+"/cms/balance1/?repage";
    }

	@RequestMapping(value = "save")
	public String save(Long id,Float amount, Float amount1,int if_checked,RedirectAttributes redirectAttributes) {
		
		System.out.println(id+"d");
		Receivable receivable=receivableService.get(id);
		Collecting c=new Collecting();
		if(if_checked!=1){
			c.setAmount1(0);
		}else{
			c.setAmount1(amount1);
		}

		
		c.setReceivable(receivable);
		c.setConsumer(receivable.getConsumer());
		c.setAmount(amount);
		
		c.setFlag(0);
		collectingService.save(c);
		//如果amount1不等于0,小额调整部分走欠款
		if(if_checked!=1){
			Balance b=new Balance();
			b.setConsumer(receivable.getConsumer());
			b.setAmount(amount1);
			balanceService.save(b);
		}
		receivable.setStatus(1);
		receivableService.save(receivable);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.ADMIN_PATH+"/cms/receivable/?repage";
	}

	



}
