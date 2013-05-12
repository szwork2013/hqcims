/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.cms.entity.CartList;
import com.thinkgem.jeesite.modules.cms.dao.CartListDao;

/**
 * 购物车Service
 * @author wharlookingfor
 * @version 2013-05-03
 */
@Component
@Transactional(readOnly = true)
public class CartListService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(CartListService.class);
	
	@Autowired
	private CartListDao cartListDao;
	
	public CartList get(Long id) {
		return cartListDao.findOne(id);
	}
	
	
	public Page<CartList> find(Page<CartList> page) {
		DetachedCriteria dc = cartListDao.createDetachedCriteria();
		dc.addOrder(Order.desc("id"));
		return cartListDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(CartList cartList) {
		cartListDao.save(cartList);
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		cartListDao.deleteById(id);
	}


	/** 
	  * @Title: update 
	  * @author lookingfor
	  * @Description: (这里用一句话描述这个方法的作用) 
	  * @param list   
	  * @throws 
	  */ 
	@Transactional(readOnly = false)
	public void update(CartList list) {
		cartListDao.update(list.getId(),list.getSale(),list.getNum());
		
	}


	/** 
	  * @Title: find 
	  * @author lookingfor
	  * @Description: (这里用一句话描述这个方法的作用) 
	  * @return   
	  * @throws 
	  */ 
	public List<CartList> find() {
		return cartListDao.findDetail();
	}





	
}
