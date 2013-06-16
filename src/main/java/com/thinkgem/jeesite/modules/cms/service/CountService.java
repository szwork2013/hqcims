/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.cms.dao.CollectingDao;
import com.thinkgem.jeesite.modules.cms.entity.Collecting;
import com.thinkgem.jeesite.modules.cms.entity.Count;

/**
 * 统计Service
 * @author wharlookingfor
 * @version 2013-06-16
 */
@Component
@Transactional(readOnly = true)
public class CountService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(CountService.class);
	
	@Autowired
	private CollectingDao collectingDao;
	
	
	public Page<Collecting> find(Page<Collecting> page, Count count) {
		DetachedCriteria dc = collectingDao.createDetachedCriteria();
		String start=count.getQueryDate()+ " 00:00:00"; 
		String end=count.getQueryDate()+  " 23:59:59"; 
		Date start_date=DateUtils.parseDate(start);
		Date end_date=DateUtils.parseDate(end);
		dc.add(Restrictions.ge("create_date",new Timestamp(start_date.getTime()))); 
		dc.add(Restrictions.le("create_date",new Timestamp(end_date.getTime()))); 
		dc.add(Restrictions.not(Restrictions.eq("flag",1))); 
		dc.addOrder(Order.desc("id"));
		return collectingDao.find(page, dc);
	}
	
	public List<Collecting> find(String queryDate){
		DetachedCriteria dc = collectingDao.createDetachedCriteria();
		String start=queryDate+ " 00:00:00"; 
		String end=queryDate+  " 23:59:59"; 
		Date start_date=DateUtils.parseDate(start);
		Date end_date=DateUtils.parseDate(end);
		dc.add(Restrictions.ge("create_date",new Timestamp(start_date.getTime()))); 
		dc.add(Restrictions.le("create_date",new Timestamp(end_date.getTime()))); 
		dc.add(Restrictions.not(Restrictions.eq("flag",1))); 
		dc.addOrder(Order.asc("flag"));
		return collectingDao.find(dc);
	}
	

	
}
