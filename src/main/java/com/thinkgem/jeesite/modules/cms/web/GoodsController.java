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
import com.thinkgem.jeesite.modules.cms.entity.Goods;
import com.thinkgem.jeesite.modules.cms.entity.OrderDetail;
import com.thinkgem.jeesite.modules.cms.service.GoodsService;

/**
 * 商品Controller
 * @author wharlookingfor
 * @version 2013-05-01
 */
@Controller
@RequestMapping(value = Global.ADMIN_PATH+"/cms/goods")
public class GoodsController extends BaseController {

	@Autowired
	private GoodsService goodsService;
	
	@ModelAttribute
	public Goods get(@RequestParam(required=false) Long id) {
		if (id != null){
			return goodsService.get(id);
		}else{
			return new Goods();
		}
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Goods goods, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Goods> page = goodsService.find(new Page<Goods>(request, response), goods); 
        model.addAttribute("page", page);
		return "modules/cms/goodsList";
	}


	

	@RequestMapping(value = "form")
	public String form(Goods goods, Model model) {
		model.addAttribute("goods", goods);
		return "modules/cms/goodsForm";
	}


	@RequestMapping(value = "save")
	public String save(Goods goods, Model model, RedirectAttributes redirectAttributes) {
		System.out.println("验证之前");
		if (!beanValidator(model, goods)){
			return form(goods, model);
		}
		goodsService.save(goods);
		addMessage(redirectAttributes, "保存商品'" + goods.getName() + "'成功");
		return "redirect:"+Global.ADMIN_PATH+"/cms/goods/?repage";
	}
	

	@RequestMapping(value = "delete")
	public String delete(Long id, RedirectAttributes redirectAttributes) {
		goodsService.delete(id);
		addMessage(redirectAttributes, "删除商品成功");
		return "redirect:"+Global.ADMIN_PATH+"/cms/goods/?repage";
	}

}
