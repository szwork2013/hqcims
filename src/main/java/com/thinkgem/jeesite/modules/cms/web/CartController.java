/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.entity.Cart;
import com.thinkgem.jeesite.modules.cms.entity.CartList;
import com.thinkgem.jeesite.modules.cms.entity.Consumer;
import com.thinkgem.jeesite.modules.cms.entity.Goods;
import com.thinkgem.jeesite.modules.cms.entity.Order;
import com.thinkgem.jeesite.modules.cms.service.CartListService;
import com.thinkgem.jeesite.modules.cms.service.CartService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 购物Controller
 * @author wharlookingfor
 * @version 2013-05-03
 */
@Controller
@RequestMapping(value = Global.ADMIN_PATH+"/cms/cart")
public class CartController extends BaseController {

	@Autowired
	private CartService cartService;
	@Autowired
	private CartListService cartListService;
	
	@ModelAttribute
	public Cart get(@RequestParam(required=false) Long id) {
		if (id != null){
			return cartService.get(id);
		}else{
			return new Cart();
		}
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Cart cart, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		User currentUser = UserUtils.getUser();
		Cart result=cartService.getCartByUser(currentUser.getId());
		if(result!=null){
			 //Page<CartList> page = cartListService.find(new Page<CartList>(request, response)); 
			 List<CartList> list=cartListService.find();
			 if(list.size()>0){
				 model.addAttribute("list", list);
			     model.addAttribute("cart", result);
			     return "modules/cms/cartList";
			 }else{
				addMessage(redirectAttributes, "页面加载失败,当前没有购物车,自动跳转至销售管理");
				return "redirect:"+Global.ADMIN_PATH+"/cms/orderDetail/";
			 }
		    
		}else{
			addMessage(redirectAttributes, "页面加载失败,当前没有购物车,自动跳转至销售管理");
			return "redirect:"+Global.ADMIN_PATH+"/cms/orderDetail/";
		}
       
	}


//	@RequestMapping(value = "form")
//	public String form(Cart cart, Model model) {
//		model.addAttribute("cart", cart);
//		return "modules/cms/cartForm";
//	}

//
	/** 
	  * @Title: save 
	  * @author lookingfor
	  * @Description: 购物车添加
	  * @param goods_ids
	  * @param sales
	  * @param nums
	  * @param consumer_id
	  * @param redirectAttributes
	  * @return   
	  * @throws 
	  */ 
	@ResponseBody
	@RequestMapping(value = "save")
	public String save(String goods_ids,String sales,String nums,Long consumer_id, RedirectAttributes redirectAttributes) {
		
		try {
			User currentUser = UserUtils.getUser();
			Cart result=cartService.getCartByUser(currentUser.getId());
			Cart cart=new Cart();
			if(result!=null){
				cart=result;
				if(consumer_id!=result.getConsumer().getId()){
					Consumer consumer=new Consumer();
					consumer.setId(consumer_id);
					cart.setConsumer(consumer);
					cartService.update(cart);
				}
			}else{
				Consumer consumer=new Consumer();
				consumer.setId(consumer_id);
				cart.setConsumer(consumer);
				cart.setUser_id(currentUser.getId());
				cart=cartService.save(cart);
			}
			goods_ids=goods_ids.substring(0, goods_ids.length()-1);
			sales=sales.substring(0, sales.length()-1);
			nums=nums.substring(0, nums.length()-1);
			String[] good_=goods_ids.split("@");
			String[] sale_=sales.split("@");
			String[] num_=nums.split("@");

			for(int i=0;i<good_.length;i++){
				long goods_id=Long.parseLong(good_[i]);
				float sale=Float.parseFloat(sale_[i]);
				int num=Integer.parseInt(num_[i]);
				CartList list=new CartList();
				Goods good=new Goods();
				good.setId(goods_id);
				list.setGoods(good);
				list.setNum(num);
				list.setSale(sale);
				list.setCart(cart);
				cartListService.save(list);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	/** 
	  * @Title: saveList 
	  * @author lookingfor
	  * @Description:购物车信息的修改保存
	  * @param ids
	  * @param sales
	  * @param nums
	  * @param consumer_id
	  * @param id
	  * @param redirectAttributes
	  * @return   
	  * @throws 
	  */ 
	@ResponseBody
	@RequestMapping(value = "saveList")
	public String saveList(String ids,String sales,String nums,Long consumer_id,Long id, RedirectAttributes redirectAttributes) {

			try {
				User currentUser = UserUtils.getUser();
				Cart cart=new Cart();
				Consumer consumer=new Consumer();
				consumer.setId(consumer_id);
				cart.setConsumer(consumer);
				cart.setUser_id(currentUser.getId());
				cart.setId(id);
				cartService.update(cart);
				if(ids.length()>0){

				ids=ids.substring(0, ids.length()-1);
				sales=sales.substring(0, sales.length()-1);
				nums=nums.substring(0, nums.length()-1);
				String[] id_=ids.split("@");
				String[] sale_=sales.split("@");
				String[] num_=nums.split("@");

				for(int i=0;i<id_.length;i++){
					long detail_id=Long.parseLong(id_[i]);
					float sale=Float.parseFloat(sale_[i]);
					int num=Integer.parseInt(num_[i]);
					CartList list=new CartList();
					list.setId(detail_id);
					list.setNum(num);
					list.setSale(sale);
					list.setCart(cart);
					cartListService.update(list);
				}
				
			}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "fail";
			}
			return "success";
	}
	

	/** 
	  * @Title: delete 
	  * @author lookingfor
	  * @Description: 购物车删除
	  * @param id
	  * @param redirectAttributes
	  * @return   
	  * @throws 
	  */ 
	@RequestMapping(value = "deleteDetail")
	public String delete(Long id, RedirectAttributes redirectAttributes) {
		cartListService.delete(id);
		addMessage(redirectAttributes, "删除购物成功");
		return "redirect:"+Global.ADMIN_PATH+"/cms/cart";
	}
	
	@RequestMapping(value = "deleteAll")
	public String deleteAll(Long id, RedirectAttributes redirectAttributes) {
		cartService.deleteAll(id);
		addMessage(redirectAttributes, "操作成功");
		return "redirect:"+Global.ADMIN_PATH+"/cms/orderDetail";
	}
	
	
	/** 
	  * @Title: saveOrder
	  * @author lookingfor
	  * @Description: 订单确认
	  * @param id
	  * @param redirectAttributes
	  * @return   
	  * @throws 
	  */ 
	@RequestMapping(value = "saveOrder")
	public String saveOrder(Long id,int status, RedirectAttributes redirectAttributes) {
		User currentUser = UserUtils.getUser();
		Order order=cartService.saveOrder(id,currentUser,status);
		addMessage(redirectAttributes, "操作成功");
		return "redirect:"+Global.ADMIN_PATH+"/cms/order/detailList?id="+order.getId();
	}

}
