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
import com.thinkgem.jeesite.modules.cms.entity.Cart;
import com.thinkgem.jeesite.modules.cms.entity.Consumer;
import com.thinkgem.jeesite.modules.cms.entity.Goods;
import com.thinkgem.jeesite.modules.cms.entity.OrderDetail;
import com.thinkgem.jeesite.modules.cms.service.CartService;
import com.thinkgem.jeesite.modules.cms.service.GoodsService;
import com.thinkgem.jeesite.modules.cms.service.OrderDetailService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 商品Controller
 * @author wharlookingfor
 * @version 2013-05-01
 */
@Controller
@RequestMapping(value = Global.ADMIN_PATH+"/cms/orderDetail")
public class OrderDetailController extends BaseController {

	@Autowired
	private OrderDetailService service;
	
	@Autowired
	private CartService cartService;
	
	@ModelAttribute
	public OrderDetail get(@RequestParam(required=false) Long id) {
//		if (id != null){
//			return new OrderDetail();
//		}else{
//			return new OrderDetail();
//		}
		return new OrderDetail();
		
	}
	
	
//	@RequestMapping(value = {"list", ""})
//	public String list(Goods goods, HttpServletRequest request, HttpServletResponse response, Model model) {
//        Page<Goods> page = service.find(new Page<Goods>(request, response), goods); 
//        model.addAttribute("page", page);
//		return "modules/cms/goodsList";
//	}


	@RequestMapping(value = "")
	public String list( OrderDetail orderDetail,HttpServletRequest request, HttpServletResponse response, Model model) {
		User currentUser = UserUtils.getUser();
		Long user_id=currentUser.getId();
		Cart result=cartService.getCartByUser(user_id);
		if(result!=null){
			orderDetail.setConsumer_id(result.getConsumer().getId());
			orderDetail.setConsumer_name(result.getConsumer().getName());
		}
		Long conusmer_id=orderDetail.getConsumer_id();
		String goods_name=orderDetail.getGoods_name();
		String goods_code=orderDetail.getGoods_code();
		
		Page<OrderDetail> page = service.find(new Page<OrderDetail>(request, response),  conusmer_id, goods_code, goods_name,user_id); 
		model.addAttribute("page", page);
		return "modules/cms/orderDetail";
	}
	
	
	@RequestMapping(value ="list")
	public String list1( OrderDetail orderDetail,HttpServletRequest request, HttpServletResponse response, Model model) {
//		User currentUser = UserUtils.getUser();
//		Cart result=cartService.getCartByUser(currentUser.getId());
//		if(result!=null){
//			orderDetail.setConsumer_id(result.getConsumer().getId());
//			orderDetail.setConsumer_name(result.getConsumer().getName());
//		}
		User currentUser = UserUtils.getUser();
		Long conusmer_id=orderDetail.getConsumer_id();
		String goods_name=orderDetail.getGoods_name();
		String goods_code=orderDetail.getGoods_code();
		Page<OrderDetail> page = service.find(new Page<OrderDetail>(request, response),  conusmer_id, goods_code, goods_name,currentUser.getId()); 
		model.addAttribute("page", page);
		return "modules/cms/orderDetail";
	}



}
