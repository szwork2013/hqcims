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
import com.thinkgem.jeesite.modules.cms.entity.ReturnList;
import com.thinkgem.jeesite.modules.cms.dao.ReturnListDao;

/**
 * 退货车Service
 * @author wharlookingfor
 * @version 2013-05-31
 */
@Component
@Transactional(readOnly = true)
public class ReturnListService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(ReturnListService.class);
	
	@Autowired
	private ReturnListDao returnListDao;
	
	public ReturnList get(Long id) {
		return returnListDao.findOne(id);
	}
	
	public Page<ReturnList> find(Page<ReturnList> page, ReturnList returnList) {
		DetachedCriteria dc = returnListDao.createDetachedCriteria();
		dc.add(Restrictions.eq("delFlag", ReturnList.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return returnListDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(ReturnList returnList) {
		returnListDao.save(returnList);
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		returnListDao.deleteById(id);
	}
	
}
