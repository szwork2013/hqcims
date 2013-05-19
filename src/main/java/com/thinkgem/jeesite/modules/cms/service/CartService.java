/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.cms.dao.CartDao;
import com.thinkgem.jeesite.modules.cms.dao.CartListDao;
import com.thinkgem.jeesite.modules.cms.dao.GoodsDao;
import com.thinkgem.jeesite.modules.cms.dao.OrderDao;
import com.thinkgem.jeesite.modules.cms.dao.OrderListDao;
import com.thinkgem.jeesite.modules.cms.entity.Cart;
import com.thinkgem.jeesite.modules.cms.entity.CartList;
import com.thinkgem.jeesite.modules.cms.entity.Consumer;
import com.thinkgem.jeesite.modules.cms.entity.Goods;
import com.thinkgem.jeesite.modules.cms.entity.Order;
import com.thinkgem.jeesite.modules.cms.entity.OrderList;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 购物Service
 * @author wharlookingfor
 * @version 2013-05-03
 */
@Component
@Transactional(readOnly = true)
public class CartService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(CartService.class);
	
	@Autowired
	private CartDao cartDao;
	@Autowired
	private CartListDao cartListDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private OrderListDao orderListDao;
	@Autowired
	private GoodsDao goodsDao;
	
//	@Autowired
//	private OrderDao orderDao;
//	@Autowired
//	private OrderListDao orderListDao;
	
	public Cart get(Long id) {
		return cartDao.findOne(id);
	}
	

	
	public Page<Cart> find(Page<Cart> page, Cart cart) {
		DetachedCriteria dc = cartDao.createDetachedCriteria();
//		if (StringUtils.isNotEmpty(Cart.getName())){
//			dc.add(Restrictions.like("name", "%"+Cart.getName()+"%"));
//		}
//		if (StringUtils.isNotEmpty(Cart.getCode())){
//			dc.add(Restrictions.like("code", "%"+Cart.getCode()+"%"));
//		}
		dc.add(Restrictions.eq("del_flag", Consumer.DEL_FLAG_NORMAL));
		dc.addOrder(org.hibernate.criterion.Order.desc("id"));
		
		return cartDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public Cart save(Cart cart) {
		Cart cart1=cartDao.save(cart);
		return cart1;
	}
//	
//	@Transactional(readOnly = false)
//	public void delete(Long id) {
//		cartDao.deleteById(id);
//	}



	/** 
	  * @Title: get 
	  * @author lookingfor
	  * @Description: 购物车的查询
	  * @return   
	  * @throws 
	  */ 
	public Cart getCartByUser(Long user_id) {
		String sql="select * from cms_cart where del_flag=0 and user_id="+user_id+" order by create_date desc";
		List<Cart> list=cartDao.findBySql(sql);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
		
	}



	/** 
	  * @Title: get 
	  * @author lookingfor
	  * @Description: 购物车的查询
	  * @return   
	  * @throws 
	  */ 
	public Long getCartCountByUser(Long user_id) {
		//Long num=cartDao.getCountCart(user_id);
		Long num=cartListDao.getCountCart(user_id);
		return num;
		
	}
	
	/** 
	  * @Title: update 
	  * @author lookingfor
	  * @Description: 购物车的修改
	  * @param cart
	  * @return   
	  * @throws 
	  */ 
	@Transactional(readOnly = false)
	public void update(Cart cart) {
		cartDao.update(cart.getId(),cart.getConsumer().getId(),cart.getUser_id());
	}



	/**
	 * @param status  
	  * @Title: saveOrder 
	  * @author lookingfor
	  * @Description: (这里用一句话描述这个方法的作用) 
	  * @param id
	  * @param id2   
	  * @throws 
	  */ 
	@Transactional(readOnly = false)
	public Order saveOrder(Long id, User user, int status) {
		//第一步 根据ID从购物车查询数据
		Cart cart=cartDao.findOne(id);
		System.out.println(cart.getChildList().size()+"ssssssssssssssssss");
		//第二步 往订单新增数据  ,并根据状态修改物品
		float total=0;
		Order order=new Order();
		order.setConsumer(cart.getConsumer());
		order.setStatus(status);
		order.setUser(user);
		order =orderDao.save(order);
		
		List<CartList> childList=cart.getChildList();
		for(int i=0;i<childList.size();i++){
			CartList cartList=childList.get(i);
			OrderList child=new OrderList();
			child.setGoods(cartList.getGoods());
			child.setNum(cartList.getNum());
			child.setOrder(order);
			child.setSale(cartList.getSale());
			child=orderListDao.save(child);
			total+=child.getNum()*child.getSale();
			//修改物品的存量
			if(status==0){
				Goods good=child.getGoods();
				int num=good.getNum();
				good.setNum(num-child.getNum());
				good.setUpdate_date(new Date());
				goodsDao.save(good);
			}
			
		}
		
		//修改订单总金额
		order.setTotal(total);
		//orderDao.save(order);
		orderDao.updateTotal(order.getId());
		//第三步 清空购物车
		
		cartListDao.delCartList(id);
		cartDao.delCart(id);
		
		return order;
	}



	/**
	 * @param id  
	  * @Title: deleteAll 
	  * @author lookingfor
	  * @Description:清空购物车
	  * @throws 
	  */ 
	public void deleteAll(Long id) {
		cartListDao.delCartList(id);
		cartDao.delCart(id);
		
	}


	
}
