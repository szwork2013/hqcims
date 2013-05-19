/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.service;

import java.util.HashMap;
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
import com.thinkgem.jeesite.modules.cms.entity.Balance;
import com.thinkgem.jeesite.modules.cms.entity.Balance1;
import com.thinkgem.jeesite.modules.cms.entity.OrderDetail;
import com.thinkgem.jeesite.modules.cms.mybatis.BalanceMybatisDao;
import com.thinkgem.jeesite.modules.cms.mybatis.OrderDetailMybatisDao;
import com.thinkgem.jeesite.modules.cms.dao.BalanceDao;

/**
 * 欠款Service
 * @author wharlookingfor
 * @version 2013-05-18
 */
@Component
@Transactional(readOnly = true)
public class Balance1Service extends BaseService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(Balance1Service.class);
	
	
	@Autowired
	private BalanceMybatisDao dao;

	
	
	public Page<Balance1> find(Page<Balance1> page, Balance1 balance) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("code", balance.getCode());
		map.put("name", balance.getName());

		int count=dao.queryCountBalance(map);
		page.setCount(count);
		map.put("start", page.getFirstResult());
		map.put("limit", page.getMaxResults());
		List<Balance1> list=dao.queryBalance(map);
		
		page.setList(list);
		
		return page;
	}
	

	
}
