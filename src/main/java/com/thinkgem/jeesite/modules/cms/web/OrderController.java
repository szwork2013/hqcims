/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.web;

import java.util.Date;
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
import com.thinkgem.jeesite.modules.cms.entity.OrderDetail;
import com.thinkgem.jeesite.modules.cms.entity.OrderList;
import com.thinkgem.jeesite.modules.cms.service.GoodsService;
import com.thinkgem.jeesite.modules.cms.service.OrderService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 商品Controller
 * @author wharlookingfor
 * @version 2013-05-01
 */
@Controller
@RequestMapping(value = Global.ADMIN_PATH+"/cms/order")
public class OrderController extends BaseController {

	@Autowired
	private OrderService service;
	
	@ModelAttribute
	public Order get(@RequestParam(required=false) Long id) {
		if (id != null){
			return service.get(id);
		}else{
			return new Order();
		}
	}
	
	/** 
	  * @Title: list 
	  * @author lookingfor
	  * @Description:订单查询主页面
	  * @param order
	  * @param request
	  * @param response
	  * @param model
	  * @return   
	  * @throws 
	  */ 
	@RequestMapping(value = {"list", ""})
	public String list(Order order, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Order> page = service.find(new Page<Order>(request, response), order); 
        model.addAttribute("page", page);
		return "modules/cms/orderList";
	}


	/** 
	  * @Title: detailList 
	  * @author lookingfor订单明细的查询
	  * @param id
	  * @param request
	  * @param response
	  * @param model
	  * @return   
	  * @throws 
	  */ 
	@RequestMapping(value = "detailList")
	public String detailList(Long id, HttpServletRequest request, HttpServletResponse response, Model model) {
        List<OrderList> list = service.findDetail(id); 
        Order order=service.get(id);
        model.addAttribute("list", list);
        model.addAttribute("order", order);
		return "modules/cms/orderDetailList";
	}
	
	
	
	/** 
	  * @Title: doReceivable 
	  * @author lookingfor
	  * @Description: 订单进行应收
	  * @param id
	  * @param redirectAttributes
	  * @return   
	  * @throws 
	  */ 
	@RequestMapping(value = "doReceivable")
	public String doReceivable(Long id, RedirectAttributes redirectAttributes) {
		service.doReceivable(id);
		addMessage(redirectAttributes, "操作成功");
		return "redirect:"+Global.ADMIN_PATH+"/cms/order/?repage";
	}
	
	/** 
	  * @Title: delete 
	  * @author lookingfor
	  * @Description: 无效订单的删除
	  * @param id
	  * @param redirectAttributes
	  * @return   
	  * @throws 
	  */ 
	@RequestMapping(value = "delete")
	public String delete(Long id, RedirectAttributes redirectAttributes) {
		service.delete(id);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.ADMIN_PATH+"/cms/order/?repage";
	}

	/** 
	  * @Title: setStatus 
	  * @author lookingfor
	  * @Description: 无效订单设置为有效
	  * @param id
	  * @param redirectAttributes
	  * @return   
	  * @throws 
	  */ 
	@RequestMapping(value = "setStatus")
	public String setStatus(Long id, RedirectAttributes redirectAttributes) {
		service.setStatus(id);
		addMessage(redirectAttributes, "操作成功");
		return "redirect:"+Global.ADMIN_PATH+"/cms/order/?repage";
	}
	
	
	/** 
	  * @Title: saveList 
	  * @author lookingfor
	  * @Description:订单明细的修改保存
	  * @param ids
	  * @param sales
	  * @param nums
	  * @param id
	  * @param redirectAttributes
	  * @return   
	  * @throws 
	  */ 
	@ResponseBody
	@RequestMapping(value = "saveList")
	public String saveList(String ids,String sales,String nums,Long order_id, RedirectAttributes redirectAttributes) {

			try {
				if(ids.length()>0){

				ids=ids.substring(0, ids.length()-1);
				sales=sales.substring(0, sales.length()-1);
				nums=nums.substring(0, nums.length()-1);
				String[] id_=ids.split("@");
				String[] sale_=sales.split("@");
				String[] num_=nums.split("@");

				service.saveList(id_,sale_,num_,order_id);
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
	  * @Description: 无效订单的删除
	  * @param id
	  * @param redirectAttributes
	  * @return   
	  * @throws 
	  */ 
	@RequestMapping(value = "deleteDetail")
	public String deleteDetail(Long id, Long order_id,RedirectAttributes redirectAttributes) {
		int i=service.deleteDetail(id,order_id);
		addMessage(redirectAttributes, "删除成功");
		if(i>0){
			return "redirect:"+Global.ADMIN_PATH+"/cms/order/detailList/?id="+order_id;
		}else{
			return "redirect:"+Global.ADMIN_PATH+"/cms/order/?repage";
		}
		
	}

	
	
}
