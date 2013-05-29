/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.service;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.cms.dao.GoodsDao;
import com.thinkgem.jeesite.modules.cms.dao.OrderDao;
import com.thinkgem.jeesite.modules.cms.dao.OrderListDao;
import com.thinkgem.jeesite.modules.cms.dao.ReceivableDao;
import com.thinkgem.jeesite.modules.cms.entity.Cart;
import com.thinkgem.jeesite.modules.cms.entity.Consumer;
import com.thinkgem.jeesite.modules.cms.entity.Goods;
import com.thinkgem.jeesite.modules.cms.entity.Order;
import com.thinkgem.jeesite.modules.cms.entity.OrderList;
import com.thinkgem.jeesite.modules.cms.entity.Receivable;

/**
 * 购物Service
 * @author wharlookingfor
 * @version 2013-05-03
 */
@Component
@Transactional(readOnly = true)
public class OrderService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderListDao orderListDao;
	
	@Autowired
	private ReceivableDao receivableDao;
	@Autowired
	private GoodsDao goodsDao;
	
	
	public Order get(Long id) {
		return orderDao.findOne(id);
	}
	public OrderList getList(Long id) {
		return orderListDao.findOne(id);
	}

	/** 
	  * @Title: find 
	  * @author lookingfor
	  * @Description: 订单的查询
	  * @param page
	  * @param order
	  * @return   
	  * @throws 
	  */ 
	public Page<Order> find(Page<Order> page, Order order) {
		DetachedCriteria dc = orderDao.createDetachedCriteria();
		if(order.getConsumer()!=null){
			dc.createAlias("consumer", "consumer");
			if (StringUtils.isNotEmpty(order.getConsumer().getName())){
				dc.add(Restrictions.like("consumer.name", "%"+order.getConsumer().getName()+"%"));
			}
			if (StringUtils.isNotEmpty(order.getConsumer().getCode())){
				dc.add(Restrictions.like("consumer.code", "%"+order.getConsumer().getCode()+"%"));
			}
		}
		if(order.getStatus()>=0){
			dc.add(Restrictions.eq("status", order.getStatus()));
		}
        if(order.getId()>0){
            dc.add(Restrictions.eq("id", order.getId()));
        }
		dc.add(Restrictions.eq("del_flag", Consumer.DEL_FLAG_NORMAL));
		dc.addOrder(org.hibernate.criterion.Order.desc("create_date"));
		return orderDao.find(page, dc);
//		String qlString="select o from cms_order where o.consumer.name like '%"+order.getConsumer().getName()+"%' and o.consumer.code like '%"+order.getConsumer().getCode()+"%' order o.create_date desc";
//		return orderDao.findBySql(page, qlString);
		
	}

	/** 
	  * @Title: findDetail 
	  * @author lookingfor
	  * @Description: (这里用一句话描述这个方法的作用) 
	  * @param page
	  * @param id
	  * @return   
	  * @throws 
	  */ 
	public Page<OrderList> findDetail(Page<OrderList> page, Long id) {
		DetachedCriteria dc = orderListDao.createDetachedCriteria();
		dc.add(Restrictions.eq("order.id", id));
		dc.addOrder(org.hibernate.criterion.Order.desc("id"));
		return orderListDao.find(page, dc);
	}

	/** 
	  * @Title: findDetail 
	  * @author lookingfor
	  * @Description: (这里用一句话描述这个方法的作用) 
	  * @param id
	  * @return   
	  * @throws 
	  */ 
	public List<OrderList> findDetail(Long id) {	
		return orderListDao.findById(id);
	}

	/** 
	  * @Title: doReceivable 
	  * @author lookingfor
	  * @Description: 订单的应收操作
	  * @param id   
	  * @throws 
	  */ 
	@Transactional(readOnly = false)
	public void doReceivable(Long id) {
		//创建应收数据
		Order order=orderDao.findOne(id);
		Receivable receivable=new Receivable();
		receivable.setAmount(order.getTotal());
		receivable.setConsumer(order.getConsumer());
		receivable.setOrder(order);
		receivableDao.save(receivable);
		//修改状态
		orderDao.updateStatus(id,2);
		
	}
	
	/** 
	  * @Title: delete 
	  * @author lookingfor
	  * @Description:无效订单的删除
	  * @param id   
	  * @throws 
	  */ 
	@Transactional(readOnly = false)
	public void delete(Long id) {
		orderDao.deleteById(id);
	}
	
	/** 
	  * @Title: setStatus 
	  * @author lookingfor
	  * @Description: 无效订单设置有效
	  * @param id   
	  * @throws 
	  */ 
	@Transactional(readOnly = false)
	public void setStatus(Long id) {
		//修改物品存量
		List<OrderList> list=orderListDao.findById(id);
		for(int i=0;i<list.size();i++){
			OrderList orderList=list.get(i);
			Goods good=orderList.getGoods();
			int num=good.getNum();
			good.setNum(num-orderList.getNum());
			good.setUpdate_date(new Date());
			goodsDao.save(good);
		}
		//修改状态
		orderDao.updateStatus(id,0);
		
	}
	

	/** 
	  * @Title: setStatus 
	  * @author lookingfor
	  * @Description: 有效订单设置无效
	  * @param id   
	  * @throws 
	  */ 
	@Transactional(readOnly = false)
	public void setStatus1(Long id) {
		//修改物品存量
		List<OrderList> list=orderListDao.findById(id);
		for(int i=0;i<list.size();i++){
			OrderList orderList=list.get(i);
			Goods good=orderList.getGoods();
			int num=good.getNum();
			good.setNum(num+orderList.getNum());
			good.setUpdate_date(new Date());
			goodsDao.save(good);
		}
		//修改状态
		orderDao.updateStatus(id,1);
		
	}
	/** 
	  * @Title: saveOrderList 
	  * @author lookingfor
	  * @Description: (这里用一句话描述这个方法的作用) 
	  * @param orderList   
	  * @throws 
	  */ 
	@Transactional(readOnly = false)
	public void saveOrderList(OrderList orderList) {
		orderListDao.save(orderList);
		
	}
	/** 
	  * @Title: saveList 
	  * @author lookingfor
	  * @Description: (这里用一句话描述这个方法的作用) 
	  * @param id_
	  * @param sale_
	  * @param num_
	  * @param order_id   
	  * @throws 
	  */ 
	@Transactional(readOnly = false)
	public void saveList(String[] id_, String[] sale_, String[] num_,Long order_id) {
		for(int i=0;i<id_.length;i++){
			long detail_id=Long.parseLong(id_[i]);
			float sale=Float.parseFloat(sale_[i]);
			int num=Integer.parseInt(num_[i]);
			
			//获取订单明细信息
			OrderList orderList=orderListDao.findOne(detail_id);
			//修改物品存量
			Goods good=orderList.getGoods();
			int update_num=good.getNum()+orderList.getNum()-num;
			good.setNum(update_num);
			good.setUpdate_date(new Date());
			//修改明细数据
			orderList.setGoods(good);
			orderList.setNum(num);
			orderList.setSale(sale);
			orderListDao.save(orderList);
		}
		//修改主订单金额
		orderDao.updateTotal(order_id);
	}
	/** 
	  * @Title: deleteDetail 
	  * @author lookingfor
	  * @Description: 订单明细的删除
	  * @param id
	  * @param order_id   
	  * @throws 
	  */ 
	@Transactional(readOnly = false)
	public int deleteDetail(Long id, Long order_id) {
		//获取订单明细信息
		OrderList orderList=orderListDao.findOne(id);
		//修改物品存量
		Goods good=orderList.getGoods();
		int update_num=good.getNum()+orderList.getNum();
		good.setNum(update_num);
		good.setUpdate_date(new Date());
		goodsDao.save(good);
		//删除明细数据
		orderListDao.deleteById(id);
		//查询该订单的明细数据
		List<OrderList> list=orderListDao.findById(order_id);
		if(list.size()>0){
			//修改主订单金额
			orderDao.updateTotal(order_id);
			return 1;
		}else{
			orderDao.deleteById(order_id);
			return 0;
		}
		
	}
	

	
//	public Page<Cart> find(Page<Cart> page, Cart cart) {
//		DetachedCriteria dc = cartDao.createDetachedCriteria();
////		if (StringUtils.isNotEmpty(Cart.getName())){
////			dc.add(Restrictions.like("name", "%"+Cart.getName()+"%"));
////		}
////		if (StringUtils.isNotEmpty(Cart.getCode())){
////			dc.add(Restrictions.like("code", "%"+Cart.getCode()+"%"));
////		}
//		dc.add(Restrictions.eq("del_flag", Consumer.DEL_FLAG_NORMAL));
//		dc.addOrder(Order.desc("id"));
//		
//		return cartDao.find(page, dc);
//	}
	
//	@Transactional(readOnly = false)
//	public Cart save(Cart cart) {
//		Cart cart1=cartDao.save(cart);
//		return cart1;
//	}
//	





	
}
