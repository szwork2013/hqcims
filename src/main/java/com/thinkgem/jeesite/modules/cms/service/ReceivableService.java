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
import com.thinkgem.jeesite.modules.cms.entity.Receivable;
import com.thinkgem.jeesite.modules.cms.dao.ReceivableDao;

/**
 * 应收Service
 * @author wharlookingfor
 * @version 2013-05-12
 */
@Component
@Transactional(readOnly = true)
public class ReceivableService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(ReceivableService.class);
	
	@Autowired
	private ReceivableDao receivableDao;
	
	public Receivable get(Long id) {
		return receivableDao.findOne(id);
	}
	
//	public Page<Receivable> find(Page<Receivable> page, Receivable receivable) {
//		DetachedCriteria dc = receivableDao.createDetachedCriteria();
//		if (receivable.getConsumer()!=null && receivable.getConsumer().getId()>0){
//			dc.add(Restrictions.eq("user.id", receivable.getConsumer().getId()));
//		}
//		if (StringUtils.isNotEmpty(receivable.getName())){
//			dc.add(Restrictions.like("name", "%"+receivable.getName()+"%"));
//		}
//		if (StringUtils.isNotEmpty(receivable.getRemarks())){
//			dc.add(Restrictions.like("remarks", "%"+receivable.getRemarks()+"%"));
//		}
//		dc.add(Restrictions.eq("delFlag", Receivable.DEL_FLAG_NORMAL));
//		dc.addOrder(Order.desc("id"));
//		return receivableDao.find(page, dc);
//	}
	
	@Transactional(readOnly = false)
	public void save(Receivable receivable) {
		receivableDao.save(receivable);
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		receivableDao.deleteById(id);
	}
	
}
