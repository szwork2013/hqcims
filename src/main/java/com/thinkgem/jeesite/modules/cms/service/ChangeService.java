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
import com.thinkgem.jeesite.modules.cms.entity.Change;
import com.thinkgem.jeesite.modules.cms.dao.ChangeDao;

/**
 * 换货Service
 * @author wharlookingfor
 * @version 2013-05-29
 */
@Component
@Transactional(readOnly = true)
public class ChangeService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(ChangeService.class);
	
	@Autowired
	private ChangeDao changeDao;
	
	public Change get(Long id) {
		return changeDao.findOne(id);
	}
	
	public Page<Change> find(Page<Change> page, Change change) {
		DetachedCriteria dc = changeDao.createDetachedCriteria();
        if(change.getConsumer()!=null){
            dc.createAlias("consumer", "consumer");
            if (StringUtils.isNotEmpty(change.getConsumer().getName())){
                dc.add(Restrictions.like("consumer.name", "%"+change.getConsumer().getName()+"%"));
            }
            if (StringUtils.isNotEmpty(change.getConsumer().getCode())){
                dc.add(Restrictions.like("consumer.code", "%"+change.getConsumer().getCode()+"%"));
            }
        }
        if(change.getGoods()!=null){
            dc.createAlias("goods", "goods");
            if (StringUtils.isNotEmpty(change.getGoods().getName())){
                dc.add(Restrictions.like("goods.name", "%"+change.getGoods().getName()+"%"));
            }
            if (StringUtils.isNotEmpty(change.getGoods().getCode())){
                dc.add(Restrictions.like("goods.code", "%"+change.getGoods().getCode()+"%"));
            }
        }
		dc.add(Restrictions.eq("delFlag", Change.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return changeDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(Change change) {
		changeDao.save(change);
	}
	
//	@Transactional(readOnly = false)
//	public void delete(Long id) {
//		changeDao.deleteById(id);
//	}
	
}
