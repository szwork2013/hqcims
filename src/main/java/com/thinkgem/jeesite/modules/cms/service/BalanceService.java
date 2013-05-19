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
public class BalanceService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(BalanceService.class);
	
	@Autowired
	private BalanceDao balanceDao;
	
	
	public Balance get(Long id) {
		return balanceDao.findOne(id);
	}
	
//	public Page<Balance> find(Page<Balance> page, Balance balance) {
////		DetachedCriteria dc = balanceDao.createDetachedCriteria();
////		if (balance.getUser()!=null && balance.getUser().getId()>0){
////			dc.add(Restrictions.eq("user.id", balance.getUser().getId()));
////		}
////		if (StringUtils.isNotEmpty(balance.getName())){
////			dc.add(Restrictions.like("name", "%"+balance.getName()+"%"));
////		}
////		if (StringUtils.isNotEmpty(balance.getRemarks())){
////			dc.add(Restrictions.like("remarks", "%"+balance.getRemarks()+"%"));
////		}
////		dc.add(Restrictions.eq("delFlag", Balance.DEL_FLAG_NORMAL));
////		dc.addOrder(Order.desc("id"));
////		return balanceDao.find(page, dc);
//		//page.setSpringPage( balanceDao.findAll(balance.getConsumer().getName(),balance.getConsumer().getCode() ,page.getSpringPage()));
//		page.setSpringPage(balanceDao.findAll(balance.getConsumer().getName(),balance.getConsumer().getCode() ,page.getSpringPage()));
//		return page;
//	}
	
	
//	public Page<Balance1> find(Page<Balance1> page, Balance1 balance) {
//		HashMap<String,Object> map=new HashMap<String,Object>();
//		map.put("code", balance.getCode());
//		map.put("name", balance.getName());
//
//		int count=dao.queryCountBalance(map);
//		page.setCount(count);
//		map.put("start", page.getFirstResult());
//		map.put("limit", page.getMaxResults());
//		List<Balance1> list=dao.queryBalance(map);
//		
//		page.setList(list);
//		
//		return page;
//	}
	
	
	@Transactional(readOnly = false)
	public void save(Balance balance) {
		balanceDao.save(balance);
	}
	
	
}
