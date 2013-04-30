/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.service;

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
import com.thinkgem.jeesite.modules.cms.entity.Consumer;
import com.thinkgem.jeesite.modules.cms.dao.ConsumerDao;

/**
 * 客户维护Service
 * @author wharlookingfor
 * @version 2013-04-30
 */
@Component
@Transactional(readOnly = true)
public class ConsumerService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(ConsumerService.class);
	
	@Autowired
	private ConsumerDao consumerDao;
	
	public Consumer get(Long id) {
		return consumerDao.findOne(id);
	}
	
	public Page<Consumer> find(Page<Consumer> page, Consumer consumer) {
		DetachedCriteria dc = consumerDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(consumer.getName())){
			dc.add(Restrictions.like("name", "%"+consumer.getName()+"%"));
		}
		if (StringUtils.isNotEmpty(consumer.getRemarks())){
			dc.add(Restrictions.like("remarks", "%"+consumer.getRemarks()+"%"));
		}
		dc.add(Restrictions.eq("del_flag", Consumer.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return consumerDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(Consumer consumer) {
		consumerDao.save(consumer);
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		consumerDao.deleteById(id);
	}
	
}
