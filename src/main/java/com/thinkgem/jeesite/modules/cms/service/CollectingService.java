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
import com.thinkgem.jeesite.modules.cms.entity.Collecting;
import com.thinkgem.jeesite.modules.cms.dao.CollectingDao;

/**
 * 实收Service
 * @author wharlookingfor
 * @version 2013-05-18
 */
@Component
@Transactional(readOnly = true)
public class CollectingService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(CollectingService.class);
	
	@Autowired
	private CollectingDao collectingDao;
	
	public Collecting get(Long id) {
		return collectingDao.findOne(id);
	}
	
	public Page<Collecting> find(Page<Collecting> page, Collecting collecting) {
		DetachedCriteria dc = collectingDao.createDetachedCriteria();

		if(collecting.getConsumer()!=null){
			dc.createAlias("consumer", "consumer");
            if(collecting.getConsumer().getId()!=null){
                dc.add(Restrictions.eq("consumer.id", collecting.getConsumer().getId()));
            }
			if (StringUtils.isNotEmpty(collecting.getConsumer().getName())){
				dc.add(Restrictions.like("consumer.name", "%"+collecting.getConsumer().getName()+"%"));
			}
			if (StringUtils.isNotEmpty(collecting.getConsumer().getCode())){
				dc.add(Restrictions.like("consumer.code", "%"+collecting.getConsumer().getCode()+"%"));
			}
		}
		dc.add(Restrictions.eq("delFlag", Collecting.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return collectingDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(Collecting collecting) {
		collectingDao.save(collecting);
	}
	

	
}
