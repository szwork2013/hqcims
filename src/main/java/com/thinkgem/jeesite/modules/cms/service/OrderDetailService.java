/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.cms.entity.OrderDetail;
import com.thinkgem.jeesite.modules.cms.mybatis.OrderDetailMybatisDao;

/**
 * 商品Service
 * @author wharlookingfor
 * @version 2013-05-01
 */
@Component
@Transactional(readOnly = true)
public class OrderDetailService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(OrderDetailService.class);
	
	@Autowired
	private OrderDetailMybatisDao dao;

	/**
	 * @param user_id  
	  * @Title: find 
	  * @author lookingfor
	  * @Description: (这里用一句话描述这个方法的作用) 
	  * @param page
	  * @param conusmer_id
	  * @param goods_code
	  * @param goods_name
	  * @return   
	  * @throws 
	  */ 
	public Page<OrderDetail> find(Page<OrderDetail> page, Long conusmer_id,
			String goods_code, String goods_name, Long user_id) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("goods_code", goods_code);
		map.put("goods_name", goods_name);
		map.put("consumer_id", conusmer_id);
		map.put("user_id", user_id);
		int count=dao.queryCountOrderDetail(map);
		page.setCount(count);
		map.put("start", page.getFirstResult());
		map.put("limit", page.getMaxResults());
		List<OrderDetail> list=dao.queryOrderDetail(map);
		
		page.setList(list);
		
		return page;
	}
	

	
	

	
	
}
